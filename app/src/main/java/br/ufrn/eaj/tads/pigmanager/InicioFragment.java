package br.ufrn.eaj.tads.pigmanager;


import android.media.Image;
import android.os.Bundle;
//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


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


        CardView card1 = view.findViewById(R.id.card1);
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("PA1","Clicou no card de cadastro");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CadastroFragment()).commit();
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
