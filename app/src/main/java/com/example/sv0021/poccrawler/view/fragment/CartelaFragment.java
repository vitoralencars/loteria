package com.example.sv0021.poccrawler.view.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sv0021.poccrawler.R;
import com.example.sv0021.poccrawler.implement.CartelaImpl;
import com.example.sv0021.poccrawler.model.Cartela;
import com.example.sv0021.poccrawler.model.DezenaCartela;
import com.example.sv0021.poccrawler.model.dto.BaseLoteriaComum;
import com.example.sv0021.poccrawler.util.recyclerview.DezenaClickListener;
import com.example.sv0021.poccrawler.view.activity.LoteriaActivity;
import com.example.sv0021.poccrawler.view.adapter.CartelaAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartelaFragment extends Fragment implements DezenaClickListener {

    private CartelaImpl impl = new CartelaImpl();

    private Cartela cartela;

    private TextView tvDezenasSelecionadas;
    private RecyclerView rvDezenas;
    private Spinner spQtdDezenas;
    private Button btnCompletar, btnSalvar, btnLimpar;
    private TextInputLayout tilTime;
    private EditText etTimeCoracao;

    private CartelaAdapter adapter;
    private LoteriaActivity activity;

    public CartelaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cartela, container, false);
        activity = (LoteriaActivity)getActivity();

        initCartela();
        initView(view);
        montarCartela();
        initSpinner();
        configurarBotoes();
        initEvents();
        configurarTimeCoracao();
        verificarEdicaoJogo();

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        activity.desativarEdicao();
    }

    private void initView(View v){
        tvDezenasSelecionadas = v.findViewById(R.id.tvDezenasSelecionadas);
        rvDezenas = v.findViewById(R.id.rvDezenas);
        spQtdDezenas = v.findViewById(R.id.spQtdDezenas);
        btnCompletar = v.findViewById(R.id.btnCompletarCartela);
        btnSalvar = v.findViewById(R.id.btnSalvarCartela);
        btnLimpar = v.findViewById(R.id.btnLimparCartela);
        tilTime = v.findViewById(R.id.tilTime);
        etTimeCoracao = v.findViewById(R.id.etTimeCoracao);
    }

    private void initEvents(){
        btnCompletar.setBackgroundColor(Color.parseColor(activity.getLoteria().getCorPadrao()));
        btnSalvar.setBackgroundColor(Color.parseColor(activity.getLoteria().getCorPadrao()));
        btnLimpar.setBackgroundColor(Color.parseColor(activity.getLoteria().getCorPadrao()));

        btnCompletar.setOnClickListener(view -> impl.onCompletarCartela(
                activity,
                CartelaFragment.this,
                cartela
        ));

        btnSalvar.setOnClickListener(view -> impl.onSalvarJogo(
                activity,
                CartelaFragment.this,
                cartela
        ));

        btnLimpar.setOnClickListener(view -> limparCartela());

        spQtdDezenas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                impl.onSpinnerSelectionListener(
                        activity,
                        CartelaFragment.this,
                        position,
                        cartela
                );
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initCartela(){
        BaseLoteriaComum loteria = activity.getLoteria();

        cartela = new Cartela();
        cartela.setQtdDesejadaDezenasSelecionadas(loteria.getQtdMinimaDezenasAposta());
        cartela.setQtdDezenasCartela(loteria.getQtdDezenasTotal());
        cartela.setQtdMaximaDezenasSelecionadas(loteria.getQtdMaximaDezenasAposta());
        cartela.setQtdMinimaDezenasSelecionadas(loteria.getQtdMinimaDezenasAposta());
    }

    private void initSpinner(){
        impl.onInitSpinnerQtdDezenas(
                activity,
                spQtdDezenas,
                cartela
        );
    }

    public void montarCartela(){
        impl.onMontarCartela(
                activity,
                this,
                cartela,
                rvDezenas
        );
    }

    public void limparCartela(){
        impl.onLimparCartela(
                activity,
                this,
                cartela,
                etTimeCoracao
        );
    }

    public void atualizarTextoDezenasSelecionadas(List<DezenaCartela> dezenasSelecionadas){
        impl.onExibirDezenasSelecionadas(
                tvDezenasSelecionadas,
                dezenasSelecionadas
        );
    }

    public void setTimeCoracaoEdicao(String timeCoracao){
        impl.onSetTimeCoracaoEdicao(etTimeCoracao, timeCoracao);
    }

    private void configurarBotoes(){
        impl.onConfigurarBotoes(
                activity,
                this,
                cartela,
                btnCompletar,
                btnSalvar,
                btnLimpar
        );
    }

    private void configurarTimeCoracao(){
        impl.onConfigurarTimeCoracao(
                activity,
                this,
                tilTime,
                etTimeCoracao
        );
    }

    private void verificarEdicaoJogo(){
        if(activity.getIdJogoEdicao() != null){
            impl.onMontarCartelaEdicao(
                    activity,
                    spQtdDezenas,
                    this,
                    cartela
            );
        }
    }

    public void setAdapter(CartelaAdapter adapter){
        this.adapter = adapter;
    }

    public CartelaAdapter getAdapter(){
        return adapter;
    }

    @Override
    public void onItemClick(int position, TextView tvDezena) {
        impl.onDezenaClick(
                activity,
                CartelaFragment.this,
                position,
                tvDezena,
                cartela
        );
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        impl.onTimeCoracaoResult(
                etTimeCoracao,
                cartela,
                requestCode,
                resultCode,
                data
        );
    }
}
