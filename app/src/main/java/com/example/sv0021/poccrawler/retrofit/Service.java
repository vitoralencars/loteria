package com.example.sv0021.poccrawler.retrofit;

import com.example.sv0021.poccrawler.model.dto.BaseLoteriaResponse;
import com.example.sv0021.poccrawler.model.dto.LoteriasResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Service {

    @GET("ultimosresultados")
    Call<LoteriasResponse> getUltimosResultados();

    @GET("loteria/{idloteria}/{concurso}")
    Call<BaseLoteriaResponse> getResultadoConcurso(@Path("idloteria")int idLoteria, @Path("concurso")int concurso);

    @GET("timemania/timescoracao")
    Call<List<String>> getTimes();

}
