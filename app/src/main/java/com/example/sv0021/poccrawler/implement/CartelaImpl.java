package com.example.sv0021.poccrawler.implement;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sv0021.poccrawler.R;
import com.example.sv0021.poccrawler.enumeradores.TipoLoteria;
import com.example.sv0021.poccrawler.model.Concurso;
import com.example.sv0021.poccrawler.model.DezenaCartela;
import com.example.sv0021.poccrawler.model.Cartela;
import com.example.sv0021.poccrawler.model.JogoSalvo;
import com.example.sv0021.poccrawler.model.dto.BaseLoteriaComum;
import com.example.sv0021.poccrawler.model.dto.BaseLoteriaResponse;
import com.example.sv0021.poccrawler.presenter.CartelaPresenter;
import com.example.sv0021.poccrawler.util.Constants;
import com.example.sv0021.poccrawler.view.activity.LoteriaActivity;
import com.example.sv0021.poccrawler.view.activity.TimesActivity;
import com.example.sv0021.poccrawler.view.adapter.CartelaAdapter;
import com.example.sv0021.poccrawler.view.fragment.CartelaFragment;
import com.google.gson.Gson;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static android.app.Activity.RESULT_OK;

public class CartelaImpl implements CartelaPresenter {

    @Override
    public void onMontarCartela(LoteriaActivity context, CartelaFragment fragment, Cartela cartela, RecyclerView rvDezenas) {
        List<DezenaCartela> dezenasCartela = new ArrayList<>();
        int[] dezenasDisponiveis = new int[cartela.getQtdDezenasCartela()];

        for(int i = 0; i < dezenasDisponiveis.length; i++){
            dezenasDisponiveis[i] = i + 1;

            DezenaCartela dezena = new DezenaCartela();
            dezena.setDezena(i + 1);
            dezena.setSelecionado(false);
            dezenasCartela.add(dezena);
        }

        cartela.setDezenasDisponiveis(dezenasDisponiveis);
        cartela.setDezenasCartela(dezenasCartela);

        fragment.setAdapter(new CartelaAdapter(context, fragment, cartela.getDezenasCartela()));
        rvDezenas.setAdapter(fragment.getAdapter());
    }

