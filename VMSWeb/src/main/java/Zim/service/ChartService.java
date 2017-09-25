package Zim.service;

import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Laxton-Joe on 2017/9/22.
 */
@Service
public class ChartService extends BaseService {
    public <T> List<T> getAggregation(Aggregation aggregation, Class<T> entityClass) {
        return getAggregationStatic(aggregation, "ApplicantMaster", entityClass);
    }

}
