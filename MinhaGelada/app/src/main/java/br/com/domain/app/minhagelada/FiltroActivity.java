package br.com.domain.app.minhagelada;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import br.com.domain.app.minhagelada.controller.FiltroController;
import br.com.domain.app.minhagelada.listagem.ListaDeFiltrosActivity;

public class FiltroActivity extends AppCompatActivity {

    private TextInputLayout textDescricao;
    private FiltroController filtroController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtro);

        textDescricao = findViewById(R.id.id_text_input_desc_filtro);
        filtroController = new FiltroController(getApplicationContext());
    }


    private boolean validar(){
        String descricao = textDescricao.getEditText().getText().toString();

        if(descricao.length() > 15){
            textDescricao.setError("Limite de caracteres excedido");
            return  false;
        }
        else if(descricao.isEmpty()){
            textDescricao.setError("Filtro não pode estar em branco");
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
            boolean result = filtroController.insert(descricao);

            if(result){
                Toast.makeText(getApplicationContext(),descricao,Toast.LENGTH_SHORT).show();
                startActivity(new Intent(FiltroActivity.this,
                                                       ListaDeFiltrosActivity.class));
                return true;

            }
        }
        return false;

    }
}
