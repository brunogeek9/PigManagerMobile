package br.ufrn.eaj.tads.pigmanager.util;

import android.util.Log;

import br.ufrn.eaj.tads.pigmanager.modelo.Usuario;
import br.ufrn.eaj.tads.pigmanager.retrofit.RetrofitConfig;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


//Classe abstrata com métodos estáticos para auxliar

public abstract class MetodosUsuario {

    public static void deletarUsuario(Usuario usuario) {

        /* DELETE */

        Call<ResponseBody> call2 = new RetrofitConfig().getUsuarioService().deletaUsuario(usuario.getId());
        call2.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.i("PA1", "Deletrou usuário 2");
                //if(response.isSuccessful())
                    //sucesso = true;
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("PA1", "Falha ao deletar usuário 2");
                //sucesso = false;
            }
        });

    }

    public static void inserirUsuario(Usuario usuario){

    }

    public static void editarUsuario(Usuario usuario){

    }
}
