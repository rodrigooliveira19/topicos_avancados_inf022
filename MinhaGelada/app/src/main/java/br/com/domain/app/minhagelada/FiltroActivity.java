package br.com.domain.app.minhagelada;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import br.com.domain.app.minhagelada.controller.FiltroController;
import br.com.domain.app.minhagelada.entidades.Filtro;
import br.com.domain.app.minhagelada.interfaceApi.JsonPlaceHolderApiFiltro;
import br.com.domain.app.minhagelada.listagem.ListaDeFiltrosActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FiltroActivity extends AppCompatActivity {

    private TextInputLayout textDescricao;

    private Filtro filtro = null;

    private JsonPlaceHolderApiFiltro jsonPlaceHolderApiFiltro;
    //private FiltroController filtroController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtro);

        textDescricao = findViewById(R.id.id_text_input_desc_filtro);
       // filtroController = new FiltroController(getApplicationContext());

        //Instanciando um objeto Retrofit para comunicação com a api.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://rodrigooliveira19.pythonanywhere.com/api_rest/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApiFiltro = retrofit.create(JsonPlaceHolderApiFiltro.class);

        Bundle extra = getIntent().getExtras();
        if(extra != null){
            int id = extra.getInt("id");
            String descricao = extra.getString("descricao");
            this.filtro = new Filtro();
            this.filtro.setId(id);
            this.filtro.setDescricao(descricao);
            this.textDescricao.getEditText().setText(descricao);

            boolean opDelete = extra.getBoolean("DELETE");
            if(opDelete){
                this.deleteFiltro();
            }
        }
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
    /*
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
    */

    public boolean salvar(View view){
        if(this.validar()){
            String descricao = textDescricao.getEditText().getText().toString();

            Call<Filtro> call;

            if (this.filtro == null){
                call = jsonPlaceHolderApiFiltro.createFiltro(descricao);

                call.enqueue(new Callback<Filtro>() {
                    @Override
                    public void onResponse(Call<Filtro> call,
                                           Response<Filtro> response) {

                        if(!response.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Erro: "+response.code(),
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Filtro filtro = response.body();
                        Toast.makeText(getApplicationContext(),"Filtro: "
                                        +filtro.getDescricao()+ " cadastrado com sucesso",
                                Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<Filtro> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),t.getMessage(),
                                Toast.LENGTH_SHORT).show();

                    }
                });

            }else{
                this.filtro.setDescricao(descricao);

                call = jsonPlaceHolderApiFiltro.updateFiltro(this.filtro);

                call.enqueue(new Callback<Filtro>() {
                    @Override
                    public void onResponse(Call<Filtro> call,
                                           Response<Filtro> response) {

                        if(!response.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Erro: "+response.code(),
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Filtro filtro = response.body();
                        Toast.makeText(getApplicationContext(),"Filtro: "
                                        +filtro.getDescricao()+ " atualizado com sucesso",
                                Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<Filtro> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),t.getMessage(),
                                Toast.LENGTH_SHORT).show();

                    }
                });

            }


        }
        return  false;
    }

    private void deleteFiltro(){
        if(this.filtro != null){

            Call<Filtro> call = jsonPlaceHolderApiFiltro.
                    deleteFiltro(this.filtro);

            call.enqueue(new Callback<Filtro>() {
                @Override
                public void onResponse(Call<Filtro> call,
                                       Response<Filtro> response) {

                    if(!response.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Erro: "+response.code(),
                                Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Filtro filtro = response.body();
                    Toast.makeText(getApplicationContext(),"Filtro: "
                                    +filtro.getDescricao()+ " excluido com sucesso",
                            Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Filtro> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),t.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
