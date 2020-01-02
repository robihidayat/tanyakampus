package id.campusin.tanyakampus.activities;

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
import id.campusin.tanyakampus.utils.PredicateUtils;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class RegisterStep1Activity extends AppCompatActivity implements View.OnClickListener {

    private PredicateUtils predicateUtils = new PredicateUtils();

    private ProgressBar loading;
    private ApiInterfaceService apiInterfaceService;

    private Button buttonNext;
    private EditText editTextName, editTextEmail, editTextPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MainTheme);
        setContentView(R.layout.activity_register_step_1);

        apiInterfaceService = RetrofitUtils.apiService();
        buttonNext = findViewById(R.id.button_register_action_continue);
        buttonNext.setOnClickListener(this);

        editTextName = findViewById(R.id.editText_update_profile_fullname);
        editTextEmail = findViewById(R.id.editText_update_profile_email);
        editTextPhone = findViewById(R.id.editText_update_profile_phone);

        loading = findViewById(R.id.progressBar_register_step_1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_register_action_continue:
                pressButtonRegister();
                break;

        }
    }

    private void pressButtonRegister(){
        if((predicateUtils.valueNotNullOrEmpty().test(editTextName.getText().toString()))
                && (predicateUtils.valueNotNullOrEmpty().test(editTextEmail.getText().toString()))
                        && (predicateUtils.valueNotNullOrEmpty().test(editTextPhone.getText().toString()))) {
            validateEmail(editTextEmail.getText().toString(), editTextPhone.getText().toString());
        } else {
            Toast.makeText(getApplicationContext(), "please complete your information !", Toast.LENGTH_LONG).show();
        }

    }

    private void validateEmail(String email, String phone){
        loading.setVisibility(View.VISIBLE);
        buttonNext.setEnabled(false);
        apiInterfaceService.validateEmail(email, phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        loading.setVisibility(View.INVISIBLE);
                        buttonNext.setEnabled(true);
                        Toast.makeText(getApplicationContext(), "Email or Phone Exist !", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete() {
                        loading.setVisibility(View.INVISIBLE);
                        Intent intentLogin = new Intent(RegisterStep1Activity.this, RegisterStep2Activity.class);
                        intentLogin.putExtra("registerName", editTextName.getText().toString());
                        intentLogin.putExtra("registerEmail", editTextEmail.getText().toString());
                        intentLogin.putExtra("registerPhone", editTextPhone.getText().toString());
                        startActivity(intentLogin);
                        finish();
                    }
                });

    }


}
