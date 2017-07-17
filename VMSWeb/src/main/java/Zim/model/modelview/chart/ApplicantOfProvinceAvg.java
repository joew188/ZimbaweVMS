package Zim.model.modelview.chart;

/**
 * Created by Laxton-Joe on 2017/6/17.
 */
public class ApplicantOfProvinceAvg {
    private int province;
    private String provinceName;
    private  int count;
    private  int total;
    private  float avg;

    public int getProvince() {
        return province;
    }

    public void setProvince(int province) {
        this.province = province;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public float getAvg() {
        return avg;
    }

    public void setAvg(float avg) {
        this.avg = avg;
    }
}
