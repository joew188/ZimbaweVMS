//package Zim.thread;
//
//import Zim.map.Record;
//import Zim.mongo.MongoDBDaoImpl;
//import Zim.task.MatchApplicantRunnable;
//
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBException;
//import javax.xml.bind.Unmarshaller;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.BlockingQueue;
//import java.util.concurrent.ConcurrentLinkedQueue;
//
///**
// * Created by Laxton-Joe on 2017/6/22.
// */
//public class Produce implements Runnable {
//
//    private String _fileName;
//    static MongoDBDaoImpl mongoDBDaoImpl = MongoDBDaoImpl.getMongoDBDaoImpl();
//
//    public Produce(String fileName) {
//
//        _fileName = fileName;
//
//
//    }
//
//    @Override
//    public void run() {
//
//
//
//            Record record = null;
//            try {
//                File xmlFile = new File(_fileName);
//
//
//                Map<String, Object> map = new HashMap<String, Object>();
//                map.put("fileId", _fileName);
//
//                mongoDBDaoImpl.addObject(map, "ApplicantProduceLog");
//
//
//                InputStream inStream = new FileInputStream(xmlFile);
//                JAXBContext jaxbContext = JAXBContext.newInstance(Record.class);
//                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//                record = (Record) jaxbUnmarshaller.unmarshal(inStream);
//                new Consume(record).run();
//            } catch (Exception e) {
//                Map<String, Object> map = new HashMap<String, Object>();
//                map.put("fileId", record.getId());
//                mongoDBDaoImpl.addObject(map, "ApplicantImportFailLog");
//            }
//
//
//
//    }
//}
