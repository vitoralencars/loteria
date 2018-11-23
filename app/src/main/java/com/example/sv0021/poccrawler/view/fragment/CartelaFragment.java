package com.example.sv0021.poccrawler.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sv0021.poccrawler.R;
import com.example.sv0021.poccrawler.implement.CartelaImpl;
import com.example.sv0021.poccrawler.model.Cartela;
import com.example.sv0021.poccrawler.model.DezenaCartela;
import com.example.sv0021.poccrawler.model.dto.LoteriaResponse;
import com.example.sv0021.poccrawler.util.recyclerview.DezenaClickListener;
import com.example.sv0021.poccrawler.view.activity.LoteriaActivity;
import com.example.sv0021.poccrawler.view.adapter.CartelaAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartelaFragment extends Fragment implements DezenaClickListener {

    private CartelaImpl cartelaImpl = new CartelaImpl();

    private Cartela cartela;

    private TextView tvDezenasSelecionadas;
    private RecyclerView rvDezenas;
    private Spinner spQtdDezenas;
    private Button btnCompletar, btnLimpar;

    private CartelaAdapter adapter;
    private LoteriaActivity activity;

    public CartelaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cartela, container, false);
        activity = (LoteriaActivity)getActivity();

        initCartela();
        initView(view);
        montarCartela();
        initSpinner();
        initEvents();

        return view;
    }

    private void initView(View v){
        tvDezenasSelecionadas = v.findViewById(R.id.tvDezenasSelecionadas);
        rvDezenas = v.findViewById(R.id.rvDezenas);
        spQtdDezenas = v.findViewById(R.id.spQtdDezenas);
        btnCompletar = v.findViewById(R.id.btnCompletarCartela);
        btnLimpar = v.findViewById(R.id.btnLimparCartela);
    }

    private void initEvents(){
        btnCompletar.setOnClickListener(view -> cartelaImpl.onCompletarCartela(
                activity,
                CartelaFragment.this,
                cartela
        ));

        btnLimpar.setOnClickListener(view -> limparCartela());

        spQtdDezenas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                cartelaImpl.onSpinnerSelectionListener(
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
        LoteriaResponse loteria = activity.getLoteria();

        cartela = new Cartela();
        cartela.setQtdDesejadaDezenasSelecionadas(loteria.getQtdMinimaDezenasAposta());
        cartela.setQtdDezenasCartela(loteria.getQtdDezenasTotal());
        cartela.setQtdMaximaDezenasSelecionadas(loteria.getQtdMaximaDezenasAposta());
        cartela.setQtdMinimaDezenasSelecionadas(loteria.getQtdMinimaDezenasAposta());
        cartela.setCorPadrao(loteria.getCorPadrao());
    }

    private void initSpinner(){
        cartelaImpl.onInitSpinnerQtdDezenas(
                activity,
                spQtdDezenas,
                cartela
        );
    }

    public void montarCartela(){
        cartelaImpl.onMontarCartela(
                activity,
                this,
                cartela,
                rvDezenas
        );
    }

    public void limparCartela(){
        cartelaImpl.onLimparCartela(
                activity,
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
                activity,
                CartelaFragment.this,
                position,
                tvDezena,
                cartela
        );
    }
}
