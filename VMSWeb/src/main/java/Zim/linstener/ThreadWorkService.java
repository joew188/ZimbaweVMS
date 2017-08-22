package Zim.linstener;

import Zim.model.Applicant;
import Zim.mongo.MongoDBDaoImpl;
import Zim.thread.*;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Date;

/**
 * Created by Laxton-Joe on 2017/6/22.
 */
public class ThreadWorkService {
    MongoDBDaoImpl mongoDBDaoImpl = MongoDBDaoImpl.getMongoDBDaoImpl();

    @PostConstruct
    public void init() {

        DBObject sampleApplicant = mongoDBDaoImpl.findById("Applicant", "011dc5ea-337b-4d3b-a46a-7df45038b075");
        Applicant sample = toApplicant(sampleApplicant);
        for (int i = 1; i < 5; i++) {
            Tester ter = new Tester(sample, i, 4000, 20000);
            Thread tProducer = new Thread(ter);
            tProducer.start();
        }
    }

    private Applicant toApplicant(DBObject dbObject) {
        Applicant applicant = null;

        try {
            applicant = new Applicant();
            //region applicant

            applicant.setOperatorGuid(String.valueOf(dbObject.get("operatorGuid")));
            applicant.setOperatorName(String.valueOf(dbObject.get("operatorName")));

            applicant.setProvinceId(Long.valueOf(dbObject.get("provinceId").toString()));
            applicant.setProvinceName(dbObject.get("provinceName").toString());
            applicant.setDistrictId(Long.valueOf(dbObject.get("districtId").toString()));
            applicant.setDistrictName(dbObject.get("districtName").toString());
            applicant.setConstituencyId(Long.valueOf(dbObject.get("constituencyId").toString()));
            applicant.setConstituencyName(dbObject.get("constituencyName").toString());
            applicant.setWardId(Long.valueOf(dbObject.get("wardId").toString()));
            applicant.setWardName(dbObject.get("wardName").toString());
            applicant.setPollingStationId(Long.valueOf(dbObject.get("pollingStationId").toString()));
            applicant.setPollingStationCode(dbObject.get("pollingStationCode").toString());
            applicant.setPollingStationName(dbObject.get("pollingStationName").toString());
            applicant.setSaveDatetime(new Date());
            applicant.setEditSaveDatetime(new Date());

            applicant.setUnderDuress(Boolean.valueOf(dbObject.get("underDuress").toString()));
            applicant.setExportDatetime(new Date());
            applicant.setImportToServerDatetime(new Date());
            //endregion

            //region applicantCompliance
            DBObject objApplicantCompliance = (DBObject) dbObject.get("applicantCompliance");
            Applicant.ApplicantCompliance applicantCompliance = new Applicant.ApplicantCompliance();

            applicantCompliance.setFormElaspedTicks(0);

            String applicationFormContentStr = null;
            if (objApplicantCompliance.get("applicationFormContent") != null && objApplicantCompliance.get("applicationFormContent").toString().length() > 0) {
                //找到 CompliancePhoto
                DBObject dbObj = mongoDBDaoImpl.findById("CompliancePhoto", new ObjectId(objApplicantCompliance.get("applicationFormContent").toString()));
                //转换成 string
                applicationFormContentStr = new String((byte[]) dbObj.get("imgData"));
            }
            applicantCompliance.setApplicationFormContent(applicationFormContentStr);

            String idDocumentFormContentStr = null;
            if (objApplicantCompliance.get("idDocumentFormContent") != null && objApplicantCompliance.get("idDocumentFormContent").toString().length() > 0) {
                //找到 CompliancePhoto
                DBObject dbObj = mongoDBDaoImpl.findById("CompliancePhoto", new ObjectId(objApplicantCompliance.get("idDocumentFormContent").toString()));
                //转换成 string
                idDocumentFormContentStr = new String((byte[]) dbObj.get("imgData"));
            }

            applicantCompliance.setIDDocumentFormContent(idDocumentFormContentStr);

            String proofOfResidenceContentStr = null;
            if (objApplicantCompliance.get("proofOfResidenceContent") != null && objApplicantCompliance.get("proofOfResidenceContent").toString().length() > 0) {
                //找到 CompliancePhoto
                DBObject dbObj = mongoDBDaoImpl.findById("CompliancePhoto", new ObjectId(objApplicantCompliance.get("proofOfResidenceContent").toString()));
                //转换成 string
                proofOfResidenceContentStr = new String((byte[]) dbObj.get("imgData"));
            }

            applicantCompliance.setIDDocumentFormContent(proofOfResidenceContentStr);

            applicant.setApplicantCompliance(applicantCompliance);
            //endregion

            //region applicantDemographic
            DBObject objApplicantDemographic = (DBObject) dbObject.get("applicantDemographic");
            Applicant.ApplicantDemographic applicantDemographic = new Applicant.ApplicantDemographic();
            applicantDemographic.setFormElaspedTicks(0);


            applicantDemographic.setProvinceId(Long.valueOf(objApplicantDemographic.get("provinceId").toString()));
            applicantDemographic.setProvinceName(objApplicantDemographic.get("provinceName").toString());
            applicantDemographic.setDistrictId(Long.valueOf(objApplicantDemographic.get("districtId").toString()));
            applicantDemographic.setDistrictName(objApplicantDemographic.get("districtName").toString());
            applicantDemographic.setConstituencyId(Long.valueOf(objApplicantDemographic.get("constituencyId").toString()));
            applicantDemographic.setConstituencyName(objApplicantDemographic.get("constituencyName").toString());
            applicantDemographic.setWardId(Long.valueOf(objApplicantDemographic.get("wardId").toString()));
            applicantDemographic.setWardName(objApplicantDemographic.get("wardName").toString());
            applicantDemographic.setStationId(Long.valueOf(objApplicantDemographic.get("stationId").toString()));
            applicantDemographic.setStationCode(objApplicantDemographic.get("stationCode").toString());
            applicantDemographic.setStationName(objApplicantDemographic.get("stationName").toString());


            applicantDemographic.setSurburb(objApplicantDemographic.get("surburb").toString());
            applicantDemographic.setStreetName(objApplicantDemographic.get("streetName").toString());
            applicantDemographic.setStandNumber(objApplicantDemographic.get("standNumber").toString());
            applicantDemographic.setDisabilityCode(objApplicantDemographic.get("disabilityCode").toString());
            applicantDemographic.setDisabilityName(objApplicantDemographic.get("disabilityName").toString());
            applicantDemographic.setRegistrationType(Short.valueOf(objApplicantDemographic.get("registrationType").toString()));
            applicantDemographic.setPhoneNumber(objApplicantDemographic.get("phoneNumber").toString());
            applicantDemographic.setEmail(objApplicantDemographic.get("email").toString());

            applicantDemographic.setGisLatitude(Long.valueOf(objApplicantDemographic.get("gisLatitude").toString()));
            applicantDemographic.setGisLongitude(Long.valueOf(objApplicantDemographic.get("gisLongitude").toString()));
            if (objApplicantDemographic.get("changeAddressProvinceId") == null) {
                applicantDemographic.setChangeAddressProvinceId(null);
            } else {
                applicantDemographic.setChangeAddressProvinceId(Long.valueOf(objApplicantDemographic.get("changeAddressProvinceId").toString()));
            }
            applicantDemographic.setChangeAddressProvinceName(null);
            applicantDemographic.setChangeAddressDistrictId(null);
            applicantDemographic.setChangeAddressDistrictName(null);
            applicantDemographic.setChangeAddressConstituencyId(null);
            applicantDemographic.setChangeAddressConstituencyName(null);
            applicantDemographic.setChangeAddressWardId(null);
            applicantDemographic.setChangeAddressWardName(null);
            applicantDemographic.setChangeAddressStationId(null);
            applicantDemographic.setChangeAddressStationName(null);
            applicantDemographic.setChangeAddressStationCode(null);
            applicantDemographic.setChangeAddressSurburb(null);
            applicantDemographic.setChangeAddressStreetName(null);
            applicantDemographic.setChangeAddressStandNumber(null);
            applicantDemographic.setTransferConstituencyProvinceId(null);
            applicantDemographic.setTransferConstituencyProvinceName(null);
            applicantDemographic.setTransferConstituencyDistrictId(null);
            applicantDemographic.setTransferConstituencyDistrictName(null);
            applicantDemographic.setTransferConstituencyConstituencyId(null);
            applicantDemographic.setTransferConstituencyConstituencyName(null);
            applicantDemographic.setTransferConstituencyWardId(null);
            applicantDemographic.setTransferConstituencyWardName(null);
            applicantDemographic.setTransferConstituencyStationId(null);
            applicantDemographic.setTransferConstituencyStationName(null);
            applicantDemographic.setTransferConstituencyStationCode(null);
            applicantDemographic.setTransferConstituencySurburb(null);
            applicantDemographic.setTransferConstituencyStreetName(null);
            applicantDemographic.setTransferConstituencyStandNumber(null);


            applicant.setApplicantDemographic(applicantDemographic);
            //endregion applicantDemographic

            //region applicantFingerprint
            Applicant.ApplicantFingerprint applicantFingerprint = new Applicant.ApplicantFingerprint();
            DBObject objApplicantFingerprint = (DBObject) dbObject.get("applicantFingerprint");
            applicantFingerprint.setFormElaspedTicks(0);

            String leftThumbImageArrayStr = null;
            if (objApplicantFingerprint.get("leftThumbImageArray") != null && objApplicantFingerprint.get("leftThumbImageArray").toString().length() > 0) {
                DBObject dbObj = mongoDBDaoImpl.findById("FingerprintImage", new ObjectId(objApplicantFingerprint.get("leftThumbImageArray").toString()));
                leftThumbImageArrayStr = new String((byte[]) dbObj.get("imgData"));
            }
            applicantFingerprint.setLeftThumbImageArray(leftThumbImageArrayStr);

            String leftIndexImageArrayStr = null;
            if (objApplicantFingerprint.get("leftIndexImageArray") != null && objApplicantFingerprint.get("leftIndexImageArray").toString().length() > 0) {
                DBObject dbObj = mongoDBDaoImpl.findById("FingerprintImage", new ObjectId(objApplicantFingerprint.get("leftIndexImageArray").toString()));
                leftIndexImageArrayStr = new String((byte[]) dbObj.get("imgData"));
            }
            applicantFingerprint.setLeftIndexImageArray(leftIndexImageArrayStr);

            String leftMiddleImageArrayStr = null;
            if (objApplicantFingerprint.get("leftMiddleImageArray") != null && objApplicantFingerprint.get("leftMiddleImageArray").toString().length() > 0) {
                DBObject dbObj = mongoDBDaoImpl.findById("FingerprintImage", new ObjectId(objApplicantFingerprint.get("leftMiddleImageArray").toString()));
                leftMiddleImageArrayStr = new String((byte[]) dbObj.get("imgData"));
            }
            applicantFingerprint.setLeftMiddleImageArray(leftMiddleImageArrayStr);

            String leftRingImageArrayStr = null;
            if (objApplicantFingerprint.get("leftRingImageArray") != null && objApplicantFingerprint.get("leftRingImageArray").toString().length() > 0) {
                DBObject dbObj = mongoDBDaoImpl.findById("FingerprintImage", new ObjectId(objApplicantFingerprint.get("leftRingImageArray").toString()));
                leftRingImageArrayStr = new String((byte[]) dbObj.get("imgData"));
            }
            applicantFingerprint.setLeftRingImageArray(leftRingImageArrayStr);

            String leftLittleImageArrayStr = null;
            if (objApplicantFingerprint.get("leftLittleImageArray") != null && objApplicantFingerprint.get("leftLittleImageArray").toString().length() > 0) {
                DBObject dbObj = mongoDBDaoImpl.findById("FingerprintImage", new ObjectId(objApplicantFingerprint.get("leftLittleImageArray").toString()));
                leftLittleImageArrayStr = new String((byte[]) dbObj.get("imgData"));
            }
            applicantFingerprint.setLeftLittleImageArray(leftLittleImageArrayStr);

            String rightThumbImageArrayStr = null;
            if (objApplicantFingerprint.get("rightThumbImageArray") != null && objApplicantFingerprint.get("rightThumbImageArray").toString().length() > 0) {
                DBObject dbObj = mongoDBDaoImpl.findById("FingerprintImage", new ObjectId(objApplicantFingerprint.get("rightThumbImageArray").toString()));
                rightThumbImageArrayStr = new String((byte[]) dbObj.get("imgData"));
            }
            applicantFingerprint.setRightThumbImageArray(rightThumbImageArrayStr);

            String rightIndexImageArrayStr = null;
            if (objApplicantFingerprint.get("rightIndexImageArray") != null && objApplicantFingerprint.get("rightIndexImageArray").toString().length() > 0) {
                DBObject dbObj = mongoDBDaoImpl.findById("FingerprintImage", new ObjectId(objApplicantFingerprint.get("rightIndexImageArray").toString()));
                rightIndexImageArrayStr = new String((byte[]) dbObj.get("imgData"));
            }
            applicantFingerprint.setRightIndexImageArray(rightIndexImageArrayStr);

            String rightMiddleImageArrayStr = null;
            if (objApplicantFingerprint.get("rightMiddleImageArray") != null && objApplicantFingerprint.get("rightMiddleImageArray").toString().length() > 0) {
                DBObject dbObj = mongoDBDaoImpl.findById("FingerprintImage", new ObjectId(objApplicantFingerprint.get("rightMiddleImageArray").toString()));
                rightMiddleImageArrayStr = new String((byte[]) dbObj.get("imgData"));
            }
            applicantFingerprint.setRightMiddleImageArray(rightMiddleImageArrayStr);

            String rightRingImageArrayStr = null;
            if (objApplicantFingerprint.get("rightRingImageArray") != null && objApplicantFingerprint.get("rightRingImageArray").toString().length() > 0) {
                DBObject dbObj = mongoDBDaoImpl.findById("FingerprintImage", new ObjectId(objApplicantFingerprint.get("rightRingImageArray").toString()));
                rightRingImageArrayStr = new String((byte[]) dbObj.get("imgData"));
            }
            applicantFingerprint.setRightRingImageArray(rightRingImageArrayStr);

            String rightLittleImageArrayStr = null;
            if (objApplicantFingerprint.get("rightLittleImageArray") != null && objApplicantFingerprint.get("rightLittleImageArray").toString().length() > 0) {
                DBObject dbObj = mongoDBDaoImpl.findById("FingerprintImage", new ObjectId(objApplicantFingerprint.get("rightLittleImageArray").toString()));
                rightLittleImageArrayStr = new String((byte[]) dbObj.get("imgData"));
            }
            applicantFingerprint.setRightLittleImageArray(rightLittleImageArrayStr);

            String leftThumbWSQArrayStr = null;
            if (objApplicantFingerprint.get("leftThumbWSQArray") != null && objApplicantFingerprint.get("leftThumbWSQArray").toString().length() > 0) {
                DBObject dbObj = mongoDBDaoImpl.findById("FingerprintWSQ", new ObjectId(objApplicantFingerprint.get("leftThumbWSQArray").toString()));
                leftThumbWSQArrayStr = new String((byte[]) dbObj.get("imgData"));
            }
            applicantFingerprint.setLeftThumbWSQArray(leftThumbWSQArrayStr);

            String leftIndexWSQArrayStr = null;
            if (objApplicantFingerprint.get("leftIndexWSQArray") != null && objApplicantFingerprint.get("leftIndexWSQArray").toString().length() > 0) {
                DBObject dbObj = mongoDBDaoImpl.findById("FingerprintWSQ", new ObjectId(objApplicantFingerprint.get("leftIndexWSQArray").toString()));
                leftIndexWSQArrayStr = new String((byte[]) dbObj.get("imgData"));
            }
            applicantFingerprint.setLeftIndexWSQArray(leftIndexWSQArrayStr);

            String leftMiddleWSQArrayStr = null;
            if (objApplicantFingerprint.get("leftMiddleWSQArray") != null && objApplicantFingerprint.get("leftMiddleWSQArray").toString().length() > 0) {
                DBObject dbObj = mongoDBDaoImpl.findById("FingerprintWSQ", new ObjectId(objApplicantFingerprint.get("leftMiddleWSQArray").toString()));
                leftMiddleWSQArrayStr = new String((byte[]) dbObj.get("imgData"));
            }
            applicantFingerprint.setLeftMiddleWSQArray(leftMiddleWSQArrayStr);

            String leftRingWSQArrayStr = null;
            if (objApplicantFingerprint.get("leftRingWSQArray") != null && objApplicantFingerprint.get("leftRingWSQArray").toString().length() > 0) {
                DBObject dbObj = mongoDBDaoImpl.findById("FingerprintWSQ", new ObjectId(objApplicantFingerprint.get("leftRingWSQArray").toString()));
                leftRingWSQArrayStr = new String((byte[]) dbObj.get("imgData"));
            }
            applicantFingerprint.setLeftRingWSQArray(leftRingWSQArrayStr);

            String leftLittleWSQArrayStr = null;
            if (objApplicantFingerprint.get("leftLittleWSQArray") != null && objApplicantFingerprint.get("leftLittleWSQArray").toString().length() > 0) {
                DBObject dbObj = mongoDBDaoImpl.findById("FingerprintWSQ", new ObjectId(objApplicantFingerprint.get("leftLittleWSQArray").toString()));
                leftLittleWSQArrayStr = new String((byte[]) dbObj.get("imgData"));
            }
            applicantFingerprint.setLeftLittleWSQArray(leftLittleWSQArrayStr);

            String rightThumbWSQArrayStr = null;
            if (objApplicantFingerprint.get("rightThumbWSQArray") != null && objApplicantFingerprint.get("rightThumbWSQArray").toString().length() > 0) {
                DBObject dbObj = mongoDBDaoImpl.findById("FingerprintWSQ", new ObjectId(objApplicantFingerprint.get("rightThumbWSQArray").toString()));
                rightThumbWSQArrayStr = new String((byte[]) dbObj.get("imgData"));
            }
            applicantFingerprint.setRightThumbWSQArray(rightThumbWSQArrayStr);

            String rightIndexWSQArrayStr = null;
            if (objApplicantFingerprint.get("rightIndexWSQArray") != null && objApplicantFingerprint.get("rightIndexWSQArray").toString().length() > 0) {
                DBObject dbObj = mongoDBDaoImpl.findById("FingerprintWSQ", new ObjectId(objApplicantFingerprint.get("rightIndexWSQArray").toString()));
                rightIndexWSQArrayStr = new String((byte[]) dbObj.get("imgData"));
            }
            applicantFingerprint.setRightIndexWSQArray(rightIndexWSQArrayStr);

            String rightMiddleWSQArrayStr = null;
            if (objApplicantFingerprint.get("rightMiddleWSQArray") != null && objApplicantFingerprint.get("rightMiddleWSQArray").toString().length() > 0) {
                DBObject dbObj = mongoDBDaoImpl.findById("FingerprintWSQ", new ObjectId(objApplicantFingerprint.get("rightMiddleWSQArray").toString()));
                rightMiddleWSQArrayStr = new String((byte[]) dbObj.get("imgData"));
            }
            applicantFingerprint.setRightMiddleWSQArray(rightMiddleWSQArrayStr);

            String rightRingWSQArrayStr = null;
            if (objApplicantFingerprint.get("rightRingWSQArray") != null && objApplicantFingerprint.get("rightRingWSQArray").toString().length() > 0) {
                DBObject dbObj = mongoDBDaoImpl.findById("FingerprintWSQ", new ObjectId(objApplicantFingerprint.get("rightRingWSQArray").toString()));
                rightRingWSQArrayStr = new String((byte[]) dbObj.get("imgData"));
            }
            applicantFingerprint.setRightRingWSQArray(rightRingWSQArrayStr);

            String rightLittleWSQArrayStr = null;
            if (objApplicantFingerprint.get("rightLittleWSQArray") != null && objApplicantFingerprint.get("rightLittleWSQArray").toString().length() > 0) {
                DBObject dbObj = mongoDBDaoImpl.findById("FingerprintWSQ", new ObjectId(objApplicantFingerprint.get("rightLittleWSQArray").toString()));
                rightLittleWSQArrayStr = new String((byte[]) dbObj.get("imgData"));
            }
            applicantFingerprint.setRightLittleWSQArray(rightLittleWSQArrayStr);

            applicantFingerprint.setLeftThumbScore(Integer.valueOf(objApplicantFingerprint.get("leftThumbScore").toString()));
            applicantFingerprint.setLeftIndexScore(Integer.valueOf(objApplicantFingerprint.get("leftIndexScore").toString()));
            applicantFingerprint.setLeftMiddleScore(Integer.valueOf(objApplicantFingerprint.get("leftMiddleScore").toString()));
            applicantFingerprint.setLeftRingScore(Integer.valueOf(objApplicantFingerprint.get("leftRingScore").toString()));
            applicantFingerprint.setLeftLittleScore(Integer.valueOf(objApplicantFingerprint.get("leftLittleScore").toString()));
            applicantFingerprint.setRightThumbScore(Integer.valueOf(objApplicantFingerprint.get("rightThumbScore").toString()));
            applicantFingerprint.setRightIndexScore(Integer.valueOf(objApplicantFingerprint.get("rightIndexScore").toString()));
            applicantFingerprint.setRightMiddleScore(Integer.valueOf(objApplicantFingerprint.get("rightMiddleScore").toString()));
            applicantFingerprint.setRightRingScore(Integer.valueOf(objApplicantFingerprint.get("rightRingScore").toString()));
            applicantFingerprint.setRightLittleScore(Integer.valueOf(objApplicantFingerprint.get("rightLittleScore").toString()));
            applicantFingerprint.setLeftThumbState(Short.valueOf(objApplicantFingerprint.get("leftThumbState").toString()));
            applicantFingerprint.setLeftIndexState(Short.valueOf(objApplicantFingerprint.get("leftIndexState").toString()));
            applicantFingerprint.setLeftMiddleState(Short.valueOf(objApplicantFingerprint.get("leftMiddleState").toString()));
            applicantFingerprint.setLeftRingState(Short.valueOf(objApplicantFingerprint.get("leftRingState").toString()));
            applicantFingerprint.setLeftLittleState(Short.valueOf(objApplicantFingerprint.get("leftLittleState").toString()));
            applicantFingerprint.setRightThumbState(Short.valueOf(objApplicantFingerprint.get("rightThumbState").toString()));
            applicantFingerprint.setRightIndexState(Short.valueOf(objApplicantFingerprint.get("rightIndexState").toString()));
            applicantFingerprint.setRightMiddleState(Short.valueOf(objApplicantFingerprint.get("rightMiddleState").toString()));
            applicantFingerprint.setRightRingState(Short.valueOf(objApplicantFingerprint.get("rightRingState").toString()));
            applicantFingerprint.setRightLittleState(Short.valueOf(objApplicantFingerprint.get("rightLittleState").toString()));
            applicantFingerprint.setSourceAFISID(Long.valueOf(objApplicantFingerprint.get("sourceAFISID").toString()));


            String leftThumbAFISTemplateTStr = null;
            if (objApplicantFingerprint.get("leftThumbAFISTemplateT") != null && objApplicantFingerprint.get("leftThumbAFISTemplateT").toString().length() > 0) {
                DBObject dbObj = mongoDBDaoImpl.findById("FingerprintTemplate", new ObjectId(objApplicantFingerprint.get("leftThumbAFISTemplateT").toString()));
                leftThumbAFISTemplateTStr = new String((byte[]) dbObj.get("imgData"));
            }
            applicantFingerprint.setLeftThumbAFISTemplateT(leftThumbAFISTemplateTStr);

            String leftIndexAFISTemplateTStr = null;
            if (objApplicantFingerprint.get("leftIndexAFISTemplateT") != null && objApplicantFingerprint.get("leftIndexAFISTemplateT").toString().length() > 0) {
                DBObject dbObj = mongoDBDaoImpl.findById("FingerprintTemplate", new ObjectId(objApplicantFingerprint.get("leftIndexAFISTemplateT").toString()));
                leftIndexAFISTemplateTStr = new String((byte[]) dbObj.get("imgData"));
            }
            applicantFingerprint.setLeftIndexAFISTemplateT(leftIndexAFISTemplateTStr);

            String leftMiddleAFISTemplateTStr = null;
            if (objApplicantFingerprint.get("leftMiddleAFISTemplateT") != null && objApplicantFingerprint.get("leftMiddleAFISTemplateT").toString().length() > 0) {
                DBObject dbObj = mongoDBDaoImpl.findById("FingerprintTemplate", new ObjectId(objApplicantFingerprint.get("leftMiddleAFISTemplateT").toString()));
                leftMiddleAFISTemplateTStr = new String((byte[]) dbObj.get("imgData"));
            }
            applicantFingerprint.setLeftMiddleAFISTemplateT(leftMiddleAFISTemplateTStr);

            String leftRingAFISTemplateTStr = null;
            if (objApplicantFingerprint.get("leftRingAFISTemplateT") != null && objApplicantFingerprint.get("leftRingAFISTemplateT").toString().length() > 0) {
                DBObject dbObj = mongoDBDaoImpl.findById("FingerprintTemplate", new ObjectId(objApplicantFingerprint.get("leftRingAFISTemplateT").toString()));
                leftRingAFISTemplateTStr = new String((byte[]) dbObj.get("imgData"));
            }
            applicantFingerprint.setLeftRingAFISTemplateT(leftRingAFISTemplateTStr);

            String leftLittleAFISTemplateTStr = null;
            if (objApplicantFingerprint.get("leftLittleAFISTemplateT") != null && objApplicantFingerprint.get("leftLittleAFISTemplateT").toString().length() > 0) {
                DBObject dbObj = mongoDBDaoImpl.findById("FingerprintTemplate", new ObjectId(objApplicantFingerprint.get("leftLittleAFISTemplateT").toString()));
                leftLittleAFISTemplateTStr = new String((byte[]) dbObj.get("imgData"));
            }
            applicantFingerprint.setLeftLittleAFISTemplateT(leftLittleAFISTemplateTStr);

            String rightThumbAFISTemplateTStr = null;
            if (objApplicantFingerprint.get("rightThumbAFISTemplateT") != null && objApplicantFingerprint.get("rightThumbAFISTemplateT").toString().length() > 0) {
                DBObject dbObj = mongoDBDaoImpl.findById("FingerprintTemplate", new ObjectId(objApplicantFingerprint.get("rightThumbAFISTemplateT").toString()));
                rightThumbAFISTemplateTStr = new String((byte[]) dbObj.get("imgData"));
            }
            applicantFingerprint.setRightThumbAFISTemplateT(rightThumbAFISTemplateTStr);

            String rightIndexAFISTemplateTStr = null;
            if (objApplicantFingerprint.get("rightIndexAFISTemplateT") != null && objApplicantFingerprint.get("rightIndexAFISTemplateT").toString().length() > 0) {
                DBObject dbObj = mongoDBDaoImpl.findById("FingerprintTemplate", new ObjectId(objApplicantFingerprint.get("rightIndexAFISTemplateT").toString()));
                rightIndexAFISTemplateTStr = new String((byte[]) dbObj.get("imgData"));
            }
            applicantFingerprint.setRightIndexAFISTemplateT(rightIndexAFISTemplateTStr);

            String rightMiddleAFISTemplateTStr = null;
            if (objApplicantFingerprint.get("rightMiddleAFISTemplateT") != null && objApplicantFingerprint.get("rightMiddleAFISTemplateT").toString().length() > 0) {
                DBObject dbObj = mongoDBDaoImpl.findById("FingerprintTemplate", new ObjectId(objApplicantFingerprint.get("rightMiddleAFISTemplateT").toString()));
                rightMiddleAFISTemplateTStr = new String((byte[]) dbObj.get("imgData"));
            }
            applicantFingerprint.setRightMiddleAFISTemplateT(rightMiddleAFISTemplateTStr);

            String rightRingAFISTemplateTStr = null;
            if (objApplicantFingerprint.get("rightRingAFISTemplateT") != null && objApplicantFingerprint.get("rightRingAFISTemplateT").toString().length() > 0) {
                DBObject dbObj = mongoDBDaoImpl.findById("FingerprintTemplate", new ObjectId(objApplicantFingerprint.get("rightRingAFISTemplateT").toString()));
                rightRingAFISTemplateTStr = new String((byte[]) dbObj.get("imgData"));
            }
            applicantFingerprint.setRightRingAFISTemplateT(rightRingAFISTemplateTStr);

            String rightLittleAFISTemplateTStr = null;
            if (objApplicantFingerprint.get("rightLittleAFISTemplateT") != null && objApplicantFingerprint.get("rightLittleAFISTemplateT").toString().length() > 0) {
                DBObject dbObj = mongoDBDaoImpl.findById("FingerprintTemplate", new ObjectId(objApplicantFingerprint.get("rightLittleAFISTemplateT").toString()));
                rightLittleAFISTemplateTStr = new String((byte[]) dbObj.get("imgData"));
            }
            applicantFingerprint.setRightLittleAFISTemplateT(rightLittleAFISTemplateTStr);


            applicantFingerprint.setLeftLittleMinutiaesCount(Long.valueOf(objApplicantFingerprint.get("leftLittleMinutiaesCount").toString()));
            applicantFingerprint.setLeftRingMinutiaesCount(Long.valueOf(objApplicantFingerprint.get("leftRingMinutiaesCount").toString()));
            applicantFingerprint.setLeftMiddleMinutiaesCount(Long.valueOf(objApplicantFingerprint.get("leftMiddleMinutiaesCount").toString()));
            applicantFingerprint.setLeftIndexMinutiaesCount(Long.valueOf(objApplicantFingerprint.get("leftIndexMinutiaesCount").toString()));
            applicantFingerprint.setLeftThumbMinutiaesCount(Long.valueOf(objApplicantFingerprint.get("leftThumbMinutiaesCount").toString()));
            applicantFingerprint.setRightLittleMinutiaesCount(Long.valueOf(objApplicantFingerprint.get("rightLittleMinutiaesCount").toString()));
            applicantFingerprint.setRightRingMinutiaesCount(Long.valueOf(objApplicantFingerprint.get("rightRingMinutiaesCount").toString()));
            applicantFingerprint.setRightMiddleMinutiaesCount(Long.valueOf(objApplicantFingerprint.get("rightMiddleMinutiaesCount").toString()));
            applicantFingerprint.setRightIndexMinutiaesCount(Long.valueOf(objApplicantFingerprint.get("rightIndexMinutiaesCount").toString()));
            applicantFingerprint.setRightThumbMinutiaesCount(Long.valueOf(objApplicantFingerprint.get("rightThumbMinutiaesCount").toString()));
            applicantFingerprint.setMissingReasonType(Short.valueOf(objApplicantFingerprint.get("missingReasonType").toString()));


            applicant.setApplicantFingerprint(applicantFingerprint);


            //endregion

            //region applicantPhoto
            Applicant.ApplicantPhoto applicantPhoto = new Applicant.ApplicantPhoto();
            DBObject objApplicantPhoto = (DBObject) dbObject.get("applicantPhoto");
            applicantPhoto.setFormElaspedTicks(0);

            String photoArrayStr = null;
            if (objApplicantPhoto.get("photoArray") != null && objApplicantPhoto.get("photoArray").toString().length() > 0) {
                DBObject dbObj = mongoDBDaoImpl.findById("PersonPhoto", new ObjectId(objApplicantPhoto.get("photoArray").toString()));
                photoArrayStr = new String((byte[]) dbObj.get("imgData"));
            }
            applicantPhoto.setPhotoArray(photoArrayStr);

            String thumbnailStr = null;
            if (objApplicantPhoto.get("thumbnail") != null && objApplicantPhoto.get("thumbnail").toString().length() > 0) {
                DBObject dbObj = mongoDBDaoImpl.findById("PersonPhoto", new ObjectId(objApplicantPhoto.get("thumbnail").toString()));
                thumbnailStr = new String((byte[]) dbObj.get("imgData"));
            }
            applicantPhoto.setThumbnail(thumbnailStr);


            applicant.setApplicantPhoto(applicantPhoto);

            //endregion applicantPhoto
        } catch (Exception ex) {
            applicant = null;
        }


        return applicant;
    }


    @PreDestroy
    public void dostory() {
    }
}
