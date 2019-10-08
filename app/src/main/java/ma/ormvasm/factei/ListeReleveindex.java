package ma.ormvasm.factei;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                readData();
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
}
