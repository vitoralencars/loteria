package com.example.sv0021.poccrawler.model;

import java.io.Serializable;
import java.util.List;

public class JogoSalvo implements Serializable {

    private Long idJogo;
    private List<Integer> dezenas;

    public JogoSalvo(Long idJogo, List<Integer> dezenas) {
        this.idJogo = idJogo;
        this.dezenas = dezenas;
    }

    public Long getIdJogo() {
        return idJogo;
    }

    public List<Integer> getDezenas() {
        return dezenas;
    }
}
