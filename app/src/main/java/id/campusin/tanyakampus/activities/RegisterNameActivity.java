package id.campusin.tanyakampus.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import id.campusin.tanyakampus.R;
import id.campusin.tanyakampus.utils.PredicateUtils;

public class RegisterNameActivity extends AppCompatActivity {

    private PredicateUtils predicateUtils = new PredicateUtils();


    private Button buttonNext;
    private EditText editTextName, editTextEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MainTheme);
        setContentView(R.layout.activity_register_name);

        buttonNext = findViewById(R.id.button_register_name);
        editTextName = findViewById(R.id.editText_register_password);
        editTextEmail = findViewById(R.id.editText_register_email);


        buttonNext.setOnClickListener(v -> {
            if(predicateUtils.valueNotNullOrEmpty().test(editTextName.getText().toString())
                    && predicateUtils.valueNotNullOrEmpty().test(editTextEmail.getText().toString())) {
                Intent intentLogin = new Intent(RegisterNameActivity.this, RegisterPasswordActivity.class);
                intentLogin.putExtra("registerName", editTextName.getText().toString());
                intentLogin.putExtra("registerEmail", editTextName.getText().toString());
                startActivity(intentLogin);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Silakan lengkapi data", Toast.LENGTH_LONG).show();
            }
            });


    }
}
