package com.example.sv0021.poccrawler.model;

import java.io.Serializable;
import java.util.List;

public class Concurso implements Serializable {

    private int numConcurso;
    private List<JogoSalvo> jogosSalvos;

    public Concurso(int numConcurso, List<JogoSalvo> jogosSalvos) {
        this.numConcurso = numConcurso;
        this.jogosSalvos = jogosSalvos;
    }

    public int getNumConcurso() {
        return numConcurso;
    }

    public List<JogoSalvo> getJogosSalvos() {
        return jogosSalvos;
    }
}
