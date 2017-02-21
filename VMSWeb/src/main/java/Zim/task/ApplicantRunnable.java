package Zim.task;

import Zim.common.FileImport;
import Zim.map.Record;
import Zim.model.Applicant;
import Zim.model.ApplicantLog;
import Zim.mongo.MongoDBDaoImpl;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Laxton-Joe on 2017/2/16.
 */
public class ApplicantRunnable implements Runnable {
    private FileInputStream _fileInputStream;

    public ApplicantRunnable(FileInputStream fileStream) {
        _fileInputStream = fileStream;
    }

    @Override
    public void run() {
//        synchronized (this) {
        try {
            MongoDBDaoImpl mongoDBDaoImpl = MongoDBDaoImpl.getMongoDBDaoImpl();

            Record rm = FileImport.ConvertFileStream(_fileInputStream);
            if (null != rm && rm.getID() != null) {
                Applicant applicant = Applicant.toApplicant(rm);
                if (mongoDBDaoImpl.findById("Applicant", applicant.get_id()) == null) {
                    WriteResult wApplicantResult = mongoDBDaoImpl.addObject(applicant.toMap(java.util.UUID.randomUUID().toString()), "Applicant");
                    if (wApplicantResult.getN() > 0) {
                        //log and queque
                        mongoDBDaoImpl.addObject(applicant.toMatchMap(), "MatchQueue");
                        mongoDBDaoImpl.addObject(ApplicantLog.toLogMap(applicant.get_id(), "Applicant Created Status:Unmatched."), "ApplicantLog");

                     //   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                     //   System.out.print(sdf.format(new Date()) + "--" + applicant.getId() + "\n");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (_fileInputStream != null)
                try {
                    _fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
