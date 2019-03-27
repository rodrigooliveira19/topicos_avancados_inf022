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

import br.com.domain.app.minhagelada.FiltroActivity;
import br.com.domain.app.minhagelada.R;
import br.com.domain.app.minhagelada.daoDB.FiltroDao;
import br.com.domain.app.minhagelada.entidades.Filtro;
import br.com.domain.app.minhagelada.interfaceApi.JsonPlaceHolderApiFiltro;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ListaDeFiltrosActivity extends AppCompatActivity {

    FiltroAdapter adapter;
    RecyclerView recyclerViewFiltro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_filtros);

        //this.configurarRecycler();
        this.recyclerViewFiltro = findViewById(R.id.recyclerViewFiltro);

        FloatingActionButton fab = findViewById(R.id.floatingButtonAddFiltro);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListaDeFiltrosActivity.this,
                        FiltroActivity.class));
            }
        });


        //Carregamento dos Estabelecimentos a partir da biblioteca Retrofit.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://rodrigooliveira19.pythonanywhere.com/api_rest/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApiFiltro jsonPlaceHolderApi = retrofit.
                create(JsonPlaceHolderApiFiltro.class);

        Call<List<Filtro>> call = jsonPlaceHolderApi.getFiltros();

        call.enqueue(new Callback<List<Filtro>>() {
            @Override
            public void onResponse(Call<List<Filtro>> call,
                                   Response<List<Filtro>> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Erro: "+response.code(),
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Filtro> filtros = response.body();

                configurarRecycler(filtros);


            }

            @Override
            public void onFailure(Call<List<Filtro>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Erro: "+t.getMessage(),
                        Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void configurarRecycler(List<Filtro> filtros) {
        // Configurando o gerenciador de layout para ser uma lista.

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        this.recyclerViewFiltro.setLayoutManager(layoutManager);

        // Adiciona o adapter que irá anexar os objetos à lista.
        //FiltroDao filtroDao = new FiltroDao(this);
        //adapter = new FiltroAdapter(filtroDao.selectAll());
        this.adapter = new FiltroAdapter(filtros);
        this.recyclerViewFiltro.setAdapter(adapter);
        this.recyclerViewFiltro.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }
}
