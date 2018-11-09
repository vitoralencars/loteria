package com.example.sv0021.poccrawler.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sv0021.poccrawler.model.DezenaCartela;
import com.example.sv0021.poccrawler.model.Loteria;
import com.example.sv0021.poccrawler.util.RecyclerViewClickListener;
import com.example.sv0021.poccrawler.view.activities.BaseCartelaActivity;
import com.example.sv0021.poccrawler.view.adapter.CartelaAdapter;

import java.util.List;

public interface BaseCartelaPresenter {

    void onMontarCartela(
            BaseCartelaActivity context,
            Loteria loteria,
            RecyclerView rvDezenas
    );

    void onCompletarCartela(
            BaseCartelaActivity context,
            Loteria loteria
    );

    void onLimparCartela(
            BaseCartelaActivity context,
            Loteria loteria
    );

    void onDezenaClick(
            BaseCartelaActivity context,
            int index,
            TextView tvDezena,
            Loteria loteria
    );

    void onExibirDezenasSelecionadas(
            TextView tvDezenasSelecionadas,
            List<DezenaCartela> dezenasSelecionados
    );

    void onInitSpinnerQtdDezenas(
            BaseCartelaActivity context,
            Spinner spQtdDezenas,
            Loteria loteria
    );

    void onSpinnerSelectionListener(
            BaseCartelaActivity context,
            int position,
            Loteria loteria
    );
}
