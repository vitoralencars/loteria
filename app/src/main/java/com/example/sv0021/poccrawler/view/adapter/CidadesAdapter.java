package com.example.sv0021.poccrawler.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sv0021.poccrawler.R;
import com.example.sv0021.poccrawler.view.activity.LoteriaActivity;


public class CidadesAdapter extends RecyclerView.Adapter<CidadesAdapter.ViewHolder>{

    private LoteriaActivity context;

    public CidadesAdapter(LoteriaActivity context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.item_cidade_ganhador, viewGroup, false);

        return new CidadesAdapter.ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String cidade = context.getLoteria().getCidades().get(position);
        String estado = context.getLoteria().getEstados().get(position);

        holder.tvCidade.setText(cidade + "/" + estado);

    }

    @Override
    public int getItemCount() {
        return context.getLoteria().getCidades().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvCidade;

        ViewHolder(@NonNull View v) {
            super(v);

            tvCidade = v.findViewById(R.id.tvCidade);
        }
    }
}
