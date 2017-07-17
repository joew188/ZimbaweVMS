package Zim.model.modelview.chart;

/**
 * Created by Laxton-Joe on 2017/6/17.
 */
public class ApplicantOfDateAvg {
    private int dateOfRegistration;
    private int count;
    private  int total;
    private  float avg;

    public int getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(int dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
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
