package br.ufrn.eaj.tads.pigmanager.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.ufrn.eaj.tads.pigmanager.R;
import br.ufrn.eaj.tads.pigmanager.modelo.Matriz;

public class MatrizAdapter extends RecyclerView.Adapter {

    Matriz matrizEscolhida;
    List<Matriz> listaMatrizes;
    Context context;


    public MatrizAdapter(List<Matriz> listaMatrizes, Context context) {
        this.listaMatrizes = listaMatrizes;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.inflate_lista_matriz,parent,false);

        MatrizViewHolder mvh = new MatrizViewHolder(view);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MatrizViewHolder mvh = (MatrizViewHolder) holder;

        matrizEscolhida = listaMatrizes.get(position);

        mvh.identificador.setText(""+matrizEscolhida.getIdentificador());
        mvh.img.setImageResource(R.drawable.porco1);
        mvh.peso.setText(""+matrizEscolhida.getPeso());
        mvh.raca.setText(matrizEscolhida.getRaca());
    }

    @Override
    public int getItemCount() {
        return listaMatrizes == null? 0 : listaMatrizes.size();
    }


    public class MatrizViewHolder extends RecyclerView.ViewHolder {

        final ImageView img;
        final TextView identificador;
        final TextView raca;
        final TextView peso;


        public MatrizViewHolder(View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img);
            identificador = itemView.findViewById(R.id.identificador);
            raca = itemView.findViewById(R.id.raca);
            peso = itemView.findViewById(R.id.peso);
        }
    }

}
