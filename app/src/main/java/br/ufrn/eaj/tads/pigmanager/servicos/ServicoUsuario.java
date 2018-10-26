package br.ufrn.eaj.tads.pigmanager.servicos;

import br.ufrn.eaj.tads.pigmanager.modelo.Usuario;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ServicoUsuario {

    @GET("usuario/{id}")
    Call<Usuario> buscarUsuario(@Path("id") String usuario);

    @GET("usuario")
    Call<Usuario> listarUsuarios();

    @POST("usuario/")
    Call<Usuario> cadastrarUsuario(@Body Usuario usuario);

    @DELETE("usuario/{id}")
    Call<ResponseBody> deletaUsuario(@Path("id") int id);

    @PUT("usuario/{id}")
    Call<ResponseBody> editarUsuario(@Path("id") int id);
}
