package Zim.common;

import com.mongodb.DBObject;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;

/**
 * Created by Laxton-Joe on 2017/9/21.
 */
public class CustomerAggregationOperation implements AggregationOperation {
    private DBObject operation;

    public CustomerAggregationOperation(DBObject operation) {
        this.operation = operation;
    }

    @Override
    public DBObject toDBObject(AggregationOperationContext context) {
        return context.getMappedObject(operation);
    }
}