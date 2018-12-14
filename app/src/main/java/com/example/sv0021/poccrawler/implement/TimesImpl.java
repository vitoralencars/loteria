package com.example.sv0021.poccrawler.implement;

import android.support.v7.widget.RecyclerView;

import com.example.sv0021.poccrawler.presenter.TimesPresenter;
import com.example.sv0021.poccrawler.retrofit.RetrofitBuilder;
import com.example.sv0021.poccrawler.retrofit.Service;
import com.example.sv0021.poccrawler.util.ProgressBarControl;
import com.example.sv0021.poccrawler.view.activity.TimesActivity;
import com.example.sv0021.poccrawler.view.adapter.TimesAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimesImpl implements TimesPresenter {
    @Override
    public void onListarTimes(TimesActivity context, RecyclerView rvTimes) {
        ProgressBarControl.mostrarProgressBar(context);
        Service service = new RetrofitBuilder().getService();
        service.getTimes().enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                ProgressBarControl.esconderProgressBar(context);
                if(response.body() != null){
                    List<String> times = response.body();
                    context.setTimes(times);
                    rvTimes.setAdapter(new TimesAdapter(context, times));
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                ProgressBarControl.esconderProgressBar(context);
            }
        });
    }
}
