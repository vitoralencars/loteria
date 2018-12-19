package com.example.sv0021.poccrawler.model.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class LoteriasResponse implements Serializable {

    @SerializedName("LoteriasComuns")
    private List<LoteriaComum> loteriasComuns;
    @SerializedName("DuplaSena")
    private DuplaSena duplaSena;

    public List<LoteriaComum> getLoteriasComuns() {
        return loteriasComuns;
    }

    public DuplaSena getDuplaSena() {
        return duplaSena;
    }
}
