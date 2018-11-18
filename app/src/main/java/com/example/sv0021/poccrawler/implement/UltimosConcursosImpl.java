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
        RetrofitBuilder builder = new RetrofitBuilder(Constants.BASE_API_URL);
        Service service = builder.getService();
        service.getUltimosResultados().enqueue(new Callback<List<LoteriaResponse>>() {
            @Override
            public void onResponse(Call<List<LoteriaResponse>> call, Response<List<LoteriaResponse>> response) {
                if(response.body() != null){
                    List<LoteriaResponse> loterias = response.body();
                    context.atualizarLoterias(loterias);
                }
            }

            @Override
            public void onFailure(Call<List<LoteriaResponse>> call, Throwable t) {
                System.out.println(t);
            }
        });
    }

}
