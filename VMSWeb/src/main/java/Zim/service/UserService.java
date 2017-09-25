package Zim.service;

import Zim.model.User;
import Zim.model.modelview.req.PagingPageQuery;
import Zim.model.modelview.res.PageResponse;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Laxton-Joe on 2017/7/7.
 */
@Service
public class UserService extends BaseService {
    private static Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    MongoTemplate mongoTemplate;

    public void add(User user) {
        mongoTemplate.insert(user);
    }

    public void edit(User user) {
        mongoTemplate.save(user);
    }

    public void delete(User user) {
        mongoTemplate.remove(user);
    }

    public List<User> get(String name, String pwd) {
        Query query = new Query();
        Criteria criteria = Criteria.where("name").is(name).and("password").is(pwd);
        query.addCriteria(criteria);
        return mongoTemplate.find(query, User.class);
    }

    public PageResponse<User> pageList(PagingPageQuery pagingPageQuery) {
        PageResponse<User> result = new PageResponse<>();
        try {
            Query query = new Query();
            sortQuery(pagingPageQuery, query);
            setCriteria(pagingPageQuery, query);
            long total = mongoTemplate.count(query, User.class);
            if (total > 0) {
                setPageQuery(pagingPageQuery, query);
                List<User> listData = mongoTemplate.find(query, User.class);
                result.setItems(listData);
            }
            setPagePaging(result, pagingPageQuery, total);
            result.setResult(true);
        } catch (Exception e) {
            result.setResult(false);
            logger.error(e.toString());
        }
        return result;
    }

    public User findById(String id) {
        User result = null;
        try {
            result = mongoTemplate.findById(new ObjectId(id), User.class);
        } catch (Exception ex) {
            result = null;
            logger.error(ex.toString());
        }
        return result;
    }
}
