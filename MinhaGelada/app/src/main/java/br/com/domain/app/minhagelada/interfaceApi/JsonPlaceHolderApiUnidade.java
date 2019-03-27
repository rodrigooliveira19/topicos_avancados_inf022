package br.com.domain.app.minhagelada.interfaceApi;

import java.util.List;

import br.com.domain.app.minhagelada.entidades.Unidade;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApiUnidade {

    @GET("unidades/")
    Call<List<Unidade>> getUnidades();
}
