package Zim.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Laxton-Joe on 2017/7/12.
 */
@Document(collection = "ImportLog")
public class ImportLog {

    public final static String ID = "_id";
    public final static String NAME = "name";
    public final static String DEVICENAME = "deviceName";
    public final static String EXPORTDATETIME = "exportDateTime";
    public final static String TOTAL = "total";
    public final static String MALE = "male";
    public final static String FEMALE = "female";
    public final static String BEGINTIME = "beginTime";
    public final static String FINISHTIME = "finishTime";
    public final static String SUCCESS = "success";
    public final static String FAIL = "fail";
    public final static String GAP = "gap";
    public final static String STATUS = "status";

    public static List<String> getColumns() {
        List<String> result = new ArrayList<>();
        result.add("_id");
        result.add("name");
        result.add("deviceName");
        result.add("exportDateTime");
        result.add("total");
        result.add("male");
        result.add("female");
        result.add("beginTime");
        result.add("finishTime");
        result.add("success");
        result.add("fail");
        result.add("gap");
        result.add("status");
        return result;
    }

    private String _id;
    private String name;
    private String deviceName;
    private Date exportDateTime;
    private int total;
    private int male;
    private int female;
    private Date beginTime;
    private Date finishTime;
    private int success;
    private int fail;
    private List<gap> gaps;
    private int status;

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

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getFail() {
        return fail;
    }

    public void setFail(int fail) {
        this.fail = fail;
    }


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<gap> getGaps() {
        return gaps;
    }

    public void setGaps(List<gap> gaps) {
        this.gaps = gaps;
    }


    public class  gap{
        private int start;
        private int end;
        private int gap;

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        public int getGap() {
            return gap;
        }

        public void setGap(int gap) {
            this.gap = gap;
        }
    }
}
