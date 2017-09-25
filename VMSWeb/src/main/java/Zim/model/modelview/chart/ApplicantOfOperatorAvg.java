package Zim.model.modelview.chart;

import Zim.common.CustomFloatSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by Laxton-Joe on 2017/6/17.
 */
public class ApplicantOfOperatorAvg {
    private String operatorGuid;
    private String operatorName;
    private int count;
    private float total;

    private float avg;

    public String getOperatorGuid() {
        return operatorGuid;
    }

    public void setOperatorGuid(String operatorGuid) {
        this.operatorGuid = operatorGuid;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

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
}
