package com.memberapps2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ibrahimmalik on 11/3/18.
 */

public class WelcomeActivity extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        SharedPreferences sharedPref = getSharedPreferences("data",MODE_PRIVATE);
        int number = sharedPref.getInt("isLogged", 0);
        if(number == 0) {
            //Open the login activity and set this so that next it value is 1 then this conditin will be false.

            // SharedPreferences.Editor prefEditor = sharedPref.edit();
            //prefEditor.putInt("isLogged",1);
            //prefEditor.commit();

            new Handler().postDelayed(new Runnable() {
                /*
                 * Showing splash screen with a timer. This will be useful when you
                 * want to show case your app logo / company
                 */
                @Override
                public void run() {
                    // This method will be executed once the timer is over
                    // Start your app main activity
                    Intent i = new Intent(WelcomeActivity.this, LoginActivity.class);
                    // Intent i = new Intent(WelcomeActivity.this, MainActivity.class);
                    startActivity(i);
                    // close this activity
                    finish();
                }
            }, SPLASH_TIME_OUT);

        } else {
            //Open this Home activity
            new Handler().postDelayed(new Runnable() {
                /*
                 * Showing splash screen with a timer. This will be useful when you
                 * want to show case your app logo / company
                 */
                @Override
                public void run() {
                    // This method will be executed once the timer is over
                    // Start your app main activity
                    Intent i = new Intent(WelcomeActivity.this, Home.class);
                    // Intent i = new Intent(WelcomeActivity.this, MainActivity.class);
                    startActivity(i);
                    // close this activity
                    finish();
                }
            }, SPLASH_TIME_OUT);
        }

    }


}
