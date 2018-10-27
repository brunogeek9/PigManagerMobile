package br.ufrn.eaj.tads.pigmanager;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.ufrn.eaj.tads.pigmanager.modelo.Usuario;


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

        // Inflate the layout for this fragment
        return view;
    }

}
