package Zim.service;

import Zim.model.UploadHistory;
import Zim.model.modelview.PagingQuery;
import Zim.model.modelview.SysPagination;
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

    public SysPagination<UploadHistory> pageList(PagingQuery pagingQuery) {
        SysPagination<UploadHistory> result = new SysPagination<>();
        try {
            Query query = new Query();
            sortQuery(pagingQuery, query);
            if (pagingQuery.getFilters() != null) {
                setCriteria(pagingQuery, query);
            }
            int total = mongoTemplate.find(query, UploadHistory.class).size();
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
