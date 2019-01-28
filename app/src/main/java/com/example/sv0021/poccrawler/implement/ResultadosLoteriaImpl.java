package com.example.sv0021.poccrawler.implement;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sv0021.poccrawler.R;
import com.example.sv0021.poccrawler.enumeradores.TipoLoteria;
import com.example.sv0021.poccrawler.model.ResultadoLoteria;
import com.example.sv0021.poccrawler.model.dto.BaseLoteriaComum;
import com.example.sv0021.poccrawler.presenter.ResultadosLoteriaPresenter;
import com.example.sv0021.poccrawler.util.MoedaUtils;
import com.example.sv0021.poccrawler.view.activity.LoteriaActivity;
import com.example.sv0021.poccrawler.view.adapter.CidadesAdapter;
import com.example.sv0021.poccrawler.view.adapter.DezenasAdapter;
import com.example.sv0021.poccrawler.view.adapter.ResultadoLoteriaAdapter;
import com.example.sv0021.poccrawler.view.fragment.BaseResultadosLoteriaFragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.sv0021.poccrawler.util.DataUtils.getDataFormatada;

public class ResultadosLoteriaImpl implements ResultadosLoteriaPresenter {

    @Override
    public void onSetTituloLoteria(LoteriaActivity context, ImageView ivIconeLoteria, TextView tvNomeLoteria) {

        tvNomeLoteria.setText(context.getLoteria().getNomeLoteria());

        switch (context.getLoteria().getCodigoLoteria()){
            case TipoLoteria.MEGASENA:
                ivIconeLoteria.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_megasena));
                break;
            case TipoLoteria.LOTOFACIL:
                ivIconeLoteria.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_lotofacil));
                break;
            case TipoLoteria.QUINA:
                ivIconeLoteria.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_quina));
                break;
            case TipoLoteria.LOTOMANIA:
                ivIconeLoteria.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_lotomania));
                break;
            case TipoLoteria.TIMEMANIA:
                ivIconeLoteria.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_megasena));
                break;
        }
    }

    @Override
    public void onPintarViews(String corPadrao, View viewTraco, TextView tvNomeLoteria, TextView tvGanhadores, TextView tvValorEstimado, ImageView ivAnterior, ImageView ivProximo) {
        tvNomeLoteria.setTextColor(Color.parseColor(corPadrao));
        tvGanhadores.setTextColor(Color.parseColor(corPadrao));
        tvValorEstimado.setTextColor(Color.parseColor(corPadrao));
        viewTraco.setBackgroundColor(Color.parseColor(corPadrao));
        pintarImageViewSeta(corPadrao, ivAnterior);
        pintarImageViewSeta(corPadrao, ivProximo);
    }

    @Override
    public void onAtualizarViewConcurso(LinearLayout llResultadosGerais, LinearLayout llResultadosDetalhados, LinearLayout llValorEstimado, int concursoAtual, int ultimoConcurso, ImageView ivAnterior, ImageView ivProximo) {
        llResultadosGerais.setVisibility(View.GONE);
        llResultadosDetalhados.setVisibility(View.GONE);
        llValorEstimado.setVisibility(concursoAtual == ultimoConcurso ? View.VISIBLE : View.GONE);
        ivAnterior.setVisibility(concursoAtual == 1 ? View.GONE : View.VISIBLE);
        ivProximo.setVisibility(concursoAtual == ultimoConcurso ? View.GONE : View.VISIBLE);
    }

    @Override
    public void onConsultarConcurso(LoteriaActivity context, BaseResultadosLoteriaFragment fragment, int concurso) {
        /*ProgressBarControl.mostrarProgressBar(context);
        Service service = new RetrofitBuilder().getService();
        service.getResultadoConcurso(context.getLoteria().getCodigoLoteria(), concurso).enqueue(new Callback<BaseLoteriaComum>() {
            @Override
            public void onResponse(Call<BaseLoteriaComum> call, Response<BaseLoteriaComum> response) {
                ProgressBarControl.esconderProgressBar(context);
                BaseLoteriaComum loteria = response.body();
                context.setLoteria(loteria);
                context.exibirMenuInferior();
                fragment.exibirInfos();
            }

            @Override
            public void onFailure(Call<BaseLoteriaComum> call, Throwable t) {
                ProgressBarControl.esconderProgressBar(context);
            }
        });*/
    }

    @Override
    public void onExibirResultadoPrincipal(LoteriaActivity context, TextView tvDataSorteio, TextView tvGanhadores, TextView tvValorEstimado, RecyclerView rvCidades, RecyclerView rvDezenas) {

        BaseLoteriaComum loteria = (BaseLoteriaComum)context.getLoteria();

        tvDataSorteio.setText(getDataFormatada(loteria.getDataSorteio()));
        tvValorEstimado.setText(MoedaUtils.getValorMoedaReal(loteria.getEstimativaPremio()));

        int numGanhadores = loteria.getGanhadores().get(0);
        switch (numGanhadores){
            case 0:
                tvGanhadores.setText(context
                        .getResources()
                        .getString(R.string.ultimos_concursos_acumulou));
                break;
            case 1:
                tvGanhadores.setText(context
                        .getResources()
                        .getString(R.string.ultimos_concursos_um_ganhador));
                break;
            default:
                tvGanhadores.setText(context
                        .getResources()
                        .getString(
                                R.string.ultimos_concursos_multiplos_ganhadores,
                                Integer.toString(numGanhadores)));
                break;
        }

        if(numGanhadores > 0){
            CidadesAdapter adapter = new CidadesAdapter(context);
            rvCidades.setAdapter(adapter);
            rvCidades.setVisibility(View.VISIBLE);
        }else{
            rvCidades.setVisibility(View.GONE);
        }

        DezenasAdapter adapter = new DezenasAdapter(
                context,
                context.getLoteria(),
                loteria.getDezenas()
        );

        rvDezenas.setLayoutManager(loteria.getDezenas().size() > 5
                ? new GridLayoutManager(context, 6)
                : new GridLayoutManager(context, loteria.getDezenas().size()));

        rvDezenas.setAdapter(adapter);
    }

    @Override
    public void onExibirResultadosDetalhados(LoteriaActivity context, RecyclerView rvResultados) {
        BaseLoteriaComum loteria = (BaseLoteriaComum)context.getLoteria();
        int qtdMaximaDezenasSorteadas = loteria.getDezenas().size();

        List<ResultadoLoteria> resultados = new ArrayList<>();

        for(int i = 0; i < loteria.getGanhadores().size(); i++){

            if(i == loteria.getGanhadores().size() - 1){
                if(context.getLoteria().getCodigoLoteria() == TipoLoteria.LOTOMANIA){
                    qtdMaximaDezenasSorteadas = 0;
                }

                if(context.getLoteria().getCodigoLoteria() == TipoLoteria.TIMEMANIA){
                    qtdMaximaDezenasSorteadas = -1;
                }
            }

            int qtdGanhadores = loteria.getGanhadores().get(i);
            double valorRateio = loteria.getRateio().get(i);
            double valorTotal = loteria.getGanhadores().get(i) * loteria.getRateio().get(i);

            ResultadoLoteria resultado = new ResultadoLoteria(
                    qtdMaximaDezenasSorteadas,
                    qtdGanhadores,
                    valorRateio,
                    valorTotal
            );

            resultados.add(resultado);
            qtdMaximaDezenasSorteadas--;
        }

        ResultadoLoteriaAdapter adapter = new ResultadoLoteriaAdapter(
                context,
                resultados,
                loteria.getCorPadrao()
        );

        rvResultados.setAdapter(adapter);
    }

    @Override
    public void onSetDatas(Date data, TextView tvData) {
        tvData.setText(getDataFormatada(data));
    }


    private void pintarImageViewSeta(String corPadrao, ImageView imageView){
        GradientDrawable drawable = (GradientDrawable)imageView.getBackground();
        drawable.setStroke(5, Color.parseColor(corPadrao));
        imageView.setColorFilter(Color.parseColor(corPadrao));
    }
}
