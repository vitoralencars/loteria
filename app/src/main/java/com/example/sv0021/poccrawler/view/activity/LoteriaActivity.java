package com.example.sv0021.poccrawler.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.view.View;

import com.example.sv0021.poccrawler.R;
import com.example.sv0021.poccrawler.application.LoteriasApplication;
import com.example.sv0021.poccrawler.implement.LoteriaImpl;
import com.example.sv0021.poccrawler.model.Concurso;
import com.example.sv0021.poccrawler.model.dto.LoteriaResponse;
import com.example.sv0021.poccrawler.util.Constants;

import java.util.List;

public class LoteriaActivity extends BaseActivity {

    private LoteriaImpl impl = new LoteriaImpl();

    private LoteriaResponse loteria;

    private BottomNavigationView bnvMenu;

    private Long idJogoEdicao = null;
    private int ultimoConcurso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_loteria);
        initView();
        recuperarIntent(getIntent());
        configurarComponentes();
    }

    private void initView(){
        bnvMenu = findViewById(R.id.bnvMenu);
    }

    private void recuperarIntent(Intent intent){
        if(intent != null){
            loteria = (LoteriaResponse)intent.getExtras().getSerializable(Constants.EXTRA_LOTERIA);
            ultimoConcurso = loteria.getConcurso();
        }
    }

    private void configurarComponentes(){
        impl.onSetNavigationListener(this, bnvMenu);
        impl.onSetPrimeiroFragment(this);
    }

    public LoteriaResponse getLoteria() {
        return loteria;
    }

    public void setLoteria(LoteriaResponse loteria){
        this.loteria = loteria;
    }

    public int getUltimoConcurso(){
        return this.ultimoConcurso;
    }

    public void exibirMenuInferior(){
        bnvMenu.setVisibility(View.VISIBLE);
        bnvMenu.setBackgroundColor(Color.parseColor(loteria.getCorPadrao()));
    }

    public List<Concurso> getConcursosSalvos(){
        return impl.onGetConcursosSalvos(loteria.getCodigoLoteria());
    }

    public void editarJogoSalvo(Long idJogo){
        bnvMenu.setSelectedItemId(R.id.menu_cartela);
        this.idJogoEdicao = idJogo;
    }

    public Long getIdJogoEdicao(){
        return this.idJogoEdicao;
    }

    public void desativarEdicao(){
        this.idJogoEdicao = null;
    }

    public void salvarJogo(String json){
            LoteriasApplication.savePreferences(
                    LoteriasApplication.getPreferenceKey(loteria.getCodigoLoteria()),
                    json
            );
    }
}
