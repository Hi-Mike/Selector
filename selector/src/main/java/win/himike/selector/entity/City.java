package win.himike.selector.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HiMike on 2017/9/3.
 */

public class City implements Parcelable {
    private int id;
    private int level;//第几级城市
    private int cid;//城市编码
    private int parentId;
    private String name;

    public City() {
    }

    protected City(Parcel in) {
        id = in.readInt();
        level = in.readInt();
        cid = in.readInt();
        parentId = in.readInt();
        name = in.readString();
    }

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(level);
        dest.writeInt(cid);
        dest.writeInt(parentId);
        dest.writeString(name);
    }
}
