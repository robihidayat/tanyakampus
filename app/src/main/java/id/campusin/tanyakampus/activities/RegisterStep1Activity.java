package id.campusin.tanyakampus.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import id.campusin.tanyakampus.R;
import id.campusin.tanyakampus.utils.PredicateUtils;

public class RegisterStep1Activity extends AppCompatActivity implements View.OnClickListener {

    private PredicateUtils predicateUtils = new PredicateUtils();


    private Button buttonNext;
    private EditText editTextName, editTextEmail, editTextPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MainTheme);
        setContentView(R.layout.activity_register_step_1);

        buttonNext = findViewById(R.id.button_register_action_continue);
        buttonNext.setOnClickListener(this);

        editTextName = findViewById(R.id.editText_update_profile_fullname);
        editTextEmail = findViewById(R.id.editText_update_profile_email);
        editTextPhone = findViewById(R.id.editText_update_profile_phone);

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
            Intent intentLogin = new Intent(RegisterStep1Activity.this, RegisterStep2Activity.class);
            intentLogin.putExtra("registerName", editTextName.getText().toString());
            intentLogin.putExtra("registerEmail", editTextEmail.getText().toString());
            intentLogin.putExtra("registerPhone", editTextPhone.getText().toString());
            startActivity(intentLogin);
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "please complete your information !", Toast.LENGTH_LONG).show();
        }

    }


}
