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

import br.com.domain.app.minhagelada.MarcaActivity;
import br.com.domain.app.minhagelada.R;
import br.com.domain.app.minhagelada.entidades.Marca;
import br.com.domain.app.minhagelada.interfaceApi.JsonPlaceHolderApiMarca;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListaDeMarcasActivity extends AppCompatActivity {

    RecyclerView recyclerViewMarca;
    MarcaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_marcas2);


        this.recyclerViewMarca = findViewById(R.id.recyclerViewMarca);

        FloatingActionButton fab = findViewById(R.id.floatingButtonAddMarca);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListaDeMarcasActivity.this,
                                                        MarcaActivity.class));
            }
        });

        //------------------------------------------------------------------
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://sobral.pythonanywhere.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApiMarca jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApiMarca.class);

        Call<List<Marca>> call = jsonPlaceHolderApi.getMarcas();

        call.enqueue(new Callback<List<Marca>>() {
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

                configurarRecycler(marcas);


            }

            @Override
            public void onFailure(Call<List<Marca>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Erro: "+t.getMessage(),
                        Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void configurarRecycler(List<Marca> marcas) {
        // Configurando o gerenciador de layout para ser uma lista.
        //recyclerViewMarca = findViewById(R.id.recyclerViewMarca);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        this.recyclerViewMarca.setLayoutManager(layoutManager);

        // Adiciona o adapter que irá anexar os objetos à lista.
       // MarcaDao marcaDao = new MarcaDao(this);
        //adapter = new MarcaAdapter(marcaDao.selectAll());
        this.adapter = new MarcaAdapter(marcas);
        this.recyclerViewMarca.setAdapter(this.adapter);
        this.recyclerViewMarca.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }
}
