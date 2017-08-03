package Zim.model;

import Zim.common.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Laxton-Joe on 2017/7/12.
 */
@Document(collection = "ImportLog")
public class ImportLog {

    private final static String ID = "_id";
    private final static String NAME = "name";
    private final static String DEVICENAME = "deviceName";
    private final static String EXPORTDATETIME = "exportDateTime";
    private final static String EXPORTTOTAL = "exportTotal";
    private final static String EXPORTMALE = "exportMale";
    private final static String EXPORTFEMALE = "exportFemale";
    private final static String IMPORTBEGINTIME = "importBeginTime";
    private final static String IMPORTFINISHTIME = "importFinishTime";
    private final static String IMPORTTOTAL = "importTotal";
    private final static String IMPORTMALE = "importMale";
    private final static String IMPORTFEMALE = "importFemale";
    private final static String STATUS = "status";

    public static List<String> getColumns() {
        List<String> result = new ArrayList<>();
        result.add("_id");
        result.add("name");
        result.add("deviceName");
        result.add("exportDateTime");
        result.add("total");
        result.add("male");
        result.add("female");
        result.add("beginTime");
        result.add("finishTime");
        result.add("success");
        result.add("fail");
        result.add("gap");
        result.add("status");
        return result;
    }

    private String _id;
    private String name;
    private String deviceName;
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date exportDateTime;
    private int exportTotal;
    private int exportMale;
    private int exportFemale;
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date importBeginTime;
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date importFinishTime;
    private int importTotal;
    private int importMale;
    private int importFemale;
    private int status;

    public static String getID() {
        return ID;
    }

    public static String getNAME() {
        return NAME;
    }

    public static String getDEVICENAME() {
        return DEVICENAME;
    }

    public static String getEXPORTDATETIME() {
        return EXPORTDATETIME;
    }

    public static String getEXPORTTOTAL() {
        return EXPORTTOTAL;
    }

    public static String getEXPORTMALE() {
        return EXPORTMALE;
    }

    public static String getEXPORTFEMALE() {
        return EXPORTFEMALE;
    }

    public static String getIMPORTBEGINTIME() {
        return IMPORTBEGINTIME;
    }

    public static String getIMPORTFINISHTIME() {
        return IMPORTFINISHTIME;
    }

    public static String getIMPORTTOTAL() {
        return IMPORTTOTAL;
    }

    public static String getIMPORTMALE() {
        return IMPORTMALE;
    }

    public static String getIMPORTFEMALE() {
        return IMPORTFEMALE;
    }

    public static String getSTATUS() {
        return STATUS;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Date getExportDateTime() {
        return exportDateTime;
    }

    public void setExportDateTime(Date exportDateTime) {
        this.exportDateTime = exportDateTime;
    }

    public int getExportTotal() {
        return exportTotal;
    }

    public void setExportTotal(int exportTotal) {
        this.exportTotal = exportTotal;
    }

    public int getExportMale() {
        return exportMale;
    }

    public void setExportMale(int exportMale) {
        this.exportMale = exportMale;
    }

    public int getExportFemale() {
        return exportFemale;
    }

    public void setExportFemale(int exportFemale) {
        this.exportFemale = exportFemale;
    }

    public Date getImportBeginTime() {
        return importBeginTime;
    }

    public void setImportBeginTime(Date importBeginTime) {
        this.importBeginTime = importBeginTime;
    }

    public Date getImportFinishTime() {
        return importFinishTime;
    }

    public void setImportFinishTime(Date importFinishTime) {
        this.importFinishTime = importFinishTime;
    }

    public int getImportTotal() {
        return importTotal;
    }

    public void setImportTotal(int importTotal) {
        this.importTotal = importTotal;
    }

    public int getImportMale() {
        return importMale;
    }

    public void setImportMale(int importMale) {
        this.importMale = importMale;
    }

    public int getImportFemale() {
        return importFemale;
    }

    public void setImportFemale(int importFemale) {
        this.importFemale = importFemale;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DBObject toDBObject() {
        DBObject dbObject = new BasicDBObject();

        dbObject.put("_id", this.get_id());
        dbObject.put("name", this.getName());
        dbObject.put("deviceName", this.getDeviceName());
        dbObject.put("exportDateTime", this.getExportDateTime());
        dbObject.put("exportTotal", this.getExportTotal());

        dbObject.put("exportMale", this.getExportMale());
        dbObject.put("exportFemale", this.getExportFemale());
        dbObject.put("importBeginTime", this.getImportBeginTime());
        dbObject.put("importFinishTime", this.getImportFinishTime());
        dbObject.put("importTotal", this.getImportTotal());
        dbObject.put("importMale", this.getImportMale());
        dbObject.put("importFemale", this.getImportFemale());
        dbObject.put("status", this.getStatus());

        return dbObject;
    }
}
