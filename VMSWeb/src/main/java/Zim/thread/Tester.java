package Zim.thread;

import Zim.common.SystemHelper;
import Zim.model.Applicant;
import Zim.mongo.MongoDBDaoImpl;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Laxton-Joe on 2017/8/16.
 */
public class Tester implements Runnable {
    MongoDBDaoImpl mongoDBDaoImpl = MongoDBDaoImpl.getMongoDBDaoImpl();
    int iBegin = 0;
    int iEnd = 0;
    int currentIndex = 1;
    int size = 0;
    int beginIndex = 0;
    Applicant sample;
    private long sorNum = 1;

    public Tester(Applicant sample, int i, int size, int beginIndex) {
        this.size = size;
        this.beginIndex = beginIndex;
        this.sample = sample;
        iBegin = ((i - 1) * size) + beginIndex;
        iEnd = (i * size) + beginIndex;
        currentIndex = iBegin;
        sorNum = currentIndex % 4000;
    }

    private Applicant toApplicant(String fistName, String lastName, String birth, String gender, String rid, String tstId) {
        Applicant applicant = this.sample;

        try {

            //region applicant
            applicant.set_id("testdata-" + rid);
            applicant.setFullName(fistName + " " + lastName);
            applicant.setGender(gender.equals("Male") ? 1 : 2);
            applicant.setDateOfBirth(Integer.valueOf(birth.replaceAll("-", "").replace(" 00:00:00", "")));

            applicant.setStatus(Short.valueOf("0"));
            applicant.setGuid(UUID.randomUUID().toString());
            applicant.setRegistrationNumber(tstId);

            String dvcName = String.format("%04d", ((int) Math.floor(Integer.valueOf(rid) / 4000)) + 1);

            String deviceName = "MW" + dvcName;
            if (sorNum > 4000) {
                sorNum = 1;
            }
            applicant.setSortNumber(sorNum);

            applicant.setBeginCreateDatetime(new Date());
            applicant.setEndCreateDatetime(new Date());
            applicant.setBeginEditDatetime(null);
            applicant.setEndEditDatetime(null);

            applicant.setSaveDatetime(new Date());
            applicant.setEditSaveDatetime(new Date());
            applicant.setDeviceName(deviceName);

            applicant.setExportDatetime(new Date());
            applicant.setImportToServerDatetime(new Date());
            //endregion

            //region applicantCompliance


            applicant.getApplicantCompliance().setFormElaspedTicks(0);


            //endregion

            //region applicantDemographic


            applicant.getApplicantDemographic().setFormElaspedTicks(0);
            applicant.getApplicantDemographic().setIdNumber(tstId);
            applicant.getApplicantDemographic().setSurname(fistName);
            applicant.getApplicantDemographic().setForenames(lastName);
            applicant.getApplicantDemographic().setDateOfBirth(new Date(Integer.valueOf(birth.substring(0, 4)), Integer.valueOf(birth.substring(5, 7)), Integer.valueOf(birth.substring(8, 10))));
            applicant.getApplicantDemographic().setDateOfBirthText(birth.substring(0, 4) + "/" + birth.substring(5, 7) + "" + birth.substring(8, 10));
            applicant.getApplicantDemographic().setGender(gender.equals("Male") ? Byte.valueOf("1") : Byte.valueOf("2"));


            //endregion applicantDemographic

            //region applicantFingerprint

            applicant.getApplicantFingerprint().setFormElaspedTicks(0);


            //endregion

            //region applicantPhoto

            applicant.getApplicantPhoto().setFormElaspedTicks(0);


            //endregion applicantPhoto
        } catch (Exception ex) {
            applicant = null;
        }


        return applicant;
    }

    @Override
    public void run() {

        while (currentIndex < iEnd) {
            if (currentIndex < 9197252) {
                DBObject queryObj = new BasicDBObject();
                queryObj.put("rid", currentIndex);
                DBObject dbObject = mongoDBDaoImpl.findQuery("Tst", queryObj);
                if (dbObject != null) {
                    String fistName = dbObject.get("firstname").toString();
                    String lastName = dbObject.get("lastname").toString();
                    String birth = dbObject.get("dateofbirth").toString();
                    String gender = dbObject.get("gender").toString();
                    String rid = dbObject.get("rid").toString();
                    String tstId = dbObject.get("_id").toString();
                    Applicant applicant = toApplicant(fistName, lastName, birth, gender, rid, tstId);
                    sorNum++;
                    if (applicant != null) {
                        mongoDBDaoImpl.insert(SystemHelper.MONGODBSETTING_DB, "Applicant", applicant.toDBObject(mongoDBDaoImpl));
                    }
                }
            }
            currentIndex++;
        }
    }
}
