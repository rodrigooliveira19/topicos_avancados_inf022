package br.com.domain.app.minhagelada;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import br.com.domain.app.minhagelada.controller.EstabelecimentoController;
import br.com.domain.app.minhagelada.entidades.Estabelecimento;
import br.com.domain.app.minhagelada.interfaceApi.JsonPlaceHolderApiEstabelecimento;
import br.com.domain.app.minhagelada.listagem.ListaDeEstabelecimentosActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EstabelecimentoActivity extends AppCompatActivity {

    private TextInputLayout textDescricao;
    private TextInputLayout textLocalizacao;

    private  Estabelecimento estabelecimento = null;

    private JsonPlaceHolderApiEstabelecimento jsonPlaceHolderApiEstabelecimento;
    //private EstabelecimentoController estabelecimentoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estabelecimento);

        textDescricao = findViewById(R.id.id_text_input_desc_estabelecimento);
        textLocalizacao = findViewById(R.id.id_text_input_locali_estabelecimento);

       // estabelecimentoController = new EstabelecimentoController(getApplicationContext());

        //Instanciando um objeto Retrofit para comunicação com a api.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://rodrigooliveira19.pythonanywhere.com/api_rest/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApiEstabelecimento = retrofit.create(JsonPlaceHolderApiEstabelecimento.class);


        Bundle extra = getIntent().getExtras();
        if(extra != null){
            int id = extra.getInt("id");
            String descricao = extra.getString("descricao");
            this.estabelecimento = new Estabelecimento();
            this.estabelecimento.setId(id);
            this.estabelecimento.setDescricao(descricao);
            this.textDescricao.getEditText().setText(descricao);

            boolean opDelete = extra.getBoolean("DELETE");
            if(opDelete){
                this.deleteEstabelecimento();
            }
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

    /*
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
    */

    public boolean salvar(View view){
        if(this.validar()){
            String descricao = textDescricao.getEditText().getText().toString();
            Call<Estabelecimento> call;

            if(this.estabelecimento == null){
                call = jsonPlaceHolderApiEstabelecimento.createEstabelecimento(descricao);

                call.enqueue(new Callback<Estabelecimento>() {
                    @Override
                    public void onResponse(Call<Estabelecimento> call,
                                           Response<Estabelecimento> response) {

                        if(!response.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Erro: "+response.code(),
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Estabelecimento estabelecimento = response.body();
                        Toast.makeText(getApplicationContext(),"Estabelecimento: "
                                        +estabelecimento.getDescricao()+ " cadastrado com sucesso",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Estabelecimento> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),t.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });

            }else{
                this.estabelecimento.setDescricao(descricao);
                call = jsonPlaceHolderApiEstabelecimento.updateEstabelecimento(this.estabelecimento);

                call.enqueue(new Callback<Estabelecimento>() {
                    @Override
                    public void onResponse(Call<Estabelecimento> call,
                                           Response<Estabelecimento> response) {

                        if(!response.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Erro: "+response.code(),
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Estabelecimento estabelecimento = response.body();
                        Toast.makeText(getApplicationContext(),"Estabelecimento: "
                                        +estabelecimento.getDescricao()+ " atualizado com sucesso",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Estabelecimento> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),t.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });

            }

        }
        return  false;
    }

    private void deleteEstabelecimento(){
        if(this.estabelecimento != null){

            Call<Estabelecimento> call = jsonPlaceHolderApiEstabelecimento.
                    deleteEstabelecimento(this.estabelecimento);

            call.enqueue(new Callback<Estabelecimento>() {
                @Override
                public void onResponse(Call<Estabelecimento> call,
                                       Response<Estabelecimento> response) {

                    if(!response.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Erro: "+response.code(),
                                Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Estabelecimento estabelecimento = response.body();
                    Toast.makeText(getApplicationContext(),"Estabelecimento: "
                                    +estabelecimento.getDescricao()+ " excluido com sucesso",
                            Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Estabelecimento> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),t.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
