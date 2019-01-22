package model;

import android.app.Application;

import helper.RetroClient;
import helper.RetroClientYoutube;

public class MainApplication extends Application {

    public static RetroClient retroClient;
    public static RetroClientYoutube retroClientYoutube;

    @Override
    public void onCreate() {
        super.onCreate();
        retroClient = retroClient.getInstance();
        retroClientYoutube = retroClientYoutube.getInstance();
    }
}