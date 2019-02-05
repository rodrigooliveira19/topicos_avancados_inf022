package br.com.domain.app.minhagelada.entidades;

public class Estabelecimento  extends AbstractEntidadePadrao{

    private String localizacao;

    /*
    public Estabelecimento(int id, String descricao,String localizacao) {
        super(id,descricao);
        this.localizacao = localizacao;
    }*/
    public Estabelecimento(){

    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
}
