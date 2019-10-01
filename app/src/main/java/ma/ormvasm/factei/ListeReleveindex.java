package ma.ormvasm.factei;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ma.ormvasm.factei.DAO.Releveindex;

public class ListeReleveindex extends AppCompatActivity {

    TextView textView =null;
    ProgressBar mPogressBar = null;
    ListView participListView = null;
    List<Releveindex> releves = new ArrayList<Releveindex>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_releveindex);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        textView = findViewById(R.id.textView);
        mPogressBar =(ProgressBar) findViewById(R.id.progressBar);
        participListView =(ListView) findViewById(R.id.releveIndexListView) ;

        //test github

        //ActionBar actionBar = ((AppCompatActivity) getActivity(this)).getSupportActionBar();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.title_activity_liste_releveindex);




    }

}
