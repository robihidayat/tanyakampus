package id.campusin.tanyakampus.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DataUniversityResponse implements Parcelable {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("abbreviation")
    private String abbreviation;
    @SerializedName("avatar")
    private String avatar;
    @SerializedName("description")
    private String description;
    @SerializedName("link")
    private String link;
    @SerializedName("ranking")
    private String ranking;
    @SerializedName("location")
    private String location;
    @SerializedName("additional_data")
    private String additionalData;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(String additionalData) {
        this.additionalData = additionalData;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }


    @Override
    public String toString() {
        return "DataUniversityResponse{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", abbreviation='" + abbreviation + '\'' +
                ", avatar='" + avatar + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                ", ranking='" + ranking + '\'' +
                ", location='" + location + '\'' +
                ", additionalData='" + additionalData + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.abbreviation);
        dest.writeString(this.avatar);
        dest.writeString(this.description);
        dest.writeString(this.link);
        dest.writeString(this.ranking);
        dest.writeString(this.location);
        dest.writeString(this.additionalData);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
    }

    public DataUniversityResponse() {
    }

    protected DataUniversityResponse(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.abbreviation = in.readString();
        this.avatar = in.readString();
        this.description = in.readString();
        this.link = in.readString();
        this.ranking = in.readString();
        this.location = in.readString();
        this.additionalData = in.readString();
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
    }

    public static final Creator<DataUniversityResponse> CREATOR = new Creator<DataUniversityResponse>() {
        @Override
        public DataUniversityResponse createFromParcel(Parcel source) {
            return new DataUniversityResponse(source);
        }

        @Override
        public DataUniversityResponse[] newArray(int size) {
            return new DataUniversityResponse[size];
        }
    };
}
