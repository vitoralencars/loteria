package com.example.sv0021.poccrawler.presenter;

import android.support.v7.widget.RecyclerView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sv0021.poccrawler.model.Cartela;
import com.example.sv0021.poccrawler.model.DezenaCartela;
import com.example.sv0021.poccrawler.view.activity.LoteriaActivity;
import com.example.sv0021.poccrawler.view.fragment.CartelaFragment;

import java.util.List;

public interface CartelaPresenter {

    void onMontarCartela(
            LoteriaActivity context,
            CartelaFragment fragment,
            Cartela cartela,
            RecyclerView rvDezenas
    );

    void onCompletarCartela(
            LoteriaActivity context,
            CartelaFragment fragment,
            Cartela cartela
    );

    void onLimparCartela(
            LoteriaActivity context,
            CartelaFragment fragment,
            Cartela cartela
    );

    void onDezenaClick(
            LoteriaActivity context,
            CartelaFragment fragment,
            int index,
            TextView tvDezena,
            Cartela cartela
    );

    void onExibirDezenasSelecionadas(
            TextView tvDezenasSelecionadas,
            List<DezenaCartela> dezenasSelecionados
    );

    void onInitSpinnerQtdDezenas(
            LoteriaActivity context,
            Spinner spQtdDezenas,
            Cartela cartela
    );

    void onSpinnerSelectionListener(
            LoteriaActivity context,
            CartelaFragment fragment,
            int position,
            Cartela cartela
    );
}
