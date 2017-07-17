package Zim.model.modelview.chart;

/**
 * Created by Laxton-Joe on 2017/6/20.
 */
public class ProvinceStatistic {
    private int province;
    private String provinceName;
    private int count;
    private int male;
    private int female;
    private int age0_20;
    private int age20_40;
    private int age40_60;
    private int age60_80;
    private int age80;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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

    public int getAge0_20() {
        return age0_20;
    }

    public void setAge0_20(int age0_20) {
        this.age0_20 = age0_20;
    }

    public int getAge20_40() {
        return age20_40;
    }

    public void setAge20_40(int age20_40) {
        this.age20_40 = age20_40;
    }

    public int getAge40_60() {
        return age40_60;
    }

    public void setAge40_60(int age40_60) {
        this.age40_60 = age40_60;
    }

    public int getAge60_80() {
        return age60_80;
    }

    public void setAge60_80(int age60_80) {
        this.age60_80 = age60_80;
    }

    public int getAge80() {
        return age80;
    }

    public void setAge80(int age80) {
        this.age80 = age80;
    }

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
}
