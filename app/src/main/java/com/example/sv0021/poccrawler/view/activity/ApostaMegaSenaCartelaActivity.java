package com.example.sv0021.poccrawler.view.activity;

import android.os.Bundle;

import com.example.sv0021.poccrawler.R;
import com.example.sv0021.poccrawler.model.Cartela;


public class ApostaMegaSenaCartelaActivity extends BaseCartelaActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_aposta_mega_sena);
        //setCartela(getLoteria());
        //montarCartela();
    }

    private Cartela getLoteria(){
        Cartela cartela = new Cartela();
        /*cartela.setQtdDesejadaDezenasSelecionadas(Constants.QTD_MINIMA_DEZENAS_SELECIONADAS_MEGA_SENA);
        cartela.setQtdMinimaDezenasSelecionadas(Constants.QTD_MINIMA_DEZENAS_SELECIONADAS_MEGA_SENA);
        cartela.setQtdMaximaDezenasSelecionadas(Constants.QTD_MAXIMA_DEZENAS_SELECIONADAS_MEGA_SENA);
        cartela.setQtdDezenasCartela(Constants.QTD_DEZENAS_MEGA_SENA);*/

        return cartela;
    }

}
