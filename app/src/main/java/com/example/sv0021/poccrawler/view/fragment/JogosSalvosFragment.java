package com.example.sv0021.poccrawler.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sv0021.poccrawler.R;
import com.example.sv0021.poccrawler.implement.JogosSalvosImpl;
import com.example.sv0021.poccrawler.view.activity.LoteriaActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class JogosSalvosFragment extends Fragment {

    private LoteriaActivity activity;
    private RecyclerView rvConcursos;

    private JogosSalvosImpl impl = new JogosSalvosImpl();

    public JogosSalvosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jogos_salvos, container, false);
        activity = (LoteriaActivity)getActivity();

        initView(view);
        listarConcursosSalvos();

        return view;
    }

    private void initView(View v){
        rvConcursos = v.findViewById(R.id.rvConcursos);
    }

    private void listarConcursosSalvos(){
        impl.onListarConcursos(activity, rvConcursos);
    }

}
