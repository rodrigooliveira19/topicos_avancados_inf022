package br.com.domain.app.minhagelada.interfaceApi;

import java.util.List;

import br.com.domain.app.minhagelada.entidades.Estabelecimento;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceHolderApiEstabelecimento {

    @GET("estabelecimentos/")
    Call<List<Estabelecimento>> getEstabelecimentos();

    @FormUrlEncoded
    @POST("cadastrarEstabelecimento/")
    Call<Estabelecimento> createEstabelecimento(@Field("descricao") String descricao);
}
