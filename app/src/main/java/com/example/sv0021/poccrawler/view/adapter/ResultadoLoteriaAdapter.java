package com.example.sv0021.poccrawler.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sv0021.poccrawler.R;
import com.example.sv0021.poccrawler.model.ResultadoLoteria;
import com.example.sv0021.poccrawler.util.MoedaUtils;

import java.util.List;

public class ResultadoLoteriaAdapter extends RecyclerView.Adapter<ResultadoLoteriaAdapter.ViewHolder>{

    private Context context;
    private List<ResultadoLoteria> resultados;
    private String corPadrao;

    public ResultadoLoteriaAdapter(Context context, List<ResultadoLoteria> resultados, String corPadrao) {
        this.context = context;
        this.resultados = resultados;
        this.corPadrao = corPadrao;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.item_resultado_loteria, viewGroup, false);

        return new ResultadoLoteriaAdapter.ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ResultadoLoteria resultado = resultados.get(position);

        holder.tvQtdDezenas.setTextColor(Color.parseColor(corPadrao));
        holder.tvQtdDezenas.setText(resultado.getQtdDezenas() + " Dezenas");

        organizarInfos(holder, resultado);
    }

    @Override
    public int getItemCount() {
        return resultados.size();
    }

    private void organizarInfos(ViewHolder holder, ResultadoLoteria resultado){
        switch (resultado.getQtdGanhadores()){
            case 0:
                holder.tvQtdGanhadores.setText("Não houve");
                holder.llRateio.setVisibility(View.GONE);
                holder.llTotal.setVisibility(View.GONE);
                break;
            case 1:
                holder.tvQtdGanhadores.setText(Integer.toString(resultado.getQtdGanhadores()));
                holder.llRateio.setVisibility(View.GONE);
                holder.llTotal.setVisibility(View.VISIBLE);
                holder.tvValorTotal.setText(MoedaUtils.getValorMoedaReal(resultado.getValorTotal()));
                break;
            default:
                holder.tvQtdGanhadores.setText(Integer.toString(resultado.getQtdGanhadores()));
                holder.tvValorRateio.setText(MoedaUtils.getValorMoedaReal(resultado.getValorRateio()));
                holder.tvValorTotal.setText(MoedaUtils.getValorMoedaReal(resultado.getValorTotal()));
                holder.llRateio.setVisibility(View.VISIBLE);
                holder.llTotal.setVisibility(View.VISIBLE);
                break;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout llRateio, llTotal;
        TextView tvQtdDezenas, tvQtdGanhadores, tvValorRateio, tvValorTotal;

        ViewHolder(@NonNull View view) {
            super(view);

            llRateio = view.findViewById(R.id.llRateio);
            llTotal = view.findViewById(R.id.llTotal);
            tvQtdDezenas = view.findViewById(R.id.tvQtdDezenas);
            tvQtdGanhadores = view.findViewById(R.id.tvQtdGanhadores);
            tvValorRateio = view.findViewById(R.id.tvValorRateio);
            tvValorTotal = view.findViewById(R.id.tvValorTotal);
        }
    }

}
