package Zim.model;

import Zim.common.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Laxton-Joe on 2017/7/25.
 */

@Document(collection = "UploadHistory")
public class UploadHistory {
    private String _id;
    private String name;
    private String fileSize;

    @JsonSerialize(using = CustomDateSerializer.class)
    private Date uploadTime;
    private String userId;
    private String userName;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public static List<String> getColumns() {
        List<String> result = new ArrayList<>();
        result.add("_id");
        result.add("name");
        result.add("fileSize");
        result.add("uploadTime");
        result.add("userId");
        result.add("userName");
        return result;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }
}
