package com.example.sv0021.poccrawler.presenter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sv0021.poccrawler.view.activity.LoteriaActivity;
import com.example.sv0021.poccrawler.view.fragment.ResultadosLoteriaFragment;

import java.util.Date;

public interface ResultadosLoteriaPresenter {

    void onSetTituloLoteria(
            LoteriaActivity context,
            ImageView ivIconeLoteria,
            TextView tvNomeLoteria
    );

    void onPintarViews(
            String corPadrao,
            View viewTraco,
            TextView tvNomeLoteria,
            TextView tvGanhadores,
            TextView tvValorEstimado,
            ImageView ivAnterior,
            ImageView ivProximo
    );

    void onAtualizarViewConcurso(
            LinearLayout llResultadosGerais,
            LinearLayout llResultadosDetalhados,
            LinearLayout llValorEstimado,
            int concursoAtual,
            int ultimoConcurso,
            ImageView ivAnterior,
            ImageView ivProximo
    );

    void onConsultarConcurso(
            LoteriaActivity context,
            ResultadosLoteriaFragment fragment,
            int concurso
    );

    void onExibirResultadoPrincipal(
            LoteriaActivity context,
            TextView tvGanhadores,
            TextView tvValorEstimado,
            RecyclerView rvCidades,
            RecyclerView rvDezenas
    );

    void onExibirResultadosDetalhados(
            LoteriaActivity context,
            RecyclerView rvResultados
    );

    void onSetDataProximoSorteio(
            Date data,
            TextView tvData
    );

}
