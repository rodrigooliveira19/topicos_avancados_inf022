package br.com.domain.app.minhagelada.interfaceApi;

import java.util.List;

import br.com.domain.app.minhagelada.entidades.Filtro;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceHolderApiFiltro {

    @GET("filtros/")
    Call<List<Filtro>> getFiltros();

    @FormUrlEncoded
    @POST("cadastrarFiltro/")
    Call<Filtro> createFiltro(@Field("descricao") String descricao);

    @POST("atualizarFiltro/")
    Call<Filtro> updateFiltro(@Body Filtro filtro);

    @POST("excluirFiltro/")
    Call<Filtro> deleteFiltro(@Body Filtro filtro);
}
