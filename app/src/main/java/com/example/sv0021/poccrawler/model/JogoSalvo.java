package com.example.sv0021.poccrawler.model;

import java.io.Serializable;
import java.util.List;

public class JogoSalvo implements Serializable {

    private Long idJogo;
    private List<Integer> dezenas;
    private String timeCoracao;

    public JogoSalvo(Long idJogo, List<Integer> dezenas) {
        this.idJogo = idJogo;
        this.dezenas = dezenas;
    }

    public JogoSalvo(Long idJogo, List<Integer> dezenas, String timeCoracao) {
        this.idJogo = idJogo;
        this.dezenas = dezenas;
        this.timeCoracao = timeCoracao;
    }

    public Long getIdJogo() {
        return idJogo;
    }

    public List<Integer> getDezenas() {
        return dezenas;
    }

    public String getTimeCoracao(){
        return timeCoracao;
    }
}
