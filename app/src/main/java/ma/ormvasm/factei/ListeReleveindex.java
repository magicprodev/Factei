package ma.ormvasm.factei;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


import android.app.Fragment;
import ma.ormvasm.factei.DAO.Releveindex;
import ma.ormvasm.factei.DAO.ReleveindexDAO;


public class ListeReleveindex extends  Fragment {

    TextView textView = null;
    ProgressBar mPogressBar = null;
    ListView releveindexListView = null;
    List<Releveindex> releves = new ArrayList<Releveindex>();
    View lastView = null;
    int position = -1;
    private Menu menu;
    //String menuMode = "";
    ReleveindexListViewAdapter adapter;
    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;
    private String stCond_save = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        //setContentView(R.layout.activity_liste_releveindex);
        View myView;
        myView = inflater.inflate(R.layout.activity_liste_releveindex, container, false);

        //menuMode = "consult";

        Toolbar toolbar = myView.findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        textView = myView.findViewById(R.id.textView);
        mPogressBar = (ProgressBar) myView.findViewById(R.id.progressBar);
        releveindexListView = (ListView) myView.findViewById(R.id.releveIndexListView);

        //test github

        //ActionBar actionBar = ((AppCompatActivity) getActivity(this)).getSupportActionBar();
        //ActionBar actionBar = getSupportActionBar();
        //actionBar.setTitle(R.string.title_activity_liste_releveindex);

        getActivity().setTitle(getString(R.string.liste_releveindex));

        releveindexListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String selected = ((TextView) view.findViewById(R.id.txtIdReleveindex)).getText().toString();

                Intent intent = new Intent(getActivity(), ModifReleveindex.class);
                intent.putExtra("ID_RELEVE", selected);
                //startActivity(intent);
                startActivityForResult(intent, 1);
                //Toast.makeText(ListeReleveindex.this, selected, Toast.LENGTH_SHORT).show();


                //Toast.makeText(ListeReleveindex.this, "ON Click "+(i-1), Toast.LENGTH_SHORT).show();

            }
        });

       /* releveindexListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (ListeReleveindex.this.lastView !=  null)
                    ListeReleveindex.this.lastView.setBackgroundResource(R.color.colorWhiteDefault);
                //hideKeyboard(getActivity());

                ListeReleveindex.this.position = i;
                view.setBackgroundResource(R.color.green_200);
                adapter.setPosition(i);
                view.setSelected(true);

                ListeReleveindex.this.lastView = view;
                updateUI("edit");

                return true;
            }
        });*/
        myView.setFocusableInTouchMode(true);
        myView.requestFocus();

        ViewGroup headerView = (ViewGroup) inflater.inflate(R.layout.custom_row_releveindex_header, releveindexListView, false);

        releveindexListView.addHeaderView(headerView, null, false);


        readData("");


        return myView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == getActivity().RESULT_OK) {
                readData(stCond_save);
            }
            if (resultCode == getActivity().RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult

    private void readData(String stCond) {
        mPogressBar.setVisibility(View.VISIBLE);
        ReleveindexDAO indx = new ReleveindexDAO(getActivity());
        indx.setCondReleve(stCond);
        releves = indx.getListReleveindex();
        adapter = new ReleveindexListViewAdapter(releves, getActivity());
        releveindexListView.setAdapter(adapter);
        mPogressBar.setVisibility(View.GONE);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        this.menu = menu;
        //updateUI("consult");
        inflater.inflate(R.menu.menu_list, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

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
        }
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                // Not implemented here
                return true;
            case android.R.id.home:
                //updateUI("consult");

                if (this.position > 0) {
                    releveindexListView.clearChoices();
                    releveindexListView.requestLayout();
                }

                return true;
        }
        searchView.setOnQueryTextListener(queryTextListener);
        return super.onOptionsItemSelected(item);
    }


    /*@Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        updateUI(this.menuMode);
        *//*if (this.position==-1) {
            MenuItem filter = menu.findItem(R.id.action_delete);
            filter.setVisible(false);
        }*//*

    }

    public void updateUI(String argMenuMode){

        switch (argMenuMode) {
            case "consult":
                this.menu.getItem(0).setVisible(true);
                this.menu.getItem(1).setVisible(false);
                //myInterface.showDrawer();
                releveindexListView.setEnabled(true);
                int l=ListeReleveindex.this.position;
                if (lastView!=null) {
                    lastView.setBackgroundResource(R.color.colorWhiteDefault);
                    this.position=-1;
                    adapter.setPosition(-1);
                }

                break;
            case "edit":
                this.menu.getItem(0).setVisible(false);
                this.menu.getItem(1).setVisible(true);
                //myInterface.hideDrawer();
                releveindexListView.setEnabled(false);

                break;

        }
        this.menuMode = argMenuMode;
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    */
}


