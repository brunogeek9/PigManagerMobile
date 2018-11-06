package br.ufrn.eaj.tads.pigmanager.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.ufrn.eaj.tads.pigmanager.R;
import br.ufrn.eaj.tads.pigmanager.modelo.Matriz;
import br.ufrn.eaj.tads.pigmanager.modelo.enums.EnumEstagio;
import br.ufrn.eaj.tads.pigmanager.retrofit.RetrofitConfig;
import br.ufrn.eaj.tads.pigmanager.util.MetodosMatriz;
import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.layout.simple_list_item_1;
import static android.R.layout.simple_list_item_2;

/**
 * A simple {@link Fragment} subclass.
 */

public class EditarMatrizFragment extends Fragment {

    Matriz matriz;
    private List<String> listaEstagio = new ArrayList<>();

    public EditarMatrizFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_editar_matriz, container, false);


        final EditText RacaEditar = view.findViewById(R.id.textRacaEditar);
        final EditText IdentificadorEditar = view.findViewById(R.id.textIdentificadorEditar);
        final EditText PesoEditar = view.findViewById(R.id.textPesoEditar);
        final EditText DataEditar = view.findViewById(R.id.textDataEditar);
        final Spinner EstagioEditar = view.findViewById(R.id.EstagioEditar);
        Log.i("TESTE","EDITAR FRAGMENT MATRIZ: " + MetodosMatriz.matriz.getIdentificador());

        matriz = MetodosMatriz.matriz;

        RacaEditar.setText(matriz.getRaca());
        IdentificadorEditar.setText(String.valueOf(matriz.getIdentificador()));
        PesoEditar.setText(String.valueOf(matriz.getPeso()));
        DataEditar.setText(String.valueOf(matriz.getDataNascimento()));


        listaEstagio.add(EnumEstagio.COBERTA.toString());
        listaEstagio.add(EnumEstagio.PRENHES.toString());
        listaEstagio.add(EnumEstagio.LACTACAO.toString());
        listaEstagio.add(EnumEstagio.VAZIA.toString());

        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, listaEstagio);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        EstagioEditar.setAdapter(adapter);

        int kk = 0; //contador

        for (int i = 0;(i <= listaEstagio.size()-1); i++) {

            if(listaEstagio.get(i).equals(matriz.getEstagio().toString())){
                kk = i;
                break;
            }else{
                kk = 0;
            }
            EstagioEditar.setSelection(kk);
        }



        Button AtualizarMatriz = view.findViewById(R.id.botaoAtualizarMatriz);

        AtualizarMatriz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                final View view = v;

                Matriz novaMatriz = new Matriz();

                try {

                novaMatriz.setId(matriz.getId());
                novaMatriz.setIdentificador(Double.parseDouble(IdentificadorEditar.getText().toString()));
                novaMatriz.setRaca(RacaEditar.getText().toString());
                novaMatriz.setPeso(Double.parseDouble(PesoEditar.getText().toString()));
                novaMatriz.setArquivo(matriz.getArquivo());
                novaMatriz.setEstagio(matriz.getEstagio());
                                    novaMatriz.setDataNascimento(new SimpleDateFormat("yyyy-MM-dd")
                            .parse(DataEditar.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                Log.i("TESTE","Identificador novo: "+ IdentificadorEditar.getText().toString());
                Log.i("TESTE","ID Matriz antigo: "+ matriz.getId());

                retrofit2.Call<ResponseBody> call = new RetrofitConfig().getMatrizService().editarMatriz(novaMatriz);

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(retrofit2.Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.i("TESTE", "Editou Matriz com sucesso!");
                        if(response.isSuccessful()){

                            Toast.makeText(getContext(), "Matriz Editada com sucesso!", Toast.LENGTH_SHORT).show();

                            Fragment fragment = new ListarMatrizFragment();
                            ((FragmentActivity) v.getContext()).getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.fragment_container, fragment)
                                    .commit();
                        }
                    }

                    @Override
                    public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {
                        Log.i("TESTE","Falha ao editar a Matriz");
                    }
                });

            }
        });


        return view;

    }

}
