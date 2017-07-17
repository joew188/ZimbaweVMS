package Zim.model.modelview.importLog;

/**
 * Created by Laxton-Joe on 2017/7/12.
 */
public class ImportByDevice {
    private String deviceName;
    private int total;
    private int male;
    private int female;

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
}
