package com.example.sv0021.poccrawler.implement;

import android.support.v7.widget.RecyclerView;

import com.example.sv0021.poccrawler.model.dto.LoteriasResponse;
import com.example.sv0021.poccrawler.presenter.UltimosConcursosPresenter;
import com.example.sv0021.poccrawler.retrofit.RetrofitBuilder;
import com.example.sv0021.poccrawler.retrofit.Service;
import com.example.sv0021.poccrawler.util.ProgressBarControl;
import com.example.sv0021.poccrawler.view.activity.UltimosConcursosActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UltimosConcursosImpl implements UltimosConcursosPresenter {

    @Override
    public void onListarUltimosConcursos(UltimosConcursosActivity context, RecyclerView rvConcursos) {
        ProgressBarControl.mostrarProgressBar(context);
        Service service = new RetrofitBuilder().getService();
        service.getUltimosResultados().enqueue(new Callback<LoteriasResponse>() {
            @Override
            public void onResponse(Call<LoteriasResponse> call, Response<LoteriasResponse> response) {
                ProgressBarControl.esconderProgressBar(context);
                if(response.body() != null){
                    LoteriasResponse loterias = response.body();
                    context.atualizarLoterias(loterias);
                }
            }

            @Override
            public void onFailure(Call<LoteriasResponse> call, Throwable throwable) {
                ProgressBarControl.esconderProgressBar(context);
            }
        });
    }

}
