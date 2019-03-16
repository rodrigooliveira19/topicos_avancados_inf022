package br.com.domain.app.minhagelada.entidades;

import java.util.ArrayList;
import java.util.List;

public class Cesta {

    private int id;
    private String descricao;

    List<ItemCesta> itemCesta;

    public Cesta(){
        itemCesta = new ArrayList<ItemCesta>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
