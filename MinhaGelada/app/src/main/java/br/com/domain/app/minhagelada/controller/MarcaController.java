package br.com.domain.app.minhagelada.controller;

import android.content.Context;

import java.util.List;

import br.com.domain.app.minhagelada.daoDB.MarcaDao;
import br.com.domain.app.minhagelada.entidades.Marca;

public class MarcaController {

    private MarcaDao marcaDao;

    public MarcaController(Context context){
       this.marcaDao = new MarcaDao(context);
    }

    private Marca createMarca(String descricao){
        Marca marca;
        marca = new Marca();
        marca.setDescricao(descricao);
        return marca;

    }

    public boolean insert(String descricao){
        return marcaDao.insert(this.createMarca(descricao));
    }

    public List<Marca> selectAll(){
        return this.marcaDao.selectAll();
    }

}
