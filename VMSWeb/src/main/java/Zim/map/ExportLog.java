//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2017.07.11 时间 05:15:44 PM CST
//

package Zim.map;

import Zim.common.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "serialNumber",
        "lastSerialNumber",
        "firstSerialNumber",
        "name",
        "deviceName",
        "exportDateTime",
        "total",
        "male",
        "female",
        "kitTotal",
        "nameOfOperator",
        "idNumberOfOperator",
        "operatorGuid"
})
@XmlRootElement(name = "ExportLog")
public class ExportLog {
    @XmlElement(name = "SerialNumber")
    private short serialNumber;
    @XmlElement(name = "LastSerialNumber")
    private String lastSerialNumber;
    @XmlElement(name = "FirstSerialNumber")
    private String firstSerialNumber;
    @XmlElement(name = "Name", required = true)
    private String name;
    @XmlElement(name = "DeviceName", required = true)
    private String deviceName;
    @JsonSerialize(using = CustomDateSerializer.class)
    @XmlElement(name = "ExportDateTime", required = true)
    @XmlSchemaType(name = "dateTime")
    private Date exportDateTime;
    @XmlElement(name = "Total")
    @XmlSchemaType(name = "unsignedInt")
    private int total;
    @XmlElement(name = "Male")
    @XmlSchemaType(name = "unsignedInt")
    private int male;
    @XmlElement(name = "Female")
    @XmlSchemaType(name = "unsignedInt")
    private int female;


    @XmlElement(name = "KitTotal")
    @XmlSchemaType(name = "unsignedInt")
    private int kitTotal;

    @XmlElement(name = "NameOfOperator")
    private String nameOfOperator;

    @XmlElement(name = "IDNumberOfOperator")
    private String idNumberOfOperator;

    @XmlElement(name = "OperatorGuid")
    private String operatorGuid;

    public short getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(short serialNumber) {
        this.serialNumber = serialNumber;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getMale() {
        return male;
    }

    public void setMale(int male) {
        this.male = male;
    }

    public int getFemale() {
        return female;
    }

    public void setFemale(int female) {
        this.female = female;
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
}


