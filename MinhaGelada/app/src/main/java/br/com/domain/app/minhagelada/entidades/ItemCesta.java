package br.com.domain.app.minhagelada.entidades;

public class ItemCesta {

    private int id;
    private int idCesta;

    private Estabelecimento estabelecimento;
    private Marca marca;
    private Unidade unidade;
    private Filtro filtro;
    private float valor;

    public ItemCesta(){}

    public  ItemCesta(String descEstabelecimento,
                      String descMarca,
                      String descUnidade,
                      String descFiltro,
                      int id,
                      float valor){
        this.estabelecimento = new Estabelecimento();
        this.marca = new Marca();
        this.unidade = new Unidade();
        this.filtro = new Filtro();
        this.id = id;
        this.valor = valor;

        this.estabelecimento.setDescricao(descEstabelecimento);
        this.marca.setDescricao(descMarca);
        this.unidade.setDescricao(descUnidade);
        this.filtro.setDescricao(descFiltro);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCesta() {
        return idCesta;
    }

    public void setIdCesta(int idCesta) {
        this.idCesta = idCesta;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public Filtro getFiltro() {
        return filtro;
    }

    public void setFiltro(Filtro filtro) {
        this.filtro = filtro;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return  "" +
                this.estabelecimento.getDescricao() +"\n"+
                "" +
                this.marca.getDescricao() +"\n"+
                "" +
                this.unidade.getDescricao() +"\n"+
                "" +
                this.filtro.getDescricao() +"\n"+
                "R$ " + this.valor +"\n";
    }
}
