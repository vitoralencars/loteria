package com.example.sv0021.poccrawler.model;

import android.support.annotation.ColorRes;

import java.util.ArrayList;
import java.util.List;

public class Loteria {

    private int qtdDezenasCartela, qtdMinimaDezenasSelecionadas, qtdMaximaDezenasSelecionadas,
            qtdDesejadaDezenasSelecionadas;
    private int[] dezenasDisponiveis;
    private @ColorRes int corPrincipal;
    private List<DezenaCartela> dezenasCartela = new ArrayList<>();
    private List<DezenaCartela> dezenasSelecionadas = new ArrayList<>();

    public int getQtdDezenasCartela() {
        return qtdDezenasCartela;
    }

    public void setQtdDezenasCartela(int qtdDezenasCartela) {
        this.qtdDezenasCartela = qtdDezenasCartela;
    }

    public int getQtdMinimaDezenasSelecionadas() {
        return qtdMinimaDezenasSelecionadas;
    }

    public void setQtdMinimaDezenasSelecionadas(int qtdMinimaDezenasSelecionadas) {
        this.qtdMinimaDezenasSelecionadas = qtdMinimaDezenasSelecionadas;
    }

    public int getQtdMaximaDezenasSelecionadas() {
        return qtdMaximaDezenasSelecionadas;
    }

    public void setQtdMaximaDezenasSelecionadas(int qtdMaximaDezenasSelecionadas) {
        this.qtdMaximaDezenasSelecionadas = qtdMaximaDezenasSelecionadas;
    }

    public int getQtdDesejadaDezenasSelecionadas() {
        return qtdDesejadaDezenasSelecionadas;
    }

    public void setQtdDesejadaDezenasSelecionadas(int qtdDesejadaDezenasSelecionadas) {
        this.qtdDesejadaDezenasSelecionadas = qtdDesejadaDezenasSelecionadas;
    }

    public int getCorPrincipal() {
        return corPrincipal;
    }

    public void setCorPrincipal(int corPrincipal) {
        this.corPrincipal = corPrincipal;
    }

    public int[] getDezenasDisponiveis() {
        return dezenasDisponiveis;
    }

    public void setDezenasDisponiveis(int[] dezenasDisponiveis) {
        this.dezenasDisponiveis = dezenasDisponiveis;
    }

    public List<DezenaCartela> getDezenasCartela() {
        return dezenasCartela;
    }

    public void setDezenasCartela(List<DezenaCartela> dezenasCartela) {
        this.dezenasCartela = dezenasCartela;
    }

    public List<DezenaCartela> getDezenasSelecionadas() {
        return dezenasSelecionadas;
    }

    public void setDezenasSelecionadas(List<DezenaCartela> dezenasSelecionadas) {
        this.dezenasSelecionadas = dezenasSelecionadas;
    }
}
