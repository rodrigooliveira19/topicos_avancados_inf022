package br.com.domain.app.minhagelada.entidades;


public abstract class AbstractEntidadePadrao {

    private int id;
    private String descricao;

   /*
    public void AbstractEntidadePadrao(int id, String descricao){
        this.id = id;
        this.descricao = descricao;
    }*/

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
