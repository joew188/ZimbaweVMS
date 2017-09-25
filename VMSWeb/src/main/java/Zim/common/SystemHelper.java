package Zim.common;

import Zim.linstener.MatchRunnable;
import Zim.model.map.ExportLog;
import Zim.mongo.MongoDao;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Laxton-Joe on 2017/2/16.
 */
public class SystemHelper {

    private static Logger logger = LoggerFactory.getLogger(SystemHelper.class);

    public static String getApplicantStatusString(int status) {
        String result = "";
        switch (status) {
            case 0:
                result = "Unmached";
                break;
            case 1:
                result = "Master";
                break;
            case 2:
                result = "Suspect";
                break;
            case 3:
                result = "Archive";
                break;
        }
        return result;
    }

    public static BlockingQueue<String> matchQueue = new LinkedBlockingQueue<>();


    public static int DateToInt(Date date) {
        int result = 0;
        String dateStr = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        dateStr = sdf.format(date);
        if (dateStr.length() > 0)
            result = Integer.parseInt(dateStr);
        return result;
    }

    public static LocalDate DateToLocal(Date date) {
        Instant instant = date.toInstant();
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        return zdt.toLocalDate();
    }

    public static String LocalToString(LocalDate local) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = local.atStartOfDay().atZone(zone).toInstant();
        Date d = Date.from(instant);
        return sdf.format(d);
    }

    /**
     * 获取前一天的 Date
     *
     * @param localDateString
     * @return
     */
    public static Date getMinusDate(String localDateString) {
        LocalDate localDate = LocalDate.parse(localDateString);
        ZoneId zone = ZoneId.of("UTC");
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * 获取后一天的 Date
     *
     * @param localDateString
     * @return
     */
    public static Date getPlusDate(String localDateString) {
        LocalDate localDate = LocalDate.parse(localDateString);

        LocalDate returnLocalDate = localDate.plusDays(1);

        ZoneId zone = ZoneId.of("UTC");
        Instant instant = returnLocalDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

    public static String MONGODBSETTING_HOST;
    public static int MONGODBSETTING_PORT;
    public static String MONGODBSETTING_USER;
    public static String MONGODBSETTING_PWD;
    public static String MONGODBSETTING_DB;

    static {


        Properties db_Properties = new Properties();
        FileInputStream in = null;
        try {
            String classPath = java.net.URLDecoder.decode(Thread.currentThread().getContextClassLoader().getResource("").getPath(), "utf-8");
            int index = classPath.indexOf("WEB-INF");
            String infPath = classPath.substring(0, index) + "WEB-INF" + File.separator;
            in = new FileInputStream(infPath + "mongodb.properties");
            db_Properties.load(in);
            MONGODBSETTING_HOST = db_Properties.getProperty("mongo.host");
            MONGODBSETTING_PORT = Integer.parseInt(db_Properties.getProperty("mongo.port"));
            MONGODBSETTING_USER = db_Properties.getProperty("mongo.user");
            MONGODBSETTING_PWD = db_Properties.getProperty("mongo.pwd");
            MONGODBSETTING_DB = db_Properties.getProperty("mongo.db");
            MongoDao mangoDao = new MongoDao();
            FindIterable<Document> matchQueueDocuments = mangoDao.findAll("MatchTaskQueue");
            for (Document document : matchQueueDocuments) {
                if (document.get("taskName").toString().length() > 0) {
                    if (!matchQueue.contains(document.get("taskName").toString())) {
                        matchQueue.put(document.get("taskName").toString());
                    }
                }
            }
            mangoDao.close();
        } catch (Exception ex) {
            logger.error(ex.toString());
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

    }

    public static boolean IsDateString(String s) {
        boolean result = true;
        try {
            LocalDate.parse(s);
        } catch (Exception e) {
            result = false;
        }
        return result;
    }


    public static String AES(String inputStr) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte md5[] = md.digest(inputStr.getBytes());
        return byte2hex(md5);
    }

    public static String byte2hex(byte[] b) // 二行制转字符串
    {
        String hs = "";
        String tmp = "";
        for (byte aB : b) {
            tmp = (Integer.toHexString(aB & 0XFF));
            if (tmp.length() == 1)
                hs = hs + "0" + tmp;
            else
                hs = hs + tmp;
        }
        return hs.toUpperCase();
    }

    public static ExportLog ImportInfo(File file) {
        ExportLog exportLog = null;
        InputStream fileStream;
        try {
            fileStream = new FileInputStream(file);
            JAXBContext jaxbContext = JAXBContext.newInstance(ExportLog.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            exportLog = (ExportLog) jaxbUnmarshaller.unmarshal(fileStream);
            fileStream.close();
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return exportLog;
    }


    public static LocalDate getUnLeapYearBirthDate(int dateOfBirth) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date dateBirth = null;
        LocalDate localBirth = null;
        try {
            dateBirth = sdf.parse(String.valueOf(dateOfBirth));
        } catch (ParseException e) {
            logger.error(e.toString());
        }

        localBirth = DateToLocal(dateBirth);
        if (localBirth.isLeapYear()) {
            int monthOfBirth = localBirth.getMonthValue();
            if (monthOfBirth == 2) {
                int dayOfBirth = localBirth.getDayOfMonth();
                if (dayOfBirth == 29) {
                    localBirth.minusDays(1);
                }
            }
        }

        return localBirth;
    }

    public static Date getDateFromInt(int dateOfBirth) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = sdf.parse(String.valueOf(dateOfBirth));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Sort.Direction getSortDirection(String sortStr) {
        Sort.Direction direction = Sort.Direction.ASC;
        if (sortStr.length() > 0) {
            if (sortStr.toLowerCase().equals("desc")) {
                direction = Sort.Direction.DESC;
            }
        }
        return direction;
    }
}

