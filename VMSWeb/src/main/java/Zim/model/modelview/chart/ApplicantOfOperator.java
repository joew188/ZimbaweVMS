package Zim.model.modelview.chart;

/**
 * Created by Laxton-Joe on 2017/6/12.
 */
public class ApplicantOfOperator {
    private String operatorGuid;
    private String operatorName;
    private int count;


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

    public String getOperatorGuid() {
        return operatorGuid;
    }

    public void setOperatorGuid(String operatorGuid) {
        this.operatorGuid = operatorGuid;
    }
}
