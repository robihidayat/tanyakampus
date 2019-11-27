package id.campusin.tanyakampus.activities;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import id.campusin.tanyakampus.R;
import id.campusin.tanyakampus.fragment.ChatFragment;
import id.campusin.tanyakampus.fragment.FavoriteFragment;
import id.campusin.tanyakampus.fragment.HomeFragment;
import id.campusin.tanyakampus.fragment.AmbassadorFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment(new HomeFragment());
        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = item -> {
        switch (item.getItemId()) {
            case R.id.menu_action_chat:
                loadFragment(new ChatFragment());
                return true;
            case R.id.menu_action_favorites:
                loadFragment(new FavoriteFragment());
                return true;
            case R.id.menu_action_home:
                loadFragment(new HomeFragment());
                return true;
            case R.id.menu_action_ambassador:
                loadFragment(new AmbassadorFragment());
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
