package br.com.domain.app.minhagelada;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.domain.app.minhagelada.controller.CestaController;
import br.com.domain.app.minhagelada.controller.EstabelecimentoController;
import br.com.domain.app.minhagelada.entidades.Cesta;
import br.com.domain.app.minhagelada.entidades.Estabelecimento;
import br.com.domain.app.minhagelada.listagem.ListaDeCestaActivity;

public class CestaActivity extends AppCompatActivity {

    private TextInputLayout textDescricao;

    private CestaController cestaController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cesta);

        this.textDescricao = findViewById(R.id.id_text_input_desc_cesta);

        this.cestaController = new CestaController(getApplicationContext());

    }

    public void salvar(View view){
        if(this.validar()){
            String descricao = textDescricao.getEditText().getText().toString();

            boolean result =cestaController.insert(descricao);
            if(result){
                Toast.makeText(getApplicationContext(),"Cesta Cadastrado com sucesso",
                        Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CestaActivity.this,
                        ListaDeCestaActivity.class));
            }

        }

    }

    private boolean validar(){
        String descricao = textDescricao.getEditText().getText().toString();

        if(descricao.length() > 30){
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

/*
    private int getIndex(Spinner spinner, String myString)
    {
        int index = 0;
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                index = i;
                break;
            }
        }
        return index;
    }
    */
}
