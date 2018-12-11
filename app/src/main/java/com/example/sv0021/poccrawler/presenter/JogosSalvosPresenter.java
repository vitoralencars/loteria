package com.example.sv0021.poccrawler.presenter;

import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.example.sv0021.poccrawler.view.activity.LoteriaActivity;
import com.example.sv0021.poccrawler.view.adapter.ConcursosAdapter;
import com.example.sv0021.poccrawler.view.adapter.JogosSalvosAdapter;

public interface JogosSalvosPresenter {

    void onListarConcursos(
            LoteriaActivity context,
            RecyclerView rvConcursos
    );

    void onConferirResultados(
            LoteriaActivity context,
            int codConcurso,
            ConcursosAdapter adapter,
            ProgressBar progressBar
    );

}
