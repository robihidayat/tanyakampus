package id.campusin.tanyakampus.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import id.campusin.tanyakampus.R;
import id.campusin.tanyakampus.utils.PredicateUtils;

public class LoginActivity extends AppCompatActivity {

    private PredicateUtils predicateUtils = new PredicateUtils();

    private Button buttonNext;
    private EditText inputText;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonNext = findViewById(R.id.button_login_next);
        inputText = findViewById(R.id.editText_login);

        buttonNext.setOnClickListener(v -> {
            if(predicateUtils.valueNotNullOrEmpty().test(inputText.getText().toString()) && predicateUtils.valueNotNullOrEmpty().test(inputText.getText().toString())){
                Toast.makeText(getApplicationContext(), "Hello "+inputText.getText().toString(),  Toast.LENGTH_LONG).show();
                Intent intentMain = new Intent( LoginActivity.this, LoginPasswordActivity.class);
                intentMain.putExtra("username", inputText.getText().toString());
                startActivity(intentMain);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Silakan lengkapi data", Toast.LENGTH_LONG).show();
            }
        });


    }

}
