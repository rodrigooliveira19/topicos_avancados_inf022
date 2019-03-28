package br.com.domain.app.minhagelada;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import br.com.domain.app.minhagelada.controller.EstabelecimentoController;
import br.com.domain.app.minhagelada.listagem.ListaDeEstabelecimentosActivity;

public class EstabelecimentoActivity extends AppCompatActivity {

    private TextInputLayout textDescricao;
    private TextInputLayout textLocalizacao;

    private EstabelecimentoController estabelecimentoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estabelecimento);

        textDescricao = findViewById(R.id.id_text_input_desc_estabelecimento);
        textLocalizacao = findViewById(R.id.id_text_input_locali_estabelecimento);

        estabelecimentoController = new EstabelecimentoController(getApplicationContext());

        Bundle extra = getIntent().getExtras();

        if(extra != null){
            int id = extra.getInt("id");
            String descricao = extra.getString("descricao");
            String localizacao = extra.getString("localizacao");
            Toast.makeText(getApplicationContext(),id+""+descricao,Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validar(){
        String descricao = textDescricao.getEditText().getText().toString();
        String localizacao = textLocalizacao.getEditText().getText().toString();

        if(descricao.length() > 30){
            textDescricao.setError("Limite de caracteres excedido");
            return  false;
        }
        else if(descricao.isEmpty()){
            textDescricao.setError("Descrição não pode estar em branco");
            return  false;
        }else if(localizacao.length() > 30){
            textLocalizacao.setError("Limite de caracteres excedido");
        }
        else{
            textDescricao.setError(null);
            textLocalizacao.setError(null);
            return  true;
        }

        return false;
    }

    public boolean salvar(View view){
        if(this.validar()){
            String descricao = textDescricao.getEditText().getText().toString();
            String localizacao= textLocalizacao.getEditText().getText().toString();

            boolean result =estabelecimentoController.insert(descricao,localizacao);
            if(result){
                Toast.makeText(getApplicationContext(),"Estabelecimento Cadastrado com sucesso",
                                                                     Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EstabelecimentoActivity.this,
                                                        ListaDeEstabelecimentosActivity.class));
                return true;
            }
            else
                return  false;
        }

        return false;
    }
}
