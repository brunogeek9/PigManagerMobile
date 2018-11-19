package br.ufrn.eaj.tads.pigmanager.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;

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

        Toolbar tool = findViewById(R.id.toolbar);
        tool.setTitle("PigManager");
        setSupportActionBar(tool);

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

               /* case R.id.nav_home:
                    fragmentSelecionado = new ListarMatrizFragment();
                    break;
                    */
                case R.id.nav_settings:
                    fragmentSelecionado = new ConfiguracaoFragment();
                    break;

            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentSelecionado).commit();
            return true;
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sair, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //AuthUI.getInstance().signOut(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
