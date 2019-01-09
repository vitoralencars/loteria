package com.example.sv0021.poccrawler.model.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Duplasena extends BaseLoteriaResponse implements Serializable {

    @SerializedName("DezenasPrimeiroSorteio")
    private List<Integer> dezenasPrimeiroSorteio;
    @SerializedName("DezenasSegundoSorteio")
    private List<Integer> dezenasSegundoSorteio;
    @SerializedName("GanhadoresPrimeiroSorteio")
    private List<Integer> ganhadoresPrimeiroSorteio;
    @SerializedName("GanhadoresSegundoSorteio")
    private List<Integer> ganhadoresSegundoSorteio;
    @SerializedName("RateioPrimeiroSorteio")
    private List<Double> rateioPrimeiroSorteio;
    @SerializedName("RateioSegundoSorteio")
    private List<Double> rateioSegundoSorteio;

    public List<Integer> getDezenasPrimeiroSorteio() {
        return dezenasPrimeiroSorteio;
    }

    public List<Integer> getDezenasSegundoSorteio() {
        return dezenasSegundoSorteio;
    }

    public List<Integer> getGanhadoresPrimeiroSorteio() {
        return ganhadoresPrimeiroSorteio;
    }

    public List<Integer> getGanhadoresSegundoSorteio() {
        return ganhadoresSegundoSorteio;
    }

    public List<Double> getRateioPrimeiroSorteio() {
        return rateioPrimeiroSorteio;
    }

    public List<Double> getRateioSegundoSorteio() {
        return rateioSegundoSorteio;
    }
}
