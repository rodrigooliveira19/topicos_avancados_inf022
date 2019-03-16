package br.com.domain.app.minhagelada;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.domain.app.minhagelada.controller.EstabelecimentoController;
import br.com.domain.app.minhagelada.entidades.Estabelecimento;

public class CestaActivity extends AppCompatActivity {

    private Spinner estabelecimetoSpinner;
    private TextInputLayout descricao;

    private EstabelecimentoController estabelecimentoController;

    private List estabelecimentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cesta);

        this.estabelecimetoSpinner = findViewById(R.id.estabelecimentoSpinner);
        this.descricao = findViewById(R.id.id_text_input_desc_cesta);

        this.estabelecimentoController = new EstabelecimentoController(getApplicationContext());
        this.estabelecimentos = new ArrayList<Estabelecimento>();

        this.estabelecimentos = estabelecimentoController.selectAll();
        if(this.estabelecimentos.size() > 0)
            startActivity(new Intent(CestaActivity.this,EstabelecimentoActivity.class));

        ArrayAdapter<Estabelecimento> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,this.estabelecimentos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }
}
