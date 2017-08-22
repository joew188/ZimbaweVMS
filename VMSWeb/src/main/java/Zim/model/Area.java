package Zim.model;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Created by Laxton-Joe on 2017/6/12.
 */
@Document(collection = "Area")
public class Area implements Serializable {

    public DBObject toDBObject() {
        DBObject dbObject = new BasicDBObject();

        dbObject.put("areaId", this.getAreaId());
        dbObject.put("name", this.getName());
        dbObject.put("type", this.getType());
        dbObject.put("streetName", this.getStreetName());
        dbObject.put("standNumber", this.getStandNumber());
        dbObject.put("streetNumber", this.getStreetNumber());
        dbObject.put("surburb", this.getSurburb());
        dbObject.put("stationCode", this.getStationCode());
        dbObject.put("localAuthority", this.getLocalAuthority());
        dbObject.put("parentAreaID", this.getParentAreaID());
        dbObject.put("lat", this.getLat());
        dbObject.put("lng", this.getLng());
        return dbObject;
    }


    private String _id;
    private int areaId;
    private String name;
    private int type;
    private String streetName;
    private String standNumber;
    private String streetNumber;
    private String surburb;
    private String stationCode;
    private String localAuthority;
    private int parentAreaID;
    private String lat;
    private String lng;

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStandNumber() {
        return standNumber;
    }

    public void setStandNumber(String standNumber) {
        this.standNumber = standNumber;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getSurburb() {
        return surburb;
    }

    public void setSurburb(String surburb) {
        this.surburb = surburb;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getLocalAuthority() {
        return localAuthority;
    }

    public void setLocalAuthority(String localAuthority) {
        this.localAuthority = localAuthority;
    }



    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }



    public int getParentAreaID() {
        return parentAreaID;
    }

    public void setParentAreaID(int parentAreaID) {
        this.parentAreaID = parentAreaID;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
