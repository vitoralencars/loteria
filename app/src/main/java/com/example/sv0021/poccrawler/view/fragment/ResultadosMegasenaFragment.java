package com.example.sv0021.poccrawler.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sv0021.poccrawler.R;
import com.example.sv0021.poccrawler.view.activity.LoteriaActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultadosMegasenaFragment extends BaseResultadosLoteriaFragment {


    public ResultadosMegasenaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_resultados_megasena, container, false);

        setActivity((LoteriaActivity)getActivity());
        initBaseView(view);
        initBaseEvents();
        exibirInfos();

        return view;
    }

}
