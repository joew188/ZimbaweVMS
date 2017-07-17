package Zim.map;

/**
 * Created by Laxton-Joe on 2017/7/13.
 */

import Zim.common.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigInteger;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "string",
        "stringNull",
        "date",
        "dateNull",
        "dateTime",
        "dateTimeNull",
        "_boolean",
        "booleanNull",
        "unsignedByte",
        "unsignedByteNull",
        "unsignedInt",
        "unsignedIntNull",
        "unsignedLong",
        "unsignedLongNull",
        "unsignedShort",
        "unsignedShortNull",
        "_byte",
        "byteNull",
        "_short",
        "shortNull",
        "_int",
        "intNull",
        "_long",
        "longNull"
})
@XmlRootElement(name = "test")
public class Test {
    @XmlElement(required = true)
    protected String string;
    @XmlElement(required = true, nillable = true)
    protected String stringNull;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar date;
    @JsonSerialize(using = CustomDateSerializer.class)
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    private Date dateNull;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    private Date dateTime;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateTimeNull;
    @XmlElement(name = "boolean")
    protected boolean _boolean;
    @XmlElement(required = true, type = Boolean.class, nillable = true)
    protected Boolean booleanNull;
    @XmlSchemaType(name = "unsignedByte")
    protected short unsignedByte;
    @XmlElement(required = true, type = Short.class, nillable = true)
    @XmlSchemaType(name = "unsignedByte")
    protected Short unsignedByteNull;
    @XmlSchemaType(name = "unsignedInt")
    protected long unsignedInt;
    @XmlElement(required = true, type = Long.class, nillable = true)
    @XmlSchemaType(name = "unsignedInt")
    protected Long unsignedIntNull;
    @XmlElement(required = true)
    @XmlSchemaType(name = "unsignedLong")
    protected BigInteger unsignedLong;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "unsignedLong")
    protected BigInteger unsignedLongNull;
    @XmlSchemaType(name = "unsignedShort")
    protected int unsignedShort;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    @XmlSchemaType(name = "unsignedShort")
    protected Integer unsignedShortNull;
    @XmlElement(name = "byte")
    protected byte _byte;
    @XmlElement(required = true, type = Byte.class, nillable = true)
    protected Byte byteNull;
    @XmlElement(name = "short")
    protected short _short;
    @XmlElement(required = true, type = Short.class, nillable = true)
    protected Short shortNull;
    @XmlElement(name = "int")
    protected int _int;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer intNull;
    @XmlElement(name = "long")
    protected long _long;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long longNull;

    /**
     * 获取string属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getString() {
        return string;
    }

    /**
     * 设置string属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setString(String value) {
        this.string = value;
    }

    /**
     * 获取stringNull属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getStringNull() {
        return stringNull;
    }

    /**
     * 设置stringNull属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setStringNull(String value) {
        this.stringNull = value;
    }

    /**
     * 获取date属性的值。
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * 设置date属性的值。
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }



    /**
     * 获取dateTimeNull属性的值。
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getDateTimeNull() {
        return dateTimeNull;
    }

    /**
     * 设置dateTimeNull属性的值。
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setDateTimeNull(XMLGregorianCalendar value) {
        this.dateTimeNull = value;
    }

    /**
     * 获取boolean属性的值。
     */
    public boolean isBoolean() {
        return _boolean;
    }

    /**
     * 设置boolean属性的值。
     */
    public void setBoolean(boolean value) {
        this._boolean = value;
    }

    /**
     * 获取booleanNull属性的值。
     *
     * @return possible object is
     * {@link Boolean }
     */
    public Boolean isBooleanNull() {
        return booleanNull;
    }

    /**
     * 设置booleanNull属性的值。
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setBooleanNull(Boolean value) {
        this.booleanNull = value;
    }

    /**
     * 获取unsignedByte属性的值。
     */
    public short getUnsignedByte() {
        return unsignedByte;
    }

    /**
     * 设置unsignedByte属性的值。
     */
    public void setUnsignedByte(short value) {
        this.unsignedByte = value;
    }

    /**
     * 获取unsignedByteNull属性的值。
     *
     * @return possible object is
     * {@link Short }
     */
    public Short getUnsignedByteNull() {
        return unsignedByteNull;
    }

    /**
     * 设置unsignedByteNull属性的值。
     *
     * @param value allowed object is
     *              {@link Short }
     */
    public void setUnsignedByteNull(Short value) {
        this.unsignedByteNull = value;
    }

    /**
     * 获取unsignedInt属性的值。
     */
    public long getUnsignedInt() {
        return unsignedInt;
    }

    /**
     * 设置unsignedInt属性的值。
     */
    public void setUnsignedInt(long value) {
        this.unsignedInt = value;
    }

    /**
     * 获取unsignedIntNull属性的值。
     *
     * @return possible object is
     * {@link Long }
     */
    public Long getUnsignedIntNull() {
        return unsignedIntNull;
    }

    /**
     * 设置unsignedIntNull属性的值。
     *
     * @param value allowed object is
     *              {@link Long }
     */
    public void setUnsignedIntNull(Long value) {
        this.unsignedIntNull = value;
    }

    /**
     * 获取unsignedLong属性的值。
     *
     * @return possible object is
     * {@link BigInteger }
     */
    public BigInteger getUnsignedLong() {
        return unsignedLong;
    }

    /**
     * 设置unsignedLong属性的值。
     *
     * @param value allowed object is
     *              {@link BigInteger }
     */
    public void setUnsignedLong(BigInteger value) {
        this.unsignedLong = value;
    }

    /**
     * 获取unsignedLongNull属性的值。
     *
     * @return possible object is
     * {@link BigInteger }
     */
    public BigInteger getUnsignedLongNull() {
        return unsignedLongNull;
    }

    /**
     * 设置unsignedLongNull属性的值。
     *
     * @param value allowed object is
     *              {@link BigInteger }
     */
    public void setUnsignedLongNull(BigInteger value) {
        this.unsignedLongNull = value;
    }

    /**
     * 获取unsignedShort属性的值。
     */
    public int getUnsignedShort() {
        return unsignedShort;
    }

    /**
     * 设置unsignedShort属性的值。
     */
    public void setUnsignedShort(int value) {
        this.unsignedShort = value;
    }

    /**
     * 获取unsignedShortNull属性的值。
     *
     * @return possible object is
     * {@link Integer }
     */
    public Integer getUnsignedShortNull() {
        return unsignedShortNull;
    }

    /**
     * 设置unsignedShortNull属性的值。
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setUnsignedShortNull(Integer value) {
        this.unsignedShortNull = value;
    }

    /**
     * 获取byte属性的值。
     */
    public byte getByte() {
        return _byte;
    }

    /**
     * 设置byte属性的值。
     */
    public void setByte(byte value) {
        this._byte = value;
    }

    /**
     * 获取byteNull属性的值。
     *
     * @return possible object is
     * {@link Byte }
     */
    public Byte getByteNull() {
        return byteNull;
    }

    /**
     * 设置byteNull属性的值。
     *
     * @param value allowed object is
     *              {@link Byte }
     */
    public void setByteNull(Byte value) {
        this.byteNull = value;
    }

    /**
     * 获取short属性的值。
     */
    public short getShort() {
        return _short;
    }

    /**
     * 设置short属性的值。
     */
    public void setShort(short value) {
        this._short = value;
    }

    /**
     * 获取shortNull属性的值。
     *
     * @return possible object is
     * {@link Short }
     */
    public Short getShortNull() {
        return shortNull;
    }

    /**
     * 设置shortNull属性的值。
     *
     * @param value allowed object is
     *              {@link Short }
     */
    public void setShortNull(Short value) {
        this.shortNull = value;
    }

    /**
     * 获取int属性的值。
     */
    public int getInt() {
        return _int;
    }

    /**
     * 设置int属性的值。
     */
    public void setInt(int value) {
        this._int = value;
    }

    /**
     * 获取intNull属性的值。
     *
     * @return possible object is
     * {@link Integer }
     */
    public Integer getIntNull() {
        return intNull;
    }

    /**
     * 设置intNull属性的值。
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setIntNull(Integer value) {
        this.intNull = value;
    }

    /**
     * 获取long属性的值。
     */
    public long getLong() {
        return _long;
    }

    /**
     * 设置long属性的值。
     */
    public void setLong(long value) {
        this._long = value;
    }

    /**
     * 获取longNull属性的值。
     *
     * @return possible object is
     * {@link Long }
     */
    public Long getLongNull() {
        return longNull;
    }

    /**
     * 设置longNull属性的值。
     *
     * @param value allowed object is
     *              {@link Long }
     */
    public void setLongNull(Long value) {
        this.longNull = value;
    }

    public Date getDateNull() {
        return dateNull;
    }

    public void setDateNull(Date dateNull) {
        this.dateNull = dateNull;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}
