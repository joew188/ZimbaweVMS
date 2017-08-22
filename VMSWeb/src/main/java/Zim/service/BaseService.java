package Zim.service;

import Zim.model.modelview.req.PagingQuery;
import Zim.model.modelview.res.PagingResponse;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Laxton-Joe on 2017/7/26.
 */
@Service
public class BaseService<T> {
    @Autowired
    MongoTemplate mongoTemplate;

    public void sortQuery(PagingQuery pagingQuery, Query query) {
        if (pagingQuery.getOrderByName() != null && pagingQuery.getOrderByName().length() > 0) {
            Sort.Direction orderBy = Sort.Direction.ASC;
            if (pagingQuery.getOrderBy() != null && pagingQuery.getOrderBy().length() > 0) {
                if (pagingQuery.getOrderBy().toLowerCase().equals("desc")) {
                    orderBy = Sort.Direction.DESC;
                }
            }
            String orderByName = pagingQuery.getOrderByName();
            if (orderByName.equals("personName")) {
                orderByName = "applicantDemographic.forenames";
            }
            if (orderByName.equals("surname")) {
                orderByName = "applicantDemographic.surname";
            }
            if (orderByName.equals("provinceName")) {
                orderByName = "applicantDemographic.provinceName";
            }
            if (orderByName.equals("districtName")) {
                orderByName = "applicantDemographic.districtName";
            }
            query.with(new Sort(new Sort.Order(orderBy, orderByName)));
        }
    }

    public void setPaging(PagingResponse<T> result, PagingQuery pagingQuery, Query query, long total) {
        long skip = (pagingQuery.getCurrentPage() - 1) * pagingQuery.getPageSize();
        long totalPage = 0;
        if (total % pagingQuery.getPageSize() == 0) {
            totalPage = total / pagingQuery.getPageSize();
        } else {
            totalPage = (total / pagingQuery.getPageSize() + 1);
        }
        if (result != null) {
            result.setOrderBy(pagingQuery.getOrderBy());
            result.setOrderByName(pagingQuery.getOrderByName());
            result.setTotalRecord(total);//总共记录
            result.setTotalPage(totalPage);//总共页数
            result.setPageSize(pagingQuery.getPageSize());//每页记录
            result.setFilters(pagingQuery.getFilters());
        }
        query.skip((int) skip);// 从那条记录开始
        query.limit((int) pagingQuery.getPageSize());// 取多少条记录
    }

    public void setCriteria(PagingQuery pagingQuery, Query query) {
        Criteria criteria = new Criteria();
        for (String key : pagingQuery.getFilters().keySet()) {
            if (pagingQuery.getFilters().get(key).length() > 0) {
                criteria = criteria.and(key).regex(pagingQuery.getFilters().get(key), "i");
            }
        }
        query.addCriteria(criteria);
    }


    public List<T> getGroupStatic(Criteria criteria, GroupBy groupBy, String collectionName, Class<T> entityClass) {
        List<T> result = new ArrayList<>();
        for (T t : mongoTemplate.group(criteria, collectionName, groupBy, entityClass)) {
            result.add(t);
        }
        return result;
    }

    public List<T> getGroupStatic(GroupBy groupBy, String collectionName, Class<T> entityClass) {
        List<T> result = new ArrayList<>();
        for (T t : mongoTemplate.group(collectionName, groupBy, entityClass)) {
            result.add(t);
        }
        return result;
    }

    public boolean queryExists(Query query, Class<T> entityClass) {
        return mongoTemplate.exists(query, entityClass);
    }

    public List<T> getCollection(Query query, Class<T> entityClass) {
        return mongoTemplate.find(query, entityClass);
    }

    public List getDistinct(String collectionName, String fieldName, DBObject query) {
        return mongoTemplate.getCollection(collectionName).distinct(fieldName, query);
        //        String jsonSql="{distinct:'Applicant', key:'deviceName'}";
//        CommandResult commandResult=mongoTemplate.executeCommand(jsonSql);
//
//        BasicDBList list = (BasicDBList)commandResult.get("values");
//        for (int i = 0; i < list.size(); i ++) {
//            System.out.println(list.get(i));
//        }
    }
}
