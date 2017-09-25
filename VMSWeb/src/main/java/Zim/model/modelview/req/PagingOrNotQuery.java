package Zim.model.modelview.req;

import Zim.model.modelview.req.BasicPagingQuery;

/**
 * Created by Laxton-Joe on 2017/8/9.
 */
public class PagingOrNotQuery extends BasicPagingQuery {
    private boolean paging;

    public boolean isPaging() {
        return paging;
    }

    public void setPaging(boolean paging) {
        this.paging = paging;
    }

}
