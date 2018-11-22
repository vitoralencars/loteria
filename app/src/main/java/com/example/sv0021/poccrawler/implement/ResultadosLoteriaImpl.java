package com.example.sv0021.poccrawler.implement;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sv0021.poccrawler.R;
import com.example.sv0021.poccrawler.enumeradores.TipoLoteria;
import com.example.sv0021.poccrawler.presenter.ResultadosLoteriaPresenter;
import com.example.sv0021.poccrawler.util.MoedaUtils;
import com.example.sv0021.poccrawler.view.activities.LoteriaActivity;
import com.example.sv0021.poccrawler.view.adapter.DezenasSorteadasAdapter;

import java.util.Date;

import static com.example.sv0021.poccrawler.util.DataUtils.getDataFormatada;

public class ResultadosLoteriaImpl implements ResultadosLoteriaPresenter {

    @Override
    public void setTituloLoteria(LoteriaActivity context, ImageView ivIconeLoteria, TextView tvNomeLoteria) {

        tvNomeLoteria.setText(context.getLoteria().getNomeLoteria());

        switch (context.getLoteria().getCodigoLoteria()){
            case TipoLoteria.MEGA_SENA:
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
        }
    }

    @Override
    public void pintarViews(String corPadrao, View viewTraco, TextView tvNomeLoteria, TextView tvGanhadores, TextView tvValorEstimado, ImageView ivAnterior, ImageView ivProximo) {
        tvNomeLoteria.setTextColor(Color.parseColor(corPadrao));
        tvGanhadores.setTextColor(Color.parseColor(corPadrao));
        tvValorEstimado.setTextColor(Color.parseColor(corPadrao));
        viewTraco.setBackgroundColor(Color.parseColor(corPadrao));
        pintarImageViewSeta(corPadrao, ivAnterior);
        pintarImageViewSeta(corPadrao, ivProximo);
    }

    @Override
    public void exibirResultadoPrincipal(LoteriaActivity context, TextView tvGanhadores, TextView tvValorEstimado, RecyclerView rvDezenas) {

        tvValorEstimado.setText(MoedaUtils.getValorMoedaReal(context.getLoteria().getEstimativaPremio()));

        int numGanhadores = context.getLoteria().getGanhadores().size();
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

        DezenasSorteadasAdapter adapter = new DezenasSorteadasAdapter(
                context,
                context.getLoteria().getCorPadrao(),
                context.getLoteria().getDezenas()
        );

        rvDezenas.setLayoutManager(context.getLoteria().getDezenas().size() > 5
                ? new GridLayoutManager(context, 6)
                : new GridLayoutManager(context, context.getLoteria().getDezenas().size()));

        rvDezenas.setAdapter(adapter);
    }

    @Override
    public void setDataProximoSorteio(Date data, TextView tvData) {
        tvData.setText(getDataFormatada(data));
    }


    private void pintarImageViewSeta(String corPadrao, ImageView imageView){
        GradientDrawable drawable = (GradientDrawable)imageView.getBackground();
        drawable.setStroke(5, Color.parseColor(corPadrao));
        imageView.setColorFilter(Color.parseColor(corPadrao));
    }
}
