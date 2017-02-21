package Zim.model.modelview;

import java.io.Serializable;

/**
 * Created by Laxton-Joe on 2017/2/20.
 */
public class DuplicateAction implements Serializable {
    private String duplicateId;
    private String action;

    public String getDuplicateId() {
        return duplicateId;
    }

    public void setDuplicateId(String duplicateId) {
        this.duplicateId = duplicateId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
