package id.campusin.tanyakampus.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Objects;

import id.campusin.tanyakampus.R;
import id.campusin.tanyakampus.helper.ApiInterfaceService;
import id.campusin.tanyakampus.helper.RetrofitUtils;
import id.campusin.tanyakampus.model.response.RegisterFirebaseResponse;
import id.campusin.tanyakampus.utils.managers.SessionManager;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LandingPage extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "LANDING_PAGE";
    private int RC_SIGN_IN = 7;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    private SessionManager session;
    private ApiInterfaceService apiInterfaceService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new SessionManager(getApplicationContext());
        setTheme(R.style.MainTheme);
        setContentView(R.layout.activity_landing_page);
        mAuth = FirebaseAuth.getInstance();
        SignInButton signInButtonGoogle = findViewById(R.id.SignInButton_button_google);
        findViewById(R.id.button_landing_page_login).setOnClickListener(this);
        findViewById(R.id.button_landing_page_register).setOnClickListener(this);
        setGooglePlusButtonText(signInButtonGoogle,  "CONTINUE WITH GOOGLE");
        signInButtonGoogle.setOnClickListener(this);
        apiInterfaceService = RetrofitUtils.apiService();

        GoogleSignInOptions gso =  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    @Override
    public void onStart() {
        super.onStart();
        if(session.isLoggedIn()){
            startActivity(new Intent(this, MainActivity.class));
        }
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser!=null){
            updateUI(currentUser);
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    private void updateUI(FirebaseUser user) {
        if(user != null){
            session.createLoginSession(
                    user.getDisplayName(),
                    user.getEmail(),
                    user.getPhoneNumber(),
                    Objects.requireNonNull(user.getPhotoUrl()).toString());
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(command -> {
                    if(command.isSuccessful()){
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                    } else {
                        Log.w("Login", "signInWithCredential:failure", command.getException());
                        Toast.makeText(this,"Auth Failed", Toast.LENGTH_LONG).show();
                        updateUI(null);
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                assert account != null;
                firebaseAuthWithGoogle(Objects.requireNonNull(account));
                requestRegisterFirebase(account.getDisplayName(), account.getEmail(),"", account.getIdToken(), Objects.requireNonNull(account.getPhotoUrl()).toString());
            } catch (ApiException e) {
                Log.w(TAG, "Google sign in failed", e);
            }
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    protected void setGooglePlusButtonText(SignInButton signInButton, String buttonText) {
        for (int i = 0; i < signInButton.getChildCount(); i++) {
            View v = signInButton.getChildAt(i);
            if (v instanceof TextView) {
                TextView tv = (TextView) v;
                tv.setText(buttonText);
                return;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.SignInButton_button_google:
                signIn();
                break;
            case R.id.button_landing_page_login:
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                break;
            case R.id.button_landing_page_register:
                startActivity(new Intent(getApplicationContext(), RegisterStep1Activity.class));
                break;
        }
    }


    @SuppressLint("CheckResult")
    private void requestRegisterFirebase(String name, String email, String phone, String password, String avatar) {
        Observable<RegisterFirebaseResponse> response = apiInterfaceService.registerFirebaseRequestObservable(email, name, password, phone, "user", avatar);
        response.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(result-> !result.getToken().isEmpty())
                .map(RegisterFirebaseResponse::getToken)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("sessionnya "+ s);
                        session.setToken(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));

                    }
                });

    }

}
