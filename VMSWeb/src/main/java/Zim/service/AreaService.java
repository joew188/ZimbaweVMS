package Zim.service;

import Zim.common.SystemHelper;
import Zim.model.Area;
import Zim.mongo.MongoDBDaoImpl;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Service;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * Created by Laxton-Joe on 2017/8/7.
 */
@Service
public class AreaService {
    static MongoDBDaoImpl mongoDBDaoImpl = MongoDBDaoImpl.getMongoDBDaoImpl();
    @Autowired
    MongoTemplate mongoTemplate;

    public String addArea(Area area) {
        mongoDBDaoImpl.insert(SystemHelper.MONGODBSETTING_DB, "Area", area.toDBObject());
        return area.get_id().toString();
    }

    public List<Area> getAreas(int pid, int type) {
        DBObject fieldObject = new BasicDBObject();
        fieldObject.put("areaId", true);
        fieldObject.put("name", true);
        DBObject queryObject = new BasicDBObject();
        Query query = new BasicQuery(queryObject, fieldObject);
        Criteria criteria = Criteria.where("parentAreaID").is(pid).and("type").is(type);
        query.addCriteria(criteria);
        return mongoTemplate.find(query, Area.class);
    }
}
