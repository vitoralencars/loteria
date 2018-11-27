package com.example.sv0021.poccrawler.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.sv0021.poccrawler.enumeradores.TipoLoteria;
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

    public static String getPreferenceKey(int codigoLoteria){
        switch (codigoLoteria){
            case TipoLoteria.MEGA_SENA:
                return Constants.SHARED_PREFS_JOGOS_MEGA_SENA;
            case TipoLoteria.LOTOFACIL:
                return Constants.SHARED_PREFS_JOGOS_LOTOFACIL;
            case TipoLoteria.QUINA:
                return Constants.SHARED_PREFS_JOGOS_QUINA;
            case TipoLoteria.LOTOMANIA:
                return Constants.SHARED_PREFS_JOGOS_LOTOMANIA;
        }

        return "";
    }

}
