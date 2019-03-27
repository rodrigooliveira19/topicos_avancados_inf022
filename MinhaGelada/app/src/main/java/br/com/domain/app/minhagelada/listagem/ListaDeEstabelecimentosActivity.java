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

import br.com.domain.app.minhagelada.EstabelecimentoActivity;
import br.com.domain.app.minhagelada.R;
import br.com.domain.app.minhagelada.daoDB.EstabelecimentoDao;
import br.com.domain.app.minhagelada.entidades.Estabelecimento;
import br.com.domain.app.minhagelada.interfaceApi.JsonPlaceHolderApiEstabelecimento;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ListaDeEstabelecimentosActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EstabelecimentoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_estabelecimentos);

        //this.configurarRecycler();

        this.recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        FloatingActionButton fab = findViewById(R.id.floatingButtonAddEstabelecimento);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListaDeEstabelecimentosActivity.this,
                        EstabelecimentoActivity.class));
            }
        });



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

                configurarRecycler(estabelecimentos);


            }

            @Override
            public void onFailure(Call<List<Estabelecimento>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Erro: "+t.getMessage(),
                        Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void configurarRecycler(List<Estabelecimento> estabelecimentos) {
        // Configurando o gerenciador de layout para ser uma lista.

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        this.recyclerView.setLayoutManager(layoutManager);

        // Adiciona o adapter que irá anexar os objetos à lista.
        //EstabelecimentoDao dao = new EstabelecimentoDao(this);
        //this.adapter = new EstabelecimentoAdapter(dao.selectAll());
        this.adapter = new EstabelecimentoAdapter(estabelecimentos);
        this.recyclerView.setAdapter(adapter);
        this.recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }
}
