package Zim.map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>anonymous complex type�� Java �ࡣ
 * <p>
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <p>
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="ID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Gender" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="RegistrationCentre" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="RegistrationNumber" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *       &lt;attribute name="ClaimReference" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" />
 *       &lt;attribute name="PersonName" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *       &lt;attribute name="Surname" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="MiddleName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="FamilyName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Disability" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" />
 *       &lt;attribute name="TypeOfDisability" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" />
 *       &lt;attribute name="IdentificationCardType" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" />
 *       &lt;attribute name="NationalIDCardNumber" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *       &lt;attribute name="DateOfBirth" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="ResidentialAddress" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="StreetName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Suburb" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="GIS" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="NationalIDNumber" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *       &lt;attribute name="RegistrantOfNames" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ComplaintType" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" />
 *       &lt;attribute name="ComplaintTime" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="DistrictOfApplication" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" />
 *       &lt;attribute name="Description" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "Record")
public class Record {

    @XmlAttribute(name = "ID", required = true)
    protected String id;

    @XmlAttribute(name = "Gender", required = true)
    protected int gender;

    @XmlAttribute(name = "RegistrationCentre", required = true)
    protected int registrationCentre;

    @XmlAttribute(name = "RegistrationNumber", required = true)
    @XmlSchemaType(name = "unsignedInt")
    protected int registrationNumber;

    @XmlAttribute(name = "ClaimReference", required = true)
    @XmlSchemaType(name = "unsignedByte")
    protected String claimReference;

    @XmlAttribute(name = "PersonName", required = true)
    @XmlSchemaType(name = "unsignedInt")
    protected String personName;

    @XmlAttribute(name = "Surname", required = true)
    protected String surname;

    @XmlAttribute(name = "MiddleName", required = true)
    protected String middleName;

    @XmlAttribute(name = "FamilyName", required = true)
    protected String familyName;

    @XmlAttribute(name = "Disability", required = true)
    @XmlSchemaType(name = "unsignedByte")
    protected short disability;

    @XmlAttribute(name = "TypeOfDisability", required = true)
    @XmlSchemaType(name = "unsignedByte")
    protected short typeOfDisability;

    @XmlAttribute(name = "IdentificationCardType", required = true)
    @XmlSchemaType(name = "unsignedInt")
    protected int identificationCardType;

    @XmlAttribute(name = "NationalIDCardNumber", required = true)
    protected String nationalIDCardNumber;

