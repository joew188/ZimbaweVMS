package Zim.service;

import Zim.model.ApplicantLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Laxton-Joe on 2017/2/20.
 */
@Service
public class ApplicantLogService {
    @Autowired
    MongoTemplate mongoTemplate;

    public List<ApplicantLog> getLogList(String applicantId) {
        List<ApplicantLog> result = null;
        Query query = new Query();
        Criteria criteria=new Criteria();

        criteria.orOperator(Criteria.where("applicantId").is(applicantId), Criteria.where("probeId").is(applicantId),Criteria.where("referenceId").is(applicantId));
        query.addCriteria(criteria);

        return mongoTemplate.find(query, ApplicantLog.class);
    }


}
