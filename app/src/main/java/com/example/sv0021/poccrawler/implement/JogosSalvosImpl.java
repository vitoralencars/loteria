package com.example.sv0021.poccrawler.implement;

import android.support.v7.widget.RecyclerView;

import com.example.sv0021.poccrawler.presenter.JogosSalvosPresenter;
import com.example.sv0021.poccrawler.view.activity.LoteriaActivity;
import com.example.sv0021.poccrawler.view.adapter.JogosSalvosAdapter;

public class JogosSalvosImpl implements JogosSalvosPresenter {

    @Override
    public void onListarJogosSalvos(LoteriaActivity context, RecyclerView rvJogosSalvos) {
        JogosSalvosAdapter adapter = new JogosSalvosAdapter(context);
        rvJogosSalvos.setAdapter(adapter);
    }
}
