package br.ufrn.eaj.tads.pigmanager.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import br.ufrn.eaj.tads.pigmanager.R;
import br.ufrn.eaj.tads.pigmanager.modelo.Usuario;
import br.ufrn.eaj.tads.pigmanager.retrofit.RetrofitConfig;
import br.ufrn.eaj.tads.pigmanager.util.MetodosUsuario;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditarFragment extends Fragment {

    Usuario usuario;

    public EditarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_editar, container, false);

        final EditText textNome = view.findViewById(R.id.textNomeEditar);
        final EditText textSenha = view.findViewById(R.id.textSenhaEditar);
        final EditText textEmail = view.findViewById(R.id.textEmailEditar);

        //MetodosUsuario utilUsuario = new MetodosUsuario();

        Log.i("TESTE","EDITAR FRAGMENT: " + MetodosUsuario.usuario.getNome());

        usuario = MetodosUsuario.usuario;

        textNome.setText(usuario.getNome());
        textSenha.setText(usuario.getSenha());
        textEmail.setText(usuario.getEmail());


        Button botaoEditar = view.findViewById(R.id.botaoEditar);

        botaoEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Usuario novoUsuario = new Usuario();
                novoUsuario.setNome(textNome.getText().toString());
                novoUsuario.setEmail(textEmail.getText().toString());
                novoUsuario.setSenha(textSenha.getText().toString());
                novoUsuario.setId(usuario.getId());

                Log.i("TESTE","Nome novo: "+ textNome.getText().toString());
                Log.i("TESTE","ID Usuario antigo: "+ usuario.getId());

                Call<ResponseBody> call = new RetrofitConfig().getUsuarioService().editarUsuario(novoUsuario);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.i("TESTE", "Editou com sucesso!");
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.i("TESTE","Falha ao editar");
                    }
                });
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}
