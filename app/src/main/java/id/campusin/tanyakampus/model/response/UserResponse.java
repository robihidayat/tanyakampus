package id.campusin.tanyakampus.model.response;

import com.google.gson.annotations.SerializedName;

public class UserResponse {

    @SerializedName("user")
    private UserModel user;

    public UserModel getUser() {
        return user;
    }
    public void setUser(UserModel user) {
        this.user = user;
    }
}
