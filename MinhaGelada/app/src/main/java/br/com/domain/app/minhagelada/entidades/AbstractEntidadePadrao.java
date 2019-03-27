package br.com.domain.app.minhagelada.entidades;
import com.google.gson.annotations.SerializedName;


public abstract class AbstractEntidadePadrao {

    private int id;
    private String descricao;

    public void AbstractEntidadePadrao(){

    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


}
