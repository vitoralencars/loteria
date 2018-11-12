package com.example.sv0021.poccrawler.retrofit;

import com.example.sv0021.poccrawler.model.dto.LoteriaResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Service {

    @GET("{loteria}")
    Call<LoteriaResponse> getUserRepositories(@Path("loteria") String loteria);

}
