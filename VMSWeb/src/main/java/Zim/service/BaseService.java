package Zim.service;

import Zim.model.User;
import Zim.model.modelview.PagingQuery;
import Zim.model.modelview.SysPagination;
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
            query.with(new Sort(new Sort.Order(orderBy, orderByName)));
        }
    }

    public void setPaging(SysPagination<T> result, PagingQuery pagingQuery, Query query, int total) {
        int skip = (pagingQuery.getCurrentPage() - 1) * pagingQuery.getPageSize();
        int totalPage = 0;
        if (total % pagingQuery.getPageSize() == 0) {
            totalPage = (int) (total / pagingQuery.getPageSize());
        } else {
            totalPage = ((int) (total / pagingQuery.getPageSize()) + 1);
        }
        result.setOrderBy(pagingQuery.getOrderBy());
        result.setOrderByName(pagingQuery.getOrderByName());
        result.setTotalRecord(total);//总共记录
        result.setTotalPage(totalPage);//总共页数
        result.setPageSize(pagingQuery.getPageSize());//每页记录
        result.setFilters(pagingQuery.getFilters());
        query.skip(skip);// 从那条记录开始
        query.limit(pagingQuery.getPageSize());// 取多少条记录
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
        Iterator<T> iteratorResult = mongoTemplate.group(criteria, collectionName, groupBy, entityClass).iterator();
        while (iteratorResult.hasNext()) {
            result.add(iteratorResult.next());
        }
        return result;
    }

    public List<T> getGroupStatic(GroupBy groupBy, String collectionName, Class<T> entityClass) {
        List<T> result = new ArrayList<>();
        Iterator<T> iteratorResult = mongoTemplate.group(collectionName, groupBy, entityClass).iterator();
        while (iteratorResult.hasNext()) {
            result.add(iteratorResult.next());
        }
        return result;
    }


    public int getCollectionSize(Query query, Class<T> entityClass) {
        return mongoTemplate.find(query, entityClass).size();
    }
    public List<T> getCollection(Query query, Class<T> entityClass) {
        return mongoTemplate.find(query, entityClass);
    }
}
