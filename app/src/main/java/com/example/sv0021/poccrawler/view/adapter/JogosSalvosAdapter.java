package com.example.sv0021.poccrawler.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sv0021.poccrawler.R;
import com.example.sv0021.poccrawler.application.LoteriasApplication;
import com.example.sv0021.poccrawler.model.JogoSalvo;
import com.example.sv0021.poccrawler.view.activity.LoteriaActivity;
import com.google.gson.Gson;

import java.util.List;

public class JogosSalvosAdapter extends RecyclerView.Adapter<JogosSalvosAdapter.ViewHolder>{

    private LoteriaActivity context;
    private List<JogoSalvo> jogosSalvos;

    public JogosSalvosAdapter(LoteriaActivity context) {
        this.context = context;
        this.jogosSalvos = context.getJogosSalvos();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.item_jogo_salvo, viewGroup, false);

        return new JogosSalvosAdapter.ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        JogoSalvo jogo = jogosSalvos.get(i);

        holder.tvConcurso.setText("Concurso: " + jogo.getConcurso());

        if(jogo.getConcurso() > context.getUltimoConcurso()){
            holder.tvAcertos.setText("Aguardando\nsorteio");
            holder.tvAcertos.setVisibility(View.VISIBLE);
            holder.ivEditar.setVisibility(View.VISIBLE);
        }else{
            holder.tvAcertos.setVisibility(View.GONE);
            holder.ivEditar.setVisibility(View.GONE);
        }

        DezenasAdapter adapter = new DezenasAdapter(
                context,
                context.getLoteria().getCorPadrao(),
                jogo.getDezenas()
        );

        holder.ivRemover.setOnClickListener(view -> removerJogoSalvo(i));

        holder.rvDezenas.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return jogosSalvos.size();
    }

    private void removerJogoSalvo(int position){
        jogosSalvos.remove(position);

        String key = LoteriasApplication.getPreferenceKey(context.getLoteria().getCodigoLoteria());
        String jsonJogos = new Gson().toJson(jogosSalvos);
        LoteriasApplication.savePreferences(key, jsonJogos);

        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvConcurso, tvAcertos;
        ImageView ivEditar, ivRemover;
        RecyclerView rvDezenas;

        ViewHolder(@NonNull View view) {
            super(view);

            tvConcurso = view.findViewById(R.id.tvConcurso);
            tvAcertos = view.findViewById(R.id.tvAcertos);
            ivEditar = view.findViewById(R.id.ivEditar);
            ivRemover = view.findViewById(R.id.ivRemover);
            rvDezenas = view.findViewById(R.id.rvDezenas);
        }
    }
}
