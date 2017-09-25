package Zim.model;

import Zim.mongo.MongoDao;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "_id",
        "importTask",
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
        "localAuthorityId",
        "localAuthorityName",
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
public class Applicant extends QueueObject {
    public Applicant(boolean isPoisonPill) {
        this.setPoisonPill(isPoisonPill);
    }

    public Applicant() {

    }

    //    public org.bson.Document toDocument(MongoDao mongoDao) {
//        org.bson.Document dbObject = new org.bson.Document();
//        //  dbObject.append("transactionId", transactionId);
//        if (this.get_id() != null && this.get_id().length() > 0) {
//            dbObject.append("_id", this.get_id());
//        }
//        // dbObject.append("fullName", this.getFullName());
//        // dbObject.append("gender", this.getGender());
//        // dbObject.append("dateOfBirth", this.getDateOfBirth());
//        // dbObject.append("dateOfRegistration", this.getDateOfRegistration());
//        // dbObject.append("status", -1);
//
//        //dbObject.append("guid", this.getGuid());
//        // dbObject.append("registrationNumber", this.getRegistrationNumber());
//        // dbObject.append("sortNumber", this.getSortNumber());
//        // dbObject.append("beginCreateDatetime", this.getBeginCreateDatetime());
//        // dbObject.append("endCreateDatetime", this.getEndCreateDatetime());
//        dbObject.append("importTask", this.getImportTask());
//        dbObject.append("beginEditDatetime", this.getBeginEditDatetime());
//        dbObject.append("endEditDatetime", this.getEndEditDatetime());
//        dbObject.append("operatorGuid", this.getOperatorGuid());
//        dbObject.append("operatorName", this.getOperatorName());
//        dbObject.append("provinceId", this.getProvinceId());
//        dbObject.append("provinceName", this.getProvinceName());
//        dbObject.append("districtId", this.getDistrictId());
//        dbObject.append("districtName", this.getDistrictName());
//        dbObject.append("constituencyId", this.getConstituencyId());
//        dbObject.append("constituencyName", this.getConstituencyName());
//        dbObject.append("localAuthorityId", this.getLocalAuthorityId());
//        dbObject.append("localAuthorityName", this.getLocalAuthorityName());
//        dbObject.append("wardId", this.getWardId());
//        dbObject.append("wardName", this.getWardName());
//        dbObject.append("pollingStationId", this.getPollingStationId());
//        dbObject.append("pollingStationCode", this.getPollingStationCode());
//        dbObject.append("pollingStationName", this.getPollingStationName());
//        dbObject.append("saveDatetime", this.getSaveDatetime());
//        dbObject.append("editSaveDatetime", this.getEditSaveDatetime());
//        dbObject.append("deviceName", this.getDeviceName());
//        dbObject.append("underDuress", this.isUnderDuress());
//        dbObject.append("exportDatetime", this.getExportDatetime());
//        dbObject.append("importToServerDatetime", this.getImportToServerDatetime());
//
//        org.bson.Document applicantCompliance = new org.bson.Document();
//
//        applicantCompliance.append("formElaspedTicks", this.getApplicantCompliance().getFormElaspedTicks());
//
//        String applicationFormContentStr = null;
//        if (this.getApplicantCompliance().getApplicationFormContent() != null && this.getApplicantCompliance().getApplicationFormContent().length() > 0) {
//            applicationFormContentStr = mongoDao.insertImg(this.getApplicantCompliance().getApplicationFormContent().getBytes(), "CompliancePhoto");
//        }
//        applicantCompliance.append("applicationFormContent", applicationFormContentStr);
//
//        String idDocumentFormContentStr = null;
//        if (this.getApplicantCompliance().getIDDocumentFormContent() != null && this.getApplicantCompliance().getIDDocumentFormContent().length() > 0) {
//            idDocumentFormContentStr = mongoDao.insertImg(this.getApplicantCompliance().getIDDocumentFormContent().getBytes(), "CompliancePhoto");
//        }
//        applicantCompliance.append("idDocumentFormContent", idDocumentFormContentStr);
//
//        String proofOfResidenceContentStr = null;
//        if (this.getApplicantCompliance().getProofOfResidenceContent() != null && this.getApplicantCompliance().getProofOfResidenceContent().length() > 0) {
//            proofOfResidenceContentStr = mongoDao.insertImg(this.getApplicantCompliance().getProofOfResidenceContent().getBytes(), "CompliancePhoto");
//        }
//        applicantCompliance.append("proofOfResidenceContent", proofOfResidenceContentStr);
//
//        dbObject.append("applicantCompliance", applicantCompliance);
//
//        org.bson.Document applicantDemographic = new org.bson.Document();
//
//        applicantDemographic.append("formElaspedTicks", this.getApplicantDemographic().getFormElaspedTicks());
//        applicantDemographic.append("idNumber", this.getApplicantDemographic().getIdNumber());
//        applicantDemographic.append("surname", this.getApplicantDemographic().getSurname());
//        applicantDemographic.append("forenames", this.getApplicantDemographic().getForenames());
//        applicantDemographic.append("dateOfBirth", this.getApplicantDemographic().getDateOfBirth());
//        applicantDemographic.append("dateOfBirthText", this.getApplicantDemographic().getDateOfBirthText());
//        applicantDemographic.put("gender", this.getApplicantDemographic().getGender());
//        applicantDemographic.append("provinceId", this.getApplicantDemographic().getProvinceId());
//        applicantDemographic.append("provinceName", this.getApplicantDemographic().getProvinceName());
//        applicantDemographic.append("districtId", this.getApplicantDemographic().getDistrictId());
//        applicantDemographic.append("districtName", this.getApplicantDemographic().getDistrictName());
//        applicantDemographic.append("constituencyId", this.getApplicantDemographic().getConstituencyId());
//        applicantDemographic.append("constituencyName", this.getApplicantDemographic().getConstituencyName());
//        applicantDemographic.append("localAuthorityId", this.getApplicantDemographic().getLocalAuthorityId());
//        applicantDemographic.append("localAuthorityName", this.getApplicantDemographic().getLocalAuthorityName());
//        applicantDemographic.append("wardId", this.getApplicantDemographic().getWardId());
//        applicantDemographic.append("wardName", this.getApplicantDemographic().getWardName());
//        applicantDemographic.append("stationId", this.getApplicantDemographic().getStationId());
//        applicantDemographic.append("stationName", this.getApplicantDemographic().getStationName());
//        applicantDemographic.append("stationCode", this.getApplicantDemographic().getStationCode());
//        applicantDemographic.append("surburb", this.getApplicantDemographic().getSurburb());
//        applicantDemographic.append("town", this.getApplicantDemographic().getTown());
//        applicantDemographic.append("streetName", this.getApplicantDemographic().getStreetName());
//        applicantDemographic.append("standNumber", this.getApplicantDemographic().getStandNumber());
//        applicantDemographic.append("disabilityCode", this.getApplicantDemographic().getDisabilityCode());
//        applicantDemographic.append("disabilityName", this.getApplicantDemographic().getDisabilityName());
//        applicantDemographic.append("registrationType", this.getApplicantDemographic().getRegistrationType());
//        applicantDemographic.append("phoneNumber", this.getApplicantDemographic().getPhoneNumber());
//        applicantDemographic.append("email", this.getApplicantDemographic().getEmail());
//        applicantDemographic.append("gisLatitude", this.getApplicantDemographic().getGisLatitude());
//        applicantDemographic.append("gisLongitude", this.getApplicantDemographic().getGisLongitude());
//        applicantDemographic.append("changeAddressProvinceId", this.getApplicantDemographic().getChangeAddressProvinceId());
//        applicantDemographic.append("changeAddressProvinceName", this.getApplicantDemographic().getChangeAddressProvinceName());
//        applicantDemographic.append("changeAddressDistrictId", this.getApplicantDemographic().getChangeAddressDistrictId());
//        applicantDemographic.append("changeAddressDistrictName", this.getApplicantDemographic().getChangeAddressDistrictName());
//        applicantDemographic.append("changeAddressConstituencyId", this.getApplicantDemographic().getChangeAddressConstituencyId());
//        applicantDemographic.append("changeAddressConstituencyName", this.getApplicantDemographic().getChangeAddressConstituencyName());
//        applicantDemographic.append("changeAddressLocalAuthorityId", this.getApplicantDemographic().getChangeAddressLocalAuthorityId());
//        applicantDemographic.append("changeAddressLocalAuthorityName", this.getApplicantDemographic().getChangeAddressLocalAuthorityName());
//        applicantDemographic.append("changeAddressWardId", this.getApplicantDemographic().getChangeAddressWardId());
//        applicantDemographic.append("changeAddressWardName", this.getApplicantDemographic().getChangeAddressWardName());
//        applicantDemographic.append("changeAddressStationId", this.getApplicantDemographic().getChangeAddressStationId());
//        applicantDemographic.append("changeAddressStationName", this.getApplicantDemographic().getChangeAddressStationName());
//        applicantDemographic.append("changeAddressStationCode", this.getApplicantDemographic().getChangeAddressStationCode());
//        applicantDemographic.append("changeAddressSurburb", this.getApplicantDemographic().getChangeAddressSurburb());
//        applicantDemographic.append("changeAddressTown", this.getApplicantDemographic().getChangeAddressTown());
//        applicantDemographic.append("changeAddressStreetName", this.getApplicantDemographic().getChangeAddressStreetName());
//        applicantDemographic.append("changeAddressStandNumber", this.getApplicantDemographic().getChangeAddressStandNumber());
//        applicantDemographic.append("transferConstituencyProvinceId", this.getApplicantDemographic().getTransferConstituencyProvinceId());
//        applicantDemographic.append("transferConstituencyProvinceName", this.getApplicantDemographic().getTransferConstituencyProvinceName());
//        applicantDemographic.append("transferConstituencyDistrictId", this.getApplicantDemographic().getTransferConstituencyDistrictId());
//        applicantDemographic.append("transferConstituencyDistrictName", this.getApplicantDemographic().getTransferConstituencyDistrictName());
//        applicantDemographic.append("transferConstituencyConstituencyId", this.getApplicantDemographic().getTransferConstituencyConstituencyId());
//        applicantDemographic.append("transferConstituencyConstituencyName", this.getApplicantDemographic().getTransferConstituencyConstituencyName());
//        applicantDemographic.append("transferConstituencyLocalAuthorityId", this.getApplicantDemographic().getTransferConstituencyLocalAuthorityId());
//        applicantDemographic.append("transferConstituencyLocalAuthorityName", this.getApplicantDemographic().getTransferConstituencyLocalAuthorityName());
//
//        applicantDemographic.append("transferConstituencyWardId", this.getApplicantDemographic().getTransferConstituencyWardId());
//        applicantDemographic.append("transferConstituencyWardName", this.getApplicantDemographic().getTransferConstituencyWardName());
//        applicantDemographic.append("transferConstituencyStationId", this.getApplicantDemographic().getTransferConstituencyStationId());
//        applicantDemographic.append("transferConstituencyStationName", this.getApplicantDemographic().getTransferConstituencyStationName());
//        applicantDemographic.append("transferConstituencyStationCode", this.getApplicantDemographic().getTransferConstituencyStationCode());
//        applicantDemographic.append("transferConstituencySurburb", this.getApplicantDemographic().getTransferConstituencySurburb());
//        applicantDemographic.append("transferConstituencyTown", this.getApplicantDemographic().getTransferConstituencyTown());
//
//
//        applicantDemographic.append("transferConstituencyStreetName", this.getApplicantDemographic().getTransferConstituencyStreetName());
//        applicantDemographic.append("transferConstituencyStandNumber", this.getApplicantDemographic().getTransferConstituencyStandNumber());
//        dbObject.put("applicantDemographic", applicantDemographic);
//
//        org.bson.Document applicantFingerprint = new org.bson.Document();
//        applicantFingerprint.append("formElaspedTicks", this.getApplicantFingerprint().getFormElaspedTicks());
//
//
//        String leftThumbImageArrayStr = null;
//        if (this.getApplicantFingerprint().getLeftThumbImageArray() != null && this.getApplicantFingerprint().getLeftThumbImageArray().length() > 0) {
//            leftThumbImageArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getLeftThumbImageArray().getBytes(), "FingerprintImage");
//        }
//        applicantFingerprint.append("leftThumbImageArray", leftThumbImageArrayStr);
//
//        String leftIndexImageArrayStr = null;
//        if (this.getApplicantFingerprint().getLeftIndexImageArray() != null && this.getApplicantFingerprint().getLeftIndexImageArray().length() > 0) {
//            leftIndexImageArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getLeftIndexImageArray().getBytes(), "FingerprintImage");
//        }
//        applicantFingerprint.append("leftIndexImageArray", leftIndexImageArrayStr);
//
//        String leftMiddleImageArrayStr = null;
//        if (this.getApplicantFingerprint().getLeftMiddleImageArray() != null && this.getApplicantFingerprint().getLeftMiddleImageArray().length() > 0) {
//            leftMiddleImageArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getLeftMiddleImageArray().getBytes(), "FingerprintImage");
//        }
//        applicantFingerprint.append("leftMiddleImageArray", leftMiddleImageArrayStr);
//
//        String leftRingImageArrayStr = null;
//        if (this.getApplicantFingerprint().getLeftRingImageArray() != null && this.getApplicantFingerprint().getLeftRingImageArray().length() > 0) {
//            leftRingImageArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getLeftRingImageArray().getBytes(), "FingerprintImage");
//        }
//        applicantFingerprint.append("leftRingImageArray", leftRingImageArrayStr);
//
//        String leftLittleImageArrayStr = null;
//        if (this.getApplicantFingerprint().getLeftLittleImageArray() != null && this.getApplicantFingerprint().getLeftLittleImageArray().length() > 0) {
//            leftLittleImageArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getLeftLittleImageArray().getBytes(), "FingerprintImage");
//        }
//        applicantFingerprint.append("leftLittleImageArray", leftLittleImageArrayStr);
//
//        String rightThumbImageArrayStr = null;
//        if (this.getApplicantFingerprint().getRightThumbImageArray() != null && this.getApplicantFingerprint().getRightThumbImageArray().length() > 0) {
//            rightThumbImageArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getRightThumbImageArray().getBytes(), "FingerprintImage");
//        }
//        applicantFingerprint.append("rightThumbImageArray", rightThumbImageArrayStr);
//
//        String rightIndexImageArrayStr = null;
//        if (this.getApplicantFingerprint().getRightIndexImageArray() != null && this.getApplicantFingerprint().getRightIndexImageArray().length() > 0) {
//            rightIndexImageArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getRightIndexImageArray().getBytes(), "FingerprintImage");
//        }
//        applicantFingerprint.append("rightIndexImageArray", rightIndexImageArrayStr);
//
//        String rightMiddleImageArrayStr = null;
//        if (this.getApplicantFingerprint().getRightMiddleImageArray() != null && this.getApplicantFingerprint().getRightMiddleImageArray().length() > 0) {
//            rightMiddleImageArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getRightMiddleImageArray().getBytes(), "FingerprintImage");
//        }
//        applicantFingerprint.append("rightMiddleImageArray", rightMiddleImageArrayStr);
//
//        String rightRingImageArrayStr = null;
//        if (this.getApplicantFingerprint().getRightRingImageArray() != null && this.getApplicantFingerprint().getRightRingImageArray().length() > 0) {
//            rightRingImageArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getRightRingImageArray().getBytes(), "FingerprintImage");
//        }
//        applicantFingerprint.append("rightRingImageArray", rightRingImageArrayStr);
//
//        String rightLittleImageArrayStr = null;
//        if (this.getApplicantFingerprint().getRightLittleImageArray() != null && this.getApplicantFingerprint().getRightLittleImageArray().length() > 0) {
//            rightLittleImageArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getRightLittleImageArray().getBytes(), "FingerprintImage");
//        }
//        applicantFingerprint.append("rightLittleImageArray", rightLittleImageArrayStr);
//
//        String leftThumbWSQArrayStr = null;
//        if (this.getApplicantFingerprint().getLeftThumbWSQArray() != null && this.getApplicantFingerprint().getLeftThumbWSQArray().length() > 0) {
//            leftThumbWSQArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getLeftThumbWSQArray().getBytes(), "FingerprintWSQ");
//        }
//        applicantFingerprint.append("leftThumbWSQArray", leftThumbWSQArrayStr);
//
//        String leftIndexWSQArrayStr = null;
//        if (this.getApplicantFingerprint().getLeftIndexWSQArray() != null && this.getApplicantFingerprint().getLeftIndexWSQArray().length() > 0) {
//            leftIndexWSQArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getLeftIndexWSQArray().getBytes(), "FingerprintWSQ");
//        }
//        applicantFingerprint.append("leftIndexWSQArray", leftIndexWSQArrayStr);
//
//        String leftMiddleWSQArrayStr = null;
//        if (this.getApplicantFingerprint().getLeftMiddleWSQArray() != null && this.getApplicantFingerprint().getLeftMiddleWSQArray().length() > 0) {
//            leftMiddleWSQArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getLeftMiddleWSQArray().getBytes(), "FingerprintWSQ");
//        }
//        applicantFingerprint.append("leftMiddleWSQArray", leftMiddleWSQArrayStr);
//
//        String leftRingWSQArrayStr = null;
//        if (this.getApplicantFingerprint().getLeftRingWSQArray() != null && this.getApplicantFingerprint().getLeftRingWSQArray().length() > 0) {
//            leftRingWSQArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getLeftRingWSQArray().getBytes(), "FingerprintWSQ");
//        }
//        applicantFingerprint.append("leftRingWSQArray", leftRingWSQArrayStr);
//
//        String leftLittleWSQArrayStr = null;
//        if (this.getApplicantFingerprint().getLeftLittleWSQArray() != null && this.getApplicantFingerprint().getLeftLittleWSQArray().length() > 0) {
//            leftLittleWSQArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getLeftLittleWSQArray().getBytes(), "FingerprintWSQ");
//        }
//        applicantFingerprint.append("leftLittleWSQArray", leftLittleWSQArrayStr);
//
//        String rightThumbWSQArrayStr = null;
//        if (this.getApplicantFingerprint().getRightThumbWSQArray() != null && this.getApplicantFingerprint().getRightThumbWSQArray().length() > 0) {
//            rightThumbWSQArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getRightThumbWSQArray().getBytes(), "FingerprintWSQ");
//        }
//        applicantFingerprint.append("rightThumbWSQArray", rightThumbWSQArrayStr);
//
//        String rightIndexWSQArrayStr = null;
//        if (this.getApplicantFingerprint().getRightIndexWSQArray() != null && this.getApplicantFingerprint().getRightIndexWSQArray().length() > 0) {
//            rightIndexWSQArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getRightIndexWSQArray().getBytes(), "FingerprintWSQ");
//        }
//        applicantFingerprint.append("rightIndexWSQArray", rightIndexWSQArrayStr);
//
//        String rightMiddleWSQArrayStr = null;
//        if (this.getApplicantFingerprint().getRightMiddleWSQArray() != null && this.getApplicantFingerprint().getRightMiddleWSQArray().length() > 0) {
//            rightMiddleWSQArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getRightMiddleWSQArray().getBytes(), "FingerprintWSQ");
//        }
//        applicantFingerprint.append("rightMiddleWSQArray", rightMiddleWSQArrayStr);
//
//        String rightRingWSQArrayStr = null;
//        if (this.getApplicantFingerprint().getRightRingWSQArray() != null && this.getApplicantFingerprint().getRightRingWSQArray().length() > 0) {
//            rightRingWSQArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getRightRingWSQArray().getBytes(), "FingerprintWSQ");
//        }
//        applicantFingerprint.append("rightRingWSQArray", rightRingWSQArrayStr);
//
//        String rightLittleWSQArrayStr = null;
//        if (this.getApplicantFingerprint().getRightLittleWSQArray() != null && this.getApplicantFingerprint().getRightLittleWSQArray().length() > 0) {
//            rightLittleWSQArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getRightLittleWSQArray().getBytes(), "FingerprintWSQ");
//        }
//        applicantFingerprint.append("rightLittleWSQArray", rightLittleWSQArrayStr);
//        applicantFingerprint.append("leftThumbScore", this.getApplicantFingerprint().getLeftThumbScore());
//        applicantFingerprint.append("leftIndexScore", this.getApplicantFingerprint().getLeftIndexScore());
//        applicantFingerprint.append("leftMiddleScore", this.getApplicantFingerprint().getLeftMiddleScore());
//        applicantFingerprint.append("leftRingScore", this.getApplicantFingerprint().getLeftRingScore());
//        applicantFingerprint.append("leftLittleScore", this.getApplicantFingerprint().getLeftLittleScore());
//        applicantFingerprint.append("rightThumbScore", this.getApplicantFingerprint().getRightThumbScore());
//        applicantFingerprint.append("rightIndexScore", this.getApplicantFingerprint().getRightIndexScore());
//        applicantFingerprint.append("rightMiddleScore", this.getApplicantFingerprint().getRightMiddleScore());
//        applicantFingerprint.append("rightRingScore", this.getApplicantFingerprint().getRightRingScore());
//        applicantFingerprint.append("rightLittleScore", this.getApplicantFingerprint().getRightLittleScore());
//        applicantFingerprint.append("leftThumbState", this.getApplicantFingerprint().getLeftThumbState());
//        applicantFingerprint.append("leftIndexState", this.getApplicantFingerprint().getLeftIndexState());
//        applicantFingerprint.append("leftMiddleState", this.getApplicantFingerprint().getLeftMiddleState());
//        applicantFingerprint.append("leftRingState", this.getApplicantFingerprint().getLeftRingState());
//        applicantFingerprint.append("leftLittleState", this.getApplicantFingerprint().getLeftLittleState());
//        applicantFingerprint.append("rightThumbState", this.getApplicantFingerprint().getRightThumbState());
//        applicantFingerprint.append("rightIndexState", this.getApplicantFingerprint().getRightIndexState());
//        applicantFingerprint.append("rightMiddleState", this.getApplicantFingerprint().getRightMiddleState());
//        applicantFingerprint.append("rightRingState", this.getApplicantFingerprint().getRightRingState());
//        applicantFingerprint.append("rightLittleState", this.getApplicantFingerprint().getRightLittleState());
//        applicantFingerprint.append("sourceAFISID", this.getApplicantFingerprint().getSourceAFISID());
//
//
//        String leftThumbAFISTemplateStr = null;
//        if (this.getApplicantFingerprint().getLeftThumbAFISTemplate() != null && this.getApplicantFingerprint().getLeftThumbAFISTemplate().length() > 0) {
//            leftThumbAFISTemplateStr = mongoDao.insertImg(this.getApplicantFingerprint().getLeftThumbAFISTemplate().getBytes(), "FingerprintTemplate");
//        }
//        applicantFingerprint.append("leftThumbAFISTemplate", leftThumbAFISTemplateStr);
//
//        String leftIndexAFISTemplateStr = null;
//        if (this.getApplicantFingerprint().getLeftIndexAFISTemplate() != null && this.getApplicantFingerprint().getLeftIndexAFISTemplate().length() > 0) {
//            leftIndexAFISTemplateStr = mongoDao.insertImg(this.getApplicantFingerprint().getLeftIndexAFISTemplate().getBytes(), "FingerprintTemplate");
//        }
//        applicantFingerprint.append("leftIndexAFISTemplate", leftIndexAFISTemplateStr);
//
//        String leftMiddleAFISTemplateStr = null;
//        if (this.getApplicantFingerprint().getLeftMiddleAFISTemplate() != null && this.getApplicantFingerprint().getLeftMiddleAFISTemplate().length() > 0) {
//            leftMiddleAFISTemplateStr = mongoDao.insertImg(this.getApplicantFingerprint().getLeftMiddleAFISTemplate().getBytes(), "FingerprintTemplate");
//        }
//        applicantFingerprint.append("leftMiddleAFISTemplate", leftMiddleAFISTemplateStr);
//
//        String leftRingAFISTemplateStr = null;
//        if (this.getApplicantFingerprint().getLeftRingAFISTemplate() != null && this.getApplicantFingerprint().getLeftRingAFISTemplate().length() > 0) {
//            leftRingAFISTemplateStr = mongoDao.insertImg(this.getApplicantFingerprint().getLeftRingAFISTemplate().getBytes(), "FingerprintTemplate");
//        }
//        applicantFingerprint.append("leftRingAFISTemplate", leftRingAFISTemplateStr);
//
//        String leftLittleAFISTemplateStr = null;
//        if (this.getApplicantFingerprint().getLeftLittleAFISTemplate() != null && this.getApplicantFingerprint().getLeftLittleAFISTemplate().length() > 0) {
//            leftLittleAFISTemplateStr = mongoDao.insertImg(this.getApplicantFingerprint().getLeftLittleAFISTemplate().getBytes(), "FingerprintTemplate");
//        }
//        applicantFingerprint.append("leftLittleAFISTemplate", leftLittleAFISTemplateStr);
//
//        String rightThumbAFISTemplateStr = null;
//        if (this.getApplicantFingerprint().getRightThumbAFISTemplate() != null && this.getApplicantFingerprint().getRightThumbAFISTemplate().length() > 0) {
//            rightThumbAFISTemplateStr = mongoDao.insertImg(this.getApplicantFingerprint().getRightThumbAFISTemplate().getBytes(), "FingerprintTemplate");
//        }
//        applicantFingerprint.append("rightThumbAFISTemplate", rightThumbAFISTemplateStr);
//
//        String rightIndexAFISTemplateStr = null;
//        if (this.getApplicantFingerprint().getRightIndexAFISTemplate() != null && this.getApplicantFingerprint().getRightIndexAFISTemplate().length() > 0) {
//            rightIndexAFISTemplateStr = mongoDao.insertImg(this.getApplicantFingerprint().getRightIndexAFISTemplate().getBytes(), "FingerprintTemplate");
//        }
//        applicantFingerprint.append("rightIndexAFISTemplate", rightIndexAFISTemplateStr);
//
//        String rightMiddleAFISTemplateStr = null;
//        if (this.getApplicantFingerprint().getRightMiddleAFISTemplate() != null && this.getApplicantFingerprint().getRightMiddleAFISTemplate().length() > 0) {
//            rightMiddleAFISTemplateStr = mongoDao.insertImg(this.getApplicantFingerprint().getRightMiddleAFISTemplate().getBytes(), "FingerprintTemplate");
//        }
//        applicantFingerprint.append("rightMiddleAFISTemplate", rightMiddleAFISTemplateStr);
//
//        String rightRingAFISTemplateStr = null;
//        if (this.getApplicantFingerprint().getRightRingAFISTemplate() != null && this.getApplicantFingerprint().getRightRingAFISTemplate().length() > 0) {
//            rightRingAFISTemplateStr = mongoDao.insertImg(this.getApplicantFingerprint().getRightRingAFISTemplate().getBytes(), "FingerprintTemplate");
//        }
//        applicantFingerprint.append("rightRingAFISTemplate", rightRingAFISTemplateStr);
//
//        String rightLittleAFISTemplateStr = null;
//        if (this.getApplicantFingerprint().getRightLittleAFISTemplate() != null && this.getApplicantFingerprint().getRightLittleAFISTemplate().length() > 0) {
//            rightLittleAFISTemplateStr = mongoDao.insertImg(this.getApplicantFingerprint().getRightLittleAFISTemplate().getBytes(), "FingerprintTemplate");
//        }
//        applicantFingerprint.append("rightLittleAFISTemplate", rightLittleAFISTemplateStr);
//
//        applicantFingerprint.append("leftLittleMinutiaesCount", this.getApplicantFingerprint().getLeftLittleMinutiaesCount());
//        applicantFingerprint.append("leftRingMinutiaesCount", this.getApplicantFingerprint().getLeftRingMinutiaesCount());
//        applicantFingerprint.append("leftMiddleMinutiaesCount", this.getApplicantFingerprint().getLeftMiddleMinutiaesCount());
//        applicantFingerprint.append("leftIndexMinutiaesCount", this.getApplicantFingerprint().getLeftIndexMinutiaesCount());
//        applicantFingerprint.append("leftThumbMinutiaesCount", this.getApplicantFingerprint().getLeftThumbMinutiaesCount());
//        applicantFingerprint.append("rightLittleMinutiaesCount", this.getApplicantFingerprint().getRightLittleMinutiaesCount());
//        applicantFingerprint.append("rightRingMinutiaesCount", this.getApplicantFingerprint().getRightRingMinutiaesCount());
//        applicantFingerprint.append("rightMiddleMinutiaesCount", this.getApplicantFingerprint().getRightMiddleMinutiaesCount());
//        applicantFingerprint.append("rightIndexMinutiaesCount", this.getApplicantFingerprint().getRightIndexMinutiaesCount());
//        applicantFingerprint.append("rightThumbMinutiaesCount", this.getApplicantFingerprint().getRightThumbMinutiaesCount());
//        applicantFingerprint.append("missingReasonType", this.getApplicantFingerprint().getMissingReasonType());
//
//        dbObject.append("applicantFingerprint", applicantFingerprint);
//
//        org.bson.Document applicantPhoto = new org.bson.Document();
//        applicantPhoto.append("formElaspedTicks", this.getApplicantPhoto().getFormElaspedTicks());
//
//
//        String photoArrayStr = null;
//        if (this.getApplicantPhoto().getPhotoArray() != null && this.getApplicantPhoto().getPhotoArray().length() > 0) {
////            ObjectId oid = mongoDBDao.saveApplicantPhotoFile(this.getApplicantPhoto().getPhotoArray().getBytes());
////            applicantPhoto.put("photoArray", oid.toString());
//            photoArrayStr = mongoDao.insertImg(this.getApplicantPhoto().getPhotoArray().getBytes(), "PersonPhoto");
//
//        }
//        applicantPhoto.append("photoArray", photoArrayStr);
//
//        //  applicantPhoto.put("photoArray", this.getApplicantPhoto().getPhotoArray());
//        String thumbnailStr = null;
//        if (this.getApplicantPhoto().getThumbnail() != null && this.getApplicantPhoto().getThumbnail().length() > 0) {
//            thumbnailStr = mongoDao.insertImg(this.getApplicantPhoto().getThumbnail().getBytes(), "PersonPhoto");
//
//        }
//        applicantPhoto.append("thumbnail", thumbnailStr);
//
//        dbObject.put("applicantPhoto", applicantPhoto);
//        return dbObject;
//    }
    public org.bson.Document createImgDocument(ObjectId id, ObjectId transactionId, byte[] fileByte) {

        org.bson.Document document = new org.bson.Document();
        document.append("_id", id);
        document.append("transactionId", transactionId);
        document.append("imgData", fileByte);
        return document;
    }

