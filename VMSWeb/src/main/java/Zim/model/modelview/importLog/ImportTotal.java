package Zim.model.modelview.importLog;

/**
 * Created by Laxton-Joe on 2017/9/6.
 */
public class ImportTotal {
    private String _id;
    private int matchedTotal;
    private int importTotal;




    public int getImportTotal() {
        return importTotal;
    }

    public void setImportTotal(int importTotal) {
        this.importTotal = importTotal;
    }

    public int getMatchedTotal() {
        return matchedTotal;
    }

    public void setMatchedTotal(int matchedTotal) {
        this.matchedTotal = matchedTotal;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
