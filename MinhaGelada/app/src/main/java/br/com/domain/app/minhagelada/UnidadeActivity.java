package br.com.domain.app.minhagelada;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import br.com.domain.app.minhagelada.controller.UnidadeController;
import br.com.domain.app.minhagelada.listagem.ListaDeUnidadesActivity;

public class UnidadeActivity extends AppCompatActivity {

    private TextInputLayout textDescricao;
    private UnidadeController unidadeController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unidade);

        textDescricao = findViewById(R.id.id_text_input_desc_unidade);
        unidadeController = new UnidadeController(getApplicationContext());
    }

    private boolean validar(){
        String descricao = textDescricao.getEditText().getText().toString();

        if(descricao.length() > 7){
            textDescricao.setError("Limite de caracteres excedido");
            return  false;
        }
        else if(descricao.isEmpty()){
            textDescricao.setError("Unidade não pode estar em branco");
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
            boolean result = unidadeController.insert(descricao);
            if(result){
                Toast.makeText(getApplicationContext(),descricao,Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UnidadeActivity.this,
                                                        ListaDeUnidadesActivity.class));
            }

            else
                return false;
        }
        return false;

    }
}
