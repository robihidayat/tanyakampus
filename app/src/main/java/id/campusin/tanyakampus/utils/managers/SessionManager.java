package id.campusin.tanyakampus.utils.managers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;


import java.util.HashMap;

import id.campusin.tanyakampus.activities.LoginActivity;
import id.campusin.tanyakampus.model.response.LoginModelResponse;

public class SessionManager {

    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "AndroidHivePref";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "name";

    public static final String KEY_PHONE = "phone";

    public static final String KEY_AVATAR = "avatar";

    public static final String KEY_INTEREST = "interest";

    public static final String KEY_DEPARTMENT = "department";

    public static final String KEY_SCHOOL = "school";

    public static final String KEY_TOKEN = "token";


    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";

    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public void createLoginSession(String name, String email, String phone, String avatar){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PHONE, phone);
        editor.putString(KEY_AVATAR, avatar);
        editor.commit();
    }

    public void profileUser(String name,
                            String email,
                            String phone,
                            String profilePicture,
                            String interest,
                            String school,
                            String department) {
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PHONE, phone);
        editor.putString(KEY_AVATAR, profilePicture);
        editor.putString(KEY_INTEREST, interest);
        editor.putString(KEY_SCHOOL, school);
        editor.putString(KEY_DEPARTMENT, department);
        editor.commit();
    }

    public void setProfile(LoginModelResponse profile){
        editor.putString(KEY_NAME, profile.getUser().getName());
        editor.putString(KEY_EMAIL, profile.getUser().getEmail());
        editor.putString(KEY_PHONE, profile.getUser().getPhone());
        editor.putString(KEY_AVATAR, profile.getUser().getProfilePicture());
        editor.putString(KEY_INTEREST, profile.getUser().getInterest());
        editor.putString(KEY_SCHOOL, profile.getUser().getSchool());
        editor.putString(KEY_DEPARTMENT, profile.getUser().getDepartment());
        editor.commit();
    }

    public void setToken( String token){
        // Storing avatar in pref
        editor.putString(KEY_TOKEN, "Bearer "+token);
        // commit changes
        editor.commit();
    }

    public String getToken(){
       return pref.getString(KEY_TOKEN, null);
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

    }



    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));

        // user email id
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));

        user.put(KEY_PHONE, pref.getString(KEY_PHONE, null));

        user.put(KEY_AVATAR, pref.getString(KEY_AVATAR, null));

        user.put(KEY_DEPARTMENT, pref.getString(KEY_DEPARTMENT, null));

        user.put(KEY_SCHOOL, pref.getString(KEY_SCHOOL, null));

        user.put(KEY_INTEREST, pref.getString(KEY_INTEREST, null));

        user.put(KEY_TOKEN, pref.getString(KEY_TOKEN, null));

        // return user
        return user;
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }

    public void clearSession(){
        editor.clear();
        editor.commit();
    }


    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }


}

