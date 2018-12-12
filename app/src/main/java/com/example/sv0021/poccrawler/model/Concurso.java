package com.example.sv0021.poccrawler.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Concurso implements Serializable {

    private int numConcurso;
    private List<JogoSalvo> jogosSalvos;
    private Date data;
    private int[] resultadoSorteio;

    public Concurso(int numConcurso, List<JogoSalvo> jogosSalvos, Date data) {
        this.numConcurso = numConcurso;
        this.jogosSalvos = jogosSalvos;
        this.data = data;
    }

    public int getNumConcurso() {
        return numConcurso;
    }

    public List<JogoSalvo> getJogosSalvos() {
        return jogosSalvos;
    }

    public Date getData() {
        return data;
    }

    public int[] getResultadoSorteio() {
        return resultadoSorteio;
    }

    public void setResultadoSorteio(int[] resultadoSorteio) {
        this.resultadoSorteio = resultadoSorteio;
    }
}
