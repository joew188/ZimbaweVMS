package Zim.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Document(collection = "User")
public class User implements Serializable {
    private String _id;
    private String name;
    private String password;
    private String group;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public static List<String> getColumns() {
        List<String> result = new ArrayList<>();
        result.add("_id");
        result.add("name");
        result.add("password");
        result.add("group");
        return result;
    }
}
