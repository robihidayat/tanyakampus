package id.campusin.tanyakampus.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import id.campusin.tanyakampus.R;
import id.campusin.tanyakampus.fragment.AmbassadorFragment;
import id.campusin.tanyakampus.fragment.AskFragment;
import id.campusin.tanyakampus.fragment.ProfileFragment;
import id.campusin.tanyakampus.fragment.HomeFragment;
import id.campusin.tanyakampus.fragment.QuestionAnswerFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MainTheme);
        setContentView(R.layout.activity_main);
        loadFragment(new HomeFragment());
        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = item -> {
        switch (item.getItemId()) {
            case R.id.menu_action_answer:
                loadFragment(new QuestionAnswerFragment());
                return true;
            case R.id.menu_action_favorites:
                loadFragment(new ProfileFragment());
                return true;
            case R.id.menu_action_home:
                loadFragment(new HomeFragment());
                return true;
            case R.id.menu_action_ambassador:
                loadFragment(new AmbassadorFragment());
                return true;
            case R.id.menu_action_ask:
                loadFragment(new AskFragment());
                return true;
        }
        return false;
    };


    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