    @XmlAttribute(name = "DateOfBirth", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateOfBirth;

    @XmlAttribute(name = "ResidentialAddress", required = true)
    protected String residentialAddress;

    @XmlAttribute(name = "StreetName", required = true)
    protected String streetName;

    @XmlAttribute(name = "Suburb", required = true)
    protected String suburb;

    @XmlAttribute(name = "GIS", required = true)
    protected String gis;

    @XmlAttribute(name = "NationalIDNumber", required = true)
    @XmlSchemaType(name = "unsignedInt")
    protected long nationalIDNumber;

    @XmlAttribute(name = "RegistrantOfNames", required = true)
    protected String registrantOfNames;

    @XmlAttribute(name = "ComplaintType", required = true)
    @XmlSchemaType(name = "unsignedByte")
    protected short complaintType;

    @XmlAttribute(name = "ComplaintTime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar complaintTime;

    @XmlAttribute(name = "DistrictOfApplication", required = true)
    @XmlSchemaType(name = "unsignedByte")
    protected short districtOfApplication;

    @XmlAttribute(name = "Description", required = true)
    protected String description;

    @XmlAttribute(name = "Photo", required = true)
    protected String photo;

    @XmlAttribute(name = "HouseNumber", required = false)
    protected String houseNumber;//null able

    @XmlAttribute(name = "FourthName", required = false)
    protected String fourthName;//null able

    /**
     * ��ȡid���Ե�ֵ��
     *
     * @return possible object is
     * {@link String }
     */
    public String getID() {
        return id;
    }

    /**
     * ����id���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setID(String value) {
        this.id = value;
    }

    /**
     * ��ȡgender���Ե�ֵ��
     *
     * @return possible object is
     * {@link String }
     */
    public int getGender() {
        return gender;
    }

    /**
     * ����gender���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setGender(int value) {
        this.gender = value;
    }

    /**
     * ��ȡregistrationCentre���Ե�ֵ��
     *
     * @return possible object is
     * {@link String }
     */
    public int getRegistrationCentre() {
        return registrationCentre;
    }

    /**
     * ����registrationCentre���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setRegistrationCentre(int value) {
        this.registrationCentre = value;
    }

    /**
     * ��ȡregistrationNumber���Ե�ֵ��
     */
    public int getRegistrationNumber() {
        return registrationNumber;
    }

    /**
     * ����registrationNumber���Ե�ֵ��
     */
    public void setRegistrationNumber(int value) {
        this.registrationNumber = value;
    }

    /**
     * ��ȡclaimReference���Ե�ֵ��
     */
    public String getClaimReference() {
        return claimReference;
    }

    /**
     * ����claimReference���Ե�ֵ��
     */
    public void setClaimReference(String value) {
        this.claimReference = value;
    }

    /**
     * ��ȡpersonName���Ե�ֵ��
     */
    public String getPersonName() {
        return personName;
    }

    /**
     * ����personName���Ե�ֵ��
     */
    public void setPersonName(String value) {
        this.personName = value;
    }

    /**
     * ��ȡsurname���Ե�ֵ��
     *
     * @return possible object is
     * {@link String }
     */
    public String getSurname() {
        return surname;
    }

    /**
     * ����surname���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSurname(String value) {
        this.surname = value;
    }

    /**
     * ��ȡmiddleName���Ե�ֵ��
     *
     * @return possible object is
     * {@link String }
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * ����middleName���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMiddleName(String value) {
        this.middleName = value;
    }

    /**
     * ��ȡfamilyName���Ե�ֵ��
     *
     * @return possible object is
     * {@link String }
     */
    public String getFamilyName() {
        return familyName;
    }

    /**
     * ����familyName���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFamilyName(String value) {
        this.familyName = value;
    }

    /**
     * ��ȡdisability���Ե�ֵ��
     */
    public short getDisability() {
        return disability;
    }

    /**
     * ����disability���Ե�ֵ��
     */
    public void setDisability(short value) {
        this.disability = value;
    }

    /**
     * ��ȡtypeOfDisability���Ե�ֵ��
     */
    public short getTypeOfDisability() {
        return typeOfDisability;
    }

    /**
     * ����typeOfDisability���Ե�ֵ��
     */
    public void setTypeOfDisability(short value) {
        this.typeOfDisability = value;
    }

    /**
     * ��ȡidentificationCardType���Ե�ֵ��
     */
    public int getIdentificationCardType() {
        return identificationCardType;
    }

    /**
     * ����identificationCardType���Ե�ֵ��
     */
    public void setIdentificationCardType(int value) {
        this.identificationCardType = value;
    }

    /**
     * ��ȡnationalIDCardNumber���Ե�ֵ��
     */
    public String getNationalIDCardNumber() {
        return nationalIDCardNumber;
    }

    /**
     * ����nationalIDCardNumber���Ե�ֵ��
     */
    public void setNationalIDCardNumber(String value) {
        this.nationalIDCardNumber = value;
    }

    /**
     * ��ȡdateOfBirth���Ե�ֵ��
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * ����dateOfBirth���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setDateOfBirth(XMLGregorianCalendar value) {
        this.dateOfBirth = value;
    }

    /**
     * ��ȡresidentialAddress���Ե�ֵ��
     *
     * @return possible object is
     * {@link String }
     */
    public String getResidentialAddress() {
        return residentialAddress;
    }

    /**
     * ����residentialAddress���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setResidentialAddress(String value) {
        this.residentialAddress = value;
    }

    /**
     * ��ȡstreetName���Ե�ֵ��
     *
     * @return possible object is
     * {@link String }
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * ����streetName���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setStreetName(String value) {
        this.streetName = value;
    }

    /**
     * ��ȡsuburb���Ե�ֵ��
     *
     * @return possible object is
     * {@link String }
     */
    public String getSuburb() {
        return suburb;
    }

    /**
     * ����suburb���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSuburb(String value) {
        this.suburb = value;
    }

    /**
     * ��ȡgis���Ե�ֵ��
     *
     * @return possible object is
     * {@link String }
     */
    public String getGIS() {
        return gis;
    }

    /**
     * ����gis���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setGIS(String value) {
        this.gis = value;
    }

    /**
     * ��ȡnationalIDNumber���Ե�ֵ��
     */
    public long getNationalIDNumber() {
        return nationalIDNumber;
    }

    /**
     * ����nationalIDNumber���Ե�ֵ��
     */
    public void setNationalIDNumber(long value) {
        this.nationalIDNumber = value;
    }

    /**
     * ��ȡregistrantOfNames���Ե�ֵ��
     *
     * @return possible object is
     * {@link String }
     */
    public String getRegistrantOfNames() {
        return registrantOfNames;
    }

    /**
     * ����registrantOfNames���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setRegistrantOfNames(String value) {
        this.registrantOfNames = value;
    }

    /**
     * ��ȡcomplaintType���Ե�ֵ��
     */
    public short getComplaintType() {
        return complaintType;
    }

    /**
     * ����complaintType���Ե�ֵ��
     */
    public void setComplaintType(short value) {
        this.complaintType = value;
    }

    /**
     * ��ȡcomplaintTime���Ե�ֵ��
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getComplaintTime() {
        return complaintTime;
    }

    /**
     * ����complaintTime���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setComplaintTime(XMLGregorianCalendar value) {
        this.complaintTime = value;
    }

    /**
     * ��ȡdistrictOfApplication���Ե�ֵ��
     */
    public short getDistrictOfApplication() {
        return districtOfApplication;
    }

    /**
     * ����districtOfApplication���Ե�ֵ��
     */
    public void setDistrictOfApplication(short value) {
        this.districtOfApplication = value;
    }

    /**
     * ��ȡdescription���Ե�ֵ��
     *
     * @return possible object is
     * {@link String }
     */
    public String getDescription() {
        return description;
    }

    /**
     * ����description���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDescription(String value) {
        this.description = value;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getFourthName() {
        return fourthName;
    }

    public void setFourthName(String fourthName) {
        this.fourthName = fourthName;
    }
}
