package id.campusin.tanyakampus.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import id.campusin.tanyakampus.BuildConfig;
import id.campusin.tanyakampus.R;
import id.campusin.tanyakampus.helper.ApiInterfaceService;
import id.campusin.tanyakampus.helper.RetrofitUtils;
import id.campusin.tanyakampus.model.response.UserResponse;
import id.campusin.tanyakampus.utils.managers.SessionManager;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SplashScreenActivity extends AppCompatActivity {

    private int loadingTime = 2000;
    private TextView textViewVersion;
    private ApiInterfaceService apiInterfaceService;
    private SessionManager session;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MainTheme);
        setContentView(R.layout.activity_splash_screan);
        apiInterfaceService = RetrofitUtils.apiService();
        session = new SessionManager(getApplicationContext());
        textViewVersion = findViewById(R.id.textView_version);
        textViewVersion.setText(BuildConfig.VERSION_NAME);

        GoogleSignInOptions gso =  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mAuth = FirebaseAuth.getInstance();
        mGoogleSignInClient = GoogleSignIn.getClient(Objects.requireNonNull(getApplicationContext()), gso);
        processLaunching();
    }


    private void processLaunching(){
        Observable<UserResponse> response = apiInterfaceService.userRequestObservable(session.getToken());
        response.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserResponse userResponse) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        session.clearSession();
                        mAuth.signOut();
                        mGoogleSignInClient.signOut();
                        loginHandler();
                    }

                    @Override
                    public void onComplete() {
                        launching();
                    }
                });

    }

    private void launching(){
        new Handler().postDelayed(() -> {
            Intent home = new Intent(SplashScreenActivity.this, MainActivity.class);
            startActivity(home);
            finish();
        },loadingTime);
    }

    private void loginHandler(){
        new Handler().postDelayed(() -> {
            Intent home = new Intent(SplashScreenActivity.this, LandingPage.class);
            startActivity(home);
            finish();
        },loadingTime);
    }



}
