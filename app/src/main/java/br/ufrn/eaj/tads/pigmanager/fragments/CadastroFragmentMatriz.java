package br.ufrn.eaj.tads.pigmanager.fragments;

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

import java.io.IOException;
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

/**
 * Classe responsável por mostrar o form
 * de cadastro de matriz e de trata-lo
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
        spinner = view.findViewById(R.id.Estagio);

        /* Adicionando os estagios de uma matriz no spinner
           para que o usuário escolha em qual estágio a matriz se encontra
           Se adicionar um novo estágio, adicione no SPINNER
         */
        lista.add(EnumEstagio.COBERTA.toString());
        lista.add(EnumEstagio.PRENHES.toString());
        lista.add(EnumEstagio.LACTACAO.toString());
        lista.add(EnumEstagio.VAZIA.toString());
        /* Fim da adição dos estagios  */

        /* Adapter para o spinner mostrar na tela os estagios da matriz
        * (OBRIGATORIO PARA O SPINNER)*/
        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, lista);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        /* Fim do Adapter*/

        Button butao = view.findViewById(R.id.botaoCadastrarMatriz);
        /* Tratando o clique no botão de cadastrar*/
        butao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    // TODO: 23/10/2018 Tratar quando o usuário não preencher os todos os campos
                    /* Pegando os dados necessários da view para mandar a requisição para o serviço
                    * (Necessário o bloco try catch porque o metodo parse da Classe SimpleDateFormat
                    * lança uma exceção)*/
                    matriz = new Matriz();
                    matriz.setIdentificador(Double.valueOf(identificador.getText().toString()));
                    matriz.setRaca(raca.getText().toString());
                    matriz.setPeso(Double.valueOf(peso.getText().toString()));
                    matriz.setArquivo("TSADSASDA");
                    matriz.setEstagio(EnumEstagio.valueOf(spinner.getSelectedItem().toString()));
                    matriz.setDataNascimento(new SimpleDateFormat("yyyy-MM-dd")
                            .parse(dataNascimento.getText().toString()));

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                /*Fim da adição dos dados ao objetos matriz*/

                Call<Matriz> call = new RetrofitConfig().getMatrizService().cadastrarMatriz(matriz);

                call.enqueue(new Callback<Matriz>() {
                    @Override
                    public void onResponse(Call<Matriz> call, Response<Matriz> response) {
                        Log.i("PA1", "CLICOU");
                        if (response.isSuccessful())
                            Toast.makeText(getActivity(), "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                        else {
                            try {
                                Log.i("PA1", response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
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
}
