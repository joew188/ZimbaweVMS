package Zim.service;

import Zim.common.CustomerAggregationOperation;
import Zim.common.SystemHelper;
import Zim.model.ImportLog;
import Zim.model.modelview.importLog.ImportByDevice;
import Zim.model.modelview.importLog.ImportByDeviceRecord;
import Zim.model.modelview.importLog.ImportTotal;
import Zim.model.modelview.req.BasicPagingQuery;
import Zim.model.modelview.req.PagingPageQuery;
import Zim.model.modelview.res.PageResponse;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Laxton-Joe on 2017/7/12.
 */
@Service
public class ImportLogService extends BaseService {
    private static Logger logger = LoggerFactory.getLogger(ImportLogService.class);
    @Autowired
    MongoTemplate mongoTemplate;

    public PageResponse<ImportLog> pageList(PagingPageQuery pagingQuery) {
        PageResponse<ImportLog> result = new PageResponse<>();
        try {
            Query query = new Query();
            sortQuery(pagingQuery, query);
            setCriteria(pagingQuery, query);
            long total = mongoTemplate.count(query, ImportLog.class);
            if (total > 0) {
                setPageQuery(pagingQuery, query);
                List<ImportLog> listImportLog = mongoTemplate.find(query, ImportLog.class);
                List<String> selTaskName = new ArrayList<>();
                for (ImportLog log : listImportLog) {
                    selTaskName.add(log.getName());
                }

                DBObject project = (DBObject) JSON.parse("{$project:{importTask:1,imported:{$cond:{if:{$gt:['$status',-1]},then:1,else:0}},matched:{$cond:{if:{$gt:['$status',0]},then:1,else:0}}}}}");
                List<ImportTotal> listTotal = getAggregationStatic(
                        Aggregation.newAggregation(
                                Aggregation.match(Criteria.where("importTask").in(selTaskName)),
                                new CustomerAggregationOperation(project),
                                Aggregation.group("importTask").sum("imported").as("importTotal").sum("matched").as("matchedTotal")),
                        "ApplicantMaster",
                        ImportTotal.class);

                for (ImportLog log : listImportLog) {
                    for (ImportTotal importTotal : listTotal) {
                        if (log.getName().equals(importTotal.get_id())) {
                            log.setImportTotal(importTotal.getImportTotal());
                            log.setMatchedTotal(importTotal.getMatchedTotal());
                            break;
                        }
                    }
                }
                result.setItems(listImportLog);//查询内容
            }
            setPagePaging(result, pagingQuery, total);
            result.setResult(true);
        } catch (Exception e) {
            result.setResult(false);
            logger.error(e.toString());
        }
        return result;
    }

    @Override
    public void setCriteria(BasicPagingQuery pagingQuery, Query query) {
        Criteria criteria = new Criteria();
        for (String key : pagingQuery.getFilters().keySet()) {
            if (pagingQuery.getFilters().get(key).length() > 0) {
                if (key.equals("exportDateTime") || key.equals("importBeginTime") || key.equals("importFinishTime")) {
                    Date date = SystemHelper.getDateFromInt(Integer.parseInt(pagingQuery.getFilters().get(key)));
                    //  LocalDate endLocalDate=SystemHelper.DateToLocal(date);
                    // Date endDate=SystemHelper.LocalToDate(endLocalDate.plusDays(1));
                    // criteria = criteria.and(key).gt(date).lt(endDate);
                    criteria = criteria.and(key).gt(date);
                } else {
                    criteria = criteria.and(key).regex(pagingQuery.getFilters().get(key), "i");
                }
            }
        }
        query.addCriteria(criteria);
    }


    public PageResponse<ImportByDevice> groupByDevice(PagingPageQuery request) {
        PageResponse<ImportByDevice> result = new PageResponse<>();
        try {
            List<AggregationOperation> listAggregations = new ArrayList<>();


            long skip = (request.getCurrentPage() - 1) * request.getPageSize();
            //   query.skip((int) skip);// 从那条记录开始
            //   query.limit((int) pagingQuery.getPageSize());// 取多少条记录

            //match
            Criteria criteria = new Criteria();
            if (request.getFilters() != null) {
                if (request.getFilters().keySet().contains("deviceName")) {
                    if (request.getFilters().get("deviceName").length() > 0) {
                        criteria = criteria.and("deviceName").regex(request.getFilters().get("deviceName"), "i");
                    }
                }
            }

            if (criteria.getCriteriaObject().keySet().size() > 0) {
                listAggregations.add(Aggregation.match(criteria));
            }

            //project
            listAggregations.add(Aggregation.project("deviceName", "exportTotal", "exportMale", "exportFemale"));
            //group
            listAggregations.add(Aggregation.group("deviceName").sum("exportTotal").as("total").sum("exportMale").as("male").sum("exportFemale").as("female"));
            //sort
            if (request.getOrderBy() != null && request.getOrderBy().length() > 0) {
                if (request.getOrderByName() != null && request.getOrderByName().length() > 0) {
                    listAggregations.add(Aggregation.sort(SystemHelper.getSortDirection(request.getOrderBy()), request.getOrderByName()));
                }
            }
            //skip
            listAggregations.add(Aggregation.skip(skip));
            //limit
            listAggregations.add(Aggregation.limit(request.getPageSize()));
            Aggregation aggregation = Aggregation.newAggregation(listAggregations);

            List<ImportByDevice> content = getAggregationStatic(
                    aggregation,
                    "ImportLog",
                    ImportByDevice.class);
            DBObject query = new BasicDBObject();
            if (request.getFilters() != null && request.getFilters().containsKey("deviceName")) {
                String params = request.getFilters().get("deviceName");
                Pattern pattern = Pattern.compile(params, Pattern.CASE_INSENSITIVE);
                query.put("deviceName", pattern);
            }
            long count = mongoTemplate.getCollection("ImportLog").distinct("deviceName", query).size();


            long totalPage = 0;//(int) (count / appQuery.getPageSize());
            if (count % request.getPageSize() == 0) {
                totalPage = count / request.getPageSize();
            } else {
                totalPage = (count / request.getPageSize() + 1);
            }
            result.setTotalRecord(count);//总共记录
            result.setTotalPage(totalPage);//总共页数
            result.setPageSize(request.getPageSize());//每页记录
            result.setFilters(request.getFilters());
            result.setCurrentPage(request.getCurrentPage());//当前页
            result.setItems(content);//查询内容
            result.setResult(true);
        } catch (Exception e) {
            result.setResult(false);
            logger.error(e.toString());
        }
        return result;
    }

