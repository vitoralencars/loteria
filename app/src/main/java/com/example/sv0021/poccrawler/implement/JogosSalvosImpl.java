package com.example.sv0021.poccrawler.implement;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.sv0021.poccrawler.model.dto.LoteriaResponse;
import com.example.sv0021.poccrawler.presenter.JogosSalvosPresenter;
import com.example.sv0021.poccrawler.retrofit.RetrofitBuilder;
import com.example.sv0021.poccrawler.retrofit.Service;
import com.example.sv0021.poccrawler.view.activity.LoteriaActivity;
import com.example.sv0021.poccrawler.view.adapter.ConcursosAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JogosSalvosImpl implements JogosSalvosPresenter {

    @Override
    public void onListarConcursos(LoteriaActivity context, RecyclerView rvConcursos) {
        ConcursosAdapter adapter = new ConcursosAdapter(context);
        rvConcursos.setAdapter(adapter);
    }

    @Override
    public void onConferirResultados(LoteriaActivity context, int codConcurso, int index, ConcursosAdapter adapter, ProgressBar progressBar) {
        Service service = new RetrofitBuilder().getService();
        service.getResultadoConcurso(context.getLoteria().getCodigoLoteria(),
                codConcurso).enqueue(new Callback<LoteriaResponse>() {
            @Override
            public void onResponse(Call<LoteriaResponse> call, Response<LoteriaResponse> response) {
                progressBar.setVisibility(View.GONE);
                adapter.setResultado(response.body(), index);
            }

            @Override
            public void onFailure(Call<LoteriaResponse> call, Throwable t) {

            }
        });
    }
}
