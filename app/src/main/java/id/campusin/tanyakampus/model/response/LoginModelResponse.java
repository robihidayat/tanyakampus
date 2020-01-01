package id.campusin.tanyakampus.model.response;

import com.google.gson.annotations.SerializedName;

public class LoginModelResponse {

    @SerializedName("token")
    private String token;
    @SerializedName("useer")
    private UserModel user;
    @SerializedName("roles")
    private Object roles;

    public Object getRoles() {
        return roles;
    }

    public void setRoles(Object roles) {
        this.roles = roles;
    }

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
