package id.campusin.tanyakampus.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import id.campusin.tanyakampus.R;

public class LandingPage extends AppCompatActivity {

    private Button buttonRegister, buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        buttonLogin = findViewById(R.id.button_landing_page_login);
        buttonRegister = findViewById(R.id.button_landing_page_register);

        buttonLogin.setOnClickListener(login -> {
            Intent intentLogin = new Intent(LandingPage.this, LoginActivity.class);
            LandingPage.this.startActivity(intentLogin);
        });

        buttonRegister.setOnClickListener(login -> {
            Intent intentLogin = new Intent(LandingPage.this, RegisterNameActivity.class);
            LandingPage.this.startActivity(intentLogin);

        });



    }
}
