package br.com.domain.app.minhagelada.controller;

import android.content.Context;

import br.com.domain.app.minhagelada.daoDB.CestaDao;
import br.com.domain.app.minhagelada.entidades.Cesta;

public class CestaController {

    private CestaDao cestaDao;

    public CestaController(Context context){
        this.cestaDao = new CestaDao(context);
    }

    private Cesta createCesta(String descricao){
        Cesta cesta;
        cesta = new Cesta();
        cesta.setDescricao(descricao);
        return cesta;
    }

    public boolean insert(String descricao){
        return this.cestaDao.insert(this.createCesta(descricao));
    }
}
