package br.ufrn.eaj.tads.pigmanager.util;

import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.Toast;

import br.ufrn.eaj.tads.pigmanager.modelo.Matriz;
import br.ufrn.eaj.tads.pigmanager.retrofit.RetrofitConfig;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class MetodosMatriz {

    public static Matriz matriz;

    //Deletar uma Matriz
    public static void DeletarMatriz(Matriz matriz){

        Call<ResponseBody> callDeletar = new RetrofitConfig().getMatrizService().removeMatriz(matriz.getId());

        callDeletar.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.i("PA1", "Deletou Matriz");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("PA1", "Não Deletou Matriz");
            }
        });


    }

    public static void AlterarMatriz(Matriz matriz){

    }


/*

        //Atualizar uma Matriz

        atualizarMatriz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ).commit();
            }
        });

*/



}
