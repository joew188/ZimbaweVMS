package Zim.service;

import Zim.model.UploadHistory;
import Zim.model.modelview.req.PagingPageQuery;
import Zim.model.modelview.res.PageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Laxton-Joe on 2017/7/25.
 */
@Service
public class UploadHistoryService extends BaseService {
    private static Logger logger = LoggerFactory.getLogger(UploadHistoryService.class);
    @Autowired
    MongoTemplate mongoTemplate;

    public void add(UploadHistory uh) {
        mongoTemplate.insert(uh);
    }

    public PageResponse<UploadHistory> pageList(PagingPageQuery pagingQuery) {
        PageResponse<UploadHistory> result = new PageResponse<>();
        try {
            Query query = new Query();
            sortQuery(pagingQuery, query);
            setCriteria(pagingQuery, query);
            long total = mongoTemplate.count(query, UploadHistory.class);
            if (total > 0) {
                setPageQuery(pagingQuery, query);
                List<UploadHistory> listData = mongoTemplate.find(query, UploadHistory.class);
                result.setItems(listData);//查询内容
            }
            setPagePaging(result, pagingQuery, total);
            result.setResult(true);
        } catch (Exception e) {
            result.setResult(false);
            logger.error(e.toString());
        }
        return result;
    }


}
