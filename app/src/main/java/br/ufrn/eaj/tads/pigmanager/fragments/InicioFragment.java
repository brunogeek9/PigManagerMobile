package br.ufrn.eaj.tads.pigmanager.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.ufrn.eaj.tads.pigmanager.R;

//import android.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class InicioFragment extends Fragment {


    public InicioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_inicio, container, false);
        CardView card2 = view.findViewById(R.id.card2);
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CadastroFragmentMatriz())
                        .commit();
            }
        });

        CardView card1 = view.findViewById(R.id.card1);
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("PA1","Clicou no card de cadastro");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CadastroFragment()).commit();
            }
        });



        CardView card3 = view.findViewById(R.id.card3);
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("PA1","Clicou no card de cadastro");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ListarMatrizFragment()).commit();
            }
        });


//        ImageButton botaoCadastro = view.findViewById(R.id.buttonCadastro);
//        botaoCadastro.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.i("PA1", "Clicou no botão de cadastro");
//
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CadastroFragment()).commit();
//
//            }
//        });


        // Inflate the layout for this fragment
        return view;
    }

}
