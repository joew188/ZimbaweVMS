package Zim.model.modelview;

import com.mongodb.DBObject;

/**
 * Created by Laxton-Joe on 2017/2/17.
 */
public class MatchedResult {
    private String id;
    private int leven;

    private String registrationNumber;
    public MatchedResult(DBObject o1) {
        this.setId(String.valueOf(o1.get("id")));
        this.setRegistrationNumber(String.valueOf(o1.get("registrationNumber")));
        this.setLeven((int)(Double.parseDouble(o1.get("leven").toString())));

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLeven() {
        return leven;
    }

    public void setLeven(int leven) {
        this.leven = leven;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }
}