    public ConcurrentHashMap<String, List<org.bson.Document>> toDocument(ObjectId transactionId) {
        ConcurrentHashMap<String, List<org.bson.Document>> result = new ConcurrentHashMap<>();
        org.bson.Document applicantObject = new org.bson.Document();
        List<org.bson.Document> listApplicant = new ArrayList<>();
//        List<org.bson.Document> listCompliancePhoto = new ArrayList<>();
        List<org.bson.Document> listFingerprintImage = new ArrayList<>();
        List<org.bson.Document> listFingerprintTemplate = new ArrayList<>();
        List<org.bson.Document> listFingerprintWSQ = new ArrayList<>();
        List<org.bson.Document> listApplicantPhotos = new ArrayList<>();
        List<org.bson.Document> listApplicantMaster = new ArrayList<>();
        //  dbObject.append("transactionId", transactionId);
        //region Applicant

        applicantObject.append("_id", this.get_id());
        applicantObject.append("transactionId", transactionId);
        // dbObject.append("fullName", this.getFullName());
        // dbObject.append("gender", this.getGender());
        // dbObject.append("dateOfBirth", this.getDateOfBirth());
        // dbObject.append("dateOfRegistration", this.getDateOfRegistration());
        // dbObject.append("status", -1);

        //dbObject.append("guid", this.getGuid());
        // dbObject.append("registrationNumber", this.getRegistrationNumber());
        // dbObject.append("sortNumber", this.getSortNumber());
        // dbObject.append("beginCreateDatetime", this.getBeginCreateDatetime());
        // dbObject.append("endCreateDatetime", this.getEndCreateDatetime());
        applicantObject.append("importTask", this.getImportTask());
        applicantObject.append("beginEditDatetime", this.getBeginEditDatetime());
        applicantObject.append("endEditDatetime", this.getEndEditDatetime());
        applicantObject.append("operatorGuid", this.getOperatorGuid());
        applicantObject.append("operatorName", this.getOperatorName());
        applicantObject.append("provinceId", this.getProvinceId());
        applicantObject.append("provinceName", this.getProvinceName());
        applicantObject.append("districtId", this.getDistrictId());
        applicantObject.append("districtName", this.getDistrictName());
        applicantObject.append("constituencyId", this.getConstituencyId());
        applicantObject.append("constituencyName", this.getConstituencyName());
        applicantObject.append("localAuthorityId", this.getLocalAuthorityId());
        applicantObject.append("localAuthorityName", this.getLocalAuthorityName());
        applicantObject.append("wardId", this.getWardId());
        applicantObject.append("wardName", this.getWardName());
        applicantObject.append("pollingStationId", this.getPollingStationId());
        applicantObject.append("pollingStationCode", this.getPollingStationCode());
        applicantObject.append("pollingStationName", this.getPollingStationName());
        applicantObject.append("saveDatetime", this.getSaveDatetime());
        applicantObject.append("editSaveDatetime", this.getEditSaveDatetime());
        applicantObject.append("deviceName", this.getDeviceName());
        applicantObject.append("underDuress", this.isUnderDuress());
        applicantObject.append("exportDatetime", this.getExportDatetime());
        applicantObject.append("importToServerDatetime", this.getImportToServerDatetime());

        org.bson.Document applicantCompliance = new org.bson.Document();

        applicantCompliance.append("formElaspedTicks", this.getApplicantCompliance().getFormElaspedTicks());

        String applicationFormContentStr = null;
        if (this.getApplicantCompliance().getApplicationFormContent() != null && this.getApplicantCompliance().getApplicationFormContent().length() > 0) {
            ObjectId objectId = new ObjectId();
            listApplicantPhotos.add(createImgDocument(objectId, transactionId, this.getApplicantCompliance().getApplicationFormContent().getBytes()));
            applicationFormContentStr = objectId.toString();
        }
        applicantCompliance.append("applicationFormContent", applicationFormContentStr);

        String idDocumentFormContentStr = null;
        if (this.getApplicantCompliance().getIDDocumentFormContent() != null && this.getApplicantCompliance().getIDDocumentFormContent().length() > 0) {
            // idDocumentFormContentStr = mongoDao.insertImg(this.getApplicantCompliance().getIDDocumentFormContent().getBytes(), "CompliancePhoto");
            ObjectId objectId = new ObjectId();
            listApplicantPhotos.add(createImgDocument(objectId, transactionId, this.getApplicantCompliance().getIDDocumentFormContent().getBytes()));
            idDocumentFormContentStr = objectId.toString();
        }
        applicantCompliance.append("idDocumentFormContent", idDocumentFormContentStr);

        String proofOfResidenceContentStr = null;
        if (this.getApplicantCompliance().getProofOfResidenceContent() != null && this.getApplicantCompliance().getProofOfResidenceContent().length() > 0) {
            // proofOfResidenceContentStr = mongoDao.insertImg(this.getApplicantCompliance().getProofOfResidenceContent().getBytes(), "CompliancePhoto");
            ObjectId objectId = new ObjectId();
            listApplicantPhotos.add(createImgDocument(objectId, transactionId, this.getApplicantCompliance().getProofOfResidenceContent().getBytes()));
            proofOfResidenceContentStr = objectId.toString();
        }
        applicantCompliance.append("proofOfResidenceContent", proofOfResidenceContentStr);

        applicantObject.append("applicantCompliance", applicantCompliance);

        org.bson.Document applicantDemographic = new org.bson.Document();

        applicantDemographic.append("formElaspedTicks", this.getApplicantDemographic().getFormElaspedTicks());
        applicantDemographic.append("idNumber", this.getApplicantDemographic().getIdNumber());
        applicantDemographic.append("surname", this.getApplicantDemographic().getSurname());
        applicantDemographic.append("forenames", this.getApplicantDemographic().getForenames());
        applicantDemographic.append("dateOfBirth", this.getApplicantDemographic().getDateOfBirth());
        applicantDemographic.append("dateOfBirthText", this.getApplicantDemographic().getDateOfBirthText());
        applicantDemographic.put("gender", this.getApplicantDemographic().getGender());
        applicantDemographic.append("provinceId", this.getApplicantDemographic().getProvinceId());
        applicantDemographic.append("provinceName", this.getApplicantDemographic().getProvinceName());
        applicantDemographic.append("districtId", this.getApplicantDemographic().getDistrictId());
        applicantDemographic.append("districtName", this.getApplicantDemographic().getDistrictName());
        applicantDemographic.append("constituencyId", this.getApplicantDemographic().getConstituencyId());
        applicantDemographic.append("constituencyName", this.getApplicantDemographic().getConstituencyName());
        applicantDemographic.append("localAuthorityId", this.getApplicantDemographic().getLocalAuthorityId());
        applicantDemographic.append("localAuthorityName", this.getApplicantDemographic().getLocalAuthorityName());
        applicantDemographic.append("wardId", this.getApplicantDemographic().getWardId());
        applicantDemographic.append("wardName", this.getApplicantDemographic().getWardName());
        applicantDemographic.append("stationId", this.getApplicantDemographic().getStationId());
        applicantDemographic.append("stationName", this.getApplicantDemographic().getStationName());
        applicantDemographic.append("stationCode", this.getApplicantDemographic().getStationCode());
        applicantDemographic.append("surburb", this.getApplicantDemographic().getSurburb());
        applicantDemographic.append("town", this.getApplicantDemographic().getTown());
        applicantDemographic.append("streetName", this.getApplicantDemographic().getStreetName());
        applicantDemographic.append("standNumber", this.getApplicantDemographic().getStandNumber());
        applicantDemographic.append("disabilityCode", this.getApplicantDemographic().getDisabilityCode());
        applicantDemographic.append("disabilityName", this.getApplicantDemographic().getDisabilityName());
        applicantDemographic.append("registrationType", this.getApplicantDemographic().getRegistrationType());
        applicantDemographic.append("phoneNumber", this.getApplicantDemographic().getPhoneNumber());
        applicantDemographic.append("email", this.getApplicantDemographic().getEmail());
        applicantDemographic.append("gisLatitude", this.getApplicantDemographic().getGisLatitude());
        applicantDemographic.append("gisLongitude", this.getApplicantDemographic().getGisLongitude());
        applicantDemographic.append("changeAddressProvinceId", this.getApplicantDemographic().getChangeAddressProvinceId());
        applicantDemographic.append("changeAddressProvinceName", this.getApplicantDemographic().getChangeAddressProvinceName());
        applicantDemographic.append("changeAddressDistrictId", this.getApplicantDemographic().getChangeAddressDistrictId());
        applicantDemographic.append("changeAddressDistrictName", this.getApplicantDemographic().getChangeAddressDistrictName());
        applicantDemographic.append("changeAddressConstituencyId", this.getApplicantDemographic().getChangeAddressConstituencyId());
        applicantDemographic.append("changeAddressConstituencyName", this.getApplicantDemographic().getChangeAddressConstituencyName());
        applicantDemographic.append("changeAddressLocalAuthorityId", this.getApplicantDemographic().getChangeAddressLocalAuthorityId());
        applicantDemographic.append("changeAddressLocalAuthorityName", this.getApplicantDemographic().getChangeAddressLocalAuthorityName());
        applicantDemographic.append("changeAddressWardId", this.getApplicantDemographic().getChangeAddressWardId());
        applicantDemographic.append("changeAddressWardName", this.getApplicantDemographic().getChangeAddressWardName());
        applicantDemographic.append("changeAddressStationId", this.getApplicantDemographic().getChangeAddressStationId());
        applicantDemographic.append("changeAddressStationName", this.getApplicantDemographic().getChangeAddressStationName());
        applicantDemographic.append("changeAddressStationCode", this.getApplicantDemographic().getChangeAddressStationCode());
        applicantDemographic.append("changeAddressSurburb", this.getApplicantDemographic().getChangeAddressSurburb());
        applicantDemographic.append("changeAddressTown", this.getApplicantDemographic().getChangeAddressTown());
        applicantDemographic.append("changeAddressStreetName", this.getApplicantDemographic().getChangeAddressStreetName());
        applicantDemographic.append("changeAddressStandNumber", this.getApplicantDemographic().getChangeAddressStandNumber());
        applicantDemographic.append("transferConstituencyProvinceId", this.getApplicantDemographic().getTransferConstituencyProvinceId());
        applicantDemographic.append("transferConstituencyProvinceName", this.getApplicantDemographic().getTransferConstituencyProvinceName());
        applicantDemographic.append("transferConstituencyDistrictId", this.getApplicantDemographic().getTransferConstituencyDistrictId());
        applicantDemographic.append("transferConstituencyDistrictName", this.getApplicantDemographic().getTransferConstituencyDistrictName());
        applicantDemographic.append("transferConstituencyConstituencyId", this.getApplicantDemographic().getTransferConstituencyConstituencyId());
        applicantDemographic.append("transferConstituencyConstituencyName", this.getApplicantDemographic().getTransferConstituencyConstituencyName());
        applicantDemographic.append("transferConstituencyLocalAuthorityId", this.getApplicantDemographic().getTransferConstituencyLocalAuthorityId());
        applicantDemographic.append("transferConstituencyLocalAuthorityName", this.getApplicantDemographic().getTransferConstituencyLocalAuthorityName());

        applicantDemographic.append("transferConstituencyWardId", this.getApplicantDemographic().getTransferConstituencyWardId());
        applicantDemographic.append("transferConstituencyWardName", this.getApplicantDemographic().getTransferConstituencyWardName());
        applicantDemographic.append("transferConstituencyStationId", this.getApplicantDemographic().getTransferConstituencyStationId());
        applicantDemographic.append("transferConstituencyStationName", this.getApplicantDemographic().getTransferConstituencyStationName());
        applicantDemographic.append("transferConstituencyStationCode", this.getApplicantDemographic().getTransferConstituencyStationCode());
        applicantDemographic.append("transferConstituencySurburb", this.getApplicantDemographic().getTransferConstituencySurburb());
        applicantDemographic.append("transferConstituencyTown", this.getApplicantDemographic().getTransferConstituencyTown());


        applicantDemographic.append("transferConstituencyStreetName", this.getApplicantDemographic().getTransferConstituencyStreetName());
        applicantDemographic.append("transferConstituencyStandNumber", this.getApplicantDemographic().getTransferConstituencyStandNumber());
        applicantObject.put("applicantDemographic", applicantDemographic);
//endregion


        org.bson.Document applicantFingerprint = new org.bson.Document();
        applicantFingerprint.append("formElaspedTicks", this.getApplicantFingerprint().getFormElaspedTicks());

        //region FingerprintImage
        String leftThumbImageArrayStr = null;
        if (this.getApplicantFingerprint().getLeftThumbImageArray() != null && this.getApplicantFingerprint().getLeftThumbImageArray().length() > 0) {
            // leftThumbImageArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getLeftThumbImageArray().getBytes(), "FingerprintImage");
            ObjectId objectId = new ObjectId();
            listFingerprintImage.add(createImgDocument(objectId, transactionId, this.getApplicantFingerprint().getLeftThumbImageArray().getBytes()));
            leftThumbImageArrayStr = objectId.toString();
        }
        applicantFingerprint.append("leftThumbImageArray", leftThumbImageArrayStr);

        String leftIndexImageArrayStr = null;
        if (this.getApplicantFingerprint().getLeftIndexImageArray() != null && this.getApplicantFingerprint().getLeftIndexImageArray().length() > 0) {
            // leftIndexImageArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getLeftIndexImageArray().getBytes(), "FingerprintImage");
            ObjectId objectId = new ObjectId();
            listFingerprintImage.add(createImgDocument(objectId, transactionId, this.getApplicantFingerprint().getLeftIndexImageArray().getBytes()));
            leftIndexImageArrayStr = objectId.toString();
        }
        applicantFingerprint.append("leftIndexImageArray", leftIndexImageArrayStr);

        String leftMiddleImageArrayStr = null;
        if (this.getApplicantFingerprint().getLeftMiddleImageArray() != null && this.getApplicantFingerprint().getLeftMiddleImageArray().length() > 0) {
            // leftMiddleImageArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getLeftMiddleImageArray().getBytes(), "FingerprintImage");
            ObjectId objectId = new ObjectId();
            listFingerprintImage.add(createImgDocument(objectId, transactionId, this.getApplicantFingerprint().getLeftMiddleImageArray().getBytes()));
            leftMiddleImageArrayStr = objectId.toString();
        }
        applicantFingerprint.append("leftMiddleImageArray", leftMiddleImageArrayStr);

        String leftRingImageArrayStr = null;
        if (this.getApplicantFingerprint().getLeftRingImageArray() != null && this.getApplicantFingerprint().getLeftRingImageArray().length() > 0) {
            // leftRingImageArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getLeftRingImageArray().getBytes(), "FingerprintImage");
            ObjectId objectId = new ObjectId();
            listFingerprintImage.add(createImgDocument(objectId, transactionId, this.getApplicantFingerprint().getLeftRingImageArray().getBytes()));
            leftRingImageArrayStr = objectId.toString();
        }
        applicantFingerprint.append("leftRingImageArray", leftRingImageArrayStr);

        String leftLittleImageArrayStr = null;
        if (this.getApplicantFingerprint().getLeftLittleImageArray() != null && this.getApplicantFingerprint().getLeftLittleImageArray().length() > 0) {
            // leftLittleImageArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getLeftLittleImageArray().getBytes(), "FingerprintImage");
            ObjectId objectId = new ObjectId();
            listFingerprintImage.add(createImgDocument(objectId, transactionId, this.getApplicantFingerprint().getLeftLittleImageArray().getBytes()));
            leftLittleImageArrayStr = objectId.toString();
        }
        applicantFingerprint.append("leftLittleImageArray", leftLittleImageArrayStr);

        String rightThumbImageArrayStr = null;
        if (this.getApplicantFingerprint().getRightThumbImageArray() != null && this.getApplicantFingerprint().getRightThumbImageArray().length() > 0) {
            //  rightThumbImageArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getRightThumbImageArray().getBytes(), "FingerprintImage");
            ObjectId objectId = new ObjectId();
            listFingerprintImage.add(createImgDocument(objectId, transactionId, this.getApplicantFingerprint().getRightThumbImageArray().getBytes()));
            rightThumbImageArrayStr = objectId.toString();
        }
        applicantFingerprint.append("rightThumbImageArray", rightThumbImageArrayStr);

        String rightIndexImageArrayStr = null;
        if (this.getApplicantFingerprint().getRightIndexImageArray() != null && this.getApplicantFingerprint().getRightIndexImageArray().length() > 0) {
            // rightIndexImageArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getRightIndexImageArray().getBytes(), "FingerprintImage");
            ObjectId objectId = new ObjectId();
            listFingerprintImage.add(createImgDocument(objectId, transactionId, this.getApplicantFingerprint().getRightIndexImageArray().getBytes()));
            rightIndexImageArrayStr = objectId.toString();
        }
        applicantFingerprint.append("rightIndexImageArray", rightIndexImageArrayStr);

        String rightMiddleImageArrayStr = null;
        if (this.getApplicantFingerprint().getRightMiddleImageArray() != null && this.getApplicantFingerprint().getRightMiddleImageArray().length() > 0) {
            //    rightMiddleImageArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getRightMiddleImageArray().getBytes(), "FingerprintImage");
            ObjectId objectId = new ObjectId();
            listFingerprintImage.add(createImgDocument(objectId, transactionId, this.getApplicantFingerprint().getRightMiddleImageArray().getBytes()));
            rightMiddleImageArrayStr = objectId.toString();
        }
        applicantFingerprint.append("rightMiddleImageArray", rightMiddleImageArrayStr);

        String rightRingImageArrayStr = null;
        if (this.getApplicantFingerprint().getRightRingImageArray() != null && this.getApplicantFingerprint().getRightRingImageArray().length() > 0) {
            //  rightRingImageArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getRightRingImageArray().getBytes(), "FingerprintImage");
            ObjectId objectId = new ObjectId();
            listFingerprintImage.add(createImgDocument(objectId, transactionId, this.getApplicantFingerprint().getRightRingImageArray().getBytes()));
            rightRingImageArrayStr = objectId.toString();
        }
        applicantFingerprint.append("rightRingImageArray", rightRingImageArrayStr);

        String rightLittleImageArrayStr = null;
        if (this.getApplicantFingerprint().getRightLittleImageArray() != null && this.getApplicantFingerprint().getRightLittleImageArray().length() > 0) {
            // rightLittleImageArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getRightLittleImageArray().getBytes(), "FingerprintImage");
            ObjectId objectId = new ObjectId();
            listFingerprintImage.add(createImgDocument(objectId, transactionId, this.getApplicantFingerprint().getRightLittleImageArray().getBytes()));
            rightLittleImageArrayStr = objectId.toString();
        }
        applicantFingerprint.append("rightLittleImageArray", rightLittleImageArrayStr);
        //endregion FingerprintImage

        //region FingerprintWSQ
        String leftThumbWSQArrayStr = null;
        if (this.getApplicantFingerprint().getLeftThumbWSQArray() != null && this.getApplicantFingerprint().getLeftThumbWSQArray().length() > 0) {
            // leftThumbWSQArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getLeftThumbWSQArray().getBytes(), "FingerprintWSQ");
            ObjectId objectId = new ObjectId();
            listFingerprintWSQ.add(createImgDocument(objectId, transactionId, this.getApplicantFingerprint().getLeftThumbWSQArray().getBytes()));
            leftThumbWSQArrayStr = objectId.toString();
        }
        applicantFingerprint.append("leftThumbWSQArray", leftThumbWSQArrayStr);

        String leftIndexWSQArrayStr = null;
        if (this.getApplicantFingerprint().getLeftIndexWSQArray() != null && this.getApplicantFingerprint().getLeftIndexWSQArray().length() > 0) {
            //leftIndexWSQArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getLeftIndexWSQArray().getBytes(), "FingerprintWSQ");
            ObjectId objectId = new ObjectId();
            listFingerprintWSQ.add(createImgDocument(objectId, transactionId, this.getApplicantFingerprint().getLeftIndexWSQArray().getBytes()));
            leftIndexWSQArrayStr = objectId.toString();
        }
        applicantFingerprint.append("leftIndexWSQArray", leftIndexWSQArrayStr);

        String leftMiddleWSQArrayStr = null;
        if (this.getApplicantFingerprint().getLeftMiddleWSQArray() != null && this.getApplicantFingerprint().getLeftMiddleWSQArray().length() > 0) {
            // leftMiddleWSQArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getLeftMiddleWSQArray().getBytes(), "FingerprintWSQ");
            ObjectId objectId = new ObjectId();
            listFingerprintWSQ.add(createImgDocument(objectId, transactionId, this.getApplicantFingerprint().getLeftMiddleWSQArray().getBytes()));
            leftMiddleWSQArrayStr = objectId.toString();
        }
        applicantFingerprint.append("leftMiddleWSQArray", leftMiddleWSQArrayStr);

        String leftRingWSQArrayStr = null;
        if (this.getApplicantFingerprint().getLeftRingWSQArray() != null && this.getApplicantFingerprint().getLeftRingWSQArray().length() > 0) {
            //  leftRingWSQArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getLeftRingWSQArray().getBytes(), "FingerprintWSQ");
            ObjectId objectId = new ObjectId();
            listFingerprintWSQ.add(createImgDocument(objectId, transactionId, this.getApplicantFingerprint().getLeftRingWSQArray().getBytes()));
            leftRingWSQArrayStr = objectId.toString();
        }
        applicantFingerprint.append("leftRingWSQArray", leftRingWSQArrayStr);

        String leftLittleWSQArrayStr = null;
        if (this.getApplicantFingerprint().getLeftLittleWSQArray() != null && this.getApplicantFingerprint().getLeftLittleWSQArray().length() > 0) {
            //  leftLittleWSQArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getLeftLittleWSQArray().getBytes(), "FingerprintWSQ");
            ObjectId objectId = new ObjectId();
            listFingerprintWSQ.add(createImgDocument(objectId, transactionId, this.getApplicantFingerprint().getLeftLittleWSQArray().getBytes()));
            leftLittleWSQArrayStr = objectId.toString();
        }
        applicantFingerprint.append("leftLittleWSQArray", leftLittleWSQArrayStr);

        String rightThumbWSQArrayStr = null;
        if (this.getApplicantFingerprint().getRightThumbWSQArray() != null && this.getApplicantFingerprint().getRightThumbWSQArray().length() > 0) {
            // rightThumbWSQArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getRightThumbWSQArray().getBytes(), "FingerprintWSQ");
            ObjectId objectId = new ObjectId();
            listFingerprintWSQ.add(createImgDocument(objectId, transactionId, this.getApplicantFingerprint().getRightThumbWSQArray().getBytes()));
            rightThumbWSQArrayStr = objectId.toString();
        }
        applicantFingerprint.append("rightThumbWSQArray", rightThumbWSQArrayStr);

        String rightIndexWSQArrayStr = null;
        if (this.getApplicantFingerprint().getRightIndexWSQArray() != null && this.getApplicantFingerprint().getRightIndexWSQArray().length() > 0) {
            // rightIndexWSQArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getRightIndexWSQArray().getBytes(), "FingerprintWSQ");
            ObjectId objectId = new ObjectId();
            listFingerprintWSQ.add(createImgDocument(objectId, transactionId, this.getApplicantFingerprint().getRightIndexWSQArray().getBytes()));
            rightIndexWSQArrayStr = objectId.toString();
        }
        applicantFingerprint.append("rightIndexWSQArray", rightIndexWSQArrayStr);

        String rightMiddleWSQArrayStr = null;
        if (this.getApplicantFingerprint().getRightMiddleWSQArray() != null && this.getApplicantFingerprint().getRightMiddleWSQArray().length() > 0) {
            // rightMiddleWSQArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getRightMiddleWSQArray().getBytes(), "FingerprintWSQ");
            ObjectId objectId = new ObjectId();
            listFingerprintWSQ.add(createImgDocument(objectId, transactionId, this.getApplicantFingerprint().getRightMiddleWSQArray().getBytes()));
            rightMiddleWSQArrayStr = objectId.toString();
        }
        applicantFingerprint.append("rightMiddleWSQArray", rightMiddleWSQArrayStr);

        String rightRingWSQArrayStr = null;
        if (this.getApplicantFingerprint().getRightRingWSQArray() != null && this.getApplicantFingerprint().getRightRingWSQArray().length() > 0) {
            // rightRingWSQArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getRightRingWSQArray().getBytes(), "FingerprintWSQ");
            ObjectId objectId = new ObjectId();
            listFingerprintWSQ.add(createImgDocument(objectId, transactionId, this.getApplicantFingerprint().getRightRingWSQArray().getBytes()));
            rightRingWSQArrayStr = objectId.toString();
        }
        applicantFingerprint.append("rightRingWSQArray", rightRingWSQArrayStr);

        String rightLittleWSQArrayStr = null;
        if (this.getApplicantFingerprint().getRightLittleWSQArray() != null && this.getApplicantFingerprint().getRightLittleWSQArray().length() > 0) {
            //rightLittleWSQArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getRightLittleWSQArray().getBytes(), "FingerprintWSQ");
            ObjectId objectId = new ObjectId();
            listFingerprintWSQ.add(createImgDocument(objectId, transactionId, this.getApplicantFingerprint().getRightLittleWSQArray().getBytes()));
            rightLittleWSQArrayStr = objectId.toString();
        }
        applicantFingerprint.append("rightLittleWSQArray", rightLittleWSQArrayStr);

        //endregion FingerprintWSQ

        applicantFingerprint.append("leftThumbScore", this.getApplicantFingerprint().getLeftThumbScore());
        applicantFingerprint.append("leftIndexScore", this.getApplicantFingerprint().getLeftIndexScore());
        applicantFingerprint.append("leftMiddleScore", this.getApplicantFingerprint().getLeftMiddleScore());
        applicantFingerprint.append("leftRingScore", this.getApplicantFingerprint().getLeftRingScore());
        applicantFingerprint.append("leftLittleScore", this.getApplicantFingerprint().getLeftLittleScore());
        applicantFingerprint.append("rightThumbScore", this.getApplicantFingerprint().getRightThumbScore());
        applicantFingerprint.append("rightIndexScore", this.getApplicantFingerprint().getRightIndexScore());
        applicantFingerprint.append("rightMiddleScore", this.getApplicantFingerprint().getRightMiddleScore());
        applicantFingerprint.append("rightRingScore", this.getApplicantFingerprint().getRightRingScore());
        applicantFingerprint.append("rightLittleScore", this.getApplicantFingerprint().getRightLittleScore());
        applicantFingerprint.append("leftThumbState", this.getApplicantFingerprint().getLeftThumbState());
        applicantFingerprint.append("leftIndexState", this.getApplicantFingerprint().getLeftIndexState());
        applicantFingerprint.append("leftMiddleState", this.getApplicantFingerprint().getLeftMiddleState());
        applicantFingerprint.append("leftRingState", this.getApplicantFingerprint().getLeftRingState());
        applicantFingerprint.append("leftLittleState", this.getApplicantFingerprint().getLeftLittleState());
        applicantFingerprint.append("rightThumbState", this.getApplicantFingerprint().getRightThumbState());
        applicantFingerprint.append("rightIndexState", this.getApplicantFingerprint().getRightIndexState());
        applicantFingerprint.append("rightMiddleState", this.getApplicantFingerprint().getRightMiddleState());
        applicantFingerprint.append("rightRingState", this.getApplicantFingerprint().getRightRingState());
        applicantFingerprint.append("rightLittleState", this.getApplicantFingerprint().getRightLittleState());
        applicantFingerprint.append("sourceAFISID", this.getApplicantFingerprint().getSourceAFISID());

        //region FingerprintTemplate
        String leftThumbAFISTemplateStr = null;
        if (this.getApplicantFingerprint().getLeftThumbAFISTemplate() != null && this.getApplicantFingerprint().getLeftThumbAFISTemplate().length() > 0) {
            //  leftThumbAFISTemplateStr = mongoDao.insertImg(this.getApplicantFingerprint().getLeftThumbAFISTemplate().getBytes(), "FingerprintTemplate");
            ObjectId objectId = new ObjectId();
            listFingerprintTemplate.add(createImgDocument(objectId, transactionId, this.getApplicantFingerprint().getLeftThumbAFISTemplate().getBytes()));
            leftThumbAFISTemplateStr = objectId.toString();
        }
        applicantFingerprint.append("leftThumbAFISTemplate", leftThumbAFISTemplateStr);

        String leftIndexAFISTemplateStr = null;
        if (this.getApplicantFingerprint().getLeftIndexAFISTemplate() != null && this.getApplicantFingerprint().getLeftIndexAFISTemplate().length() > 0) {
            // leftIndexAFISTemplateStr = mongoDao.insertImg(this.getApplicantFingerprint().getLeftIndexAFISTemplate().getBytes(), "FingerprintTemplate");
            ObjectId objectId = new ObjectId();
            listFingerprintTemplate.add(createImgDocument(objectId, transactionId, this.getApplicantFingerprint().getLeftIndexAFISTemplate().getBytes()));
            leftIndexAFISTemplateStr = objectId.toString();
        }
        applicantFingerprint.append("leftIndexAFISTemplate", leftIndexAFISTemplateStr);

        String leftMiddleAFISTemplateStr = null;
        if (this.getApplicantFingerprint().getLeftMiddleAFISTemplate() != null && this.getApplicantFingerprint().getLeftMiddleAFISTemplate().length() > 0) {
            // leftMiddleAFISTemplateStr = mongoDao.insertImg(this.getApplicantFingerprint().getLeftMiddleAFISTemplate().getBytes(), "FingerprintTemplate");
            ObjectId objectId = new ObjectId();
            listFingerprintTemplate.add(createImgDocument(objectId, transactionId, this.getApplicantFingerprint().getLeftMiddleAFISTemplate().getBytes()));
            leftMiddleAFISTemplateStr = objectId.toString();
        }
        applicantFingerprint.append("leftMiddleAFISTemplate", leftMiddleAFISTemplateStr);

        String leftRingAFISTemplateStr = null;
        if (this.getApplicantFingerprint().getLeftRingAFISTemplate() != null && this.getApplicantFingerprint().getLeftRingAFISTemplate().length() > 0) {
            // leftRingAFISTemplateStr = mongoDao.insertImg(this.getApplicantFingerprint().getLeftRingAFISTemplate().getBytes(), "FingerprintTemplate");
            ObjectId objectId = new ObjectId();
            listFingerprintTemplate.add(createImgDocument(objectId, transactionId, this.getApplicantFingerprint().getLeftRingAFISTemplate().getBytes()));
            leftRingAFISTemplateStr = objectId.toString();

        }
        applicantFingerprint.append("leftRingAFISTemplate", leftRingAFISTemplateStr);

        String leftLittleAFISTemplateStr = null;
        if (this.getApplicantFingerprint().getLeftLittleAFISTemplate() != null && this.getApplicantFingerprint().getLeftLittleAFISTemplate().length() > 0) {
            //leftLittleAFISTemplateStr = mongoDao.insertImg(this.getApplicantFingerprint().getLeftLittleAFISTemplate().getBytes(), "FingerprintTemplate");
            ObjectId objectId = new ObjectId();
            listFingerprintTemplate.add(createImgDocument(objectId, transactionId, this.getApplicantFingerprint().getLeftLittleAFISTemplate().getBytes()));
            leftLittleAFISTemplateStr = objectId.toString();
        }
        applicantFingerprint.append("leftLittleAFISTemplate", leftLittleAFISTemplateStr);

        String rightThumbAFISTemplateStr = null;
        if (this.getApplicantFingerprint().getRightThumbAFISTemplate() != null && this.getApplicantFingerprint().getRightThumbAFISTemplate().length() > 0) {
            //   rightThumbAFISTemplateStr = mongoDao.insertImg(this.getApplicantFingerprint().getRightThumbAFISTemplate().getBytes(), "FingerprintTemplate");
            ObjectId objectId = new ObjectId();
            listFingerprintTemplate.add(createImgDocument(objectId, transactionId, this.getApplicantFingerprint().getRightThumbAFISTemplate().getBytes()));
            rightThumbAFISTemplateStr = objectId.toString();
        }
        applicantFingerprint.append("rightThumbAFISTemplate", rightThumbAFISTemplateStr);

        String rightIndexAFISTemplateStr = null;
        if (this.getApplicantFingerprint().getRightIndexAFISTemplate() != null && this.getApplicantFingerprint().getRightIndexAFISTemplate().length() > 0) {
            // rightIndexAFISTemplateStr = mongoDao.insertImg(this.getApplicantFingerprint().getRightIndexAFISTemplate().getBytes(), "FingerprintTemplate");
            ObjectId objectId = new ObjectId();
            listFingerprintTemplate.add(createImgDocument(objectId, transactionId, this.getApplicantFingerprint().getRightIndexAFISTemplate().getBytes()));
            rightIndexAFISTemplateStr = objectId.toString();
        }
        applicantFingerprint.append("rightIndexAFISTemplate", rightIndexAFISTemplateStr);

        String rightMiddleAFISTemplateStr = null;
        if (this.getApplicantFingerprint().getRightMiddleAFISTemplate() != null && this.getApplicantFingerprint().getRightMiddleAFISTemplate().length() > 0) {
            // rightMiddleAFISTemplateStr = mongoDao.insertImg(this.getApplicantFingerprint().getRightMiddleAFISTemplate().getBytes(), "FingerprintTemplate");
            ObjectId objectId = new ObjectId();
            listFingerprintTemplate.add(createImgDocument(objectId, transactionId, this.getApplicantFingerprint().getRightMiddleAFISTemplate().getBytes()));
            rightMiddleAFISTemplateStr = objectId.toString();
        }
        applicantFingerprint.append("rightMiddleAFISTemplate", rightMiddleAFISTemplateStr);

        String rightRingAFISTemplateStr = null;
        if (this.getApplicantFingerprint().getRightRingAFISTemplate() != null && this.getApplicantFingerprint().getRightRingAFISTemplate().length() > 0) {
            // rightRingAFISTemplateStr = mongoDao.insertImg(this.getApplicantFingerprint().getRightRingAFISTemplate().getBytes(), "FingerprintTemplate");
            ObjectId objectId = new ObjectId();
            listFingerprintTemplate.add(createImgDocument(objectId, transactionId, this.getApplicantFingerprint().getRightRingAFISTemplate().getBytes()));
            rightRingAFISTemplateStr = objectId.toString();
        }
        applicantFingerprint.append("rightRingAFISTemplate", rightRingAFISTemplateStr);

        String rightLittleAFISTemplateStr = null;
        if (this.getApplicantFingerprint().getRightLittleAFISTemplate() != null && this.getApplicantFingerprint().getRightLittleAFISTemplate().length() > 0) {
            // rightLittleAFISTemplateStr = mongoDao.insertImg(this.getApplicantFingerprint().getRightLittleAFISTemplate().getBytes(), "FingerprintTemplate");
            ObjectId objectId = new ObjectId();
            listFingerprintTemplate.add(createImgDocument(objectId, transactionId, this.getApplicantFingerprint().getRightLittleAFISTemplate().getBytes()));
            rightLittleAFISTemplateStr = objectId.toString();
        }
        applicantFingerprint.append("rightLittleAFISTemplate", rightLittleAFISTemplateStr);
        //endregion FingerprintTemplate

        applicantFingerprint.append("leftLittleMinutiaesCount", this.getApplicantFingerprint().getLeftLittleMinutiaesCount());
        applicantFingerprint.append("leftRingMinutiaesCount", this.getApplicantFingerprint().getLeftRingMinutiaesCount());
        applicantFingerprint.append("leftMiddleMinutiaesCount", this.getApplicantFingerprint().getLeftMiddleMinutiaesCount());
        applicantFingerprint.append("leftIndexMinutiaesCount", this.getApplicantFingerprint().getLeftIndexMinutiaesCount());
        applicantFingerprint.append("leftThumbMinutiaesCount", this.getApplicantFingerprint().getLeftThumbMinutiaesCount());
        applicantFingerprint.append("rightLittleMinutiaesCount", this.getApplicantFingerprint().getRightLittleMinutiaesCount());
        applicantFingerprint.append("rightRingMinutiaesCount", this.getApplicantFingerprint().getRightRingMinutiaesCount());
        applicantFingerprint.append("rightMiddleMinutiaesCount", this.getApplicantFingerprint().getRightMiddleMinutiaesCount());
        applicantFingerprint.append("rightIndexMinutiaesCount", this.getApplicantFingerprint().getRightIndexMinutiaesCount());
        applicantFingerprint.append("rightThumbMinutiaesCount", this.getApplicantFingerprint().getRightThumbMinutiaesCount());
        applicantFingerprint.append("missingReasonType", this.getApplicantFingerprint().getMissingReasonType());

        applicantObject.append("applicantFingerprint", applicantFingerprint);

        org.bson.Document applicantPhoto = new org.bson.Document();
        applicantPhoto.append("formElaspedTicks", this.getApplicantPhoto().getFormElaspedTicks());


        String photoArrayStr = null;
        if (this.getApplicantPhoto().getPhotoArray() != null && this.getApplicantPhoto().getPhotoArray().length() > 0) {
//            ObjectId oid = mongoDBDao.saveApplicantPhotoFile(this.getApplicantPhoto().getPhotoArray().getBytes());
//            applicantPhoto.put("photoArray", oid.toString());
            //   photoArrayStr = mongoDao.insertImg(this.getApplicantPhoto().getPhotoArray().getBytes(), "PersonPhoto");
            ObjectId objectId = new ObjectId();
            listApplicantPhotos.add(createImgDocument(objectId, transactionId, this.getApplicantPhoto().getPhotoArray().getBytes()));
            photoArrayStr = objectId.toString();
        }
        applicantPhoto.append("photoArray", photoArrayStr);

        //  applicantPhoto.put("photoArray", this.getApplicantPhoto().getPhotoArray());
        String thumbnailStr = null;
        if (this.getApplicantPhoto().getThumbnail() != null && this.getApplicantPhoto().getThumbnail().length() > 0) {
            //  thumbnailStr = mongoDao.insertImg(this.getApplicantPhoto().getThumbnail().getBytes(), "PersonPhoto");
            ObjectId objectId = new ObjectId();
            listApplicantPhotos.add(createImgDocument(objectId, transactionId, this.getApplicantPhoto().getThumbnail().getBytes()));
            thumbnailStr = objectId.toString();
        }
        applicantPhoto.append("thumbnail", thumbnailStr);

        applicantObject.put("applicantPhoto", applicantPhoto);
        // return applicantObject;


        if (listFingerprintImage.size() > 0) {
            result.put("FingerprintImage", listFingerprintImage);
        }
        if (listFingerprintTemplate.size() > 0) {
            result.put("FingerprintTemplate", listFingerprintTemplate);
        }
        if (listFingerprintWSQ.size() > 0) {
            result.put("FingerprintWSQ", listFingerprintWSQ);
        }
        if (listApplicantPhotos.size() > 0) {
            result.put("ApplicantPhotos", listApplicantPhotos);
        }
        listApplicantMaster.add(this.toMasterDocument(transactionId));
        result.put("ApplicantMaster", listApplicantMaster);

        listApplicant.add(applicantObject);
        result.put("Applicant", listApplicant);


        return result;
    }

//    public ApplicantMaster toMaster() {
//        ApplicantMaster master = new ApplicantMaster();
//        master.set_id(this.get_id());
//        master.setImportTask(this.getImportTask());
//        master.setForenames(this.getApplicantDemographic().getForenames());
//        master.setSurname(this.getApplicantDemographic().getSurname());
//        master.setGender(this.getGender());
//        master.setDateOfBirth(this.getDateOfBirth());
//        master.setProvinceId(this.getApplicantDemographic().getProvinceId());
//        master.setProvinceName(this.getApplicantDemographic().getProvinceName());
//        master.setDistrictId(this.getApplicantDemographic().getDistrictId());
//        master.setDistrictName(this.getApplicantDemographic().getDistrictName());
//        master.setConstituencyId(this.getApplicantDemographic().getConstituencyId());
//        master.setConstituencyName(this.getApplicantDemographic().getConstituencyName());
//        master.setRegistrationNumber(this.getRegistrationNumber());
//        master.setDateOfRegistration(this.getDateOfRegistration());
//        master.setDeviceName(this.getDeviceName());
//        master.setSortNumber(this.getSortNumber());
//        master.setOperatorGuid(this.getOperatorGuid());
//        master.setOperatorName(this.getOperatorName());
//        master.setBeginCreateDatetime(this.getBeginCreateDatetime());
//        master.setEndCreateDatetime(this.getEndCreateDatetime());
//        master.setStatus(0);
//        return master;
//    }

