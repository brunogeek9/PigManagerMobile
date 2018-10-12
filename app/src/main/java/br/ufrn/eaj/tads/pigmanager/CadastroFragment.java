package br.ufrn.eaj.tads.pigmanager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import br.ufrn.eaj.tads.pigmanager.modelo.Usuario;
import br.ufrn.eaj.tads.pigmanager.retrofit.RetrofitConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class CadastroFragment extends Fragment {


    public CadastroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cadastro, container, false);



        final EditText textNome = view.findViewById(R.id.textNome);
        final EditText textSenha = view.findViewById(R.id.textSenha);
        final EditText textEmail = view.findViewById(R.id.textEmail);

        final Button botaoCadastrar = view.findViewById(R.id.botaoCadastrar);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nome = textNome.getText().toString();
                String senha = textSenha.getText().toString();
                String email = textEmail.getText().toString();

                //Criando um objeto de usuário com os dados que foram preenchidos
                //para inserir no método POST
                Usuario usuario = new Usuario(nome,email,senha);

                //Enviando um objeto de Usuário para ser cadastrado
                Call<Usuario> call = new RetrofitConfig().getUsuarioService().cadastrarUsuario(usuario);
                //Fazendo uma chamada assíncrona para não prejudar a UI Thread
                call.enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        //Método que trata a resposta

                        //Caso a resposta seja bem sucedida
                        if(response.isSuccessful()){
                            Log.i("PA1", "Inseriu com sucesso");

                        }
                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        //Método que trata a falha
                        Log.i("PA1", "Falha ao inserir");
                        Log.i("PA1","Erro: "+t.getMessage());
                    }
                });

                //Exemplo de GET
//
//                Call<Usuario> call = new RetrofitConfig().getUsuarioService().buscarUsuario("1");
//                call.enqueue(new Callback<Usuario>() {
//                    @Override
//                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
//                        Usuario usuario = response.body();
//                        Log.i("PA1", usuario.getNome());
//                    }
//
//                    @Override
//                    public void onFailure(Call<Usuario> call, Throwable t) {
//                        Log.i("PA1","Falhou"+t.getMessage());
//                    }
//                });
            }
        });


        // Inflate the layout for this fragment
        return view;
    }

}
