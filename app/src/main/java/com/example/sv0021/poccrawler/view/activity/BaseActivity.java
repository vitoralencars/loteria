package com.example.sv0021.poccrawler.view.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sv0021.poccrawler.R;

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setLayout(@LayoutRes int idLayout){
        setContentView(idLayout);
    }

    public void exibirToast(Context context, String mensagem){
        Toast.makeText(context, mensagem, Toast.LENGTH_SHORT).show();
    }

    public ProgressBar getProgressBar(){
        return findViewById(R.id.progressBar);
    }

    public void loadActivity(Intent intent){
        startActivity(intent);
    }

    public void loadActivity(Class destino){
        startActivity(new Intent(this, destino));
    }

}
