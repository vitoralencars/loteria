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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sv0021.poccrawler.R;
import com.example.sv0021.poccrawler.implement.JogosSalvosImpl;
import com.example.sv0021.poccrawler.model.Concurso;
import com.example.sv0021.poccrawler.model.dto.LoteriaComum;
import com.example.sv0021.poccrawler.util.DataUtils;
import com.example.sv0021.poccrawler.view.activity.LoteriaActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ConcursosAdapter extends RecyclerView.Adapter<ConcursosAdapter.ViewHolder>{

    private LoteriaActivity context;
    private List<Concurso> concursos;

    public ConcursosAdapter(LoteriaActivity context) {
        this.context = context;
        this.concursos = context.getConcursosSalvos();
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
        Concurso concurso = concursos.get(position);

        customizarProgressBar(holder.progressConferir);

        holder.rlConferir.setVisibility(concurso.getNumConcurso() < context.getUltimoConcurso() + 1 ?
                View.VISIBLE : View.GONE);

        holder.btnConferir.setBackgroundColor(Color.parseColor(context.getLoteria().getCorPadrao()));
        holder.tvConcurso.setTextColor(Color.parseColor(context.getLoteria().getCorPadrao()));
        holder.tvConcurso.setText(Integer.toString(concurso.getNumConcurso()));
        holder.tvDataConcurso.setText("(" + DataUtils.getDataFormatada(concurso.getData()) + ")");

        JogosSalvosAdapter jogosAdapter = new JogosSalvosAdapter(context, concurso, this);
        holder.rvJogosSalvos.setAdapter(jogosAdapter);

        if(concurso.getResultadoSorteio() != null && concurso.getResultadoSorteio().length > 0) {
            holder.btnConferir.setVisibility(View.GONE);
        }else{
            holder.btnConferir.setVisibility(View.VISIBLE);
            holder.btnConferir.setOnClickListener(view -> conferirResultados(holder, position));
        }

        if(concurso.getResultadoSorteio() != null){
            exibirResultadoFinal(holder, concurso);
        }else{
            holder.llResultado.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return concursos.size();
    }

    void atualizarConcursos(){
        this.concursos = context.getConcursosSalvos();
        notifyDataSetChanged();
    }

    private void customizarProgressBar(ProgressBar progressBar){
        progressBar.getIndeterminateDrawable().setColorFilter(
                Color.parseColor(context.getLoteria().getCorPadrao()),
                PorterDuff.Mode.SRC_IN
        );
    }

    private void conferirResultados(ViewHolder holder, int posConcurso){
        holder.btnConferir.setVisibility(View.GONE);
        holder.progressConferir.setVisibility(View.VISIBLE);

        JogosSalvosImpl impl = new JogosSalvosImpl();
        impl.onConferirResultados(
                context,
                concursos.get(posConcurso).getNumConcurso(),
                posConcurso,
                this,
                holder.progressConferir);
    }

    public void setResultado(LoteriaComum response, int posConcurso){
        int[] resultadoLoteria = new int[response.getDezenas().size()];

        for(int i = 0; i < resultadoLoteria.length; i++){
            resultadoLoteria[i] = response.getDezenas().get(i);
        }

        concursos.get(posConcurso).setResultadoSorteio(resultadoLoteria);

        context.salvarJogo(new Gson().toJson(concursos));

        notifyDataSetChanged();
    }

    private void exibirResultadoFinal(ViewHolder holder, Concurso concurso){
        List<Integer> resultado = new ArrayList<>();
        for(Integer i : concurso.getResultadoSorteio()){
            resultado.add(i);
        }

        DezenasAdapter adapter = new DezenasAdapter(
                context,
                context.getLoteria(),
                resultado
        );

        holder.rvDezenasResultado.setAdapter(adapter);
        holder.llResultado.setVisibility(View.VISIBLE);
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout llResultado;
        RelativeLayout rlConferir;
        ProgressBar progressConferir;
        Button btnConferir;
        TextView tvConcurso, tvDataConcurso;
        RecyclerView rvDezenasResultado, rvJogosSalvos;

        ViewHolder(@NonNull View v) {
            super(v);

            llResultado = v.findViewById(R.id.llResultado);
            rlConferir = v.findViewById(R.id.rlConferir);
            progressConferir = v.findViewById(R.id.progressConferir);
            btnConferir = v.findViewById(R.id.btnConferir);
            tvConcurso = v.findViewById(R.id.tvConcurso);
            tvDataConcurso = v.findViewById(R.id.tvDataConcurso);
            rvDezenasResultado = v.findViewById(R.id.rvDezenasResultado);
            rvJogosSalvos = v.findViewById(R.id.rvJogosSalvos);
        }
    }
}
