package Zim.model.modelview.res;

import java.util.List;

/**
 * Created by Laxton-Joe on 2017/8/11.
 */
public class PageResponse<T> extends BasicPageResponse {
    private List<T> items;

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
