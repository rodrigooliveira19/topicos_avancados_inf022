package br.com.domain.app.minhagelada;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import br.com.domain.app.minhagelada.controller.EstabelecimentoController;
import br.com.domain.app.minhagelada.controller.FiltroController;
import br.com.domain.app.minhagelada.controller.ItemCestaControler;
import br.com.domain.app.minhagelada.controller.MarcaController;
import br.com.domain.app.minhagelada.controller.UnidadeController;
import br.com.domain.app.minhagelada.entidades.Estabelecimento;
import br.com.domain.app.minhagelada.entidades.Filtro;
import br.com.domain.app.minhagelada.entidades.Marca;
import br.com.domain.app.minhagelada.entidades.Unidade;

public class ItemCestaActivity extends AppCompatActivity {

    private Spinner estabelecimentoSpinner;
    private Spinner marcaSpinner;
    private Spinner unidadeSpinner;
    private Spinner filtroSpinner;
    private EditText editValor;

    private EstabelecimentoController estabelecimentoController;
    private MarcaController marcaController;
    private UnidadeController unidadeController;
    private FiltroController filtroController;
    private ItemCestaControler itemCestaControler;

    private List<Estabelecimento> estabelecimentos;
    private List<Marca> marcas;
    private List<Unidade> unidades;
    private List<Filtro>  filtros;

    private int idCesta;
    private Estabelecimento estabelecimento;
    private Marca marca;
    private Unidade unidade;
    private Filtro filtro;
    private float valor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_cesta);

        this.estabelecimentoSpinner = findViewById(R.id.estabelecimentoSpinner);
        this.marcaSpinner = findViewById(R.id.marcaSpinner);
        this.unidadeSpinner = findViewById(R.id.unidadeSpinner);
        this.filtroSpinner = findViewById(R.id.filtroSpinner);
        this.editValor = findViewById(R.id.editValor);

        this.estabelecimentoController = new EstabelecimentoController(getApplicationContext());
        this.marcaController = new MarcaController(getApplicationContext());
        this.unidadeController = new UnidadeController(getApplicationContext());
        this.filtroController = new FiltroController(getApplicationContext());
        this.itemCestaControler = new ItemCestaControler(getApplicationContext());

        this.estabelecimentos = estabelecimentoController.selectAll();
        this.marcas = this.marcaController.selectAll();
        this.unidades = this.unidadeController.selectAll();
        this.filtros = this.filtroController.selectAll();

        //Carregando os spiners

        ArrayAdapter<Marca> adapterMarca = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,this.marcas);
        adapterMarca.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<Unidade> adapterUnidade = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,this.unidades);
        adapterUnidade.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<Filtro> adapterFiltro = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,this.filtros);
        adapterFiltro.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        ArrayAdapter<Estabelecimento> adapterEstabelecimento = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,this.estabelecimentos);
        adapterEstabelecimento.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.estabelecimentoSpinner.setAdapter(adapterEstabelecimento);
        this.marcaSpinner.setAdapter(adapterMarca);
        this.unidadeSpinner.setAdapter(adapterUnidade);
        this.filtroSpinner.setAdapter(adapterFiltro);

        //Pegando a referência do objeto selecionado no spinner.
        marcaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Marca marca = (Marca) parent.getSelectedItem();
                setMarca(marca);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        unidadeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Unidade unidade = (Unidade) parent.getSelectedItem();
                setUnidade(unidade);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        filtroSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Filtro filtro = (Filtro) parent.getSelectedItem();
                setFiltro(filtro);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        estabelecimentoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Estabelecimento estabelecimento = (Estabelecimento) parent.getSelectedItem();
                setEstabelecimento(estabelecimento);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Bundle extra = getIntent().getExtras();

        if(extra != null){
            this.idCesta = extra.getInt("idCesta");
        }

    }


    public void salvar(View view){
        if(this.validar()){
            boolean result =this.itemCestaControler.insert(this.estabelecimento,this.marca,
                                                            this.unidade, this.filtro,
                                                             this.valor,this.idCesta);

            if(result)
                Toast.makeText(getApplicationContext(),"Itens Cadastrados com sucesso",
                        Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(),"Não foi possivel carregar os itens",
                        Toast.LENGTH_SHORT).show();
        }
    }


    private boolean validar(){
        String valorString = this.editValor.getText().toString();
        if(valorString != null)
            this.valor = Float.parseFloat(valorString);
        else
            return  false;
        if(this.marca == null)
            return false;
        if(this.unidade == null)
            return false;
        if(this.filtro == null)
            return false;
        if(this.estabelecimento == null)
            return false;

        return  true;
    }

    private void  setMarca(Marca marca){
        this.marca = marca;
    }


    private void  setUnidade(Unidade unidade){
        this.unidade = unidade;
    }


    private void  setFiltro(Filtro filtro){
        this.filtro = filtro;
    }


    private void  setEstabelecimento(Estabelecimento estabelecimento){
        this.estabelecimento = estabelecimento;
    }
}
