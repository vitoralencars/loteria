package com.example.sv0021.poccrawler.implement;

import android.support.v7.widget.RecyclerView;

import com.example.sv0021.poccrawler.presenter.JogosSalvosPresenter;
import com.example.sv0021.poccrawler.view.activity.LoteriaActivity;
import com.example.sv0021.poccrawler.view.adapter.ConcursosAdapter;

public class JogosSalvosImpl implements JogosSalvosPresenter {

    @Override
    public void onListarConcursos(LoteriaActivity context, RecyclerView rvConcursos) {
        ConcursosAdapter adapter = new ConcursosAdapter(context);
        rvConcursos.setAdapter(adapter);
    }
}
