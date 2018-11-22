package com.example.sv0021.poccrawler.implement;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.sv0021.poccrawler.presenter.LoteriaPresenter;
import com.example.sv0021.poccrawler.view.activities.LoteriaActivity;
import com.example.sv0021.poccrawler.view.adapter.ViewPagerAdapter;
import com.example.sv0021.poccrawler.view.fragment.ResultadosLoteriaFragment;

import java.util.ArrayList;
import java.util.List;

public class LoteriaImpl implements LoteriaPresenter {

    @Override
    public void onMontarViewPager(LoteriaActivity context, ViewPager viewPager) {

        List<CharSequence> titulosFragments = new ArrayList<>();
        titulosFragments.add("Concursos");

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new ResultadosLoteriaFragment());

        ViewPagerAdapter adapter = new ViewPagerAdapter(
                context.getSupportFragmentManager(),
                titulosFragments,
                fragments
        );

        viewPager.setAdapter(adapter);
    }
}
