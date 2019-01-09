package com.example.sv0021.poccrawler.implement;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.sv0021.poccrawler.R;
import com.example.sv0021.poccrawler.model.dto.BaseLoteriaComum;
import com.example.sv0021.poccrawler.model.dto.BaseLoteriaResponse;
import com.example.sv0021.poccrawler.model.dto.Duplasena;
import com.example.sv0021.poccrawler.model.dto.LoteriasResponse;
import com.example.sv0021.poccrawler.model.dto.Lotofacil;
import com.example.sv0021.poccrawler.model.dto.Lotomania;
import com.example.sv0021.poccrawler.model.dto.Megasena;
import com.example.sv0021.poccrawler.model.dto.Quina;
import com.example.sv0021.poccrawler.model.dto.Timemania;
import com.example.sv0021.poccrawler.presenter.UltimosConcursosPresenter;
import com.example.sv0021.poccrawler.retrofit.RetrofitBuilder;
import com.example.sv0021.poccrawler.retrofit.Service;
import com.example.sv0021.poccrawler.util.DataUtils;
import com.example.sv0021.poccrawler.util.MoedaUtils;
import com.example.sv0021.poccrawler.util.ProgressBarControl;
import com.example.sv0021.poccrawler.view.activity.UltimosConcursosActivity;
import com.example.sv0021.poccrawler.view.adapter.DezenasAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UltimosConcursosImpl implements UltimosConcursosPresenter {

    @Override
    public void onListarUltimosConcursos(UltimosConcursosActivity context) {
        ProgressBarControl.mostrarProgressBar(context);
        Service service = new RetrofitBuilder().getService();
        service.getUltimosResultados().enqueue(new Callback<LoteriasResponse>() {
            @Override
            public void onResponse(Call<LoteriasResponse> call, Response<LoteriasResponse> response) {
                ProgressBarControl.esconderProgressBar(context);
                if(response.body() != null){
                    LoteriasResponse loterias = response.body();

                    exibirMegasena(context, loterias.getMegasena());
                    exibirLotofacil(context, loterias.getLotofacil());
                    exibirQuina(context, loterias.getQuina());
                    exibirLotomania(context, loterias.getLotomania());
                    exibirTimemania(context, loterias.getTimemania());
                    exibirDuplasena(context, loterias.getDuplasena());
                }
            }

            @Override
            public void onFailure(Call<LoteriasResponse> call, Throwable throwable) {
                ProgressBarControl.esconderProgressBar(context);
            }
        });
    }

    private void exibirMegasena(UltimosConcursosActivity context, Megasena megasena){
        CardView card = context.findViewById(R.id.cardMegasena);
        View viewTop = context.findViewById(R.id.viewTopMegasena);
        View viewBottom = context.findViewById(R.id.viewBottomMegasena);
        RecyclerView rvSorteio = context.findViewById(R.id.rvSorteioMegasena);
        TextView tvNomeLoteria = context.findViewById(R.id.tvNomeLoteriaMegasena);
        TextView tvDataConcurso = context.findViewById(R.id.tvDataConcursoMegasena);
        TextView tvConcurso = context.findViewById(R.id.tvConcursoMegasena);
        TextView tvGanhadores = context.findViewById(R.id.tvGanhadoresMegasena);
        TextView tvValorEstimado = context.findViewById(R.id.tvValorEstimadoMegasena);
        TextView tvDataProximoSorteio = context.findViewById(R.id.tvDataProximoSorteioMegasena);

        card.setVisibility(View.VISIBLE);
        tvNomeLoteria.setText(megasena.getNomeLoteria());
        tvDataConcurso.setText(DataUtils.getDataFormatada(megasena.getDataSorteio()));
        tvConcurso.setText(Integer.toString(megasena.getConcurso()));
        tvValorEstimado.setText(MoedaUtils.getValorMoedaReal(megasena.getEstimativaPremio()));
        tvDataProximoSorteio.setText(DataUtils.getDataFormatada(megasena.getProximoSorteio()));

        setInformacoesGanhadores(context, tvGanhadores, megasena.getGanhadores().get(0));
        listarDezenasSorteadas(context, megasena, megasena.getDezenas(), rvSorteio);

        pintarCampo(context, viewTop, megasena.getCorPadrao());
        pintarCampo(context, viewBottom, megasena.getCorPadrao());
        pintarCampo(context, tvNomeLoteria, megasena.getCorPadrao());
        pintarCampo(context, tvDataConcurso, megasena.getCorPadrao());
        pintarCampo(context, tvConcurso, megasena.getCorPadrao());
        pintarCampo(context, tvGanhadores, megasena.getCorPadrao());
        pintarCampo(context, tvValorEstimado, megasena.getCorPadrao());
    }

    private void exibirLotofacil(UltimosConcursosActivity context, Lotofacil lotofacil){
        CardView card = context.findViewById(R.id.cardLotofacil);
        View viewTop = context.findViewById(R.id.viewTopLotofacil);
        View viewBottom = context.findViewById(R.id.viewBottomLotofacil);
        RecyclerView rvSorteio = context.findViewById(R.id.rvSorteioLotofacil);
        TextView tvNomeLoteria = context.findViewById(R.id.tvNomeLoteriaLotofacil);
        TextView tvDataConcurso = context.findViewById(R.id.tvDataConcursoLotofacil);
        TextView tvConcurso = context.findViewById(R.id.tvConcursoLotofacil);
        TextView tvGanhadores = context.findViewById(R.id.tvGanhadoresLotofacil);
        TextView tvValorEstimado = context.findViewById(R.id.tvValorEstimadoLotofacil);
        TextView tvDataProximoSorteio = context.findViewById(R.id.tvDataProximoSorteioLotofacil);

        card.setVisibility(View.VISIBLE);
        tvNomeLoteria.setText(lotofacil.getNomeLoteria());
        tvDataConcurso.setText(DataUtils.getDataFormatada(lotofacil.getDataSorteio()));
        tvConcurso.setText(Integer.toString(lotofacil.getConcurso()));
        tvValorEstimado.setText(MoedaUtils.getValorMoedaReal(lotofacil.getEstimativaPremio()));
        tvDataProximoSorteio.setText(DataUtils.getDataFormatada(lotofacil.getProximoSorteio()));

        setInformacoesGanhadores(context, tvGanhadores, lotofacil.getGanhadores().get(0));
        listarDezenasSorteadas(context, lotofacil, lotofacil.getDezenas(), rvSorteio);

        pintarCampo(context, viewTop, lotofacil.getCorPadrao());
        pintarCampo(context, viewBottom, lotofacil.getCorPadrao());
        pintarCampo(context, tvNomeLoteria, lotofacil.getCorPadrao());
        pintarCampo(context, tvDataConcurso, lotofacil.getCorPadrao());
        pintarCampo(context, tvConcurso, lotofacil.getCorPadrao());
        pintarCampo(context, tvGanhadores, lotofacil.getCorPadrao());
        pintarCampo(context, tvValorEstimado, lotofacil.getCorPadrao());
    }

    private void exibirQuina(UltimosConcursosActivity context, Quina quina){
        CardView card = context.findViewById(R.id.cardQuina);
        View viewTop = context.findViewById(R.id.viewTopQuina);
        View viewBottom = context.findViewById(R.id.viewBottomQuina);
        RecyclerView rvSorteio = context.findViewById(R.id.rvSorteioQuina);
        TextView tvNomeLoteria = context.findViewById(R.id.tvNomeLoteriaQuina);
        TextView tvDataConcurso = context.findViewById(R.id.tvDataConcursoQuina);
        TextView tvConcurso = context.findViewById(R.id.tvConcursoQuina);
        TextView tvGanhadores = context.findViewById(R.id.tvGanhadoresQuina);
        TextView tvValorEstimado = context.findViewById(R.id.tvValorEstimadoQuina);
        TextView tvDataProximoSorteio = context.findViewById(R.id.tvDataProximoSorteioQuina);

        card.setVisibility(View.VISIBLE);
        tvNomeLoteria.setText(quina.getNomeLoteria());
        tvDataConcurso.setText(DataUtils.getDataFormatada(quina.getDataSorteio()));
        tvConcurso.setText(Integer.toString(quina.getConcurso()));
        tvValorEstimado.setText(MoedaUtils.getValorMoedaReal(quina.getEstimativaPremio()));
        tvDataProximoSorteio.setText(DataUtils.getDataFormatada(quina.getProximoSorteio()));

        setInformacoesGanhadores(context, tvGanhadores, quina.getGanhadores().get(0));
        listarDezenasSorteadas(context, quina, quina.getDezenas(), rvSorteio);

        pintarCampo(context, viewTop, quina.getCorPadrao());
        pintarCampo(context, viewBottom, quina.getCorPadrao());
        pintarCampo(context, tvNomeLoteria, quina.getCorPadrao());
        pintarCampo(context, tvDataConcurso, quina.getCorPadrao());
        pintarCampo(context, tvConcurso, quina.getCorPadrao());
        pintarCampo(context, tvGanhadores, quina.getCorPadrao());
        pintarCampo(context, tvValorEstimado, quina.getCorPadrao());


    }

    private void exibirLotomania(UltimosConcursosActivity context, Lotomania lotomania){
        CardView card = context.findViewById(R.id.cardLotomania);
        View viewTop = context.findViewById(R.id.viewTopLotomania);
        View viewBottom = context.findViewById(R.id.viewBottomLotomania);
        RecyclerView rvSorteio = context.findViewById(R.id.rvSorteioLotomania);
        TextView tvNomeLoteria = context.findViewById(R.id.tvNomeLoteriaLotomania);
        TextView tvDataConcurso = context.findViewById(R.id.tvDataConcursoLotomania);
        TextView tvConcurso = context.findViewById(R.id.tvConcursoLotomania);
        TextView tvGanhadores = context.findViewById(R.id.tvGanhadoresLotomania);
        TextView tvValorEstimado = context.findViewById(R.id.tvValorEstimadoLotomania);
        TextView tvDataProximoSorteio = context.findViewById(R.id.tvDataProximoSorteioLotomania);

        card.setVisibility(View.VISIBLE);
        tvNomeLoteria.setText(lotomania.getNomeLoteria());
        tvDataConcurso.setText(DataUtils.getDataFormatada(lotomania.getDataSorteio()));
        tvConcurso.setText(Integer.toString(lotomania.getConcurso()));
        tvValorEstimado.setText(MoedaUtils.getValorMoedaReal(lotomania.getEstimativaPremio()));
        tvDataProximoSorteio.setText(DataUtils.getDataFormatada(lotomania.getProximoSorteio()));

        setInformacoesGanhadores(context, tvGanhadores, lotomania.getGanhadores().get(0));
        listarDezenasSorteadas(context, lotomania, lotomania.getDezenas(), rvSorteio);

        pintarCampo(context, viewTop, lotomania.getCorPadrao());
        pintarCampo(context, viewBottom, lotomania.getCorPadrao());
        pintarCampo(context, tvNomeLoteria, lotomania.getCorPadrao());
        pintarCampo(context, tvDataConcurso, lotomania.getCorPadrao());
        pintarCampo(context, tvConcurso, lotomania.getCorPadrao());
        pintarCampo(context, tvGanhadores, lotomania.getCorPadrao());
        pintarCampo(context, tvValorEstimado, lotomania.getCorPadrao());
    }

    private void exibirTimemania(UltimosConcursosActivity context, Timemania timemania){
        CardView card = context.findViewById(R.id.cardTimemania);
        View viewTop = context.findViewById(R.id.viewTopTimemania);
        View viewBottom = context.findViewById(R.id.viewBottomTimemania);
        RecyclerView rvSorteio = context.findViewById(R.id.rvSorteioTimemania);
        TextView tvNomeLoteria = context.findViewById(R.id.tvNomeLoteriaTimemania);
        TextView tvDataConcurso = context.findViewById(R.id.tvDataConcursoTimemania);
        TextView tvConcurso = context.findViewById(R.id.tvConcursoTimemania);
        TextView tvGanhadores = context.findViewById(R.id.tvGanhadoresTimemania);
        TextView tvValorEstimado = context.findViewById(R.id.tvValorEstimadoTimemania);
        TextView tvDataProximoSorteio = context.findViewById(R.id.tvDataProximoSorteioTimemania);

        card.setVisibility(View.VISIBLE);
        tvNomeLoteria.setText(timemania.getNomeLoteria());
        tvDataConcurso.setText(DataUtils.getDataFormatada(timemania.getDataSorteio()));
        tvConcurso.setText(Integer.toString(timemania.getConcurso()));
        tvValorEstimado.setText(MoedaUtils.getValorMoedaReal(timemania.getEstimativaPremio()));
        tvDataProximoSorteio.setText(DataUtils.getDataFormatada(timemania.getProximoSorteio()));

        setInformacoesGanhadores(context, tvGanhadores, timemania.getGanhadores().get(0));
        listarDezenasSorteadas(context, timemania, timemania.getDezenas(), rvSorteio);

        pintarCampo(context, viewTop, timemania.getCorPadrao());
        pintarCampo(context, viewBottom, timemania.getCorPadrao());
        pintarCampo(context, tvNomeLoteria, timemania.getCorPadrao());
        pintarCampo(context, tvDataConcurso, timemania.getCorPadrao());
        pintarCampo(context, tvConcurso, timemania.getCorPadrao());
        pintarCampo(context, tvGanhadores, timemania.getCorPadrao());
        pintarCampo(context, tvValorEstimado, timemania.getCorPadrao());
    }

    private void exibirDuplasena(UltimosConcursosActivity context, Duplasena duplasena){
        CardView card = context.findViewById(R.id.cardDuplasena);
        View viewTop = context.findViewById(R.id.viewTopDuplasena);
        View viewBottom = context.findViewById(R.id.viewBottomDuplasena);
        RecyclerView rvPrimeiroSorteio = context.findViewById(R.id.rvPrimeiroSorteio);
        RecyclerView rvSegundoSorteio = context.findViewById(R.id.rvSegundoSorteio);
        TextView tvNomeLoteria = context.findViewById(R.id.tvNomeLoteriaDuplasena);
        TextView tvDataConcurso = context.findViewById(R.id.tvDataConcursoDuplasena);
        TextView tvConcurso = context.findViewById(R.id.tvConcursoDuplasena);
        TextView tvGanhadores = context.findViewById(R.id.tvGanhadoresDuplasena);
        TextView tvValorEstimado = context.findViewById(R.id.tvValorEstimadoDuplasena);
        TextView tvDataProximoSorteio = context.findViewById(R.id.tvDataProximoSorteioDuplasena);

        card.setVisibility(View.VISIBLE);
        tvNomeLoteria.setText(duplasena.getNomeLoteria());
        tvDataConcurso.setText(DataUtils.getDataFormatada(duplasena.getDataSorteio()));
        tvConcurso.setText(Integer.toString(duplasena.getConcurso()));
        tvValorEstimado.setText(MoedaUtils.getValorMoedaReal(duplasena.getEstimativaPremio()));
        tvDataProximoSorteio.setText(DataUtils.getDataFormatada(duplasena.getProximoSorteio()));

        setInformacoesGanhadores(
                context,
                tvGanhadores,
                duplasena.getGanhadoresPrimeiroSorteio().get(0)
        );

        listarDezenasSorteadas(
                context,
                duplasena,
                duplasena.getDezenasPrimeiroSorteio(),
                rvPrimeiroSorteio
        );

        listarDezenasSorteadas(
                context,
                duplasena,
                duplasena.getDezenasSegundoSorteio(),
                rvSegundoSorteio
        );

        pintarCampo(context, viewTop, duplasena.getCorPadrao());
        pintarCampo(context, viewBottom, duplasena.getCorPadrao());
        pintarCampo(context, tvNomeLoteria, duplasena.getCorPadrao());
        pintarCampo(context, tvDataConcurso, duplasena.getCorPadrao());
        pintarCampo(context, tvConcurso, duplasena.getCorPadrao());
        pintarCampo(context, tvGanhadores, duplasena.getCorPadrao());
        pintarCampo(context, tvValorEstimado, duplasena.getCorPadrao());
    }

    private void setInformacoesGanhadores(Context context, TextView tvGanhadores, int numGanhadores){
        switch (numGanhadores){
            case 0:
                tvGanhadores.setText(context.getResources()
                        .getString(R.string.ultimos_concursos_acumulou));
                break;
            case 1:
                tvGanhadores.setText(context.getResources()
                        .getString(R.string.ultimos_concursos_um_ganhador));
                break;
            default:
                tvGanhadores.setText(context.getResources()
                        .getString(R.string.ultimos_concursos_multiplos_ganhadores,
                                Integer.toString(numGanhadores)));
                break;
        }
    }

    private void listarDezenasSorteadas(Context context, BaseLoteriaResponse loteria, List<Integer> dezenas, RecyclerView rvSorteio){

        DezenasAdapter adapter = new DezenasAdapter(
                context,
                loteria,
                dezenas
        );

        rvSorteio.setLayoutManager(dezenas.size() > 5
                ? new GridLayoutManager(context, 6)
                : new GridLayoutManager(context, dezenas.size()));

        rvSorteio.setAdapter(adapter);
    }

    private void pintarCampo(Context context, View view, String cor){
        if(view instanceof TextView){
            ((TextView)view).setTextColor(Color.parseColor(cor));
        }else{
            view.setBackgroundColor(Color.parseColor(cor));
        }
    }

}
