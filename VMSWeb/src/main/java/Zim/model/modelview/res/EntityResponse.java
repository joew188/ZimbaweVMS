package Zim.model.modelview.res;

/**
 * Created by Laxton-Joe on 2017/8/11.
 */
public class EntityResponse<T> extends PagingResponse {
    private int recordIndex = 0;
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
}
