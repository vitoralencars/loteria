package com.example.sv0021.poccrawler.implement;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.example.sv0021.poccrawler.R;
import com.example.sv0021.poccrawler.application.LoteriasApplication;
import com.example.sv0021.poccrawler.enumeradores.TipoLoteria;
import com.example.sv0021.poccrawler.model.JogoSalvo;
import com.example.sv0021.poccrawler.presenter.LoteriaPresenter;
import com.example.sv0021.poccrawler.util.Constants;
import com.example.sv0021.poccrawler.view.activity.LoteriaActivity;
import com.example.sv0021.poccrawler.view.fragment.CartelaFragment;
import com.example.sv0021.poccrawler.view.fragment.JogosSalvosFragment;
import com.example.sv0021.poccrawler.view.fragment.ResultadosLoteriaFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class LoteriaImpl implements LoteriaPresenter {

    @Override
    public void onSetPrimeiroFragment(LoteriaActivity context) {
        atualizarFrameLayout(context, new ResultadosLoteriaFragment());
    }

    @Override
    public void onSetNavigationListener(LoteriaActivity context, BottomNavigationView bnvMenu) {
        bnvMenu.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()){
                case R.id.menu_concurso:
                    bnvMenu.getMenu().getItem(0).setChecked(true);
                    atualizarFrameLayout(context, new ResultadosLoteriaFragment());
                    break;
                case R.id.menu_cartela:
                    bnvMenu.getMenu().getItem(1).setChecked(true);
                    atualizarFrameLayout(context, new CartelaFragment());
                    break;
                case R.id.menu_jogos_salvos:
                    bnvMenu.getMenu().getItem(2).setChecked(true);
                    atualizarFrameLayout(context, new JogosSalvosFragment());
                    break;
            }
            return false;
        });
    }

    @Override
    public List<JogoSalvo> onGetJogosSalvos(int tipoLoteria) {

        String key = "";
        switch (tipoLoteria){
            case TipoLoteria.MEGA_SENA:
                key = Constants.SHARED_PREFS_JOGOS_MEGA_SENA;
                break;
            case TipoLoteria.LOTOFACIL:
                key = Constants.SHARED_PREFS_JOGOS_LOTOFACIL;
                break;
            case TipoLoteria.QUINA:
                key = Constants.SHARED_PREFS_JOGOS_QUINA;
                break;
            case TipoLoteria.LOTOMANIA:
                key = Constants.SHARED_PREFS_JOGOS_LOTOMANIA;
                break;
        }

        String json = LoteriasApplication.getPreferences(key);
        if(json.isEmpty()){
            return new ArrayList<>();
        }else{
            Type type = new TypeToken<List<JogoSalvo>>() {}.getType();
            List<JogoSalvo> jogoSalvos = new Gson().fromJson(json, type);
            Collections.sort(jogoSalvos, (o1, o2) -> o2.getConcurso() - o1.getConcurso());

            return jogoSalvos;
        }
    }

    private void atualizarFrameLayout(LoteriaActivity context, Fragment fragment){
        FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.flFragments, fragment);
        transaction.commit();
    }
}
