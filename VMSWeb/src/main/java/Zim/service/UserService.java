package Zim.service;

import Zim.model.User;
import Zim.model.modelview.ApplicantQuery;
import Zim.model.modelview.SysPagination;
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
public class UserService {
    private static String USER_COLLECTION = "User";
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

    public SysPagination<User> pageList(ApplicantQuery appQuery) {

        SysPagination<User> result = new SysPagination<>();
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
                    if (User.getColumns().contains(key)) {
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
            int count = mongoTemplate.find(query, User.class).size();
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
                List<User> listData = mongoTemplate.find(query, User.class);
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
}
