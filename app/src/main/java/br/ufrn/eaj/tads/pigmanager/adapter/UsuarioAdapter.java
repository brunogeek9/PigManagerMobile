package br.ufrn.eaj.tads.pigmanager.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import br.ufrn.eaj.tads.pigmanager.fragments.EditarFragment;
import br.ufrn.eaj.tads.pigmanager.R;
import br.ufrn.eaj.tads.pigmanager.modelo.Usuario;
import br.ufrn.eaj.tads.pigmanager.util.MetodosUsuario;

public class UsuarioAdapter extends RecyclerView.Adapter {
    Usuario usuario;
    List<Usuario> listarUsuario;
    Context context;

    public UsuarioAdapter(List<Usuario> listarUsuario, Context context) {
        this.listarUsuario = listarUsuario;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.inflate_lista_usuario, parent, false);
        UsuarioViewHolder usuarioViewHolder = new UsuarioViewHolder(view);

        return usuarioViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        UsuarioViewHolder usuarioViewHolder = (UsuarioViewHolder) holder;

        usuario = listarUsuario.get(position);

        usuarioViewHolder.nome.setText("" + usuario.getNome());
        usuarioViewHolder.email.setText("" + usuario.getEmail());
        usuarioViewHolder.senha.setText("" + usuario.getSenha());

        usuarioViewHolder.imgDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletarUsuario(position);
            }
        });

        usuarioViewHolder.imgEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //editarUsuario(position);

                Usuario usuario = listarUsuario.get(position);

                /*
                Bundle dados = new Bundle();
                dados.putString("nome", usuario.getNome());
                dados.putString("email", usuario.getEmail());
                dados.putString("senha", usuario.getSenha());
                dados.putString("id", usuario.getId().toString());

                Fragment fragment = new EditarFragment();
                fragment.setArguments(dados);
                */

                //MetodosUsuario utilUsuario = new MetodosUsuario();
                //utilUsuario.setUsuario(usuario);

                MetodosUsuario.usuario = usuario;

                //Implementar a troca de fragment

                //context.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentSelecionado).commit();

                // Create new fragment and transaction
//                Fragment fragment = new EditarFragment();
//                android.support.v4.app.FragmentManager fm2 =
//                FragmentTransaction ft2 = fm2.beginTransaction();
//                // Replace whatever is in the fragment_container view with this fragment,
//                // and add the transaction to the back stack
//                ft2.replace(R.id.layout_alvo, newFragment);
//                ft2.addToBackStack(null);
//                // Commit the transaction
//                ft2.commit();

                Log.i("TESTE", "USUARIO ADAPTER: Usuário: " + MetodosUsuario.usuario.getNome());

                Fragment fragment = new EditarFragment();


                //Salvar esse trecho de código, pois ele é maravilhoso.
                ((FragmentActivity) v.getContext()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listarUsuario == null ? 0 : listarUsuario.size();
    }


    private void deletarUsuario(int position) {

        //listarUsuario.remove(usuario);
        MetodosUsuario.deletarUsuario(listarUsuario.get(position));

        listarUsuario.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listarUsuario.size());
    }

    private void editarUsuario(int position){
        //MetodosUsuario.editarUsuario(listarUsuario.get(position));
        /*
        Usuario usuario = listarUsuario.get(position);
        Bundle dados = new Bundle();
        dados.putString("nome", usuario.getNome());
        dados.putString("email", usuario.getEmail());
        dados.putString("senha", usuario.getSenha());
        dados.putString("id", usuario.getId().toString());

        Fragment fragment = new EditarFragment();
        fragment.setArguments(dados);
        */


        //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentSelecionado).commit();
    }


    public class UsuarioViewHolder extends RecyclerView.ViewHolder {
        private TextView nome, email, senha;
        private ImageButton imgEditar;
        private ImageButton imgDeletar;

        public UsuarioViewHolder(View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.txtnome);
            email = itemView.findViewById(R.id.txtemail);
            senha = itemView.findViewById(R.id.txtsenha);
            imgEditar = itemView.findViewById(R.id.imgEditar);
            imgDeletar = itemView.findViewById(R.id.imgDeletar);

        }
    }
}
