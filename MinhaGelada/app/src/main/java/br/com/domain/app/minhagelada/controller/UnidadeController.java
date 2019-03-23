package br.com.domain.app.minhagelada.controller;

import android.content.Context;

import java.util.List;

import br.com.domain.app.minhagelada.daoDB.UnidadeDao;
import br.com.domain.app.minhagelada.entidades.Unidade;

public class UnidadeController {

    private UnidadeDao unidadeDao;

    public UnidadeController(Context context){
       this.unidadeDao = new UnidadeDao(context);
    }

    private Unidade createUnidade(String descricao){
        Unidade unidade;
        unidade = new Unidade();
        unidade.setDescricao(descricao);
        return unidade;

    }

    public boolean insert(String descricao){
        return unidadeDao.insert(this.createUnidade(descricao));
    }

    public List<Unidade> selectAll(){
        return this.unidadeDao.selectAll();

    }

}
