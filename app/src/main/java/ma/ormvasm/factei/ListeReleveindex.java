package ma.ormvasm.factei;

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

import ma.ormvasm.factei.DAO.Releveindex;
import ma.ormvasm.factei.DAO.ReleveindexDAO;


public class ListeReleveindex extends AppCompatActivity {

    TextView textView =null;
    ProgressBar mPogressBar = null;
    ListView releveindexListView = null;
    List<Releveindex> releves = new ArrayList<Releveindex>();
    ReleveindexListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_releveindex);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        textView = findViewById(R.id.textView);
        mPogressBar =(ProgressBar) findViewById(R.id.progressBar);
        releveindexListView =(ListView) findViewById(R.id.releveIndexListView) ;

        //test github

        //ActionBar actionBar = ((AppCompatActivity) getActivity(this)).getSupportActionBar();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.title_activity_liste_releveindex);



        releveindexListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

               String selected = ((TextView) view.findViewById(R.id.txtIdReleveindex)).getText().toString();
               Toast.makeText(ListeReleveindex.this, selected, Toast.LENGTH_SHORT).show();

                //Toast.makeText(ListeReleveindex.this, "ON Click "+(i-1), Toast.LENGTH_SHORT).show();

            }
        });

        ViewGroup headerView = (ViewGroup) this.getLayoutInflater().inflate(R.layout.custom_row_releveindex_header, releveindexListView,false);

        releveindexListView.addHeaderView(headerView,null,false);

        mPogressBar.setVisibility(View.VISIBLE);
        readData();
        mPogressBar.setVisibility(View.GONE);





    }




    private void readData(){
            ReleveindexDAO indx = new ReleveindexDAO(ListeReleveindex.this);
            releves = indx.getListReleveindex();
            adapter = new ReleveindexListViewAdapter(releves,this);
            releveindexListView.setAdapter(adapter);
    }
}
