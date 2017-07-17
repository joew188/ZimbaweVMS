package Zim.model.modelview.importLog;

import Zim.model.ImportLog;

import java.util.List;

/**
 * Created by Laxton-Joe on 2017/7/13.
 */
public class ImportGaps {
    private String deviceName;
    private List<ImportLog.gap> gaps;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public List<ImportLog.gap> getGaps() {
        return gaps;
    }

    public void setGaps(List<ImportLog.gap> gaps) {
        this.gaps = gaps;
    }
}
