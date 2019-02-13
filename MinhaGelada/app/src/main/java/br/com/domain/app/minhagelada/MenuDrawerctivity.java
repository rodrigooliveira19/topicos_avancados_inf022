package br.com.domain.app.minhagelada;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import br.com.domain.app.minhagelada.listagem.ListaDeEstabelecimentosActivity;

public class MenuDrawerctivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawerctivity);
        Toolbar toolbar = /*(Toolbar)*/ findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = /*(FloatingActionButton)*/ findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_estabelecimento) {
            startActivity(new Intent(MenuDrawerctivity.this,
                                                    EstabelecimentoActivity.class));
        } else if (id == R.id.nav_marca) {
            startActivity(new Intent(MenuDrawerctivity.this,
                                                    MarcaActivity.class));

        } else if (id == R.id.nav_unidade) {
            startActivity(new Intent(MenuDrawerctivity.this,
                                                    UnidadeActivity.class));

        } else if (id == R.id.nav_filtro) {
            startActivity(new Intent(MenuDrawerctivity.this,
                                                    FiltroActivity.class));

        } else if (id == R.id.nav_cesta) {
            Toast.makeText(getApplicationContext(), "Toquei cesta",
                    Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MenuDrawerctivity.this,
                                                   ListaDeEstabelecimentosActivity.class));

        } /*else if (id == R.id.nav_send) {
            Toast.makeText(getApplicationContext(), "Toquei na send",
                    Toast.LENGTH_SHORT).show();

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
