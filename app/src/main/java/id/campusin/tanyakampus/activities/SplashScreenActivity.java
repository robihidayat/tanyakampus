package id.campusin.tanyakampus.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import id.campusin.tanyakampus.BuildConfig;
import id.campusin.tanyakampus.R;

public class SplashScreenActivity extends AppCompatActivity {

    private int loadingTime = 4000;
    private TextView textViewVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MainTheme);
        setContentView(R.layout.activity_splash_screan);


        textViewVersion = findViewById(R.id.textView_version);
        textViewVersion.setText(BuildConfig.VERSION_NAME);
        new Handler().postDelayed(() -> {
            Intent home = new Intent(SplashScreenActivity.this, LandingPage.class);
            startActivity(home);
            finish();
        },loadingTime);



    }
}
