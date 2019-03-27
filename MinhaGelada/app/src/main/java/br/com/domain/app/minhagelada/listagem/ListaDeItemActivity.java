package br.com.domain.app.minhagelada.listagem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import br.com.domain.app.minhagelada.R;
import br.com.domain.app.minhagelada.controller.ItemCestaControler;

public class ListaDeItemActivity extends AppCompatActivity {

    private ItemCestaControler itemCestaControler;
    private int idCesta;

    RecyclerView recyclerView;
    ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_itens);

        this.recyclerView = (RecyclerView)findViewById(R.id.recyclerViewItemCesta);

        this.itemCestaControler = new ItemCestaControler(getApplicationContext());

        Bundle extra = getIntent().getExtras();

        if(extra != null){
            this.idCesta = extra.getInt("idCesta");
        }

        this.configurarRecycler();
    }


    private void configurarRecycler() {
        // Configurando o gerenciador de layout para ser uma lista.

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        this.recyclerView.setLayoutManager(layoutManager);

        // Adiciona o adapter que irá anexar os objetos à lista.
        //itemCestaControler dao = new EstabelecimentoDao(this);
        adapter = new ItemAdapter(this.itemCestaControler.selectAll(this.idCesta));
        this.recyclerView.setAdapter(adapter);
        this.recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }
}
