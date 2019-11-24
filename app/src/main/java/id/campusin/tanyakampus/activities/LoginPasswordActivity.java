package id.campusin.tanyakampus.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import id.campusin.tanyakampus.R;
import id.campusin.tanyakampus.helper.ApiHelper;
import id.campusin.tanyakampus.helper.ApiInterfaceService;
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
    private ProgressDialog loading;
    private ApiInterfaceService apiInterfaceService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_password);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;

        apiInterfaceService = ApiHelper.process();
        mContext = this;
        buttonNext = findViewById(R.id.button_login_next);
        inputText = findViewById(R.id.editText_login_password);

        buttonNext.setOnClickListener(v -> {
            if(predicateUtils.valueNotNullOrEmpty().test(inputText.getText().toString()) && predicateUtils.valueNotNullOrEmpty().test(inputText.getText().toString())){
                requestLogin(bundle.getString("username"),inputText.getText().toString());
                Toast.makeText(getApplicationContext(), "Hello "+inputText.getText().toString(),  Toast.LENGTH_LONG).show();
                Intent intentMain = new Intent( LoginPasswordActivity.this, MainActivity.class);
                intentMain.putExtra("password", inputText.getText().toString());
                startActivity(intentMain);
                finish();
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
                        try {
                            processLogin(response);
                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                        loading.dismiss();
                    }
                });
    }

    private void processLogin(Response<ResponseBody> response) throws JSONException, IOException {
        if (response.isSuccessful()){
            assert response.body() != null;
            JSONObject jsonResult = new JSONObject(response.body().string());
            if (jsonResult.getString("token") != null){
                Toast.makeText(mContext, "BERHASIL LOGIN", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(mContext, MainActivity.class);
//                intent.putExtra("result_nama", "berhasil");
//                startActivity(intent);
//                finish();
            } else {
                String error_message = jsonResult.getString("error_msg");
                Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
            }
        } else {
            loading.dismiss();
        }
    }
}
