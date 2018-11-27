package com.example.sv0021.poccrawler.presenter;

import android.support.design.widget.BottomNavigationView;
import android.widget.FrameLayout;

import com.example.sv0021.poccrawler.model.JogoSalvo;
import com.example.sv0021.poccrawler.view.activity.LoteriaActivity;

import java.util.List;

public interface LoteriaPresenter {

    void onSetPrimeiroFragment(
            LoteriaActivity context
    );

    void onSetNavigationListener(
            LoteriaActivity context,
            BottomNavigationView bnvMenu,
            FrameLayout frameLayout
    );

    List<JogoSalvo> onGetJogosSalvos(
            int tipoLoteria
    );
}
