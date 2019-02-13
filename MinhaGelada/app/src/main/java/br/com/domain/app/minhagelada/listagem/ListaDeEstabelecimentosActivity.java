package br.com.domain.app.minhagelada.listagem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import br.com.domain.app.minhagelada.R;
import br.com.domain.app.minhagelada.daoDB.EstabelecimentoDao;
import br.com.domain.app.minhagelada.entidades.Estabelecimento;

public class ListaDeEstabelecimentosActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EstabelecimentoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_estabelecimentos);
        this.configurarRecycler();
    }

    private void configurarRecycler() {
        // Configurando o gerenciador de layout para ser uma lista.
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Adiciona o adapter que irá anexar os objetos à lista.
        EstabelecimentoDao dao = new EstabelecimentoDao(this);
        adapter = new EstabelecimentoAdapter(dao.selectAll());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }
}
