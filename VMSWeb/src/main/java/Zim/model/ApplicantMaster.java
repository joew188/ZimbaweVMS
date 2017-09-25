package Zim.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by Laxton-Joe on 2017/8/23.
 */
@Document(collection = "ApplicantMaster")
public class ApplicantMaster extends QueueObject {
    private String _id;
    private String surname;
    private String forenames;
    private byte gender;
    private int dateOfBirth;
    private int dateOfRegistration;
    private int provinceId;
    private String provinceName;
    private int districtId;
    private String districtName;
    private int constituencyId;
    private String constituencyName;
    private short sortNumber;
    private String deviceName;
    private String operatorGuid;
    private String operatorName;
    private String registrationNumber;
    private int status;
    private Date beginCreateDatetime;
    private Date endCreateDatetime;
    private String importTask;

    public ApplicantMaster(boolean isPoisonPill) {
        this.setPoisonPill(isPoisonPill);
    }

    public ApplicantMaster() {

    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getForenames() {
        return forenames;
    }

    public void setForenames(String forenames) {
        this.forenames = forenames;
    }

    public byte getGender() {
        return gender;
    }

    public void setGender(byte gender) {
        this.gender = gender;
    }

    public int getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(int dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(int dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public short getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(short sortNumber) {
        this.sortNumber = sortNumber;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

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

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getBeginCreateDatetime() {
        return beginCreateDatetime;
    }

    public void setBeginCreateDatetime(Date beginCreateDatetime) {
        this.beginCreateDatetime = beginCreateDatetime;
    }

    public Date getEndCreateDatetime() {
        return endCreateDatetime;
    }

    public void setEndCreateDatetime(Date endCreateDatetime) {
        this.endCreateDatetime = endCreateDatetime;
    }

    public int getConstituencyId() {
        return constituencyId;
    }

    public void setConstituencyId(int constituencyId) {
        this.constituencyId = constituencyId;
    }

    public String getConstituencyName() {
        return constituencyName;
    }

    public void setConstituencyName(String constituencyName) {
        this.constituencyName = constituencyName;
    }

    public String getImportTask() {
        return importTask;
    }

    public void setImportTask(String importTask) {
        this.importTask = importTask;
    }

//    public MatchInfo toMatchInfo() {
//        MatchInfo info = new MatchInfo();
//        info.set_id(this.get_id());
//        info.setFullName(this.forenames + " " + this.surname);
//        info.setGender(this.getGender());
//        info.setDateOfBirth(this.getDateOfBirth());
//        info.setRegistrationNumber(this.getRegistrationNumber());
//        return info;
//    }


//    public org.bson.Document toDocument() {
//        org.bson.Document dbObject = new org.bson.Document();
//        if (this.get_id() != null && this.get_id().length() > 0) {
//            dbObject.append("_id", this.get_id());
//        }
//        dbObject.append("importTask", this.getImportTask());
//        // dbObject.append("transactionId", transactionId);
//        dbObject.append("forenames", this.getForenames());
//        dbObject.append("surname", this.getSurname());
//        dbObject.append("gender", this.getGender());
//        dbObject.append("dateOfBirth", this.getDateOfBirth());
//        dbObject.append("provinceId", this.getProvinceId());
//        dbObject.append("provinceName", this.getProvinceName());
//        dbObject.append("districtId", this.getDistrictId());
//        dbObject.append("districtName", this.getDistrictName());
//        dbObject.append("constituencyId", this.getConstituencyId());
//        dbObject.append("constituencyName", this.getConstituencyName());
//        dbObject.append("registrationNumber", this.getRegistrationNumber());
//        dbObject.append("dateOfRegistration", this.getDateOfRegistration());
//        dbObject.append("deviceName", this.getDeviceName());
//        dbObject.append("sortNumber", this.getSortNumber());
//        dbObject.append("operatorGuid", this.getOperatorGuid());
//        dbObject.append("operatorName", this.getOperatorName());
//        dbObject.append("beginCreateDatetime", this.getBeginCreateDatetime());
//        dbObject.append("endCreateDatetime", this.getEndCreateDatetime());
//        dbObject.append("status", 0);
//        return dbObject;
//    }
}
