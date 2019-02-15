package br.com.domain.app.minhagelada.listagem;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import br.com.domain.app.minhagelada.MarcaActivity;
import br.com.domain.app.minhagelada.R;
import br.com.domain.app.minhagelada.daoDB.MarcaDao;

public class ListaDeMarcasActivity extends AppCompatActivity {

    RecyclerView recyclerViewMarca;
    MarcaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_marcas2);

        this.configurarRecycler();

        FloatingActionButton fab = findViewById(R.id.floatingButtonAddMarca);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListaDeMarcasActivity.this,
                                                        MarcaActivity.class));
            }
        });
    }

    private void configurarRecycler() {
        // Configurando o gerenciador de layout para ser uma lista.
        recyclerViewMarca = findViewById(R.id.recyclerViewMarca);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewMarca.setLayoutManager(layoutManager);

        // Adiciona o adapter que irá anexar os objetos à lista.
        MarcaDao marcaDao = new MarcaDao(this);
        adapter = new MarcaAdapter(marcaDao.selectAll());
        recyclerViewMarca.setAdapter(adapter);
        recyclerViewMarca.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }
}
