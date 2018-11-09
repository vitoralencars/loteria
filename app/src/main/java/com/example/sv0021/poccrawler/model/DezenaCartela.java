package com.example.sv0021.poccrawler.model;

import android.support.annotation.ColorRes;

import java.io.Serializable;

public class DezenaCartela implements Serializable {

    private int dezena;
    private boolean selecionado;
    private @ColorRes  int corBackGround;

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

    public int getCorBackGround() {
        return corBackGround;
    }

    public void setCorBackGround(int corBackGround) {
        this.corBackGround = corBackGround;
    }
}
