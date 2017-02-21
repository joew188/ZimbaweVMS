package Zim.common;

import Zim.map.Record;
import Zim.model.Applicant;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;

/**
 * Created by Laxton-Joe on 2017/2/16.
 */
public class FileImport {

    public static Record ConvertFileStream(InputStream stream) {
        Record rc = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Record.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            rc = (Record) jaxbUnmarshaller.unmarshal(stream);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return rc;
    }
}
