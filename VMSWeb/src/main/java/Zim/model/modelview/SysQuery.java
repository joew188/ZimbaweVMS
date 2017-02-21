package Zim.model.modelview;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Laxton-Joe on 2017/2/20.
 */
public class SysQuery implements Serializable {
    public SysQuery() {

    }

    private int pageSize=10;
    private int currentPage=1;
    private String orderByName;
    private String orderBy;
    private HashMap<String, String> filters;

    public int getPageSize() {
        if(pageSize==0)
        {
            pageSize=10;
        }
        return pageSize;
    }

    public void setPageSize(int pageSize) {
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

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}