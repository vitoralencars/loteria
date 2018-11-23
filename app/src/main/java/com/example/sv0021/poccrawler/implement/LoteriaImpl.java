package com.example.sv0021.poccrawler.implement;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.example.sv0021.poccrawler.R;
import com.example.sv0021.poccrawler.presenter.LoteriaPresenter;
import com.example.sv0021.poccrawler.view.activity.LoteriaActivity;
import com.example.sv0021.poccrawler.view.adapter.ViewPagerAdapter;
import com.example.sv0021.poccrawler.view.fragment.CartelaFragment;
import com.example.sv0021.poccrawler.view.fragment.ResultadosLoteriaFragment;

import java.util.ArrayList;
import java.util.List;

public class LoteriaImpl implements LoteriaPresenter {

    @Override
    public void onMontarViewPager(LoteriaActivity context, ViewPager viewPager) {

        List<CharSequence> titulosFragments = new ArrayList<>();
        titulosFragments.add("Concursos");
        titulosFragments.add("Cartela");

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new ResultadosLoteriaFragment());
        fragments.add(new CartelaFragment());

        ViewPagerAdapter adapter = new ViewPagerAdapter(
                context.getSupportFragmentManager(),
                titulosFragments,
                fragments
        );

        viewPager.setAdapter(adapter);
    }

    @Override
    public void onSetNavigationListener(ViewPager viewPager, BottomNavigationView bnvMenu) {
        viewPager.addOnPageChangeListener(viewPagerListener(bnvMenu));
        bnvMenu.setOnNavigationItemSelectedListener(bnvMenuListener(viewPager));
    }

    private ViewPager.OnPageChangeListener viewPagerListener(BottomNavigationView bnvMenu){
        return new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                bnvMenu.getMenu().getItem(i).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        };
    }

    private BottomNavigationView.OnNavigationItemSelectedListener bnvMenuListener(ViewPager viewPager){
        return menuItem -> {
            switch (menuItem.getItemId()){
                case R.id.menu_concurso:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.menu_cartela:
                    viewPager.setCurrentItem(1);
                    break;
            }

            return false;
        };
    }

}