    @Override
    public void onCompletarCartela(LoteriaActivity context, CartelaFragment fragment, Cartela cartela) {
        Random random = new Random();
        int qtdDezenas = cartela.getQtdDesejadaDezenasSelecionadas() - cartela.getDezenasSelecionadas().size();

        if(qtdDezenas == 0){
            context.exibirToast(
                    context,
                    context
                            .getResources()
                            .getString(R.string.erro_qtd_maxima_dezenas_selecionadas)
            );
        }else {
            List<DezenaCartela> dezenasSelecionadas = cartela.getDezenasSelecionadas();
            for (int i = 0; i < qtdDezenas; i++) {
                int index = random.nextInt(cartela.getDezenasDisponiveis().length);
                dezenasSelecionadas.add(cartela.getDezenasCartela()
                        .get(cartela.getDezenasDisponiveis()[index] - 1));
                cartela.setDezenasDisponiveis(ArrayUtils.remove(cartela.getDezenasDisponiveis(), index));
            }

            cartela.setDezenasSelecionadas(dezenasSelecionadas);

            fragment.atualizarTextoDezenasSelecionadas(cartela.getDezenasSelecionadas());

            for (DezenaCartela dezena : cartela.getDezenasCartela()) {
                if (dezenasSelecionadas.contains(dezena)) {
                    dezena.setSelecionado(true);
                }
            }
            fragment.getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void onLimparCartela(LoteriaActivity context, CartelaFragment fragment, Cartela cartela, EditText etTimeCoracao) {
        etTimeCoracao.setText("");

        int[] dezenasDisponiveis = new int[cartela.getQtdDezenasCartela()];
        for(int i = 0; i < cartela.getQtdDezenasCartela(); i++){
            cartela.getDezenasCartela().get(i).setSelecionado(false);
            dezenasDisponiveis[i] = i + 1;
        }

        cartela.setDezenasDisponiveis(dezenasDisponiveis);
        cartela.getDezenasSelecionadas().clear();

        fragment.atualizarTextoDezenasSelecionadas(cartela.getDezenasSelecionadas());
        fragment.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onDezenaClick(LoteriaActivity context, CartelaFragment fragment, int index, TextView tvDezena, Cartela cartela) {
        DezenaCartela dezena = cartela.getDezenasCartela().get(index);
        dezena.setSelecionado(!dezena.isSelecionado());

        cartela.getDezenasCartela().set(index, dezena);

        int indexArray = 0;

        int[] dezenasDisponiveis = cartela.getDezenasDisponiveis();
        for(int i = 0; i < dezenasDisponiveis.length; i++){
            if(dezenasDisponiveis[i] == cartela.getDezenasCartela().get(index).getDezena()){
                indexArray = i;
                break;
            }
        }
        cartela.setDezenasDisponiveis(dezenasDisponiveis);

        List<DezenaCartela> dezenasSelecionadas = cartela.getDezenasSelecionadas();
        if(dezena.isSelecionado()) {
            if(dezenasSelecionadas.size() == cartela.getQtdDesejadaDezenasSelecionadas()){
                context.exibirToast(context, context.getResources().getString(R.string.erro_qtd_maxima_dezenas_selecionadas));
                dezena.setSelecionado(false);
            }else {
                cartela.setDezenasDisponiveis(ArrayUtils.remove(cartela.getDezenasDisponiveis(), indexArray));
                dezenasSelecionadas.add(dezena);
            }
        }else{
            cartela.setDezenasDisponiveis(ArrayUtils.add(cartela.getDezenasDisponiveis(), indexArray));
            for(DezenaCartela d : dezenasSelecionadas){
                if(d.getDezena() == dezena.getDezena()){
                    dezenasSelecionadas.remove(d);
                    break;
                }
            }
        }

        BaseLoteriaResponse loteria = context.getLoteria();
        String corBackground = loteria.getCodigoLoteria() != TipoLoteria.TIMEMANIA ?
                loteria.getCorPadrao() : loteria.getCorSecundaria();

        cartela.setDezenasSelecionadas(dezenasSelecionadas);
        fragment.getAdapter().atualizarBackground(dezena, tvDezena, corBackground);
        fragment.atualizarTextoDezenasSelecionadas(cartela.getDezenasSelecionadas());
    }

    @Override
    public void onExibirDezenasSelecionadas(TextView tvDezenasSelecionadas, List<DezenaCartela> dezenasSelecionados) {
        Collections.sort(dezenasSelecionados, (o1, o2) -> o1.getDezena() - o2.getDezena());
        String listaDezenas = "";

        for(int i = 0; i < dezenasSelecionados.size(); i++){
            DezenaCartela dezena = dezenasSelecionados.get(i);
            String dezenaTexto = dezena.getDezena() >= 10 ?
                    Integer.toString(dezena.getDezena()) : "0" + dezena.getDezena();

            if(dezenaTexto.length() == 3){
                dezenaTexto = Character.toString(dezenaTexto.charAt(1)) + Character.toString(dezenaTexto.charAt(2));
            }

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
    public void onSetTimeCoracaoEdicao(EditText etTime, String timeCoracao) {
        etTime.setText(timeCoracao);
    }

    @Override
    public void onInitSpinnerQtdDezenas(LoteriaActivity context, Spinner spQtdDezenas, Cartela cartela) {
        int totalValores = cartela.getQtdMaximaDezenasSelecionadas() - cartela.getQtdMinimaDezenasSelecionadas() + 1;
        String[] quantidades = new String[totalValores];

        for(int i = 0; i < totalValores; i++){
            quantidades[i] = Integer.toString(cartela.getQtdMinimaDezenasSelecionadas() + i);
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
    public void onSpinnerSelectionListener(LoteriaActivity context, CartelaFragment fragment, int position, Cartela cartela) {
        int qtdDesejadaSelecionada = cartela.getQtdMinimaDezenasSelecionadas() + position;

        if(qtdDesejadaSelecionada < cartela.getQtdDesejadaDezenasSelecionadas()){
            fragment.limparCartela();
        }

        cartela.setQtdDesejadaDezenasSelecionadas(qtdDesejadaSelecionada);
    }

    @Override
    public void onSalvarJogo(LoteriaActivity context, CartelaFragment fragment, Cartela cartela) {

        if(context.getIdJogoEdicao() != null){
            salvarEdicao(context, fragment, cartela);
        }else{
            salvarJogo(context, fragment, cartela);
        }

    }

    @Override
    public void onMontarCartelaEdicao(LoteriaActivity context, Spinner spQtdDezenas, CartelaFragment fragment, Cartela cartela) {

        Long idJogo = context.getIdJogoEdicao();

        if(idJogo != null) {
            JogoSalvo jogoEdicao = null;
            List<JogoSalvo> jogosSalvos = context.getConcursosSalvos().get(0).getJogosSalvos();

            for (int i = 0; i < jogosSalvos.size(); i++) {
                if (jogosSalvos.get(i).getIdJogo().equals(idJogo)) {
                    jogoEdicao = jogosSalvos.get(i);
                    break;
                }
            }

            if (jogoEdicao != null) {

                if(context.getLoteria().getCodigoLoteria() == TipoLoteria.TIMEMANIA){
                    String timeCoracao = jogoEdicao.getTimeCoracao();
                    cartela.setTimeCoracao(timeCoracao);
                    fragment.setTimeCoracaoEdicao(timeCoracao);
                }

                List<DezenaCartela> dezenasSelecionadas = cartela.getDezenasSelecionadas();
                for (int i = 0; i < jogoEdicao.getDezenas().size(); i++) {
                    DezenaCartela dezenaSelecionada = cartela.getDezenasCartela()
                            .get(jogoEdicao.getDezenas().get(i) - 1);

                    dezenasSelecionadas.add(dezenaSelecionada);

                    cartela.setDezenasDisponiveis(ArrayUtils.removeElement(
                            cartela.getDezenasDisponiveis(),
                            dezenaSelecionada.getDezena())
                    );

                    DezenaCartela dezenaCartela = new DezenaCartela();
                    dezenaCartela.setDezena(jogoEdicao.getDezenas().get(i));
                    dezenaCartela.setSelecionado(true);

                    cartela.getDezenasCartela().set(jogoEdicao.getDezenas().get(i) - 1, dezenaCartela);
                }

                cartela.setQtdDesejadaDezenasSelecionadas(dezenasSelecionadas.size());
                cartela.setDezenasSelecionadas(dezenasSelecionadas);

                fragment.atualizarTextoDezenasSelecionadas(cartela.getDezenasSelecionadas());
                fragment.getAdapter().notifyDataSetChanged();

                for (int i = 0; i < spQtdDezenas.getAdapter().getCount(); i++) {
                    if (Integer.parseInt(spQtdDezenas.getItemAtPosition(i).toString()) == dezenasSelecionadas.size()) {
                        spQtdDezenas.setSelection(i);
                        break;
                    }
                }
            }
        }

    }

    @Override
    public void onConfigurarTimeCoracao(LoteriaActivity context, CartelaFragment fragment, TextInputLayout tilTime, EditText etTimeCoracao) {

        if(context.getLoteria().getCodigoLoteria() == TipoLoteria.TIMEMANIA){
            tilTime.setVisibility(View.VISIBLE);
            etTimeCoracao.setOnClickListener(view -> {
                Intent intent = new Intent(context, TimesActivity.class);
                fragment.startActivityForResult(intent, Constants.REQUEST_CODE_TIMEMANIA);
            });

        }

    }

    @Override
    public void onTimeCoracaoResult(EditText etTime, Cartela cartela, int requestCode, int resultCode, Intent data) {

        if(requestCode == Constants.REQUEST_CODE_TIMEMANIA && resultCode == RESULT_OK &&
                data.getExtras() != null){

            String time = data.getStringExtra(Constants.EXTRA_TIME_CORACAO);
            cartela.setTimeCoracao(time);
            etTime.setText(time);
        }

    }

    @Override
    public void onConfigurarBotoes(LoteriaActivity context, CartelaFragment fragment, Cartela cartela, Button btnCompletar, Button btnSalvar, Button btnLimpar) {
        String corPadrao = context.getLoteria().getCorPadrao();
        btnCompletar.setBackgroundColor(Color.parseColor(corPadrao));
        btnSalvar.setBackgroundColor(Color.parseColor(corPadrao));
        btnLimpar.setBackgroundColor(Color.parseColor(corPadrao));

        String corSecundaria = context.getLoteria().getCorSecundaria();
        btnCompletar.setTextColor(Color.parseColor(corSecundaria));
        btnSalvar.setTextColor(Color.parseColor(corSecundaria));
        btnLimpar.setTextColor(Color.parseColor(corSecundaria));

        btnCompletar.setOnClickListener(view -> onCompletarCartela(
                context,
                fragment,
                cartela
        ));

        btnSalvar.setOnClickListener(view -> onSalvarJogo(
                context,
                fragment,
                cartela
        ));

        btnLimpar.setOnClickListener(view -> fragment.limparCartela());


    }

    private void salvarJogo(LoteriaActivity context, CartelaFragment fragment, Cartela cartela){
        if(cartela.getDezenasSelecionadas().size() < cartela.getQtdDesejadaDezenasSelecionadas()){
            context.exibirToast(
                    context,
                    context
                            .getResources()
                            .getString(
                                    R.string.erro_qtd_minima_dezenas_selecionadas,
                                    Integer.toString(cartela.getQtdDesejadaDezenasSelecionadas())
                            )
            );

            return;
        }

        if(cartela.getTimeCoracao() == null || cartela.getTimeCoracao().isEmpty()){
            context.exibirToast(
                    context,
                    context
                            .getResources()
                            .getString(R.string.erro_time_nao_selecionado)
            );

            return;
        }

        List<Integer> dezenas = new ArrayList<>();
        for (DezenaCartela dezena : cartela.getDezenasSelecionadas()) {
            dezenas.add(dezena.getDezena());
        }

        List<Concurso> concursosSalvos = context.getConcursosSalvos();

        int proximoConcurso = context.getUltimoConcurso() + 1;

        JogoSalvo jogoSalvo;

        if(context.getLoteria().getCodigoLoteria() == TipoLoteria.TIMEMANIA){
            jogoSalvo = new JogoSalvo(System.currentTimeMillis(), dezenas, cartela.getTimeCoracao());
        }else{
            jogoSalvo = new JogoSalvo(System.currentTimeMillis(), dezenas);
        }

        if(concursosSalvos.size() > 0 &&
                concursosSalvos.get(0).getNumConcurso() == proximoConcurso){

            concursosSalvos.get(0).getJogosSalvos().add(jogoSalvo);
        }else{
            List<JogoSalvo> jogosSalvos = new ArrayList<>();
            jogosSalvos.add(jogoSalvo);

            concursosSalvos.add(
                    new Concurso(proximoConcurso, jogosSalvos, context.getLoteria().getProximoSorteio()));
        }

        context.salvarJogo(new Gson().toJson(concursosSalvos));

        context.exibirToast(context, context.getResources().getString(R.string.cartela_jogo_salvo));
        fragment.limparCartela();

    }

    private void salvarEdicao(LoteriaActivity context, CartelaFragment fragment, Cartela cartela){
        Long idJogo = context.getIdJogoEdicao();

        if(idJogo != null) {
            int indexJogoArray = -1;
            List<JogoSalvo> jogosSalvos = context.getConcursosSalvos().get(0).getJogosSalvos();

            for (int i = 0; i < jogosSalvos.size(); i++) {
                if (jogosSalvos.get(i).getIdJogo().equals(idJogo)) {
                    indexJogoArray = i;
                }
            }

            List<Integer> dezenas = new ArrayList<>();
            for (DezenaCartela dezena : cartela.getDezenasSelecionadas()) {
                dezenas.add(dezena.getDezena());
            }

            List<Concurso> concursosSalvos = context.getConcursosSalvos();
            concursosSalvos.get(0).getJogosSalvos().set(indexJogoArray, new JogoSalvo(idJogo, dezenas));
            context.salvarJogo(new Gson().toJson(concursosSalvos));

            context.exibirToast(context, context.getResources().getString(R.string.cartela_jogo_alterado));
            fragment.limparCartela();

            context.desativarEdicao();
        }
    }

}
