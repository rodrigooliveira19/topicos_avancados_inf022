package br.com.domain.app.minhagelada.listagem;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import br.com.domain.app.minhagelada.CestaActivity;
import br.com.domain.app.minhagelada.EstabelecimentoActivity;
import br.com.domain.app.minhagelada.R;
import br.com.domain.app.minhagelada.daoDB.CestaDao;

public class ListaDeCestaActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CestaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_cesta);


        this.configurarRecycler();

        FloatingActionButton fab = findViewById(R.id.floatingButtonAddCesta);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListaDeCestaActivity.this,
                        CestaActivity.class));
            }
        });

    }

    private void configurarRecycler() {
        // Configurando o gerenciador de layout para ser uma lista.
        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewCesta);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Adiciona o adapter que irá anexar os objetos à lista.
        CestaDao dao = new CestaDao(this);
        adapter = new CestaAdapter(dao.selectAll());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }
}
