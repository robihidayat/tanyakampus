package id.campusin.tanyakampus.model.response;

import com.google.gson.annotations.SerializedName;

public class RegisterFirebaseResponse {

    @SerializedName("token")
    private String token;
    @SerializedName("useer")
    private UserModel user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
