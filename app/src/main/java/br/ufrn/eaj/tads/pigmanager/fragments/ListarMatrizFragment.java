package br.ufrn.eaj.tads.pigmanager.fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;
import java.io.IOException;
import java.util.List;
import br.ufrn.eaj.tads.pigmanager.adapter.MatrizAdapter;
import br.ufrn.eaj.tads.pigmanager.R;
import br.ufrn.eaj.tads.pigmanager.modelo.Matriz;
import br.ufrn.eaj.tads.pigmanager.retrofit.RetrofitConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListarMatrizFragment extends Fragment {

    private List<Matriz> listaMatriz;
    RecyclerView recyMatriz;
    FloatingActionButton fab;


    public ListarMatrizFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view  = inflater.inflate(R.layout.fragment_listar_matriz, container, false);

        recyMatriz = view.findViewById(R.id.recyclerViewMatriz);
        fab = view.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CadastroFragmentMatriz()).commit();

                Snackbar.make(view, "Cadastrar Nova Matriz", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        RecyclerView.LayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        recyMatriz.setLayoutManager(layout);
        recyMatriz.setItemAnimator(new DefaultItemAnimator());

        Call<List<Matriz>> call = new RetrofitConfig().getMatrizService().listarMatrizes();

        call.enqueue(new Callback<List<Matriz>>() {
            @Override
            public void onResponse(Call<List<Matriz>> call, Response<List<Matriz>> response) {
                Log.i("PA1", "Entrou no Listar Matriz");

                if (response.isSuccessful()) {
                    listaMatriz = response.body();

                    recyMatriz.setAdapter(new MatrizAdapter(listaMatriz,getContext()));

                    Log.i("PA1", "Só Sucesso!!");
                    Toast.makeText(getContext(), "Só Sucesso!!"+"Matrizes na Lista:"+listaMatriz.size(), Toast.LENGTH_SHORT).show();
                }else {
                    try {
                        Log.i("PA1", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<List<Matriz>> call, Throwable t) {
                Log.i("PA1", "Falhou no Listar Matriz");
                Toast.makeText(getContext(), "Falhou no Listar Matriz", Toast.LENGTH_SHORT).show();
            }
        });




        return view;

    }

}
