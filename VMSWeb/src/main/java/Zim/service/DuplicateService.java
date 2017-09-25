package Zim.service;

import Zim.common.SystemHelper;
import Zim.model.Applicant;
import Zim.model.ApplicantLog;
import Zim.model.ApplicantMaster;
import Zim.model.Duplicate;
import Zim.model.modelview.DuplicateAction;
import Zim.model.modelview.req.BasicPagingQuery;
import Zim.model.modelview.req.PagingOneQuery;
import Zim.model.modelview.req.PagingPageQuery;


import Zim.model.modelview.res.EntityResponse;
import Zim.model.modelview.res.PageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class DuplicateService extends BaseService {
    private static Logger logger = LoggerFactory.getLogger(DuplicateService.class);
    @Autowired
    MongoTemplate mongoTemplate;

    public PageResponse<Duplicate> pageList(PagingPageQuery pagingQuery) {
        PageResponse<Duplicate> response = new PageResponse<>();
        try {
            Query query = new Query();
            sortQuery(pagingQuery, query);
            setCriteria(pagingQuery, query);
            long total = mongoTemplate.count(query, Duplicate.class);
            if (total > 0) {
                setPageQuery(pagingQuery, query);
                List<Duplicate> listData = mongoTemplate.find(query, Duplicate.class);
                response.setItems(listData);//查询内容
            }
            setPagePaging(response, pagingQuery, total);
            response.setResult(true);
        } catch (Exception e) {
            response.setResult(false);
            logger.error(e.toString());
        }
        return response;
    }

    public Duplicate pageFineOne(EntityResponse<Duplicate> response, PagingOneQuery pagingOneQuery) {
        Duplicate duplicate = null;
        try {
            Query query = new Query();
            sortQuery(pagingOneQuery, query);
            setCriteria(pagingOneQuery, query);
            long total = mongoTemplate.count(query, Duplicate.class);
            if (total > 0) {
                setPageQuery(pagingOneQuery, query);
                List<Duplicate> listData = mongoTemplate.find(query, Duplicate.class);
                if (listData.size() > pagingOneQuery.getRecordIndex()) {
                    duplicate = listData.get(pagingOneQuery.getRecordIndex());
                    if (pagingOneQuery.getRecordId() != null && pagingOneQuery.getRecordId().length() > 0) {
                        if (!duplicate.get_id().equals(pagingOneQuery.getRecordId())) {
                            duplicate = null;
                        }
                    }
                }
            }
            if (duplicate != null) {
                Applicant probeApplicant = mongoTemplate.findById(duplicate.getProbeId(), Applicant.class);
                ApplicantMaster probeMaster = mongoTemplate.findById(duplicate.getProbeId(), ApplicantMaster.class);
                probeApplicant.merge(probeMaster);
                duplicate.setProbe(probeApplicant);

                Applicant refApplicant = mongoTemplate.findById(duplicate.getReferenceId(), Applicant.class);
                ApplicantMaster refMaster = mongoTemplate.findById(duplicate.getReferenceId(), ApplicantMaster.class);
                refApplicant.merge(refMaster);
                duplicate.setReference(refApplicant);
            }
            setFindOnePaging(response, pagingOneQuery, total);
            response.setResult(true);
        } catch (Exception e) {
            duplicate = null;
            logger.error(e.toString());
        }
        return duplicate;
    }

    public String action(DuplicateAction action) {
        String msg = "";
        try {

            String dupId = action.getDuplicateId();
            Duplicate duplicate = mongoTemplate.findById(dupId, Duplicate.class);
            ApplicantMaster probe = null;
            ApplicantMaster reference = null;
            if (duplicate.getStatus().equals("Close")) {
                msg = "duplicate has been close";
            } else {
                probe = mongoTemplate.findById(duplicate.getProbeId(), ApplicantMaster.class);
                reference = mongoTemplate.findById(duplicate.getReferenceId(), ApplicantMaster.class);
                switch (action.getAction()) {
                    case "positive"://duplicate-close
                        duplicate.setStatus("Close");
                        mongoTemplate.save(duplicate, "Duplicate");

                        ApplicantLog appLog = new ApplicantLog();
                        appLog.setReferenceId(duplicate.getReferenceId());
                        appLog.setProbeId(duplicate.getProbeId());
                        String sb = String.format("Trigger Duplicate:%s,Action:Positive;", duplicate.get_id()) +
                                String.format("Duplicate: %s Close;", duplicate.get_id()) +
                                String.format("Applicant: %s -> %s;", probe.get_id(), SystemHelper.getApplicantStatusString(probe.getStatus())) +
                                String.format("Applicant: %s -> %s.", reference.get_id(), SystemHelper.getApplicantStatusString(reference.getStatus()));
                        appLog.setLogEvent(sb);
//                        appLog.setLogEvent(String.format("action:Positive, Duplicate: %s Close.%s->%s,%s->%s", duplicate.get_id(),
//                                probe.getId(), probe.getStatus(), reference.getId(), reference.getStatus()
//                        ));
                        appLog.setLogTime(new Date());
                        mongoTemplate.insert(appLog, "ApplicantLog");
                        break;
                    case "matched":
                        probe.setStatus(2);

                        mongoTemplate.save(probe);
                        //查找 IndividualsId==probe.IndividualsId的数据，更改IndividualsId=reference。IndividualsId的数据
                        //   setApplicantIndividualsId(probe.getIndividualsId(), reference.getIndividualsId());
                        setApplicantAutoClose("Matched", dupId, probe.get_id(), "Suspect");
                        break;
                    case "update": // probe to master,reference to archive
                        reference.setStatus(3);

                        mongoTemplate.save(reference);
                        //setApplicantIndividualsId(reference.getIndividualsId(), probe.getIndividualsId());
                        setApplicantAutoClose("Update", dupId, reference.get_id(), "Archive");
                        break;
                    case "archive":// probe to archive,reference to master
                        probe.setStatus(3);
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
            logger.error(e.toString());
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
                ApplicantMaster probe = mongoTemplate.findById(dup.getProbeId(), ApplicantMaster.class);
                ApplicantMaster reference = mongoTemplate.findById(dup.getReferenceId(), ApplicantMaster.class);
                if (!(probe.getStatus() == 1 && reference.getStatus() == 1)) {
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

//    public Duplicate find(String duplicateId) {
//        Duplicate result = null;
//        result = mongoTemplate.findById(duplicateId, Duplicate.class);
//        if (result != null) {
//            Applicant probeApplicant = mongoTemplate.findById(result.getProbeId(), Applicant.class);
//            ApplicantMaster probeMaster = mongoTemplate.findById(result.getProbeId(), ApplicantMaster.class);
//            probeApplicant.merge(probeMaster);
//            result.setProbe(probeApplicant);
//
//            Applicant refApplicant = mongoTemplate.findById(result.getReferenceId(), Applicant.class);
//            ApplicantMaster refMaster = mongoTemplate.findById(result.getReferenceId(), ApplicantMaster.class);
//            refApplicant.merge(refMaster);
//            result.setReference(refApplicant);
//        }
//        return result;
//    }

    public String getNextDuplicate() {
        String result = "";
        Query query = new Query();
        query.addCriteria(Criteria.where("status").is("Pending"));
        query.limit(1);// 取多少条记录
        if (mongoTemplate.count(query, Duplicate.class) > 0) {

            List<Duplicate> listData = mongoTemplate.find(query, Duplicate.class);
            result = listData.get(0).get_id();
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
    public void setCriteria(BasicPagingQuery pagingQuery, Query query) {
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