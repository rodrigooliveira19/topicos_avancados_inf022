package br.com.domain.app.minhagelada.interfaceApi;

import java.util.List;

import br.com.domain.app.minhagelada.entidades.Estabelecimento;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApiEstabelecimento {

    @GET("estabelecimentos/")
    Call<List<Estabelecimento>> getEstabelecimentos();
}
