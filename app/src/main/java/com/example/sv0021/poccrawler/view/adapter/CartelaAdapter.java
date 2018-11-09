package com.example.sv0021.poccrawler.view.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sv0021.poccrawler.R;
import com.example.sv0021.poccrawler.model.DezenaCartela;
import com.example.sv0021.poccrawler.view.activities.BaseCartelaActivity;

import java.util.List;

public class CartelaAdapter extends RecyclerView.Adapter<CartelaAdapter.ViewHolder>{

    private BaseCartelaActivity context;
    private List<DezenaCartela> dezenas;

    public CartelaAdapter(BaseCartelaActivity context, List<DezenaCartela> dezenas){
        this.context = context;
        this.dezenas = dezenas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.item_dezena_cartela, viewGroup, false);

        return new CartelaAdapter.ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        DezenaCartela dezena = dezenas.get(i);

        String dezenaCartela = dezena.getDezena() >= 10 ?
                Integer.toString(dezena.getDezena()) :
                "0" + dezena.getDezena();

        holder.tvDezena.setText(dezenaCartela);
        atualizarBackground(dezena, holder.tvDezena);
    }

    @Override
    public int getItemCount() {
        return dezenas.size();
    }

    public void atualizarBackground(DezenaCartela dezena, TextView tvDezena){
        GradientDrawable background = (GradientDrawable)tvDezena.getBackground();

        if(!dezena.isSelecionado()){
            tvDezena.setTextColor(ContextCompat.getColor(
                    context,
                    R.color.branco
            ));
            background.setColor(ContextCompat.getColor(
                    context,
                    R.color.verde_mega_sena
                    //dezena.getCorBackGround()
            ));
        }else{
            tvDezena.setTextColor(ContextCompat.getColor(
                    context,
                    R.color.preto
            ));
            background.setColor(ContextCompat.getColor(
                    context,
                    R.color.cinza
            ));
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvDezena;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDezena = itemView.findViewById(R.id.tvDezena);

            itemView.setOnClickListener(view -> context.onItemClick(
                    getAdapterPosition(), tvDezena));
        }
    }
}
