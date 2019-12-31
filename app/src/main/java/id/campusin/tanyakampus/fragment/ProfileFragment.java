package id.campusin.tanyakampus.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import id.campusin.tanyakampus.BuildConfig;
import id.campusin.tanyakampus.R;
import id.campusin.tanyakampus.activities.LandingPage;
import id.campusin.tanyakampus.activities.UpdateProfileActivity;
import id.campusin.tanyakampus.utils.managers.SessionManager;


public class ProfileFragment extends Fragment implements View.OnClickListener {


    private SessionManager session;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        GoogleSignInOptions gso =  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mAuth = FirebaseAuth.getInstance();
        mGoogleSignInClient = GoogleSignIn.getClient(Objects.requireNonNull(getContext()), gso);
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.Button_Profile_keluar).setOnClickListener(this);
        view.findViewById(R.id.FloatingActionButton_profile_edit).setOnClickListener(this);
        session = new SessionManager(getContext());
        Glide.with(this).load(session.getUserDetails().get("avatar"))
                .placeholder(R.drawable.profile_lazzy_mode)
                .into((ImageView) view.findViewById(R.id.CircleImageView_profile_ambassador));

        TextView profileName = view.findViewById(R.id.TextView_profile_name);
        TextView profilePhone = view.findViewById(R.id.TextView_profile_phone);
        TextView profileEmail = view.findViewById(R.id.TextView_profile_email);
        TextView profileInterest = view.findViewById(R.id.TextView_profile_interest);
        TextView profileSchool = view.findViewById(R.id.TextView_profile_school);
        TextView profileDepartment = view.findViewById(R.id.TextView_profile_department);
        TextView version = view.findViewById(R.id.textView_version_profile);

        profileName.setText(session.getUserDetails().get("name") != null ? session.getUserDetails().get("name"):"--");
        profileEmail.setText(session.getUserDetails().get("email") != null ? session.getUserDetails().get("email") : "--");
        profilePhone.setText(session.getUserDetails().get("phone") != null ? session.getUserDetails().get("phone") : "--");
        profileInterest.setText(session.getUserDetails().get("interest") != null ? session.getUserDetails().get("interest") : "--");
        profileSchool.setText(session.getUserDetails().get("school") != null ? session.getUserDetails().get("school") : "--");
        profileDepartment.setText(session.getUserDetails().get("department") != null ? session.getUserDetails().get("department") : "--");
        version.setText(BuildConfig.VERSION_NAME);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Button_Profile_keluar:
                signOut();
                startActivity(new Intent(getContext(), LandingPage.class));
                break;
            case R.id.FloatingActionButton_profile_edit:
                startActivity(new Intent(getContext(), UpdateProfileActivity.class));
                break;
        }
    }

    private void signOut() {
        mAuth.signOut();
        mGoogleSignInClient.signOut();
        session.clearSession();
    }
}
