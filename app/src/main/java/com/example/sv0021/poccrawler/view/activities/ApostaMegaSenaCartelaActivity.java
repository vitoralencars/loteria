package com.example.sv0021.poccrawler.view.activities;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.example.sv0021.poccrawler.R;
import com.example.sv0021.poccrawler.model.Loteria;
import com.example.sv0021.poccrawler.util.Constants;


public class ApostaMegaSenaCartelaActivity extends BaseCartelaActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_aposta_mega_sena);
        setLoteria(getLoteria());
        montarCartela();
    }

    private Loteria getLoteria(){
        Loteria loteria = new Loteria();
        loteria.setQtdDesejadaDezenasSelecionadas(Constants.QTD_MINIMA_DEZENAS_SELECIONADAS_MEGA_SENA);
        loteria.setQtdMinimaDezenasSelecionadas(Constants.QTD_MINIMA_DEZENAS_SELECIONADAS_MEGA_SENA);
        loteria.setQtdMaximaDezenasSelecionadas(Constants.QTD_MAXIMA_DEZENAS_SELECIONADAS_MEGA_SENA);
        loteria.setQtdDezenasCartela(Constants.QTD_DEZENAS_MEGA_SENA);
        loteria.setCorPrincipal(ContextCompat.getColor(this, R.color.verde_mega_sena));

        return loteria;
    }

}
