package com.example.sv0021.poccrawler.model.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class LoteriaComum extends BaseLoteriaResponse implements Serializable {

    @SerializedName("Dezenas")
    private List<Integer> dezenas;
    @SerializedName("Ganhadores")
    private List<Integer> ganhadores;
    @SerializedName("Rateio")
    private List<Double> rateio;

    public List<Integer> getDezenas() {
        return dezenas;
    }

    public List<Integer> getGanhadores() {
        return ganhadores;
    }

    public List<Double> getRateio() {
        return rateio;
    }
}
