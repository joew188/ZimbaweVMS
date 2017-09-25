package Zim.service;

import Zim.model.modelview.req.BasicPagingQuery;
import Zim.model.modelview.req.PagingOneQuery;
import Zim.model.modelview.req.PagingPageQuery;
import Zim.model.modelview.res.EntityResponse;
import Zim.model.modelview.res.PageResponse;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Laxton-Joe on 2017/7/26.
 */
@Service
public class BaseService {
    @Autowired
    MongoTemplate mongoTemplate;

    public void sortQuery(BasicPagingQuery pagingQuery, Query query) {
        if (pagingQuery.getOrderByName() != null && pagingQuery.getOrderByName().length() > 0) {
            Sort.Direction orderBy = Sort.Direction.ASC;
            if (pagingQuery.getOrderBy() != null && pagingQuery.getOrderBy().length() > 0) {
                if (pagingQuery.getOrderBy().toLowerCase().equals("desc")) {
                    orderBy = Sort.Direction.DESC;
                }
            }
            String orderByName = pagingQuery.getOrderByName();
            query.with(new Sort(new Sort.Order(orderBy, orderByName)));
        }
    }

    public <T> void setPagePaging(PageResponse<T> response, PagingPageQuery pagingQuery, long total) {

        long totalPage = 0;
        if (total % pagingQuery.getPageSize() == 0) {
            totalPage = total / pagingQuery.getPageSize();
        } else {
            totalPage = (total / pagingQuery.getPageSize() + 1);
        }
        response.setCurrentPage(pagingQuery.getCurrentPage());//当前页
        response.setOrderBy(pagingQuery.getOrderBy());
        response.setOrderByName(pagingQuery.getOrderByName());
        response.setTotalRecord(total);//总共记录
        response.setTotalPage(totalPage);//总共页数
        response.setPageSize(pagingQuery.getPageSize());//每页记录
        response.setFilters(pagingQuery.getFilters());
    }

    public void setPageQuery(BasicPagingQuery pagingQuery, Query query) {
        long skip = (pagingQuery.getCurrentPage() - 1) * pagingQuery.getPageSize();
        query.skip((int) skip);// 从那条记录开始
        query.limit((int) pagingQuery.getPageSize());// 取多少条记录
    }

    public <T> void setFindOnePaging(EntityResponse<T> response, PagingOneQuery pagingQuery, long recordCount) {

        long totalPage = 0;
        if (recordCount % pagingQuery.getPageSize() == 0) {
            totalPage = recordCount / pagingQuery.getPageSize();
        } else {
            totalPage = (recordCount / pagingQuery.getPageSize() + 1);
        }
        response.setCurrentPage(pagingQuery.getCurrentPage());//当前页
        response.setOrderBy(pagingQuery.getOrderBy());
        response.setOrderByName(pagingQuery.getOrderByName());
        response.setTotalRecord(recordCount);//总共记录
        response.setTotalPage(totalPage);//总共页数
        response.setPageSize(pagingQuery.getPageSize());//每页记录
        response.setFilters(pagingQuery.getFilters());
        response.setRecordIndex(pagingQuery.getRecordIndex());
        response.setRecordId(pagingQuery.getRecordId());

    }

    public void setCriteria(BasicPagingQuery pagingQuery, Query query) {
        if (pagingQuery.getFilters() != null) {
            Criteria criteria = new Criteria();
            for (String key : pagingQuery.getFilters().keySet()) {
                if (pagingQuery.getFilters().get(key).length() > 0) {
                    criteria = criteria.and(key).regex(pagingQuery.getFilters().get(key), "i");
                }
            }
            query.addCriteria(criteria);
        }
    }


    public <T> List<T> getGroupStatic(Criteria criteria, GroupBy groupBy, String collectionName, Class<T> entityClass) {
        List<T> result = new ArrayList<>();
        for (T t : mongoTemplate.group(criteria, collectionName, groupBy, entityClass)) {
            result.add(t);
        }
        return result;
    }

//    public List<T> getGroupStatic(GroupBy groupBy, String collectionName, Class<T> entityClass) {
//        List<T> result = new ArrayList<>();
//        for (T t : mongoTemplate.group(collectionName, groupBy, entityClass)) {
//            result.add(t);
//        }
//        return result;
//    }

    public <T> List<T> getAggregationStatic(Aggregation aggregation, String collectionName, Class<T> entityClass) {
        return mongoTemplate.aggregate(aggregation, collectionName, entityClass).getMappedResults();
    }


    public <T> boolean queryExists(Query query, Class<T> entityClass) {
        return mongoTemplate.exists(query, entityClass);
    }

    public <T> List<T> getCollection(Query query, Class<T> entityClass) {
        return mongoTemplate.find(query, entityClass);
    }

//    public List getDistinct(String collectionName, String fieldName, DBObject query) {
//        return mongoTemplate.getCollection(collectionName).distinct(fieldName, query);
//        //        String jsonSql="{distinct:'Applicant', key:'deviceName'}";
////        CommandResult commandResult=mongoTemplate.executeCommand(jsonSql);
////
////        BasicDBList list = (BasicDBList)commandResult.get("values");
////        for (int i = 0; i < list.size(); i ++) {
////            System.out.println(list.get(i));
////        }
//    }
}
