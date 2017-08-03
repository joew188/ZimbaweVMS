package Zim.service;

import Zim.model.User;
import Zim.model.modelview.PagingQuery;
import Zim.model.modelview.SysPagination;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Laxton-Joe on 2017/7/7.
 */
@Service
public class UserService extends BaseService<User> {
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

    public User getById(String id) {
        return mongoTemplate.findById(id, User.class);
    }

    public List<User> get(String name, String pwd) {
        Query query = new Query();
        Criteria criteria = Criteria.where("name").is(name).and("password").is(pwd);
        query.addCriteria(criteria);
        return mongoTemplate.find(query, User.class);
    }

    public SysPagination<User> pageList(PagingQuery appQuery) {
        SysPagination<User> result = new SysPagination<>();
        try {
            Query query = new Query();
            sortQuery(appQuery, query);
            if (appQuery.getFilters() != null) {
//                appQuery.getFilters().keySet().stream().filter(key -> !User.getColumns().contains(key)).forEach(key -> {
//                    appQuery.getFilters().remove(key);
//                });
                setCriteria(appQuery, query);
            }
            int total = mongoTemplate.find(query, User.class).size();
            if (total > 0) {
                setPaging(result, appQuery, query, total);
                List<User> listData = mongoTemplate.find(query, User.class);
                result.setCurrentPage(appQuery.getCurrentPage());//当前页
                result.setItems(listData);//查询内容
            }
            result.setResult(true);
        } catch (Exception e) {
            result.setResult(false);
        }
        return result;
    }
//
//    public int findSize(String name) {
//        int result = 0;
//        Query query = new Query(Criteria.where("name").is(name));
//        result = mongoTemplate.find(query, User.class).size();
//        return result;
//    }

    public User findById(String id) {
        User result = null;
        try {
            result = mongoTemplate.findById(new ObjectId(id), User.class);
        } catch (Exception ex) {
            result = null;
        }
        return result;
    }
}
