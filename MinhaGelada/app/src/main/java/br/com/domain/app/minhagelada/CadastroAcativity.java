package br.com.domain.app.minhagelada;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CadastroAcativity extends AppCompatActivity {

    private ListView listCadastro;
    private String[] opcoes = {"Estabelecimento","Marca",
                               "Unidade","Filtro","Produto"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_acativity);

        listCadastro = findViewById(R.id.idListCadastro);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplication(),
                                                          android.R.layout.simple_list_item_1,
                                                          android.R.id.text1,
                                                          opcoes);
        listCadastro.setAdapter(adapter);

        listCadastro.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String opcaoClicada = listCadastro.getItemAtPosition(position).toString();

                if(opcaoClicada.equals("Estabelecimento"))
                    startActivity(new Intent(CadastroAcativity.this,
                                                            EstabelecimentoActivity.class));
                else if(opcaoClicada.equals("Marca"))
                    startActivity(new Intent(CadastroAcativity.this,
                                                            MarcaActivity.class));
                else if(opcaoClicada.equals("Unidade"))
                    startActivity(new Intent(CadastroAcativity.this,
                                                            UnidadeActivity.class));
                else if(opcaoClicada.equals("Filtro"))
                    startActivity(new Intent(CadastroAcativity.this,
                                                            FiltroActivity.class));
                else if(opcaoClicada.equals("Produto"))
                    startActivity(new Intent(CadastroAcativity.this,
                            ProdutoActivity.class));
            }
        });
    }
}
