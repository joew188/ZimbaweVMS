package Zim.service;

import Zim.model.UploadHistory;
import Zim.model.modelview.req.PagingQuery;
import Zim.model.modelview.res.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Laxton-Joe on 2017/7/25.
 */
@Service
public class UploadHistoryService extends BaseService<UploadHistory> {
    @Autowired
    MongoTemplate mongoTemplate;

    public void add(UploadHistory uh) {
        mongoTemplate.insert(uh);
    }

    public PageResponse<UploadHistory> pageList(PagingQuery pagingQuery) {
        PageResponse<UploadHistory> result = new PageResponse<>();
        try {
            Query query = new Query();
            sortQuery(pagingQuery, query);
            if (pagingQuery.getFilters() != null) {
                setCriteria(pagingQuery, query);
            }
            long total = mongoTemplate.count(query, UploadHistory.class);
            if (total > 0) {
                setPaging(result, pagingQuery, query, total);
                List<UploadHistory> listData = mongoTemplate.find(query, UploadHistory.class);
                result.setCurrentPage(pagingQuery.getCurrentPage());//当前页
                result.setItems(listData);//查询内容
            }
            result.setResult(true);
        } catch (Exception e) {
            result.setResult(false);
        }
        return result;
    }


}
