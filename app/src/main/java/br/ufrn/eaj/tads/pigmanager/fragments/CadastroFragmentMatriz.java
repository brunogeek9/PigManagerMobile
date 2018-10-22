package br.ufrn.eaj.tads.pigmanager.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.ufrn.eaj.tads.pigmanager.modelo.enums.EnumEstagio.ALEITAMENTO;

/**
 * A simple {@link Fragment} subclass.
*/
public class CadastroFragmentMatriz extends Fragment {
    // TODO: Rename parameter arguments, choose names that

    // TODO: Rename and change types of parameters
    private EditText raca;
    private EditText identificador;
    private EditText peso;
    private EditText dataNascimento;
    private Matriz matriz;
    private Spinner spinner;
    private List<String> lista = new ArrayList<>();

    public CadastroFragmentMatriz() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cadastro_matriz, container, false);

        identificador = view.findViewById(R.id.textIdentificador);
        raca = view.findViewById(R.id.textRaca);
        dataNascimento = view.findViewById(R.id.textData);
        peso = view.findViewById(R.id.textPeso);
        spinner = view.findViewById(R.id.spinner);

        lista.add(EnumEstagio.ALEITAMENTO.toString());
        lista.add(EnumEstagio.DESCANSO.toString());
        lista.add(EnumEstagio.GRAVIDA.toString());
        lista.add(EnumEstagio.PRENHA.toString());

        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, lista);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button butao = view.findViewById(R.id.botaoCadastrarMatriz);
        butao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try {
                    matriz = new Matriz();
                    matriz.setIdentificador(Double.valueOf(identificador.getText().toString()));
                    matriz.setRaca(raca.getText().toString());
                    matriz.setPeso(Double.valueOf(peso.getText().toString()));
                    matriz.setArquivo("TSADSASDA");
                    matriz.setEstagio(EnumEstagio.valueOf(spinner.getSelectedItem().toString()));
                    matriz.setDataNascimento(new SimpleDateFormat("yyyy/MM/dd")
                                    .parse(dataNascimento.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                ;
                Call<Matriz> call = new RetrofitConfig().getMatrizService().cadastrarMatriz(matriz);

                call.enqueue(new Callback<Matriz>() {
                    @Override
                    public void onResponse(Call<Matriz> call, Response<Matriz> response) {
                        Log.i("PA1", "CLICOU");
                        if (response.isSuccessful())
                            Toast.makeText(getActivity(), "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Matriz> call, Throwable t) {
                        Log.i("PA1", t.getMessage());
                        Toast.makeText(getActivity(), "Erro ao salvar: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        return view;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
