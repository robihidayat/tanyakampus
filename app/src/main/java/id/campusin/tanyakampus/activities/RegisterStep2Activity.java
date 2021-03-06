package id.campusin.tanyakampus.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import id.campusin.tanyakampus.R;
import id.campusin.tanyakampus.helper.ApiInterfaceService;
import id.campusin.tanyakampus.helper.RetrofitUtils;
import id.campusin.tanyakampus.model.response.RegisterFirebaseResponse;
import id.campusin.tanyakampus.utils.PredicateUtils;
import id.campusin.tanyakampus.utils.managers.SessionManager;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegisterStep2Activity extends AppCompatActivity {
    private PredicateUtils predicateUtils = new PredicateUtils();

    private Button buttonNext;
    private EditText editTextPassword, editTextPasswordConfirm;

    private Context mContext;
    private ProgressBar loading;
    private ApiInterfaceService apiInterfaceService;
    private SessionManager session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MainTheme);
        setContentView(R.layout.activity_register_step_2);
        session = new SessionManager(getApplicationContext());
        apiInterfaceService = RetrofitUtils.apiService();
        mContext = this;

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;

        String name =  bundle.getString("registerName");
        String email =  bundle.getString("registerEmail");
        String phone = bundle.getString("registerPhone");

        editTextPassword = findViewById(R.id.editText_register_password);
        editTextPasswordConfirm = findViewById(R.id.editText_register_password_confirm);
        buttonNext = findViewById(R.id.button_register_password);
        loading = findViewById(R.id.progressBar_register_step_2);

        buttonNext.setOnClickListener(v -> {

        if(predicateUtils.valueNotNullOrEmpty().test(editTextPassword.getText().toString())
                && predicateUtils.valueNotNullOrEmpty().test(editTextPassword.getText().toString())) {

            if(editTextPassword.getText().toString().contentEquals(editTextPasswordConfirm.getText().toString())){
                requestRegister(name, email, phone, editTextPassword.getText().toString());
            } else {
                Toast.makeText(getApplicationContext(), "your password not match", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Silakan lengkapi data", Toast.LENGTH_LONG).show();
         }
        });
    }


    private void requestRegister(String name, String email, String phone, String password){
        apiInterfaceService.registerRequestObservable(email,name, password, password, phone, "user")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterFirebaseResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterFirebaseResponse registerFirebaseResponse) {
                        buttonNext.setEnabled(false);
                        session.setToken(registerFirebaseResponse.getToken());
                        session.createLoginSession(
                                registerFirebaseResponse.getUser().getName(),
                                registerFirebaseResponse.getUser().getEmail(),
                                registerFirebaseResponse.getUser().getPhone(),
                                registerFirebaseResponse.getUser().getProfilePicture()
                        );
                    }

                    @Override
                    public void onError(Throwable e) {
                        loading.setVisibility(View.INVISIBLE);
                        Toast.makeText(mContext, "Silakan Coba lagi", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onComplete() {
                        buttonNext.setEnabled(true);
                        loading.setVisibility(View.INVISIBLE);
                        Intent intentLogin = new Intent(RegisterStep2Activity.this, MainActivity.class);
                        startActivity(intentLogin);
                        finish();
                    }
                });





//        apiInterfaceService.registerRequest(email,name, password, password, phone, "user")
//                .enqueue(new Callback<ResponseBody>() {
//                    @Override
//                    public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {
//                        buttonNext.setEnabled(true);
//                        try {
//                            if (response.isSuccessful()){
//                                assert response.body() != null;
//                                JSONObject jsonResult = new JSONObject(response.body().string());
//                                if (jsonResult.getString("token") != null){
//                                    loading.setVisibility(View.INVISIBLE);
//                                    session.setToken(jsonResult.getString("token"));
//                                    session.createLoginSession(
//                                            (String)jsonResult.getJSONObject("user").get("name"),
//                                            (String)jsonResult.getJSONObject("user").get("email"),
//                                            (String)jsonResult.getJSONObject("user").get("phone"),
//                                            (String)jsonResult.getJSONObject("user").get("profile_picture")
//                                            );
//                                    Intent intentLogin = new Intent(RegisterStep2Activity.this, MainActivity.class);
//                                    startActivity(intentLogin);
//                                    finish();
//                                } else {
//                                    String error_message = jsonResult.getString("error_msg");
//                                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
//                                }
//                            } else {
//                                loading.setVisibility(View.INVISIBLE);
//                                Toast.makeText(mContext, "password atau username salah", Toast.LENGTH_SHORT).show();
//                            }
//                        } catch (JSONException | IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    @Override
//                    public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable t) {
//                        buttonNext.setEnabled(true);
//                        if(!call.isCanceled()) {
//                            call.cancel();
//                        }
//                        Log.e("debug", "onFailure: ERROR > " + t.toString());
//                    }
//                });
    }


}



