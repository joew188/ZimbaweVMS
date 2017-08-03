package Zim.model;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;


/**
 * <p>anonymous complex type的 Java 类。
 * <p>
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Guid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RegistrationNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SortNumber" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *         &lt;element name="BeginCreateDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="EndCreateDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="BeginEditDatetime" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *         &lt;element name="EndEditDatetime" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *         &lt;element name="OperatorGuid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="OperatorName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ProvinceId" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *         &lt;element name="ProvinceName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DistrictId" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *         &lt;element name="DistrictName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ConstituencyId" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *         &lt;element name="ConstituencyName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WardId" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *         &lt;element name="WardName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PollingStationId" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *         &lt;element name="PollingStationName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SaveDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="EditSaveDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="DeviceName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UnderDuress" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ExportDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="ImportToServerDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="ApplicantCompliance">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="BeginCreateDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                   &lt;element name="EndCreateDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                   &lt;element name="BeginEditDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                   &lt;element name="EndEditDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                   &lt;element name="ApplicationFormContent" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="IDDocumentFormContent" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ProofOfResidenceContent" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ApplicantDemographic">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="BeginCreateDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                   &lt;element name="EndCreateDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                   &lt;element name="BeginEditDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                   &lt;element name="EndEditDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                   &lt;element name="IdNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Surname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Forenames" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="DateOfBirth" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                   &lt;element name="DateOfBirthText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Gender" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                   &lt;element name="ProvinceId" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *                   &lt;element name="ProvinceName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="DistrictId" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *                   &lt;element name="DistrictName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ConstituencyId" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *                   &lt;element name="ConstituencyName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="WardId" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *                   &lt;element name="WardName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="StationId" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *                   &lt;element name="StationName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="StationCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Surburb" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="StreetName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="StandNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Disability" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *                   &lt;element name="OtherDisability" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="RegistrationType" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *                   &lt;element name="PhoneNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="GISLatitude" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *                   &lt;element name="GISLongitude" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *                   &lt;element name="ChangeAddressProvinceId" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *                   &lt;element name="ChangeAddressProvinceName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ChangeAddressDistrictId" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *                   &lt;element name="ChangeAddressDistrictName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ChangeAddressConstituencyId" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *                   &lt;element name="ChangeAddressConstituencyName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ChangeAddressWardId" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *                   &lt;element name="ChangeAddressWardName" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *                   &lt;element name="ChangeAddressStationId" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *                   &lt;element name="ChangeAddressStationName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ChangeAddressStationCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ChangeAddressSurburb" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ChangeAddressStreetName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ChangeAddressStandNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="TransferConstituencyProvinceId" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *                   &lt;element name="TransferConstituencyProvinceName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="TransferConstituencyDistrictId" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *                   &lt;element name="TransferConstituencyDistrictName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="TransferConstituencyConstituencyId" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *                   &lt;element name="TransferConstituencyConstituencyName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="TransferConstituencyWardId" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *                   &lt;element name="TransferConstituencyWardName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="TransferConstituencyStationId" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *                   &lt;element name="TransferConstituencyStationName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="TransferConstituencyStationCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="TransferConstituencySurburb" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="TransferConstituencyStreetName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="TransferConstituencyStandNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ApplicantFingerprint">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="BeginCreateDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                   &lt;element name="EndCreateDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                   &lt;element name="BeginEditDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                   &lt;element name="EndEditDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                   &lt;element name="LeftThumbImageArray" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="LeftIndexImageArray" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="LeftMiddleImageArray" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="LeftRingImageArray" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="LeftLittleImageArray" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="RightThumbImageArray" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="RightIndexImageArray" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="RightMiddleImageArray" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="RightRingImageArray" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="RightLittleImageArray" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="LeftThumbWSQArray" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="LeftIndexWSQArray" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="LeftMiddleWSQArray" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="LeftRingWSQArray" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="LeftLittleWSQArray" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="RightThumbWSQArray" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="RightIndexWSQArray" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="RightMiddleWSQArray" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="RightRingWSQArray" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="RightLittleWSQArray" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="LeftThumbScore" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
 *                   &lt;element name="LeftIndexScore" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
 *                   &lt;element name="LeftMiddleScore" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
 *                   &lt;element name="LeftRingScore" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
 *                   &lt;element name="LeftLittleScore" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
 *                   &lt;element name="RightThumbScore" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
 *                   &lt;element name="RightIndexScore" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
 *                   &lt;element name="RightMiddleScore" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
 *                   &lt;element name="RightRingScore" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
 *                   &lt;element name="RightLittleScore" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
 *                   &lt;element name="LeftThumbState" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *                   &lt;element name="LeftIndexState" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *                   &lt;element name="LeftMiddleState" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *                   &lt;element name="LeftRingState" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *                   &lt;element name="LeftLittleState" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *                   &lt;element name="RightThumbState" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *                   &lt;element name="RightIndexState" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *                   &lt;element name="RightMiddleState" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *                   &lt;element name="RightRingState" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *                   &lt;element name="RightLittleState" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *                   &lt;element name="SourceAFISID" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *                   &lt;element name="LeftThumbAFISTemplateT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="LeftIndexAFISTemplateT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="LeftMiddleAFISTemplateT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="LeftRingAFISTemplateT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="LeftLittleAFISTemplateT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="RightThumbAFISTemplateT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="RightIndexAFISTemplateT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="RightMiddleAFISTemplateT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="RightRingAFISTemplateT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="RightLittleAFISTemplateT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="LeftLittleMinutiaesCount" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *                   &lt;element name="LeftRingMinutiaesCount" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *                   &lt;element name="LeftMiddleMinutiaesCount" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *                   &lt;element name="LeftIndexMinutiaesCount" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *                   &lt;element name="LeftThumbMinutiaesCount" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *                   &lt;element name="RightLittleMinutiaesCount" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *                   &lt;element name="RightRingMinutiaesCount" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *                   &lt;element name="RightMiddleMinutiaesCount" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *                   &lt;element name="RightIndexMinutiaesCount" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *                   &lt;element name="RightThumbMinutiaesCount" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *                   &lt;element name="MissingReasonType" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ApplicantPhoto">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="BeginCreateDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                   &lt;element name="EndCreateDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                   &lt;element name="BeginEditDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                   &lt;element name="EndEditDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                   &lt;element name="PhotoArray" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Thumbnail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "_id",
        "surname",
        "gender",
        "dateOfBirth",
        "dateOfRegistration",
        "status",
        "guid",
        "registrationNumber",
        "sortNumber",
        "beginCreateDatetime",
        "endCreateDatetime",
        "beginEditDatetime",
        "endEditDatetime",
        "operatorGuid",
        "operatorName",
        "provinceId",
        "provinceName",
        "districtId",
        "districtName",
        "constituencyId",
        "constituencyName",
        "wardId",
        "wardName",
        "pollingStationId",
        "pollingStationCode",
        "pollingStationName",
        "saveDatetime",
        "editSaveDatetime",
        "deviceName",
        "underDuress",
        "exportDatetime",
        "importToServerDatetime",
        "applicantCompliance",
        "applicantDemographic",
        "applicantFingerprint",
        "applicantPhoto"
})
@Document(collection = "Applicant")
@XmlRootElement(name = "Applicant")
public class Applicant {
    public DBObject toDBObject() {
        DBObject dbObject = new BasicDBObject();

        dbObject.put("_id", this.get_id());
        dbObject.put("surname", this.getSurname());
        dbObject.put("gender", this.getGender());
        dbObject.put("dateOfBirth", this.getDateOfBirth());
        dbObject.put("dateOfRegistration", this.getDateOfRegistration());

        dbObject.put("status", this.getStatus());
        dbObject.put("guid", this.getGuid());
        dbObject.put("registrationNumber", this.getRegistrationNumber());
        dbObject.put("sortNumber", this.getSortNumber());
        dbObject.put("beginCreateDatetime", this.getBeginCreateDatetime());
        dbObject.put("endCreateDatetime", this.getEndCreateDatetime());
        dbObject.put("beginEditDatetime", this.getBeginEditDatetime());
        dbObject.put("endEditDatetime", this.getEndEditDatetime());
        dbObject.put("operatorGuid", this.getOperatorGuid());
        dbObject.put("operatorName", this.getOperatorName());
        dbObject.put("provinceId", this.getProvinceId());
        dbObject.put("provinceName", this.getProvinceName());
        dbObject.put("districtId", this.getDistrictId());
        dbObject.put("districtName", this.getDistrictName());
        dbObject.put("constituencyId", this.getConstituencyId());
        dbObject.put("constituencyName", this.getConstituencyName());
        dbObject.put("wardId", this.getWardId());
        dbObject.put("wardName", this.getWardName());
        dbObject.put("pollingStationId", this.getPollingStationId());
        dbObject.put("pollingStationCode", this.getPollingStationCode());
        dbObject.put("pollingStationName", this.getPollingStationName());
        dbObject.put("saveDatetime", this.getSaveDatetime());
        dbObject.put("editSaveDatetime", this.getEditSaveDatetime());
        dbObject.put("deviceName", this.getDeviceName());
        dbObject.put("underDuress", this.isUnderDuress());
        dbObject.put("exportDatetime", this.getExportDatetime());
        dbObject.put("importToServerDatetime", this.getImportToServerDatetime());

        DBObject applicantCompliance = new BasicDBObject();

        applicantCompliance.put("beginCreateDatetime", this.getApplicantCompliance().getBeginCreateDatetime());
        applicantCompliance.put("endCreateDatetime", this.getApplicantCompliance().getEndCreateDatetime());
        applicantCompliance.put("beginEditDatetime", this.getApplicantCompliance().getBeginEditDatetime());
        applicantCompliance.put("endEditDatetime", this.getApplicantCompliance().getEndEditDatetime());
        applicantCompliance.put("applicationFormContent", this.getApplicantCompliance().getApplicationFormContent());
        applicantCompliance.put("idDocumentFormContent", this.getApplicantCompliance().getIDDocumentFormContent());
        applicantCompliance.put("proofOfResidenceContent", this.getApplicantCompliance().getProofOfResidenceContent());

        dbObject.put("applicantCompliance", applicantCompliance);

        DBObject applicantDemographic = new BasicDBObject();
        applicantDemographic.put("beginCreateDatetime", this.getApplicantDemographic().getBeginCreateDatetime());
        applicantDemographic.put("endCreateDatetime", this.getApplicantDemographic().getEndCreateDatetime());
        applicantDemographic.put("beginEditDatetime", this.getApplicantDemographic().getBeginEditDatetime());
        applicantDemographic.put("endEditDatetime", this.getApplicantDemographic().getEndEditDatetime());
        applicantDemographic.put("idNumber", this.getApplicantDemographic().getIdNumber());
        applicantDemographic.put("surname", this.getApplicantDemographic().getSurname());
        applicantDemographic.put("forenames", this.getApplicantDemographic().getForenames());
        applicantDemographic.put("dateOfBirth", this.getApplicantDemographic().getDateOfBirth());
        applicantDemographic.put("dateOfBirthText", this.getApplicantDemographic().getDateOfBirthText());
        applicantDemographic.put("gender", this.getApplicantDemographic().getGender());
        applicantDemographic.put("provinceId", this.getApplicantDemographic().getProvinceId());
        applicantDemographic.put("provinceName", this.getApplicantDemographic().getProvinceName());
        applicantDemographic.put("districtId", this.getApplicantDemographic().getDistrictId());
        applicantDemographic.put("districtName", this.getApplicantDemographic().getDistrictName());
        applicantDemographic.put("constituencyId", this.getApplicantDemographic().getConstituencyId());
        applicantDemographic.put("constituencyName", this.getApplicantDemographic().getConstituencyName());
        applicantDemographic.put("wardId", this.getApplicantDemographic().getWardId());
        applicantDemographic.put("wardName", this.getApplicantDemographic().getWardName());
        applicantDemographic.put("stationId", this.getApplicantDemographic().getStationId());
        applicantDemographic.put("stationName", this.getApplicantDemographic().getStationName());
        applicantDemographic.put("stationCode", this.getApplicantDemographic().getStationCode());
        applicantDemographic.put("surburb", this.getApplicantDemographic().getSurburb());
        applicantDemographic.put("streetName", this.getApplicantDemographic().getStreetName());
        applicantDemographic.put("standNumber", this.getApplicantDemographic().getStandNumber());
        applicantDemographic.put("disability", this.getApplicantDemographic().getDisability());
        applicantDemographic.put("otherDisability", this.getApplicantDemographic().getOtherDisability());
        applicantDemographic.put("registrationType", this.getApplicantDemographic().getRegistrationType());
        applicantDemographic.put("phoneNumber", this.getApplicantDemographic().getPhoneNumber());
        applicantDemographic.put("email", this.getApplicantDemographic().getEmail());
        applicantDemographic.put("gisLatitude", this.getApplicantDemographic().getGISLatitude());
        applicantDemographic.put("gisLongitude", this.getApplicantDemographic().getGISLongitude());
        applicantDemographic.put("changeAddressProvinceId", this.getApplicantDemographic().getChangeAddressProvinceId());
        applicantDemographic.put("changeAddressProvinceName", this.getApplicantDemographic().getChangeAddressProvinceName());
        applicantDemographic.put("changeAddressDistrictId", this.getApplicantDemographic().getChangeAddressDistrictId());
        applicantDemographic.put("changeAddressDistrictName", this.getApplicantDemographic().getChangeAddressDistrictName());
        applicantDemographic.put("changeAddressConstituencyId", this.getApplicantDemographic().getChangeAddressConstituencyId());
        applicantDemographic.put("changeAddressConstituencyName", this.getApplicantDemographic().getChangeAddressConstituencyName());
        applicantDemographic.put("changeAddressWardId", this.getApplicantDemographic().getChangeAddressWardId());
        applicantDemographic.put("changeAddressWardName", this.getApplicantDemographic().getChangeAddressWardName());
        applicantDemographic.put("changeAddressStationId", this.getApplicantDemographic().getChangeAddressStationId());
        applicantDemographic.put("changeAddressStationName", this.getApplicantDemographic().getChangeAddressStationName());
        applicantDemographic.put("changeAddressStationCode", this.getApplicantDemographic().getChangeAddressStationCode());
        applicantDemographic.put("changeAddressSurburb", this.getApplicantDemographic().getChangeAddressSurburb());
        applicantDemographic.put("changeAddressStreetName", this.getApplicantDemographic().getChangeAddressStreetName());
        applicantDemographic.put("changeAddressStandNumber", this.getApplicantDemographic().getChangeAddressStandNumber());
        applicantDemographic.put("transferConstituencyProvinceId", this.getApplicantDemographic().getTransferConstituencyProvinceId());
        applicantDemographic.put("transferConstituencyProvinceName", this.getApplicantDemographic().getTransferConstituencyProvinceName());
        applicantDemographic.put("transferConstituencyDistrictId", this.getApplicantDemographic().getTransferConstituencyDistrictId());
        applicantDemographic.put("transferConstituencyDistrictName", this.getApplicantDemographic().getTransferConstituencyDistrictName());
        applicantDemographic.put("transferConstituencyConstituencyId", this.getApplicantDemographic().getTransferConstituencyConstituencyId());
        applicantDemographic.put("transferConstituencyConstituencyName", this.getApplicantDemographic().getTransferConstituencyConstituencyName());
        applicantDemographic.put("transferConstituencyWardId", this.getApplicantDemographic().getTransferConstituencyWardId());
        applicantDemographic.put("transferConstituencyWardName", this.getApplicantDemographic().getTransferConstituencyWardName());
        applicantDemographic.put("transferConstituencyStationId", this.getApplicantDemographic().getTransferConstituencyStationId());
        applicantDemographic.put("transferConstituencyStationName", this.getApplicantDemographic().getTransferConstituencyStationName());
        applicantDemographic.put("transferConstituencyStationCode", this.getApplicantDemographic().getTransferConstituencyStationCode());
        applicantDemographic.put("transferConstituencySurburb", this.getApplicantDemographic().getTransferConstituencySurburb());
        applicantDemographic.put("transferConstituencyStreetName", this.getApplicantDemographic().getTransferConstituencyStreetName());
        applicantDemographic.put("transferConstituencyStandNumber", this.getApplicantDemographic().getTransferConstituencyStandNumber());
        dbObject.put("applicantDemographic", applicantDemographic);

        DBObject applicantFingerprint = new BasicDBObject();
        applicantFingerprint.put("beginCreateDatetime", this.getApplicantFingerprint().getBeginCreateDatetime());

        applicantFingerprint.put("endCreateDatetime", this.getApplicantFingerprint().getEndCreateDatetime());
        applicantFingerprint.put("beginEditDatetime", this.getApplicantFingerprint().getBeginEditDatetime());
        applicantFingerprint.put("endEditDatetime", this.getApplicantFingerprint().getEndEditDatetime());
        applicantFingerprint.put("leftThumbImageArray", this.getApplicantFingerprint().getLeftThumbImageArray());
        applicantFingerprint.put("leftIndexImageArray", this.getApplicantFingerprint().getLeftIndexImageArray());
        applicantFingerprint.put("leftMiddleImageArray", this.getApplicantFingerprint().getLeftMiddleImageArray());
        applicantFingerprint.put("leftRingImageArray", this.getApplicantFingerprint().getLeftRingImageArray());
        applicantFingerprint.put("leftLittleImageArray", this.getApplicantFingerprint().getLeftLittleImageArray());
        applicantFingerprint.put("rightThumbImageArray", this.getApplicantFingerprint().getRightThumbImageArray());
        applicantFingerprint.put("rightIndexImageArray", this.getApplicantFingerprint().getRightIndexImageArray());
        applicantFingerprint.put("rightMiddleImageArray", this.getApplicantFingerprint().getRightMiddleImageArray());
        applicantFingerprint.put("rightRingImageArray", this.getApplicantFingerprint().getRightRingImageArray());
        applicantFingerprint.put("rightLittleImageArray", this.getApplicantFingerprint().getRightLittleImageArray());
        applicantFingerprint.put("leftThumbWSQArray", this.getApplicantFingerprint().getLeftThumbWSQArray());
        applicantFingerprint.put("leftIndexWSQArray", this.getApplicantFingerprint().getLeftIndexWSQArray());
        applicantFingerprint.put("leftMiddleWSQArray", this.getApplicantFingerprint().getLeftMiddleWSQArray());
        applicantFingerprint.put("leftRingWSQArray", this.getApplicantFingerprint().getLeftRingWSQArray());
        applicantFingerprint.put("leftLittleWSQArray", this.getApplicantFingerprint().getLeftLittleWSQArray());
        applicantFingerprint.put("rightThumbWSQArray", this.getApplicantFingerprint().getRightThumbWSQArray());
        applicantFingerprint.put("rightIndexWSQArray", this.getApplicantFingerprint().getRightIndexWSQArray());
        applicantFingerprint.put("rightMiddleWSQArray", this.getApplicantFingerprint().getRightMiddleWSQArray());
        applicantFingerprint.put("rightRingWSQArray", this.getApplicantFingerprint().getRightRingWSQArray());
        applicantFingerprint.put("rightLittleWSQArray", this.getApplicantFingerprint().getRightLittleWSQArray());
        applicantFingerprint.put("leftThumbScore", this.getApplicantFingerprint().getLeftThumbScore());
        applicantFingerprint.put("leftIndexScore", this.getApplicantFingerprint().getLeftIndexScore());
        applicantFingerprint.put("leftMiddleScore", this.getApplicantFingerprint().getLeftMiddleScore());
        applicantFingerprint.put("leftRingScore", this.getApplicantFingerprint().getLeftRingScore());
        applicantFingerprint.put("leftLittleScore", this.getApplicantFingerprint().getLeftLittleScore());
        applicantFingerprint.put("rightThumbScore", this.getApplicantFingerprint().getRightThumbScore());
        applicantFingerprint.put("rightIndexScore", this.getApplicantFingerprint().getRightIndexScore());
        applicantFingerprint.put("rightMiddleScore", this.getApplicantFingerprint().getRightMiddleScore());
        applicantFingerprint.put("rightRingScore", this.getApplicantFingerprint().getRightRingScore());
        applicantFingerprint.put("rightLittleScore", this.getApplicantFingerprint().getRightLittleScore());
        applicantFingerprint.put("leftThumbState", this.getApplicantFingerprint().getLeftThumbState());
        applicantFingerprint.put("leftIndexState", this.getApplicantFingerprint().getLeftIndexState());
        applicantFingerprint.put("leftMiddleState", this.getApplicantFingerprint().getLeftMiddleState());
        applicantFingerprint.put("leftRingState", this.getApplicantFingerprint().getLeftRingState());
        applicantFingerprint.put("leftLittleState", this.getApplicantFingerprint().getLeftLittleState());
        applicantFingerprint.put("rightThumbState", this.getApplicantFingerprint().getRightThumbState());
        applicantFingerprint.put("rightIndexState", this.getApplicantFingerprint().getRightIndexState());
        applicantFingerprint.put("rightMiddleState", this.getApplicantFingerprint().getRightMiddleState());
        applicantFingerprint.put("rightRingState", this.getApplicantFingerprint().getRightRingState());
        applicantFingerprint.put("rightLittleState", this.getApplicantFingerprint().getRightLittleState());
        applicantFingerprint.put("sourceAFISID", this.getApplicantFingerprint().getSourceAFISID());
        applicantFingerprint.put("leftThumbAFISTemplateT", this.getApplicantFingerprint().getLeftThumbAFISTemplateT());
        applicantFingerprint.put("leftIndexAFISTemplateT", this.getApplicantFingerprint().getLeftIndexAFISTemplateT());
        applicantFingerprint.put("leftMiddleAFISTemplateT", this.getApplicantFingerprint().getLeftMiddleAFISTemplateT());
        applicantFingerprint.put("leftRingAFISTemplateT", this.getApplicantFingerprint().getLeftRingAFISTemplateT());
        applicantFingerprint.put("leftLittleAFISTemplateT", this.getApplicantFingerprint().getLeftLittleAFISTemplateT());
        applicantFingerprint.put("rightThumbAFISTemplateT", this.getApplicantFingerprint().getRightThumbAFISTemplateT());
        applicantFingerprint.put("rightIndexAFISTemplateT", this.getApplicantFingerprint().getRightIndexAFISTemplateT());
        applicantFingerprint.put("rightMiddleAFISTemplateT", this.getApplicantFingerprint().getRightMiddleAFISTemplateT());
        applicantFingerprint.put("rightRingAFISTemplateT", this.getApplicantFingerprint().getRightRingAFISTemplateT());
        applicantFingerprint.put("rightLittleAFISTemplateT", this.getApplicantFingerprint().getRightLittleAFISTemplateT());
        applicantFingerprint.put("leftLittleMinutiaesCount", this.getApplicantFingerprint().getLeftLittleMinutiaesCount());
        applicantFingerprint.put("leftRingMinutiaesCount", this.getApplicantFingerprint().getLeftRingMinutiaesCount());
        applicantFingerprint.put("leftMiddleMinutiaesCount", this.getApplicantFingerprint().getLeftMiddleMinutiaesCount());
        applicantFingerprint.put("leftIndexMinutiaesCount", this.getApplicantFingerprint().getLeftIndexMinutiaesCount());
        applicantFingerprint.put("leftThumbMinutiaesCount", this.getApplicantFingerprint().getLeftThumbMinutiaesCount());
        applicantFingerprint.put("rightLittleMinutiaesCount", this.getApplicantFingerprint().getRightLittleMinutiaesCount());
        applicantFingerprint.put("rightRingMinutiaesCount", this.getApplicantFingerprint().getRightRingMinutiaesCount());
        applicantFingerprint.put("rightMiddleMinutiaesCount", this.getApplicantFingerprint().getRightMiddleMinutiaesCount());
        applicantFingerprint.put("rightIndexMinutiaesCount", this.getApplicantFingerprint().getRightIndexMinutiaesCount());
        applicantFingerprint.put("rightThumbMinutiaesCount", this.getApplicantFingerprint().getRightThumbMinutiaesCount());
        applicantFingerprint.put("missingReasonType", this.getApplicantFingerprint().getMissingReasonType());

        dbObject.put("applicantFingerprint", applicantFingerprint);

        DBObject applicantPhoto = new BasicDBObject();
        applicantPhoto.put("beginCreateDatetime", this.getApplicantPhoto().getBeginCreateDatetime());
        applicantPhoto.put("endCreateDatetime", this.getApplicantPhoto().getEndCreateDatetime());
        applicantPhoto.put("beginEditDatetime", this.getApplicantPhoto().getBeginEditDatetime());
        applicantPhoto.put("endEditDatetime", this.getApplicantPhoto().getEndEditDatetime());
        applicantPhoto.put("photoArray", this.getApplicantPhoto().getPhotoArray());
        applicantPhoto.put("thumbnail", this.getApplicantPhoto().getThumbnail());

        dbObject.put("applicantPhoto", applicantPhoto);
        return dbObject;
    }

    @XmlElement(name = "Id", required = false)
    private String _id;
    @XmlElement(name = "Surname", required = false)
    private String surname;

    @XmlElement(name = "Gender", required = false)
    @XmlSchemaType(name = "int")
    private int gender;

    @XmlElement(name = "DateOfBirth", required = false)
    @XmlSchemaType(name = "int")
    private int dateOfBirth;

    @XmlElement(name = "DateOfRegistration", required = false)
    @XmlSchemaType(name = "int")
    private int dateOfRegistration;
    @XmlElement(name = "Status", required = false)
    @XmlSchemaType(name = "unsignedShort")
    private short status;
    @XmlElement(name = "Guid", required = true)
    protected String guid;
    @XmlElement(name = "RegistrationNumber", required = true)
    protected String registrationNumber;
    @XmlElement(name = "SortNumber")
    @XmlSchemaType(name = "unsignedInt")
    protected long sortNumber;
    @XmlElement(name = "BeginCreateDatetime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected Date beginCreateDatetime;
    @XmlElement(name = "EndCreateDatetime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected Date endCreateDatetime;
    @XmlElement(name = "BeginEditDatetime", required = true, nillable = true)
    protected Date beginEditDatetime;
    @XmlElement(name = "EndEditDatetime", required = true, nillable = true)
    protected Date endEditDatetime;

    @XmlElement(name = "OperatorGuid", required = true)
    protected String operatorGuid;
    @XmlElement(name = "OperatorName", required = true)
    protected String operatorName;
    @XmlElement(name = "ProvinceId")
    @XmlSchemaType(name = "unsignedInt")
    protected long provinceId;
    @XmlElement(name = "ProvinceName", required = true)
    protected String provinceName;
    @XmlElement(name = "DistrictId")
    @XmlSchemaType(name = "unsignedInt")
    protected long districtId;
    @XmlElement(name = "DistrictName", required = true)
    protected String districtName;
    @XmlElement(name = "ConstituencyId")
    @XmlSchemaType(name = "unsignedInt")
    protected long constituencyId;
    @XmlElement(name = "ConstituencyName", required = true)
    protected String constituencyName;
    @XmlElement(name = "WardId")
    @XmlSchemaType(name = "unsignedInt")
    protected long wardId;
    @XmlElement(name = "WardName", required = true)
    protected String wardName;
    @XmlElement(name = "PollingStationId")
    @XmlSchemaType(name = "unsignedInt")
    protected long pollingStationId;
    @XmlElement(name = "PollingStationCode", required = true, nillable = true)
    protected String pollingStationCode;
    @XmlElement(name = "PollingStationName", required = true, nillable = true)
    protected String pollingStationName;

    @XmlElement(name = "SaveDatetime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected Date saveDatetime;

    @XmlElement(name = "EditSaveDatetime", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected Date editSaveDatetime;

    @XmlElement(name = "DeviceName", required = true)
    protected String deviceName;
    @XmlElement(name = "UnderDuress")
    protected boolean underDuress;

    @XmlElement(name = "ExportDatetime", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected Date exportDatetime;
    @XmlElement(name = "ImportToServerDatetime", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected Date importToServerDatetime;

    @XmlElement(name = "ApplicantCompliance", required = true)
    protected Applicant.ApplicantCompliance applicantCompliance;
    @XmlElement(name = "ApplicantDemographic", required = true)
    protected Applicant.ApplicantDemographic applicantDemographic;
    @XmlElement(name = "ApplicantFingerprint", required = true)
    protected Applicant.ApplicantFingerprint applicantFingerprint;
    @XmlElement(name = "ApplicantPhoto", required = true)
    protected Applicant.ApplicantPhoto applicantPhoto;

    /**
     * 获取guid属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getGuid() {
        return guid;
    }

    /**
     * 设置guid属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setGuid(String value) {
        this.guid = value;
    }

    /**
     * 获取registrationNumber属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    /**
     * 设置registrationNumber属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setRegistrationNumber(String value) {
        this.registrationNumber = value;
    }

    /**
     * 获取sortNumber属性的值。
     */
    public long getSortNumber() {
        return sortNumber;
    }

    /**
     * 设置sortNumber属性的值。
     */
    public void setSortNumber(long value) {
        this.sortNumber = value;
    }

    /**
     * 获取beginCreateDatetime属性的值。
     *
     * @return possible object is
     * {@link Date }
     */
    public Date getBeginCreateDatetime() {
        return beginCreateDatetime;
    }

    /**
     * 设置beginCreateDatetime属性的值。
     *
     * @param value allowed object is
     *              {@link Date }
     */
    public void setBeginCreateDatetime(Date value) {
        this.beginCreateDatetime = value;
    }

    /**
     * 获取endCreateDatetime属性的值。
     *
     * @return possible object is
     * {@link Date }
     */
    public Date getEndCreateDatetime() {
        return endCreateDatetime;
    }

    /**
     * 设置endCreateDatetime属性的值。
     *
     * @param value allowed object is
     *              {@link Date }
     */
    public void setEndCreateDatetime(Date value) {
        this.endCreateDatetime = value;
    }

    /**
     * 获取beginEditDatetime属性的值。
     *
     * @return possible object is
     * {@link Object }
     */
    public Date getBeginEditDatetime() {
        return beginEditDatetime;
    }

    /**
     * 设置beginEditDatetime属性的值。
     *
     * @param value allowed object is
     *              {@link Date }
     */
    public void setBeginEditDatetime(Date value) {
        this.beginEditDatetime = value;
    }

    /**
     * 获取endEditDatetime属性的值。
     *
     * @return possible object is
     * {@link Object }
     */
    public Date getEndEditDatetime() {
        return endEditDatetime;
    }

    /**
     * 设置endEditDatetime属性的值。
     *
     * @param value allowed object is
     *              {@link Object }
     */
    public void setEndEditDatetime(Date value) {
        this.endEditDatetime = value;
    }

    /**
     * 获取operatorGuid属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getOperatorGuid() {
        return operatorGuid;
    }

    /**
     * 设置operatorGuid属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setOperatorGuid(String value) {
        this.operatorGuid = value;
    }

    /**
     * 获取operatorName属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getOperatorName() {
        return operatorName;
    }

    /**
     * 设置operatorName属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setOperatorName(String value) {
        this.operatorName = value;
    }

    /**
     * 获取provinceId属性的值。
     */
    public long getProvinceId() {
        return provinceId;
    }

    /**
     * 设置provinceId属性的值。
     */
    public void setProvinceId(long value) {
        this.provinceId = value;
    }

    /**
     * 获取provinceName属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * 设置provinceName属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setProvinceName(String value) {
        this.provinceName = value;
    }

    /**
     * 获取districtId属性的值。
     */
    public long getDistrictId() {
        return districtId;
    }

    /**
     * 设置districtId属性的值。
     */
    public void setDistrictId(long value) {
        this.districtId = value;
    }

    /**
     * 获取districtName属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getDistrictName() {
        return districtName;
    }

    /**
     * 设置districtName属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDistrictName(String value) {
        this.districtName = value;
    }

    /**
     * 获取constituencyId属性的值。
     */
    public long getConstituencyId() {
        return constituencyId;
    }

    /**
     * 设置constituencyId属性的值。
     */
    public void setConstituencyId(long value) {
        this.constituencyId = value;
    }

    /**
     * 获取constituencyName属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getConstituencyName() {
        return constituencyName;
    }

    /**
     * 设置constituencyName属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setConstituencyName(String value) {
        this.constituencyName = value;
    }

    /**
     * 获取wardId属性的值。
     */
    public long getWardId() {
        return wardId;
    }

    /**
     * 设置wardId属性的值。
     */
    public void setWardId(long value) {
        this.wardId = value;
    }

    /**
     * 获取wardName属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getWardName() {
        return wardName;
    }

    /**
     * 设置wardName属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setWardName(String value) {
        this.wardName = value;
    }

    /**
     * 获取pollingStationId属性的值。
     */
    public long getPollingStationId() {
        return pollingStationId;
    }

    /**
     * 设置pollingStationId属性的值。
     */
    public void setPollingStationId(long value) {
        this.pollingStationId = value;
    }

    /**
     * 获取pollingStationName属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getPollingStationName() {
        return pollingStationName;
    }

    /**
     * 设置pollingStationName属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPollingStationName(String value) {
        this.pollingStationName = value;
    }

    /**
     * 获取pollingStationCode属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getPollingStationCode() {
        return pollingStationCode;
    }

    /**
     * 设置pollingStationCode属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPollingStationCode(String value) {
        this.pollingStationCode = value;
    }

    /**
     * 获取saveDatetime属性的值。
     *
     * @return possible object is
     * {@link Date }
     */
    public Date getSaveDatetime() {
        return saveDatetime;
    }

    /**
     * 设置saveDatetime属性的值。
     *
     * @param value allowed object is
     *              {@link Date }
     */
    public void setSaveDatetime(Date value) {
        this.saveDatetime = value;
    }

    /**
     * 获取editSaveDatetime属性的值。
     *
     * @return possible object is
     * {@link Date }
     */
    public Date getEditSaveDatetime() {
        return editSaveDatetime;
    }

    /**
     * 设置editSaveDatetime属性的值。
     *
     * @param value allowed object is
     *              {@link Date }
     */
    public void setEditSaveDatetime(Date value) {
        this.editSaveDatetime = value;
    }

    /**
     * 获取deviceName属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * 设置deviceName属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDeviceName(String value) {
        this.deviceName = value;
    }

    /**
     * 获取underDuress属性的值。
     */
    public boolean isUnderDuress() {
        return underDuress;
    }

    /**
     * 设置underDuress属性的值。
     */
    public void setUnderDuress(boolean value) {
        this.underDuress = value;
    }

    /**
     * 获取exportDatetime属性的值。
     *
     * @return possible object is
     * {@link Date }
     */
    public Date getExportDatetime() {
        return exportDatetime;
    }

    /**
     * 设置exportDatetime属性的值。
     *
     * @param value allowed object is
     *              {@link Date }
     */
    public void setExportDatetime(Date value) {
        this.exportDatetime = value;
    }

    /**
     * 获取importToServerDatetime属性的值。
     *
     * @return possible object is
     * {@link Date }
     */
    public Date getImportToServerDatetime() {
        return importToServerDatetime;
    }

    /**
     * 设置importToServerDatetime属性的值。
     *
     * @param value allowed object is
     *              {@link Date }
     */
    public void setImportToServerDatetime(Date value) {
        this.importToServerDatetime = value;
    }

    /**
     * 获取applicantCompliance属性的值。
     *
     * @return possible object is
     * {@link Applicant.ApplicantCompliance }
     */
    public Applicant.ApplicantCompliance getApplicantCompliance() {
        return applicantCompliance;
    }

    /**
     * 设置applicantCompliance属性的值。
     *
     * @param value allowed object is
     *              {@link Applicant.ApplicantCompliance }
     */
    public void setApplicantCompliance(Applicant.ApplicantCompliance value) {
        this.applicantCompliance = value;
    }

    /**
     * 获取applicantDemographic属性的值。
     *
     * @return possible object is
     * {@link Applicant.ApplicantDemographic }
     */
    public Applicant.ApplicantDemographic getApplicantDemographic() {
        return applicantDemographic;
    }

    /**
     * 设置applicantDemographic属性的值。
     *
     * @param value allowed object is
     *              {@link Applicant.ApplicantDemographic }
     */
    public void setApplicantDemographic(Applicant.ApplicantDemographic value) {
        this.applicantDemographic = value;
    }

    /**
     * 获取applicantFingerprint属性的值。
     *
     * @return possible object is
     * {@link Applicant.ApplicantFingerprint }
     */
    public Applicant.ApplicantFingerprint getApplicantFingerprint() {
        return applicantFingerprint;
    }

    /**
     * 设置applicantFingerprint属性的值。
     *
     * @param value allowed object is
     *              {@link Applicant.ApplicantFingerprint }
     */
    public void setApplicantFingerprint(Applicant.ApplicantFingerprint value) {
        this.applicantFingerprint = value;
    }

    /**
     * 获取applicantPhoto属性的值。
     *
     * @return possible object is
     * {@link Applicant.ApplicantPhoto }
     */
    public Applicant.ApplicantPhoto getApplicantPhoto() {
        return applicantPhoto;
    }

    /**
     * 设置applicantPhoto属性的值。
     *
     * @param value allowed object is
     *              {@link Applicant.ApplicantPhoto }
     */
    public void setApplicantPhoto(Applicant.ApplicantPhoto value) {
        this.applicantPhoto = value;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(int dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }


    /**
     * <p>anonymous complex type的 Java 类。
     * <p>
     * <p>以下模式片段指定包含在此类中的预期内容。
     * <p>
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="BeginCreateDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
     *         &lt;element name="EndCreateDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
     *         &lt;element name="BeginEditDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
     *         &lt;element name="EndEditDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
     *         &lt;element name="ApplicationFormContent" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="IDDocumentFormContent" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ProofOfResidenceContent" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "beginCreateDatetime",
            "endCreateDatetime",
            "beginEditDatetime",
            "endEditDatetime",
            "applicationFormContent",
            "idDocumentFormContent",
            "proofOfResidenceContent"
    })
    public static class ApplicantCompliance {
        @XmlElement(name = "BeginCreateDatetime", required = true)
        @XmlSchemaType(name = "dateTime")
        protected Date beginCreateDatetime;


        @XmlElement(name = "EndCreateDatetime", required = true)
        @XmlSchemaType(name = "dateTime")
        protected Date endCreateDatetime;

        @XmlElement(name = "BeginEditDatetime", required = true, nillable = true)
        @XmlSchemaType(name = "dateTime")
        protected Date beginEditDatetime;
        @XmlElement(name = "EndEditDatetime", required = true, nillable = true)
        @XmlSchemaType(name = "dateTime")
        protected Date endEditDatetime;
        @XmlElement(name = "ApplicationFormContent", required = true)
        protected String applicationFormContent;
        @XmlElement(name = "IDDocumentFormContent", required = true)
        protected String idDocumentFormContent;
        @XmlElement(name = "ProofOfResidenceContent", required = true)
        protected String proofOfResidenceContent;

        /**
         * 获取beginCreateDatetime属性的值。
         *
         * @return possible object is
         * {@link Date }
         */
        public Date getBeginCreateDatetime() {
            return beginCreateDatetime;
        }

        /**
         * 设置beginCreateDatetime属性的值。
         *
         * @param value allowed object is
         *              {@link Date }
         */
        public void setBeginCreateDatetime(Date value) {
            this.beginCreateDatetime = value;
        }

        /**
         * 获取endCreateDatetime属性的值。
         *
         * @return possible object is
         * {@link Date }
         */
        public Date getEndCreateDatetime() {
            return endCreateDatetime;
        }

        /**
         * 设置endCreateDatetime属性的值。
         *
         * @param value allowed object is
         *              {@link Date }
         */
        public void setEndCreateDatetime(Date value) {
            this.endCreateDatetime = value;
        }

        /**
         * 获取beginEditDatetime属性的值。
         *
         * @return possible object is
         * {@link Date }
         */
        public Date getBeginEditDatetime() {
            return beginEditDatetime;
        }

        /**
         * 设置beginEditDatetime属性的值。
         *
         * @param value allowed object is
         *              {@link Date }
         */
        public void setBeginEditDatetime(Date value) {
            this.beginEditDatetime = value;
        }

        /**
         * 获取endEditDatetime属性的值。
         *
         * @return possible object is
         * {@link Date }
         */
        public Date getEndEditDatetime() {
            return endEditDatetime;
        }

        /**
         * 设置endEditDatetime属性的值。
         *
         * @param value allowed object is
         *              {@link Date }
         */
        public void setEndEditDatetime(Date value) {
            this.endEditDatetime = value;
        }

        /**
         * 获取applicationFormContent属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getApplicationFormContent() {
            return applicationFormContent;
        }

        /**
         * 设置applicationFormContent属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setApplicationFormContent(String value) {
            this.applicationFormContent = value;
        }

        /**
         * 获取idDocumentFormContent属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getIDDocumentFormContent() {
            return idDocumentFormContent;
        }

        /**
         * 设置idDocumentFormContent属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setIDDocumentFormContent(String value) {
            this.idDocumentFormContent = value;
        }

        /**
         * 获取proofOfResidenceContent属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getProofOfResidenceContent() {
            return proofOfResidenceContent;
        }

        /**
         * 设置proofOfResidenceContent属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setProofOfResidenceContent(String value) {
            this.proofOfResidenceContent = value;
        }

    }


    /**
     * <p>anonymous complex type的 Java 类。
     * <p>
     * <p>以下模式片段指定包含在此类中的预期内容。
     * <p>
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="BeginCreateDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
     *         &lt;element name="EndCreateDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
     *         &lt;element name="BeginEditDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
     *         &lt;element name="EndEditDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
     *         &lt;element name="IdNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Surname" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Forenames" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="DateOfBirth" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
     *         &lt;element name="DateOfBirthText" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Gender" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *         &lt;element name="ProvinceId" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
     *         &lt;element name="ProvinceName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="DistrictId" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
     *         &lt;element name="DistrictName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ConstituencyId" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
     *         &lt;element name="ConstituencyName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="WardId" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
     *         &lt;element name="WardName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="StationId" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
     *         &lt;element name="StationName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="StationCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Surburb" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="StreetName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="StandNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Disability" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
     *         &lt;element name="OtherDisability" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="RegistrationType" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
     *         &lt;element name="PhoneNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Email" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="GISLatitude" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
     *         &lt;element name="GISLongitude" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
     *         &lt;element name="ChangeAddressProvinceId" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
     *         &lt;element name="ChangeAddressProvinceName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ChangeAddressDistrictId" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
     *         &lt;element name="ChangeAddressDistrictName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ChangeAddressConstituencyId" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
     *         &lt;element name="ChangeAddressConstituencyName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ChangeAddressWardId" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
     *         &lt;element name="ChangeAddressWardName" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
     *         &lt;element name="ChangeAddressStationId" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
     *         &lt;element name="ChangeAddressStationName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ChangeAddressStationCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ChangeAddressSurburb" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ChangeAddressStreetName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ChangeAddressStandNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="TransferConstituencyProvinceId" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
     *         &lt;element name="TransferConstituencyProvinceName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="TransferConstituencyDistrictId" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
     *         &lt;element name="TransferConstituencyDistrictName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="TransferConstituencyConstituencyId" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
     *         &lt;element name="TransferConstituencyConstituencyName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="TransferConstituencyWardId" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
     *         &lt;element name="TransferConstituencyWardName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="TransferConstituencyStationId" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
     *         &lt;element name="TransferConstituencyStationName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="TransferConstituencyStationCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="TransferConstituencySurburb" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="TransferConstituencyStreetName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="TransferConstituencyStandNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "beginCreateDatetime",
            "endCreateDatetime",
            "beginEditDatetime",
            "endEditDatetime",
            "idNumber",
            "surname",
            "forenames",
            "dateOfBirth",
            "dateOfBirthText",
            "gender",
            "provinceId",
            "provinceName",
            "districtId",
            "districtName",
            "constituencyId",
            "constituencyName",
            "wardId",
            "wardName",
            "stationId",
            "stationName",
            "stationCode",
            "surburb",
            "streetName",
            "standNumber",
            "disability",
            "otherDisability",
            "registrationType",
            "phoneNumber",
            "email",
            "gisLatitude",
            "gisLongitude",
            "changeAddressProvinceId",
            "changeAddressProvinceName",
            "changeAddressDistrictId",
            "changeAddressDistrictName",
            "changeAddressConstituencyId",
            "changeAddressConstituencyName",
            "changeAddressWardId",
            "changeAddressWardName",
            "changeAddressStationId",
            "changeAddressStationName",
            "changeAddressStationCode",
            "changeAddressSurburb",
            "changeAddressStreetName",
            "changeAddressStandNumber",
            "transferConstituencyProvinceId",
            "transferConstituencyProvinceName",
            "transferConstituencyDistrictId",
            "transferConstituencyDistrictName",
            "transferConstituencyConstituencyId",
            "transferConstituencyConstituencyName",
            "transferConstituencyWardId",
            "transferConstituencyWardName",
            "transferConstituencyStationId",
            "transferConstituencyStationName",
            "transferConstituencyStationCode",
            "transferConstituencySurburb",
            "transferConstituencyStreetName",
            "transferConstituencyStandNumber"
    })
    public static class ApplicantDemographic {

        @XmlElement(name = "BeginCreateDatetime", required = true)
        @XmlSchemaType(name = "dateTime")
        protected Date beginCreateDatetime;

        @XmlElement(name = "EndCreateDatetime", required = true)
        @XmlSchemaType(name = "dateTime")
        protected Date endCreateDatetime;

        @XmlElement(name = "BeginEditDatetime", required = true, nillable = true)
        @XmlSchemaType(name = "dateTime")
        protected Date beginEditDatetime;

        @XmlElement(name = "EndEditDatetime", required = true, nillable = true)
        @XmlSchemaType(name = "dateTime")
        protected Date endEditDatetime;

        @XmlElement(name = "IdNumber", required = true)
        protected String idNumber;
        @XmlElement(name = "Surname", required = true)
        protected String surname;
        @XmlElement(name = "Forenames", required = true)
        protected String forenames;
        @XmlElement(name = "DateOfBirth", required = true)
        @XmlSchemaType(name = "dateTime")
        protected Date dateOfBirth;
        @XmlElement(name = "DateOfBirthText", required = true)
        protected String dateOfBirthText;
        @XmlElement(name = "Gender")
        protected byte gender;
        @XmlElement(name = "ProvinceId")
        @XmlSchemaType(name = "unsignedInt")
        protected long provinceId;
        @XmlElement(name = "ProvinceName", required = true)
        protected String provinceName;
        @XmlElement(name = "DistrictId")
        @XmlSchemaType(name = "unsignedInt")
        protected long districtId;
        @XmlElement(name = "DistrictName", required = true)
        protected String districtName;
        @XmlElement(name = "ConstituencyId")
        @XmlSchemaType(name = "unsignedInt")
        protected long constituencyId;
        @XmlElement(name = "ConstituencyName", required = true)
        protected String constituencyName;
        @XmlElement(name = "WardId")
        @XmlSchemaType(name = "unsignedInt")
        protected long wardId;
        @XmlElement(name = "WardName", required = true)
        protected String wardName;
        @XmlElement(name = "StationId")
        @XmlSchemaType(name = "unsignedInt")
        protected long stationId;
        @XmlElement(name = "StationName", required = true)
        protected String stationName;
        @XmlElement(name = "StationCode", required = true)
        protected String stationCode;
        @XmlElement(name = "Surburb", required = true)
        protected String surburb;
        @XmlElement(name = "StreetName", required = true)
        protected String streetName;
        @XmlElement(name = "StandNumber", required = true)
        protected String standNumber;
        @XmlElement(name = "Disability")
        @XmlSchemaType(name = "unsignedInt")
        protected long disability;
        @XmlElement(name = "OtherDisability", required = true)
        protected String otherDisability;
        @XmlElement(name = "RegistrationType")
        @XmlSchemaType(name = "unsignedByte")
        protected short registrationType;
        @XmlElement(name = "PhoneNumber", required = true)
        protected String phoneNumber;
        @XmlElement(name = "Email", required = true, nillable = true)
        protected String email;
        @XmlElement(name = "GISLatitude")
        @XmlSchemaType(name = "unsignedInt")
        protected long gisLatitude;
        @XmlElement(name = "GISLongitude")
        @XmlSchemaType(name = "unsignedInt")
        protected long gisLongitude;
        @XmlElement(name = "ChangeAddressProvinceId", required = true, type = Long.class, nillable = true)
        @XmlSchemaType(name = "unsignedInt")
        protected Long changeAddressProvinceId;
        @XmlElement(name = "ChangeAddressProvinceName", required = true, nillable = true)
        protected String changeAddressProvinceName;
        @XmlElement(name = "ChangeAddressDistrictId", required = true, type = Long.class, nillable = true)
        @XmlSchemaType(name = "unsignedInt")
        protected Long changeAddressDistrictId;
        @XmlElement(name = "ChangeAddressDistrictName", required = true, nillable = true)
        protected String changeAddressDistrictName;
        @XmlElement(name = "ChangeAddressConstituencyId", required = true, type = Long.class, nillable = true)
        @XmlSchemaType(name = "unsignedInt")
        protected Long changeAddressConstituencyId;
        @XmlElement(name = "ChangeAddressConstituencyName", required = true, nillable = true)
        protected String changeAddressConstituencyName;
        @XmlElement(name = "ChangeAddressWardId", required = true, type = Long.class, nillable = true)
        @XmlSchemaType(name = "unsignedInt")
        protected Long changeAddressWardId;
        @XmlElement(name = "ChangeAddressWardName", required = true, type = Short.class, nillable = true)
        @XmlSchemaType(name = "unsignedByte")
        protected Short changeAddressWardName;
        @XmlElement(name = "ChangeAddressStationId", required = true, type = Long.class, nillable = true)
        @XmlSchemaType(name = "unsignedInt")
        protected Long changeAddressStationId;
        @XmlElement(name = "ChangeAddressStationName", required = true, nillable = true)
        protected String changeAddressStationName;
        @XmlElement(name = "ChangeAddressStationCode", required = true, nillable = true)
        protected String changeAddressStationCode;
        @XmlElement(name = "ChangeAddressSurburb", required = true, nillable = true)
        protected String changeAddressSurburb;
        @XmlElement(name = "ChangeAddressStreetName", required = true, nillable = true)
        protected String changeAddressStreetName;
        @XmlElement(name = "ChangeAddressStandNumber", required = true, nillable = true)
        protected String changeAddressStandNumber;
        @XmlElement(name = "TransferConstituencyProvinceId", required = true, type = Long.class, nillable = true)
        @XmlSchemaType(name = "unsignedInt")
        protected Long transferConstituencyProvinceId;
        @XmlElement(name = "TransferConstituencyProvinceName", required = true, nillable = true)
        protected String transferConstituencyProvinceName;
        @XmlElement(name = "TransferConstituencyDistrictId", required = true, type = Long.class, nillable = true)
        @XmlSchemaType(name = "unsignedInt")
        protected Long transferConstituencyDistrictId;
        @XmlElement(name = "TransferConstituencyDistrictName", required = true, nillable = true)
        protected String transferConstituencyDistrictName;
        @XmlElement(name = "TransferConstituencyConstituencyId", required = true, type = Long.class, nillable = true)
        @XmlSchemaType(name = "unsignedInt")
        protected Long transferConstituencyConstituencyId;
        @XmlElement(name = "TransferConstituencyConstituencyName", required = true, nillable = true)
        protected String transferConstituencyConstituencyName;
        @XmlElement(name = "TransferConstituencyWardId", required = true, type = Long.class, nillable = true)
        @XmlSchemaType(name = "unsignedInt")
        protected Long transferConstituencyWardId;
        @XmlElement(name = "TransferConstituencyWardName", required = true, nillable = true)
        protected String transferConstituencyWardName;
        @XmlElement(name = "TransferConstituencyStationId", required = true, type = Long.class, nillable = true)
        @XmlSchemaType(name = "unsignedInt")
        protected Long transferConstituencyStationId;
        @XmlElement(name = "TransferConstituencyStationName", required = true, nillable = true)
        protected String transferConstituencyStationName;
        @XmlElement(name = "TransferConstituencyStationCode", required = true, nillable = true)
        protected String transferConstituencyStationCode;
        @XmlElement(name = "TransferConstituencySurburb", required = true, nillable = true)
        protected String transferConstituencySurburb;
        @XmlElement(name = "TransferConstituencyStreetName", required = true, nillable = true)
        protected String transferConstituencyStreetName;
        @XmlElement(name = "TransferConstituencyStandNumber", required = true, nillable = true)
        protected String transferConstituencyStandNumber;

        /**
         * 获取beginCreateDatetime属性的值。
         *
         * @return possible object is
         * {@link Date }
         */
        public Date getBeginCreateDatetime() {
            return beginCreateDatetime;
        }

        /**
         * 设置beginCreateDatetime属性的值。
         *
         * @param value allowed object is
         *              {@link Date }
         */
        public void setBeginCreateDatetime(Date value) {
            this.beginCreateDatetime = value;
        }

        /**
         * 获取endCreateDatetime属性的值。
         *
         * @return possible object is
         * {@link Date }
         */
        public Date getEndCreateDatetime() {
            return endCreateDatetime;
        }

        /**
         * 设置endCreateDatetime属性的值。
         *
         * @param value allowed object is
         *              {@link Date }
         */
        public void setEndCreateDatetime(Date value) {
            this.endCreateDatetime = value;
        }

        /**
         * 获取beginEditDatetime属性的值。
         *
         * @return possible object is
         * {@link Date }
         */
        public Date getBeginEditDatetime() {
            return beginEditDatetime;
        }

        /**
         * 设置beginEditDatetime属性的值。
         *
         * @param value allowed object is
         *              {@link Date }
         */
        public void setBeginEditDatetime(Date value) {
            this.beginEditDatetime = value;
        }

        /**
         * 获取endEditDatetime属性的值。
         *
         * @return possible object is
         * {@link Date }
         */
        public Date getEndEditDatetime() {
            return endEditDatetime;
        }

        /**
         * 设置endEditDatetime属性的值。
         *
         * @param value allowed object is
         *              {@link Date }
         */
        public void setEndEditDatetime(Date value) {
            this.endEditDatetime = value;
        }

        /**
         * 获取idNumber属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getIdNumber() {
            return idNumber;
        }

        /**
         * 设置idNumber属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setIdNumber(String value) {
            this.idNumber = value;
        }

        /**
         * 获取surname属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getSurname() {
            return surname;
        }

        /**
         * 设置surname属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setSurname(String value) {
            this.surname = value;
        }

        /**
         * 获取forenames属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getForenames() {
            return forenames;
        }

        /**
         * 设置forenames属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setForenames(String value) {
            this.forenames = value;
        }

        /**
         * 获取dateOfBirth属性的值。
         *
         * @return possible object is
         * {@link Date }
         */
        public Date getDateOfBirth() {
            return dateOfBirth;
        }

        /**
         * 设置dateOfBirth属性的值。
         *
         * @param value allowed object is
         *              {@link Date }
         */
        public void setDateOfBirth(Date value) {
            this.dateOfBirth = value;
        }

        /**
         * 获取dateOfBirthText属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getDateOfBirthText() {
            return dateOfBirthText;
        }

        /**
         * 设置dateOfBirthText属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setDateOfBirthText(String value) {
            this.dateOfBirthText = value;
        }

        /**
         * 获取gender属性的值。
         */
        public byte getGender() {
            return gender;
        }

        /**
         * 设置gender属性的值。
         */
        public void setGender(byte value) {
            this.gender = value;
        }

        /**
         * 获取provinceId属性的值。
         */
        public long getProvinceId() {
            return provinceId;
        }

        /**
         * 设置provinceId属性的值。
         */
        public void setProvinceId(long value) {
            this.provinceId = value;
        }

        /**
         * 获取provinceName属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getProvinceName() {
            return provinceName;
        }

        /**
         * 设置provinceName属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setProvinceName(String value) {
            this.provinceName = value;
        }

        /**
         * 获取districtId属性的值。
         */
        public long getDistrictId() {
            return districtId;
        }

        /**
         * 设置districtId属性的值。
         */
        public void setDistrictId(long value) {
            this.districtId = value;
        }

        /**
         * 获取districtName属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getDistrictName() {
            return districtName;
        }

        /**
         * 设置districtName属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setDistrictName(String value) {
            this.districtName = value;
        }

        /**
         * 获取constituencyId属性的值。
         */
        public long getConstituencyId() {
            return constituencyId;
        }

        /**
         * 设置constituencyId属性的值。
         */
        public void setConstituencyId(long value) {
            this.constituencyId = value;
        }

        /**
         * 获取constituencyName属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getConstituencyName() {
            return constituencyName;
        }

        /**
         * 设置constituencyName属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setConstituencyName(String value) {
            this.constituencyName = value;
        }

        /**
         * 获取wardId属性的值。
         */
        public long getWardId() {
            return wardId;
        }

        /**
         * 设置wardId属性的值。
         */
        public void setWardId(long value) {
            this.wardId = value;
        }

        /**
         * 获取wardName属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getWardName() {
            return wardName;
        }

        /**
         * 设置wardName属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setWardName(String value) {
            this.wardName = value;
        }

        /**
         * 获取stationId属性的值。
         */
        public long getStationId() {
            return stationId;
        }

        /**
         * 设置stationId属性的值。
         */
        public void setStationId(long value) {
            this.stationId = value;
        }

        /**
         * 获取stationName属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getStationName() {
            return stationName;
        }

        /**
         * 设置stationName属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setStationName(String value) {
            this.stationName = value;
        }

        /**
         * 获取stationCode属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getStationCode() {
            return stationCode;
        }

        /**
         * 设置stationCode属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setStationCode(String value) {
            this.stationCode = value;
        }

        /**
         * 获取surburb属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getSurburb() {
            return surburb;
        }

        /**
         * 设置surburb属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setSurburb(String value) {
            this.surburb = value;
        }

        /**
         * 获取streetName属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getStreetName() {
            return streetName;
        }

        /**
         * 设置streetName属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setStreetName(String value) {
            this.streetName = value;
        }

        /**
         * 获取standNumber属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getStandNumber() {
            return standNumber;
        }

        /**
         * 设置standNumber属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setStandNumber(String value) {
            this.standNumber = value;
        }

        /**
         * 获取disability属性的值。
         */
        public long getDisability() {
            return disability;
        }

        /**
         * 设置disability属性的值。
         */
        public void setDisability(long value) {
            this.disability = value;
        }

        /**
         * 获取otherDisability属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getOtherDisability() {
            return otherDisability;
        }

        /**
         * 设置otherDisability属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setOtherDisability(String value) {
            this.otherDisability = value;
        }

        /**
         * 获取registrationType属性的值。
         */
        public short getRegistrationType() {
            return registrationType;
        }

        /**
         * 设置registrationType属性的值。
         */
        public void setRegistrationType(short value) {
            this.registrationType = value;
        }

        /**
         * 获取phoneNumber属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getPhoneNumber() {
            return phoneNumber;
        }

        /**
         * 设置phoneNumber属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setPhoneNumber(String value) {
            this.phoneNumber = value;
        }

        /**
         * 获取email属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getEmail() {
            return email;
        }

        /**
         * 设置email属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setEmail(String value) {
            this.email = value;
        }

        /**
         * 获取gisLatitude属性的值。
         */
        public long getGISLatitude() {
            return gisLatitude;
        }

        /**
         * 设置gisLatitude属性的值。
         */
        public void setGISLatitude(long value) {
            this.gisLatitude = value;
        }

        /**
         * 获取gisLongitude属性的值。
         */
        public long getGISLongitude() {
            return gisLongitude;
        }

        /**
         * 设置gisLongitude属性的值。
         */
        public void setGISLongitude(long value) {
            this.gisLongitude = value;
        }

        /**
         * 获取changeAddressProvinceId属性的值。
         *
         * @return possible object is
         * {@link Long }
         */
        public Long getChangeAddressProvinceId() {
            return changeAddressProvinceId;
        }

        /**
         * 设置changeAddressProvinceId属性的值。
         *
         * @param value allowed object is
         *              {@link Long }
         */
        public void setChangeAddressProvinceId(Long value) {
            this.changeAddressProvinceId = value;
        }

        /**
         * 获取changeAddressProvinceName属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getChangeAddressProvinceName() {
            return changeAddressProvinceName;
        }

        /**
         * 设置changeAddressProvinceName属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setChangeAddressProvinceName(String value) {
            this.changeAddressProvinceName = value;
        }

        /**
         * 获取changeAddressDistrictId属性的值。
         *
         * @return possible object is
         * {@link Long }
         */
        public Long getChangeAddressDistrictId() {
            return changeAddressDistrictId;
        }

        /**
         * 设置changeAddressDistrictId属性的值。
         *
         * @param value allowed object is
         *              {@link Long }
         */
        public void setChangeAddressDistrictId(Long value) {
            this.changeAddressDistrictId = value;
        }

        /**
         * 获取changeAddressDistrictName属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getChangeAddressDistrictName() {
            return changeAddressDistrictName;
        }

        /**
         * 设置changeAddressDistrictName属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setChangeAddressDistrictName(String value) {
            this.changeAddressDistrictName = value;
        }

        /**
         * 获取changeAddressConstituencyId属性的值。
         *
         * @return possible object is
         * {@link Long }
         */
        public Long getChangeAddressConstituencyId() {
            return changeAddressConstituencyId;
        }

        /**
         * 设置changeAddressConstituencyId属性的值。
         *
         * @param value allowed object is
         *              {@link Long }
         */
        public void setChangeAddressConstituencyId(Long value) {
            this.changeAddressConstituencyId = value;
        }

        /**
         * 获取changeAddressConstituencyName属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getChangeAddressConstituencyName() {
            return changeAddressConstituencyName;
        }

        /**
         * 设置changeAddressConstituencyName属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setChangeAddressConstituencyName(String value) {
            this.changeAddressConstituencyName = value;
        }

        /**
         * 获取changeAddressWardId属性的值。
         *
         * @return possible object is
         * {@link Long }
         */
        public Long getChangeAddressWardId() {
            return changeAddressWardId;
        }

        /**
         * 设置changeAddressWardId属性的值。
         *
         * @param value allowed object is
         *              {@link Long }
         */
        public void setChangeAddressWardId(Long value) {
            this.changeAddressWardId = value;
        }

        /**
         * 获取changeAddressWardName属性的值。
         *
         * @return possible object is
         * {@link Short }
         */
        public Short getChangeAddressWardName() {
            return changeAddressWardName;
        }

        /**
         * 设置changeAddressWardName属性的值。
         *
         * @param value allowed object is
         *              {@link Short }
         */
        public void setChangeAddressWardName(Short value) {
            this.changeAddressWardName = value;
        }

        /**
         * 获取changeAddressStationId属性的值。
         *
         * @return possible object is
         * {@link Long }
         */
        public Long getChangeAddressStationId() {
            return changeAddressStationId;
        }

        /**
         * 设置changeAddressStationId属性的值。
         *
         * @param value allowed object is
         *              {@link Long }
         */
        public void setChangeAddressStationId(Long value) {
            this.changeAddressStationId = value;
        }

        /**
         * 获取changeAddressStationName属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getChangeAddressStationName() {
            return changeAddressStationName;
        }

        /**
         * 设置changeAddressStationName属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setChangeAddressStationName(String value) {
            this.changeAddressStationName = value;
        }

        /**
         * 获取changeAddressStationCode属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getChangeAddressStationCode() {
            return changeAddressStationCode;
        }

        /**
         * 设置changeAddressStationCode属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setChangeAddressStationCode(String value) {
            this.changeAddressStationCode = value;
        }

        /**
         * 获取changeAddressSurburb属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getChangeAddressSurburb() {
            return changeAddressSurburb;
        }

        /**
         * 设置changeAddressSurburb属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setChangeAddressSurburb(String value) {
            this.changeAddressSurburb = value;
        }

        /**
         * 获取changeAddressStreetName属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getChangeAddressStreetName() {
            return changeAddressStreetName;
        }

        /**
         * 设置changeAddressStreetName属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setChangeAddressStreetName(String value) {
            this.changeAddressStreetName = value;
        }

        /**
         * 获取changeAddressStandNumber属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getChangeAddressStandNumber() {
            return changeAddressStandNumber;
        }

        /**
         * 设置changeAddressStandNumber属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setChangeAddressStandNumber(String value) {
            this.changeAddressStandNumber = value;
        }

        /**
         * 获取transferConstituencyProvinceId属性的值。
         *
         * @return possible object is
         * {@link Long }
         */
        public Long getTransferConstituencyProvinceId() {
            return transferConstituencyProvinceId;
        }

        /**
         * 设置transferConstituencyProvinceId属性的值。
         *
         * @param value allowed object is
         *              {@link Long }
         */
        public void setTransferConstituencyProvinceId(Long value) {
            this.transferConstituencyProvinceId = value;
        }

        /**
         * 获取transferConstituencyProvinceName属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getTransferConstituencyProvinceName() {
            return transferConstituencyProvinceName;
        }

        /**
         * 设置transferConstituencyProvinceName属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setTransferConstituencyProvinceName(String value) {
            this.transferConstituencyProvinceName = value;
        }

        /**
         * 获取transferConstituencyDistrictId属性的值。
         *
         * @return possible object is
         * {@link Long }
         */
        public Long getTransferConstituencyDistrictId() {
            return transferConstituencyDistrictId;
        }

        /**
         * 设置transferConstituencyDistrictId属性的值。
         *
         * @param value allowed object is
         *              {@link Long }
         */
        public void setTransferConstituencyDistrictId(Long value) {
            this.transferConstituencyDistrictId = value;
        }

        /**
         * 获取transferConstituencyDistrictName属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getTransferConstituencyDistrictName() {
            return transferConstituencyDistrictName;
        }

        /**
         * 设置transferConstituencyDistrictName属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setTransferConstituencyDistrictName(String value) {
            this.transferConstituencyDistrictName = value;
        }

        /**
         * 获取transferConstituencyConstituencyId属性的值。
         *
         * @return possible object is
         * {@link Long }
         */
        public Long getTransferConstituencyConstituencyId() {
            return transferConstituencyConstituencyId;
        }

        /**
         * 设置transferConstituencyConstituencyId属性的值。
         *
         * @param value allowed object is
         *              {@link Long }
         */
        public void setTransferConstituencyConstituencyId(Long value) {
            this.transferConstituencyConstituencyId = value;
        }

        /**
         * 获取transferConstituencyConstituencyName属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getTransferConstituencyConstituencyName() {
            return transferConstituencyConstituencyName;
        }

        /**
         * 设置transferConstituencyConstituencyName属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setTransferConstituencyConstituencyName(String value) {
            this.transferConstituencyConstituencyName = value;
        }

        /**
         * 获取transferConstituencyWardId属性的值。
         *
         * @return possible object is
         * {@link Long }
         */
        public Long getTransferConstituencyWardId() {
            return transferConstituencyWardId;
        }

        /**
         * 设置transferConstituencyWardId属性的值。
         *
         * @param value allowed object is
         *              {@link Long }
         */
        public void setTransferConstituencyWardId(Long value) {
            this.transferConstituencyWardId = value;
        }

        /**
         * 获取transferConstituencyWardName属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getTransferConstituencyWardName() {
            return transferConstituencyWardName;
        }

        /**
         * 设置transferConstituencyWardName属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setTransferConstituencyWardName(String value) {
            this.transferConstituencyWardName = value;
        }

        /**
         * 获取transferConstituencyStationId属性的值。
         *
         * @return possible object is
         * {@link Long }
         */
        public Long getTransferConstituencyStationId() {
            return transferConstituencyStationId;
        }

        /**
         * 设置transferConstituencyStationId属性的值。
         *
         * @param value allowed object is
         *              {@link Long }
         */
        public void setTransferConstituencyStationId(Long value) {
            this.transferConstituencyStationId = value;
        }

        /**
         * 获取transferConstituencyStationName属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getTransferConstituencyStationName() {
            return transferConstituencyStationName;
        }

        /**
         * 设置transferConstituencyStationName属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setTransferConstituencyStationName(String value) {
            this.transferConstituencyStationName = value;
        }

        /**
         * 获取transferConstituencyStationCode属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getTransferConstituencyStationCode() {
            return transferConstituencyStationCode;
        }

        /**
         * 设置transferConstituencyStationCode属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setTransferConstituencyStationCode(String value) {
            this.transferConstituencyStationCode = value;
        }

        /**
         * 获取transferConstituencySurburb属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getTransferConstituencySurburb() {
            return transferConstituencySurburb;
        }

        /**
         * 设置transferConstituencySurburb属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setTransferConstituencySurburb(String value) {
            this.transferConstituencySurburb = value;
        }

        /**
         * 获取transferConstituencyStreetName属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getTransferConstituencyStreetName() {
            return transferConstituencyStreetName;
        }

        /**
         * 设置transferConstituencyStreetName属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setTransferConstituencyStreetName(String value) {
            this.transferConstituencyStreetName = value;
        }

        /**
         * 获取transferConstituencyStandNumber属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getTransferConstituencyStandNumber() {
            return transferConstituencyStandNumber;
        }

        /**
         * 设置transferConstituencyStandNumber属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setTransferConstituencyStandNumber(String value) {
            this.transferConstituencyStandNumber = value;
        }

    }


    /**
     * <p>anonymous complex type的 Java 类。
     * <p>
     * <p>以下模式片段指定包含在此类中的预期内容。
     * <p>
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="BeginCreateDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
     *         &lt;element name="EndCreateDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
     *         &lt;element name="BeginEditDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
     *         &lt;element name="EndEditDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
     *         &lt;element name="LeftThumbImageArray" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="LeftIndexImageArray" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="LeftMiddleImageArray" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="LeftRingImageArray" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="LeftLittleImageArray" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="RightThumbImageArray" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="RightIndexImageArray" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="RightMiddleImageArray" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="RightRingImageArray" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="RightLittleImageArray" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="LeftThumbWSQArray" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="LeftIndexWSQArray" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="LeftMiddleWSQArray" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="LeftRingWSQArray" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="LeftLittleWSQArray" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="RightThumbWSQArray" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="RightIndexWSQArray" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="RightMiddleWSQArray" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="RightRingWSQArray" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="RightLittleWSQArray" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="LeftThumbScore" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
     *         &lt;element name="LeftIndexScore" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
     *         &lt;element name="LeftMiddleScore" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
     *         &lt;element name="LeftRingScore" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
     *         &lt;element name="LeftLittleScore" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
     *         &lt;element name="RightThumbScore" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
     *         &lt;element name="RightIndexScore" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
     *         &lt;element name="RightMiddleScore" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
     *         &lt;element name="RightRingScore" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
     *         &lt;element name="RightLittleScore" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
     *         &lt;element name="LeftThumbState" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
     *         &lt;element name="LeftIndexState" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
     *         &lt;element name="LeftMiddleState" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
     *         &lt;element name="LeftRingState" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
     *         &lt;element name="LeftLittleState" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
     *         &lt;element name="RightThumbState" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
     *         &lt;element name="RightIndexState" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
     *         &lt;element name="RightMiddleState" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
     *         &lt;element name="RightRingState" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
     *         &lt;element name="RightLittleState" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
     *         &lt;element name="SourceAFISID" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
     *         &lt;element name="LeftThumbAFISTemplateT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="LeftIndexAFISTemplateT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="LeftMiddleAFISTemplateT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="LeftRingAFISTemplateT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="LeftLittleAFISTemplateT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="RightThumbAFISTemplateT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="RightIndexAFISTemplateT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="RightMiddleAFISTemplateT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="RightRingAFISTemplateT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="RightLittleAFISTemplateT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="LeftLittleMinutiaesCount" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
     *         &lt;element name="LeftRingMinutiaesCount" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
     *         &lt;element name="LeftMiddleMinutiaesCount" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
     *         &lt;element name="LeftIndexMinutiaesCount" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
     *         &lt;element name="LeftThumbMinutiaesCount" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
     *         &lt;element name="RightLittleMinutiaesCount" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
     *         &lt;element name="RightRingMinutiaesCount" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
     *         &lt;element name="RightMiddleMinutiaesCount" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
     *         &lt;element name="RightIndexMinutiaesCount" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
     *         &lt;element name="RightThumbMinutiaesCount" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
     *         &lt;element name="MissingReasonType" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "beginCreateDatetime",
            "endCreateDatetime",
            "beginEditDatetime",
            "endEditDatetime",
            "leftThumbImageArray",
            "leftIndexImageArray",
            "leftMiddleImageArray",
            "leftRingImageArray",
            "leftLittleImageArray",
            "rightThumbImageArray",
            "rightIndexImageArray",
            "rightMiddleImageArray",
            "rightRingImageArray",
            "rightLittleImageArray",
            "leftThumbWSQArray",
            "leftIndexWSQArray",
            "leftMiddleWSQArray",
            "leftRingWSQArray",
            "leftLittleWSQArray",
            "rightThumbWSQArray",
            "rightIndexWSQArray",
            "rightMiddleWSQArray",
            "rightRingWSQArray",
            "rightLittleWSQArray",
            "leftThumbScore",
            "leftIndexScore",
            "leftMiddleScore",
            "leftRingScore",
            "leftLittleScore",
            "rightThumbScore",
            "rightIndexScore",
            "rightMiddleScore",
            "rightRingScore",
            "rightLittleScore",
            "leftThumbState",
            "leftIndexState",
            "leftMiddleState",
            "leftRingState",
            "leftLittleState",
            "rightThumbState",
            "rightIndexState",
            "rightMiddleState",
            "rightRingState",
            "rightLittleState",
            "sourceAFISID",
            "leftThumbAFISTemplateT",
            "leftIndexAFISTemplateT",
            "leftMiddleAFISTemplateT",
            "leftRingAFISTemplateT",
            "leftLittleAFISTemplateT",
            "rightThumbAFISTemplateT",
            "rightIndexAFISTemplateT",
            "rightMiddleAFISTemplateT",
            "rightRingAFISTemplateT",
            "rightLittleAFISTemplateT",
            "leftLittleMinutiaesCount",
            "leftRingMinutiaesCount",
            "leftMiddleMinutiaesCount",
            "leftIndexMinutiaesCount",
            "leftThumbMinutiaesCount",
            "rightLittleMinutiaesCount",
            "rightRingMinutiaesCount",
            "rightMiddleMinutiaesCount",
            "rightIndexMinutiaesCount",
            "rightThumbMinutiaesCount",
            "missingReasonType"
    })
    public static class ApplicantFingerprint {
        @XmlElement(name = "BeginCreateDatetime", required = true)
        @XmlSchemaType(name = "dateTime")
        protected Date beginCreateDatetime;

        @XmlElement(name = "EndCreateDatetime", required = true)
        @XmlSchemaType(name = "dateTime")
        protected Date endCreateDatetime;

        @XmlElement(name = "BeginEditDatetime", required = true, nillable = true)
        @XmlSchemaType(name = "dateTime")
        protected Date beginEditDatetime;

        @XmlElement(name = "EndEditDatetime", required = true, nillable = true)
        @XmlSchemaType(name = "dateTime")
        protected Date endEditDatetime;
        @XmlElement(name = "LeftThumbImageArray", required = true, nillable = true)
        protected String leftThumbImageArray;
        @XmlElement(name = "LeftIndexImageArray", required = true, nillable = true)
        protected String leftIndexImageArray;
        @XmlElement(name = "LeftMiddleImageArray", required = true, nillable = true)
        protected String leftMiddleImageArray;
        @XmlElement(name = "LeftRingImageArray", required = true, nillable = true)
        protected String leftRingImageArray;
        @XmlElement(name = "LeftLittleImageArray", required = true, nillable = true)
        protected String leftLittleImageArray;
        @XmlElement(name = "RightThumbImageArray", required = true, nillable = true)
        protected String rightThumbImageArray;
        @XmlElement(name = "RightIndexImageArray", required = true, nillable = true)
        protected String rightIndexImageArray;
        @XmlElement(name = "RightMiddleImageArray", required = true, nillable = true)
        protected String rightMiddleImageArray;
        @XmlElement(name = "RightRingImageArray", required = true, nillable = true)
        protected String rightRingImageArray;
        @XmlElement(name = "RightLittleImageArray", required = true, nillable = true)
        protected String rightLittleImageArray;
        @XmlElement(name = "LeftThumbWSQArray", required = true, nillable = true)
        protected String leftThumbWSQArray;
        @XmlElement(name = "LeftIndexWSQArray", required = true, nillable = true)
        protected String leftIndexWSQArray;
        @XmlElement(name = "LeftMiddleWSQArray", required = true, nillable = true)
        protected String leftMiddleWSQArray;
        @XmlElement(name = "LeftRingWSQArray", required = true, nillable = true)
        protected String leftRingWSQArray;
        @XmlElement(name = "LeftLittleWSQArray", required = true, nillable = true)
        protected String leftLittleWSQArray;
        @XmlElement(name = "RightThumbWSQArray", required = true, nillable = true)
        protected String rightThumbWSQArray;
        @XmlElement(name = "RightIndexWSQArray", required = true, nillable = true)
        protected String rightIndexWSQArray;
        @XmlElement(name = "RightMiddleWSQArray", required = true, nillable = true)
        protected String rightMiddleWSQArray;
        @XmlElement(name = "RightRingWSQArray", required = true, nillable = true)
        protected String rightRingWSQArray;
        @XmlElement(name = "RightLittleWSQArray", required = true, nillable = true)
        protected String rightLittleWSQArray;
        @XmlElement(name = "LeftThumbScore", required = true, type = Integer.class, nillable = true)
        @XmlSchemaType(name = "unsignedShort")
        protected Integer leftThumbScore;
        @XmlElement(name = "LeftIndexScore", required = true, type = Integer.class, nillable = true)
        @XmlSchemaType(name = "unsignedShort")
        protected Integer leftIndexScore;
        @XmlElement(name = "LeftMiddleScore", required = true, type = Integer.class, nillable = true)
        @XmlSchemaType(name = "unsignedShort")
        protected Integer leftMiddleScore;
        @XmlElement(name = "LeftRingScore", required = true, type = Integer.class, nillable = true)
        @XmlSchemaType(name = "unsignedShort")
        protected Integer leftRingScore;
        @XmlElement(name = "LeftLittleScore", required = true, type = Integer.class, nillable = true)
        @XmlSchemaType(name = "unsignedShort")
        protected Integer leftLittleScore;
        @XmlElement(name = "RightThumbScore", required = true, type = Integer.class, nillable = true)
        @XmlSchemaType(name = "unsignedShort")
        protected Integer rightThumbScore;
        @XmlElement(name = "RightIndexScore", required = true, type = Integer.class, nillable = true)
        @XmlSchemaType(name = "unsignedShort")
        protected Integer rightIndexScore;
        @XmlElement(name = "RightMiddleScore", required = true, type = Integer.class, nillable = true)
        @XmlSchemaType(name = "unsignedShort")
        protected Integer rightMiddleScore;
        @XmlElement(name = "RightRingScore", required = true, type = Integer.class, nillable = true)
        @XmlSchemaType(name = "unsignedShort")
        protected Integer rightRingScore;
        @XmlElement(name = "RightLittleScore", required = true, type = Integer.class, nillable = true)
        @XmlSchemaType(name = "unsignedShort")
        protected Integer rightLittleScore;
        @XmlElement(name = "LeftThumbState", required = true, type = Short.class, nillable = true)
        @XmlSchemaType(name = "unsignedByte")
        protected Short leftThumbState;
        @XmlElement(name = "LeftIndexState", required = true, type = Short.class, nillable = true)
        @XmlSchemaType(name = "unsignedByte")
        protected Short leftIndexState;
        @XmlElement(name = "LeftMiddleState", required = true, type = Short.class, nillable = true)
        @XmlSchemaType(name = "unsignedByte")
        protected Short leftMiddleState;
        @XmlElement(name = "LeftRingState", required = true, type = Short.class, nillable = true)
        @XmlSchemaType(name = "unsignedByte")
        protected Short leftRingState;
        @XmlElement(name = "LeftLittleState", required = true, type = Short.class, nillable = true)
        @XmlSchemaType(name = "unsignedByte")
        protected Short leftLittleState;
        @XmlElement(name = "RightThumbState", required = true, type = Short.class, nillable = true)
        @XmlSchemaType(name = "unsignedByte")
        protected Short rightThumbState;
        @XmlElement(name = "RightIndexState", required = true, type = Short.class, nillable = true)
        @XmlSchemaType(name = "unsignedByte")
        protected Short rightIndexState;
        @XmlElement(name = "RightMiddleState", required = true, type = Short.class, nillable = true)
        @XmlSchemaType(name = "unsignedByte")
        protected Short rightMiddleState;
        @XmlElement(name = "RightRingState", required = true, type = Short.class, nillable = true)
        @XmlSchemaType(name = "unsignedByte")
        protected Short rightRingState;
        @XmlElement(name = "RightLittleState", required = true, type = Short.class, nillable = true)
        @XmlSchemaType(name = "unsignedByte")
        protected Short rightLittleState;
        @XmlElement(name = "SourceAFISID", required = true, type = Long.class, nillable = true)
        @XmlSchemaType(name = "unsignedInt")
        protected Long sourceAFISID;
        @XmlElement(name = "LeftThumbAFISTemplateT", required = true, nillable = true)
        protected String leftThumbAFISTemplateT;
        @XmlElement(name = "LeftIndexAFISTemplateT", required = true, nillable = true)
        protected String leftIndexAFISTemplateT;
        @XmlElement(name = "LeftMiddleAFISTemplateT", required = true, nillable = true)
        protected String leftMiddleAFISTemplateT;
        @XmlElement(name = "LeftRingAFISTemplateT", required = true, nillable = true)
        protected String leftRingAFISTemplateT;
        @XmlElement(name = "LeftLittleAFISTemplateT", required = true, nillable = true)
        protected String leftLittleAFISTemplateT;
        @XmlElement(name = "RightThumbAFISTemplateT", required = true, nillable = true)
        protected String rightThumbAFISTemplateT;
        @XmlElement(name = "RightIndexAFISTemplateT", required = true, nillable = true)
        protected String rightIndexAFISTemplateT;
        @XmlElement(name = "RightMiddleAFISTemplateT", required = true, nillable = true)
        protected String rightMiddleAFISTemplateT;
        @XmlElement(name = "RightRingAFISTemplateT", required = true, nillable = true)
        protected String rightRingAFISTemplateT;
        @XmlElement(name = "RightLittleAFISTemplateT", required = true, nillable = true)
        protected String rightLittleAFISTemplateT;
        @XmlElement(name = "LeftLittleMinutiaesCount", required = true, type = Long.class, nillable = true)
        @XmlSchemaType(name = "unsignedInt")
        protected Long leftLittleMinutiaesCount;
        @XmlElement(name = "LeftRingMinutiaesCount", required = true, type = Long.class, nillable = true)
        @XmlSchemaType(name = "unsignedInt")
        protected Long leftRingMinutiaesCount;
        @XmlElement(name = "LeftMiddleMinutiaesCount", required = true, type = Long.class, nillable = true)
        @XmlSchemaType(name = "unsignedInt")
        protected Long leftMiddleMinutiaesCount;
        @XmlElement(name = "LeftIndexMinutiaesCount", required = true, type = Long.class, nillable = true)
        @XmlSchemaType(name = "unsignedInt")
        protected Long leftIndexMinutiaesCount;
        @XmlElement(name = "LeftThumbMinutiaesCount", required = true, type = Long.class, nillable = true)
        @XmlSchemaType(name = "unsignedInt")
        protected Long leftThumbMinutiaesCount;
        @XmlElement(name = "RightLittleMinutiaesCount", required = true, type = Long.class, nillable = true)
        @XmlSchemaType(name = "unsignedInt")
        protected Long rightLittleMinutiaesCount;
        @XmlElement(name = "RightRingMinutiaesCount", required = true, type = Long.class, nillable = true)
        @XmlSchemaType(name = "unsignedInt")
        protected Long rightRingMinutiaesCount;
        @XmlElement(name = "RightMiddleMinutiaesCount", required = true, type = Long.class, nillable = true)
        @XmlSchemaType(name = "unsignedInt")
        protected Long rightMiddleMinutiaesCount;
        @XmlElement(name = "RightIndexMinutiaesCount", required = true, type = Long.class, nillable = true)
        @XmlSchemaType(name = "unsignedInt")
        protected Long rightIndexMinutiaesCount;
        @XmlElement(name = "RightThumbMinutiaesCount", required = true, type = Long.class, nillable = true)
        @XmlSchemaType(name = "unsignedInt")
        protected Long rightThumbMinutiaesCount;
        @XmlElement(name = "MissingReasonType", required = true, type = Short.class, nillable = true)
        @XmlSchemaType(name = "unsignedByte")
        protected Short missingReasonType;

        /**
         * 获取beginCreateDatetime属性的值。
         *
         * @return possible object is
         * {@link Date }
         */
        public Date getBeginCreateDatetime() {
            return beginCreateDatetime;
        }

        /**
         * 设置beginCreateDatetime属性的值。
         *
         * @param value allowed object is
         *              {@link Date }
         */
        public void setBeginCreateDatetime(Date value) {
            this.beginCreateDatetime = value;
        }

        /**
         * 获取endCreateDatetime属性的值。
         *
         * @return possible object is
         * {@link Date }
         */
        public Date getEndCreateDatetime() {
            return endCreateDatetime;
        }

        /**
         * 设置endCreateDatetime属性的值。
         *
         * @param value allowed object is
         *              {@link Date }
         */
        public void setEndCreateDatetime(Date value) {
            this.endCreateDatetime = value;
        }

        /**
         * 获取beginEditDatetime属性的值。
         *
         * @return possible object is
         * {@link Date }
         */
        public Date getBeginEditDatetime() {
            return beginEditDatetime;
        }

        /**
         * 设置beginEditDatetime属性的值。
         *
         * @param value allowed object is
         *              {@link Date }
         */
        public void setBeginEditDatetime(Date value) {
            this.beginEditDatetime = value;
        }

        /**
         * 获取endEditDatetime属性的值。
         *
         * @return possible object is
         * {@link Date }
         */
        public Date getEndEditDatetime() {
            return endEditDatetime;
        }

        /**
         * 设置endEditDatetime属性的值。
         *
         * @param value allowed object is
         *              {@link Date }
         */
        public void setEndEditDatetime(Date value) {
            this.endEditDatetime = value;
        }

        /**
         * 获取leftThumbImageArray属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getLeftThumbImageArray() {
            return leftThumbImageArray;
        }

        /**
         * 设置leftThumbImageArray属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setLeftThumbImageArray(String value) {
            this.leftThumbImageArray = value;
        }

        /**
         * 获取leftIndexImageArray属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getLeftIndexImageArray() {
            return leftIndexImageArray;
        }

        /**
         * 设置leftIndexImageArray属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setLeftIndexImageArray(String value) {
            this.leftIndexImageArray = value;
        }

        /**
         * 获取leftMiddleImageArray属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getLeftMiddleImageArray() {
            return leftMiddleImageArray;
        }

        /**
         * 设置leftMiddleImageArray属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setLeftMiddleImageArray(String value) {
            this.leftMiddleImageArray = value;
        }

        /**
         * 获取leftRingImageArray属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getLeftRingImageArray() {
            return leftRingImageArray;
        }

        /**
         * 设置leftRingImageArray属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setLeftRingImageArray(String value) {
            this.leftRingImageArray = value;
        }

        /**
         * 获取leftLittleImageArray属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getLeftLittleImageArray() {
            return leftLittleImageArray;
        }

        /**
         * 设置leftLittleImageArray属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setLeftLittleImageArray(String value) {
            this.leftLittleImageArray = value;
        }

        /**
         * 获取rightThumbImageArray属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getRightThumbImageArray() {
            return rightThumbImageArray;
        }

        /**
         * 设置rightThumbImageArray属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setRightThumbImageArray(String value) {
            this.rightThumbImageArray = value;
        }

        /**
         * 获取rightIndexImageArray属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getRightIndexImageArray() {
            return rightIndexImageArray;
        }

        /**
         * 设置rightIndexImageArray属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setRightIndexImageArray(String value) {
            this.rightIndexImageArray = value;
        }

        /**
         * 获取rightMiddleImageArray属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getRightMiddleImageArray() {
            return rightMiddleImageArray;
        }

        /**
         * 设置rightMiddleImageArray属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setRightMiddleImageArray(String value) {
            this.rightMiddleImageArray = value;
        }

        /**
         * 获取rightRingImageArray属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getRightRingImageArray() {
            return rightRingImageArray;
        }

        /**
         * 设置rightRingImageArray属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setRightRingImageArray(String value) {
            this.rightRingImageArray = value;
        }

        /**
         * 获取rightLittleImageArray属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getRightLittleImageArray() {
            return rightLittleImageArray;
        }

        /**
         * 设置rightLittleImageArray属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setRightLittleImageArray(String value) {
            this.rightLittleImageArray = value;
        }

        /**
         * 获取leftThumbWSQArray属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getLeftThumbWSQArray() {
            return leftThumbWSQArray;
        }

        /**
         * 设置leftThumbWSQArray属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setLeftThumbWSQArray(String value) {
            this.leftThumbWSQArray = value;
        }

        /**
         * 获取leftIndexWSQArray属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getLeftIndexWSQArray() {
            return leftIndexWSQArray;
        }

        /**
         * 设置leftIndexWSQArray属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setLeftIndexWSQArray(String value) {
            this.leftIndexWSQArray = value;
        }

        /**
         * 获取leftMiddleWSQArray属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getLeftMiddleWSQArray() {
            return leftMiddleWSQArray;
        }

        /**
         * 设置leftMiddleWSQArray属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setLeftMiddleWSQArray(String value) {
            this.leftMiddleWSQArray = value;
        }

        /**
         * 获取leftRingWSQArray属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getLeftRingWSQArray() {
            return leftRingWSQArray;
        }

        /**
         * 设置leftRingWSQArray属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setLeftRingWSQArray(String value) {
            this.leftRingWSQArray = value;
        }

        /**
         * 获取leftLittleWSQArray属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getLeftLittleWSQArray() {
            return leftLittleWSQArray;
        }

        /**
         * 设置leftLittleWSQArray属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setLeftLittleWSQArray(String value) {
            this.leftLittleWSQArray = value;
        }

        /**
         * 获取rightThumbWSQArray属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getRightThumbWSQArray() {
            return rightThumbWSQArray;
        }

        /**
         * 设置rightThumbWSQArray属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setRightThumbWSQArray(String value) {
            this.rightThumbWSQArray = value;
        }

        /**
         * 获取rightIndexWSQArray属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getRightIndexWSQArray() {
            return rightIndexWSQArray;
        }

        /**
         * 设置rightIndexWSQArray属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setRightIndexWSQArray(String value) {
            this.rightIndexWSQArray = value;
        }

        /**
         * 获取rightMiddleWSQArray属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getRightMiddleWSQArray() {
            return rightMiddleWSQArray;
        }

        /**
         * 设置rightMiddleWSQArray属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setRightMiddleWSQArray(String value) {
            this.rightMiddleWSQArray = value;
        }

        /**
         * 获取rightRingWSQArray属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getRightRingWSQArray() {
            return rightRingWSQArray;
        }

        /**
         * 设置rightRingWSQArray属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setRightRingWSQArray(String value) {
            this.rightRingWSQArray = value;
        }

        /**
         * 获取rightLittleWSQArray属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getRightLittleWSQArray() {
            return rightLittleWSQArray;
        }

        /**
         * 设置rightLittleWSQArray属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setRightLittleWSQArray(String value) {
            this.rightLittleWSQArray = value;
        }

        /**
         * 获取leftThumbScore属性的值。
         *
         * @return possible object is
         * {@link Integer }
         */
        public Integer getLeftThumbScore() {
            return leftThumbScore;
        }

        /**
         * 设置leftThumbScore属性的值。
         *
         * @param value allowed object is
         *              {@link Integer }
         */
        public void setLeftThumbScore(Integer value) {
            this.leftThumbScore = value;
        }

        /**
         * 获取leftIndexScore属性的值。
         *
         * @return possible object is
         * {@link Integer }
         */
        public Integer getLeftIndexScore() {
            return leftIndexScore;
        }

        /**
         * 设置leftIndexScore属性的值。
         *
         * @param value allowed object is
         *              {@link Integer }
         */
        public void setLeftIndexScore(Integer value) {
            this.leftIndexScore = value;
        }

        /**
         * 获取leftMiddleScore属性的值。
         *
         * @return possible object is
         * {@link Integer }
         */
        public Integer getLeftMiddleScore() {
            return leftMiddleScore;
        }

        /**
         * 设置leftMiddleScore属性的值。
         *
         * @param value allowed object is
         *              {@link Integer }
         */
        public void setLeftMiddleScore(Integer value) {
            this.leftMiddleScore = value;
        }

        /**
         * 获取leftRingScore属性的值。
         *
         * @return possible object is
         * {@link Integer }
         */
        public Integer getLeftRingScore() {
            return leftRingScore;
        }

        /**
         * 设置leftRingScore属性的值。
         *
         * @param value allowed object is
         *              {@link Integer }
         */
        public void setLeftRingScore(Integer value) {
            this.leftRingScore = value;
        }

        /**
         * 获取leftLittleScore属性的值。
         *
         * @return possible object is
         * {@link Integer }
         */
        public Integer getLeftLittleScore() {
            return leftLittleScore;
        }

        /**
         * 设置leftLittleScore属性的值。
         *
         * @param value allowed object is
         *              {@link Integer }
         */
        public void setLeftLittleScore(Integer value) {
            this.leftLittleScore = value;
        }

        /**
         * 获取rightThumbScore属性的值。
         *
         * @return possible object is
         * {@link Integer }
         */
        public Integer getRightThumbScore() {
            return rightThumbScore;
        }

        /**
         * 设置rightThumbScore属性的值。
         *
         * @param value allowed object is
         *              {@link Integer }
         */
        public void setRightThumbScore(Integer value) {
            this.rightThumbScore = value;
        }

        /**
         * 获取rightIndexScore属性的值。
         *
         * @return possible object is
         * {@link Integer }
         */
        public Integer getRightIndexScore() {
            return rightIndexScore;
        }

        /**
         * 设置rightIndexScore属性的值。
         *
         * @param value allowed object is
         *              {@link Integer }
         */
        public void setRightIndexScore(Integer value) {
            this.rightIndexScore = value;
        }

        /**
         * 获取rightMiddleScore属性的值。
         *
         * @return possible object is
         * {@link Integer }
         */
        public Integer getRightMiddleScore() {
            return rightMiddleScore;
        }

        /**
         * 设置rightMiddleScore属性的值。
         *
         * @param value allowed object is
         *              {@link Integer }
         */
        public void setRightMiddleScore(Integer value) {
            this.rightMiddleScore = value;
        }

        /**
         * 获取rightRingScore属性的值。
         *
         * @return possible object is
         * {@link Integer }
         */
        public Integer getRightRingScore() {
            return rightRingScore;
        }

        /**
         * 设置rightRingScore属性的值。
         *
         * @param value allowed object is
         *              {@link Integer }
         */
        public void setRightRingScore(Integer value) {
            this.rightRingScore = value;
        }

        /**
         * 获取rightLittleScore属性的值。
         *
         * @return possible object is
         * {@link Integer }
         */
        public Integer getRightLittleScore() {
            return rightLittleScore;
        }

        /**
         * 设置rightLittleScore属性的值。
         *
         * @param value allowed object is
         *              {@link Integer }
         */
        public void setRightLittleScore(Integer value) {
            this.rightLittleScore = value;
        }

        /**
         * 获取leftThumbState属性的值。
         *
         * @return possible object is
         * {@link Short }
         */
        public Short getLeftThumbState() {
            return leftThumbState;
        }

        /**
         * 设置leftThumbState属性的值。
         *
         * @param value allowed object is
         *              {@link Short }
         */
        public void setLeftThumbState(Short value) {
            this.leftThumbState = value;
        }

        /**
         * 获取leftIndexState属性的值。
         *
         * @return possible object is
         * {@link Short }
         */
        public Short getLeftIndexState() {
            return leftIndexState;
        }

        /**
         * 设置leftIndexState属性的值。
         *
         * @param value allowed object is
         *              {@link Short }
         */
        public void setLeftIndexState(Short value) {
            this.leftIndexState = value;
        }

        /**
         * 获取leftMiddleState属性的值。
         *
         * @return possible object is
         * {@link Short }
         */
        public Short getLeftMiddleState() {
            return leftMiddleState;
        }

        /**
         * 设置leftMiddleState属性的值。
         *
         * @param value allowed object is
         *              {@link Short }
         */
        public void setLeftMiddleState(Short value) {
            this.leftMiddleState = value;
        }

        /**
         * 获取leftRingState属性的值。
         *
         * @return possible object is
         * {@link Short }
         */
        public Short getLeftRingState() {
            return leftRingState;
        }

        /**
         * 设置leftRingState属性的值。
         *
         * @param value allowed object is
         *              {@link Short }
         */
        public void setLeftRingState(Short value) {
            this.leftRingState = value;
        }

        /**
         * 获取leftLittleState属性的值。
         *
         * @return possible object is
         * {@link Short }
         */
        public Short getLeftLittleState() {
            return leftLittleState;
        }

        /**
         * 设置leftLittleState属性的值。
         *
         * @param value allowed object is
         *              {@link Short }
         */
        public void setLeftLittleState(Short value) {
            this.leftLittleState = value;
        }

        /**
         * 获取rightThumbState属性的值。
         *
         * @return possible object is
         * {@link Short }
         */
        public Short getRightThumbState() {
            return rightThumbState;
        }

        /**
         * 设置rightThumbState属性的值。
         *
         * @param value allowed object is
         *              {@link Short }
         */
        public void setRightThumbState(Short value) {
            this.rightThumbState = value;
        }

        /**
         * 获取rightIndexState属性的值。
         *
         * @return possible object is
         * {@link Short }
         */
        public Short getRightIndexState() {
            return rightIndexState;
        }

        /**
         * 设置rightIndexState属性的值。
         *
         * @param value allowed object is
         *              {@link Short }
         */
        public void setRightIndexState(Short value) {
            this.rightIndexState = value;
        }

        /**
         * 获取rightMiddleState属性的值。
         *
         * @return possible object is
         * {@link Short }
         */
        public Short getRightMiddleState() {
            return rightMiddleState;
        }

        /**
         * 设置rightMiddleState属性的值。
         *
         * @param value allowed object is
         *              {@link Short }
         */
        public void setRightMiddleState(Short value) {
            this.rightMiddleState = value;
        }

        /**
         * 获取rightRingState属性的值。
         *
         * @return possible object is
         * {@link Short }
         */
        public Short getRightRingState() {
            return rightRingState;
        }

        /**
         * 设置rightRingState属性的值。
         *
         * @param value allowed object is
         *              {@link Short }
         */
        public void setRightRingState(Short value) {
            this.rightRingState = value;
        }

        /**
         * 获取rightLittleState属性的值。
         *
         * @return possible object is
         * {@link Short }
         */
        public Short getRightLittleState() {
            return rightLittleState;
        }

        /**
         * 设置rightLittleState属性的值。
         *
         * @param value allowed object is
         *              {@link Short }
         */
        public void setRightLittleState(Short value) {
            this.rightLittleState = value;
        }

        /**
         * 获取sourceAFISID属性的值。
         *
         * @return possible object is
         * {@link Long }
         */
        public Long getSourceAFISID() {
            return sourceAFISID;
        }

        /**
         * 设置sourceAFISID属性的值。
         *
         * @param value allowed object is
         *              {@link Long }
         */
        public void setSourceAFISID(Long value) {
            this.sourceAFISID = value;
        }

        /**
         * 获取leftThumbAFISTemplateT属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getLeftThumbAFISTemplateT() {
            return leftThumbAFISTemplateT;
        }

        /**
         * 设置leftThumbAFISTemplateT属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setLeftThumbAFISTemplateT(String value) {
            this.leftThumbAFISTemplateT = value;
        }

        /**
         * 获取leftIndexAFISTemplateT属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getLeftIndexAFISTemplateT() {
            return leftIndexAFISTemplateT;
        }

        /**
         * 设置leftIndexAFISTemplateT属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setLeftIndexAFISTemplateT(String value) {
            this.leftIndexAFISTemplateT = value;
        }

        /**
         * 获取leftMiddleAFISTemplateT属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getLeftMiddleAFISTemplateT() {
            return leftMiddleAFISTemplateT;
        }

        /**
         * 设置leftMiddleAFISTemplateT属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setLeftMiddleAFISTemplateT(String value) {
            this.leftMiddleAFISTemplateT = value;
        }

        /**
         * 获取leftRingAFISTemplateT属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getLeftRingAFISTemplateT() {
            return leftRingAFISTemplateT;
        }

        /**
         * 设置leftRingAFISTemplateT属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setLeftRingAFISTemplateT(String value) {
            this.leftRingAFISTemplateT = value;
        }

        /**
         * 获取leftLittleAFISTemplateT属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getLeftLittleAFISTemplateT() {
            return leftLittleAFISTemplateT;
        }

        /**
         * 设置leftLittleAFISTemplateT属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setLeftLittleAFISTemplateT(String value) {
            this.leftLittleAFISTemplateT = value;
        }

        /**
         * 获取rightThumbAFISTemplateT属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getRightThumbAFISTemplateT() {
            return rightThumbAFISTemplateT;
        }

        /**
         * 设置rightThumbAFISTemplateT属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setRightThumbAFISTemplateT(String value) {
            this.rightThumbAFISTemplateT = value;
        }

        /**
         * 获取rightIndexAFISTemplateT属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getRightIndexAFISTemplateT() {
            return rightIndexAFISTemplateT;
        }

        /**
         * 设置rightIndexAFISTemplateT属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setRightIndexAFISTemplateT(String value) {
            this.rightIndexAFISTemplateT = value;
        }

        /**
         * 获取rightMiddleAFISTemplateT属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getRightMiddleAFISTemplateT() {
            return rightMiddleAFISTemplateT;
        }

        /**
         * 设置rightMiddleAFISTemplateT属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setRightMiddleAFISTemplateT(String value) {
            this.rightMiddleAFISTemplateT = value;
        }

        /**
         * 获取rightRingAFISTemplateT属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getRightRingAFISTemplateT() {
            return rightRingAFISTemplateT;
        }

        /**
         * 设置rightRingAFISTemplateT属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setRightRingAFISTemplateT(String value) {
            this.rightRingAFISTemplateT = value;
        }

        /**
         * 获取rightLittleAFISTemplateT属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getRightLittleAFISTemplateT() {
            return rightLittleAFISTemplateT;
        }

        /**
         * 设置rightLittleAFISTemplateT属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setRightLittleAFISTemplateT(String value) {
            this.rightLittleAFISTemplateT = value;
        }

        /**
         * 获取leftLittleMinutiaesCount属性的值。
         *
         * @return possible object is
         * {@link Long }
         */
        public Long getLeftLittleMinutiaesCount() {
            return leftLittleMinutiaesCount;
        }

        /**
         * 设置leftLittleMinutiaesCount属性的值。
         *
         * @param value allowed object is
         *              {@link Long }
         */
        public void setLeftLittleMinutiaesCount(Long value) {
            this.leftLittleMinutiaesCount = value;
        }

        /**
         * 获取leftRingMinutiaesCount属性的值。
         *
         * @return possible object is
         * {@link Long }
         */
        public Long getLeftRingMinutiaesCount() {
            return leftRingMinutiaesCount;
        }

        /**
         * 设置leftRingMinutiaesCount属性的值。
         *
         * @param value allowed object is
         *              {@link Long }
         */
        public void setLeftRingMinutiaesCount(Long value) {
            this.leftRingMinutiaesCount = value;
        }

        /**
         * 获取leftMiddleMinutiaesCount属性的值。
         *
         * @return possible object is
         * {@link Long }
         */
        public Long getLeftMiddleMinutiaesCount() {
            return leftMiddleMinutiaesCount;
        }

        /**
         * 设置leftMiddleMinutiaesCount属性的值。
         *
         * @param value allowed object is
         *              {@link Long }
         */
        public void setLeftMiddleMinutiaesCount(Long value) {
            this.leftMiddleMinutiaesCount = value;
        }

        /**
         * 获取leftIndexMinutiaesCount属性的值。
         *
         * @return possible object is
         * {@link Long }
         */
        public Long getLeftIndexMinutiaesCount() {
            return leftIndexMinutiaesCount;
        }

        /**
         * 设置leftIndexMinutiaesCount属性的值。
         *
         * @param value allowed object is
         *              {@link Long }
         */
        public void setLeftIndexMinutiaesCount(Long value) {
            this.leftIndexMinutiaesCount = value;
        }

        /**
         * 获取leftThumbMinutiaesCount属性的值。
         *
         * @return possible object is
         * {@link Long }
         */
        public Long getLeftThumbMinutiaesCount() {
            return leftThumbMinutiaesCount;
        }

        /**
         * 设置leftThumbMinutiaesCount属性的值。
         *
         * @param value allowed object is
         *              {@link Long }
         */
        public void setLeftThumbMinutiaesCount(Long value) {
            this.leftThumbMinutiaesCount = value;
        }

        /**
         * 获取rightLittleMinutiaesCount属性的值。
         *
         * @return possible object is
         * {@link Long }
         */
        public Long getRightLittleMinutiaesCount() {
            return rightLittleMinutiaesCount;
        }

        /**
         * 设置rightLittleMinutiaesCount属性的值。
         *
         * @param value allowed object is
         *              {@link Long }
         */
        public void setRightLittleMinutiaesCount(Long value) {
            this.rightLittleMinutiaesCount = value;
        }

        /**
         * 获取rightRingMinutiaesCount属性的值。
         *
         * @return possible object is
         * {@link Long }
         */
        public Long getRightRingMinutiaesCount() {
            return rightRingMinutiaesCount;
        }

        /**
         * 设置rightRingMinutiaesCount属性的值。
         *
         * @param value allowed object is
         *              {@link Long }
         */
        public void setRightRingMinutiaesCount(Long value) {
            this.rightRingMinutiaesCount = value;
        }

        /**
         * 获取rightMiddleMinutiaesCount属性的值。
         *
         * @return possible object is
         * {@link Long }
         */
        public Long getRightMiddleMinutiaesCount() {
            return rightMiddleMinutiaesCount;
        }

        /**
         * 设置rightMiddleMinutiaesCount属性的值。
         *
         * @param value allowed object is
         *              {@link Long }
         */
        public void setRightMiddleMinutiaesCount(Long value) {
            this.rightMiddleMinutiaesCount = value;
        }

        /**
         * 获取rightIndexMinutiaesCount属性的值。
         *
         * @return possible object is
         * {@link Long }
         */
        public Long getRightIndexMinutiaesCount() {
            return rightIndexMinutiaesCount;
        }

        /**
         * 设置rightIndexMinutiaesCount属性的值。
         *
         * @param value allowed object is
         *              {@link Long }
         */
        public void setRightIndexMinutiaesCount(Long value) {
            this.rightIndexMinutiaesCount = value;
        }

        /**
         * 获取rightThumbMinutiaesCount属性的值。
         *
         * @return possible object is
         * {@link Long }
         */
        public Long getRightThumbMinutiaesCount() {
            return rightThumbMinutiaesCount;
        }

        /**
         * 设置rightThumbMinutiaesCount属性的值。
         *
         * @param value allowed object is
         *              {@link Long }
         */
        public void setRightThumbMinutiaesCount(Long value) {
            this.rightThumbMinutiaesCount = value;
        }

        /**
         * 获取missingReasonType属性的值。
         *
         * @return possible object is
         * {@link Short }
         */
        public Short getMissingReasonType() {
            return missingReasonType;
        }

        /**
         * 设置missingReasonType属性的值。
         *
         * @param value allowed object is
         *              {@link Short }
         */
        public void setMissingReasonType(Short value) {
            this.missingReasonType = value;
        }

    }


    /**
     * <p>anonymous complex type的 Java 类。
     * <p>
     * <p>以下模式片段指定包含在此类中的预期内容。
     * <p>
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="BeginCreateDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
     *         &lt;element name="EndCreateDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
     *         &lt;element name="BeginEditDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
     *         &lt;element name="EndEditDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
     *         &lt;element name="PhotoArray" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Thumbnail" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "beginCreateDatetime",
            "endCreateDatetime",
            "beginEditDatetime",
            "endEditDatetime",
            "photoArray",
            "thumbnail"
    })
    public static class ApplicantPhoto {
        @XmlElement(name = "BeginCreateDatetime", required = true)
        @XmlSchemaType(name = "dateTime")
        protected Date beginCreateDatetime;

        @XmlElement(name = "EndCreateDatetime", required = true)
        @XmlSchemaType(name = "dateTime")
        protected Date endCreateDatetime;

        @XmlElement(name = "BeginEditDatetime", required = true, nillable = true)
        @XmlSchemaType(name = "dateTime")
        protected Date beginEditDatetime;

        @XmlElement(name = "EndEditDatetime", required = true, nillable = true)
        @XmlSchemaType(name = "dateTime")
        protected Date endEditDatetime;
        @XmlElement(name = "PhotoArray", required = true)
        protected String photoArray;
        @XmlElement(name = "Thumbnail", required = true)
        protected String thumbnail;

        /**
         * 获取beginCreateDatetime属性的值。
         *
         * @return possible object is
         * {@link Date }
         */
        public Date getBeginCreateDatetime() {
            return beginCreateDatetime;
        }

        /**
         * 设置beginCreateDatetime属性的值。
         *
         * @param value allowed object is
         *              {@link Date }
         */
        public void setBeginCreateDatetime(Date value) {
            this.beginCreateDatetime = value;
        }

        /**
         * 获取endCreateDatetime属性的值。
         *
         * @return possible object is
         * {@link Date }
         */
        public Date getEndCreateDatetime() {
            return endCreateDatetime;
        }

        /**
         * 设置endCreateDatetime属性的值。
         *
         * @param value allowed object is
         *              {@link Date }
         */
        public void setEndCreateDatetime(Date value) {
            this.endCreateDatetime = value;
        }

        /**
         * 获取beginEditDatetime属性的值。
         *
         * @return possible object is
         * {@link Date }
         */
        public Date getBeginEditDatetime() {
            return beginEditDatetime;
        }

        /**
         * 设置beginEditDatetime属性的值。
         *
         * @param value allowed object is
         *              {@link Date }
         */
        public void setBeginEditDatetime(Date value) {
            this.beginEditDatetime = value;
        }

        /**
         * 获取endEditDatetime属性的值。
         *
         * @return possible object is
         * {@link Date }
         */
        public Date getEndEditDatetime() {
            return endEditDatetime;
        }

        /**
         * 设置endEditDatetime属性的值。
         *
         * @param value allowed object is
         *              {@link Date }
         */
        public void setEndEditDatetime(Date value) {
            this.endEditDatetime = value;
        }

        /**
         * 获取photoArray属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getPhotoArray() {
            return photoArray;
        }

        /**
         * 设置photoArray属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setPhotoArray(String value) {
            this.photoArray = value;
        }

        /**
         * 获取thumbnail属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getThumbnail() {
            return thumbnail;
        }

        /**
         * 设置thumbnail属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setThumbnail(String value) {
            this.thumbnail = value;
        }

    }

}
