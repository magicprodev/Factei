package ma.ormvasm.factei;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


import android.app.Fragment;


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
}

