package Zim.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import Zim.common.CustomDateSerializer;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Laxton-Joe on 2017/2/7.
 */
@Document(collection = "ApplicantLog")
public class ApplicantLog implements Serializable {

    public  ApplicantLog(String appId, String event)
    {
        this.applicantId=appId;
        this.LogEvent=event;
        this.LogTime=new Date();
    }

    private String applicantId;
    private String probeId;
    private String referenceId;
    private String LogEvent;
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date LogTime;

    public ApplicantLog() {

    }

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    public String getLogEvent() {
        return LogEvent;
    }

    public void setLogEvent(String logEvent) {
        LogEvent = logEvent;
    }

    public Date getLogTime() {
        return LogTime;
    }

    public void setLogTime(Date logTime) {
        LogTime = logTime;
    }

    /***
     * 保存到MongoDB 的 Map
     *
     * @param
     * @return
     */
    public static Map<String, Object> toLogMap(String appId,String logEvent)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("applicantId",appId);
        map.put("LogEvent",logEvent);
        map.put("LogTime",new Date());
        return map;
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
}
