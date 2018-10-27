package br.ufrn.eaj.tads.pigmanager.servicos;

import java.util.List;

import br.ufrn.eaj.tads.pigmanager.modelo.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServicoUsuario {

    @GET("usuario/{id}")
    Call<Usuario> buscarUsuario(@Path("id") String usuario);

    @GET("usuario/")
    Call<List<Usuario>> listarUsuarios();

    @POST("usuario/")
    Call<Usuario> cadastrarUsuario(@Body Usuario usuario);
}
