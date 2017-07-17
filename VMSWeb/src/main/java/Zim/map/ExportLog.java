//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2017.07.11 时间 05:15:44 PM CST
//

package Zim.map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
    @XmlElement(name = "ExportDateTime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar exportDateTime;
    @XmlElement(name = "Total")
    @XmlSchemaType(name = "unsignedInt")
    protected long total;
    @XmlElement(name = "Male")
    @XmlSchemaType(name = "unsignedInt")
    protected long male;
    @XmlElement(name = "Female")
    @XmlSchemaType(name = "unsignedInt")
    protected long female;

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

    /**
     * 获取exportDateTime属性的值。
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getExportDateTime() {
        return exportDateTime;
    }

    /**
     * 设置exportDateTime属性的值。
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setExportDateTime(XMLGregorianCalendar value) {
        this.exportDateTime = value;
    }

    /**
     * 获取total属性的值。
     */
    public long getTotal() {
        return total;
    }

    /**
     * 设置total属性的值。
     */
    public void setTotal(long value) {
        this.total = value;
    }

    /**
     * 获取male属性的值。
     */
    public long getMale() {
        return male;
    }

    /**
     * 设置male属性的值。
     */
    public void setMale(long value) {
        this.male = value;
    }

    /**
     * 获取female属性的值。
     */
    public long getFemale() {
        return female;
    }

    /**
     * 设置female属性的值。
     */
    public void setFemale(long value) {
        this.female = value;
    }

}

