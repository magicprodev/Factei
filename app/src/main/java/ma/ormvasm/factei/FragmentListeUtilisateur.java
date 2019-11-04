package ma.ormvasm.factei;

import android.app.Fragment;
import android.app.SearchManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


import android.app.Fragment;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ma.ormvasm.factei.DAO.Parametre;
import ma.ormvasm.factei.DAO.ParametreDAO;
import ma.ormvasm.factei.DAO.Prise;
import ma.ormvasm.factei.DAO.Utilisateur;
import ma.ormvasm.factei.DAO.UtilisateurDAO;

public class FragmentListeUtilisateur extends Fragment {
    TextView textView = null;
    ProgressBar mPogressBar = null;
    ListView utilisateurListView = null;
    List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
    View lastView = null;
    int position = -1;
    private Menu menu;
    UtilisateurListViewAdapter adapter;
    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;
    private String stCond_save = "";
    private String urlString ="";
    Parametre p;
    ParametreDAO pdao ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        View myView;
        myView = inflater.inflate(R.layout.activity_fragment_liste_utilisateur, container, false);

        //menuMode = "consult";

        Toolbar toolbar = myView.findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        textView = myView.findViewById(R.id.textView);
        mPogressBar = (ProgressBar) myView.findViewById(R.id.progressBar);
        utilisateurListView = (ListView) myView.findViewById(R.id.utilisateurListView);

        getActivity().setTitle(getString(R.string.liste_utilisateur));

        myView.setFocusableInTouchMode(true);
        myView.requestFocus();

        ViewGroup headerView = (ViewGroup) inflater.inflate(R.layout.custom_row_utilisateur_header, utilisateurListView, false);

        utilisateurListView.addHeaderView(headerView, null, false);


        readData("");


        return myView;

    }


    private void readData(String stCond) {
        mPogressBar.setVisibility(View.VISIBLE);
        UtilisateurDAO usr = new UtilisateurDAO(getActivity());
        usr.setCondUtilisateur(stCond);
        utilisateurs = usr.getAllData();
        adapter = new UtilisateurListViewAdapter(utilisateurs, getActivity());
        utilisateurListView.setAdapter(adapter);
        mPogressBar.setVisibility(View.GONE);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        this.menu = menu;
        //updateUI("consult");
        inflater.inflate(R.menu.menu_list, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchItem.setVisible(false);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                new getAllUsersTask().execute(new ApiConnector());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class getAllUsersTask extends AsyncTask<ApiConnector,Long, JSONArray> {
        private boolean serverOK = true ;
        @Override
        protected void onPreExecute() {
             mPogressBar.setVisibility(View.VISIBLE);
        }
        @Override
        protected JSONArray doInBackground(ApiConnector... params) {
            pdao =new ParametreDAO(getActivity());
            p=pdao.getData("IP_SERVEUR");
            String serv =p.getValeur_parametre();
            p=pdao.getData("CODE_CMV");
            String cmv =p.getValeur_parametre();
            urlString = serv + "/datalist/?cmd=userslist&cmv=" +cmv+"&offset=0";

            if (ApiConnector.isServerAlive(urlString)==false){
                serverOK = false;
                return null;
            }
            else {
                ArrayList<Utilisateur> resp=new ArrayList<Utilisateur>();
                JSONArray jsonArray = params[0].getData(Utilisateur.class,urlString,resp);
                serverOK = true;
                return jsonArray;}
        }
        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            final UtilisateurDAO udao =new UtilisateurDAO(getActivity());
            if (serverOK){
                if (jsonArray == null) {
                    Helper.showMessage(getActivity(), getString(R.string.donnees_non_trouvees), getString(R.string.title_err_import), R.drawable.ic_error_red);
                }
                else{

                    udao.InsererUtilisateursFromJson(jsonArray,getActivity());
                    readData("");}
                    //Helper.showMessage(getActivity(),getString(R.string.donnees_importees_avec_succes),getString(R.string.message),R.drawable.ic_success_green);
            }
            else {
                Helper.showMessage(getActivity(),getString(R.string.probleme_connexion),getString(R.string.title_err_import),R.drawable.ic_error_red);
            }
            mPogressBar.setVisibility(View.GONE);
        }
    }

}

