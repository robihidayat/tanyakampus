package id.campusin.tanyakampus.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class FilterSearchModel implements Parcelable {

    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("title")
    private String title;
    @SerializedName("rate")
    private Integer rate;

    public FilterSearchModel(String posterPath, String title, Integer rate) {
        this.posterPath = posterPath;
        this.title = title;
        this.rate = rate;
    }

    public FilterSearchModel() {
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

    public static Creator<FilterSearchModel> getCREATOR() {
        return CREATOR;
    }

    protected FilterSearchModel(Parcel in) {
        posterPath = in.readString();
        title = in.readString();
        if (in.readByte() == 0) {
            rate = null;
        } else {
            rate = in.readInt();
        }
    }

    public static final Creator<FilterSearchModel> CREATOR = new Creator<FilterSearchModel>() {
        @Override
        public FilterSearchModel createFromParcel(Parcel in) {
            return new FilterSearchModel(in);
        }

        @Override
        public FilterSearchModel[] newArray(int size) {
            return new FilterSearchModel[size];
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
