package br.com.domain.app.minhagelada.entidades;

public class Estabelecimento  extends AbstractEntidadePadrao{

    private String localizacao;

    public Estabelecimento(){

    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    @Override
    public String toString() {
        return super.getDescricao();
    }


}
