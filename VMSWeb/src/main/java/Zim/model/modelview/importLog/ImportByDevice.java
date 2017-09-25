package Zim.model.modelview.importLog;

/**
 * Created by Laxton-Joe on 2017/7/12.
 */
public class ImportByDevice {
    private String _id;
    private int total;
    private int male;
    private int female;



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

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
