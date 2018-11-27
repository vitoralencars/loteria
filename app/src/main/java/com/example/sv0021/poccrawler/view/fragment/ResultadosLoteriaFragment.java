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
public class ResultadosLoteriaFragment extends Fragment {

    private LoteriaActivity activity;

    private LinearLayout llResultadosGerais, llResultadosDetalhados, llValorEstimado;
    private TextView tvNomeLoteria, tvGanhadores, tvValorEstimado, tvDataProximoSorteio, tvConcurso;
    private View viewTraco;
    private ImageView ivIconeLoteria, ivAnterior, ivProximo;
    private RecyclerView rvDezenas, rvResultados;

    private ResultadosLoteriaImpl impl = new ResultadosLoteriaImpl();

    private int concursoAtual;

    public ResultadosLoteriaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_resultados_loteria, container, false);
        activity = (LoteriaActivity)getActivity();
        concursoAtual = activity.getLoteria().getConcurso();

        initView(view);
        initEvents();

        return view;
    }

    private void initView(View v){
        llResultadosGerais = v.findViewById(R.id.llResultadosGerais);
        llResultadosDetalhados = v.findViewById(R.id.llResultadosDetalhados);
        llValorEstimado = v.findViewById(R.id.llValorEstimado);
        tvNomeLoteria = v.findViewById(R.id.tvNomeLoteria);
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
    }

    private void initEvents(){
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

        impl.onSetDataProximoSorteio(
                activity.getLoteria().getDataSorteio(),
                tvDataProximoSorteio
        );

        consultarConcurso();
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
                ResultadosLoteriaFragment.this,
                concursoAtual
        );
    }

    public void exibirInfos(){
        llResultadosGerais.setVisibility(View.VISIBLE);
        llResultadosDetalhados.setVisibility(View.VISIBLE);

        impl.onExibirResultadoPrincipal(
                activity,
                tvGanhadores,
                tvValorEstimado,
                rvDezenas
        );

        impl.onExibirResultadosSecundarios(
                activity,
                rvResultados
        );
    }

}
