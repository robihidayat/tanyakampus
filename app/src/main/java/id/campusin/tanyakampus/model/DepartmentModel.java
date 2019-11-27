package id.campusin.tanyakampus.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DepartmentModel implements Parcelable {

    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("title")
    private String title;
    @SerializedName("rate")
    private Integer rate;

    public DepartmentModel(String posterPath, String title, Integer rate) {
        this.posterPath = posterPath;
        this.title = title;
        this.rate = rate;
    }

    public DepartmentModel() {
    }


    public String getPosterPath() {
        return posterPath;
    }

    public String getTitle() {
        return title;
    }

    public Integer getRate() {
        return rate;
    }

    public static Creator<DepartmentModel> getCREATOR() {
        return CREATOR;
    }

    protected DepartmentModel(Parcel in) {
        posterPath = in.readString();
        title = in.readString();
        if (in.readByte() == 0) {
            rate = null;
        } else {
            rate = in.readInt();
        }
    }

    public static final Creator<DepartmentModel> CREATOR = new Creator<DepartmentModel>() {
        @Override
        public DepartmentModel createFromParcel(Parcel in) {
            return new DepartmentModel(in);
        }

        @Override
        public DepartmentModel[] newArray(int size) {
            return new DepartmentModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.posterPath);
        dest.writeString(this.title);
        dest.writeValue(this.rate);
    }


}
