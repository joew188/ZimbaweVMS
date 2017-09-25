package Zim.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Laxton-Joe on 2017/9/18.
 */

@Document(collection = "MatchTaskQueue")
public class MatchTaskQueue {
    public MatchTaskQueue(String taskName) {
        this.taskName = taskName;
    }

    private ObjectId _id;
    private String taskName;

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public org.bson.Document toDocument() {

        org.bson.Document document = new org.bson.Document();
        document.append("taskName", this.getTaskName());

        return document;
    }
}
