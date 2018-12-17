package com.example.sv0021.poccrawler.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.sv0021.poccrawler.R;
import com.example.sv0021.poccrawler.implement.TimesImpl;
import com.example.sv0021.poccrawler.util.Constants;
import com.example.sv0021.poccrawler.util.recyclerview.ItemClickListener;

import java.util.List;

public class TimesActivity extends BaseActivity implements ItemClickListener {

    private RecyclerView rvTimes;
    private List<String> times;

    private TimesImpl impl = new TimesImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_times);
        initView();
        listarTimes();
    }

    private void initView(){
        rvTimes = findViewById(R.id.rvTimes);
    }

    private void listarTimes(){
        impl.onListarTimes(this, rvTimes);
    }

    public void setTimes(List<String> times) {
        this.times = times;
    }

    @Override
    public void onItemClick(int position) {
        String time = times.get(position);

        Intent intent = new Intent();
        intent.putExtra(Constants.EXTRA_TIME_CORACAO, time);
        setResult(RESULT_OK, intent);

        TimesActivity.this.finish();
    }
}
