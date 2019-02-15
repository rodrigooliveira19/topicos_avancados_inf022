package br.com.domain.app.minhagelada.listagem;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import br.com.domain.app.minhagelada.R;
import br.com.domain.app.minhagelada.UnidadeActivity;
import br.com.domain.app.minhagelada.daoDB.UnidadeDao;

public class ListaDeUnidadesActivity extends AppCompatActivity {

    RecyclerView recyclerViewUnidade;
    UnidadeAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_unidades);

        this.configurarRecycler();

        FloatingActionButton fab = findViewById(R.id.floatingButtonAddUnidade);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListaDeUnidadesActivity.this,
                        UnidadeActivity.class));
            }
        });
    }

    private void configurarRecycler() {
        // Configurando o gerenciador de layout para ser uma lista.
        recyclerViewUnidade = findViewById(R.id.recyclerViewUnidade);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewUnidade.setLayoutManager(layoutManager);

        // Adiciona o adapter que irá anexar os objetos à lista.
        UnidadeDao unidadeDao = new UnidadeDao(this);
        adapter = new UnidadeAdapter(unidadeDao.selectAll());
        recyclerViewUnidade.setAdapter(adapter);
        recyclerViewUnidade.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }
}
