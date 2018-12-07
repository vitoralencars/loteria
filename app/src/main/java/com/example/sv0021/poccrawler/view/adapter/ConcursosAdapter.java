package com.example.sv0021.poccrawler.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sv0021.poccrawler.R;
import com.example.sv0021.poccrawler.model.Concurso;
import com.example.sv0021.poccrawler.view.activity.LoteriaActivity;

public class ConcursosAdapter extends RecyclerView.Adapter<ConcursosAdapter.ViewHolder>{

    private LoteriaActivity context;

    public ConcursosAdapter(LoteriaActivity context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.item_concurso_salvo, viewGroup, false);

        return new ConcursosAdapter.ViewHolder(row);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Concurso concurso = context.getConcursosSalvos().get(position);

        holder.tvConcurso.setTextColor(Color.parseColor(context.getLoteria().getCorPadrao()));
        holder.tvConcurso.setText(Integer.toString(concurso.getNumConcurso()));

        JogosSalvosAdapter adapter = new JogosSalvosAdapter(context, concurso, this);
        holder.rvJogosSalvos.setAdapter(adapter);

    }

    @Override
    public int getItemCount() {
        return context.getConcursosSalvos().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvConcurso;
        RecyclerView rvJogosSalvos;

        ViewHolder(@NonNull View v) {
            super(v);

            tvConcurso = v.findViewById(R.id.tvConcurso);
            rvJogosSalvos = v.findViewById(R.id.rvJogosSalvos);
        }
    }
}
