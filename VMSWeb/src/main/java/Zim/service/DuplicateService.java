package Zim.service;

import Zim.common.SystemHelper;
import Zim.model.Applicant;
import Zim.model.ApplicantLog;
import Zim.model.Duplicate;
import Zim.model.modelview.DuplicateAction;
import Zim.model.modelview.req.PagingQuery;

import Zim.model.modelview.res.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Laxton-Joe on 2017/2/20.
 */

@Service
public class DuplicateService extends BaseService<Duplicate> {
    @Autowired
    MongoTemplate mongoTemplate;

    public PageResponse<Duplicate> pageList(PagingQuery pagingQuery) {
        PageResponse<Duplicate> result = new PageResponse<Duplicate>();
        try {
            Query query = new Query();
            sortQuery(pagingQuery, query);
            if (pagingQuery.getFilters() != null) {
                setCriteria(pagingQuery, query);
            }
            long total = mongoTemplate.count(query, Duplicate.class);
            if (total > 0) {
                setPaging(result, pagingQuery, query,Integer.valueOf(String.valueOf(total)) );
                List<Duplicate> listData = mongoTemplate.find(query, Duplicate.class);
                result.setCurrentPage(pagingQuery.getCurrentPage());//当前页
                result.setItems(listData);//查询内容
            }
            result.setResult(true);
        } catch (Exception e) {
            result.setResult(false);
        }
        return result;
    }

    public String action(DuplicateAction action) {
        String msg = "";
        try {

            String dupId = action.getDuplicateId();
            Duplicate duplicate = mongoTemplate.findById(dupId, Duplicate.class);
            Applicant probe = null;
            Applicant reference = null;
            if (duplicate.getStatus().equals("Close")) {
                msg = "duplicate has been close";
            } else {
                probe = mongoTemplate.findById(duplicate.getProbeId(), Applicant.class);
                reference = mongoTemplate.findById(duplicate.getReferenceId(), Applicant.class);
                switch (action.getAction()) {
                    case "positive"://duplicate-close
                        duplicate.setStatus("Close");
                        mongoTemplate.save(duplicate, "Duplicate");

                        ApplicantLog appLog = new ApplicantLog();
                        appLog.setReferenceId(duplicate.getReferenceId());
                        appLog.setProbeId(duplicate.getProbeId());
                        StringBuilder sb = new StringBuilder();
                        sb.append(String.format("Trigger Duplicate:%s,Action:Positive;", duplicate.get_id()));
                        sb.append(String.format("Duplicate: %s Close;", duplicate.get_id()));
                        sb.append(String.format("Applicant: %s -> %s;", probe.get_id(), SystemHelper.getApplicantStatusString(probe.getStatus())));
                        sb.append(String.format("Applicant: %s -> %s.", reference.get_id(), SystemHelper.getApplicantStatusString(reference.getStatus())));
                        appLog.setLogEvent(sb.toString());
//                        appLog.setLogEvent(String.format("action:Positive, Duplicate: %s Close.%s->%s,%s->%s", duplicate.get_id(),
//                                probe.getId(), probe.getStatus(), reference.getId(), reference.getStatus()
//                        ));
                        appLog.setLogTime(new Date());
                        mongoTemplate.insert(appLog, "ApplicantLog");
                        break;
                    case "matched":
                        probe.setStatus(Short.parseShort("2"));

                        mongoTemplate.save(probe);
                        //查找 IndividualsId==probe.IndividualsId的数据，更改IndividualsId=reference。IndividualsId的数据
                        //   setApplicantIndividualsId(probe.getIndividualsId(), reference.getIndividualsId());
                        setApplicantAutoClose("Matched", dupId, probe.get_id(), "Suspect");
                        break;
                    case "update": // probe to master,reference to archive
                        reference.setStatus(Short.parseShort("3"));

                        mongoTemplate.save(reference);
                        //setApplicantIndividualsId(reference.getIndividualsId(), probe.getIndividualsId());
                        setApplicantAutoClose("Update", dupId, reference.get_id(), "Archive");
                        break;
                    case "archive":// probe to archive,reference to master
                        probe.setStatus(Short.parseShort("3"));
                        mongoTemplate.save(probe);
                        setApplicantAutoClose("Archive", dupId, probe.get_id(), "Archive");
                        //  setApplicantIndividualsId(probe.getIndividualsId(), reference.getIndividualsId());
                        break;
                    default:
                        throw new Exception("not this action");

                }
//                duplicate.setStatus("Close");
//                mongoTemplate.save(duplicate, "Duplicate");

//                appLog.setReferenceId(duplicate.getReferenceId());
//                appLog.setProbeId(duplicate.getProbeId());
//                appLog.setLogEvent(String.format("Duplicate: %s Close.%s->%s,%s->%s", duplicate.get_id(),
//                        probe.getId(), probe.getStatus(), reference.getId(), reference.getStatus()
//                ));
//                appLog.setLogTime(new Date());
//
//
//                mongoTemplate.insert(appLog, "ApplicantLog");
            }
        } catch (Exception e) {
            msg = e.getMessage();
        }
        return msg;
    }

