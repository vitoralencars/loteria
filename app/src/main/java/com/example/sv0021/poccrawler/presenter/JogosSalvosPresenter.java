package com.example.sv0021.poccrawler.presenter;

import android.support.v7.widget.RecyclerView;

import com.example.sv0021.poccrawler.view.activity.LoteriaActivity;

public interface JogosSalvosPresenter {

    void onListarJogosSalvos(
            LoteriaActivity context,
            RecyclerView rvJogosSalvos
    );

}
