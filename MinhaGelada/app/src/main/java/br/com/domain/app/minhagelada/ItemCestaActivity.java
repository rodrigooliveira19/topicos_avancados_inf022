package br.com.domain.app.minhagelada;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import br.com.domain.app.minhagelada.controller.FiltroController;
import br.com.domain.app.minhagelada.controller.MarcaController;
import br.com.domain.app.minhagelada.controller.UnidadeController;
import br.com.domain.app.minhagelada.entidades.Filtro;
import br.com.domain.app.minhagelada.entidades.Marca;
import br.com.domain.app.minhagelada.entidades.Unidade;

public class ItemCestaActivity extends AppCompatActivity {

    private Spinner marcaSpinner;
    private Spinner unidadeSpinner;
    private Spinner filtroSpinner;
    private EditText editValor;

    private MarcaController marcaController;
    private UnidadeController unidadeController;
    private FiltroController filtroController;

    private List<Marca> marcas;
    private List<Unidade> unidades;
    private List<Filtro>  filtros;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_cesta);

        this.marcaSpinner = findViewById(R.id.marcaSpinner);
        this.unidadeSpinner = findViewById(R.id.unidadeSpinner);
        this.filtroSpinner = findViewById(R.id.filtroSpinner);
        this.editValor = findViewById(R.id.editValor);

        this.marcaController = new MarcaController(getApplicationContext());
        this.unidadeController = new UnidadeController(getApplicationContext());
        this.filtroController = new FiltroController(getApplicationContext());

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

        this.marcaSpinner.setAdapter(adapterMarca);
        this.unidadeSpinner.setAdapter(adapterUnidade);
        this.filtroSpinner.setAdapter(adapterFiltro);

    }


    public void salvar(View view){}
}
