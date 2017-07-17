//package Zim.model;
//
//import java.io.Serializable;
//
//
//import Zim.common.SystemHelper;
//import Zim.map.Record;
//
//import org.springframework.data.mongodb.core.mapping.Document;
//
//
//import java.util.*;
//
//@Document(collection = "Applicant")
//public class Applicant implements Serializable {
//    private String _id;
//    private int gender;
//
//
//    private String wardName;
//    private int ward;
//    private String constituencyName;
//    private int constituency;
//    private String districtName;
//    private int district;
//    private String provinceName;
//    private int province;
//
//    private int registrationNumber;
//    private String claimReference;
//    private String personName;
//    private String surname;
//    private String middleName;
//    private String fourthName;//null able
//    private String title;
//    private int disablity;
//    private int identificationCardType;
//    private String nationalIDCardNumber;
//    private int dateOfBirth;
//    private int dateOfRegistration;
//
//    private Date beginCreateDatetime;
//    private Date endCreateDatetime;
//    private Date beginEditDatetime;
//    private Date endEditDatetime;
//
//    private String houseNumber;//null able
//    private String streetName;//null able
//    private String suburb;//null able
//    private String gisLatitude;
//    private String gisLongitude;
//    private int status = 0;
//    private String operatorGuid;
//    private String operatorName;
//    private String deviceName;
//    // private int operatorId;
//    private String individualsId;
//    private String photo;
//
//    public int getGender() {
//        return gender;
//    }
//
//    public void setGender(int gender) {
//        this.gender = gender;
//    }
//
//    public long getRegistrationNumber() {
//        return registrationNumber;
//    }
//
//    public void setRegistrationNumber(int registrationNumber) {
//        this.registrationNumber = registrationNumber;
//    }
//
//    public String getClaimReference() {
//        return claimReference;
//    }
//
//    public void setClaimReference(String claimReference) {
//        this.claimReference = claimReference;
//    }
//
//    public String getPersonName() {
//        return personName;
//    }
//
//    public void setPersonName(String personName) {
//        this.personName = personName;
//    }
//
//    public String getSurname() {
//        return surname;
//    }
//
//    public void setSurname(String surname) {
//        this.surname = surname;
//    }
//
//    public String getMiddleName() {
//        return middleName;
//    }
//
//    public void setMiddleName(String middleName) {
//        this.middleName = middleName;
//    }
//
//
//    public int getStatus() {
//        return status;
//    }
//
//    //    Master|Unmached|Archive|Suspect
//    public void setStatus(int status) {
//        this.status = status;
//    }
//
//    public String getIndividualsId() {
//        return individualsId;
//    }
//
//    public void setIndividualsId(String individualsId) {
//        this.individualsId = individualsId;
//    }
//
//    /***
//     * 保存到MongoDB 的 Map
//     *
//     * @param individualsId
//     * @return
//     */
//    public Map<String, Object> toMap(String individualsId) {
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("_id", this.get_id());
//        map.put("gender", this.gender);
//
//        map.put("ward", this.ward);
//        map.put("wardName", this.getWardName());
//        map.put("district", this.district);
//        map.put("districtName", this.getDistrictName());
//        map.put("province", this.province);
//        map.put("provinceName", this.getProvinceName());
//        map.put("constituency", this.constituency);
//        map.put("constituencyName", this.getConstituencyName());
//        map.put("registrationNumber", this.registrationNumber);
//        map.put("claimReference", claimReference);
//        map.put("personName", this.personName);
//        map.put("surname", this.surname);
//        map.put("middleName", this.middleName);
//        if (this.fourthName != null)
//            map.put("fourthName", this.fourthName);
//
//        map.put("title", this.title);
//        map.put("disablity", this.disablity);
//        map.put("identificationCardType", this.identificationCardType);
//        map.put("nationalIDCardNumber", this.nationalIDCardNumber);
//
//
//        map.put("dateOfBirth", this.dateOfBirth);
//        map.put("dateOfRegistration", this.dateOfRegistration);
//        if (this.houseNumber != null)
//            map.put("houseNumber", this.houseNumber);
//        if (this.streetName != null)
//            map.put("streetName", this.streetName);
//        if (this.suburb != null)
//            map.put("suburb", this.suburb);
//
//        map.put("gisLatitude", this.gisLatitude);
//        map.put("gisLongitude", this.gisLongitude);
//        map.put("photo", this.photo);
//        map.put("status", this.status);
//        map.put("individualsId", individualsId);
//        map.put("operatorGuid", this.operatorGuid);
//        map.put("operatorName", this.operatorName);
//        map.put("deviceName", this.deviceName);
//        map.put("beginCreateDatetime", this.beginCreateDatetime);
//        map.put("endCreateDatetime", this.endCreateDatetime);
//        map.put("beginEditDatetime", this.beginEditDatetime);
//        map.put("endEditDatetime", this.endEditDatetime);
//        map.put("applicationFormContent", this.applicationFormContent);
//        map.put("signatureFormContent", this.signatureFormContent);
//        map.put("IDDocumentFormContent", this.IDDocumentFormContent);
//        map.put("supportingDocumentFormContent", this.supportingDocumentFormContent);
//        map.put("leftThumbImageArray", this.leftThumbImageArray);
//        map.put("leftIndexImageArray", this.leftIndexImageArray);
//        map.put("leftMiddleImageArray", this.leftMiddleImageArray);
//        map.put("leftRingImageArray", this.leftRingImageArray);
//        map.put("leftLittleImageArray", this.leftLittleImageArray);
//        map.put("rightThumbImageArray", this.rightThumbImageArray);
//        map.put("rightIndexImageArray", this.rightIndexImageArray);
//        map.put("rightMiddleImageArray", this.rightMiddleImageArray);
//        map.put("rightRingImageArray", this.rightRingImageArray);
//        map.put("rightLittleImageArray", this.rightLittleImageArray);
//        map.put("leftThumbWSQArray", this.leftThumbWSQArray);
//        map.put("leftIndexWSQArray", this.leftIndexWSQArray);
//        map.put("leftMiddleWSQArray", this.leftMiddleWSQArray);
//        map.put("leftRingWSQArray", this.leftRingWSQArray);
//        map.put("leftLittleWSQArray", this.leftLittleWSQArray);
//        map.put("rightThumbWSQArray", this.rightThumbWSQArray);
//        map.put("rightIndexWSQArray", this.rightIndexWSQArray);
//        map.put("rightMiddleWSQArray", this.rightMiddleWSQArray);
//        map.put("rightRingWSQArray", this.rightRingWSQArray);
//        map.put("rightLittleWSQArray", this.rightLittleWSQArray);
//        return map;
//    }
//
//
//    /***
//     * Record 转换到 Applicant 的实体对象
//     *
//     * @param rm
//     * @return
//     */
//    public static Applicant toApplicant(Record rm) {
//        Applicant result = new Applicant();
//        result.set_id(rm.getId());
//        result.setGender(rm.getGender().toLowerCase().equals("male") ? 0 : 1);
//        result.setWard(rm.getWardId());
//        result.setWardName(rm.getWardName());
//        result.setProvince(rm.getProvinceId());
//        result.setProvinceName(rm.getProvinceName());
//        result.setDistrict(rm.getDistrictId());
//        result.setDistrictName(rm.getDistrictName());
//        result.setConstituency(rm.getConstituencyId());
//        result.setConstituencyName(rm.getConstituencyName());
//        result.setClaimReference(rm.getClaimReference());
//        result.setPersonName(rm.getPersonName());
//        result.setSurname(rm.getSurname());
//        result.setMiddleName(rm.getMiddleName());
//        if (rm.getFourthName() != null)
//            result.setFourthName(rm.getFourthName());//null able
//        result.setTitle(rm.getTitle());
//        result.setDisablity(rm.getDisability());
//        result.setIdentificationCardType(rm.getIdentificationCardType());
//        result.setNationalIDCardNumber(rm.getNationalIDCardNumber());
//        result.setOperatorGuid(rm.getOperatorGuid());
//        result.setOperatorName(rm.getOperatorName());
//        result.setDeviceName(rm.getDeviceName());
//        int intBirth = SystemHelper.DateToInt(rm.getDateOfBirth().toGregorianCalendar().getTime());
//        if (intBirth > 0)
//            result.setDateOfBirth(intBirth);
//        int intRegistration = SystemHelper.DateToInt(rm.getEndCreateDatetime().toGregorianCalendar().getTime());
//        if (intRegistration > 0)
//            result.setDateOfRegistration(intRegistration);
//        if (rm.getHouseNumber() != null)
//            result.setHouseNumber(rm.getHouseNumber());
//        if (rm.getStreetName() != null)
//            result.setStreetName(rm.getStreetName());
//        if (rm.getSuburb() != null)
//            result.setSuburb(rm.getSuburb());
//        result.setGisLatitude(rm.getGisLatitude());
//        result.setGisLongitude(rm.getGisLongitude());
//        if (rm.getPhoto() != null)
//            result.setPhoto(rm.getPhoto());
//        if (rm.getBeginCreateDatetime() != null)
//            result.setBeginCreateDatetime(rm.getBeginCreateDatetime().toGregorianCalendar().getTime());
//        if (rm.getEndCreateDatetime() != null)
//            result.setEndCreateDatetime(rm.getEndCreateDatetime().toGregorianCalendar().getTime());
//        if (rm.getBeginEditDatetime() != null)
//            result.setBeginEditDatetime(rm.getBeginEditDatetime().toGregorianCalendar().getTime());
//        if (rm.getEndEditDatetime() != null)
//            result.setEndEditDatetime(rm.getEndEditDatetime().toGregorianCalendar().getTime());
//        if (rm.getApplicationFormContent() != null)
//            result.setApplicationFormContent(rm.getApplicationFormContent());
//        if (rm.getSignatureFormContent() != null)
//            result.setSignatureFormContent(rm.getSignatureFormContent());
//        if (rm.getIDDocumentFormContent() != null)
//            result.setIDDocumentFormContent(rm.getIDDocumentFormContent());
//        if (rm.getSupportingDocumentFormContent() != null)
//            result.setSupportingDocumentFormContent(rm.getSupportingDocumentFormContent());
//        if (rm.getLeftThumbImageArray() != null)
//            result.setLeftThumbImageArray(rm.getLeftThumbImageArray());
//        if (rm.getLeftIndexImageArray() != null)
//            result.setLeftIndexImageArray(rm.getLeftIndexImageArray());
//        if (rm.getLeftMiddleImageArray() != null)
//            result.setLeftMiddleImageArray(rm.getLeftMiddleImageArray());
//        if (rm.getLeftRingImageArray() != null)
//            result.setLeftRingImageArray(rm.getLeftRingImageArray());
//        if (rm.getLeftLittleImageArray() != null)
//            result.setLeftLittleImageArray(rm.getLeftLittleImageArray());
//        if (rm.getRightThumbImageArray() != null)
//            result.setRightThumbImageArray(rm.getRightThumbImageArray());
//        if (rm.getRightIndexImageArray() != null)
//            result.setRightIndexImageArray(rm.getRightIndexImageArray());
//        if (rm.getRightMiddleImageArray() != null)
//            result.setRightMiddleImageArray(rm.getRightMiddleImageArray());
//        if (rm.getRightRingImageArray() != null)
//            result.setRightRingImageArray(rm.getRightRingImageArray());
//        if (rm.getRightLittleImageArray() != null)
//            result.setRightLittleImageArray(rm.getRightLittleImageArray());
//        if (rm.getLeftThumbWSQArray() != null)
//            result.setLeftThumbWSQArray(rm.getLeftThumbWSQArray());
//        if (rm.getLeftIndexWSQArray() != null)
//            result.setLeftIndexWSQArray(rm.getLeftIndexWSQArray());
//        if (rm.getLeftMiddleWSQArray() != null)
//            result.setLeftMiddleWSQArray(rm.getLeftMiddleWSQArray());
//        if (rm.getLeftRingWSQArray() != null)
//            result.setLeftRingWSQArray(rm.getLeftRingWSQArray());
//        if (rm.getLeftLittleWSQArray() != null)
//            result.setLeftLittleWSQArray(rm.getLeftLittleWSQArray());
//        if (rm.getRightThumbWSQArray() != null)
//            result.setRightThumbWSQArray(rm.getRightThumbWSQArray());
//        if (rm.getRightIndexWSQArray() != null)
//            result.setRightIndexWSQArray(rm.getRightIndexWSQArray());
//        if (rm.getRightMiddleWSQArray() != null)
//            result.setRightMiddleWSQArray(rm.getRightMiddleWSQArray());
//        if (rm.getRightRingWSQArray() != null)
//            result.setRightRingWSQArray(rm.getRightRingWSQArray());
//        if (rm.getRightLittleWSQArray() != null)
//            result.setRightLittleWSQArray(rm.getRightLittleWSQArray());
//        return result;
//    }
//
////    public Map<String, Object> toMatchMap() {
////        Map<String, Object> map = new HashMap<String, Object>();
////        map.put(MatchQueue.ID, this.get_id());
////        map.put(MatchQueue.DATEOFBIRTH, this.dateOfBirth);
////        map.put(MatchQueue.GENDER, this.gender);
////        map.put(MatchQueue.SURNAME, this.surname);
////
////
////        return map;
////    }
//
//
//    public int getDateOfBirth() {
//        return dateOfBirth;
//    }
//
//    public void setDateOfBirth(int dateOfBirth) {
//        this.dateOfBirth = dateOfBirth;
//    }
//
//
//
//
//    public int getWard() {
//        return ward;
//    }
//
//    public void setWard(int ward) {
//        this.ward = ward;
//    }
//
//    public int getDistrict() {
//        return district;
//    }
//
//    public void setDistrict(int district) {
//        this.district = district;
//    }
//
//    public int getProvince() {
//        return province;
//    }
//
//    public void setProvince(int province) {
//        this.province = province;
//    }
//
//    public String getFourthName() {
//        return fourthName;
//    }
//
//    public void setFourthName(String fourthName) {
//        this.fourthName = fourthName;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public int getDisablity() {
//        return disablity;
//    }
//
//    public void setDisablity(int disablity) {
//        this.disablity = disablity;
//    }
//
//    public int getIdentificationCardType() {
//        return identificationCardType;
//    }
//
//    public void setIdentificationCardType(int identificationCardType) {
//        this.identificationCardType = identificationCardType;
//    }
//
//    public String getNationalIDCardNumber() {
//        return nationalIDCardNumber;
//    }
//
//    public void setNationalIDCardNumber(String nationalIDCardNumber) {
//        this.nationalIDCardNumber = nationalIDCardNumber;
//    }
//
//    public String getHouseNumber() {
//        return houseNumber;
//    }
//
//    public void setHouseNumber(String houseNumber) {
//        this.houseNumber = houseNumber;
//    }
//
//    public String getStreetName() {
//        return streetName;
//    }
//
//    public void setStreetName(String streetName) {
//        this.streetName = streetName;
//    }
//
//    public String getSuburb() {
//        return suburb;
//    }
//
//    public void setSuburb(String suburb) {
//        this.suburb = suburb;
//    }
//
//    public String getGisLatitude() {
//        return gisLatitude;
//    }
//
//    public void setGisLatitude(String gisLatitude) {
//        this.gisLatitude = gisLatitude;
//    }
//
//    public String getGisLongitude() {
//        return gisLongitude;
//    }
//
//    public void setGisLongitude(String gisLongitude) {
//        this.gisLongitude = gisLongitude;
//    }
//
//    public String getPhoto() {
//        return photo;
//    }
//
//    public void setPhoto(String photo) {
//        this.photo = photo;
//    }
//
//    public String get_id() {
//        return _id;
//    }
//
//    public void set_id(String _id) {
//        this._id = _id;
//    }
//
//    public String getOperatorGuid() {
//        return operatorGuid;
//    }
//
//    public void setOperatorGuid(String operatorGuid) {
//        this.operatorGuid = operatorGuid;
//    }
//
//    public String getOperatorName() {
//        return operatorName;
//    }
//
//    public void setOperatorName(String operatorName) {
//        this.operatorName = operatorName;
//    }
//
//    public String getDeviceName() {
//        return deviceName;
//    }
//
//    public void setDeviceName(String deviceName) {
//        this.deviceName = deviceName;
//    }
//
//    public int getDateOfRegistration() {
//        return dateOfRegistration;
//    }
//
//    public void setDateOfRegistration(int dateOfRegistration) {
//        this.dateOfRegistration = dateOfRegistration;
//    }
//
//    public Date getBeginCreateDatetime() {
//        return beginCreateDatetime;
//    }
//
//    public void setBeginCreateDatetime(Date beginCreateDatetime) {
//        this.beginCreateDatetime = beginCreateDatetime;
//    }
//
//    public Date getEndCreateDatetime() {
//        return endCreateDatetime;
//    }
//
//    public void setEndCreateDatetime(Date endCreateDatetime) {
//        this.endCreateDatetime = endCreateDatetime;
//    }
//
//    public Date getBeginEditDatetime() {
//        return beginEditDatetime;
//    }
//
//    public void setBeginEditDatetime(Date beginEditDatetime) {
//        this.beginEditDatetime = beginEditDatetime;
//    }
//
//    public Date getEndEditDatetime() {
//        return endEditDatetime;
//    }
//
//    public void setEndEditDatetime(Date endEditDatetime) {
//        this.endEditDatetime = endEditDatetime;
//    }
//
//
//    public int getConstituency() {
//        return constituency;
//    }
//
//    public void setConstituency(int constituency) {
//        this.constituency = constituency;
//    }
//
//
//    public String getConstituencyName() {
//        return constituencyName;
//    }
//
//    public void setConstituencyName(String constituencyName) {
//        this.constituencyName = constituencyName;
//    }
//
//    public String getDistrictName() {
//        return districtName;
//    }
//
//    public void setDistrictName(String districtName) {
//        this.districtName = districtName;
//    }
//
//    public String getProvinceName() {
//        return provinceName;
//    }
//
//    public void setProvinceName(String provinceName) {
//        this.provinceName = provinceName;
//    }
//
//    public String getWardName() {
//        return wardName;
//    }
//
//    public void setWardName(String wardName) {
//        this.wardName = wardName;
//    }
//
//    public final static String ID="_id";
//    public final static String GENDER="gender";
//    public final static String SURNAME="surname";
//    public final static String DATEOFBIRTH="dateOfBirth";
//
//
//    private String applicationFormContent;
//    private String signatureFormContent;
//    private String IDDocumentFormContent;
//    private String supportingDocumentFormContent;
//    private String leftThumbImageArray;
//    private String leftIndexImageArray;
//    private String leftMiddleImageArray;
//    private String leftRingImageArray;
//    private String leftLittleImageArray;
//    private String rightThumbImageArray;
//    private String rightIndexImageArray;
//    private String rightMiddleImageArray;
//    private String rightRingImageArray;
//    private String rightLittleImageArray;
//    private String leftThumbWSQArray;
//    private String leftIndexWSQArray;
//    private String leftMiddleWSQArray;
//    private String leftRingWSQArray;
//    private String leftLittleWSQArray;
//    private String rightThumbWSQArray;
//    private String rightIndexWSQArray;
//    private String rightMiddleWSQArray;
//    private String rightRingWSQArray;
//    private String rightLittleWSQArray;
//
//    public String getApplicationFormContent() {
//        return applicationFormContent;
//    }
//
//    public void setApplicationFormContent(String applicationFormContent) {
//        this.applicationFormContent = applicationFormContent;
//    }
//
//    public String getSignatureFormContent() {
//        return signatureFormContent;
//    }
//
//    public void setSignatureFormContent(String signatureFormContent) {
//        this.signatureFormContent = signatureFormContent;
//    }
//
//    public String getIDDocumentFormContent() {
//        return IDDocumentFormContent;
//    }
//
//    public void setIDDocumentFormContent(String IDDocumentFormContent) {
//        this.IDDocumentFormContent = IDDocumentFormContent;
//    }
//
//    public String getSupportingDocumentFormContent() {
//        return supportingDocumentFormContent;
//    }
//
//    public void setSupportingDocumentFormContent(String supportingDocumentFormContent) {
//        this.supportingDocumentFormContent = supportingDocumentFormContent;
//    }
//
//    public String getLeftThumbImageArray() {
//        return leftThumbImageArray;
//    }
//
//    public void setLeftThumbImageArray(String leftThumbImageArray) {
//        this.leftThumbImageArray = leftThumbImageArray;
//    }
//
//    public String getLeftIndexImageArray() {
//        return leftIndexImageArray;
//    }
//
//    public void setLeftIndexImageArray(String leftIndexImageArray) {
//        this.leftIndexImageArray = leftIndexImageArray;
//    }
//
//    public String getLeftMiddleImageArray() {
//        return leftMiddleImageArray;
//    }
//
//    public void setLeftMiddleImageArray(String leftMiddleImageArray) {
//        this.leftMiddleImageArray = leftMiddleImageArray;
//    }
//
//    public String getLeftRingImageArray() {
//        return leftRingImageArray;
//    }
//
//    public void setLeftRingImageArray(String leftRingImageArray) {
//        this.leftRingImageArray = leftRingImageArray;
//    }
//
//    public String getLeftLittleImageArray() {
//        return leftLittleImageArray;
//    }
//
//    public void setLeftLittleImageArray(String leftLittleImageArray) {
//        this.leftLittleImageArray = leftLittleImageArray;
//    }
//
//    public String getRightThumbImageArray() {
//        return rightThumbImageArray;
//    }
//
//    public void setRightThumbImageArray(String rightThumbImageArray) {
//        this.rightThumbImageArray = rightThumbImageArray;
//    }
//
//    public String getRightIndexImageArray() {
//        return rightIndexImageArray;
//    }
//
//    public void setRightIndexImageArray(String rightIndexImageArray) {
//        this.rightIndexImageArray = rightIndexImageArray;
//    }
//
//    public String getRightMiddleImageArray() {
//        return rightMiddleImageArray;
//    }
//
//    public void setRightMiddleImageArray(String rightMiddleImageArray) {
//        this.rightMiddleImageArray = rightMiddleImageArray;
//    }
//
//    public String getRightRingImageArray() {
//        return rightRingImageArray;
//    }
//
//    public void setRightRingImageArray(String rightRingImageArray) {
//        this.rightRingImageArray = rightRingImageArray;
//    }
//
//    public String getRightLittleImageArray() {
//        return rightLittleImageArray;
//    }
//
//    public void setRightLittleImageArray(String rightLittleImageArray) {
//        this.rightLittleImageArray = rightLittleImageArray;
//    }
//
//    public String getLeftThumbWSQArray() {
//        return leftThumbWSQArray;
//    }
//
//    public void setLeftThumbWSQArray(String leftThumbWSQArray) {
//        this.leftThumbWSQArray = leftThumbWSQArray;
//    }
//
//    public String getLeftIndexWSQArray() {
//        return leftIndexWSQArray;
//    }
//
//    public void setLeftIndexWSQArray(String leftIndexWSQArray) {
//        this.leftIndexWSQArray = leftIndexWSQArray;
//    }
//
//    public String getLeftMiddleWSQArray() {
//        return leftMiddleWSQArray;
//    }
//
//    public void setLeftMiddleWSQArray(String leftMiddleWSQArray) {
//        this.leftMiddleWSQArray = leftMiddleWSQArray;
//    }
//
//    public String getLeftRingWSQArray() {
//        return leftRingWSQArray;
//    }
//
//    public void setLeftRingWSQArray(String leftRingWSQArray) {
//        this.leftRingWSQArray = leftRingWSQArray;
//    }
//
//    public String getLeftLittleWSQArray() {
//        return leftLittleWSQArray;
//    }
//
//    public void setLeftLittleWSQArray(String leftLittleWSQArray) {
//        this.leftLittleWSQArray = leftLittleWSQArray;
//    }
//
//    public String getRightThumbWSQArray() {
//        return rightThumbWSQArray;
//    }
//
//    public void setRightThumbWSQArray(String rightThumbWSQArray) {
//        this.rightThumbWSQArray = rightThumbWSQArray;
//    }
//
//    public String getRightIndexWSQArray() {
//        return rightIndexWSQArray;
//    }
//
//    public void setRightIndexWSQArray(String rightIndexWSQArray) {
//        this.rightIndexWSQArray = rightIndexWSQArray;
//    }
//
//    public String getRightMiddleWSQArray() {
//        return rightMiddleWSQArray;
//    }
//
//    public void setRightMiddleWSQArray(String rightMiddleWSQArray) {
//        this.rightMiddleWSQArray = rightMiddleWSQArray;
//    }
//
//    public String getRightRingWSQArray() {
//        return rightRingWSQArray;
//    }
//
//    public void setRightRingWSQArray(String rightRingWSQArray) {
//        this.rightRingWSQArray = rightRingWSQArray;
//    }
//
//    public String getRightLittleWSQArray() {
//        return rightLittleWSQArray;
//    }
//
//    public void setRightLittleWSQArray(String rightLittleWSQArray) {
//        this.rightLittleWSQArray = rightLittleWSQArray;
//    }
//}
//
