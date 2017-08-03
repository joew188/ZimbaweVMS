package Zim.model.modelview.chart;

/**
 * Created by Laxton-Joe on 2017/6/9.
 */
public class ApplicantOfProvince {

    private int provinceId;
    private String provinceName;
    private  int count;
    private int distinct;
    private int duplicate;





    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getDistinct() {
        return distinct;
    }

    public void setDistinct(int distinct) {
        this.distinct = distinct;
    }

    public int getDuplicate() {
        return duplicate;
    }

    public void setDuplicate(int duplicate) {
        this.duplicate = duplicate;
    }


    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }
}
