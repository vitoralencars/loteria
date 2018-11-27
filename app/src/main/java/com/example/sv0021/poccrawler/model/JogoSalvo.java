package com.example.sv0021.poccrawler.model;

import java.io.Serializable;
import java.util.List;

public class JogoSalvo implements Serializable {

    private int concurso;
    private List<Integer> dezenas;

    public JogoSalvo(int concurso, List<Integer> dezenas) {
        this.concurso = concurso;
        this.dezenas = dezenas;
    }

    public int getConcurso() {
        return concurso;
    }

    public List<Integer> getDezenas() {
        return dezenas;
    }
}
