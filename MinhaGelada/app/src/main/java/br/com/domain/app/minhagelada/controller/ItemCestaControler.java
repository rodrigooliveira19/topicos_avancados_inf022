package br.com.domain.app.minhagelada.controller;

import android.content.Context;

import br.com.domain.app.minhagelada.daoDB.ItemCestaDao;
import br.com.domain.app.minhagelada.entidades.Estabelecimento;
import br.com.domain.app.minhagelada.entidades.Filtro;
import br.com.domain.app.minhagelada.entidades.ItemCesta;
import br.com.domain.app.minhagelada.entidades.Marca;
import br.com.domain.app.minhagelada.entidades.Unidade;

public class ItemCestaControler {

    private ItemCestaDao itemCestaDao;

    public ItemCestaControler(Context context){
        this.itemCestaDao = new ItemCestaDao(context);
    }


    private ItemCesta createItemCesta(Estabelecimento estabelecimento, Marca marca, Unidade unidade,
                                      Filtro filtro, double valor, int idCesta){
        ItemCesta itemCesta;
        itemCesta = new ItemCesta();
        itemCesta.setEstabelecimento(estabelecimento);
        itemCesta.setMarca(marca);
        itemCesta.setUnidade(unidade);
        itemCesta.setFiltro(filtro);
        itemCesta.setValor(valor);
        itemCesta.setIdCesta(idCesta);

        return itemCesta;
    }

    public boolean insert(Estabelecimento estabelecimento, Marca marca, Unidade unidade,
                             Filtro filtro, double valor,int idCesta){

        ItemCesta itemCesta = this.createItemCesta(estabelecimento,marca,unidade,
                                                    filtro, valor, idCesta);
        return itemCestaDao.insert(itemCesta);
    }
}
