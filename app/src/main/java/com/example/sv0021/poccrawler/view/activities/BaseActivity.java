package com.example.sv0021.poccrawler.view.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setLayout(int idLayout){
        setContentView(idLayout);
    }

    public void exibirToast(Context context, String mensagem){
        Toast.makeText(context, mensagem, Toast.LENGTH_SHORT).show();
    }

}
