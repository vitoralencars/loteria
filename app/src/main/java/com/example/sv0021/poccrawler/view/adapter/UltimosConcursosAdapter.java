package com.example.sv0021.poccrawler.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sv0021.poccrawler.R;
import com.example.sv0021.poccrawler.model.dto.LoteriaResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UltimosConcursosAdapter extends RecyclerView.Adapter<UltimosConcursosAdapter.ViewHolder>{

    private Context context;
    private List<LoteriaResponse> loterias;

    public UltimosConcursosAdapter(Context context, List<LoteriaResponse> loterias) {
        this.context = context;
        this.loterias = loterias;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.item_lista_loteria, viewGroup, false);

        return new UltimosConcursosAdapter.ViewHolder(row);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LoteriaResponse loteria = loterias.get(position);

        holder.ivIconeLoteria.setBackground(loteria.getIcLoteria());
        holder.tvNomeLoteria.setText(loteria.getNomeLoteria());
        holder.tvDataConcurso.setText(getDataFormatada(loteria.getData()));
        holder.tvNumeroConcurso.setText(Integer.toString(loteria.getNumero()));
        holder.tvDataProximoSorteio.setText(context.getResources().getString(
                R.string.ultimos_concursos_proximo_sorteio,
                getDataFormatada(loteria.getProximoData()))
        );

        setCaracteristicasLoteria(holder, loteria);
    }

    @Override
    public int getItemCount() {
        return loterias.size();
    }

    @SuppressLint("SimpleDateFormat")
    private String getDataFormatada(Date data){
        return new SimpleDateFormat("dd/MM/yyyy").format(data);
    }

    private void setCaracteristicasLoteria(ViewHolder holder, LoteriaResponse loteria){
        switch (loteria.getNomeLoteria()){
            case "mega-sena":
                pintarCampos(holder, loteria.getCorPadrao());
                loteria.setIcLoteria(ContextCompat.getDrawable(context, R.drawable.ic_megasena));
                break;
            case "quina":
                pintarCampos(holder, loteria.getCorPadrao());
                loteria.setIcLoteria(ContextCompat.getDrawable(context, R.drawable.ic_megasena));
                break;
            case "lotofacil":
                pintarCampos(holder, loteria.getCorPadrao());
                loteria.setIcLoteria(ContextCompat.getDrawable(context, R.drawable.ic_megasena));
                break;
            case "lotomania:":
                pintarCampos(holder, loteria.getCorPadrao());
                loteria.setIcLoteria(ContextCompat.getDrawable(context, R.drawable.ic_megasena));
                break;
        }
    }

    private void pintarCampos(ViewHolder holder, @ColorRes int corPadrao){
        holder.viewTop.setBackgroundColor(ContextCompat.getColor(context, corPadrao));
        holder.viewBottom.setBackgroundColor(ContextCompat.getColor(context, corPadrao));
        holder.tvNomeLoteria.setTextColor(ContextCompat.getColor(context, corPadrao));
        holder.tvDataConcurso.setTextColor(ContextCompat.getColor(context, corPadrao));
        holder.tvNumeroConcurso.setTextColor(ContextCompat.getColor(context, corPadrao));
        holder.tvGanhadores.setTextColor(ContextCompat.getColor(context, corPadrao));
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        View viewTop, viewBottom;
        ImageView ivIconeLoteria;
        TextView tvNomeLoteria, tvNumeroConcurso, tvDataConcurso, tvGanhadores, tvValorEstimado,
                tvDataProximoSorteio;
        RecyclerView rvSorteio;

        ViewHolder(@NonNull View view) {
            super(view);

            viewTop = view.findViewById(R.id.viewTop);
            viewBottom = view.findViewById(R.id.viewBottom);
            ivIconeLoteria = view.findViewById(R.id.ivIconeLoteria);
            tvNomeLoteria = view.findViewById(R.id.tvNomeLoteria);
            tvNumeroConcurso = view.findViewById(R.id.tvNumeroConcurso);
            tvDataConcurso = view.findViewById(R.id.tvDataConcurso);
            tvGanhadores = view.findViewById(R.id.tvGanhadores);
            tvValorEstimado = view.findViewById(R.id.tvValorEstimado);
            tvDataProximoSorteio = view.findViewById(R.id.tvDataProximoSorteio);
            rvSorteio = view.findViewById(R.id.rvSorteio);
        }
    }
}