    public org.bson.Document toMasterDocument(ObjectId transactionId) {
        org.bson.Document dbObject = new org.bson.Document();

        dbObject.append("_id", this.get_id());
        dbObject.append("transactionId", transactionId);
        dbObject.append("importTask", this.getImportTask());

        dbObject.append("forenames", this.getApplicantDemographic().getForenames());
        dbObject.append("surname", this.getApplicantDemographic().getSurname());
        dbObject.append("gender", this.getGender());
        dbObject.append("dateOfBirth", this.getDateOfBirth());
        dbObject.append("provinceId", this.getApplicantDemographic().getProvinceId());
        dbObject.append("provinceName", this.getApplicantDemographic().getProvinceName());
        dbObject.append("districtId", this.getApplicantDemographic().getDistrictId());
        dbObject.append("districtName", this.getApplicantDemographic().getDistrictName());
        dbObject.append("constituencyId", this.getApplicantDemographic().getConstituencyId());
        dbObject.append("constituencyName", this.getApplicantDemographic().getConstituencyName());
        dbObject.append("registrationNumber", this.getRegistrationNumber());
        dbObject.append("dateOfRegistration", this.getDateOfRegistration());
        dbObject.append("deviceName", this.getDeviceName());
        dbObject.append("sortNumber", this.getSortNumber());
        dbObject.append("operatorGuid", this.getOperatorGuid());
        dbObject.append("operatorName", this.getOperatorName());
        dbObject.append("beginCreateDatetime", this.getBeginCreateDatetime());
        dbObject.append("endCreateDatetime", this.getEndCreateDatetime());
        dbObject.append("status", -1);
        return dbObject;
    }

