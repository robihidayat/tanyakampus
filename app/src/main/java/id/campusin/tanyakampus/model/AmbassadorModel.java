package id.campusin.tanyakampus.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class AmbassadorModel implements Parcelable {

    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("title")
    private String title;
    @SerializedName("rate")
    private Integer rate;

    public AmbassadorModel(String posterPath, String title, Integer rate) {
        this.posterPath = posterPath;
        this.title = title;
        this.rate = rate;
    }

    public AmbassadorModel() {
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

    public static Creator<AmbassadorModel> getCREATOR() {
        return CREATOR;
    }

    protected AmbassadorModel(Parcel in) {
        posterPath = in.readString();
        title = in.readString();
        if (in.readByte() == 0) {
            rate = null;
        } else {
            rate = in.readInt();
        }
    }

    public static final Creator<AmbassadorModel> CREATOR = new Creator<AmbassadorModel>() {
        @Override
        public AmbassadorModel createFromParcel(Parcel in) {
            return new AmbassadorModel(in);
        }

        @Override
        public AmbassadorModel[] newArray(int size) {
            return new AmbassadorModel[size];
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
