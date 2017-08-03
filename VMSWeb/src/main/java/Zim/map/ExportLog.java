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
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "name",
        "deviceName",
        "exportDateTime",
        "total",
        "male",
        "female"
})
@XmlRootElement(name = "ExportLog")
public class ExportLog {

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "DeviceName", required = true)
    protected String deviceName;
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

    /**
     * 获取name属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getName() {
        return name;
    }

    /**
     * 设置name属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * 获取deviceName属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * 设置deviceName属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDeviceName(String value) {
        this.deviceName = value;
    }








    public Date getExportDateTime() {
        return exportDateTime;
    }

    public void setExportDateTime(Date exportDateTime) {
        this.exportDateTime = exportDateTime;
    }

    public int getFemale() {
        return female;
    }

    public void setFemale(int female) {
        this.female = female;
    }

    public int getMale() {
        return male;
    }

    public void setMale(int male) {
        this.male = male;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

