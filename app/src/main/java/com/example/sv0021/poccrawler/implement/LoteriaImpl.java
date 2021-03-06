package com.example.sv0021.poccrawler.implement;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.example.sv0021.poccrawler.R;
import com.example.sv0021.poccrawler.application.LoteriasApplication;
import com.example.sv0021.poccrawler.enumeradores.TipoLoteria;
import com.example.sv0021.poccrawler.model.Concurso;
import com.example.sv0021.poccrawler.presenter.LoteriaPresenter;
import com.example.sv0021.poccrawler.util.Constants;
import com.example.sv0021.poccrawler.view.activity.LoteriaActivity;
import com.example.sv0021.poccrawler.view.fragment.CartelaFragment;
import com.example.sv0021.poccrawler.view.fragment.JogosSalvosFragment;
import com.example.sv0021.poccrawler.view.fragment.BaseResultadosLoteriaFragment;
import com.example.sv0021.poccrawler.view.fragment.ResultadosMegasenaFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class LoteriaImpl implements LoteriaPresenter {

    @Override
    public void onSetPrimeiroFragment(LoteriaActivity context) {
        setFragmentResultados(context);
    }

    @Override
    public void onSetNavigationListener(LoteriaActivity context, BottomNavigationView bnvMenu) {
        bnvMenu.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()){
                case R.id.menu_concurso:
                    bnvMenu.getMenu().getItem(0).setChecked(true);
                    setFragmentResultados(context);
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
    public List<Concurso> onGetConcursosSalvos(int codLoteria) {

        String key = "";
        switch (codLoteria){
            case TipoLoteria.MEGASENA:
                key = Constants.SHARED_PREFS_JOGOS_MEGASENA;
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
            case TipoLoteria.TIMEMANIA:
                key = Constants.SHARED_PREFS_JOGOS_TIMEMANIA;
                break;
            case TipoLoteria.DUPLASENA:
                key = Constants.SHARED_PREFS_JOGOS_DUPLASENA;
                break;
        }

        String json = LoteriasApplication.getPreferences(key);
        if(json.isEmpty()){
            return new ArrayList<>();
        }else{
            Type type = new TypeToken<List<Concurso>>() {}.getType();
            List<Concurso> concursos = new Gson().fromJson(json, type);
            Collections.sort(concursos, (o1, o2) -> o2.getNumConcurso() - o1.getNumConcurso());

            return concursos;
        }
    }

    private void setFragmentResultados(LoteriaActivity context){
        switch (context.getLoteria().getCodigoLoteria()){
            case TipoLoteria.MEGASENA:
                atualizarFrameLayout(context, new ResultadosMegasenaFragment());
                break;
            case TipoLoteria.LOTOFACIL:
                break;
            case TipoLoteria.QUINA:
                break;
            case TipoLoteria.LOTOMANIA:
                break;
            case TipoLoteria.TIMEMANIA:
                break;
            case TipoLoteria.DUPLASENA:
                break;
        }
    }

    private void atualizarFrameLayout(LoteriaActivity context, Fragment fragment){
        FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.flFragments, fragment);
        transaction.commit();
    }
}
