package Zim.model.modelview;

import java.util.List;

/**
 * Created by Laxton-Joe on 2017/8/9.
 */
public class AreaView {
    private int parentId;
    private int type;
    private List<AreaSimple> areas;

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<AreaSimple> getAreas() {
        return areas;
    }

    public void setAreas(List<AreaSimple> areas) {
        this.areas = areas;
    }
}
