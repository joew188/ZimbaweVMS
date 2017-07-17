package Zim.task;

import Zim.model.Applicant;

/**
 * Created by Laxton-Joe on 2017/7/17.
 */
public class MatchInfo {
    private boolean waitMatch;
    private String _id;
    private String guid;
    private String surname;
    private Boolean gender;
    private int dateOfBirth;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public int getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(int dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public static MatchInfo toMatchInfo(Applicant applicant, boolean importSuccess) {
        MatchInfo info = new MatchInfo();
        info.set_id(applicant.get_id());
        info.setGuid(applicant.getGuid());
        info.setSurname(applicant.getSurname());
        info.setGender(applicant.getGender());
        info.setDateOfBirth(applicant.getDateOfBirth());
        info.setWaitMatch(importSuccess);
        return info;
    }

    public boolean isWaitMatch() {
        return waitMatch;
    }

    public void setWaitMatch(boolean waitMatch) {
        this.waitMatch = waitMatch;
    }
}