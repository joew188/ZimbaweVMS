package Zim.model.modelview.chart;

import Zim.common.CustomFloatSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by Laxton-Joe on 2017/6/17.
 */
public class ApplicantOfDeviceAvg {
    private String _id;
    private int count;
    private  float total;
    private  float avg;



    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    @JsonSerialize(using = CustomFloatSerializer.class)
    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    @JsonSerialize(using = CustomFloatSerializer.class)
    public float getAvg() {
        return avg;
    }

    public void setAvg(float avg) {
        this.avg = avg;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
