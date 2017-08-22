package Zim.model;

import Zim.common.CustomDateSerializer;
import Zim.common.SystemHelper;
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
    private String referenceId;
    private int leven;
    private String status;
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date createdTime;

    public Duplicate(String probeId, String referenceId, int leven) {
       this.probeId= probeId;
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

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("_id", this.get_id());
        map.put("probeId", this.probeId);
        map.put("referenceId", this.referenceId);
        map.put("status", this.status);
        map.put("leven", this.leven);
        map.put("createdTime", SystemHelper.getNowString());
        return map;
    }

    public static List<String> getColumns() {
        List<String> result = new ArrayList<>();
        result.add("_id");
        result.add("probeId");
        result.add("referenceId");
        result.add("leven");
        result.add("createdTime");
        result.add("status");
        return result;
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
}
