package ma.ormvasm.factei;

import android.app.Fragment;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
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
                // Not implemented here
                refresh();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void refresh(){
        new getAllDartsTask().execute(new ApiConnector());
    }


    private class getAllDartsTask extends AsyncTask<ApiConnector,Long, JSONArray> {
        private boolean serverOK = true ;

        @Override
        protected void onPreExecute() {
            // mPogressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected JSONArray doInBackground(ApiConnector... params) {
            int idClass = -1;
            int idExam = -1;
            int idSalle = -1;
            int temp = spinnerClasse.getSelectedItemPosition();
            if (adapterClasse.getItem(spinnerClasse.getSelectedItemPosition())!=null)
                idClass =  adapterClasse.getItem(spinnerClasse.getSelectedItemPosition()).getIdclasse();
            int temp2 = spinnerExam.getSelectedItemPosition();
            if(spinnerExam.getSelectedItemPosition() != -1 && adapterExam.getItem(spinnerExam.getSelectedItemPosition()) !=null)
                idExam =  adapterExam.getItem(spinnerExam.getSelectedItemPosition()).getIdexam();
            if(spinnerSalle.getSelectedItemPosition() != -1 && adapterSalle.getItem(spinnerSalle.getSelectedItemPosition())!=null)
                idSalle =  adapterSalle.getItem(spinnerSalle.getSelectedItemPosition()).getIdsalle();

            if(idClass != -1 && idExam != -1 &&  idSalle != -1 )
                urlString = urlString + "?cmd=listeEtudiants&classe="+idClass+"&examen="+idExam+"&salle="+idSalle;
            else
                return null;
            if (ApiConnector.isServerAlive(urlString)==false){
                serverOK = false;
                return null;
            }

            else {

                JSONArray jsonArray = params[0].getDataExamenEtudiant(urlString);
                serverOK = true;
                //setListViewAdapter(jsonArray);
                return jsonArray;}
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            if (serverOK){
                if (jsonArray == null)
                    textView.setText("donnees_non_trouvees");
                else
                    //setTextToTextView(jsonArray);
                    setListViewAdapter(jsonArray);}
            else {
                textView.setText("probleme_connexion");
            }

//            mPogressBar.setVisibility(View.GONE);
        }
    }


}

