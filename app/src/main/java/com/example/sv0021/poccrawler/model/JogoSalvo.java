package com.example.sv0021.poccrawler.model;

import java.io.Serializable;
import java.util.List;

public class JogoSalvo implements Serializable {

    private List<Integer> dezenas;

    public JogoSalvo(List<Integer> dezenas) {
        this.dezenas = dezenas;
    }

    public List<Integer> getDezenas() {
        return dezenas;
    }
}
