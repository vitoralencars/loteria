package com.example.sv0021.poccrawler.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sv0021.poccrawler.R;
import com.example.sv0021.poccrawler.view.activity.TimesActivity;

import java.util.List;

public class TimesAdapter extends RecyclerView.Adapter<TimesAdapter.ViewHolder>{

    private TimesActivity context;
    private List<String> times;

    public TimesAdapter(TimesActivity context, List<String> times) {
        this.context = context;
        this.times = times;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.item_time, viewGroup, false);

        return new TimesAdapter.ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTime.setText(times.get(position));
    }

    @Override
    public int getItemCount() {
        return times.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvTime;

        ViewHolder(@NonNull View v) {
            super(v);

            tvTime = v.findViewById(R.id.tvTime);
        }
    }
}
