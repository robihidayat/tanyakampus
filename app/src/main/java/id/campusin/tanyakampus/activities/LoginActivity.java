package id.campusin.tanyakampus.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import id.campusin.tanyakampus.R;
import id.campusin.tanyakampus.helper.ApiInterfaceService;
import id.campusin.tanyakampus.helper.RetrofitUtils;
import id.campusin.tanyakampus.utils.PredicateUtils;
import id.campusin.tanyakampus.utils.managers.AlertDialogManager;
import id.campusin.tanyakampus.utils.managers.SessionManager;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private PredicateUtils predicateUtils = new PredicateUtils();

    private Button buttonLogin;
    private EditText editTextPassword, editTextUsername;

    private ProgressBar loading;
    private ApiInterfaceService apiInterfaceService;

    private AlertDialogManager alert = new AlertDialogManager();

    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MainTheme);
        setContentView(R.layout.activity_login);
        buttonLogin = findViewById(R.id.button_login_action);
        editTextPassword = findViewById(R.id.editText_login_password);
        editTextUsername = findViewById(R.id.editText_login_username);
        loading = findViewById(R.id.progressBar_login);
        buttonLogin.setOnClickListener(this);
        editTextPassword.setOnClickListener(this);
        editTextUsername.setOnClickListener(this);
        apiInterfaceService = RetrofitUtils.apiService();

        // Session Manager
        session = new SessionManager(getApplicationContext());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_login_action:
                login();
                break;

        }
    }

    private void login(){
        if(predicateUtils.valueNotNullOrEmpty().test(editTextUsername.getText().toString()) && predicateUtils.valueNotNullOrEmpty().test(editTextPassword.getText().toString())){
            loading.setVisibility(View.VISIBLE);
            requestLogin(editTextUsername.getText().toString(), editTextPassword.getText().toString());
            } else {
            alert.showAlertDialog(LoginActivity.this, "error ", "Silakan lengkapi seluruh data", false);
         }
    }

    private void requestLogin(String username, String password){
        apiInterfaceService.loginRequest(username, password)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {
                        buttonLogin.setEnabled(true);
                        try {
                            if (response.isSuccessful()){
                                assert response.body() != null;
                                JSONObject jsonResult = new JSONObject(response.body().string());
                                if (jsonResult.getString("token") != null){
                                    loading.setVisibility(View.INVISIBLE);
                                    session.setToken(jsonResult.getString("token"));
                                    session.profileUser(
                                            (String)jsonResult.getJSONObject("user").get("name"),
                                            (String)jsonResult.getJSONObject("user").get("email"),
                                            (String)jsonResult.getJSONObject("user").get("phone"),
                                            (String)jsonResult.getJSONObject("user").get("profile_picture"),
                                            (String)jsonResult.getJSONObject("user").get("interest"),
                                            (String)jsonResult.getJSONObject("user").get("school"),
                                            (String)jsonResult.getJSONObject("user").get("department")
                                           );
                                    Intent intentMain = new Intent(getApplication(), MainActivity.class);
                                    startActivity(intentMain);
                                    finish();
                                } else {
                                    alert.showAlertDialog(LoginActivity.this, "Login failed..", "Username/Password is incorrect", false);
                                }
                            } else {
                                loading.setVisibility(View.INVISIBLE);
                                alert.showAlertDialog(LoginActivity.this, "Login failed..", "Please enter username and password", false);
                            }
                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable t) {
                        buttonLogin.setEnabled(true);
                        if(!call.isCanceled()) {
                            call.cancel();
                        }
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                    }
                });
    }


}
