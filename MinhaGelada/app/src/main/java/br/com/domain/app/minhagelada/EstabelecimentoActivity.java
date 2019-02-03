package br.com.domain.app.minhagelada;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class EstabelecimentoActivity extends AppCompatActivity {

    private TextInputLayout textDescricao;
    private TextInputLayout textLocalizacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estabelecimento);

        textDescricao = findViewById(R.id.id_text_input_desc_estabelecimento);
        textLocalizacao = findViewById(R.id.id_text_input_locali_estabelecimento);
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
            descricao+="\n";
            descricao+= textLocalizacao.getEditText().getText().toString();

            Toast.makeText(getApplicationContext(),descricao,Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;
    }
}
