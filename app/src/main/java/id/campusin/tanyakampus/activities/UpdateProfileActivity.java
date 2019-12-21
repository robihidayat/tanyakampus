package id.campusin.tanyakampus.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import id.campusin.tanyakampus.R;
import id.campusin.tanyakampus.helper.ApiInterfaceService;
import id.campusin.tanyakampus.helper.RetrofitUtils;
import id.campusin.tanyakampus.utils.managers.AlertDialogManager;
import id.campusin.tanyakampus.utils.managers.SessionManager;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfileActivity extends AppCompatActivity {

    private EditText editEmail, editPhone, editInterest, editDepartment, editName, editSchool;
    private FloatingActionButton buttonSave;
    private SessionManager session;
    private ApiInterfaceService apiInterfaceService;
    private AlertDialogManager alert = new AlertDialogManager();
    private ProgressBar loading;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MainTheme);
        setContentView(R.layout.activity_update_profile);
        session = new SessionManager(getApplicationContext());
        editEmail = findViewById(R.id.EditText_update_profile_email);
        editEmail.setHint(session.getUserDetails().get("email") != null ? session.getUserDetails().get("email") : " ex. email@email.com");
        editPhone = findViewById(R.id.EditText_update_profile_phone);
        editPhone.setHint(session.getUserDetails().get("phone") != null ? session.getUserDetails().get("phone") : "ex. 087382921");
        editInterest = findViewById(R.id.EditText_update_profile_interest);
        editInterest.setHint(session.getUserDetails().get("interest") != null ? session.getUserDetails().get("interest") : "ex. IPA");
        editDepartment = findViewById(R.id.EditText_update_profile_department);
        editDepartment.setHint(session.getUserDetails().get("department") != null ? session.getUserDetails().get("department"): " ex. ILKOM");
        editName = findViewById(R.id.EditText_update_profile_name);
        editName.setHint(session.getUserDetails().get("name") != null ? session.getUserDetails().get("name") : "ex. name");
        editSchool = findViewById(R.id.EditText_update_profile_school);
        editSchool.setHint(session.getUserDetails().get("school") != null ? session.getUserDetails().get("school") :"ex. School");

        buttonSave = findViewById(R.id.FloatingActionButton_profile_edit);

        loading = findViewById(R.id.progressBar_update_profile);

        Glide.with(this).load(session.getUserDetails().get("avatar"))
                .placeholder(R.drawable.profile_lazzy_mode)
                .into((ImageView) findViewById(R.id.CircleImageView_update_profile_ambassador));

        apiInterfaceService = RetrofitUtils.apiService();

        buttonSave.setOnClickListener( v-> {
            if(editSchool.getText() == null || editPhone.getText() == null || editInterest.getText() == null || editDepartment.getText() == null){
                alert.showAlertDialog(this, "error ", "Silakan lengkapi seluruh data", false);
            }
            updateProfile(editName.getText().toString(), editName.getText().toString(), editDepartment.getText().toString(), editInterest.getText().toString(), editSchool.getText().toString());
            loading.setVisibility(View.VISIBLE);

        });



    }


    private void updateProfile(String name, String phone, String department, String interest, String school){
        System.out.println("dapet Token "+ session.getToken());
        apiInterfaceService.updateProfile(
                "Bearer "+session.getToken(),
                interest,
                phone,
                school,
                department).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.isSuccessful()){
                        assert response.body() != null;
                        JSONObject jsonResult = new JSONObject(response.body().string());
                        if (jsonResult.getString("user") != null){
                            session.profileUser(
                                    (String)jsonResult.getJSONObject("user").get("name"),
                                    (String)jsonResult.getJSONObject("user").get("email"),
                                    (String)jsonResult.getJSONObject("user").get("phone"),
                                    (String)jsonResult.getJSONObject("user").get("profile_picture"),
                                    (String)jsonResult.getJSONObject("user").get("interest"),
                                    (String)jsonResult.getJSONObject("user").get("school"),
                                    (String)jsonResult.getJSONObject("user").get("department")
                            );
                            loading.setVisibility(View.INVISIBLE);
                        } else {
                            String error_message = jsonResult.getString("error_msg");
                        }
                    } else {

                    }
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if(!call.isCanceled()) {
                    call.cancel();
                }
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });

    }
}
