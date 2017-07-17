package Zim.thread;

import Zim.map.Record;

import Zim.mongo.MongoDBDaoImpl;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Laxton-Joe on 2017/6/22.
 */
public class Producer implements Runnable {
    static MongoDBDaoImpl mongoDBDaoImpl = MongoDBDaoImpl.getMongoDBDaoImpl();
    private ConcurrentLinkedQueue<String> _queue;
    private BlockingQueue<Record> _buffer;

    public Producer(ConcurrentLinkedQueue<String> queue, BlockingQueue<Record> buffer) {
        _queue = queue;
        _buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
//            synchronized (_queue) {
            if (!_queue.isEmpty()) {
                String fileName = _queue.poll();
                if (null != fileName) {
                    try {
                        File file = new File(fileName);
                        InputStream fileStream = new FileInputStream(file);
                        JAXBContext jaxbContext = JAXBContext.newInstance(Record.class);
                        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                        Record record = (Record) jaxbUnmarshaller.unmarshal(fileStream);
                        _buffer.put(record);
                    } catch (InterruptedException e) {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("file", fileName);
                        map.put("ExceptionType", "InterruptedException");

                        map.put("Exception", e.toString());
                        mongoDBDaoImpl.addObject(map, "ApplicantProduceFailLog");

                    } catch (JAXBException e) {

                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("file", fileName);
                        map.put("ExceptionType", "JAXBException");
                        map.put("Exception", e.toString());
                        mongoDBDaoImpl.addObject(map, "ApplicantProduceFailLog");

                    } catch (FileNotFoundException e) {
                        if (new File(fileName).exists()) {
                            _queue.add(fileName);
                        }
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("file", fileName);
                        map.put("ExceptionType", "FileNotFoundException");
                        map.put("Exception", e.toString());
                        mongoDBDaoImpl.addObject(map, "ApplicantProduceFailLog");
                    }
                }
            }
//            }
        }
    }
}