    @XmlElement(name = "Id", required = false)
    private String _id;
    @XmlElement(name = "ImportTask", required = false)
    private String importTask;

    @XmlElement(name = "Gender", required = false)
    @XmlSchemaType(name = "byte")
    private byte gender;

    @XmlElement(name = "DateOfBirth", required = false)
    @XmlSchemaType(name = "int")
    private int dateOfBirth;

    @XmlElement(name = "DateOfRegistration", required = false)
    @XmlSchemaType(name = "int")
    private int dateOfRegistration;
    @XmlElement(name = "Status", required = false)
    @XmlSchemaType(name = "int")
    private int status;
    @XmlElement(name = "Guid", required = true)
    protected String guid;
    @XmlElement(name = "RegistrationNumber", required = true)
    protected String registrationNumber;
    @XmlElement(name = "SortNumber")
    @XmlSchemaType(name = "unsignedShort")
    protected short sortNumber;
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
    @XmlSchemaType(name = "int")
    protected int provinceId;
    @XmlElement(name = "ProvinceName", required = true)
    protected String provinceName;
    @XmlElement(name = "DistrictId")
    @XmlSchemaType(name = "int")
    protected int districtId;
    @XmlElement(name = "DistrictName", required = true)
    protected String districtName;
    @XmlElement(name = "ConstituencyId")
    @XmlSchemaType(name = "int")
    protected int constituencyId;
    @XmlElement(name = "ConstituencyName", required = true)
    protected String constituencyName;
    @XmlElement(name = "LocalAuthorityId")
    @XmlSchemaType(name = "int")
    private int localAuthorityId;
    @XmlElement(name = "LocalAuthorityName", required = true)
    private String localAuthorityName;
    @XmlElement(name = "WardId")
    @XmlSchemaType(name = "int")
    protected int wardId;
    @XmlElement(name = "WardName", required = true)
    protected String wardName;
    @XmlElement(name = "PollingStationId")
    @XmlSchemaType(name = "int")
    protected int pollingStationId;
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
    public short getSortNumber() {
        return sortNumber;
    }

