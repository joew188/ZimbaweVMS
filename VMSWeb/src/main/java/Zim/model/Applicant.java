package Zim.model;

import java.io.Serializable;


import Zim.common.SystemHelper;
import Zim.map.Record;

import org.springframework.data.mongodb.core.mapping.Document;


import java.util.*;

@Document(collection = "Applicant")
public class Applicant implements Serializable {
    private String _id;
    private int gender;
    private int registrationCentre;
    private int localAuthority;
    private int ward;
    private int district;
    private int province;
    private int registrationNumber;
    private String claimReference;
    private String personName;
    private String surname;
    private String middleName;
    private String fourthName;//null able
    private String title;
    private int disablity;
    private int identificationCardType;
    private String nationalIDCardNumber;
    private int dateOfBirth;
    private String houseNumber;//null able
    private String streetName;//null able
    private String suburb;//null able
    private String gisLatitude;
    private String gisLongitude;
    private int status = 0;
    private String individualsId;
    private String photo;

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getRegistrationCentre() {
        return registrationCentre;
    }

    public void setRegistrationCentre(int registrationCentre) {
        this.registrationCentre = registrationCentre;
    }

    public long getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(int registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getClaimReference() {
        return claimReference;
    }

    public void setClaimReference(String claimReference) {
        this.claimReference = claimReference;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getIndividualsId() {
        return individualsId;
    }

    public void setIndividualsId(String individualsId) {
        this.individualsId = individualsId;
    }

//    public String getId() {
//        return _id;
//    }
//
//    public void setId(String id) {
//        this._id = id;
//    }

    /***
     * 保存到MongoDB 的 Map
     *
     * @param individualsId
     * @return
     */
    public Map<String, Object> toMap(String individualsId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("_id", this.get_id());
        map.put("gender", this.gender);
        map.put("registrationCentre", this.registrationCentre);
        map.put("localAuthority", this.localAuthority);
        map.put("ward", this.ward);
        map.put("district", this.district);
        map.put("province", this.province);
        map.put("registrationNumber", this.registrationNumber);
        map.put("claimReference", claimReference);
        map.put("personName", this.personName);
        map.put("surname", this.surname);
        map.put("middleName", this.middleName);
        if (this.fourthName != null)
            map.put("fourthName", this.fourthName);
//        else
//            map.put("fourthName","");
        map.put("title", this.title);
        map.put("disablity", this.disablity);
        map.put("identificationCardType", this.identificationCardType);
        map.put("nationalIDCardNumber", this.nationalIDCardNumber);


        map.put("dateOfBirth", this.dateOfBirth);
        if (this.houseNumber != null)
            map.put("houseNumber", this.houseNumber);
        if (this.streetName != null)
            map.put("streetName", this.streetName);
        if (this.suburb != null)
            map.put("suburb", this.suburb);

        map.put("gisLatitude", this.gisLatitude);
        map.put("gisLongitude", this.gisLongitude);
        map.put("photo", this.photo);
        map.put("status", this.status);
        map.put("individualsId", individualsId);
        return map;
    }


    /***
     * Record 转换到 Applicant 的实体对象
     *
     * @param rm
     * @return
     */
    public static Applicant toApplicant(Record rm) {
        Applicant result = new Applicant();
        result.set_id(rm.getID());
        result.setGender(rm.getGender());
        result.setRegistrationCentre(rm.getRegistrationCentre());
        result.setLocalAuthority(0);
        result.setWard(0);
        result.setDistrict(1);
        result.setProvince(1);
        result.setRegistrationNumber(rm.getRegistrationNumber());
        result.setClaimReference(rm.getClaimReference());
        result.setPersonName(rm.getPersonName());
        result.setSurname(rm.getSurname());
        result.setMiddleName(rm.getMiddleName());
        if (rm.getFourthName() != null)
            result.setFourthName(rm.getFourthName());//null able
        result.setTitle(rm.getPersonName());
        result.setDisablity(rm.getDisability());
        result.setIdentificationCardType(rm.getIdentificationCardType());
        result.setNationalIDCardNumber(rm.getNationalIDCardNumber());

        int intBirth = SystemHelper.DateToInt(rm.getDateOfBirth().toGregorianCalendar().getTime());
        if (intBirth > 0)
            result.setDateOfBirth(intBirth);
        if (rm.getHouseNumber() != null)
            result.setHouseNumber(rm.getHouseNumber());
        if (rm.getStreetName() != null)
            result.setStreetName(rm.getStreetName());
        if (rm.getSuburb() != null)
            result.setSuburb(rm.getSuburb());
        result.setGisLatitude(rm.getGIS());
        result.setGisLongitude(rm.getGIS());
        if (rm.getPhoto() != null)
            result.setPhoto(rm.getPhoto());

        return result;
    }

    public Map<String, Object> toMatchMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(MatchQueue.ID, this.get_id());
        map.put(MatchQueue.DATEOFBIRTH, this.dateOfBirth);
        map.put(MatchQueue.GENDER, this.gender);
        map.put(MatchQueue.SURNAME, this.surname);


        return map;
    }



    public int getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(int dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public static List<String> getColumns() {
        List<String> result = new ArrayList<>();
        result.add("_id");
        result.add("gender");
        result.add("localAuthority");
        result.add("houseNumber");
        result.add("ward");
        result.add("title");
        result.add("disablity");
        result.add("nationalIDCardNumber");
        result.add("streetName");
        result.add("province");
        result.add("claimReference");
        result.add("surname");
        result.add("registrationCentre");
        result.add("identificationCardType");
        result.add("identificationCardType");
        result.add("fourthName");
        result.add("dateOfBirth");
        result.add("personName");
        result.add("registrationNumber");
        result.add("district");
        result.add("gisLongitude");
        result.add("suburb");
        result.add("middleName");
        result.add("gisLatitude");
        result.add("individualsId");
        result.add("status");
        return result;
    }

    public int getLocalAuthority() {
        return localAuthority;
    }

    public void setLocalAuthority(int localAuthority) {
        this.localAuthority = localAuthority;
    }

    public int getWard() {
        return ward;
    }

    public void setWard(int ward) {
        this.ward = ward;
    }

    public int getDistrict() {
        return district;
    }

    public void setDistrict(int district) {
        this.district = district;
    }

    public int getProvince() {
        return province;
    }

    public void setProvince(int province) {
        this.province = province;
    }

    public String getFourthName() {
        return fourthName;
    }

    public void setFourthName(String fourthName) {
        this.fourthName = fourthName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDisablity() {
        return disablity;
    }

    public void setDisablity(int disablity) {
        this.disablity = disablity;
    }

    public int getIdentificationCardType() {
        return identificationCardType;
    }

    public void setIdentificationCardType(int identificationCardType) {
        this.identificationCardType = identificationCardType;
    }

    public String getNationalIDCardNumber() {
        return nationalIDCardNumber;
    }

    public void setNationalIDCardNumber(String nationalIDCardNumber) {
        this.nationalIDCardNumber = nationalIDCardNumber;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getGisLatitude() {
        return gisLatitude;
    }

    public void setGisLatitude(String gisLatitude) {
        this.gisLatitude = gisLatitude;
    }

    public String getGisLongitude() {
        return gisLongitude;
    }

    public void setGisLongitude(String gisLongitude) {
        this.gisLongitude = gisLongitude;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}

