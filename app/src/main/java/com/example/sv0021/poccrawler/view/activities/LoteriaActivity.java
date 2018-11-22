package com.example.sv0021.poccrawler.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.example.sv0021.poccrawler.R;
import com.example.sv0021.poccrawler.implement.LoteriaImpl;
import com.example.sv0021.poccrawler.model.dto.LoteriaResponse;
import com.example.sv0021.poccrawler.util.Constants;

public class LoteriaActivity extends BaseActivity {

    private LoteriaImpl impl = new LoteriaImpl();

    private LoteriaResponse loteria;
    private ViewPager vpLoteria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_loteria);
        initView();
        recuperarIntent(getIntent());
        montarViewPager();
    }

    private void initView(){
        vpLoteria = findViewById(R.id.vpLoteria);
    }

    private void recuperarIntent(Intent intent){
        if(intent != null){
            loteria = (LoteriaResponse)intent.getExtras().getSerializable(Constants.EXTRA_LOTERIA);
        }
    }

    private void montarViewPager(){
        impl.onMontarViewPager(this, vpLoteria);
    }

    public LoteriaResponse getLoteria() {
        return loteria;
    }

    public void setLoteria(LoteriaResponse loteria){
        this.loteria = loteria;
    }

}
