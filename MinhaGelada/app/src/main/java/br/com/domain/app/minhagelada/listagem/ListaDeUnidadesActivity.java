package br.com.domain.app.minhagelada.listagem;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import br.com.domain.app.minhagelada.R;
import br.com.domain.app.minhagelada.UnidadeActivity;
import br.com.domain.app.minhagelada.daoDB.UnidadeDao;
import br.com.domain.app.minhagelada.entidades.Unidade;
import br.com.domain.app.minhagelada.interfaceApi.JsonPlaceHolderApiUnidade;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListaDeUnidadesActivity extends AppCompatActivity {

    RecyclerView recyclerViewUnidade;
    UnidadeAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_unidades);

        //this.configurarRecycler();
        this.recyclerViewUnidade = findViewById(R.id.recyclerViewUnidade);

        FloatingActionButton fab = findViewById(R.id.floatingButtonAddUnidade);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListaDeUnidadesActivity.this,
                        UnidadeActivity.class));
            }
        });


        //Carregamento das Unidades a partir da biblioteca Retrofit.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://rodrigooliveira19.pythonanywhere.com/api_rest/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApiUnidade jsonPlaceHolderApi = retrofit.
                create(JsonPlaceHolderApiUnidade.class);

        Call<List<Unidade>> call = jsonPlaceHolderApi.getUnidades();

        call.enqueue(new Callback<List<Unidade>>() {
            @Override
            public void onResponse(Call<List<Unidade>> call,
                                   Response<List<Unidade>> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Erro: "+response.code(),
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Unidade> unidades = response.body();

                configurarRecycler(unidades);


            }

            @Override
            public void onFailure(Call<List<Unidade>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Erro: "+t.getMessage(),
                        Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void configurarRecycler(List<Unidade> unidades) {
        // Configurando o gerenciador de layout para ser uma lista.

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        this.recyclerViewUnidade.setLayoutManager(layoutManager);

        // Adiciona o adapter que irá anexar os objetos à lista.
        //UnidadeDao unidadeDao = new UnidadeDao(this);
        //adapter = new UnidadeAdapter(unidadeDao.selectAll());
        this.adapter = new UnidadeAdapter(unidades);
        this.recyclerViewUnidade.setAdapter(adapter);
        this.recyclerViewUnidade.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }
}
