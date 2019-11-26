package id.campusin.tanyakampus.helper;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class AnalyticsApps extends Application {

    private static AnalyticsApps instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public boolean checkIfHasCheckNetwork(){
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public static AnalyticsApps getInstance(){
        return instance;
    }


    public static boolean hasNetWork(){
        return instance.checkIfHasCheckNetwork();
    }



}
