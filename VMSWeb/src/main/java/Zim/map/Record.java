package Zim.map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "Record")
public class Record {

    @XmlAttribute(name = "ID", required = true)
    private String id;

    @XmlAttribute(name = "OperatorGuid", required = true)
    private String operatorGuid;
    @XmlAttribute(name = "OperatorName", required = true)
    private String operatorName;
    @XmlAttribute(name = "DeviceName", required = true)
    private String deviceName;

    @XmlAttribute(name = "Gender", required = true)
    private String gender;
    @XmlAttribute(name = "PersonName", required = true)
    private String personName;
    @XmlAttribute(name = "Surname", required = true)
    private String surname;
    @XmlAttribute(name = "MiddleName", required = true)
    private String middleName;
    @XmlAttribute(name = "FourthName", required = false)
    private String fourthName;
    @XmlAttribute(name = "Title", required = false)
    private String title;
    @XmlAttribute(name = "StreetName", required = true)
    private String streetName;
    @XmlAttribute(name = "IdentificationCardType", required = true)
    @XmlSchemaType(name = "unsignedInt")
    private int identificationCardType;
    @XmlAttribute(name = "NationalIDCardNumber", required = true)
    private String nationalIDCardNumber;
    @XmlAttribute(name = "DateOfBirth", required = true)
    @XmlSchemaType(name = "date")
    private XMLGregorianCalendar dateOfBirth;
    @XmlAttribute(name = "HouseNumber", required = false)
    private String houseNumber;//null able


    @XmlAttribute(name = "ProvinceId", required = true)
    @XmlSchemaType(name = "unsignedInt")
    private int provinceId;
    @XmlAttribute(name = "Province", required = true)
    private String provinceName;
    @XmlAttribute(name = "DistrictId", required = true)
    @XmlSchemaType(name = "unsignedInt")
    private int districtId;
    @XmlAttribute(name = "District", required = true)
    private String districtName;
    @XmlAttribute(name = "ConstituencyId", required = true)
    @XmlSchemaType(name = "unsignedInt")
    private int constituencyId;
    @XmlAttribute(name = "Constituency", required = true)
    private String constituencyName;
    @XmlAttribute(name = "WardId", required = true)
    @XmlSchemaType(name = "unsignedInt")
    private int wardId;
    @XmlAttribute(name = "Ward", required = true)
    private String wardName;
    @XmlAttribute(name = "PollingStationId", required = true)
    @XmlSchemaType(name = "unsignedInt")
    private int pollingStationId;
    @XmlAttribute(name = "PollingStation", required = true)
    private String pollingStation;
    @XmlAttribute(name = "RegistrationNumber", required = true)
    private String registrationNumber;

    @XmlAttribute(name = "BeginCreateDatetime", required = true)
    @XmlSchemaType(name = "date")
    private XMLGregorianCalendar beginCreateDatetime;
    @XmlAttribute(name = "EndCreateDatetime", required = true)
    @XmlSchemaType(name = "date")
    private XMLGregorianCalendar endCreateDatetime;
    @XmlAttribute(name = "BeginEditDatetime")
    @XmlSchemaType(name = "date")
    private XMLGregorianCalendar beginEditDatetime;
    @XmlAttribute(name = "EndEditDatetime")
    @XmlSchemaType(name = "date")
    private XMLGregorianCalendar endEditDatetime;

    @XmlAttribute(name = "ClaimReference", required = true)
    @XmlSchemaType(name = "unsignedByte")
    private String claimReference;
    @XmlAttribute(name = "Disability", required = true)
    @XmlSchemaType(name = "unsignedByte")
    private short disability;
    @XmlAttribute(name = "Suburb", required = true)
    private String suburb;

    @XmlAttribute(name = "GISLatitude", required = true)
    private String gisLatitude;

    @XmlAttribute(name = "GISLongitude", required = true)
    private String gisLongitude;

    @XmlAttribute(name = "Photo", required = true)
    private String photo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
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

