package com.example.sv0021.poccrawler.retrofit;

import com.example.sv0021.poccrawler.util.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    private Retrofit retrofit;
    private Service service;

    public RetrofitBuilder(){
        retrofit = initRetrofit(Constants.BASE_API_URL);
        service = initService(retrofit);
    }

    private Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    private Retrofit initRetrofit(String baseURL){
        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    private Service initService(Retrofit retrofit){
        return retrofit.create(Service.class);
    }

    public Service getService(){
        return this.service;
    }

}
