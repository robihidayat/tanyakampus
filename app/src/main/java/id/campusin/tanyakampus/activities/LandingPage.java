package id.campusin.tanyakampus.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import id.campusin.tanyakampus.R;

public class LandingPage extends AppCompatActivity {

    private Button buttonRegister, buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        buttonLogin = findViewById(R.id.button_landing_page_login);
        buttonRegister = findViewById(R.id.button_landing_page_register);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View login) {
                Intent intentLogin = new Intent(LandingPage.this, LoginActivity.class);
                LandingPage.this.startActivity(intentLogin);
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View login) {
                Intent intentLogin = new Intent(LandingPage.this, LoginActivity.class);
                LandingPage.this.startActivity(intentLogin);

            }
        });



    }
}
