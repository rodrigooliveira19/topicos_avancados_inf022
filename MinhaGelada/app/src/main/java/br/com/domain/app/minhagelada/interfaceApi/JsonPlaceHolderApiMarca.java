package br.com.domain.app.minhagelada.interfaceApi;

import java.util.List;

import br.com.domain.app.minhagelada.entidades.Marca;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceHolderApiMarca {

    @GET("marcas/")
    Call<List<Marca>> getMarcas();

    @FormUrlEncoded
    @POST("cadastrarMarca/")
    Call<Marca> createMarca(@Field("descricao") String descricao);

    @POST("atualizarMarca/")
    Call<Marca> updateMarca(@Body Marca marca);

    @POST("excluirMarca/")
    Call<Marca> deleteMarca(@Body Marca marca);
}
