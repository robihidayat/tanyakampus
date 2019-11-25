package id.campusin.tanyakampus.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import id.campusin.tanyakampus.R;
import id.campusin.tanyakampus.utils.PredicateUtils;

public class RegisterPasswordActivity extends AppCompatActivity {
    private PredicateUtils predicateUtils = new PredicateUtils();

    private Button buttonNext;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_password);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;

        String name =  bundle.getString("registerName");
        String email =  bundle.getString("registerEmail");

        editTextPassword = findViewById(R.id.editText_register_password);
        buttonNext = findViewById(R.id.button_register_password);
        buttonNext.setOnClickListener(v -> {
        if(predicateUtils.valueNotNullOrEmpty().test(editTextPassword.getText().toString())
                && predicateUtils.valueNotNullOrEmpty().test(editTextPassword.getText().toString())) {

            Intent intentLogin = new Intent(RegisterPasswordActivity.this, MainActivity.class);
            startActivity(intentLogin);
            finish();

            } else {
            Toast.makeText(getApplicationContext(), "Silakan lengkapi data", Toast.LENGTH_LONG).show();
         }
        });



    }
}
