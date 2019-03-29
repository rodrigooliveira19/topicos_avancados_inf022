package br.com.domain.app.minhagelada;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import br.com.domain.app.minhagelada.controller.UnidadeController;
import br.com.domain.app.minhagelada.entidades.Unidade;
import br.com.domain.app.minhagelada.interfaceApi.JsonPlaceHolderApiUnidade;
import br.com.domain.app.minhagelada.listagem.ListaDeUnidadesActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UnidadeActivity extends AppCompatActivity {

    private TextInputLayout textDescricao;

    private JsonPlaceHolderApiUnidade jsonPlaceHolderApiUnidade;

    //private UnidadeController unidadeController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unidade);

        textDescricao = findViewById(R.id.id_text_input_desc_unidade);
        //unidadeController = new UnidadeController(getApplicationContext());

        //Instanciando um objeto Retrofit para comunicação com a api.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://rodrigooliveira19.pythonanywhere.com/api_rest/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApiUnidade = retrofit.create(JsonPlaceHolderApiUnidade.class);
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
    /*
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
    */

    public boolean salvar(View view){
        if(this.validar()){
            String descricao = textDescricao.getEditText().getText().toString();

            Call<Unidade> call = jsonPlaceHolderApiUnidade.createUnidade(descricao);

            call.enqueue(new Callback<Unidade>() {
                @Override
                public void onResponse(Call<Unidade> call,
                                       Response<Unidade> response) {

                    if(!response.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Erro: "+response.code(),
                                Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Unidade unidade = response.body();
                    Toast.makeText(getApplicationContext(),"Unidade: "
                                    +unidade.getDescricao()+ " cadastrado com sucesso",
                            Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(Call<Unidade> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),t.getMessage(),
                            Toast.LENGTH_SHORT).show();

                }
            });

        }
        return  false;
    }
}
