package ma.ormvasm.factei;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.app.FragmentTransaction;

import ma.ormvasm.factei.DAO.Autorisation;
import ma.ormvasm.factei.DAO.AutorisationDAO;
import ma.ormvasm.factei.DAO.Parametre;
import ma.ormvasm.factei.DAO.ParametreDAO;
import ma.ormvasm.factei.DAO.Utilisateur;
import ma.ormvasm.factei.DAO.UtilisateurDAO;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Context context;

    ListeReleveindex lr;
    SaisieReleveindex sr;
    FragmentSettings fs;
    FragmentListeUtilisateur fu;
    FragmentImportData fi;
    FragmentExportData fe;
    FragmentListePrise fp;
    Bundle args;

    Utilisateur usr;
    String groupe_encours="";
    Autorisation aut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        getFragmentManager().beginTransaction()
                .replace(R.id.content_frame
                        , new FragmentAcceuil())

                .commit();

        toggle.setDrawerIndicatorEnabled(true);



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
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction;



        if (id == R.id.nav_listeReleves) {
            lr = new ListeReleveindex();
            args = new Bundle();
            args.putString("valide", "0");
            lr.setArguments(args);
            fm.beginTransaction()
                    .replace(R.id.content_frame
                            , lr)
                    .addToBackStack(null)
                    .commit();

        }
        if (id == R.id.nav_listeAnciensReleves) {
            lr = new ListeReleveindex();
            args = new Bundle();
            args.putString("valide", "1");
            lr.setArguments(args);
            fm.beginTransaction()
                    .replace(R.id.content_frame
                            , lr)
                    .addToBackStack(null)
                    .commit();

        }
        else
        if (id == R.id.nav_saisie_releve) {
            sr = new SaisieReleveindex();
            fm.beginTransaction()
                    .replace(R.id.content_frame
                            , sr)
                    .addToBackStack(null)
                    .commit();


        }
        else
        if (id == R.id.nav_listePrises) {
            fp = new FragmentListePrise();
            fm.beginTransaction()
                    .replace(R.id.content_frame
                            , fp)
                    .addToBackStack(null)
                    .commit();


        }
        else
        if (id == R.id.nav_parametres) {
            fs = new FragmentSettings();
            fm.beginTransaction()
                    .replace(R.id.content_frame
                            , fs)
                    .addToBackStack(null)
                    .commit();


        }
        else
        if (id == R.id.nav_utilisateurs) {
            fu = new FragmentListeUtilisateur();
            fm.beginTransaction()
                    .replace(R.id.content_frame
                            , fu)
                    .addToBackStack(null)
                    .commit();


        }
        else
        if (id == R.id.nav_import) {
            fi = new FragmentImportData();
            fm.beginTransaction()
                    .replace(R.id.content_frame
                            , fi)
                    .addToBackStack(null)
                    .commit();


        }
        else
        if (id == R.id.nav_export) {
            fe = new FragmentExportData();
            fm.beginTransaction()
                    .replace(R.id.content_frame
                            , fe)
                    .addToBackStack(null)
                    .commit();


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void saisiereleveindex(View v) {

        Intent i = new Intent(MainActivity.this, SaisieReleveindex.class);
        startActivity(i);

    }
    public void listereleveindex(View v) {

        Intent i = new Intent(MainActivity.this, ListeReleveindex.class);
        startActivity(i);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Log.d(TAG, "MainActivity.onOptionsItemSelected");
        getMenuInflater().inflate(R.menu.activity_menu_drawer, menu);

        //updateMenu(menu,MainActivity.this);

        MenuItem mnItem2  = menu.findItem(R.id.nav_listeAnciensReleves);
        //aut=Helper.getAutorisation("ListeReleveindexAnc",MainActivity.this);

        mnItem2.setVisible(false);




        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }


    private void updateMenu(Menu menu,Context ctx){

        boolean b;

        MenuItem mnItem1 = menu.findItem(R.id.nav_listeReleves);
        aut=Helper.getAutorisation("ListeReleveindex",ctx);
        b=(aut.getDroit_access().equals("Y"));
        mnItem1.setVisible(b);

        MenuItem mnItem2  = menu.findItem(R.id.nav_listeAnciensReleves);
        aut=Helper.getAutorisation("ListeReleveindexAnc",ctx);
        b=(aut.getDroit_access().equals("Y"));
        mnItem2.setVisible(false);

        MenuItem mnItem = menu.findItem(R.id.nav_saisie_releve);
        aut=Helper.getAutorisation("SaisieReleveindex",ctx);
        mnItem.setVisible((aut.getDroit_access().equals("Y")));

        mnItem = menu.findItem(R.id.nav_listePrises);
        aut=Helper.getAutorisation("FragmentListePrise",ctx);
        mnItem.setVisible((aut.getDroit_access().equals("Y")));

        mnItem = menu.findItem(R.id.nav_parametres);
        aut=Helper.getAutorisation("FragmentSettings",ctx);
        mnItem.setVisible((aut.getDroit_access().equals("Y")));

        mnItem = menu.findItem(R.id.nav_utilisateurs);
        aut=Helper.getAutorisation("FragmentListeUtilisateur",ctx);
        mnItem.setVisible((aut.getDroit_access().equals("Y")));

        mnItem = menu.findItem(R.id.nav_import);
        aut=Helper.getAutorisation("FragmentImportData",ctx);
        mnItem.setVisible((aut.getDroit_access().equals("Y")));

        mnItem = menu.findItem(R.id.nav_export);
        aut=Helper.getAutorisation("FragmentExportData",ctx);
        mnItem.setVisible((aut.getDroit_access().equals("Y")));




    }



}
