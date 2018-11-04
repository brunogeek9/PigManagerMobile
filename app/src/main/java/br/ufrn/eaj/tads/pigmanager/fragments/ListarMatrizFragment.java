package br.ufrn.eaj.tads.pigmanager.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    public ListarMatrizFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

    View view  = inflater.inflate(R.layout.fragment_listar_matriz, container, false);



    final RecyclerView recyMatriz = view.findViewById(R.id.recyclerViewMatriz);

        //Date data = new GregorianCalendar(2018, Calendar.JANUARY, 20).getTime();
        //listaMatriz.add(new Matriz(2.0,"KKKK",2.9, data, EnumEstagio.ALEITAMENTO,"dawda"));
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        recyMatriz.setLayoutManager(layout);
        recyMatriz.setItemAnimator(new DefaultItemAnimator());


       //recyMatriz.setAdapter(new MatrizAdapter(listaMatriz,getContext()));


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
