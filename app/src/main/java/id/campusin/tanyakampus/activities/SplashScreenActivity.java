package id.campusin.tanyakampus.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import id.campusin.tanyakampus.R;

public class SplashScreenActivity extends AppCompatActivity {
    private int loadingTime = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.SplashScreen);
        setContentView(R.layout.activity_splash_screan);

        new Handler().postDelayed(() -> {
            Intent home = new Intent(SplashScreenActivity.this, LandingPage.class);
            startActivity(home);
            finish();
        },loadingTime);

    }
}
