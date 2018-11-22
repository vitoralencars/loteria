package com.example.sv0021.poccrawler.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sv0021.poccrawler.R;
import com.example.sv0021.poccrawler.implement.ResultadosLoteriaImpl;
import com.example.sv0021.poccrawler.view.activities.LoteriaActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultadosLoteriaFragment extends Fragment {

    private LoteriaActivity activity;

    private TextView tvNomeLoteria, tvGanhadores, tvValorEstimado, tvDataProximoSorteio;
    private View viewTraco;
    private ImageView ivIconeLoteria, ivAnterior, ivProximo;
    private RecyclerView rvDezenas;

    private ResultadosLoteriaImpl impl = new ResultadosLoteriaImpl();

    public ResultadosLoteriaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_resultados_loteria, container, false);
        activity = (LoteriaActivity)getActivity();

        initView(view);
        initEvents();

        return view;
    }

    private void initView(View v){
        tvNomeLoteria = v.findViewById(R.id.tvNomeLoteria);
        tvGanhadores = v.findViewById(R.id.tvGanhadores);
        tvValorEstimado = v.findViewById(R.id.tvValorEstimado);
        tvDataProximoSorteio = v.findViewById(R.id.tvDataProximoSorteio);
        viewTraco = v.findViewById(R.id.viewTraco);
        ivIconeLoteria = v.findViewById(R.id.ivIconeLoteria);
        ivAnterior = v.findViewById(R.id.ivAnterior);
        ivProximo = v.findViewById(R.id.ivProximo);
        rvDezenas = v.findViewById(R.id.rvDezenas);
    }

    private void initEvents(){
        impl.setTituloLoteria(
                activity,
                ivIconeLoteria,
                tvNomeLoteria
        );

        impl.pintarViews(
                activity.getLoteria().getCorPadrao(),
                viewTraco,
                tvNomeLoteria,
                tvGanhadores,
                tvValorEstimado,
                ivAnterior,
                ivProximo
        );

        impl.exibirResultadoPrincipal(
                activity,
                tvGanhadores,
                tvValorEstimado,
                rvDezenas
        );

        impl.setDataProximoSorteio(
                activity.getLoteria().getDataSorteio(),
                tvDataProximoSorteio
        );
    }

}
