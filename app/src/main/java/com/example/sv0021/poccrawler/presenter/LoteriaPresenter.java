package com.example.sv0021.poccrawler.presenter;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;

import com.example.sv0021.poccrawler.view.activity.LoteriaActivity;

public interface LoteriaPresenter {

    void onMontarViewPager(
            LoteriaActivity context,
            ViewPager viewPager
    );

    void onSetNavigationListener(
            ViewPager viewPager,
            BottomNavigationView bnvMenu
    );

}
