package Zim.model;

import org.bson.types.ObjectId;

/**
 * Created by Laxton-Joe on 2017/8/15.
 */
public class BinaryEntity {
    private ObjectId _id;
    private byte[] imgData;

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public byte[] getImgData() {
        return imgData;
    }

    public void setImgData(byte[] imgData) {
        this.imgData = imgData;
    }
}
