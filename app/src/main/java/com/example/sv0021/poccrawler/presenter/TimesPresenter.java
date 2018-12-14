package com.example.sv0021.poccrawler.presenter;

import android.support.v7.widget.RecyclerView;

import com.example.sv0021.poccrawler.view.activity.TimesActivity;

public interface TimesPresenter {

    void onListarTimes(
            TimesActivity context,
            RecyclerView rvTimes
    );

}
