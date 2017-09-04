package win.himike.selector.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by HiMike on 2017/9/3.
 */

public class City implements Parcelable {
    private String name;
    private String zip;
    private List<City> city;

    protected City(Parcel in) {
        name = in.readString();
        zip = in.readString();
        city = in.createTypedArrayList(City.CREATOR);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public List<City> getCity() {
        return city;
    }

    public void setCity(List<City> city) {
        this.city = city;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(zip);
        parcel.writeTypedList(city);
    }
}
