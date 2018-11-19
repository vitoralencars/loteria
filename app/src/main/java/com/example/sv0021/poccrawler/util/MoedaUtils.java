package com.example.sv0021.poccrawler.util;

import java.text.NumberFormat;

public class MoedaUtils {

    public static String getValorMoedaReal(double valor){
        return NumberFormat.getCurrencyInstance().format(valor).replace("R$", "R$ ");
    }

}
