package Zim.model;

/**
 * Created by Laxton-Joe on 2017/7/17.
 */
public class MatchInfo extends QueueObject {

    private String _id;
    private String registrationNumber;
    private String fullName;
    private int gender;
    private int dateOfBirth;

    public MatchInfo(boolean isPoisonPill) {
        this.setPoisonPill(isPoisonPill);
    }

    public MatchInfo() {

    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }


    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(int dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }
}