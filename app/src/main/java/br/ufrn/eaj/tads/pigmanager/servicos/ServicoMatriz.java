package br.ufrn.eaj.tads.pigmanager.servicos;

import java.util.List;

import br.ufrn.eaj.tads.pigmanager.modelo.Matriz;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ServicoMatriz {

    @POST("matriz/")
    public Call<Matriz> cadastrarMatriz(@Body Matriz matriz);

    @GET("matriz")
    Call<List<Matriz>> listarMatrizes();

    @PUT("matriz/")
    Call<ResponseBody> editarMatriz(@Body Matriz matriz);

    @DELETE("matriz/{id}")
    Call<ResponseBody> removeMatriz(@Path("id") int id);

}
