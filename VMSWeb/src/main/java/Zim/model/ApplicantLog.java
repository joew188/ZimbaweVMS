package Zim.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import Zim.common.CustomDateSerializer;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Laxton-Joe on 2017/2/7.
 */
@Document(collection = "ApplicantLog")
public class ApplicantLog implements Serializable {
//
//    public  ApplicantLog(String applicantId, String logEvent)
//    {
//        this.applicantId=applicantId;
//        this.logEvent=logEvent;
//        this.logTime=new Date();
//    }

    private String applicantId;
    private String probeId;
    private String referenceId;
    private String logEvent;
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date logTime;

    public ApplicantLog() {

    }

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
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

    public String getLogEvent() {
        return logEvent;
    }

    public void setLogEvent(String logEvent) {
        this.logEvent = logEvent;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }
}
