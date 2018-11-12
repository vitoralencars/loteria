package com.example.sv0021.poccrawler.implement;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sv0021.poccrawler.R;
import com.example.sv0021.poccrawler.model.DezenaCartela;
import com.example.sv0021.poccrawler.model.Loteria;
import com.example.sv0021.poccrawler.presenter.BaseCartelaPresenter;
import com.example.sv0021.poccrawler.view.activities.BaseCartelaActivity;
import com.example.sv0021.poccrawler.view.adapter.CartelaAdapter;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BaseCartelaImpl implements BaseCartelaPresenter {

    @Override
    public void onMontarCartela(BaseCartelaActivity context, Loteria loteria, RecyclerView rvDezenas) {
        List<DezenaCartela> dezenasCartela = new ArrayList<>();
        int[] dezenasDisponiveis = new int[loteria.getQtdDezenasCartela()];

        for(int i = 0; i < dezenasDisponiveis.length; i++){
            dezenasDisponiveis[i] = i + 1;

            DezenaCartela dezena = new DezenaCartela();
            dezena.setDezena(i + 1);
            dezena.setSelecionado(false);
            dezena.setCorBackGround(loteria.getCorPrincipal());
            dezenasCartela.add(dezena);
        }

        loteria.setDezenasDisponiveis(dezenasDisponiveis);
        loteria.setDezenasCartela(dezenasCartela);

        context.setAdapter(new CartelaAdapter(context, loteria.getDezenasCartela()));
        rvDezenas.setAdapter(context.getAdapter());
    }

    @Override
    public void onCompletarCartela(BaseCartelaActivity context, Loteria loteria) {
        Random random = new Random();
        int qtdDezenas = loteria.getQtdDesejadaDezenasSelecionadas() - loteria.getDezenasSelecionadas().size();

        if(qtdDezenas == 0){
            context.exibirToast(context, "Você já selecionou o número máximo de dezenas para esta cartela");
        }else {
            List<DezenaCartela> dezenasSelecionadas = loteria.getDezenasSelecionadas();
            for (int i = 0; i < qtdDezenas; i++) {
                int index = random.nextInt(loteria.getDezenasDisponiveis().length);
                dezenasSelecionadas.add(loteria.getDezenasCartela().get(loteria.getDezenasDisponiveis()[index] - 1));
                loteria.setDezenasDisponiveis(ArrayUtils.remove(loteria.getDezenasDisponiveis(), index));
            }

            loteria.setDezenasSelecionadas(dezenasSelecionadas);
            loteria.setDezenasDisponiveis(loteria.getDezenasDisponiveis());

            context.atualizarTextoDezenasSelecionadas(loteria.getDezenasSelecionadas());

            for (DezenaCartela dezena : loteria.getDezenasCartela()) {
                if (dezenasSelecionadas.contains(dezena)) {
                    dezena.setSelecionado(true);
                } else {
                    dezena.setSelecionado(false);
                }
            }
            context.getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void onLimparCartela(BaseCartelaActivity context, Loteria loteria) {
        int[] dezenasDisponiveis = new int[loteria.getQtdDezenasCartela()];
        for(int i = 0; i < loteria.getQtdDezenasCartela(); i++){
            loteria.getDezenasCartela().get(i).setSelecionado(false);
            dezenasDisponiveis[i] = i + 1;
        }

        loteria.setDezenasDisponiveis(dezenasDisponiveis);
        loteria.getDezenasSelecionadas().clear();

        context.atualizarTextoDezenasSelecionadas(loteria.getDezenasSelecionadas());
        context.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onDezenaClick(BaseCartelaActivity context, int index, TextView tvDezena, Loteria loteria) {
        DezenaCartela dezena = loteria.getDezenasCartela().get(index);
        dezena.setSelecionado(!dezena.isSelecionado());

        loteria.getDezenasCartela().set(index, dezena);

        int indexArray = 0;

        int[] dezenasDisponiveis = loteria.getDezenasDisponiveis();
        for(int i = 0; i < dezenasDisponiveis.length; i++){
            if(dezenasDisponiveis[i] == loteria.getDezenasCartela().get(index).getDezena()){
                indexArray = i;
                break;
            }
        }
        loteria.setDezenasDisponiveis(dezenasDisponiveis);

        List<DezenaCartela> dezenasSelecionadas = loteria.getDezenasSelecionadas();
        if(dezena.isSelecionado()) {
            if(dezenasSelecionadas.size() == loteria.getQtdDesejadaDezenasSelecionadas()){
                context.exibirToast(context, "Você já selecionou o número máximo de dezenas para esta cartela");
                dezena.setSelecionado(false);
            }else {
                loteria.setDezenasDisponiveis(ArrayUtils.remove(loteria.getDezenasDisponiveis(), indexArray));
                dezenasSelecionadas.add(dezena);
            }
        }else{
            loteria.setDezenasDisponiveis(ArrayUtils.add(loteria.getDezenasDisponiveis(), indexArray));
            dezenasSelecionadas.remove(dezena);
        }

        loteria.setDezenasSelecionadas(dezenasSelecionadas);
        context.getAdapter().atualizarBackground(dezena, tvDezena);
        context.atualizarTextoDezenasSelecionadas(loteria.getDezenasSelecionadas());
    }

    @Override
    public void onExibirDezenasSelecionadas(TextView tvDezenasSelecionadas, List<DezenaCartela> dezenasSelecionados) {
        Collections.sort(dezenasSelecionados, (o1, o2) -> o1.getDezena() - o2.getDezena());
        String listaDezenas = "";

        for(int i = 0; i < dezenasSelecionados.size(); i++){
            DezenaCartela dezena = dezenasSelecionados.get(i);
            String dezenaTexto = dezena.getDezena() >= 10 ?
                    Integer.toString(dezena.getDezena()) : "0" + dezena.getDezena();

            listaDezenas += i > 0 ? " - " + dezenaTexto : dezenaTexto;
        }

        if(listaDezenas.isEmpty()){
            tvDezenasSelecionadas.setVisibility(View.GONE);
        }else{
            tvDezenasSelecionadas.setText(listaDezenas);
            tvDezenasSelecionadas.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onInitSpinnerQtdDezenas(BaseCartelaActivity context, Spinner spQtdDezenas, Loteria loteria) {
        int totalValores = loteria.getQtdMaximaDezenasSelecionadas() - loteria.getQtdMinimaDezenasSelecionadas() + 1;
        String[] quantidades = new String[totalValores];

        for(int i = 0; i < totalValores; i++){
            quantidades[i] = Integer.toString(loteria.getQtdMinimaDezenasSelecionadas() + i);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                context,
                R.layout.spinner_row_cartela,
                R.id.tvValor,
                quantidades
        );

        spQtdDezenas.setAdapter(adapter);
    }

    @Override
    public void onSpinnerSelectionListener(BaseCartelaActivity context, int position, Loteria loteria) {
        int qtdDesejadaSelecionada = loteria.getQtdMinimaDezenasSelecionadas() + position;

        if(qtdDesejadaSelecionada < loteria.getQtdDesejadaDezenasSelecionadas()){
            context.limparCartela();
        }

        loteria.setQtdDesejadaDezenasSelecionadas(qtdDesejadaSelecionada);
    }
}
