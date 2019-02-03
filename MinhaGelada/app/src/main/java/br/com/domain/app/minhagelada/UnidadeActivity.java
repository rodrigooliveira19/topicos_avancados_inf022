package br.com.domain.app.minhagelada;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class UnidadeActivity extends AppCompatActivity {

    private TextInputLayout textDescricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unidade);

        textDescricao = findViewById(R.id.id_text_input_desc_unidade);
    }

    private boolean validar(){
        String descricao = textDescricao.getEditText().getText().toString();

        if(descricao.length() > 7){
            textDescricao.setError("Limite de caracteres excedido");
            return  false;
        }
        else if(descricao.isEmpty()){
            textDescricao.setError("Descrição não pode estar em branco");
            return  false;
        }
        else{
            textDescricao.setError(null);
            return  true;
        }

    }
    public boolean salvar(View view){
        if(this.validar()){
            String descricao = textDescricao.getEditText().getText().toString();
            descricao+="\n";

            Toast.makeText(getApplicationContext(),descricao,Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;

    }
}
