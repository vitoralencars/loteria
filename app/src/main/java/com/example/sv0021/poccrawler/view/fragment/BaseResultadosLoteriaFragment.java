package com.example.sv0021.poccrawler.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sv0021.poccrawler.R;
import com.example.sv0021.poccrawler.implement.ResultadosLoteriaImpl;
import com.example.sv0021.poccrawler.view.activity.LoteriaActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseResultadosLoteriaFragment extends Fragment {

    private LoteriaActivity activity;

    private LinearLayout llResultadosGerais, llResultadosDetalhados, llValorEstimado;
    private TextView tvNomeLoteria, tvDataSorteioConcurso, tvGanhadores, tvValorEstimado,
            tvDataProximoSorteio, tvConcurso;
    private View viewTraco;
    private ImageView ivIconeLoteria, ivAnterior, ivProximo;
    private RecyclerView rvDezenas, rvResultados, rvCidades;

    private ResultadosLoteriaImpl impl = new ResultadosLoteriaImpl();

    private int concursoAtual;

    public BaseResultadosLoteriaFragment() {
        // Required empty public constructor
    }


    public void initBaseView(View v){
        llResultadosGerais = v.findViewById(R.id.llResultadosGerais);
        llResultadosDetalhados = v.findViewById(R.id.llResultadosDetalhados);
        llValorEstimado = v.findViewById(R.id.llValorEstimado);
        tvNomeLoteria = v.findViewById(R.id.tvNomeLoteria);
        tvDataSorteioConcurso = v.findViewById(R.id.tvDataSorteioConcurso);
        tvGanhadores = v.findViewById(R.id.tvGanhadores);
        tvValorEstimado = v.findViewById(R.id.tvValorEstimado);
        tvDataProximoSorteio = v.findViewById(R.id.tvDataProximoSorteio);
        tvConcurso = v.findViewById(R.id.tvConcurso);
        viewTraco = v.findViewById(R.id.viewTraco);
        ivIconeLoteria = v.findViewById(R.id.ivIconeLoteria);
        ivAnterior = v.findViewById(R.id.ivAnterior);
        ivProximo = v.findViewById(R.id.ivProximo);
        rvDezenas = v.findViewById(R.id.rvDezenas);
        rvResultados = v.findViewById(R.id.rvResultados);
        rvCidades = v.findViewById(R.id.rvCidades);
    }

    public void initBaseEvents(){
        ivProximo.setOnClickListener(view -> {
            concursoAtual++;
            consultarConcurso();
        });

        ivAnterior.setOnClickListener(view -> {
            concursoAtual--;
            consultarConcurso();
        });

        impl.onSetTituloLoteria(
                activity,
                ivIconeLoteria,
                tvNomeLoteria
        );

        impl.onPintarViews(
                activity.getLoteria().getCorPadrao(),
                viewTraco,
                tvNomeLoteria,
                tvGanhadores,
                tvValorEstimado,
                ivAnterior,
                ivProximo
        );

        impl.onSetDatas(
                activity.getLoteria().getProximoSorteio(),
                tvDataProximoSorteio
        );

        consultarConcurso();
    }

    public void setActivity(LoteriaActivity activity){
        this.activity = activity;
        setConcursoAtual();
    }

    private  void setConcursoAtual(){
        this.concursoAtual = activity.getLoteria().getConcurso();
    }

    private void consultarConcurso(){
        tvConcurso.setText("Concurso " + concursoAtual);

        impl.onAtualizarViewConcurso(
                llResultadosGerais,
                llResultadosDetalhados,
                llValorEstimado,
                concursoAtual,
                activity.getUltimoConcurso(),
                ivAnterior,
                ivProximo
        );

        impl.onConsultarConcurso(
                activity,
                BaseResultadosLoteriaFragment.this,
                concursoAtual
        );
    }

    /*public void exibirInfos(){
        llResultadosGerais.setVisibility(View.VISIBLE);
        llResultadosDetalhados.setVisibility(View.VISIBLE);

        impl.onExibirResultadoPrincipal(
                activity,
                tvDataSorteioConcurso,
                tvGanhadores,
                tvValorEstimado,
                rvCidades,
                rvDezenas
        );

        impl.onExibirResultadosDetalhados(
                activity,
                rvResultados
        );
    }*/

}
