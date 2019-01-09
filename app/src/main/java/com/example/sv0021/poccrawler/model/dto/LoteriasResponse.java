package com.example.sv0021.poccrawler.model.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoteriasResponse implements Serializable {

    @SerializedName("Megasena")
    private Megasena megasena;
    @SerializedName("Lotofacil")
    private Lotofacil lotofacil;
    @SerializedName("Quina")
    private Quina quina;
    @SerializedName("Lotomania")
    private Lotomania lotomania;
    @SerializedName("Timemania")
    private Timemania timemania;
    @SerializedName("Duplasena")
    private Duplasena duplasena;

    public Megasena getMegasena() {
        return megasena;
    }

    public Lotofacil getLotofacil() {
        return lotofacil;
    }

    public Quina getQuina() {
        return quina;
    }

    public Lotomania getLotomania() {
        return lotomania;
    }

    public Timemania getTimemania() {
        return timemania;
    }

    public Duplasena getDuplasena() {
        return duplasena;
    }
}
