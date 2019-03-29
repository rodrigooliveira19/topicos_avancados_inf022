package br.com.domain.app.minhagelada;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import br.com.domain.app.minhagelada.controller.MarcaController;

import br.com.domain.app.minhagelada.entidades.Marca;
import br.com.domain.app.minhagelada.interfaceApi.JsonPlaceHolderApiMarca;
import br.com.domain.app.minhagelada.listagem.ListaDeMarcasActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MarcaActivity extends AppCompatActivity {

    private TextInputLayout textDescricao;

    private Marca marca = null;

    private JsonPlaceHolderApiMarca jsonPlaceHolderApiMarca;
    private MarcaController marcaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marca);

        textDescricao = findViewById(R.id.id_text_input_desc_marca);
       // marcaController = new MarcaController(getApplicationContext());

        //Instanciando um objeto Retrofit para comunicação com a api.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://rodrigooliveira19.pythonanywhere.com/api_rest/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApiMarca = retrofit.create(JsonPlaceHolderApiMarca.class);

        Bundle extra = getIntent().getExtras();
        if(extra != null){
            int id = extra.getInt("id");
            String descricao = extra.getString("descricao");
            this.marca = new Marca();
            this.marca.setId(id);
            this.marca.setDescricao(descricao);
            this.textDescricao.getEditText().setText(descricao);

            boolean opDelete = extra.getBoolean("DELETE");
            if(opDelete){
                this.deleteMarca();
            }
        }

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
    /*
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
    */

    public boolean salvar(View view){
        if(this.validar()){
            String descricao = textDescricao.getEditText().getText().toString();

            Call<Marca> call;

            if(this.marca == null){
                call = jsonPlaceHolderApiMarca.createMarca(descricao);

                call.enqueue(new Callback<Marca>() {
                    @Override
                    public void onResponse(Call<Marca> call,
                                           Response<Marca> response) {

                        if(!response.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Erro: "+response.code(),
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Marca marca = response.body();
                        Toast.makeText(getApplicationContext(),"Marca: "
                                        +marca.getDescricao()+ " cadastrada com sucesso",
                                Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<Marca> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),t.getMessage(),
                                Toast.LENGTH_SHORT).show();

                    }
                });
            }else{
                this.marca.setDescricao(descricao);
                call = jsonPlaceHolderApiMarca.updateMarca(this.marca);

                call.enqueue(new Callback<Marca>() {
                    @Override
                    public void onResponse(Call<Marca> call,
                                           Response<Marca> response) {

                        if(!response.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Erro: "+response.code(),
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Marca marca = response.body();
                        Toast.makeText(getApplicationContext(),"Marca: "
                                        +marca.getDescricao()+ " atualizada com sucesso",
                                Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<Marca> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),t.getMessage(),
                                Toast.LENGTH_SHORT).show();

                    }
                });
            }



        }
        return  false;
    }

    private void deleteMarca(){
        if(this.marca != null){

            Call<Marca> call = jsonPlaceHolderApiMarca.
                    deleteMarca(this.marca);

            call.enqueue(new Callback<Marca>() {
                @Override
                public void onResponse(Call<Marca> call,
                                       Response<Marca> response) {

                    if(!response.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Erro: "+response.code(),
                                Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Marca marca = response.body();
                    Toast.makeText(getApplicationContext(),"Marca: "
                                    +marca.getDescricao()+ " excluido com sucesso",
                            Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Marca> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),t.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
