package com.example.sv0021.poccrawler.presenter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sv0021.poccrawler.model.dto.LoteriaResponse;
import com.example.sv0021.poccrawler.view.activities.LoteriaActivity;

import java.util.Date;

public interface ResultadosLoteriaPresenter {

    void setTituloLoteria(
            LoteriaActivity context,
            ImageView ivIconeLoteria,
            TextView tvNomeLoteria
    );

    void pintarViews(
            String corPadrao,
            View viewTraco,
            TextView tvNomeLoteria,
            TextView tvGanhadores,
            TextView tvValorEstimado,
            ImageView ivAnterior,
            ImageView ivProximo
    );

    void exibirResultadoPrincipal(
            LoteriaActivity context,
            TextView tvGanhadores,
            TextView tvValorEstimado,
            RecyclerView rvDezenas
    );

    void setDataProximoSorteio(
            Date data,
            TextView tvData
    );

}
