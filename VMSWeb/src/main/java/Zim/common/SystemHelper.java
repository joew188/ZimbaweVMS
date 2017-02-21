package Zim.common;

import javax.xml.datatype.XMLGregorianCalendar;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

            String classPath = java.net.URLDecoder.decode( Thread.currentThread().getContextClassLoader().getResource("").getPath(),"utf-8");
            int index = classPath.indexOf("WEB-INF");
            String infPath = classPath.substring(0, index) + "WEB-INF" + File.separator;
            in = new FileInputStream(infPath + "mongodb.properties");
            db_Properties.load(in);
            MONGODBSETTING_HOST = db_Properties.getProperty("mongo.host");
            MONGODBSETTING_PORT = Integer.parseInt(db_Properties.getProperty("mongo.port"));
            MONGODBSETTING_USER = db_Properties.getProperty("mongo.user");
            MONGODBSETTING_PWD = db_Properties.getProperty("mongo.pwd");
            MONGODBSETTING_DB=db_Properties.getProperty("mongo.db");
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

    public static  List<Integer> getBetweenDate(int birthDayValue) {
        List<Integer> result=new ArrayList<>();
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
}

