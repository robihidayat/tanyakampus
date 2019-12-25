package id.campusin.tanyakampus.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class UniversityModel implements Parcelable {

    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("title")
    private String title;
    @SerializedName("rate")
    private Integer rate;
    @SerializedName("description")
    private String description;
    @SerializedName("address")
    private String address;


    public UniversityModel(String posterPath, String title, Integer rate, String description, String address) {
        this.posterPath = posterPath;
        this.title = title;
        this.rate = rate;
        this.description = description;
        this.address = address;
    }

    public UniversityModel() {
    }

    public String getDescription() {
        return description;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getTitle() {
        return title;
    }

    public String getAddress() {
        return address;
    }

    public Integer getRate() {
        return rate;
    }

    public static Creator<UniversityModel> getCREATOR() {
        return CREATOR;
    }

    protected UniversityModel(Parcel in) {
        posterPath = in.readString();
        title = in.readString();
        if (in.readByte() == 0) {
            rate = null;
        } else {
            rate = in.readInt();
        }
        description = in.readString();
        address = in.readString();
    }

    public static final Creator<UniversityModel> CREATOR = new Creator<UniversityModel>() {
        @Override
        public UniversityModel createFromParcel(Parcel in) {
            return new UniversityModel(in);
        }

        @Override
        public UniversityModel[] newArray(int size) {
            return new UniversityModel[size];
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
        dest.writeString(this.description);
        dest.writeString(this.address);
    }

    @Override
    public String toString() {
        return "UniversityModelResponse{" +
                "posterPath='" + posterPath + '\'' +
                ", title='" + title + '\'' +
                ", rate=" + rate +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
