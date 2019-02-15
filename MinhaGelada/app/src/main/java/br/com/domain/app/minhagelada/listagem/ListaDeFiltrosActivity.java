package br.com.domain.app.minhagelada.listagem;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import br.com.domain.app.minhagelada.FiltroActivity;
import br.com.domain.app.minhagelada.R;
import br.com.domain.app.minhagelada.daoDB.FiltroDao;


public class ListaDeFiltrosActivity extends AppCompatActivity {

    FiltroAdapter adapter;
    RecyclerView recyclerViewFiltro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_filtros);

        this.configurarRecycler();

        FloatingActionButton fab = findViewById(R.id.floatingButtonAddFiltro);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListaDeFiltrosActivity.this,
                        FiltroActivity.class));
            }
        });
    }


    private void configurarRecycler() {
        // Configurando o gerenciador de layout para ser uma lista.
        recyclerViewFiltro = findViewById(R.id.recyclerViewFiltro);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewFiltro.setLayoutManager(layoutManager);

        // Adiciona o adapter que irá anexar os objetos à lista.
        FiltroDao filtroDao = new FiltroDao(this);
        adapter = new FiltroAdapter(filtroDao.selectAll());
        recyclerViewFiltro.setAdapter(adapter);
        recyclerViewFiltro.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }
}
