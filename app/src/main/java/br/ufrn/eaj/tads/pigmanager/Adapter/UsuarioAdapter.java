package br.ufrn.eaj.tads.pigmanager.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.ufrn.eaj.tads.pigmanager.R;
import br.ufrn.eaj.tads.pigmanager.modelo.Usuario;

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
        View view = LayoutInflater.from(context).inflate(R.layout.inflate_lista_usuario,parent,false);
        UsuarioViewHolder usuarioViewHolder = new UsuarioViewHolder(view);

        return usuarioViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        UsuarioViewHolder usuarioViewHolder = (UsuarioViewHolder) holder;

        usuario = listarUsuario.get(position);

        usuarioViewHolder.nome.setText(""+usuario.getNome());
        usuarioViewHolder.email.setText(""+usuario.getEmail());
        usuarioViewHolder.senha.setText(""+usuario.getSenha());
    }

    @Override
    public int getItemCount() {
        return listarUsuario == null? 0: listarUsuario.size();
    }

    public class UsuarioViewHolder extends RecyclerView.ViewHolder{
        private TextView nome,email,senha ;

        public UsuarioViewHolder(View itemView) {
            super(itemView);
             nome = itemView.findViewById(R.id.txtnome);
             email = itemView.findViewById(R.id.txtemail);
             senha = itemView.findViewById(R.id.txtsenha);

        }
    }
}
