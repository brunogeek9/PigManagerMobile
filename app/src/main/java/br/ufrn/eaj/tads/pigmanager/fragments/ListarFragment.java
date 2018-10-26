package br.ufrn.eaj.tads.pigmanager.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.eaj.tads.pigmanager.R;
import br.ufrn.eaj.tads.pigmanager.modelo.Matriz;
import br.ufrn.eaj.tads.pigmanager.modelo.Usuario;
import br.ufrn.eaj.tads.pigmanager.retrofit.RetrofitConfig;
import br.ufrn.eaj.tads.pigmanager.servicos.ServicoUsuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListarFragment extends Fragment {
    private RetrofitConfig retrofitConfig;
    private List<Usuario> listaUsuario = new ArrayList<>();

    public ListarFragment() {
        // Required empty public constructor
    }


    public void listarUsuariosRetrofit(){
        ServicoUsuario servicoUsuario = (ServicoUsuario) retrofitConfig.getUsuarioService().listarUsuarios();
        Call<List<Usuario>> call = servicoUsuario.listarUsuarios();
        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if(response.isSuccessful()){
                    listaUsuario = response.body();

                    for (int i = 0; i<listaUsuario.size(); i++){
                        Usuario usuario = listaUsuario.get(i);
                        Log.i("LISTA DE USUARIOS", "USUARIOS CADASTRADOS" + usuario.getId() + usuario.getNome() + usuario.getSenha() + usuario.getEmail());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Log.i("LISTA DE USUARIOS", "Falha ao listarr");
                Log.i("LISTA DE USUARIOS","Erro: "+t.getMessage());

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        //listarUsuariosRetrofit();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_listar, container, false);

    }

}
