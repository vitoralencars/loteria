package com.example.sv0021.poccrawler.view.activities;

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
import com.example.sv0021.poccrawler.implement.BaseCartelaImpl;
import com.example.sv0021.poccrawler.model.DezenaCartela;
import com.example.sv0021.poccrawler.model.Loteria;
import com.example.sv0021.poccrawler.util.recyclerview.DezenaClickListener;
import com.example.sv0021.poccrawler.view.adapter.CartelaAdapter;

import java.util.List;

@SuppressLint("Registered")
public class BaseCartelaActivity extends BaseActivity implements DezenaClickListener {

    private BaseCartelaImpl baseCartelaImpl = new BaseCartelaImpl();

    private TextView tvDezenasSelecionadas;
    private RecyclerView rvDezenas;
    private Spinner spQtdDezenas;
    private Button btnCompletar, btnLimpar;
    private CartelaAdapter adapter;

    private Loteria loteria;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void setLayout(int idLayout){
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
        btnCompletar.setOnClickListener(view -> baseCartelaImpl.onCompletarCartela(
                this,
                loteria
        ));

        btnLimpar.setOnClickListener(view -> limparCartela());

        spQtdDezenas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                baseCartelaImpl.onSpinnerSelectionListener(
                        BaseCartelaActivity.this,
                        position,
                        loteria
                );
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void setLoteria(Loteria loteria){
        this.loteria = loteria;
        initSpinner();
    }

    private void initSpinner(){
        baseCartelaImpl.onInitSpinnerQtdDezenas(
                this,
                spQtdDezenas,
                loteria
        );
    }

    public void montarCartela(){
        baseCartelaImpl.onMontarCartela(
                this,
                loteria,
                rvDezenas
        );
    }

    public void limparCartela(){
        baseCartelaImpl.onLimparCartela(
                this,
                loteria
        );
    }

    public void atualizarTextoDezenasSelecionadas(List<DezenaCartela> dezenasSelecionados){
        baseCartelaImpl.onExibirDezenasSelecionadas(tvDezenasSelecionadas, dezenasSelecionados);
    }

    public void setAdapter(CartelaAdapter adapter){
        this.adapter = adapter;
    }

    public CartelaAdapter getAdapter(){
        return adapter;
    }

    @Override
    public void onItemClick(int position, TextView tvDezena) {
        baseCartelaImpl.onDezenaClick(
                this,
                position,
                tvDezena,
                loteria
        );
    }
}
