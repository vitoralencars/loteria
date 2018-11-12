package com.example.sv0021.poccrawler.implement;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;

import com.example.sv0021.poccrawler.R;
import com.example.sv0021.poccrawler.model.dto.LoteriaResponse;
import com.example.sv0021.poccrawler.presenter.UltimosConcursosPresenter;
import com.example.sv0021.poccrawler.retrofit.RetrofitBuilder;
import com.example.sv0021.poccrawler.retrofit.Service;
import com.example.sv0021.poccrawler.util.Constants;
import com.example.sv0021.poccrawler.view.activities.UltimosConcursosActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UltimosConcursosImpl implements UltimosConcursosPresenter {

    @Override
    public void onListarUltimosConcursos(UltimosConcursosActivity context, RecyclerView rvConcursos) {
        List<LoteriaResponse> loterias = new ArrayList<>();
        for(String nomeLoteria : Constants.NOMES_LOTERIAS){
            RetrofitBuilder builder = new RetrofitBuilder(Constants.BASE_API_URL);
            Service service = builder.getService();
            service.getUserRepositories(nomeLoteria).enqueue(new Callback<LoteriaResponse>() {
                @Override
                public void onResponse(Call<LoteriaResponse> call, Response<LoteriaResponse> response) {
                    if(response.body() != null){
                        LoteriaResponse loteria = response.body();
                        setAtributosLoterias(context, loteria, nomeLoteria);
                        loterias.add(loteria);
                        context.atualizarLoterias(loterias);
                    }
                }

                @Override
                public void onFailure(Call<LoteriaResponse> call, Throwable t) {

                }
            });
        }
    }

    private void setAtributosLoterias(UltimosConcursosActivity context,
                                     LoteriaResponse loteria,
                                     String nomeLoteria){
        loteria.setNomeLoteria(nomeLoteria);

        switch (nomeLoteria){
            case "mega-sena":
                loteria.setCorPadrao(R.color.verde_mega_sena);
                loteria.setIcLoteria(ContextCompat.getDrawable(context, R.drawable.ic_megasena));
                break;
            case "quina":
                loteria.setCorPadrao(R.color.roxo_quina);
                loteria.setIcLoteria(ContextCompat.getDrawable(context, R.drawable.ic_megasena));
                break;
            case "lotofacil":
                loteria.setCorPadrao(R.color.rosa_lotofacil);
                loteria.setIcLoteria(ContextCompat.getDrawable(context, R.drawable.ic_megasena));
                break;
            case "lotomania:":
                loteria.setCorPadrao(R.color.laranja_lotomania);
                loteria.setIcLoteria(ContextCompat.getDrawable(context, R.drawable.ic_megasena));
                break;
        }

    }

}
