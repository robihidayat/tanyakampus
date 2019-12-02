package id.campusin.tanyakampus.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import id.campusin.tanyakampus.R;
import id.campusin.tanyakampus.helper.ApiInterfaceService;
import id.campusin.tanyakampus.helper.RetrofitUtils;
import id.campusin.tanyakampus.utils.PredicateUtils;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPasswordActivity extends AppCompatActivity {

    private PredicateUtils predicateUtils = new PredicateUtils();

    private Button buttonNext;
    private EditText inputText;

    private Context mContext;
    private ProgressBar loading;
    private ApiInterfaceService apiInterfaceService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_password);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;

        apiInterfaceService = RetrofitUtils.apiService();
        mContext = this;
        buttonNext = findViewById(R.id.button_login_next);
        inputText = findViewById(R.id.editText_login_password);

        loading = findViewById(R.id.progressBar_login);

        buttonNext.setOnClickListener(v -> {
            if(predicateUtils.valueNotNullOrEmpty().test(inputText.getText().toString()) && predicateUtils.valueNotNullOrEmpty().test(inputText.getText().toString())){
                loading.setVisibility(View.VISIBLE);
                buttonNext.setEnabled(false);
                requestLogin(bundle.getString("username"),inputText.getText().toString());
            } else {
                Toast.makeText(getApplicationContext(), "Silakan lengkapi data", Toast.LENGTH_LONG).show();
            }
        });

    }


    private void requestLogin(String username, String password){
        apiInterfaceService.loginRequest(username, password)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {
                        buttonNext.setEnabled(true);
                        try {
                            if (response.isSuccessful()){
                                assert response.body() != null;
                                JSONObject jsonResult = new JSONObject(response.body().string());
                                if (jsonResult.getString("token") != null){
                                    loading.setVisibility(View.INVISIBLE);
                                    Toast.makeText(mContext, "login berhasil", Toast.LENGTH_SHORT).show();
                                    Intent intentMain = new Intent( LoginPasswordActivity.this, MainActivity.class);
                                    intentMain.putExtra("password", inputText.getText().toString());
                                    startActivity(intentMain);
                                    finish();
                                } else {
                                    String error_message = jsonResult.getString("error_msg");
                                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                loading.setVisibility(View.INVISIBLE);
                                Toast.makeText(mContext, "password atau username salah", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable t) {
                        buttonNext.setEnabled(true);
                        if(!call.isCanceled()) {
                            call.cancel();
                        }
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                    }
                });
        }

}
