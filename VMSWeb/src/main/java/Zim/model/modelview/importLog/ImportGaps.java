package Zim.model.modelview.importLog;

import java.util.List;

/**
 * Created by Laxton-Joe on 2017/7/13.
 */
public class ImportGaps {
    private String deviceName;
    private List<gap> gaps;
    private int beginIndex;
    private int endIndex;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public List<gap> getGaps() {
        return gaps;
    }

    public void setGaps(List<gap> gaps) {
        this.gaps = gaps;
    }

    public int getBeginIndex() {
        return beginIndex;
    }

    public void setBeginIndex(int beginIndex) {
        this.beginIndex = beginIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

//    public List<ImportLog.gap> getGaps() {
//        return gaps;
//    }
//
//    public void setGaps(List<ImportLog.gap> gaps) {
//        this.gaps = gaps;
//    }
}
