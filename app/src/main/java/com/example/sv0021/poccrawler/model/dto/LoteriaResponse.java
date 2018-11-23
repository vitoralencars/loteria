package com.example.sv0021.poccrawler.model.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class LoteriaResponse implements Serializable {

    @SerializedName("CodigoLoteria")
    private int codigoLoteria;
    @SerializedName("NomeLoteria")
    private String nomeLoteria;
    @SerializedName("CorPadrao")
    private String corPadrao;
    @SerializedName("QtdMinimaDezenasAposta")
    private int qtdMinimaDezenasAposta;
    @SerializedName("QtdMaximaDezenasAposta")
    private int qtdMaximaDezenasAposta;
    @SerializedName("QtdDezenasTotal")
    private int qtdDezenasTotal;
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
    private double valorAcumulado;
    @SerializedName("EstimativaPremio")
    private double estimativaPremio;
    @SerializedName("ProximoSorteio")
    private Date proximoSorteio;

    public int getCodigoLoteria() {
        return codigoLoteria;
    }

    public String getNomeLoteria() {
        return nomeLoteria;
    }

    public String getCorPadrao() {
        return corPadrao;
    }

    public int getQtdMinimaDezenasAposta() {
        return qtdMinimaDezenasAposta;
    }

    public int getQtdMaximaDezenasAposta() {
        return qtdMaximaDezenasAposta;
    }

    public int getQtdDezenasTotal() {
        return qtdDezenasTotal;
    }

    public int getConcurso() {
        return concurso;
    }

    public Date getDataSorteio() {
        return dataSorteio;
    }

    public List<Integer> getDezenas() {
        return dezenas;
    }

    public List<Integer> getGanhadores() {
        return ganhadores;
    }

    public List<Double> getRateio() {
        return rateio;
    }

    public double getValorAcumulado() {
        return valorAcumulado;
    }

    public double getEstimativaPremio() {
        return estimativaPremio;
    }

    public Date getProximoSorteio() {
        return proximoSorteio;
    }

}
