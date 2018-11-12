package com.example.sv0021.poccrawler.model.dto;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class LoteriaResponse implements Serializable {

    private String nomeLoteria;
    private @ColorRes int corPadrao;
    private Drawable icLoteria;
    @SerializedName("numero")
    private int numero;
    @SerializedName("data")
    private Date data;
    @SerializedName("sorteio")
    private List<Integer> sorteio;
    @SerializedName("ganhadores")
    private List<Integer> ganhadores;
    @SerializedName("rateio")
    private List<Double> rateio;
    @SerializedName("acumulado")
    private String acumulado;
    @SerializedName("valor_acumulado")
    private double valorAcumulado;
    @SerializedName("proximo_estimativa")
    private double proximoEstimativa;
    @SerializedName("proximo_data")
    private Date proximoData;

    public String getNomeLoteria() {
        return nomeLoteria;
    }

    public void setNomeLoteria(String nomeLoteria) {
        this.nomeLoteria = nomeLoteria;
    }

    public @ColorRes int getCorPadrao() {
        return corPadrao;
    }

    public void setCorPadrao(int corPadrao) {
        this.corPadrao = corPadrao;
    }

    public Drawable getIcLoteria() {
        return icLoteria;
    }

    public void setIcLoteria(Drawable icLoteria) {
        this.icLoteria = icLoteria;
    }

    public int getNumero() {
        return numero;
    }

    public Date getData() {
        return data;
    }

    public List<Integer> getSorteio() {
        return sorteio;
    }

    public List<Integer> getGanhadores() {
        return ganhadores;
    }

    public List<Double> getRateio() {
        return rateio;
    }

    public String getAcumulado() {
        return acumulado;
    }

    public double getValorAcumulado() {
        return valorAcumulado;
    }

    public double getProximoEstimativa() {
        return proximoEstimativa;
    }

    public Date getProximoData() {
        return proximoData;
    }
}
