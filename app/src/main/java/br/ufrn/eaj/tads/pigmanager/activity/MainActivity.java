package br.ufrn.eaj.tads.pigmanager.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import br.ufrn.eaj.tads.pigmanager.R;
import br.ufrn.eaj.tads.pigmanager.fragments.ConfiguracaoFragment;
import br.ufrn.eaj.tads.pigmanager.fragments.InicioFragment;
import br.ufrn.eaj.tads.pigmanager.fragments.ListarUsuarioFragment;
import br.ufrn.eaj.tads.pigmanager.fragments.ListarMatrizFragment;
import br.ufrn.eaj.tads.pigmanager.helper.BottomNavigationViewHelper;

public class MainActivity extends AppCompatActivity {
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomnav = findViewById(R.id.bottom_navigation);
        //Desativar animação
        BottomNavigationViewHelper.disableShiftMode(bottomnav);
        bottomnav.setOnNavigationItemSelectedListener(navListenner);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ListarMatrizFragment()).commit();



    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListenner = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment fragmentSelecionado = null;

            switch (item.getItemId()){
//                case R.id.nav_home:
//                    fragmentSelecionado = new InicioFragment();
//                    break;
                case R.id.nav_listUsuario:
                    fragmentSelecionado = new ListarUsuarioFragment();
                    break;
                case R.id.nav_listPig:
                    fragmentSelecionado = new ListarMatrizFragment();
                    break;
                case R.id.nav_settings:
                    fragmentSelecionado = new ConfiguracaoFragment();
                    break;

            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentSelecionado).commit();
            return true;
        }
    };

}
