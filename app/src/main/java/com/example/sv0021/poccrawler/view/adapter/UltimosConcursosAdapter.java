package com.example.sv0021.poccrawler.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sv0021.poccrawler.R;
import com.example.sv0021.poccrawler.model.dto.LoteriaResponse;
import com.example.sv0021.poccrawler.util.MoedaUtils;
import com.example.sv0021.poccrawler.util.recyclerview.ItemClickListener;
import com.example.sv0021.poccrawler.view.activity.UltimosConcursosActivity;

import java.util.List;

import static com.example.sv0021.poccrawler.util.DataUtils.getDataFormatada;

public class UltimosConcursosAdapter extends RecyclerView.Adapter<UltimosConcursosAdapter.ViewHolder>{

    private UltimosConcursosActivity context;
    private List<LoteriaResponse> loterias;
    private ItemClickListener itemClickListener;

    public UltimosConcursosAdapter(UltimosConcursosActivity context, List<LoteriaResponse> loterias) {
        this.context = context;
        this.loterias = loterias;
        this.itemClickListener = context;
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

        holder.tvNomeLoteria.setText(loteria.getNomeLoteria());
        holder.tvDataConcurso.setText(getDataFormatada(loteria.getDataSorteio()));
        holder.tvNumeroConcurso.setText(Integer.toString(loteria.getConcurso()));
        holder.tvValorEstimado.setText(MoedaUtils.getValorMoedaReal(loteria.getEstimativaPremio()));
        holder.tvDataProximoSorteio.setText(getDataFormatada(loteria.getProximoSorteio()));

        setInformacoesGanhadores(holder, loteria.getGanhadores().get(0));
        listarDezenasSorteadas(holder, loteria);
        setCaracteristicasLoteria(holder, loteria);
    }

    @Override
    public int getItemCount() {
        return loterias.size();
    }

    private void setCaracteristicasLoteria(ViewHolder holder, LoteriaResponse loteria){
        switch (loteria.getCodigoLoteria()){
            case 1:
                pintarCampos(holder, loteria.getCorPadrao());
                holder.ivIconeLoteria.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_megasena));
                break;
            case 2:
                pintarCampos(holder, loteria.getCorPadrao());
                holder.ivIconeLoteria.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_lotofacil));
                break;
            case 3:
                pintarCampos(holder, loteria.getCorPadrao());
                holder.ivIconeLoteria.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_quina));
                break;
            case 4:
                pintarCampos(holder, loteria.getCorPadrao());
                holder.ivIconeLoteria.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_lotomania));
                break;
        }
    }

    private void pintarCampos(ViewHolder holder, String corPadrao){
        holder.viewTop.setBackgroundColor(Color.parseColor(corPadrao));
        holder.viewBottom.setBackgroundColor(Color.parseColor(corPadrao));
        holder.tvNomeLoteria.setTextColor(Color.parseColor(corPadrao));
        holder.tvDataConcurso.setTextColor(Color.parseColor(corPadrao));
        holder.tvNumeroConcurso.setTextColor(Color.parseColor(corPadrao));
        holder.tvGanhadores.setTextColor(Color.parseColor(corPadrao));
        holder.tvValorEstimado.setTextColor(Color.parseColor(corPadrao));
    }

    private void setInformacoesGanhadores(ViewHolder holder, int numGanhadores){
        switch (numGanhadores){
            case 0:
                holder.tvGanhadores.setText(context.getResources()
                        .getString(R.string.ultimos_concursos_acumulou));
                break;
            case 1:
                holder.tvGanhadores.setText(context.getResources()
                        .getString(R.string.ultimos_concursos_um_ganhador));
                break;
            default:
                holder.tvGanhadores.setText(context.getResources()
                        .getString(R.string.ultimos_concursos_multiplos_ganhadores,
                                Integer.toString(numGanhadores)));
                break;
        }
    }

    private void listarDezenasSorteadas(ViewHolder holder, LoteriaResponse loteria){

        DezenasAdapter adapter = new DezenasAdapter(
                context,
                loteria.getCorPadrao(),
                loteria.getDezenas()
        );

        holder.rvSorteio.setLayoutManager(loteria.getDezenas().size() > 5
                ? new GridLayoutManager(context, 6)
                : new GridLayoutManager(context, loteria.getDezenas().size()));

        holder.rvSorteio.setAdapter(adapter);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onItemClick(getAdapterPosition());
        }
    }
}
