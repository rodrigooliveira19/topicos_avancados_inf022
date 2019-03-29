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
import br.com.domain.app.minhagelada.interfaceApi.JsonPlaceHolderApiEstabelecimento;
import br.com.domain.app.minhagelada.interfaceApi.JsonPlaceHolderApiFiltro;
import br.com.domain.app.minhagelada.interfaceApi.JsonPlaceHolderApiMarca;
import br.com.domain.app.minhagelada.interfaceApi.JsonPlaceHolderApiUnidade;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


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

    /*
    private List<Estabelecimento> estabelecimentos;
    private List<Marca> marcas;
    private List<Unidade> unidades;
    private List<Filtro>  filtros;
    */
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

        this.itemCestaControler = new ItemCestaControler(getApplicationContext());

        /*
        this.estabelecimentoController = new EstabelecimentoController(getApplicationContext());
        this.marcaController = new MarcaController(getApplicationContext());
        this.unidadeController = new UnidadeController(getApplicationContext());
        this.filtroController = new FiltroController(getApplicationContext());
        this.itemCestaControler = new ItemCestaControler(getApplicationContext());
        */

        /*
        this.estabelecimentos = estabelecimentoController.selectAll();
        this.marcas = this.marcaController.selectAll();
        this.unidades = this.unidadeController.selectAll();
        this.filtros = this.filtroController.selectAll();
        */



        //Carregamento dos Estabelecimentos a partir da biblioteca Retrofit.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://rodrigooliveira19.pythonanywhere.com/api_rest/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApiEstabelecimento jsonPlaceHolderApi = retrofit.
                create(JsonPlaceHolderApiEstabelecimento.class);

        Call<List<Estabelecimento>> call = jsonPlaceHolderApi.getEstabelecimentos();

        call.enqueue(new Callback<List<Estabelecimento>>() {
            @Override
            public void onResponse(Call<List<Estabelecimento>> call,
                                   Response<List<Estabelecimento>> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Erro: "+response.code(),
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Estabelecimento> estabelecimentos = response.body();

                loadSpinnerEstabelecimento(estabelecimentos);


            }

            @Override
            public void onFailure(Call<List<Estabelecimento>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Erro: "+t.getMessage(),
                        Toast.LENGTH_SHORT).show();

            }
        });




        JsonPlaceHolderApiMarca jsonPlaceHolderApiMarca = retrofit.create(JsonPlaceHolderApiMarca.class);

        Call<List<Marca>> callMarca = jsonPlaceHolderApiMarca.getMarcas();

        callMarca.enqueue(new Callback<List<Marca>>() {
            @Override
            public void onResponse(Call<List<Marca>> call, Response<List<Marca>> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Erro: "+response.code(),
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Marca> marcas = response.body();

                if (marcas == null)
                    Toast.makeText(getApplicationContext(),"Erro: "+marcas.size(),
                            Toast.LENGTH_SHORT).show();

                loadSpinnerMarca(marcas);


            }

            @Override
            public void onFailure(Call<List<Marca>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Erro: "+t.getMessage(),
                        Toast.LENGTH_SHORT).show();

            }
        });


        JsonPlaceHolderApiUnidade jsonPlaceHolderApiUnidade = retrofit.
                create(JsonPlaceHolderApiUnidade.class);

        Call<List<Unidade>> callUnidade = jsonPlaceHolderApiUnidade.getUnidades();

        callUnidade.enqueue(new Callback<List<Unidade>>() {
            @Override
            public void onResponse(Call<List<Unidade>> call, Response<List<Unidade>> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Erro: "+response.code(),
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Unidade> unidades = response.body();

                if (unidades == null)
                    Toast.makeText(getApplicationContext(),"Erro: "+unidades.size(),
                            Toast.LENGTH_SHORT).show();

                loadSpinnerUnidade(unidades);


            }



            @Override
            public void onFailure(Call<List<Unidade>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Erro: "+t.getMessage(),
                        Toast.LENGTH_SHORT).show();

            }
        });


        JsonPlaceHolderApiFiltro jsonPlaceHolderApiFiltro = retrofit.
                create(JsonPlaceHolderApiFiltro.class);

        Call<List<Filtro>> callFiltro = jsonPlaceHolderApiFiltro.getFiltros();

        callFiltro.enqueue(new Callback<List<Filtro>>() {
            @Override
            public void onResponse(Call<List<Filtro>> call, Response<List<Filtro>> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Erro: "+response.code(),
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Filtro> filtros = response.body();

                if (filtros == null)
                    Toast.makeText(getApplicationContext(),"Erro: "+filtros.size(),
                            Toast.LENGTH_SHORT).show();

                loadSpinnerFiltro(filtros);

            }

            @Override
            public void onFailure(Call<List<Filtro>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Erro: "+t.getMessage(),
                        Toast.LENGTH_SHORT).show();

            }
        });


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



    private void loadSpinnerEstabelecimento(List<Estabelecimento> estabelecimentos){

        ArrayAdapter<Estabelecimento> adapterEstabelecimento = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,estabelecimentos);
        adapterEstabelecimento.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.estabelecimentoSpinner.setAdapter(adapterEstabelecimento);

    }

    private void loadSpinnerMarca(List<Marca> marcas){

        ArrayAdapter<Marca> adapterMarca = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,marcas);
        adapterMarca.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.marcaSpinner.setAdapter(adapterMarca);

    }

    private void loadSpinnerUnidade(List<Unidade> unidades){

        ArrayAdapter<Unidade> adapterUnidade = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,unidades);
        adapterUnidade.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.unidadeSpinner.setAdapter(adapterUnidade);
    }

    private void loadSpinnerFiltro(List<Filtro> filtros){

        ArrayAdapter<Filtro> adapterFiltro = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,filtros);
        adapterFiltro.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.filtroSpinner.setAdapter(adapterFiltro);
    }

}
