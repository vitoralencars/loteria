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

import java.util.Collections;
import java.util.List;

public class DezenasSorteadasAdapter extends RecyclerView.Adapter<DezenasSorteadasAdapter.ViewHolder>{

    private Context context;
    private String corPadrao;
    private List<Integer> dezenas;

    public DezenasSorteadasAdapter(Context context, String corPadrao, List<Integer> dezenas) {
        this.context = context;
        this.corPadrao = corPadrao;
        this.dezenas = dezenas;

        Collections.sort(this.dezenas);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.item_dezena_cartela, viewGroup, false);

        return new DezenasSorteadasAdapter.ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String valor = dezenas.get(position) >= 10
                ? Integer.toString(dezenas.get(position)) : "0" + dezenas.get(position);

        holder.tvDezena.setText(valor);
        setBackground(holder);

    }

    @Override
    public int getItemCount() {
        return dezenas.size();
    }

    private void setBackground(ViewHolder holder){
        GradientDrawable background = (GradientDrawable)holder.tvDezena.getBackground();
        background.setColor(Color.parseColor(corPadrao));
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvDezena;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDezena = itemView.findViewById(R.id.tvDezena);
        }
    }
}
