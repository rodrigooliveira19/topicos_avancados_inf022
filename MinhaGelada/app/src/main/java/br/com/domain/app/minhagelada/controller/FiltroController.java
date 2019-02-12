package br.com.domain.app.minhagelada.controller;

import android.content.Context;

import br.com.domain.app.minhagelada.daoDB.FiltroDao;
import br.com.domain.app.minhagelada.entidades.Filtro;

public class FiltroController {

    private FiltroDao filtroDao;

    public FiltroController(Context context){
       this.filtroDao = new FiltroDao(context);
    }

    private Filtro createUnidade(String descricao){
        Filtro filtro;
        filtro = new Filtro();
        filtro.setDescricao(descricao);
        return filtro;

    }

    public boolean insert(String descricao){
        return filtroDao.insert(this.createUnidade(descricao));
    }

}
