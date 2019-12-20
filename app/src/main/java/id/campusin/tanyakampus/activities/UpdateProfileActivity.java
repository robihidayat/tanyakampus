package id.campusin.tanyakampus.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import id.campusin.tanyakampus.R;
import id.campusin.tanyakampus.fragment.ProfileFragment;
import id.campusin.tanyakampus.helper.ApiInterfaceService;
import id.campusin.tanyakampus.helper.RetrofitUtils;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MainTheme);
        setContentView(R.layout.activity_update_profile);
        session = new SessionManager(getApplicationContext());
        editEmail = findViewById(R.id.EditText_update_profile_email);
        editEmail.setHint(session.getUserDetails().get("email"));
        editPhone = findViewById(R.id.EditText_update_profile_phone);
        editPhone.setHint(session.getUserDetails().get("phone"));
        editInterest = findViewById(R.id.EditText_update_profile_interest);
        editInterest.setHint(session.getUserDetails().get("interest"));
        editDepartment = findViewById(R.id.EditText_update_profile_department);
        editDepartment.setHint(session.getUserDetails().get("department"));
        editName = findViewById(R.id.EditText_update_profile_name);
        editName.setHint(session.getUserDetails().get("name"));
        editSchool = findViewById(R.id.EditText_update_profile_school);
        editSchool.setHint(session.getUserDetails().get("school"));

        buttonSave = findViewById(R.id.FloatingActionButton_profile_edit);

        apiInterfaceService = RetrofitUtils.apiService();

        buttonSave.setOnClickListener( v->{
            updateProfile(editName.getText().toString(), editName.getText().toString(), editDepartment.getText().toString(), editInterest.getText().toString(), editSchool.getText().toString());
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

                            finish();
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
