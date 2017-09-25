package Zim.model;

import Zim.common.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Laxton-Joe on 2017/2/20.
 */

@Document(collection = "Duplicate")
public class Duplicate implements Serializable {
    private String _id;
    private String probeId;
    private String probeNumber;
    private String referenceNumber;
    private String referenceId;
    private int leven;
    private String status;
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date createdTime;

    public Duplicate(String probeId, String referenceId, int leven) {
        this.probeId = probeId;
        this.referenceId = referenceId;
        this.leven = leven;
        this.status = "Pending";
        this.createdTime = new Date();
    }

    public String getProbeId() {
        return probeId;
    }

    public void setProbeId(String probeId) {
        this.probeId = probeId;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




    public int getLeven() {
        return leven;
    }

    public void setLeven(int leven) {
        this.leven = leven;
    }

    private Applicant probe;
    private Applicant reference;

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Applicant getProbe() {
        return probe;
    }

    public void setProbe(Applicant probe) {
        this.probe = probe;
    }

    public Applicant getReference() {
        return reference;
    }

    public void setReference(Applicant reference) {
        this.reference = reference;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getProbeNumber() {
        return probeNumber;
    }

    public void setProbeNumber(String probeNumber) {
        this.probeNumber = probeNumber;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }
}
