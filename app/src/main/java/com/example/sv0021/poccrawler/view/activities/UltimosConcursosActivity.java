package com.example.sv0021.poccrawler.view.activities;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.sv0021.poccrawler.R;
import com.example.sv0021.poccrawler.implement.UltimosConcursosImpl;
import com.example.sv0021.poccrawler.model.dto.LoteriaResponse;
import com.example.sv0021.poccrawler.view.adapter.UltimosConcursosAdapter;

import java.util.List;

public class UltimosConcursosActivity extends BaseActivity {

    private UltimosConcursosImpl impl = new UltimosConcursosImpl();
    private RecyclerView rvConcursos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_ultimos_concursos);
        initView();
        initEvents();
    }

    private void initView(){
        rvConcursos = findViewById(R.id.rvConcursos);
    }

    private void initEvents(){
        impl.onListarUltimosConcursos(UltimosConcursosActivity.this, rvConcursos);
    }

    public void atualizarLoterias(List<LoteriaResponse> loterias){
        UltimosConcursosAdapter adapter = new UltimosConcursosAdapter(this, loterias);
        rvConcursos.setAdapter(adapter);
    }

}
