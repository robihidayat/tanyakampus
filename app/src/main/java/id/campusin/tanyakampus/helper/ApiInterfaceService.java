package id.campusin.tanyakampus.helper;

import id.campusin.tanyakampus.model.response.LoginModelResponse;
import id.campusin.tanyakampus.model.response.RegisterFirebaseResponse;
import id.campusin.tanyakampus.model.response.UniversityModelResponse;
import id.campusin.tanyakampus.model.response.UserResponse;
import io.reactivex.Observable;
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
    Observable<LoginModelResponse> loginRequestObservable(@Field("email") String email,
                                                          @Field("password") String password);


    @GET("/api/user")
    Observable<UserResponse> userRequestObservable(@Header("Authorization") String token);


    @GET("/api/university")
    Observable<UniversityModelResponse> universityListRequestObservable(@Header("Authorization") String token);


    @FormUrlEncoded
    @POST("/api/validate/email")
    Observable<ResponseBody> validateEmail(@Field("email") String email,
                                     @Field("phone") String phone);


    @FormUrlEncoded
    @POST("/api/register")
    Call<ResponseBody> registerRequest(@Field("email") String email,
                                       @Field("name") String name,
                                       @Field("password") String password,
                                       @Field("password_confirmation") String password_confirmation,
                                       @Field("phone") String phone,
                                       @Field("role") String role);

    @FormUrlEncoded
    @POST("/api/register")
    Observable<RegisterFirebaseResponse> registerRequestObservable(@Field("email") String email,
                                                 @Field("name") String name,
                                                 @Field("password") String password,
                                                 @Field("password_confirmation") String password_confirmation,
                                                 @Field("phone") String phone,
                                                 @Field("role") String role);


    @FormUrlEncoded
    @POST("/api/registerFirebase")
    Observable<RegisterFirebaseResponse> registerFirebaseRequestObservable(
            @Field("email") String email,
            @Field("name") String name,
            @Field("token_google") String password,
            @Field("phone") String phone,
            @Field("role") String role,
            @Field("profile_picture") String profile_picture);


    @FormUrlEncoded
    @POST("/api/updateProfile")
    Call<ResponseBody> updateProfile(@Header("Authorization") String token,
                                     @Field("interest") String interest,
                                     @Field("phone") String phone,
                                     @Field("school") String school,
                                     @Field("department") String department,
                                     @Field("avatar") String avatar);


    @FormUrlEncoded
    @POST("/api/updateProfile")
    Observable<LoginModelResponse> updateProfileObservable(@Header("Authorization") String token,
                                     @Field("interest") String interest,
                                     @Field("phone") String phone,
                                     @Field("school") String school,
                                     @Field("department") String department);

}
