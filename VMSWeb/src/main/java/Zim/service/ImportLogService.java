package Zim.service;

import Zim.model.ImportLog;
import Zim.model.modelview.ApplicantQuery;
import Zim.model.modelview.SysPagination;
import Zim.model.modelview.importLog.ImportByDevice;
import Zim.model.modelview.importLog.ImportGaps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Laxton-Joe on 2017/7/12.
 */
@Service
public class ImportLogService {
    @Autowired
    MongoTemplate mongoTemplate;

    public SysPagination<ImportLog> pageList(ApplicantQuery appQuery) {

        SysPagination<ImportLog> result = new SysPagination<>();
        try {
            Query query = new Query();
            if (appQuery.getOrderBy() != null && appQuery.getOrderBy().length() > 0) {
                if (appQuery.getOrderBy().toUpperCase().equals("DESC")) {
                    if (appQuery.getOrderByName() != null && appQuery.getOrderByName().length() > 0)
                        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, appQuery.getOrderByName())));
                } else {
                    if (appQuery.getOrderByName() != null && appQuery.getOrderByName().length() > 0)
                        query.with(new Sort(new Sort.Order(Sort.Direction.ASC, appQuery.getOrderByName())));
                }
            }
            int skip = (appQuery.getCurrentPage() - 1) * appQuery.getPageSize();
            Criteria criteria = null;
            if (appQuery.getFilters() != null) {
                boolean isFirst = true;
                for (String key : appQuery.getFilters().keySet()) {
                    if (ImportLog.getColumns().contains(key)) {
                        if (appQuery.getFilters().get(key).length() > 0) {
                            if (isFirst) {
                                if (key.equals("group")) {
                                    criteria = Criteria.where(key).is(appQuery.getFilters().get(key));
                                } else {
                                    criteria = Criteria.where(key).regex(appQuery.getFilters().get(key), "i");
                                }
                                isFirst = false;
                            } else {
                                if (key.equals("group")) {
                                    criteria = criteria.and(key).is(appQuery.getFilters().get(key));
                                } else {
                                    criteria = criteria.and(key).regex(appQuery.getFilters().get(key), "i");
                                }
                            }
                        }
                    }
                }
                if (criteria != null) {
                    query.addCriteria(criteria);
                }

            }
            int count = mongoTemplate.find(query, ImportLog.class).size();
            if (count > 0) {
                int totalPage = 0;//(int) (count / appQuery.getPageSize());
                if (count % appQuery.getPageSize() == 0) {
                    totalPage = (int) (count / appQuery.getPageSize());
                } else {
                    totalPage = ((int) (count / appQuery.getPageSize()) + 1);
                }
                result.setTotalRecord(count);//总共记录
                result.setTotalPage(totalPage);//总共页数
                result.setPageSize(appQuery.getPageSize());//每页记录
                result.setFilters(appQuery.getFilters());

                query.skip(skip);// 从那条记录开始
                query.limit(appQuery.getPageSize());// 取多少条记录
                List<ImportLog> listData = mongoTemplate.find(query, ImportLog.class);
                if (listData.size() > 0) {
                    result.setCurrentPage(appQuery.getCurrentPage());//当前页

                    result.setItems(listData);//查询内容
                }
            }
            result.setResult(true);
        } catch (Exception e) {
            result.setResult(false);
        }

        return result;
    }

    public List<ImportByDevice> getDeviceStatic(Criteria criteria, GroupBy groupBy) {
        List<ImportByDevice> result = new ArrayList<>();
        Iterator<ImportByDevice> iteratorResult = mongoTemplate.group(criteria, "ImportLog", groupBy, ImportByDevice.class).iterator();
        while (iteratorResult.hasNext()) {
            result.add((ImportByDevice) iteratorResult.next());
        }
        return result;
    }
    public List<ImportGaps> getGapStatic(Criteria criteria, GroupBy groupBy) {
        List<ImportGaps> result = new ArrayList<>();
        Iterator<ImportGaps> iteratorResult = mongoTemplate.group(criteria, "ImportLog", groupBy, ImportGaps.class).iterator();
        while (iteratorResult.hasNext()) {
            result.add((ImportGaps) iteratorResult.next());
        }
        return result;
    }
}
