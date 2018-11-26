package com.example.sv0021.poccrawler.view.activity;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sv0021.poccrawler.R;
import com.example.sv0021.poccrawler.implement.CartelaImpl;
import com.example.sv0021.poccrawler.model.DezenaCartela;
import com.example.sv0021.poccrawler.model.Cartela;
import com.example.sv0021.poccrawler.util.recyclerview.DezenaClickListener;
import com.example.sv0021.poccrawler.view.adapter.CartelaAdapter;

import java.util.List;

@SuppressLint("Registered")
public class BaseCartelaActivity extends BaseActivity {

    private CartelaImpl cartelaImpl = new CartelaImpl();

    private TextView tvDezenasSelecionadas;
    private RecyclerView rvDezenas;
    private Spinner spQtdDezenas;
    private Button btnCompletar, btnLimpar;
    private CartelaAdapter adapter;

    private Cartela cartela;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    /*public void setLayout(int idLayout){
        setContentView(idLayout);
        initView();
        initEvents();
    }

    private void initView(){
        tvDezenasSelecionadas = findViewById(R.id.tvDezenasSelecionadas);
        rvDezenas = findViewById(R.id.rvDezenas);
        spQtdDezenas = findViewById(R.id.spQtdDezenas);
        btnCompletar = findViewById(R.id.btnCompletarCartela);
        btnLimpar = findViewById(R.id.btnLimparCartela);
    }

    private void initEvents(){
        btnCompletar.setOnClickListener(view -> cartelaImpl.onCompletarCartela(
                this,
                cartela
        ));

        btnLimpar.setOnClickListener(view -> limparCartela());

        spQtdDezenas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                cartelaImpl.onSpinnerSelectionListener(
                        BaseCartelaActivity.this,
                        position,
                        cartela
                );
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void setCartela(Cartela cartela){
        this.cartela = cartela;
        initSpinner();
    }

    private void initSpinner(){
        cartelaImpl.onInitSpinnerQtdDezenas(
                this,
                spQtdDezenas,
                cartela
        );
    }

    public void montarCartela(){
        cartelaImpl.onMontarCartela(
                this,
                cartela,
                rvDezenas
        );
    }

    public void limparCartela(){
        cartelaImpl.onLimparCartela(
                this,
                cartela
        );
    }

    public void atualizarTextoDezenasSelecionadas(List<DezenaCartela> dezenasSelecionados){
        cartelaImpl.onExibirDezenasSelecionadas(tvDezenasSelecionadas, dezenasSelecionados);
    }

    public void setAdapter(CartelaAdapter adapter){
        this.adapter = adapter;
    }

    public CartelaAdapter getAdapter(){
        return adapter;
    }

    @Override
    public void onItemClick(int position, TextView tvDezena) {
        cartelaImpl.onDezenaClick(
                this,
                position,
                tvDezena,
                cartela
        );
    }*/
}