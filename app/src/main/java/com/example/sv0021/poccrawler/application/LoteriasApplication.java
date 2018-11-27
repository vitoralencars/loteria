package com.example.sv0021.poccrawler.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.sv0021.poccrawler.util.Constants;


public class LoteriasApplication extends Application {

    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
    }

    private static SharedPreferences getSharedPreferences(){
        return appContext.getSharedPreferences(Constants.SHARED_PREFS_TAG, Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getPreferencesEditor(){
        return getSharedPreferences().edit();
    }

    public static void savePreferences(String key, String valor){
        SharedPreferences.Editor editor = getPreferencesEditor();
        editor.putString(key, valor);
        editor.commit();
    }

    public static String getPreferences(String key){
        return getSharedPreferences().getString(key, "");
    }

}
