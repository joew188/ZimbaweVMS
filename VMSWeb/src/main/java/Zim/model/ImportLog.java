package Zim.model;

import Zim.common.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by Laxton-Joe on 2017/7/12.
 */
@Document(collection = "ImportLog")
public class ImportLog {

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
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date matchBeginTime;
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date matchFinishTime;
    private int importTotal;
    private int importMale;
    private int importFemale;
    private int matchedTotal;
    private String status;

    private String lastSerialNumber;

    private String firstSerialNumber;

    private int kitTotal;

    private String nameOfOperator;

    private String idNumberOfOperator;

    private String operatorGuid;


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


//    public DBObject toDBObject() {
//        DBObject dbObject = new BasicDBObject();
//
//        dbObject.put("_id", this.get_id());
//        dbObject.put("name", this.getName());
//        dbObject.put("deviceName", this.getDeviceName());
//        dbObject.put("exportDateTime", this.getExportDateTime());
//        dbObject.put("exportTotal", this.getExportTotal());
//
//        dbObject.put("exportMale", this.getExportMale());
//        dbObject.put("exportFemale", this.getExportFemale());
//        dbObject.put("importBeginTime", this.getImportBeginTime());
//        dbObject.put("importFinishTime", this.getImportFinishTime());
//        dbObject.put("importTotal", this.getImportTotal());
//        dbObject.put("importMale", this.getImportMale());
//        dbObject.put("importFemale", this.getImportFemale());
//        dbObject.put("status", this.getStatus());
//
//        return dbObject;
//    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getLastSerialNumber() {
        return lastSerialNumber;
    }

    public void setLastSerialNumber(String lastSerialNumber) {
        this.lastSerialNumber = lastSerialNumber;
    }

    public String getFirstSerialNumber() {
        return firstSerialNumber;
    }

    public void setFirstSerialNumber(String firstSerialNumber) {
        this.firstSerialNumber = firstSerialNumber;
    }

    public int getKitTotal() {
        return kitTotal;
    }

    public void setKitTotal(int kitTotal) {
        this.kitTotal = kitTotal;
    }

    public String getNameOfOperator() {
        return nameOfOperator;
    }

    public void setNameOfOperator(String nameOfOperator) {
        this.nameOfOperator = nameOfOperator;
    }

    public String getIdNumberOfOperator() {
        return idNumberOfOperator;
    }

    public void setIdNumberOfOperator(String idNumberOfOperator) {
        this.idNumberOfOperator = idNumberOfOperator;
    }

    public String getOperatorGuid() {
        return operatorGuid;
    }

    public void setOperatorGuid(String operatorGuid) {
        this.operatorGuid = operatorGuid;
    }

    public org.bson.Document toDocument() {
        org.bson.Document document = new org.bson.Document();


        document.append("name", this.getName());
        document.append("deviceName", this.getDeviceName());
        document.append("exportDateTime", this.getExportDateTime());
        document.append("exportTotal", this.getExportTotal());

        document.append("exportMale", this.getExportMale());
        document.append("exportFemale", this.getExportFemale());
        document.append("importBeginTime", this.getImportBeginTime());
        document.append("importFinishTime", this.getImportFinishTime());
        document.append("matchFinishTime", this.getMatchFinishTime());
        document.append("importTotal", this.getImportTotal());
        document.append("importMale", this.getImportMale());
        document.append("importFemale", this.getImportFemale());
        document.append("status", this.getStatus());

        return document;
    }

    public Date getMatchBeginTime() {
        return matchBeginTime;
    }

    public void setMatchBeginTime(Date matchBeginTime) {
        this.matchBeginTime = matchBeginTime;
    }

    public Date getMatchFinishTime() {
        return matchFinishTime;
    }

    public void setMatchFinishTime(Date matchFinishTime) {
        this.matchFinishTime = matchFinishTime;
    }

    public int getMatchedTotal() {
        return matchedTotal;
    }

    public void setMatchedTotal(int matchedTotal) {
        this.matchedTotal = matchedTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
