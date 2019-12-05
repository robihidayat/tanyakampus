package id.campusin.tanyakampus.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import id.campusin.tanyakampus.R;
import id.campusin.tanyakampus.utils.PredicateUtils;

public class RegisterStep2Activity extends AppCompatActivity {
    private PredicateUtils predicateUtils = new PredicateUtils();

    private Button buttonNext;
    private EditText editTextPassword, editTextPasswordConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MainTheme);
        setContentView(R.layout.activity_register_step_2);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;

        String name =  bundle.getString("registerName");
        String email =  bundle.getString("registerEmail");
        String phone = bundle.getString("registerPhone");

        editTextPassword = findViewById(R.id.editText_register_password);
        editTextPasswordConfirm = findViewById(R.id.editText_register_password_confirm);
        buttonNext = findViewById(R.id.button_register_password);

        buttonNext.setOnClickListener(v -> {

        if(predicateUtils.valueNotNullOrEmpty().test(editTextPassword.getText().toString())
                && predicateUtils.valueNotNullOrEmpty().test(editTextPassword.getText().toString())) {

            if(editTextPassword.getText().toString().contentEquals(editTextPasswordConfirm.getText().toString())){

                Intent intentLogin = new Intent(RegisterStep2Activity.this, MainActivity.class);
                startActivity(intentLogin);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "your password not match", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Silakan lengkapi data", Toast.LENGTH_LONG).show();
         }
        });
    }
}
