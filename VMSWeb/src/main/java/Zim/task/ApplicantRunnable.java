//package Zim.task;
//
//import Zim.common.FileImport;
//import Zim.map.Record;
//import Zim.model.Applicant;
//import Zim.model.ApplicantLog;
//import Zim.mongo.MongoDBDaoImpl;
//import com.mongodb.DBObject;
//import com.mongodb.WriteResult;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by Laxton-Joe on 2017/2/16.
// */
//public class ApplicantRunnable implements Runnable {
//    private FileInputStream _fileInputStream;
//    private String _fileName;
//    private boolean _importSuccess = false;
//
//    public ApplicantRunnable(FileInputStream fileStream, String fileName) {
//        _fileInputStream = fileStream;
//        _fileName = fileName;
//    }
//
//    @Override
//    public void run() {
//        MongoDBDaoImpl mongoDBDaoImpl = MongoDBDaoImpl.getMongoDBDaoImpl();
////        Map<String, Object> mapLog0 = new HashMap<String, Object>();
////        mapLog0.put("fileId", _fileInputStream.toString());
////
////        mongoDBDaoImpl.addObject(mapLog0, "ApplicantImportBeginLog");
//        try {
//            Record rm = FileImport.ConvertFileStream(_fileInputStream);
//            if (null != rm && rm.getId() != null) {
//                Applicant applicant = Applicant.toApplicant(rm);
//
////                Map<String, Object> mapLog = new HashMap<String, Object>();
////                mapLog.put("fileId", applicant.get_id());
////
////                mongoDBDaoImpl.addObject(mapLog, "ApplicantImportFileLog");
//
//                if (mongoDBDaoImpl.findById("Applicant", applicant.get_id()) == null) {
//                    WriteResult wApplicantResult = mongoDBDaoImpl.addObject(applicant.toMap(java.util.UUID.randomUUID().toString()), "Applicant");
//                    if (wApplicantResult.getN() > 0) {
//                        //log and queque
//                    //    mongoDBDaoImpl.addObject(applicant.toMatchMap(), "MatchQueue");
//                        mongoDBDaoImpl.addObject(ApplicantLog.toLogMap(applicant.get_id(), "Applicant Created Status:Unmatched."), "ApplicantLog");
//                        _importSuccess = true;
//                    }
////                    else {
////                        Map<String, Object> mapLog2 = new HashMap<String, Object>();
////                        mapLog2.put("fileId", applicant.get_id());
////
////                        mongoDBDaoImpl.addObject(mapLog2, "ApplicantImportFailLog");
////                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            Map<String, Object> mapLog4 = new HashMap<String, Object>();
//            mapLog4.put("fileId", "ApplicantImportExceptionLog");
//
//            mongoDBDaoImpl.addObject(mapLog4, "ApplicantImportExceptionLog");
//
//        } finally {
//            if (!_importSuccess) {
//                Map<String, Object> mapLog2 = new HashMap<String, Object>();
//                mapLog2.put("fileId", _fileName);
//
//                mongoDBDaoImpl.addObject(mapLog2, "ApplicantImportFailLog");
//            }
//            if (_fileInputStream != null) {
//                try {
//                    _fileInputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//    }
//}
