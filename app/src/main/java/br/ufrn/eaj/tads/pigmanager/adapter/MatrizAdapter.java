package br.ufrn.eaj.tads.pigmanager.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import br.ufrn.eaj.tads.pigmanager.R;
import br.ufrn.eaj.tads.pigmanager.fragments.EditarMatrizFragment;
import br.ufrn.eaj.tads.pigmanager.modelo.Matriz;
import br.ufrn.eaj.tads.pigmanager.util.MetodosMatriz;
import de.hdodenhof.circleimageview.CircleImageView;

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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        MatrizViewHolder mvh = (MatrizViewHolder) holder;

        matrizEscolhida = listaMatrizes.get(position);

        mvh.identificador.setText(""+(int) matrizEscolhida.getIdentificador());
        mvh.img.setImageResource(R.drawable.porco1);
        mvh.estagio.setText(""+matrizEscolhida.getEstagio());


        mvh.deletarM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletarMatriz(position);
            }
        });


        mvh.atualizarM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Matriz matriz = listaMatrizes.get(position);

                MetodosMatriz.matriz = matriz;

                Log.i("TESTE", "MATRIZ ADAPTER: Matriz: " + MetodosMatriz.matriz.getIdentificador());

                Fragment fragmentEditar = new EditarMatrizFragment();


                //Salvar esse trecho de código, pois ele é maravilhoso.
                ((FragmentActivity) v.getContext()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragmentEditar)
                        .commit();

            }
        });



    }

    @Override
    public int getItemCount() {
        return listaMatrizes == null? 0 : listaMatrizes.size();
    }




    private void deletarMatriz(int position) {

        MetodosMatriz.DeletarMatriz(listaMatrizes.get(position));

        listaMatrizes.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listaMatrizes.size());
    }







    public class MatrizViewHolder extends RecyclerView.ViewHolder {

        final CircleImageView img;
        final TextView identificador;
        final TextView estagio;
        final ImageButton deletarM;
        final ImageButton atualizarM;

        public MatrizViewHolder(View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.imgk);
            identificador = itemView.findViewById(R.id.identificadork);
            estagio = itemView.findViewById(R.id.estagiok);
            deletarM = itemView.findViewById(R.id.deletarItem);
            atualizarM = itemView.findViewById(R.id.atualizarItem);

        }
    }

}
