package com.example.sv0021.poccrawler.model;

import java.io.Serializable;

public class ResultadoLoteria implements Serializable {

    private int qtdDezenas;
    private int qtdGanhadores;
    private double valorTotal;
    private double valorRateio;

    public ResultadoLoteria(int qtdDezenas, int qtdGanhadores, double valorRateio, double valorTotal) {
        this.qtdDezenas = qtdDezenas;
        this.qtdGanhadores = qtdGanhadores;
        this.valorRateio = valorRateio;
        this.valorTotal = valorTotal;
    }

    public int getQtdDezenas() {
        return qtdDezenas;
    }

    public int getQtdGanhadores() {
        return qtdGanhadores;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public double getValorRateio() {
        return valorRateio;
    }
}