    public XMLGregorianCalendar getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(XMLGregorianCalendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
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

    public int getWardId() {
        return wardId;
    }

    public void setWardId(int wardId) {
        this.wardId = wardId;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public int getPollingStationId() {
        return pollingStationId;
    }

    public void setPollingStationId(int pollingStationId) {
        this.pollingStationId = pollingStationId;
    }

    public String getPollingStation() {
        return pollingStation;
    }

    public void setPollingStation(String pollingStation) {
        this.pollingStation = pollingStation;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public XMLGregorianCalendar getBeginCreateDatetime() {
        return beginCreateDatetime;
    }

    public void setBeginCreateDatetime(XMLGregorianCalendar beginCreateDatetime) {
        this.beginCreateDatetime = beginCreateDatetime;
    }

    public XMLGregorianCalendar getEndCreateDatetime() {
        return endCreateDatetime;
    }

    public void setEndCreateDatetime(XMLGregorianCalendar endCreateDatetime) {
        this.endCreateDatetime = endCreateDatetime;
    }

    public XMLGregorianCalendar getBeginEditDatetime() {
        return beginEditDatetime;
    }

    public void setBeginEditDatetime(XMLGregorianCalendar beginEditDatetime) {
        this.beginEditDatetime = beginEditDatetime;
    }

    public XMLGregorianCalendar getEndEditDatetime() {
        return endEditDatetime;
    }

    public void setEndEditDatetime(XMLGregorianCalendar endEditDatetime) {
        this.endEditDatetime = endEditDatetime;
    }

    public String getClaimReference() {
        return claimReference;
    }

    public void setClaimReference(String claimReference) {
        this.claimReference = claimReference;
    }

    public short getDisability() {
        return disability;
    }

    public void setDisability(short disability) {
        this.disability = disability;
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

    @XmlAttribute(name = "ApplicationFormContent", required = false)
    private String ApplicationFormContent;

    @XmlAttribute(name = "SignatureFormContent", required = false)
    private String SignatureFormContent;
    @XmlAttribute(name = "IDDocumentFormContent", required = false)
    private String IDDocumentFormContent;
    @XmlAttribute(name = "SupportingDocumentFormContent", required = false)
    private String SupportingDocumentFormContent;
    @XmlAttribute(name = "LeftThumbImageArray", required = false)
    private String LeftThumbImageArray;
    @XmlAttribute(name = "LeftIndexImageArray", required = false)
    private String LeftIndexImageArray;
    @XmlAttribute(name = "LeftMiddleImageArray", required = false)
    private String LeftMiddleImageArray;
    @XmlAttribute(name = "LeftRingImageArray", required = false)
    private String LeftRingImageArray;
    @XmlAttribute(name = "LeftLittleImageArray", required = false)
    private String LeftLittleImageArray;
    @XmlAttribute(name = "RightThumbImageArray", required = false)
    private String RightThumbImageArray;
    @XmlAttribute(name = "RightIndexImageArray", required = false)
    private String RightIndexImageArray;
    @XmlAttribute(name = "RightMiddleImageArray", required = false)
    private String RightMiddleImageArray;
    @XmlAttribute(name = "RightRingImageArray", required = false)
    private String RightRingImageArray;
    @XmlAttribute(name = "RightLittleImageArray", required = false)
    private String RightLittleImageArray;

    @XmlAttribute(name = "LeftThumbWSQArray", required = false)
    private String LeftThumbWSQArray;

    @XmlAttribute(name = "LeftIndexWSQArray", required = false)
    private String LeftIndexWSQArray;

    @XmlAttribute(name = "LeftMiddleWSQArray", required = false)
    private String LeftMiddleWSQArray;

    @XmlAttribute(name = "LeftRingWSQArray", required = false)
    private String LeftRingWSQArray;

    @XmlAttribute(name = "LeftLittleWSQArray", required = false)
    private String LeftLittleWSQArray;

    @XmlAttribute(name = "RightThumbWSQArray", required = false)
    private String RightThumbWSQArray;

    @XmlAttribute(name = "RightIndexWSQArray", required = false)
    private String RightIndexWSQArray;

    @XmlAttribute(name = "RightMiddleWSQArray", required = false)
    private String RightMiddleWSQArray;

    @XmlAttribute(name = "RightRingWSQArray", required = false)
    private String RightRingWSQArray;

    @XmlAttribute(name = "RightLittleWSQArray", required = false)
    private String RightLittleWSQArray;

    public String getApplicationFormContent() {
        return ApplicationFormContent;
    }

    public void setApplicationFormContent(String applicationFormContent) {
        ApplicationFormContent = applicationFormContent;
    }

    public String getSignatureFormContent() {
        return SignatureFormContent;
    }

    public void setSignatureFormContent(String signatureFormContent) {
        SignatureFormContent = signatureFormContent;
    }

    public String getIDDocumentFormContent() {
        return IDDocumentFormContent;
    }

    public void setIDDocumentFormContent(String IDDocumentFormContent) {
        this.IDDocumentFormContent = IDDocumentFormContent;
    }

    public String getSupportingDocumentFormContent() {
        return SupportingDocumentFormContent;
    }

    public void setSupportingDocumentFormContent(String supportingDocumentFormContent) {
        SupportingDocumentFormContent = supportingDocumentFormContent;
    }

    public String getLeftThumbImageArray() {
        return LeftThumbImageArray;
    }

    public void setLeftThumbImageArray(String leftThumbImageArray) {
        LeftThumbImageArray = leftThumbImageArray;
    }

    public String getLeftIndexImageArray() {
        return LeftIndexImageArray;
    }

    public void setLeftIndexImageArray(String leftIndexImageArray) {
        LeftIndexImageArray = leftIndexImageArray;
    }

    public String getLeftMiddleImageArray() {
        return LeftMiddleImageArray;
    }

    public void setLeftMiddleImageArray(String leftMiddleImageArray) {
        LeftMiddleImageArray = leftMiddleImageArray;
    }

    public String getLeftRingImageArray() {
        return LeftRingImageArray;
    }

    public void setLeftRingImageArray(String leftRingImageArray) {
        LeftRingImageArray = leftRingImageArray;
    }

    public String getLeftLittleImageArray() {
        return LeftLittleImageArray;
    }

    public void setLeftLittleImageArray(String leftLittleImageArray) {
        LeftLittleImageArray = leftLittleImageArray;
    }

    public String getRightThumbImageArray() {
        return RightThumbImageArray;
    }

    public void setRightThumbImageArray(String rightThumbImageArray) {
        RightThumbImageArray = rightThumbImageArray;
    }

    public String getRightIndexImageArray() {
        return RightIndexImageArray;
    }

    public void setRightIndexImageArray(String rightIndexImageArray) {
        RightIndexImageArray = rightIndexImageArray;
    }

    public String getRightMiddleImageArray() {
        return RightMiddleImageArray;
    }

    public void setRightMiddleImageArray(String rightMiddleImageArray) {
        RightMiddleImageArray = rightMiddleImageArray;
    }

    public String getRightRingImageArray() {
        return RightRingImageArray;
    }

    public void setRightRingImageArray(String rightRingImageArray) {
        RightRingImageArray = rightRingImageArray;
    }

    public String getRightLittleImageArray() {
        return RightLittleImageArray;
    }

    public void setRightLittleImageArray(String rightLittleImageArray) {
        RightLittleImageArray = rightLittleImageArray;
    }

    public String getLeftThumbWSQArray() {
        return LeftThumbWSQArray;
    }

    public void setLeftThumbWSQArray(String leftThumbWSQArray) {
        LeftThumbWSQArray = leftThumbWSQArray;
    }

    public String getLeftIndexWSQArray() {
        return LeftIndexWSQArray;
    }

    public void setLeftIndexWSQArray(String leftIndexWSQArray) {
        LeftIndexWSQArray = leftIndexWSQArray;
    }

    public String getLeftMiddleWSQArray() {
        return LeftMiddleWSQArray;
    }

    public void setLeftMiddleWSQArray(String leftMiddleWSQArray) {
        LeftMiddleWSQArray = leftMiddleWSQArray;
    }

    public String getLeftRingWSQArray() {
        return LeftRingWSQArray;
    }

    public void setLeftRingWSQArray(String leftRingWSQArray) {
        LeftRingWSQArray = leftRingWSQArray;
    }

    public String getLeftLittleWSQArray() {
        return LeftLittleWSQArray;
    }

    public void setLeftLittleWSQArray(String leftLittleWSQArray) {
        LeftLittleWSQArray = leftLittleWSQArray;
    }

    public String getRightThumbWSQArray() {
        return RightThumbWSQArray;
    }

    public void setRightThumbWSQArray(String rightThumbWSQArray) {
        RightThumbWSQArray = rightThumbWSQArray;
    }

    public String getRightIndexWSQArray() {
        return RightIndexWSQArray;
    }

    public void setRightIndexWSQArray(String rightIndexWSQArray) {
        RightIndexWSQArray = rightIndexWSQArray;
    }

    public String getRightMiddleWSQArray() {
        return RightMiddleWSQArray;
    }

    public void setRightMiddleWSQArray(String rightMiddleWSQArray) {
        RightMiddleWSQArray = rightMiddleWSQArray;
    }

    public String getRightRingWSQArray() {
        return RightRingWSQArray;
    }

    public void setRightRingWSQArray(String rightRingWSQArray) {
        RightRingWSQArray = rightRingWSQArray;
    }

    public String getRightLittleWSQArray() {
        return RightLittleWSQArray;
    }

    public void setRightLittleWSQArray(String rightLittleWSQArray) {
        RightLittleWSQArray = rightLittleWSQArray;
    }
}
