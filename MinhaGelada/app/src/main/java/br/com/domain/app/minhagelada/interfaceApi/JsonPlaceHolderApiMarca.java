package br.com.domain.app.minhagelada.interfaceApi;

import java.util.List;

import br.com.domain.app.minhagelada.entidades.Marca;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApiMarca {

    @GET("marcas/")
    Call<List<Marca>> getMarcas();
}
