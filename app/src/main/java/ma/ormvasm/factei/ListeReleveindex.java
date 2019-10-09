package ma.ormvasm.factei;

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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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

    TextView textView =null;
    ProgressBar mPogressBar = null;
    ListView releveindexListView = null;
    List<Releveindex> releves = new ArrayList<Releveindex>();
    ReleveindexListViewAdapter adapter;
    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        //setContentView(R.layout.activity_liste_releveindex);
        View myView;
        myView = inflater.inflate(R.layout.activity_liste_releveindex, container, false);



        Toolbar toolbar = myView.findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        textView = myView.findViewById(R.id.textView);
        mPogressBar =(ProgressBar) myView.findViewById(R.id.progressBar);
        releveindexListView =(ListView) myView.findViewById(R.id.releveIndexListView) ;

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

        ViewGroup headerView = (ViewGroup) inflater.inflate(R.layout.custom_row_releveindex_header, releveindexListView,false);

        releveindexListView.addHeaderView(headerView,null,false);


        readData();



        return  myView;
    }

   @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == getActivity().RESULT_OK){
                adapter = new ReleveindexListViewAdapter(releves,getActivity());
                releveindexListView.setAdapter(adapter);
            }
            if (resultCode == getActivity().RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult

    private void readData(){
            mPogressBar.setVisibility(View.VISIBLE);
            ReleveindexDAO indx = new ReleveindexDAO(getActivity());
            releves = indx.getListReleveindex();
            adapter = new ReleveindexListViewAdapter(releves,getActivity());
            releveindexListView.setAdapter(adapter);
            mPogressBar.setVisibility(View.GONE);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
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

                    readData();
                    return true;
                }
                @Override
                public boolean onQueryTextSubmit(String query) {
                    //Log.i("onQueryTextSubmit", query);
                    List<Releveindex> releves_search = new ArrayList<Releveindex>();
                    for (Releveindex rel : releves) {
                        if (rel.getCode_prise().contains(query)) {
                            releves_search.add(rel);
                        }
                    }
                    releves=releves_search;
                    adapter = new ReleveindexListViewAdapter(releves, getActivity());
                    releveindexListView.setAdapter(adapter);
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
                return false;
            default:
                break;
        }
        searchView.setOnQueryTextListener(queryTextListener);
        return super.onOptionsItemSelected(item);
    }


}
