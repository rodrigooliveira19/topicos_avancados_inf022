package br.com.domain.app.minhagelada.controller;

import android.content.Context;

import java.util.List;

import br.com.domain.app.minhagelada.daoDB.EstabelecimentoDao;
import br.com.domain.app.minhagelada.entidades.Estabelecimento;

public class EstabelecimentoController {

    private EstabelecimentoDao estabelecimentoDao;

    public EstabelecimentoController(Context context){
       this.estabelecimentoDao = new EstabelecimentoDao(context);
    }

    private Estabelecimento createEstabelecimento(String descricao, String localizacao){
        Estabelecimento estabelecimento;
        estabelecimento = new Estabelecimento();
        estabelecimento.setDescricao(descricao);
        estabelecimento.setLocalizacao(localizacao);
        return estabelecimento;

    }

    public boolean insert(String descricao, String localizacao){
        return this.estabelecimentoDao.insert(this.createEstabelecimento(descricao,localizacao));
    }

    public List<Estabelecimento> selectAll(){
        return this.estabelecimentoDao.selectAll();

    }

}
