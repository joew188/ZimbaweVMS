package Zim.model.modelview.importLog;

/**
 * Created by Laxton-Joe on 2017/7/18.
 */
public class ImportByDeviceRecord {
    private String deviceName;
    private int total;
    private int male;
    private int female;

    private int recordTotal;
    private int recordMale;
    private int recordFemale;

    private int totalDisparity;
    private int maleDisparity;
    private int femaleDisparity;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getMale() {
        return male;
    }

    public void setMale(int male) {
        this.male = male;
    }

    public int getFemale() {
        return female;
    }

    public void setFemale(int female) {
        this.female = female;
    }

    public int getRecordTotal() {
        return recordTotal;
    }

    public void setRecordTotal(int recordTotal) {
        this.recordTotal = recordTotal;
    }

    public int getRecordMale() {
        return recordMale;
    }

    public void setRecordMale(int recordMale) {
        this.recordMale = recordMale;
    }

    public int getRecordFemale() {
        return recordFemale;
    }

    public void setRecordFemale(int recordFemale) {
        this.recordFemale = recordFemale;
    }

    public int getTotalDisparity() {
        return totalDisparity;
    }

    public void setTotalDisparity(int totalDisparity) {
        this.totalDisparity = totalDisparity;
    }

    public int getMaleDisparity() {
        return maleDisparity;
    }

    public void setMaleDisparity(int maleDisparity) {
        this.maleDisparity = maleDisparity;
    }

    public int getFemaleDisparity() {
        return femaleDisparity;
    }

    public void setFemaleDisparity(int femaleDisparity) {
        this.femaleDisparity = femaleDisparity;
    }
}
