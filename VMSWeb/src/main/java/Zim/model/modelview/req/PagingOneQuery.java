package Zim.model.modelview.req;

import Zim.model.modelview.req.BasicPagingQuery;

/**
 * Created by Laxton-Joe on 2017/8/11.
 */
public class PagingOneQuery extends BasicPagingQuery {
    public PagingOneQuery() {

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
