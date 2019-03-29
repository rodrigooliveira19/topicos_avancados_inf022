package br.com.domain.app.minhagelada.interfaceApi;

import java.util.List;

import br.com.domain.app.minhagelada.entidades.Unidade;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceHolderApiUnidade {

    @GET("unidades/")
    Call<List<Unidade>> getUnidades();

    @FormUrlEncoded
    @POST("cadastrarUnidade/")
    Call<Unidade> createUnidade(@Field("descricao") String descricao);

    @POST("atualizarUnidade/")
    Call<Unidade> updateUnidade(@Body Unidade unidade);
}
