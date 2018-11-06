package br.ufrn.eaj.tads.pigmanager.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.ufrn.eaj.tads.pigmanager.adapter.UsuarioAdapter;
import br.ufrn.eaj.tads.pigmanager.R;
import br.ufrn.eaj.tads.pigmanager.modelo.Usuario;
import br.ufrn.eaj.tads.pigmanager.retrofit.RetrofitConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListarUsuarioFragment extends Fragment {

    private List<Usuario> listaUsuario = new ArrayList<>();
    private FloatingActionButton fab;

    public ListarUsuarioFragment() {
        // Required empty public constructor
    }

    /*public void listarUsuariosRetrofit(){
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
    }*/

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listar, container, false);

        fab = view.findViewById(R.id.fabUsuario);

        final RecyclerView recyUsuario = view.findViewById(R.id.recyclerViewUsuario);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyUsuario.setLayoutManager(layoutManager);
        recyUsuario.setItemAnimator(new DefaultItemAnimator());

        Log.i("VDC", "aba listar usuario");

        Call<List<Usuario>> call = new RetrofitConfig().getUsuarioService().listarUsuarios();
        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                Log.i("VDC", "resposta bem sucedida? " +response.isSuccessful());

                if (response.isSuccessful()) {
                    listaUsuario = response.body();
                    recyUsuario.setAdapter(new UsuarioAdapter(listaUsuario,getContext()));


                    //Log.i("PA1", "Só Sucesso!!");
                    //Toast.makeText(getContext(), "Só Sucesso!!"+"Usuários na Lista:"+listaUsuario.size(), Toast.LENGTH_SHORT).show();
                }else {
                    try {
                        Log.i("PA1", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Log.i("VDC", "Falha ao listarr");
                Log.i("VDC","Erro: "+t.getMessage());
                Toast.makeText(getContext(), "Falha ao Listar Usuários", Toast.LENGTH_SHORT).show();

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((FragmentActivity) v.getContext()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new CadastroUsuarioFragment())
                        .commit();
            }
        });

//        recyUsuario.addOnItemTouchListener(new RecyclerUsuarioClique(getContext(), recyUsuario, new RecyclerUsuarioClique.OnItemClickListener() {
//            @Override
//            public void onItemClique(View v, int position) {
//                Log.i("PA1", "Entrou no click o OnListener");
//            }
//        }));

//        Log.i("PA1","TESTE ENTROU");
//        testeId();


       /*final Button bt = view.findViewById(R.id.btteste);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("VDC", "BOTAO LISTAR");

                Call<List<Usuario>> call = new RetrofitConfig().getUsuarioService().listarUsuarios();
                call.enqueue(new Callback<List<Usuario>>() {
                    @Override
                    public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                        Log.i("VDC", "resposta bem sucedida? " +response.isSuccessful());

                        if(response.isSuccessful()){
                           // Log.i("VDC", "passou do if"+ response.isSuccessful());
                            listaUsuario = response.body();

                            for (int i = 0; i<listaUsuario.size(); i++){
                                Usuario usuario = listaUsuario.get(i);
                                Log.i("VDC", "USUARIOS CADASTRADOS" + usuario.getId() + usuario.getNome() + usuario.getSenha() + usuario.getEmail());
                            }
                        }
                    }
                    //teste

                    @Override
                    public void onFailure(Call<List<Usuario>> call, Throwable t) {
                        Log.i("VDC", "Falha ao listarr");
                        Log.i("VDC","Erro: "+t.getMessage());

                    }
                });
            }
        });



        Log.i("VDC", "ENTOU NO CARD DE LISTAR");*/




        return view;

    }

   private void listarUsuarios(){

   }

}
