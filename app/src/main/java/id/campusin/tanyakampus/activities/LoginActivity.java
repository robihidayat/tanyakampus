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
import id.campusin.tanyakampus.model.response.LoginModelResponse;
import id.campusin.tanyakampus.model.response.UserResponse;
import id.campusin.tanyakampus.utils.PredicateUtils;
import id.campusin.tanyakampus.utils.managers.AlertDialogManager;
import id.campusin.tanyakampus.utils.managers.SessionManager;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
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

    private void requestLogin(String username, String password) {
       apiInterfaceService.loginRequestObservable(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginModelResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginModelResponse loginModelResponse) {
                        buttonLogin.setEnabled(false);
                        session.setProfile(loginModelResponse);
                        session.setToken(loginModelResponse.getToken());
                    }

                    @Override
                    public void onError(Throwable e) {
                        loading.setVisibility(View.INVISIBLE);
                        buttonLogin.setEnabled(true);
                        alert.showAlertDialog(LoginActivity.this, "Login failed..", "Please enter username and password", false);
                    }

                    @Override
                    public void onComplete() {
                        loading.setVisibility(View.INVISIBLE);
                        Intent intentMain = new Intent(getApplication(), MainActivity.class);
                        startActivity(intentMain);
                        finish();
                    }
                });
    }


}
