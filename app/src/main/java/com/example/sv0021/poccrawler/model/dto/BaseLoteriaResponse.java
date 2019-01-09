package com.example.sv0021.poccrawler.model.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class BaseLoteriaResponse implements Serializable {

    @SerializedName("CodigoLoteria")
    private int codigoLoteria;
    @SerializedName("NomeLoteria")
    private String nomeLoteria;
    @SerializedName("CorPadrao")
    private String corPadrao;
    @SerializedName("CorSecundaria")
    private String corSecundaria;
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
    @SerializedName("Cidades")
    private List<String> cidades;
    @SerializedName("Estados")
    private List<String> estados;
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

    public String getCorSecundaria() {
        return corSecundaria;
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

    public List<String> getCidades() {
        return cidades;
    }

    public List<String> getEstados() {
        return estados;
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
