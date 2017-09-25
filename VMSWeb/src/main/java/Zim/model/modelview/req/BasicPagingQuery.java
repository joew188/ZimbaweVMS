package Zim.model.modelview.req;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Laxton-Joe on 2017/2/20.
 */
public class BasicPagingQuery implements Serializable {
    public BasicPagingQuery() {

    }

    private long pageSize=10;
    private long currentPage=1;
    private String orderByName;
    private String orderBy;
    private HashMap<String, String> filters;

    public long getPageSize() {
        if(pageSize==0)
        {
            pageSize=10;
        }
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }



    public String getOrderByName() {
        return orderByName;
    }

    public void setOrderByName(String orderByName) {
        this.orderByName = orderByName;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public HashMap<String, String> getFilters() {
        return filters;
    }

    public void setFilters(HashMap<String, String> filters) {
        this.filters = filters;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }
}
