package id.campusin.tanyakampus.helper;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterfaceService {

    @FormUrlEncoded
    @POST("/api/login")
    Call<ResponseBody> loginRequest(@Field("email") String email,
                                    @Field("password") String password);


    @GET("/api/user")
    Call<ResponseBody> userRequest(@Header("Authorization") String token);


    @FormUrlEncoded
    @POST("/api/register")
    Call<ResponseBody> registerRequest(@Field("email") String email,
                                       @Field("name") String name,
                                       @Field("password") String password,
                                       @Field("password_confirmation") String password_confirmation,
                                       @Field("phone") String phone,
                                       @Field("role") String role);


    @FormUrlEncoded
    @POST("/api/registerFirebase")
    Call<ResponseBody> registerFirebaseRequest(@Field("email") String email,
                                       @Field("name") String name,
                                       @Field("token_google") String password,
                                       @Field("phone") String phone,
                                       @Field("role") String role);


    @FormUrlEncoded
    @POST("/api/updateProfile")
    Call<ResponseBody> updateProfile(@Header("Authorization") String token,
                                     @Field("interest") String interest,
                                       @Field("phone") String phone,
                                       @Field("school") String school,
                                       @Field("department") String department);

}
