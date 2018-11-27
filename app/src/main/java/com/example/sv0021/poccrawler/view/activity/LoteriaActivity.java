package com.example.sv0021.poccrawler.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.view.View;
import android.widget.FrameLayout;

import com.example.sv0021.poccrawler.R;
import com.example.sv0021.poccrawler.implement.LoteriaImpl;
import com.example.sv0021.poccrawler.model.JogoSalvo;
import com.example.sv0021.poccrawler.model.dto.LoteriaResponse;
import com.example.sv0021.poccrawler.util.Constants;

import java.util.List;

public class LoteriaActivity extends BaseActivity {

    private LoteriaImpl impl = new LoteriaImpl();

    private LoteriaResponse loteria;

    private BottomNavigationView bnvMenu;
    private FrameLayout flFragments;

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
        flFragments = findViewById(R.id.flFragments);
    }

    private void recuperarIntent(Intent intent){
        if(intent != null){
            loteria = (LoteriaResponse)intent.getExtras().getSerializable(Constants.EXTRA_LOTERIA);
            ultimoConcurso = loteria.getConcurso();
        }
    }

    private void configurarComponentes(){
        impl.onSetNavigationListener(this, bnvMenu, flFragments);
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

    public List<JogoSalvo> getJogosSalvos(){
        return impl.onGetJogosSalvos(loteria.getCodigoLoteria());
    }
}
