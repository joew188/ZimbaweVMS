package Zim.model.modelview;

/**
 * Created by Laxton-Joe on 2017/8/9.
 */
public class ApplicantSimple {
    private String _id;
    private String idNumber;
    private String name;
    private String gender;
    private String dateOfBirthText;
    private String photo;
    private String address;

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirthText() {
        return dateOfBirthText;
    }

    public void setDateOfBirthText(String dateOfBirthText) {
        this.dateOfBirthText = dateOfBirthText;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
