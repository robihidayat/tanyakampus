package id.campusin.tanyakampus.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class TipsAndtricksModel implements Parcelable {

    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("rate")
    private Integer rate;

    public TipsAndtricksModel(String posterPath, String title, String description, Integer rate) {
        this.posterPath = posterPath;
        this.title = title;
        this.description= description;
        this.rate = rate;
    }

    public TipsAndtricksModel() {
    }


    public String getPosterPath() {
        return posterPath;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Integer getRate() {
        return rate;
    }

    public static Creator<TipsAndtricksModel> getCREATOR() {
        return CREATOR;
    }

    protected TipsAndtricksModel(Parcel in) {
        posterPath = in.readString();
        title = in.readString();
        description = in.readString();
        if (in.readByte() == 0) {
            rate = null;
        } else {
            rate = in.readInt();
        }
    }

    public static final Creator<TipsAndtricksModel> CREATOR = new Creator<TipsAndtricksModel>() {
        @Override
        public TipsAndtricksModel createFromParcel(Parcel in) {
            return new TipsAndtricksModel(in);
        }

        @Override
        public TipsAndtricksModel[] newArray(int size) {
            return new TipsAndtricksModel[size];
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
        dest.writeString(this.description);
        dest.writeValue(this.rate);
    }


}
