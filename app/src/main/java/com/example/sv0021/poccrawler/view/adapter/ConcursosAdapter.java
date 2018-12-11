package com.example.sv0021.poccrawler.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sv0021.poccrawler.R;
import com.example.sv0021.poccrawler.implement.JogosSalvosImpl;
import com.example.sv0021.poccrawler.model.Concurso;
import com.example.sv0021.poccrawler.model.dto.LoteriaResponse;
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

        customizarProgressBar(holder.progressConferir);

        holder.rlConferir.setVisibility(concurso.getNumConcurso() < context.getUltimoConcurso() + 1 ?
                View.VISIBLE : View.GONE);

        holder.btnConferir.setBackgroundColor(Color.parseColor(context.getLoteria().getCorPadrao()));
        holder.tvConcurso.setTextColor(Color.parseColor(context.getLoteria().getCorPadrao()));
        holder.tvConcurso.setText(Integer.toString(concurso.getNumConcurso()));

        JogosSalvosAdapter jogosAdapter = new JogosSalvosAdapter(context, concurso, this);
        holder.rvJogosSalvos.setAdapter(jogosAdapter);

        holder.btnConferir.setOnClickListener(view -> conferirResultados(holder, concurso.getNumConcurso()));

    }

    @Override
    public int getItemCount() {
        return context.getConcursosSalvos().size();
    }

    private void customizarProgressBar(ProgressBar progressBar){
        progressBar.getIndeterminateDrawable().setColorFilter(
                Color.parseColor(context.getLoteria().getCorPadrao()),
                PorterDuff.Mode.SRC_IN
        );
    }

    private void conferirResultados(ViewHolder holder, int codConcurso){
        holder.btnConferir.setVisibility(View.GONE);
        holder.progressConferir.setVisibility(View.VISIBLE);

        JogosSalvosImpl impl = new JogosSalvosImpl();
        impl.onConferirResultados(context, codConcurso, this, holder.progressConferir);
    }

    public void setResultado(LoteriaResponse response){
        int[] resultadoLoteria = new int[response.getDezenas().size()];

        for(int i = 0; i < resultadoLoteria.length; i++){
            resultadoLoteria[i] = response.getDezenas().get(i);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout rlConferir;
        ProgressBar progressConferir;
        Button btnConferir;
        TextView tvConcurso;
        RecyclerView rvJogosSalvos;

        ViewHolder(@NonNull View v) {
            super(v);

            rlConferir = v.findViewById(R.id.rlConferir);
            progressConferir = v.findViewById(R.id.progressConferir);
            btnConferir = v.findViewById(R.id.btnConferir);
            tvConcurso = v.findViewById(R.id.tvConcurso);
            rvJogosSalvos = v.findViewById(R.id.rvJogosSalvos);
        }
    }
}
