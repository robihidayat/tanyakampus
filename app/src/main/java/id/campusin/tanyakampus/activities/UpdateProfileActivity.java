package id.campusin.tanyakampus.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import id.campusin.tanyakampus.R;
import id.campusin.tanyakampus.helper.ApiInterfaceService;
import id.campusin.tanyakampus.helper.RetrofitUtils;
import id.campusin.tanyakampus.model.response.LoginModelResponse;
import id.campusin.tanyakampus.utils.managers.AlertDialogManager;
import id.campusin.tanyakampus.utils.managers.SessionManager;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
        editEmail = findViewById(R.id.editText_update_profile_email);
        editEmail.setHint(session.getUserDetails().get("email") != null ? session.getUserDetails().get("email") : " ex. email@email.com");
        editPhone = findViewById(R.id.editText_update_profile_phone);
        editPhone.setHint(session.getUserDetails().get("phone") != null ? session.getUserDetails().get("phone") : "ex. 087382921");
        editInterest = findViewById(R.id.editText_update_profile_interest);
        editInterest.setHint(session.getUserDetails().get("interest") != null ? session.getUserDetails().get("interest") : "ex. IPA");
        editDepartment = findViewById(R.id.editText_update_profile_department);
        editDepartment.setHint(session.getUserDetails().get("department") != null ? session.getUserDetails().get("department"): " ex. ILKOM");
        editName = findViewById(R.id.editText_update_profile_fullname);
        editName.setHint(session.getUserDetails().get("name") != null ? session.getUserDetails().get("name") : "ex. name");
        editSchool = findViewById(R.id.editText_update_profile_school);
        editSchool.setHint(session.getUserDetails().get("school") != null ? session.getUserDetails().get("school") :"ex. School");

        buttonSave = findViewById(R.id.FloatingActionButton_profile_edit);

        loading = findViewById(R.id.progressBar_update_profile);

        Glide.with(this).load(session.getUserDetails().get("avatar"))
                .placeholder(R.drawable.profile_lazzy_mode)
                .into((ImageView) findViewById(R.id.CircleImageView_update_profile_avatar));

        apiInterfaceService = RetrofitUtils.apiService();

        buttonSave.setOnClickListener( v-> {
        if( editSchool.getText() == null || editSchool.getText().toString().equals("") ||
                editPhone.getText() == null || editPhone.getText().toString().equals("") ||
                editInterest.getText() == null || editInterest.getText().toString().equals("") |
                editDepartment.getText() == null  || editDepartment.getText().toString().equals("")) {

                alert.showAlertDialog(this, "error ", "Silakan lengkapi seluruh data", false);

        } else {
            loading.setVisibility(View.VISIBLE);
            updateProfile(editPhone.getText().toString(), editDepartment.getText().toString(), editInterest.getText().toString(), editSchool.getText().toString());
        }

        });



    }


    private void updateProfile(String phone, String department, String interest, String school){
        apiInterfaceService.updateProfileObservable(session.getToken(), interest, phone, school, department)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<LoginModelResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(LoginModelResponse userResponse) {
                                session.profileUser(
                                        userResponse.getUser().getName(),
                                        userResponse.getUser().getEmail(),
                                        userResponse.getUser().getPhone(),
                                        userResponse.getUser().getInterest(),
                                        userResponse.getUser().getSchool(),
                                        userResponse.getUser().getDepartment()
                                );
                            }


                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(getApplicationContext(), "Update Error", Toast.LENGTH_LONG).show();
                                loading.setVisibility(View.INVISIBLE);
                            }


                            @Override
                            public void onComplete() {
                                Toast.makeText(getApplicationContext(), "Update Success", Toast.LENGTH_LONG).show();
                                loading.setVisibility(View.INVISIBLE);

                            }
                        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(UpdateProfileActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
