package Zim.common;
import Zim.map.ExportLog;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.XMLGregorianCalendar;

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

/**
 * Created by Laxton-Joe on 2017/2/16.
 */
public class SystemHelper {
    public static final String SHORTDATEFORMATSTRING = "dd/MM/yyyy";
    public static final String LONGTDATEFORMATSTRING = "dd/MM/yyyy hh:mm:ss";

    public static String getBirthDateString(XMLGregorianCalendar xgcal) {
        Date date = xgcal.toGregorianCalendar().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(SHORTDATEFORMATSTRING);

        String dateTimeString = simpleDateFormat.format(date);
        return dateTimeString;


    }

    public static Calendar getBirthDate(XMLGregorianCalendar calendar) {

        Calendar cal = Calendar.getInstance();

        return cal;
    }

    public static int getAge(String dateOfBirthStr) {
        int age = 0;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(SHORTDATEFORMATSTRING);
            Date date = sdf.parse(dateOfBirthStr);

            Calendar dateOfBirth = Calendar.getInstance();
            dateOfBirth.setTime(date);
            Calendar now = Calendar.getInstance();
            if (dateOfBirth != null) {
                if (dateOfBirth.before(now)) {
                    age = now.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
                    if (now.get(Calendar.DAY_OF_YEAR) < dateOfBirth.get(Calendar.DAY_OF_YEAR)) {
                        age -= 1;
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return age;
    }

    public static Object getNowString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(LONGTDATEFORMATSTRING);
        Date date = new Date();
        String dateTimeString = simpleDateFormat.format(date);
        return dateTimeString;
    }

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


    public static int DateToInt(Date date) {
        int result = 0;
        String dateStr = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        sdf.setTimeZone(TimeZone.getTimeZone(SysConfigUtil.getSetting("system-timezone")));
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

    public static Date LocalToDate(LocalDate local) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = local.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);

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
        //  LocalDate returnLocalDate= localDate.minusDays(1);
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
            //  MongoDBDaoImpl mongoDBDaoImpl = MongoDBDaoImpl.getMongoDBDaoImpl();
//            List<DBObject> aProvince = mongoDBDaoImpl.find("vms", "Province", -1);
//            for (DBObject doj : aProvince) {
//                Province aaa= ((Province) doj);
//
//            }
        } catch (Exception ex) {
            //log
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
            LocalDate localDate = LocalDate.parse(s);
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    public static List<Integer> getBetweenDate(int birthDayValue) {
        List<Integer> result = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date dateBirth = null;
        LocalDate localBirth = null;
        try {
            dateBirth = sdf.parse(String.valueOf(birthDayValue));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (dateBirth != null) {
            localBirth = SystemHelper.DateToLocal(dateBirth);
            if (localBirth.isLeapYear()) {
                int monthOfBirth = localBirth.getMonthValue();
                if (monthOfBirth == 2) {
                    int dayOfBirth = localBirth.getDayOfMonth();
                    if (monthOfBirth == 29) {
                        localBirth.minusDays(1);
                    }
                }
            }
        }

        LocalDate startDate = localBirth.minusYears(10);
        LocalDate endDate = localBirth.plusYears(10);
        int iStart = Integer.parseInt(SystemHelper.LocalToString(startDate));
        int iEend = Integer.parseInt(SystemHelper.LocalToString(endDate));
        result.add(iStart);
        result.add(iEend);
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
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs.toUpperCase();
    }

    public static ExportLog ImportInfo(File file) {
        ExportLog exportLog = null;
        try {
            InputStream fileStream = new FileInputStream(file);
            JAXBContext jaxbContext = JAXBContext.newInstance(ExportLog.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            exportLog = (ExportLog) jaxbUnmarshaller.unmarshal(fileStream);
        } catch (Exception e) {


        }
        return exportLog;
    }
//
//    public static Map<String, Object> xmlString2Map(String xmlStr) {
//        Map<String, Object> map = new HashMap<String, Object>();
//        Document doc;
//        try {
//            doc = DocumentHelper.parseText(xmlStr);
//            Element el = doc.getRootElement();
//            map = recGetXmlElementValue(el, map);
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        }
//        return map;
//    }
//
//    private static Map<String, Object> recGetXmlElementValue(Element ele, Map<String, Object> map) {
//        List<Element> eleList = ele.elements();
//        if (eleList.size() == 0) {
//            map.put(ele.getName(), ele.getTextTrim());
//            return map;
//        } else {
//            for (Iterator<Element> iter = eleList.iterator(); iter.hasNext(); ) {
//                Element innerEle = iter.next();
//                recGetXmlElementValue(innerEle, map);
//            }
//            return map;
//        }
//    }

    public static String readXML(String path) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                fileInputStream));
        String xml = "";
        String line;
        while ((line = reader.readLine()) != null) {
            xml = xml + line;
        }
        reader.close();
        return xml;
    }

    public static LocalDate getUnLeapYearBirthDate(int dateOfBirth) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date dateBirth = null;
        LocalDate localBirth = null;
        try {
            dateBirth = sdf.parse(String.valueOf(dateOfBirth));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        localBirth = SystemHelper.DateToLocal(dateBirth);
        if (localBirth.isLeapYear()) {
            int monthOfBirth = localBirth.getMonthValue();
            if (monthOfBirth == 2) {
                int dayOfBirth = localBirth.getDayOfMonth();
                if (monthOfBirth == 29) {
                    localBirth.minusDays(1);
                }
            }
        }

        return localBirth;
    }

    public static List<String> getApplicantColumns() {
        List<String> result = new ArrayList<>();
        result.add("_id");
        result.add("applicantDemographic.gender");
        result.add("localAuthority");
        result.add("houseNumber");
        result.add("title");
        result.add("disablity");
        result.add("nationalIDCardNumber");
        result.add("streetName");
        result.add("province");
        result.add("provinceName");
        result.add("district");
        result.add("districtName");
        result.add("constituency");
        result.add("constituencyName");
        result.add("ward");
        result.add("wardName");
        result.add("claimReference");
        result.add("applicantDemographic.surname");
        result.add("identificationCardType");
        result.add("identificationCardType");
        result.add("fourthName");
        result.add("applicantDemographic.dateOfBirth");
        result.add("dateOfRegistration");
        result.add("applicantDemographic.personName");
        result.add("registrationNumber");
        result.add("district");
        result.add("gisLongitude");
        result.add("suburb");
        result.add("middleName");
        result.add("gisLatitude");
        result.add("individualsId");
        result.add("status");
        result.add("operatorGuid");
        result.add("operatorName");
        result.add("deviceName");
        result.add("beginCreateDatetime");
        result.add("endCreateDatetime");
        result.add("beginEditDatetime");
        result.add("endEditDatetime");
        return result;
    }

//    public static Map<String, Object> objectToMap(Object obj) throws Exception {
//        if (obj == null)
//            return null;
//
//        Map<String, Object> map = new HashMap<String, Object>();
//
//        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
//        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
//        for (PropertyDescriptor property : propertyDescriptors) {
//            String key = property.getName();
//            if (key.compareToIgnoreCase("class") == 0) {
//                continue;
//            }
//            Method getter = property.getReadMethod();
//            Object value = getter != null ? getter.invoke(obj) : null;
//            map.put(key, value);
//        }
//
//        return map;
//    }
//
//    public static Map<?, ?> objectToMap(Object obj) {
//        if(obj == null)
//            return null;
//
//        return new org.apache.commons.beanutils.BeanMap(obj);
//    }
}

