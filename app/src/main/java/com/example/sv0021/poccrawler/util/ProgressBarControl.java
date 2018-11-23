package com.example.sv0021.poccrawler.util;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.example.sv0021.poccrawler.view.activity.BaseActivity;

public class ProgressBarControl {

    private static ProgressBar progressBar;

    public static void mostrarProgressBar(Context context){
        if(context != null) {
            progressBar = ((BaseActivity)context).getProgressBar();
            if (progressBar != null && progressBar.getVisibility() == View.GONE) {
                progressBar.setVisibility(View.VISIBLE);
                ((BaseActivity) context).getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
        }
    }

    public static void esconderProgressBar(Context context){
        if(context != null) {
            progressBar = ((BaseActivity)context).getProgressBar();
            if (progressBar != null && progressBar.getVisibility() == View.VISIBLE) {
                progressBar.setVisibility(View.GONE);
                ((BaseActivity) context).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
        }
    }

}
