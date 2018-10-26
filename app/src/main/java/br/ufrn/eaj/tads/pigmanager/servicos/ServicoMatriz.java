package br.ufrn.eaj.tads.pigmanager.servicos;

import java.util.List;

import br.ufrn.eaj.tads.pigmanager.modelo.Matriz;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServicoMatriz {

    @POST("matriz/")
    public Call<Matriz> cadastrarMatriz(@Body Matriz matriz);

    @GET("matriz")
    Call<List<Matriz>> listarMatrizes();
}
