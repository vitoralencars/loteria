package com.example.sv0021.poccrawler.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sv0021.poccrawler.R;
import com.example.sv0021.poccrawler.model.Concurso;
import com.example.sv0021.poccrawler.model.JogoSalvo;
import com.example.sv0021.poccrawler.view.activity.LoteriaActivity;
import com.google.gson.Gson;

import java.util.List;

public class JogosSalvosAdapter extends RecyclerView.Adapter<JogosSalvosAdapter.ViewHolder>{

    private LoteriaActivity context;
    private Concurso concurso;
    private ConcursosAdapter concursosAdapter;

    public JogosSalvosAdapter(LoteriaActivity context, Concurso concurso, ConcursosAdapter concursosAdapter) {
        this.context = context;
        this.concurso = concurso;
        this.concursosAdapter = concursosAdapter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.item_jogo_salvo, viewGroup, false);

        return new JogosSalvosAdapter.ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        JogoSalvo jogo = concurso.getJogosSalvos().get(position);

        boolean concursoRealizado = concurso.getNumConcurso() <= context.getUltimoConcurso();
        if(concursoRealizado){
            holder.llEdicaoRemocao.setVisibility(View.GONE);
            holder.tvAcertos.setVisibility(View.VISIBLE);
        }else{
            holder.llEdicaoRemocao.setVisibility(View.VISIBLE);
            holder.tvAcertos.setVisibility(View.GONE);
        }

        DezenasAdapter adapter = new DezenasAdapter(
                context,
                context.getLoteria().getCorPadrao(),
                jogo.getDezenas()
        );

        holder.ivEditar.setOnClickListener(view -> editarJogoSalvo(jogo));
        holder.ivRemover.setOnClickListener(view -> removerJogoSalvo(position));

        holder.rvDezenas.setAdapter(adapter);

    }

    @Override
    public int getItemCount() {
        return concurso.getJogosSalvos().size();
    }

    private void editarJogoSalvo(JogoSalvo jogo){
        context.editarJogoSalvo(jogo.getIdJogo());
    }

    private void removerJogoSalvo(int position){
        int index = 0;
        List<Concurso> concursos = context.getConcursosSalvos();

        for(Concurso concurso : concursos){
            if(concurso.getNumConcurso() == concursos.get(index).getNumConcurso()){
                break;
            }
            index++;
        }

        concursos.get(index).getJogosSalvos().remove(position);

        if(concursos.get(index).getJogosSalvos().size() == 0){
            concursos.remove(index);
        }

        context.salvarJogo(new Gson().toJson(concursos));

        concursosAdapter.notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout llEdicaoRemocao;
        TextView tvAcertos;
        ImageView ivEditar, ivRemover;
        RecyclerView rvDezenas;

        ViewHolder(@NonNull View view) {
            super(view);

            llEdicaoRemocao = view.findViewById(R.id.llEdicaoRemocao);
            tvAcertos = view.findViewById(R.id.tvAcertos);
            ivEditar = view.findViewById(R.id.ivEditar);
            ivRemover = view.findViewById(R.id.ivRemover);
            rvDezenas = view.findViewById(R.id.rvDezenas);
        }
    }
}
