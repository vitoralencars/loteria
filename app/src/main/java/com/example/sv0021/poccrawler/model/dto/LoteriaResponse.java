package com.example.sv0021.poccrawler.model.dto;

import android.graphics.drawable.Drawable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class LoteriaResponse implements Serializable {

    private Drawable icLoteria;

    @SerializedName("CodigoLoteria")
    private int codigoLoteria;
    @SerializedName("NomeLoteria")
    private String nomeLoteria;
    @SerializedName("CorPadrao")
    private String corPadrao;
    @SerializedName("Concurso")
    private int concurso;
    @SerializedName("DataSorteio")
    private Date dataSorteio;
    @SerializedName("Dezenas")
    private List<Integer> dezenas;
    @SerializedName("Ganhadores")
    private List<Integer> ganhadores;
    @SerializedName("Rateio")
    private List<Double> rateio;
    @SerializedName("ValorAcumulado")
    private String valorAcumulado;
    @SerializedName("EstimativaPremio")
    private double estimativaPremio;

    public Drawable getIcLoteria() {
        return icLoteria;
    }

    public void setIcLoteria(Drawable icLoteria) {
        this.icLoteria = icLoteria;
    }

    public int getCodigoLoteria() {
        return codigoLoteria;
    }

    public String getNomeLoteria() {
        return nomeLoteria;
    }

    public void setNomeLoteria(String nomeLoteria) {
        this.nomeLoteria = nomeLoteria;
    }

    public String getCorPadrao() {
        return corPadrao;
    }

    public void setCorPadrao(String corPadrao) {
        this.corPadrao = corPadrao;
    }

    public int getConcurso() {
        return concurso;
    }

    public void setConcurso(int concurso) {
        this.concurso = concurso;
    }

    public Date getDataSorteio() {
        return dataSorteio;
    }

    public void setDataSorteio(Date dataSorteio) {
        this.dataSorteio = dataSorteio;
    }

    public List<Integer> getDezenas() {
        return dezenas;
    }

    public void setDezenas(List<Integer> dezenas) {
        this.dezenas = dezenas;
    }

    public List<Integer> getGanhadores() {
        return ganhadores;
    }

    public void setGanhadores(List<Integer> ganhadores) {
        this.ganhadores = ganhadores;
    }

    public List<Double> getRateio() {
        return rateio;
    }

    public void setRateio(List<Double> rateio) {
        this.rateio = rateio;
    }

    public String getValorAcumulado() {
        return valorAcumulado;
    }

    public void setValorAcumulado(String valorAcumulado) {
        this.valorAcumulado = valorAcumulado;
    }

    public double getEstimativaPremio() {
        return estimativaPremio;
    }

    public void setEstimativaPremio(double estimativaPremio) {
        this.estimativaPremio = estimativaPremio;
    }

}
