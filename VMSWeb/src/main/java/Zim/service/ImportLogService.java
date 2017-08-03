package Zim.service;

import Zim.common.SystemHelper;
import Zim.model.ImportLog;
import Zim.model.modelview.PagingQuery;
import Zim.model.modelview.SysPagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Created by Laxton-Joe on 2017/7/12.
 */
@Service
public class ImportLogService extends BaseService {
    @Autowired
    MongoTemplate mongoTemplate;

    public SysPagination<ImportLog> pageList(PagingQuery pagingQuery) {
        SysPagination<ImportLog> result = new SysPagination<>();
        try {
            Query query = new Query();
            sortQuery(pagingQuery, query);
            if (pagingQuery.getFilters() != null) {
//                pagingQuery.getFilters().keySet().stream().filter(key -> !ImportLog.getColumns().contains(key)).forEach(key -> {
//                    pagingQuery.getFilters().remove(key);
//                });
                setCriteria(pagingQuery, query);
            }
            int total = mongoTemplate.find(query, ImportLog.class).size();
            if (total > 0) {
                setPaging(result, pagingQuery, query, total);
                List<ImportLog> listData = mongoTemplate.find(query, ImportLog.class);
                result.setCurrentPage(pagingQuery.getCurrentPage());//当前页
                result.setItems(listData);//查询内容
            }
            result.setResult(true);
        } catch (Exception e) {
            result.setResult(false);
        }
        return result;
    }

    @Override
    public void setCriteria(PagingQuery pagingQuery, Query query) {
        Criteria criteria = new Criteria();
        for (String key : pagingQuery.getFilters().keySet()) {
            if (pagingQuery.getFilters().get(key).length() > 0) {
                if (key.equals("exportDateTime") || key.equals("importBeginTime") || key.equals("importFinishTime")) {
                    Date date= SystemHelper.getDateFromInt(Integer.parseInt(pagingQuery.getFilters().get(key)));
                    LocalDate endLocalDate=SystemHelper.DateToLocal(date);
                    Date endDate=SystemHelper.LocalToDate(endLocalDate.plusDays(1));
                   // criteria = criteria.and(key).gt(date).lt(endDate);
                    criteria = criteria.and(key).gt(date);
                } else {
                    criteria = criteria.and(key).regex(pagingQuery.getFilters().get(key), "i");
                }
            }
        }
        query.addCriteria(criteria);
    }
//    public List<ImportByDevice> getDeviceStatic(Criteria criteria, GroupBy groupBy) {
//        List<ImportByDevice> result = new ArrayList<>();
//        Iterator<ImportByDevice> iteratorResult = mongoTemplate.group(criteria, "ImportLog", groupBy, ImportByDevice.class).iterator();
//        while (iteratorResult.hasNext()) {
//            result.add((ImportByDevice) iteratorResult.next());
//        }
//        return result;
//    }
//
//    public List<ImportGaps> getGapStatic(Criteria criteria, GroupBy groupBy) {
//        List<ImportGaps> result = new ArrayList<>();
//        Iterator<ImportGaps> iteratorResult = mongoTemplate.group(criteria, "ImportLog", groupBy, ImportGaps.class).iterator();
//        while (iteratorResult.hasNext()) {
//            result.add((ImportGaps) iteratorResult.next());
//        }
//        return result;
//    }
//
//    public List<ImportByDeviceRecord> getDeviceRecordStatic(Criteria criteria, GroupBy groupBy) {
//        List<ImportByDeviceRecord> result = new ArrayList<>();
//        Iterator<ImportByDeviceRecord> iteratorResult = mongoTemplate.group(criteria, "ImportLog", groupBy, ImportByDeviceRecord.class).iterator();
//        while (iteratorResult.hasNext()) {
//            result.add((ImportByDeviceRecord) iteratorResult.next());
//        }
//        return result;
//    }
}