    private void setApplicantAutoClose(String action, String triggerDupId, String appId, String newStatus) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.orOperator(Criteria.where("probeId").is(appId), Criteria.where("referenceId").is(appId));
        criteria.and("status").is("Pending");
        query.addCriteria(criteria);
        if (mongoTemplate.count(query, Duplicate.class) > 0) {
            List<Duplicate> listData = mongoTemplate.find(query, Duplicate.class);
            for (Duplicate dup : listData) {
                Applicant probe = mongoTemplate.findById(dup.getProbeId(), Applicant.class);
                Applicant reference = mongoTemplate.findById(dup.getReferenceId(), Applicant.class);
                if ((probe.getStatus() == 1 && reference.getStatus() == 1) == false) {
                    dup.setStatus("Close");
                    mongoTemplate.save(dup);
                    ApplicantLog appLog = new ApplicantLog();
                    appLog.setReferenceId(dup.getReferenceId());
                    appLog.setProbeId(dup.getProbeId());
                    StringBuilder sb = new StringBuilder();
                    sb.append(String.format("Trigger Duplicate:%s,Action:%s;", triggerDupId, action));
                    sb.append(String.format("Duplicate: %s Close;", dup.get_id()));

                    if (probe.getStatus() == 1) {
                        sb.append(String.format("Applicant: %s -> %s;", probe.get_id(), SystemHelper.getApplicantStatusString(probe.getStatus())));
                        sb.append(String.format("Applicant: %s -> %s.", reference.get_id(), newStatus));
                    } else {
                        sb.append(String.format("Applicant: %s -> %s;", probe.get_id(), newStatus));
                        sb.append(String.format("Applicant: %s -> %s.", reference.get_id(), SystemHelper.getApplicantStatusString(reference.getStatus())));
                    }
                    appLog.setLogEvent(sb.toString());
                    appLog.setLogTime(new Date());
                    mongoTemplate.insert(appLog, "ApplicantLog");
                }
            }
        }
    }

    public Duplicate find(String duplicateId) {
        Duplicate result = null;
        result = mongoTemplate.findById(duplicateId, Duplicate.class);
        if (result != null) {
            Applicant app = mongoTemplate.findById(result.getProbeId(), Applicant.class);
            result.setProbe(app);

            Applicant ref = mongoTemplate.findById(result.getReferenceId(), Applicant.class);
            result.setReference(ref);
        }
        return result;
    }

    public String getNextDuplicate() {
        String result = "";
        Query query = new Query();
        query.addCriteria(Criteria.where("status").is("Pending"));
        query.limit(1);// 取多少条记录
        if (mongoTemplate.count(query, Duplicate.class) > 0) {

            List<Duplicate> listData = mongoTemplate.find(query, Duplicate.class);
            result = listData.get(0).get_id().toString();
        }
        return result;
    }

    public List<Duplicate> getApplicantDuplicates(String applicantId) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.orOperator(Criteria.where("probeId").is(applicantId), Criteria.where("referenceId").is(applicantId));
        query.addCriteria(criteria);
        return mongoTemplate.find(query, Duplicate.class);
    }

    @Override
    public void setCriteria(PagingQuery pagingQuery, Query query) {
        Criteria criteria = null;
        for (String key : pagingQuery.getFilters().keySet()) {
            if (pagingQuery.getFilters().get(key).length() > 0) {
                if (criteria == null) {
                    switch (key) {
                        case "status":
                            criteria = Criteria.where(key).is(pagingQuery.getFilters().get(key));
                            break;
                        case "createdTime":
                            Date startDate = SystemHelper.getMinusDate(pagingQuery.getFilters().get(key));
                            Date endDate = SystemHelper.getPlusDate(pagingQuery.getFilters().get(key));
                            criteria = Criteria.where(key).gte(startDate).lt(endDate);
                            break;
                        default:
                            criteria = Criteria.where(key).regex(pagingQuery.getFilters().get(key), "i");
                            break;
                    }
                } else {
                    switch (key) {
                        case "status":
                            criteria = criteria.and(key).is(pagingQuery.getFilters().get(key));
                            break;
                        case "createdTime":
                            if (SystemHelper.IsDateString(pagingQuery.getFilters().get(key))) {
                                Date startDate = SystemHelper.getMinusDate(pagingQuery.getFilters().get(key));
                                Date endDate = SystemHelper.getPlusDate(pagingQuery.getFilters().get(key));
                                criteria = criteria.and(key).gte(startDate).lt(endDate);
                            }
                            break;
                        default:
                            criteria = criteria.and(key).regex(pagingQuery.getFilters().get(key), "i");
                            break;
                    }
                }
            }
        }
        if (criteria != null) {
            query.addCriteria(criteria);
        }
    }
}