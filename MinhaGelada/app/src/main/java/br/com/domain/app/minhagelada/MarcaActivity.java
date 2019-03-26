package br.com.domain.app.minhagelada;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import br.com.domain.app.minhagelada.controller.MarcaController;

import br.com.domain.app.minhagelada.listagem.ListaDeMarcasActivity;


public class MarcaActivity extends AppCompatActivity {

    private TextInputLayout textDescricao;
    private MarcaController marcaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marca);

        textDescricao = findViewById(R.id.id_text_input_desc_marca);
        marcaController = new MarcaController(getApplicationContext());

    }


    private boolean validar(){
        String descricao = textDescricao.getEditText().getText().toString();

        if(descricao.length() > 20){
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
            boolean result = marcaController.insert(descricao);

            if(result){
                Toast.makeText(getApplicationContext(),"Marca cadastrada com Sucesso",
                                                        Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MarcaActivity.this,
                                                        ListaDeMarcasActivity.class));

            }

            return true;
        }
        return false;

    }
}
