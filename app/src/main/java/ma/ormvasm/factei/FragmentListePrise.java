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
import ma.ormvasm.factei.DAO.PriseDAO;

public class FragmentListePrise extends Fragment {
    TextView textView = null;
    ProgressBar mPogressBar = null;
    ListView priseListView = null;
    List<Prise> prises = new ArrayList<Prise>();
    View lastView = null;
    int position = -1;
    private Menu menu;
    PriseListViewAdapter adapter;
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
        myView = inflater.inflate(R.layout.activity_fragment_liste_prise, container, false);

        //menuMode = "consult";

        Toolbar toolbar = myView.findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        textView = myView.findViewById(R.id.textView);
        mPogressBar = (ProgressBar) myView.findViewById(R.id.progressBar);
        priseListView = (ListView) myView.findViewById(R.id.priseListView);

        getActivity().setTitle(getString(R.string.liste_prise));

        myView.setFocusableInTouchMode(true);
        myView.requestFocus();

        ViewGroup headerView = (ViewGroup) inflater.inflate(R.layout.custom_row_prise_header, priseListView, false);

        priseListView.addHeaderView(headerView, null, false);


        readData("");



        return myView;

    }


    private void readData(String stCond) {
        mPogressBar.setVisibility(View.VISIBLE);
        PriseDAO usr = new PriseDAO(getActivity());
        usr.setCondPrise(stCond);
        prises = usr.getPrises();
        adapter = new PriseListViewAdapter(prises, getActivity());
        priseListView.setAdapter(adapter);
        textView.setText(getString(R.string.nb_total_prises)+" " +usr.getNbPrises());
        mPogressBar.setVisibility(View.GONE);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        this.menu = menu;
        //updateUI("consult");
        inflater.inflate(R.menu.menu_list, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        MenuItem refreshItem = menu.findItem(R.id.action_refresh);
        refreshItem.setVisible(false);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    //Log.i("onQueryTextChange", newText);

                    //readData("code_prise like '%"+newText+"%'");
                    return true;
                }

                @Override
                public boolean onQueryTextSubmit(String query) {
                    //Log.i("onQueryTextSubmit", query);
                    stCond_save = "code_prise like '%" + query + "%'";
                    readData(stCond_save);
                    return true;
                }
            };

            searchView.setOnQueryTextListener(queryTextListener);

            searchView.setOnCloseListener(new SearchView.OnCloseListener() {
                @Override
                public boolean onClose() {
                    //Toast t = Toast.makeText(getActivity(), "close", Toast.LENGTH_SHORT);
                    //t.show();
                    stCond_save = "";
                    readData(stCond_save);
                    return false;
                }
            });
        }

        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                new getAllPrisesTask().execute(new ApiConnector());
                return true;
            case R.id.action_search:

                return true;
        }
        searchView.setOnQueryTextListener(queryTextListener);
        return super.onOptionsItemSelected(item);

    }

    private class getAllPrisesTask extends AsyncTask<ApiConnector,Long, JSONArray> {
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
            p=pdao.getData("UTILISATEUR");
            String user =p.getValeur_parametre();

            urlString = "http://"+serv + "/eFact/datalist/?cmd=priseslist&cmv=" +cmv+"&user="+user+"&offset=0";

            if (ApiConnector.isServerAlive(urlString)==false){
                serverOK = false;
                return null;
            }
            else {
                ArrayList<Prise> resp=new ArrayList<Prise>();
                JSONArray jsonArray = params[0].getData(Prise.class,urlString,resp);
                serverOK = true;
                return jsonArray;}
        }
        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            final PriseDAO prdao =new PriseDAO(getActivity());
            if (serverOK){
                if (jsonArray == null) {
                    Helper.showMessage(getActivity(), getString(R.string.donnees_non_trouvees), getString(R.string.title_err_import), R.drawable.ic_error_red);
                }
                else {
                    prdao.InsererPrisesFromJson(jsonArray, getActivity());
                    readData("");

                    Helper.showMessage(getActivity(), getString(R.string.donnees_importees_avec_succes), getString(R.string.title_import), R.drawable.ic_success_green);
                }

            }
            else {

                Helper.showMessage(getActivity(),getString(R.string.probleme_connexion),getString(R.string.title_err_import),R.drawable.ic_error_red);
            }
            mPogressBar.setVisibility(View.GONE);
        }
    }

}

