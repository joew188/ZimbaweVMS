package Zim.service;

import Zim.common.SystemHelper;
import Zim.model.Applicant;
import Zim.model.ApplicantLog;
import Zim.model.Duplicate;
import Zim.model.modelview.DuplicateAction;
import Zim.model.modelview.SysPagination;
import Zim.model.modelview.SysQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
public class DuplicateService {
    private static String Duplicate_COLLECTION = "Duplicate";
    @Autowired
    MongoTemplate mongoTemplate;

    public SysPagination<Duplicate> pageList(SysQuery parmQuery) {

        SysPagination<Duplicate> result = new SysPagination<Duplicate>();
        try {
            Query query = new Query();
            if (parmQuery.getOrderBy() != null && parmQuery.getOrderBy().length() > 0) {
                if (parmQuery.getOrderBy().toUpperCase().equals("DESC")) {
                    if (parmQuery.getOrderByName() != null && parmQuery.getOrderBy().length() > 0)
                        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, parmQuery.getOrderByName())));
                } else {
                    if (parmQuery.getOrderByName() != null && parmQuery.getOrderBy().length() > 0)
                        query.with(new Sort(new Sort.Order(Sort.Direction.ASC, parmQuery.getOrderByName())));
                }
            }
            int skip = (parmQuery.getCurrentPage() - 1) * parmQuery.getPageSize();
            Criteria criteria = null;
            if (parmQuery.getFilters() != null) {
                boolean isFirst = true;
                for (String key : parmQuery.getFilters().keySet()) {
                    if (Duplicate.getColumns().contains(key)) {
                        if (parmQuery.getFilters().get(key).length() > 0) {
                            if (isFirst) {
                                if (key.equals("status")) {
                                    criteria = Criteria.where(key).is(parmQuery.getFilters().get(key));
                                } else if (key.equals("createdTime")) {
                                    Date startDate = SystemHelper.getMinusDate(parmQuery.getFilters().get(key));
                                    Date endDate = SystemHelper.getPlusDate(parmQuery.getFilters().get(key));
                                    criteria = criteria.where(key).gte(startDate).lt(endDate);
                                } else {
                                    criteria = Criteria.where(key).regex(parmQuery.getFilters().get(key),"i");
                                }
                                isFirst = false;
                            } else {
                                if (key.equals("status")) {
                                    criteria = criteria.and(key).is(parmQuery.getFilters().get(key));
                                } else if (key.equals("createdTime")) {
                                    if (SystemHelper.IsDateString(parmQuery.getFilters().get(key))) {
                                        Date startDate = SystemHelper.getMinusDate(parmQuery.getFilters().get(key));
                                        Date endDate = SystemHelper.getPlusDate(parmQuery.getFilters().get(key));
                                        criteria = criteria.and(key).gte(startDate).lt(endDate);
                                    }
                                } else {
                                    criteria = criteria.and(key).regex(parmQuery.getFilters().get(key),"i");
                                }
                            }
                        }
                    }
                }
                if (criteria != null) {
                    query.addCriteria(criteria);
                }
            }
            int count = mongoTemplate.find(query, Duplicate.class).size();
            if (count > 0) {
                //  int totalPage = (int) (count / parmQuery.getPageSize());
                int totalPage = 0;//(int) (count / appQuery.getPageSize());
                if (count % parmQuery.getPageSize() == 0) {
                    totalPage = (int) (count / parmQuery.getPageSize());
                } else {
                    totalPage = ((int) (count / parmQuery.getPageSize()) + 1);
                }
                result.setTotalRecord(count);//总共记录
                result.setTotalPage(totalPage);//总共页数
                result.setPageSize(parmQuery.getPageSize());//每页记录
                result.setFilters(parmQuery.getFilters());

                query.skip(skip);// 从那条记录开始
                query.limit(parmQuery.getPageSize());// 取多少条记录
                List<Duplicate> listData = mongoTemplate.find(query, Duplicate.class);
                if (listData.size() > 0) {
                    result.setCurrentPage(parmQuery.getCurrentPage());//当前页
                    result.setItems(listData);//查询内容
                }
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
                        sb.append(String.format("Applicant: %s -> %s;", probe.get_id(), probe.getStatus()));
                        sb.append(String.format("Applicant: %s -> %s.", reference.get_id(), reference.getStatus()));
                        appLog.setLogEvent(sb.toString());
//                        appLog.setLogEvent(String.format("action:Positive, Duplicate: %s Close.%s->%s,%s->%s", duplicate.get_id(),
//                                probe.getId(), probe.getStatus(), reference.getId(), reference.getStatus()
//                        ));
                        appLog.setLogTime(new Date());
                        mongoTemplate.insert(appLog, "ApplicantLog");
                        break;
                    case "matched":
                        probe.setStatus(2);
                        probe.setIndividualsId(reference.getIndividualsId());
                        mongoTemplate.save(probe);
                        //查找 IndividualsId==probe.IndividualsId的数据，更改IndividualsId=reference。IndividualsId的数据
                        //   setApplicantIndividualsId(probe.getIndividualsId(), reference.getIndividualsId());
                        setApplicantAutoClose("Matched", dupId, probe.get_id(), "Suspect");
                        break;
                    case "update": // probe to master,reference to archive
                        reference.setStatus(3);
                        reference.setIndividualsId(reference.getIndividualsId());
                        mongoTemplate.save(reference);
                        //setApplicantIndividualsId(reference.getIndividualsId(), probe.getIndividualsId());
                        setApplicantAutoClose("Update", dupId, reference.get_id(), "Archive");
                        break;
                    case "archive":// probe to archive,reference to master
                        probe.setStatus(3);
                        probe.setIndividualsId(reference.getIndividualsId());
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

    private void setApplicantIndividualsId(String oldId, String newId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("individualsId").is(oldId));
        if (mongoTemplate.find(query, Applicant.class).size() > 0) {
            List<Applicant> listData = mongoTemplate.find(query, Applicant.class);
            for (Applicant app : listData) {
                app.setIndividualsId(newId);
                mongoTemplate.save(app);
            }
        }
    }

    private void setApplicantAutoClose(String action, String triggerDupId, String appId, String newStatus) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.orOperator(criteria.where("probeId").is(appId), criteria.where("referenceId").is(appId));
        criteria.and("status").is("Pending");
        query.addCriteria(criteria);
        if (mongoTemplate.find(query, Duplicate.class).size() > 0) {
            List<Duplicate> listData = mongoTemplate.find(query, Duplicate.class);
            for (Duplicate dup : listData) {
                Applicant probe = mongoTemplate.findById(dup.getProbeId(), Applicant.class);
                Applicant reference = mongoTemplate.findById(dup.getReferenceId(), Applicant.class);
                if ((probe.getStatus()==1 && reference.getStatus()==1) == false) {
                    dup.setStatus("Close");
                    mongoTemplate.save(dup);
                    ApplicantLog appLog = new ApplicantLog();
                    appLog.setReferenceId(dup.getReferenceId());
                    appLog.setProbeId(dup.getProbeId());
                    StringBuilder sb = new StringBuilder();
                    sb.append(String.format("Trigger Duplicate:%s,Action:%s;", triggerDupId, action));
                    sb.append(String.format("Duplicate: %s Close;", dup.get_id()));

                    if (probe.getStatus()==1) {
                        sb.append(String.format("Applicant: %s -> %s;", probe.get_id(), probe.getStatus()));
                        sb.append(String.format("Applicant: %s -> %s.", reference.get_id(), newStatus));
                    } else {
                        sb.append(String.format("Applicant: %s -> %s;", probe.get_id(), newStatus));
                        sb.append(String.format("Applicant: %s -> %s.", reference.get_id(), reference.getStatus()));
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
        if (mongoTemplate.find(query, Duplicate.class).size() > 0) {

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
}