package com.example.sv0021.poccrawler.presenter;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
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
            Cartela cartela,
            EditText etTimeCoracao
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

    void onSetTimeCoracaoEdicao(
            EditText etTime,
            String timeCoracao
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

    void onSalvarJogo(
            LoteriaActivity context,
            CartelaFragment fragment,
            Cartela cartela
    );

    void onMontarCartelaEdicao(
            LoteriaActivity context,
            Spinner spQtdDezenas,
            CartelaFragment fragment,
            Cartela cartela
    );

    void onConfigurarTimeCoracao(
            LoteriaActivity context,
            CartelaFragment fragment,
            TextInputLayout tilTime,
            EditText etTimeCoracao
    );

    void onTimeCoracaoResult(
            EditText etTime,
            Cartela cartela,
            int requestCode,
            int resultCode,
            Intent data
    );

    void onConfigurarBotoes(
            LoteriaActivity context,
            CartelaFragment fragment,
            Cartela cartela,
            Button btnCompletar,
            Button btnSalvar,
            Button btnLimpar
    );
}
