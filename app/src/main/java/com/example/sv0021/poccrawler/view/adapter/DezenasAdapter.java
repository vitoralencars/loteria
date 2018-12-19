package com.example.sv0021.poccrawler.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sv0021.poccrawler.R;
import com.example.sv0021.poccrawler.enumeradores.TipoLoteria;
import com.example.sv0021.poccrawler.model.dto.LoteriaComum;

import java.util.Collections;
import java.util.List;

public class DezenasAdapter extends RecyclerView.Adapter<DezenasAdapter.ViewHolder>{

    private Context context;
    private LoteriaComum loteria;
    private List<Integer> dezenas;

    public  DezenasAdapter(Context context, LoteriaComum loteria, List<Integer> dezenas) {
        this.context = context;
        this.loteria = loteria;
        this.dezenas = dezenas;

        Collections.sort(this.dezenas);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.item_dezena_cartela, viewGroup, false);

        return new DezenasAdapter.ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String valor = dezenas.get(position) >= 10
                ? Integer.toString(dezenas.get(position)) : "0" + dezenas.get(position);

        if(valor.length() == 3){
            valor = Character.toString(valor.charAt(1)) + Character.toString(valor.charAt(2));
        }

        String corDezena, corBackground;
        if(loteria.getCodigoLoteria() != TipoLoteria.TIMEMANIA){
            corDezena = loteria.getCorSecundaria();
            corBackground = loteria.getCorPadrao();
        }else{
            corDezena = loteria.getCorPadrao();
            corBackground = loteria.getCorSecundaria();
        }
        holder.tvDezena.setTextColor(Color.parseColor(corDezena));
        holder.tvDezena.setText(valor);
        setBackground(holder, corBackground);

    }

    @Override
    public int getItemCount() {
        return dezenas.size();
    }

    private void setBackground(ViewHolder holder, String corBackground){
        GradientDrawable background = (GradientDrawable)holder.tvDezena.getBackground();
        background.setColor(Color.parseColor(corBackground));
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvDezena;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDezena = itemView.findViewById(R.id.tvDezena);
        }
    }
}
