package com.example.sv0021.poccrawler.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.sv0021.poccrawler.R;
import com.example.sv0021.poccrawler.implement.LoteriaImpl;
import com.example.sv0021.poccrawler.model.dto.LoteriaResponse;
import com.example.sv0021.poccrawler.util.Constants;

public class LoteriaActivity extends BaseActivity {

    private LoteriaImpl impl = new LoteriaImpl();

    private LoteriaResponse loteria;

    private ViewPager vpLoteria;
    private BottomNavigationView bnvMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_loteria);
        initView();
        recuperarIntent(getIntent());
        configurarComponentes();
    }

    private void initView(){
        vpLoteria = findViewById(R.id.vpLoteria);
        bnvMenu = findViewById(R.id.bnvMenu);
    }

    private void recuperarIntent(Intent intent){
        if(intent != null){
            loteria = (LoteriaResponse)intent.getExtras().getSerializable(Constants.EXTRA_LOTERIA);
        }
    }

    private void configurarComponentes(){
        impl.onMontarViewPager(this, vpLoteria);
        impl.onSetNavigationListener(vpLoteria, bnvMenu);
    }

    public LoteriaResponse getLoteria() {
        return loteria;
    }

    public void setLoteria(LoteriaResponse loteria){
        this.loteria = loteria;
    }

    public void exibirMenuInferior(String corPadrao){
        bnvMenu.setVisibility(View.VISIBLE);
        bnvMenu.setBackgroundColor(Color.parseColor(loteria.getCorPadrao()));
    }

}
