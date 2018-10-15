package br.ufrn.eaj.tads.pigmanager.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.ufrn.eaj.tads.pigmanager.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConfiguracaoFragment extends Fragment {


    public ConfiguracaoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //inflando a view para retorna-lá depois
        View view = inflater.inflate(R.layout.fragment_configuracao, container, false);




        // Inflate the layout for this fragment
        return view;
    }

}