    public PageResponse<ImportByDeviceRecord> groupByDeviceRecord(PagingPageQuery request) {
        PageResponse<ImportByDeviceRecord> result = new PageResponse<>();
        try {
            List<AggregationOperation> listAggregations = new ArrayList<>();
            long skip = (request.getCurrentPage() - 1) * request.getPageSize();
            Criteria criteria = new Criteria();
            if (request.getFilters() != null) {
                if (request.getFilters().keySet().contains("deviceName")) {
                    if (request.getFilters().get("deviceName").length() > 0) {
                        criteria = criteria.and("deviceName").regex(request.getFilters().get("deviceName"), "i");
                    }
                }
            }
            if (criteria.getCriteriaObject().keySet().size() > 0) {
                listAggregations.add(Aggregation.match(criteria));
            }
            //project
            listAggregations.add(Aggregation.project("deviceName", "exportTotal", "exportMale", "exportFemale"));
            //group
            listAggregations.add(Aggregation.group("deviceName").sum("exportTotal").as("total").sum("exportMale").as("male").sum("exportFemale").as("female"));
            //sort
            if (request.getOrderBy() != null && request.getOrderBy().length() > 0) {
                if (request.getOrderByName() != null && request.getOrderByName().length() > 0) {
                    listAggregations.add(Aggregation.sort(SystemHelper.getSortDirection(request.getOrderBy()), request.getOrderByName()));
                }
            }
            //skip
            listAggregations.add(Aggregation.skip(skip));
            //limit
            listAggregations.add(Aggregation.limit(request.getPageSize()));
            Aggregation aggregation = Aggregation.newAggregation(listAggregations);

            List<ImportByDeviceRecord> content = getAggregationStatic(aggregation, "ImportLog", ImportByDeviceRecord.class);

            List<String> selDeviceName = new ArrayList<>();
            for (ImportByDeviceRecord record : content) {
                selDeviceName.add(record.get_id());
            }
            DBObject project = (DBObject) JSON.parse("{$project:{deviceName:1,recordMale:{$cond:{if:{$eq:['$gender',1]},then:1,else:0}},recordFemale:{$cond:{if:{$eq:['$gender',2]},then:1,else:0}}}}}");
            List<ImportByDeviceRecord> content2 = getAggregationStatic(
                    Aggregation.newAggregation(
                            Aggregation.match(Criteria.where("status").gt(0).and("deviceName").in(selDeviceName)),
                            new CustomerAggregationOperation(project),
                            Aggregation.group("deviceName").count().as("recordTotal").sum("recordMale").as("recordMale").sum("recordFemale").as("recordFemale")),
                    "ApplicantMaster",
                    ImportByDeviceRecord.class);
            for (ImportByDeviceRecord record : content) {
                for (ImportByDeviceRecord applicant : content2) {
                    if (record.get_id().equals(applicant.get_id())) {
                        record.setRecordTotal(applicant.getRecordTotal());
                        record.setRecordMale(applicant.getRecordMale());
                        record.setRecordFemale(applicant.getRecordFemale());
                        record.setTotalDisparity(record.getTotal() - applicant.getRecordTotal());
                        record.setMaleDisparity(record.getMale() - applicant.getRecordMale());
                        record.setFemaleDisparity(record.getFemale() - applicant.getRecordFemale());
                    }
                }
            }

            DBObject query = new BasicDBObject();
            if (request.getFilters() != null && request.getFilters().containsKey("deviceName")) {
                String params = request.getFilters().get("deviceName");
                Pattern pattern = Pattern.compile(params, Pattern.CASE_INSENSITIVE);
                query.put("deviceName", pattern);
            }
            long count = mongoTemplate.getCollection("ImportLog").distinct("deviceName", query).size();

            long totalPage = 0;//(int) (count / appQuery.getPageSize());
            if (count % request.getPageSize() == 0) {
                totalPage = count / request.getPageSize();
            } else {
                totalPage = (count / request.getPageSize() + 1);
            }
            result.setTotalRecord(count);//总共记录
            result.setTotalPage(totalPage);//总共页数
            result.setPageSize(request.getPageSize());//每页记录
            result.setFilters(request.getFilters());
            result.setCurrentPage(request.getCurrentPage());//当前页
            result.setItems(content);//查询内容
            result.setResult(true);


            result.setResult(true);
        } catch (Exception e) {
            result.setResult(false);
            logger.error(e.toString());
        }

        return result;

    }
}
