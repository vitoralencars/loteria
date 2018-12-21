package com.example.sv0021.poccrawler.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.sv0021.poccrawler.R;
import com.example.sv0021.poccrawler.implement.UltimosConcursosImpl;
import com.example.sv0021.poccrawler.model.dto.LoteriaComum;
import com.example.sv0021.poccrawler.model.dto.LoteriasResponse;
import com.example.sv0021.poccrawler.util.Constants;
import com.example.sv0021.poccrawler.util.recyclerview.ItemClickListener;
import com.example.sv0021.poccrawler.view.adapter.UltimosConcursosAdapter;

public class UltimosConcursosActivity extends BaseActivity implements ItemClickListener {

    private UltimosConcursosImpl impl = new UltimosConcursosImpl();
    private RecyclerView rvConcursos;
    private LoteriasResponse loterias;

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

    public void atualizarLoterias(LoteriasResponse loterias){
        this.loterias = loterias;
        rvConcursos.setAdapter(new UltimosConcursosAdapter(this, loterias.getLoteriasComuns()));
    }

    private void exibirDuplaSena(){

    }

    @Override
    public void onItemClick(int position) {
        LoteriaComum loteria = loterias.getLoteriasComuns().get(position);

        Intent intent = new Intent(UltimosConcursosActivity.this, LoteriaActivity.class);
        intent.putExtra(Constants.EXTRA_LOTERIA, loteria);

        loadActivity(intent);
    }
}
