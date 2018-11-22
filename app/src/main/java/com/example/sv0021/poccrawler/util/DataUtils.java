package com.example.sv0021.poccrawler.util;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtils {

    @SuppressLint("SimpleDateFormat")
    public static String getDataFormatada(Date data){
        return new SimpleDateFormat("dd/MM/yyyy").format(data);
    }

}
