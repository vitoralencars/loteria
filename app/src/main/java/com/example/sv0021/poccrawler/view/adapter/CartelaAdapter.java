package com.example.sv0021.poccrawler.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sv0021.poccrawler.R;
import com.example.sv0021.poccrawler.enumeradores.TipoLoteria;
import com.example.sv0021.poccrawler.model.DezenaCartela;
import com.example.sv0021.poccrawler.model.dto.LoteriaResponse;
import com.example.sv0021.poccrawler.view.activity.LoteriaActivity;
import com.example.sv0021.poccrawler.view.fragment.CartelaFragment;

import java.util.List;

public class CartelaAdapter extends RecyclerView.Adapter<CartelaAdapter.ViewHolder>{

    private LoteriaActivity context;
    private CartelaFragment fragment;
    private List<DezenaCartela> dezenas;

    public CartelaAdapter(LoteriaActivity context, CartelaFragment fragment, List<DezenaCartela> dezenas){
        this.context = context;
        this.fragment = fragment;
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

        if(dezenaCartela.length() == 3){
            dezenaCartela = Character.toString(dezenaCartela.charAt(1)) + Character.toString(dezenaCartela.charAt(2));
        }

        String corDezena;
        LoteriaResponse loteria = context.getLoteria();
        if(loteria.getCodigoLoteria() != TipoLoteria.TIMEMANIA){
            corDezena = loteria.getCorSecundaria();
        }else{
            corDezena = loteria.getCorPadrao();
        }
        holder.tvDezena.setTextColor(Color.parseColor(corDezena));
        holder.tvDezena.setText(dezenaCartela);
        atualizarBackground(dezena, holder.tvDezena, corDezena);
    }

    @Override
    public int getItemCount() {
        return dezenas.size();
    }

    public void atualizarBackground(DezenaCartela dezena, TextView tvDezena, String corDezena){
        GradientDrawable background = (GradientDrawable)tvDezena.getBackground();

        if(!dezena.isSelecionado()){
            tvDezena.setTextColor(Color.parseColor(corDezena));
            background.setAlpha(127);
        }else{
            background.setAlpha(255);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvDezena;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDezena = itemView.findViewById(R.id.tvDezena);

            itemView.setOnClickListener(view -> fragment.onItemClick(
                    getAdapterPosition(), tvDezena));
        }
    }
}