    /**
     * 设置sortNumber属性的值。
     */
    public void setSortNumber(short value) {
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
    public int getProvinceId() {
        return provinceId;
    }

    /**
     * 设置provinceId属性的值。
     */
    public void setProvinceId(int value) {
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
    public int getDistrictId() {
        return districtId;
    }

    /**
     * 设置districtId属性的值。
     */
    public void setDistrictId(int value) {
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
    public int getConstituencyId() {
        return constituencyId;
    }

    /**
     * 设置constituencyId属性的值。
     */
    public void setConstituencyId(int value) {
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
    public int getWardId() {
        return wardId;
    }

    /**
     * 设置wardId属性的值。
     */
    public void setWardId(int value) {
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
    public int getPollingStationId() {
        return pollingStationId;
    }

    /**
     * 设置pollingStationId属性的值。
     */
    public void setPollingStationId(int value) {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public String getImportTask() {
        return importTask;
    }

    public void setImportTask(String importTask) {
        this.importTask = importTask;
    }

    public int getLocalAuthorityId() {
        return localAuthorityId;
    }

    public void setLocalAuthorityId(int localAuthorityId) {
        this.localAuthorityId = localAuthorityId;
    }

    public String getLocalAuthorityName() {
        return localAuthorityName;
    }

    public void setLocalAuthorityName(String localAuthorityName) {
        this.localAuthorityName = localAuthorityName;
    }

    public void merge(ApplicantMaster master) {
        if (master != null) {
            this.setDeviceName(master.getDeviceName());
            this.setSortNumber(master.getSortNumber());
            this.setDateOfBirth(master.getDateOfBirth());
            this.applicantDemographic.setForenames(master.getForenames());
            this.applicantDemographic.setSurname(master.getSurname());
            this.applicantDemographic.setProvinceId(master.getProvinceId());
            this.applicantDemographic.setProvinceName(master.getProvinceName());
            this.applicantDemographic.setDistrictId(master.getDistrictId());
            this.applicantDemographic.setDistrictName(master.getDistrictName());
            this.applicantDemographic.setConstituencyId(master.getConstituencyId());
            this.applicantDemographic.setConstituencyName(master.getConstituencyName());
            this.setGender(master.getGender());
            this.setOperatorGuid(master.getOperatorGuid());
            this.setOperatorName(master.getOperatorName());
            this.setBeginCreateDatetime(master.getBeginCreateDatetime());
            this.setEndCreateDatetime(master.getEndCreateDatetime());
            this.setStatus(master.getStatus());
            this.setDateOfRegistration(master.getDateOfRegistration());
            this.setRegistrationNumber(master.getRegistrationNumber());
        }
    }


    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "formElaspedTicks",
            "applicationFormContent",
            "idDocumentFormContent",
            "proofOfResidenceContent"
    })
    public static class ApplicantCompliance {
        @XmlElement(name = "FormElaspedTicks")
        @XmlSchemaType(name = "unsignedInt")
        protected long formElaspedTicks;
        @XmlElement(name = "ApplicationFormContent", required = true)
        protected String applicationFormContent;
        @XmlElement(name = "IDDocumentFormContent", required = true)
        protected String idDocumentFormContent;
        @XmlElement(name = "ProofOfResidenceContent", required = true)
        protected String proofOfResidenceContent;

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
         * 获取formElaspedTicks属性的值。
         */
        public long getFormElaspedTicks() {
            return formElaspedTicks;
        }

        /**
         * 设置formElaspedTicks属性的值。
         */
        public void setFormElaspedTicks(long value) {
            this.formElaspedTicks = value;
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
            "formElaspedTicks",
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
            "localAuthorityId",
            "localAuthorityName",
            "wardId",
            "wardName",
            "stationId",
            "stationName",
            "stationCode",
            "surburb",
            "town",
            "streetName",
            "standNumber",
            "disabilityCode",
            "disabilityName",
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
            "changeAddressLocalAuthorityId",
            "changeAddressLocalAuthorityName",
            "changeAddressWardId",
            "changeAddressWardName",
            "changeAddressStationId",
            "changeAddressStationName",
            "changeAddressStationCode",
            "changeAddressSurburb",
            "changeAddressStreetName",
            "changeAddressStandNumber",
            "changeAddressTown",
            "transferConstituencyProvinceId",
            "transferConstituencyProvinceName",
            "transferConstituencyDistrictId",
            "transferConstituencyDistrictName",
            "transferConstituencyConstituencyId",
            "transferConstituencyConstituencyName",
            "transferConstituencyLocalAuthorityId",
            "transferConstituencyLocalAuthorityName",
            "transferConstituencyWardId",
            "transferConstituencyWardName",
            "transferConstituencyStationId",
            "transferConstituencyStationName",
            "transferConstituencyStationCode",
            "transferConstituencySurburb",
            "transferConstituencyStreetName",
            "transferConstituencyStandNumber",
            "transferConstituencyTown"
    })
    public static class ApplicantDemographic {
        @XmlElement(name = "FormElaspedTicks")
        @XmlSchemaType(name = "unsignedInt")
        protected long formElaspedTicks;
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
        @XmlSchemaType(name = "int")
        protected int provinceId;
        @XmlElement(name = "ProvinceName", required = true)
        protected String provinceName;
        @XmlElement(name = "DistrictId")
        @XmlSchemaType(name = "unsignedInt")
        protected int districtId;
        @XmlElement(name = "DistrictName", required = true)
        protected String districtName;
        @XmlElement(name = "ConstituencyId")
        @XmlSchemaType(name = "int")
        protected int constituencyId;
        @XmlElement(name = "ConstituencyName", required = true)
        protected String constituencyName;
        @XmlSchemaType(name = "int")
        private int localAuthorityId;
        @XmlElement(name = "LocalAuthorityName", required = true)
        private String localAuthorityName;
        @XmlElement(name = "WardId")
        @XmlSchemaType(name = "int")
        protected int wardId;
        @XmlElement(name = "WardName", required = true)
        protected String wardName;
        @XmlElement(name = "StationId")
        @XmlSchemaType(name = "int")
        protected int stationId;
        @XmlElement(name = "StationName", required = true)
        protected String stationName;
        @XmlElement(name = "StationCode", required = true)
        protected String stationCode;
        @XmlElement(name = "Surburb", required = true)
        protected String surburb;
        @XmlElement(name = "Town", required = true)
        private String town;
        @XmlElement(name = "StreetName", required = true)
        protected String streetName;
        @XmlElement(name = "StandNumber", required = true)
        protected String standNumber;
        @XmlElement(name = "DisabilityCode", required = true)
        protected String disabilityCode;
        @XmlElement(name = "DisabilityName", required = true)
        protected String disabilityName;
        @XmlElement(name = "RegistrationType")
        @XmlSchemaType(name = "unsignedByte")
        protected short registrationType;
        @XmlElement(name = "PhoneNumber", required = true)
        protected String phoneNumber;
        @XmlElement(name = "Email", required = true, nillable = true)
        protected String email;
        @XmlElement(name = "GISLatitude")
        @XmlSchemaType(name = "unsignedInt")
        private long gisLatitude;
        @XmlElement(name = "GISLongitude")
        @XmlSchemaType(name = "unsignedInt")
        private long gisLongitude;
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
        @XmlElement(name = "ChangeAddressLocalAuthorityId", required = true, type = Integer.class, nillable = true)
        @XmlSchemaType(name = "unsignedShort")
        private Integer changeAddressLocalAuthorityId;
        @XmlElement(name = "ChangeAddressLocalAuthorityName", required = true, nillable = true)
        private String changeAddressLocalAuthorityName;
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
        @XmlElement(name = "ChangeAddressTown", required = true, nillable = true)
        private String changeAddressTown;
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
        @XmlElement(name = "TransferConstituencyLocalAuthorityId", required = true, type = Short.class, nillable = true)
        @XmlSchemaType(name = "unsignedByte")
        private Short transferConstituencyLocalAuthorityId;
        @XmlElement(name = "TransferConstituencyLocalAuthorityName", required = true, nillable = true)
        private String transferConstituencyLocalAuthorityName;
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
        @XmlElement(name = "TransferConstituencyTown", required = true, nillable = true)
        private String transferConstituencyTown;

        /**
         * 获取formElaspedTicks属性的值。
         */
        public long getFormElaspedTicks() {
            return formElaspedTicks;
        }

        /**
         * 设置formElaspedTicks属性的值。
         */
        public void setFormElaspedTicks(long value) {
            this.formElaspedTicks = value;
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
        public int getProvinceId() {
            return provinceId;
        }

        /**
         * 设置provinceId属性的值。
         */
        public void setProvinceId(int value) {
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
        public int getDistrictId() {
            return districtId;
        }

        /**
         * 设置districtId属性的值。
         */
        public void setDistrictId(int value) {
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
        public int getConstituencyId() {
            return constituencyId;
        }

        /**
         * 设置constituencyId属性的值。
         */
        public void setConstituencyId(int value) {
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
        public int getWardId() {
            return wardId;
        }

        /**
         * 设置wardId属性的值。
         */
        public void setWardId(int value) {
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
        public int getStationId() {
            return stationId;
        }

        /**
         * 设置stationId属性的值。
         */
        public void setStationId(int value) {
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
         * 获取disabilityCode属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getDisabilityCode() {
            return disabilityCode;
        }

        /**
         * 设置disabilityCode属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setDisabilityCode(String value) {
            this.disabilityCode = value;
        }

        /**
         * 获取disabilityName属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getDisabilityName() {
            return disabilityName;
        }

        /**
         * 设置disabilityName属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setDisabilityName(String value) {
            this.disabilityName = value;
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

        public String getTown() {
            return town;
        }

        public void setTown(String town) {
            this.town = town;
        }

        public long getGisLatitude() {
            return gisLatitude;
        }

        public void setGisLatitude(long gisLatitude) {
            this.gisLatitude = gisLatitude;
        }

        public long getGisLongitude() {
            return gisLongitude;
        }

        public void setGisLongitude(long gisLongitude) {
            this.gisLongitude = gisLongitude;
        }

        public Integer getChangeAddressLocalAuthorityId() {
            return changeAddressLocalAuthorityId;
        }

        public void setChangeAddressLocalAuthorityId(Integer changeAddressLocalAuthorityId) {
            this.changeAddressLocalAuthorityId = changeAddressLocalAuthorityId;
        }

        public String getChangeAddressLocalAuthorityName() {
            return changeAddressLocalAuthorityName;
        }

        public void setChangeAddressLocalAuthorityName(String changeAddressLocalAuthorityName) {
            this.changeAddressLocalAuthorityName = changeAddressLocalAuthorityName;
        }

        public String getChangeAddressTown() {
            return changeAddressTown;
        }

        public void setChangeAddressTown(String changeAddressTown) {
            this.changeAddressTown = changeAddressTown;
        }

        public Short getTransferConstituencyLocalAuthorityId() {
            return transferConstituencyLocalAuthorityId;
        }

        public void setTransferConstituencyLocalAuthorityId(Short transferConstituencyLocalAuthorityId) {
            this.transferConstituencyLocalAuthorityId = transferConstituencyLocalAuthorityId;
        }

        public String getTransferConstituencyLocalAuthorityName() {
            return transferConstituencyLocalAuthorityName;
        }

        public void setTransferConstituencyLocalAuthorityName(String transferConstituencyLocalAuthorityName) {
            this.transferConstituencyLocalAuthorityName = transferConstituencyLocalAuthorityName;
        }

        public String getTransferConstituencyTown() {
            return transferConstituencyTown;
        }

        public void setTransferConstituencyTown(String transferConstituencyTown) {
            this.transferConstituencyTown = transferConstituencyTown;
        }

        public int getLocalAuthorityId() {
            return localAuthorityId;
        }

        public void setLocalAuthorityId(int localAuthorityId) {
            this.localAuthorityId = localAuthorityId;
        }

        public String getLocalAuthorityName() {
            return localAuthorityName;
        }

        public void setLocalAuthorityName(String localAuthorityName) {
            this.localAuthorityName = localAuthorityName;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "formElaspedTicks",
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
            "leftThumbAFISTemplate",
            "leftIndexAFISTemplate",
            "leftMiddleAFISTemplate",
            "leftRingAFISTemplate",
            "leftLittleAFISTemplate",
            "rightThumbAFISTemplate",
            "rightIndexAFISTemplate",
            "rightMiddleAFISTemplate",
            "rightRingAFISTemplate",
            "rightLittleAFISTemplate",
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
        @XmlElement(name = "FormElaspedTicks")
        @XmlSchemaType(name = "unsignedInt")
        protected long formElaspedTicks;
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
        @XmlElement(name = "LeftThumbAFISTemplate", required = true, nillable = true)
        protected String leftThumbAFISTemplate;
        @XmlElement(name = "LeftIndexAFISTemplate", required = true, nillable = true)
        protected String leftIndexAFISTemplate;
        @XmlElement(name = "LeftMiddleAFISTemplate", required = true, nillable = true)
        protected String leftMiddleAFISTemplate;
        @XmlElement(name = "LeftRingAFISTemplate", required = true, nillable = true)
        protected String leftRingAFISTemplate;
        @XmlElement(name = "LeftLittleAFISTemplate", required = true, nillable = true)
        protected String leftLittleAFISTemplate;
        @XmlElement(name = "RightThumbAFISTemplate", required = true, nillable = true)
        protected String rightThumbAFISTemplate;
        @XmlElement(name = "RightIndexAFISTemplate", required = true, nillable = true)
        protected String rightIndexAFISTemplate;
        @XmlElement(name = "RightMiddleAFISTemplate", required = true, nillable = true)
        protected String rightMiddleAFISTemplate;
        @XmlElement(name = "RightRingAFISTemplate", required = true, nillable = true)
        protected String rightRingAFISTemplate;
        @XmlElement(name = "RightLittleAFISTemplate", required = true, nillable = true)
        protected String rightLittleAFISTemplate;
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
         * 获取formElaspedTicks属性的值。
         */
        public long getFormElaspedTicks() {
            return formElaspedTicks;
        }

        /**
         * 设置formElaspedTicks属性的值。
         */
        public void setFormElaspedTicks(long value) {
            this.formElaspedTicks = value;
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
         * 获取leftThumbAFISTemplate属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getLeftThumbAFISTemplate() {
            return leftThumbAFISTemplate;
        }

        /**
         * 设置leftThumbAFISTemplate属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setLeftThumbAFISTemplate(String value) {
            this.leftThumbAFISTemplate = value;
        }

        /**
         * 获取leftIndexAFISTemplate属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getLeftIndexAFISTemplate() {
            return leftIndexAFISTemplate;
        }

        /**
         * 设置leftIndexAFISTemplate属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setLeftIndexAFISTemplate(String value) {
            this.leftIndexAFISTemplate = value;
        }

        /**
         * 获取leftMiddleAFISTemplate属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getLeftMiddleAFISTemplate() {
            return leftMiddleAFISTemplate;
        }

        /**
         * 设置leftMiddleAFISTemplate属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setLeftMiddleAFISTemplate(String value) {
            this.leftMiddleAFISTemplate = value;
        }

        /**
         * 获取leftRingAFISTemplate属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getLeftRingAFISTemplate() {
            return leftRingAFISTemplate;
        }

        /**
         * 设置leftRingAFISTemplate属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setLeftRingAFISTemplate(String value) {
            this.leftRingAFISTemplate = value;
        }

        /**
         * 获取leftLittleAFISTemplate属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getLeftLittleAFISTemplate() {
            return leftLittleAFISTemplate;
        }

        /**
         * 设置leftLittleAFISTemplate属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setLeftLittleAFISTemplate(String value) {
            this.leftLittleAFISTemplate = value;
        }

        /**
         * 获取rightThumbAFISTemplate属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getRightThumbAFISTemplate() {
            return rightThumbAFISTemplate;
        }

        /**
         * 设置rightThumbAFISTemplate属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setRightThumbAFISTemplate(String value) {
            this.rightThumbAFISTemplate = value;
        }

        /**
         * 获取rightIndexAFISTemplate属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getRightIndexAFISTemplate() {
            return rightIndexAFISTemplate;
        }

        /**
         * 设置rightIndexAFISTemplate属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setRightIndexAFISTemplate(String value) {
            this.rightIndexAFISTemplate = value;
        }

        /**
         * 获取rightMiddleAFISTemplate属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getRightMiddleAFISTemplate() {
            return rightMiddleAFISTemplate;
        }

        /**
         * 设置rightMiddleAFISTemplate属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setRightMiddleAFISTemplate(String value) {
            this.rightMiddleAFISTemplate = value;
        }

        /**
         * 获取rightRingAFISTemplate属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getRightRingAFISTemplate() {
            return rightRingAFISTemplate;
        }

        /**
         * 设置rightRingAFISTemplate属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setRightRingAFISTemplate(String value) {
            this.rightRingAFISTemplate = value;
        }

        /**
         * 获取rightLittleAFISTemplate属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getRightLittleAFISTemplate() {
            return rightLittleAFISTemplate;
        }

        /**
         * 设置rightLittleAFISTemplate属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setRightLittleAFISTemplate(String value) {
            this.rightLittleAFISTemplate = value;
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

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "formElaspedTicks",
            "photoArray",
            "thumbnail"
    })
    public static class ApplicantPhoto {

        @XmlElement(name = "FormElaspedTicks")
        @XmlSchemaType(name = "unsignedInt")
        protected long formElaspedTicks;
        @XmlElement(name = "PhotoArray", required = true)
        protected String photoArray;
        @XmlElement(name = "Thumbnail", required = true)
        protected String thumbnail;


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

        /**
         * 获取formElaspedTicks属性的值。
         */
        public long getFormElaspedTicks() {
            return formElaspedTicks;
        }

        /**
         * 设置formElaspedTicks属性的值。
         */
        public void setFormElaspedTicks(long value) {
            this.formElaspedTicks = value;
        }


    }

    public org.bson.Document toTestApplicantMaster(String fistName, ObjectId transactionId,
                                                   String lastName, String birth,
                                                   String gender, String rid,
                                                   String tstId, short sorNum) {
        org.bson.Document result = new org.bson.Document();


        result.append("_id", "testdata-" + rid);
        result.append("gender", gender.equals("Male") ? (byte) 1 : (byte) 2);
        result.append("dateOfBirth", Integer.valueOf(birth.replaceAll("-", "").replace(" 00:00:00", "")));
        result.append("status", (byte) -1);

        result.append("registrationNumber", tstId);

        String dvcName = String.format("%04d", ((int) Math.floor(Integer.valueOf(rid) / 4000)) + 1);

        String deviceName = "MW" + dvcName;

        result.append("sortNumber", sorNum);

        result.append("beginCreateDatetime", new Date());
        result.append("endCreateDatetime", new Date());
        result.append("transactionId", transactionId);
        result.append("forenames", fistName);

        result.append("surname", lastName);
        int pid = getRandomProvinceId();
        String pName = getTestProvinceName(pid);

        result.append("provinceId", pid);
        result.append("provinceName", pName);

        int did = getRandomDistrictId(pid);
        String dName = getRandomDistrictName(did);

        result.append("districtId", did);
        result.append("districtName", dName);

        int cid = getRandomConstituencyId(did);
        String cName = getRandomConstituencyName(cid);

        result.append("constituencyId", cid);
        result.append("constituencyName", cName);
        result.append("deviceName", deviceName);
        result.append("operatorGuid", this.getOperatorGuid());
        result.append("operatorName", this.getOperatorName());

        result.append("dateOfRegistration", this.getDateOfRegistration());

        return result;
    }

    private String getTestProvinceName(int pid) {
        String[] strArray = {"BULAWAYO METROPOLITAN", "HARARE METROPOLITAN", "MANICALAND", "MASHONALAND CENTRAL", "MASHONALAND EAST",
                "MASHONALAND WEST", "MASVINGO", "MATEBELELAND NORTH", "MIDLANDS", "MATABELELAND SOUTH"};
        return strArray[pid - 1];
    }

    private int getRandomProvinceId() {
        Random ra = new Random();
        return ra.nextInt(10) + 1;
    }

    private int getRandomDistrictId(int pid) {
        int did = 0;
        switch (pid) {
            case 1:
                did = 11;
                break;
            case 2:
                int[] didArr2 = {12, 13};
                did = didArr2[getRandomIndex(2)];
                break;
            case 3:
                int[] didArr3 = {14, 15};
                did = didArr3[getRandomIndex(2)];
                break;
            case 4:
                int[] didArr4 = {16, 17};
                did = didArr4[getRandomIndex(2)];
                break;
            case 5:
                int[] didArr5 = {18, 19, 20};
                did = didArr5[getRandomIndex(3)];
                break;
            case 6:
                did = 21;
                break;
            case 7:
                int[] didArr7 = {22, 23, 24, 25, 26, 27};
                did = didArr7[getRandomIndex(6)];
                break;
            case 8:
                int[] didArr8 = {28, 29, 30};
                did = didArr8[getRandomIndex(3)];
                break;
            case 9:
                int[] didArr9 = {31, 32};
                did = didArr9[getRandomIndex(2)];
                break;
            case 10:
                did = 33;
                break;
        }
        return did;
    }

    private String getRandomDistrictName(int did) {

        Map<Integer, String> districtMap = new HashMap<>();
        districtMap.put(11, "BULAWAYO");
        districtMap.put(12, "CHITUNGWIZA");
        districtMap.put(13, "HARARE");
        districtMap.put(14, "BUHERA");
        districtMap.put(15, "CHIMANIMANI ");
        districtMap.put(16, "GURUVE");
        districtMap.put(17, "MAZOWE");
        districtMap.put(18, "GOROMONZI");
        districtMap.put(19, "MARONDERA");
        districtMap.put(20, "MUREWA");
        districtMap.put(21, "CHEGUTU");
        districtMap.put(22, "BIKITA");
        districtMap.put(23, "CHIREDZI");
        districtMap.put(24, "GUTU");
        districtMap.put(25, "MASVINGO    ");
        districtMap.put(26, "MWENEZI");
        districtMap.put(27, "ZAKA");
        districtMap.put(28, "BINGA");
        districtMap.put(29, "BUBI");
        districtMap.put(30, "TSHOLOTSHO");
        districtMap.put(31, "GWERU");
        districtMap.put(32, "KWEKWE");
        districtMap.put(33, "INSIZA");
        return districtMap.get(did);
    }

    private int getRandomConstituencyId(int did) {
        int cid = 0;
        switch (did) {
            case 11:
                cid = 34;
                break;
            case 12:
                cid = 35;
                break;
            case 13:

                cid = 36;
                break;
            case 14:

                cid = 37;
                break;
            case 15:

                cid = 38;
                break;
            case 16:
                cid = 39;
                break;
            case 17:
                int[] didArr17 = {40, 41};
                cid = didArr17[getRandomIndex(2)];
                break;
            case 18:

                cid = 42;
                break;
            case 19:

                cid = 43;
                break;
            case 20:
                cid = 44;
                break;
            case 21:
                int[] didArr21 = {45, 46, 47};
                cid = didArr21[getRandomIndex(3)];
                break;
            case 22:
                cid = 48;
                break;
            case 23:
                cid = 49;
                break;
            case 24:
                int[] didArr24 = {50, 51};
                cid = didArr24[getRandomIndex(2)];
                break;
            case 25:
                cid = 52;
                break;
            case 26:
                cid = 53;
                break;
            case 27:
                cid = 54;
                break;
            case 28:
                cid = 55;
                break;
            case 29:
                cid = 56;
                break;
            case 30:
                cid = 57;
                break;
            case 31:
                int[] didArr31 = {58, 59, 60};
                cid = didArr31[getRandomIndex(2)];
                break;
            case 32:
                cid = 61;
                break;
            case 33:
                int[] didArr33 = {62, 63};
                cid = didArr33[getRandomIndex(2)];
                break;

        }
        return cid;
    }

    private String getRandomConstituencyName(int did) {

        Map<Integer, String> ConstituencyMap = new HashMap<>();

        ConstituencyMap.put(34, "NKULUMANE");
        ConstituencyMap.put(35, "ZENGEZA EAST");
        ConstituencyMap.put(36, "GLEN VIEW NORTH");
        ConstituencyMap.put(37, "BUHERA WEST");
        ConstituencyMap.put(38, "CHIMANIMANI WEST");
        ConstituencyMap.put(39, "GURUVE SOUTH");
        ConstituencyMap.put(40, "MAZOWE NORTH");
        ConstituencyMap.put(41, "MAZOWE SOUTH");
        ConstituencyMap.put(42, "GOROMONZI SOUTH");
        ConstituencyMap.put(43, "MARONDERA CENTRAL");
        ConstituencyMap.put(44, "MUREWA SOUTH");
        ConstituencyMap.put(45, "CHEGUTU WEST    ");
        ConstituencyMap.put(46, "MHONDORO-MUBAIRA");
        ConstituencyMap.put(47, "NORTON");
        ConstituencyMap.put(48, "BIKITA WEST");
        ConstituencyMap.put(49, "CHIREDZI WEST");
        ConstituencyMap.put(50, "GUTU CENTRAL");
        ConstituencyMap.put(51, "GUTU WEST");
        ConstituencyMap.put(52, "MASVINGO WEST");
        ConstituencyMap.put(53, "MWENEZI EAST");
        ConstituencyMap.put(54, "ZAKA NORTH");
        ConstituencyMap.put(55, "BINGA NORTH ");
        ConstituencyMap.put(56, "BUBI");
        ConstituencyMap.put(57, "TSHOLOTSHO SOUTH");
        ConstituencyMap.put(58, "CHIWUNDURA");
        ConstituencyMap.put(59, "MKOBA");
        ConstituencyMap.put(60, "VUNGU");
        ConstituencyMap.put(61, "MBIZO");
        ConstituencyMap.put(62, "INSIZA NORTH");
        ConstituencyMap.put(63, "INSIZA SOUTH");
        return ConstituencyMap.get(did);
    }

    private int getRandomIndex(int seed) {
        Random ra = new Random();
        return ra.nextInt(seed);
    }

    public org.bson.Document toTestApplicantDBObject(String rid, ObjectId transactionId, MongoDao mongoDao) {
        org.bson.Document dbObject = new org.bson.Document();
        dbObject.append("transactionId", transactionId);
        dbObject.append("_id", "testdata-" + rid);
        dbObject.append("beginEditDatetime", this.getBeginEditDatetime());
        dbObject.append("endEditDatetime", this.getEndEditDatetime());
        dbObject.append("provinceId", this.getProvinceId());
        dbObject.append("provinceName", this.getProvinceName());
        dbObject.append("districtId", this.getDistrictId());
        dbObject.append("districtName", this.getDistrictName());
        dbObject.append("constituencyId", this.getConstituencyId());
        dbObject.append("constituencyName", this.getConstituencyName());
        dbObject.append("localAuthorityId", this.getLocalAuthorityId());
        dbObject.append("localAuthorityName", this.getLocalAuthorityName());
        dbObject.append("wardId", this.getWardId());
        dbObject.append("wardName", this.getWardName());
        dbObject.append("pollingStationId", this.getPollingStationId());
        dbObject.append("pollingStationCode", this.getPollingStationCode());
        dbObject.append("pollingStationName", this.getPollingStationName());
        dbObject.append("saveDatetime", this.getSaveDatetime());
        dbObject.append("editSaveDatetime", this.getEditSaveDatetime());

        dbObject.append("underDuress", this.isUnderDuress());
        dbObject.append("exportDatetime", this.getExportDatetime());
        dbObject.append("importToServerDatetime", this.getImportToServerDatetime());

        org.bson.Document applicantCompliance = new org.bson.Document();

        applicantCompliance.append("formElaspedTicks", this.getApplicantCompliance().getFormElaspedTicks());
        String applicationFormContentStr = null;
        if (this.getApplicantCompliance().getApplicationFormContent() != null && this.getApplicantCompliance().getApplicationFormContent().length() > 0) {
            applicationFormContentStr = mongoDao.insertImg(this.getApplicantCompliance().getApplicationFormContent().getBytes(), "CompliancePhoto");
        }
        applicantCompliance.append("applicationFormContent", applicationFormContentStr);

        String idDocumentFormContentStr = null;
        if (this.getApplicantCompliance().getIDDocumentFormContent() != null && this.getApplicantCompliance().getIDDocumentFormContent().length() > 0) {
            idDocumentFormContentStr = mongoDao.insertImg(this.getApplicantCompliance().getIDDocumentFormContent().getBytes(), "CompliancePhoto");
        }
        applicantCompliance.append("idDocumentFormContent", idDocumentFormContentStr);

        String proofOfResidenceContentStr = null;
        if (this.getApplicantCompliance().getProofOfResidenceContent() != null && this.getApplicantCompliance().getProofOfResidenceContent().length() > 0) {
            proofOfResidenceContentStr = mongoDao.insertImg(this.getApplicantCompliance().getProofOfResidenceContent().getBytes(), "CompliancePhoto");
        }
        applicantCompliance.append("proofOfResidenceContent", proofOfResidenceContentStr);

        dbObject.append("applicantCompliance", applicantCompliance);

        org.bson.Document applicantDemographic = new org.bson.Document();

        applicantDemographic.append("formElaspedTicks", this.getApplicantDemographic().getFormElaspedTicks());
        applicantDemographic.append("idNumber", this.getApplicantDemographic().getIdNumber());
        applicantDemographic.append("dateOfBirth", this.getApplicantDemographic().getDateOfBirth());
        applicantDemographic.append("dateOfBirthText", this.getApplicantDemographic().getDateOfBirthText());
        applicantDemographic.put("gender", this.getApplicantDemographic().getGender());

        applicantDemographic.append("localAuthorityId", this.getApplicantDemographic().getLocalAuthorityId());
        applicantDemographic.append("localAuthorityName", this.getApplicantDemographic().getLocalAuthorityName());
        applicantDemographic.append("wardId", this.getApplicantDemographic().getWardId());
        applicantDemographic.append("wardName", this.getApplicantDemographic().getWardName());
        applicantDemographic.append("stationId", this.getApplicantDemographic().getStationId());
        applicantDemographic.append("stationName", this.getApplicantDemographic().getStationName());
        applicantDemographic.append("stationCode", this.getApplicantDemographic().getStationCode());
        applicantDemographic.append("surburb", this.getApplicantDemographic().getSurburb());
        applicantDemographic.append("town", this.getApplicantDemographic().getTown());
        applicantDemographic.append("streetName", this.getApplicantDemographic().getStreetName());
        applicantDemographic.append("standNumber", this.getApplicantDemographic().getStandNumber());
        applicantDemographic.append("disabilityCode", this.getApplicantDemographic().getDisabilityCode());
        applicantDemographic.append("disabilityName", this.getApplicantDemographic().getDisabilityName());
        applicantDemographic.append("registrationType", this.getApplicantDemographic().getRegistrationType());
        applicantDemographic.append("phoneNumber", this.getApplicantDemographic().getPhoneNumber());
        applicantDemographic.append("email", this.getApplicantDemographic().getEmail());
        applicantDemographic.append("gisLatitude", this.getApplicantDemographic().getGisLatitude());
        applicantDemographic.append("gisLongitude", this.getApplicantDemographic().getGisLongitude());
        applicantDemographic.append("changeAddressProvinceId", this.getApplicantDemographic().getChangeAddressProvinceId());
        applicantDemographic.append("changeAddressProvinceName", this.getApplicantDemographic().getChangeAddressProvinceName());
        applicantDemographic.append("changeAddressDistrictId", this.getApplicantDemographic().getChangeAddressDistrictId());
        applicantDemographic.append("changeAddressDistrictName", this.getApplicantDemographic().getChangeAddressDistrictName());
        applicantDemographic.append("changeAddressConstituencyId", this.getApplicantDemographic().getChangeAddressConstituencyId());
        applicantDemographic.append("changeAddressConstituencyName", this.getApplicantDemographic().getChangeAddressConstituencyName());
        applicantDemographic.append("changeAddressLocalAuthorityId", this.getApplicantDemographic().getChangeAddressLocalAuthorityId());
        applicantDemographic.append("changeAddressLocalAuthorityName", this.getApplicantDemographic().getChangeAddressLocalAuthorityName());
        applicantDemographic.append("changeAddressWardId", this.getApplicantDemographic().getChangeAddressWardId());
        applicantDemographic.append("changeAddressWardName", this.getApplicantDemographic().getChangeAddressWardName());
        applicantDemographic.append("changeAddressStationId", this.getApplicantDemographic().getChangeAddressStationId());
        applicantDemographic.append("changeAddressStationName", this.getApplicantDemographic().getChangeAddressStationName());
        applicantDemographic.append("changeAddressStationCode", this.getApplicantDemographic().getChangeAddressStationCode());
        applicantDemographic.append("changeAddressSurburb", this.getApplicantDemographic().getChangeAddressSurburb());
        applicantDemographic.append("changeAddressTown", this.getApplicantDemographic().getChangeAddressTown());
        applicantDemographic.append("changeAddressStreetName", this.getApplicantDemographic().getChangeAddressStreetName());
        applicantDemographic.append("changeAddressStandNumber", this.getApplicantDemographic().getChangeAddressStandNumber());
        applicantDemographic.append("transferConstituencyProvinceId", this.getApplicantDemographic().getTransferConstituencyProvinceId());
        applicantDemographic.append("transferConstituencyProvinceName", this.getApplicantDemographic().getTransferConstituencyProvinceName());
        applicantDemographic.append("transferConstituencyDistrictId", this.getApplicantDemographic().getTransferConstituencyDistrictId());
        applicantDemographic.append("transferConstituencyDistrictName", this.getApplicantDemographic().getTransferConstituencyDistrictName());
        applicantDemographic.append("transferConstituencyConstituencyId", this.getApplicantDemographic().getTransferConstituencyConstituencyId());
        applicantDemographic.append("transferConstituencyConstituencyName", this.getApplicantDemographic().getTransferConstituencyConstituencyName());
        applicantDemographic.append("transferConstituencyLocalAuthorityId", this.getApplicantDemographic().getTransferConstituencyLocalAuthorityId());
        applicantDemographic.append("transferConstituencyLocalAuthorityName", this.getApplicantDemographic().getTransferConstituencyLocalAuthorityName());

        applicantDemographic.append("transferConstituencyWardId", this.getApplicantDemographic().getTransferConstituencyWardId());
        applicantDemographic.append("transferConstituencyWardName", this.getApplicantDemographic().getTransferConstituencyWardName());
        applicantDemographic.append("transferConstituencyStationId", this.getApplicantDemographic().getTransferConstituencyStationId());
        applicantDemographic.append("transferConstituencyStationName", this.getApplicantDemographic().getTransferConstituencyStationName());
        applicantDemographic.append("transferConstituencyStationCode", this.getApplicantDemographic().getTransferConstituencyStationCode());
        applicantDemographic.append("transferConstituencySurburb", this.getApplicantDemographic().getTransferConstituencySurburb());
        applicantDemographic.append("transferConstituencyTown", this.getApplicantDemographic().getTransferConstituencyTown());


        applicantDemographic.append("transferConstituencyStreetName", this.getApplicantDemographic().getTransferConstituencyStreetName());
        applicantDemographic.append("transferConstituencyStandNumber", this.getApplicantDemographic().getTransferConstituencyStandNumber());
        dbObject.put("applicantDemographic", applicantDemographic);

        org.bson.Document applicantFingerprint = new org.bson.Document();
        applicantFingerprint.append("formElaspedTicks", this.getApplicantFingerprint().getFormElaspedTicks());


        String leftThumbImageArrayStr = null;
        if (this.getApplicantFingerprint().getLeftThumbImageArray() != null && this.getApplicantFingerprint().getLeftThumbImageArray().length() > 0) {
            leftThumbImageArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getLeftThumbImageArray().getBytes(), "FingerprintImage");
        }
        applicantFingerprint.append("leftThumbImageArray", leftThumbImageArrayStr);

        String leftIndexImageArrayStr = null;
        if (this.getApplicantFingerprint().getLeftIndexImageArray() != null && this.getApplicantFingerprint().getLeftIndexImageArray().length() > 0) {
            leftIndexImageArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getLeftIndexImageArray().getBytes(), "FingerprintImage");
        }
        applicantFingerprint.append("leftIndexImageArray", leftIndexImageArrayStr);

        String leftMiddleImageArrayStr = null;
        if (this.getApplicantFingerprint().getLeftMiddleImageArray() != null && this.getApplicantFingerprint().getLeftMiddleImageArray().length() > 0) {
            leftMiddleImageArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getLeftMiddleImageArray().getBytes(), "FingerprintImage");
        }
        applicantFingerprint.append("leftMiddleImageArray", leftMiddleImageArrayStr);

        String leftRingImageArrayStr = null;
        if (this.getApplicantFingerprint().getLeftRingImageArray() != null && this.getApplicantFingerprint().getLeftRingImageArray().length() > 0) {
            leftRingImageArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getLeftRingImageArray().getBytes(), "FingerprintImage");
        }
        applicantFingerprint.append("leftRingImageArray", leftRingImageArrayStr);

        String leftLittleImageArrayStr = null;
        if (this.getApplicantFingerprint().getLeftLittleImageArray() != null && this.getApplicantFingerprint().getLeftLittleImageArray().length() > 0) {
            leftLittleImageArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getLeftLittleImageArray().getBytes(), "FingerprintImage");
        }
        applicantFingerprint.append("leftLittleImageArray", leftLittleImageArrayStr);

        String rightThumbImageArrayStr = null;
        if (this.getApplicantFingerprint().getRightThumbImageArray() != null && this.getApplicantFingerprint().getRightThumbImageArray().length() > 0) {
            rightThumbImageArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getRightThumbImageArray().getBytes(), "FingerprintImage");
        }
        applicantFingerprint.append("rightThumbImageArray", rightThumbImageArrayStr);

        String rightIndexImageArrayStr = null;
        if (this.getApplicantFingerprint().getRightIndexImageArray() != null && this.getApplicantFingerprint().getRightIndexImageArray().length() > 0) {
            rightIndexImageArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getRightIndexImageArray().getBytes(), "FingerprintImage");
        }
        applicantFingerprint.append("rightIndexImageArray", rightIndexImageArrayStr);

        String rightMiddleImageArrayStr = null;
        if (this.getApplicantFingerprint().getRightMiddleImageArray() != null && this.getApplicantFingerprint().getRightMiddleImageArray().length() > 0) {
            rightMiddleImageArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getRightMiddleImageArray().getBytes(), "FingerprintImage");
        }
        applicantFingerprint.append("rightMiddleImageArray", rightMiddleImageArrayStr);

        String rightRingImageArrayStr = null;
        if (this.getApplicantFingerprint().getRightRingImageArray() != null && this.getApplicantFingerprint().getRightRingImageArray().length() > 0) {
            rightRingImageArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getRightRingImageArray().getBytes(), "FingerprintImage");
        }
        applicantFingerprint.append("rightRingImageArray", rightRingImageArrayStr);

        String rightLittleImageArrayStr = null;
        if (this.getApplicantFingerprint().getRightLittleImageArray() != null && this.getApplicantFingerprint().getRightLittleImageArray().length() > 0) {
            rightLittleImageArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getRightLittleImageArray().getBytes(), "FingerprintImage");
        }
        applicantFingerprint.append("rightLittleImageArray", rightLittleImageArrayStr);

        String leftThumbWSQArrayStr = null;
        if (this.getApplicantFingerprint().getLeftThumbWSQArray() != null && this.getApplicantFingerprint().getLeftThumbWSQArray().length() > 0) {
            leftThumbWSQArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getLeftThumbWSQArray().getBytes(), "FingerprintWSQ");
        }
        applicantFingerprint.append("leftThumbWSQArray", leftThumbWSQArrayStr);

        String leftIndexWSQArrayStr = null;
        if (this.getApplicantFingerprint().getLeftIndexWSQArray() != null && this.getApplicantFingerprint().getLeftIndexWSQArray().length() > 0) {
            leftIndexWSQArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getLeftIndexWSQArray().getBytes(), "FingerprintWSQ");
        }
        applicantFingerprint.append("leftIndexWSQArray", leftIndexWSQArrayStr);

