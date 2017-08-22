package Zim.model.modelview.req;

import Zim.model.modelview.req.PagingQuery;

/**
 * Created by Laxton-Joe on 2017/8/11.
 */
public class PagingFindOneQuery  extends PagingQuery {
    public PagingFindOneQuery() {

    }
    private int recordIndex;
    private String recordId;
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
