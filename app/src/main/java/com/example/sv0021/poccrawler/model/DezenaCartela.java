package com.example.sv0021.poccrawler.model;

import java.io.Serializable;

public class DezenaCartela implements Serializable {

    private int dezena;
    private boolean selecionado;
    private String corBackground;

    public int getDezena() {
        return dezena;
    }

    public void setDezena(int dezena) {
        this.dezena = dezena;
    }

    public boolean isSelecionado() {
        return selecionado;
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
    }

    public String getCorBackground() {
        return corBackground;
    }

    public void setCorBackground(String corBackGround) {
        this.corBackground = corBackGround;
    }
}
