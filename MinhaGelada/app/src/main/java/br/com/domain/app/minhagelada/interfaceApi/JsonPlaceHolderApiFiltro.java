package br.com.domain.app.minhagelada.interfaceApi;

import java.util.List;

import br.com.domain.app.minhagelada.entidades.Filtro;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApiFiltro {

    @GET("filtros/")
    Call<List<Filtro>> getFiltros();
}