        String leftMiddleWSQArrayStr = null;
        if (this.getApplicantFingerprint().getLeftMiddleWSQArray() != null && this.getApplicantFingerprint().getLeftMiddleWSQArray().length() > 0) {
            leftMiddleWSQArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getLeftMiddleWSQArray().getBytes(), "FingerprintWSQ");
        }
        applicantFingerprint.append("leftMiddleWSQArray", leftMiddleWSQArrayStr);

        String leftRingWSQArrayStr = null;
        if (this.getApplicantFingerprint().getLeftRingWSQArray() != null && this.getApplicantFingerprint().getLeftRingWSQArray().length() > 0) {
            leftRingWSQArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getLeftRingWSQArray().getBytes(), "FingerprintWSQ");
        }
        applicantFingerprint.append("leftRingWSQArray", leftRingWSQArrayStr);

        String leftLittleWSQArrayStr = null;
        if (this.getApplicantFingerprint().getLeftLittleWSQArray() != null && this.getApplicantFingerprint().getLeftLittleWSQArray().length() > 0) {
            leftLittleWSQArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getLeftLittleWSQArray().getBytes(), "FingerprintWSQ");
        }
        applicantFingerprint.append("leftLittleWSQArray", leftLittleWSQArrayStr);

        String rightThumbWSQArrayStr = null;
        if (this.getApplicantFingerprint().getRightThumbWSQArray() != null && this.getApplicantFingerprint().getRightThumbWSQArray().length() > 0) {
            rightThumbWSQArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getRightThumbWSQArray().getBytes(), "FingerprintWSQ");
        }
        applicantFingerprint.append("rightThumbWSQArray", rightThumbWSQArrayStr);

        String rightIndexWSQArrayStr = null;
        if (this.getApplicantFingerprint().getRightIndexWSQArray() != null && this.getApplicantFingerprint().getRightIndexWSQArray().length() > 0) {
            rightIndexWSQArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getRightIndexWSQArray().getBytes(), "FingerprintWSQ");
        }
        applicantFingerprint.append("rightIndexWSQArray", rightIndexWSQArrayStr);

        String rightMiddleWSQArrayStr = null;
        if (this.getApplicantFingerprint().getRightMiddleWSQArray() != null && this.getApplicantFingerprint().getRightMiddleWSQArray().length() > 0) {
            rightMiddleWSQArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getRightMiddleWSQArray().getBytes(), "FingerprintWSQ");
        }
        applicantFingerprint.append("rightMiddleWSQArray", rightMiddleWSQArrayStr);

        String rightRingWSQArrayStr = null;
        if (this.getApplicantFingerprint().getRightRingWSQArray() != null && this.getApplicantFingerprint().getRightRingWSQArray().length() > 0) {
            rightRingWSQArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getRightRingWSQArray().getBytes(), "FingerprintWSQ");
        }
        applicantFingerprint.append("rightRingWSQArray", rightRingWSQArrayStr);

        String rightLittleWSQArrayStr = null;
        if (this.getApplicantFingerprint().getRightLittleWSQArray() != null && this.getApplicantFingerprint().getRightLittleWSQArray().length() > 0) {
            rightLittleWSQArrayStr = mongoDao.insertImg(this.getApplicantFingerprint().getRightLittleWSQArray().getBytes(), "FingerprintWSQ");
        }
        applicantFingerprint.append("rightLittleWSQArray", rightLittleWSQArrayStr);
        applicantFingerprint.append("leftThumbScore", this.getApplicantFingerprint().getLeftThumbScore());
        applicantFingerprint.append("leftIndexScore", this.getApplicantFingerprint().getLeftIndexScore());
        applicantFingerprint.append("leftMiddleScore", this.getApplicantFingerprint().getLeftMiddleScore());
        applicantFingerprint.append("leftRingScore", this.getApplicantFingerprint().getLeftRingScore());
        applicantFingerprint.append("leftLittleScore", this.getApplicantFingerprint().getLeftLittleScore());
        applicantFingerprint.append("rightThumbScore", this.getApplicantFingerprint().getRightThumbScore());
        applicantFingerprint.append("rightIndexScore", this.getApplicantFingerprint().getRightIndexScore());
        applicantFingerprint.append("rightMiddleScore", this.getApplicantFingerprint().getRightMiddleScore());
        applicantFingerprint.append("rightRingScore", this.getApplicantFingerprint().getRightRingScore());
        applicantFingerprint.append("rightLittleScore", this.getApplicantFingerprint().getRightLittleScore());
        applicantFingerprint.append("leftThumbState", this.getApplicantFingerprint().getLeftThumbState());
        applicantFingerprint.append("leftIndexState", this.getApplicantFingerprint().getLeftIndexState());
        applicantFingerprint.append("leftMiddleState", this.getApplicantFingerprint().getLeftMiddleState());
        applicantFingerprint.append("leftRingState", this.getApplicantFingerprint().getLeftRingState());
        applicantFingerprint.append("leftLittleState", this.getApplicantFingerprint().getLeftLittleState());
        applicantFingerprint.append("rightThumbState", this.getApplicantFingerprint().getRightThumbState());
        applicantFingerprint.append("rightIndexState", this.getApplicantFingerprint().getRightIndexState());
        applicantFingerprint.append("rightMiddleState", this.getApplicantFingerprint().getRightMiddleState());
        applicantFingerprint.append("rightRingState", this.getApplicantFingerprint().getRightRingState());
        applicantFingerprint.append("rightLittleState", this.getApplicantFingerprint().getRightLittleState());
        applicantFingerprint.append("sourceAFISID", this.getApplicantFingerprint().getSourceAFISID());


        String leftThumbAFISTemplateStr = null;
        if (this.getApplicantFingerprint().getLeftThumbAFISTemplate() != null && this.getApplicantFingerprint().getLeftThumbAFISTemplate().length() > 0) {
            leftThumbAFISTemplateStr = mongoDao.insertImg(this.getApplicantFingerprint().getLeftThumbAFISTemplate().getBytes(), "FingerprintTemplate");
        }
        applicantFingerprint.append("leftThumbAFISTemplate", leftThumbAFISTemplateStr);

        String leftIndexAFISTemplateStr = null;
        if (this.getApplicantFingerprint().getLeftIndexAFISTemplate() != null && this.getApplicantFingerprint().getLeftIndexAFISTemplate().length() > 0) {
            leftIndexAFISTemplateStr = mongoDao.insertImg(this.getApplicantFingerprint().getLeftIndexAFISTemplate().getBytes(), "FingerprintTemplate");
        }
        applicantFingerprint.append("leftIndexAFISTemplate", leftIndexAFISTemplateStr);

        String leftMiddleAFISTemplateStr = null;
        if (this.getApplicantFingerprint().getLeftMiddleAFISTemplate() != null && this.getApplicantFingerprint().getLeftMiddleAFISTemplate().length() > 0) {
            leftMiddleAFISTemplateStr = mongoDao.insertImg(this.getApplicantFingerprint().getLeftMiddleAFISTemplate().getBytes(), "FingerprintTemplate");
        }
        applicantFingerprint.append("leftMiddleAFISTemplate", leftMiddleAFISTemplateStr);

        String leftRingAFISTemplateStr = null;
        if (this.getApplicantFingerprint().getLeftRingAFISTemplate() != null && this.getApplicantFingerprint().getLeftRingAFISTemplate().length() > 0) {
            leftRingAFISTemplateStr = mongoDao.insertImg(this.getApplicantFingerprint().getLeftRingAFISTemplate().getBytes(), "FingerprintTemplate");
        }
        applicantFingerprint.append("leftRingAFISTemplate", leftRingAFISTemplateStr);

        String leftLittleAFISTemplateStr = null;
        if (this.getApplicantFingerprint().getLeftLittleAFISTemplate() != null && this.getApplicantFingerprint().getLeftLittleAFISTemplate().length() > 0) {
            leftLittleAFISTemplateStr = mongoDao.insertImg(this.getApplicantFingerprint().getLeftLittleAFISTemplate().getBytes(), "FingerprintTemplate");
        }
        applicantFingerprint.append("leftLittleAFISTemplate", leftLittleAFISTemplateStr);

        String rightThumbAFISTemplateStr = null;
        if (this.getApplicantFingerprint().getRightThumbAFISTemplate() != null && this.getApplicantFingerprint().getRightThumbAFISTemplate().length() > 0) {
            rightThumbAFISTemplateStr = mongoDao.insertImg(this.getApplicantFingerprint().getRightThumbAFISTemplate().getBytes(), "FingerprintTemplate");
        }
        applicantFingerprint.append("rightThumbAFISTemplate", rightThumbAFISTemplateStr);

        String rightIndexAFISTemplateStr = null;
        if (this.getApplicantFingerprint().getRightIndexAFISTemplate() != null && this.getApplicantFingerprint().getRightIndexAFISTemplate().length() > 0) {
            rightIndexAFISTemplateStr = mongoDao.insertImg(this.getApplicantFingerprint().getRightIndexAFISTemplate().getBytes(), "FingerprintTemplate");
        }
        applicantFingerprint.append("rightIndexAFISTemplate", rightIndexAFISTemplateStr);

        String rightMiddleAFISTemplateStr = null;
        if (this.getApplicantFingerprint().getRightMiddleAFISTemplate() != null && this.getApplicantFingerprint().getRightMiddleAFISTemplate().length() > 0) {
            rightMiddleAFISTemplateStr = mongoDao.insertImg(this.getApplicantFingerprint().getRightMiddleAFISTemplate().getBytes(), "FingerprintTemplate");
        }
        applicantFingerprint.append("rightMiddleAFISTemplate", rightMiddleAFISTemplateStr);

        String rightRingAFISTemplateStr = null;
        if (this.getApplicantFingerprint().getRightRingAFISTemplate() != null && this.getApplicantFingerprint().getRightRingAFISTemplate().length() > 0) {
            rightRingAFISTemplateStr = mongoDao.insertImg(this.getApplicantFingerprint().getRightRingAFISTemplate().getBytes(), "FingerprintTemplate");
        }
        applicantFingerprint.append("rightRingAFISTemplate", rightRingAFISTemplateStr);

        String rightLittleAFISTemplateStr = null;
        if (this.getApplicantFingerprint().getRightLittleAFISTemplate() != null && this.getApplicantFingerprint().getRightLittleAFISTemplate().length() > 0) {
            rightLittleAFISTemplateStr = mongoDao.insertImg(this.getApplicantFingerprint().getRightLittleAFISTemplate().getBytes(), "FingerprintTemplate");
        }
        applicantFingerprint.append("rightLittleAFISTemplate", rightLittleAFISTemplateStr);

        applicantFingerprint.append("leftLittleMinutiaesCount", this.getApplicantFingerprint().getLeftLittleMinutiaesCount());
        applicantFingerprint.append("leftRingMinutiaesCount", this.getApplicantFingerprint().getLeftRingMinutiaesCount());
        applicantFingerprint.append("leftMiddleMinutiaesCount", this.getApplicantFingerprint().getLeftMiddleMinutiaesCount());
        applicantFingerprint.append("leftIndexMinutiaesCount", this.getApplicantFingerprint().getLeftIndexMinutiaesCount());
        applicantFingerprint.append("leftThumbMinutiaesCount", this.getApplicantFingerprint().getLeftThumbMinutiaesCount());
        applicantFingerprint.append("rightLittleMinutiaesCount", this.getApplicantFingerprint().getRightLittleMinutiaesCount());
        applicantFingerprint.append("rightRingMinutiaesCount", this.getApplicantFingerprint().getRightRingMinutiaesCount());
        applicantFingerprint.append("rightMiddleMinutiaesCount", this.getApplicantFingerprint().getRightMiddleMinutiaesCount());
        applicantFingerprint.append("rightIndexMinutiaesCount", this.getApplicantFingerprint().getRightIndexMinutiaesCount());
        applicantFingerprint.append("rightThumbMinutiaesCount", this.getApplicantFingerprint().getRightThumbMinutiaesCount());
        applicantFingerprint.append("missingReasonType", this.getApplicantFingerprint().getMissingReasonType());

        dbObject.append("applicantFingerprint", applicantFingerprint);

        org.bson.Document applicantPhoto = new org.bson.Document();
        applicantPhoto.append("formElaspedTicks", this.getApplicantPhoto().getFormElaspedTicks());


        String photoArrayStr = null;
        if (this.getApplicantPhoto().getPhotoArray() != null && this.getApplicantPhoto().getPhotoArray().length() > 0) {
            photoArrayStr = mongoDao.insertImg(this.getApplicantPhoto().getPhotoArray().getBytes(), "PersonPhoto");

        }
        applicantPhoto.append("photoArray", photoArrayStr);

        String thumbnailStr = null;
        if (this.getApplicantPhoto().getThumbnail() != null && this.getApplicantPhoto().getThumbnail().length() > 0) {
            thumbnailStr = mongoDao.insertImg(this.getApplicantPhoto().getThumbnail().getBytes(), "PersonPhoto");

        }
        applicantPhoto.append("thumbnail", thumbnailStr);

        dbObject.put("applicantPhoto", applicantPhoto);
        return dbObject;
    }

    public MatchInfo toMatchInfo() {
        MatchInfo info = new MatchInfo();
        info.set_id(this.get_id());
        info.setFullName(this.getApplicantDemographic().getForenames() + " " + this.getApplicantDemographic().getSurname());
        info.setGender(this.getGender());
        info.setDateOfBirth(this.getDateOfBirth());
        info.setRegistrationNumber(this.getRegistrationNumber());
        return info;
    }
}
