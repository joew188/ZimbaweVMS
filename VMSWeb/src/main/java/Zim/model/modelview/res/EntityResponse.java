package Zim.model.modelview.res;

/**
 * Created by Laxton-Joe on 2017/8/11.
 */
public class EntityResponse<T> extends BasicPageResponse {
    private int recordIndex = 0;
    private String recordId;
    private T entity;

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public int getRecordIndex() {
        return recordIndex;
    }

    public void setRecordIndex(int recordIndex) {
        this.recordIndex = recordIndex;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }
}
