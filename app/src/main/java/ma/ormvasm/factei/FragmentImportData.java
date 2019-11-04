package ma.ormvasm.factei;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;

import ma.ormvasm.factei.DAO.CmvDAO;
import ma.ormvasm.factei.DAO.Parametre;
import ma.ormvasm.factei.DAO.ParametreDAO;
import ma.ormvasm.factei.DAO.Prise;
import ma.ormvasm.factei.DAO.PriseDAO;
import ma.ormvasm.factei.DAO.UtilisateurDAO;
import ma.ormvasm.factei.DAO.Utilisateur;
public class FragmentImportData extends Fragment {

    TextView cmv;
    TextView utilisateur;
    TextView lblcmv;
    TextView lblutilisateur;
    Button btnimport;
    TextView textView = null;
    ProgressBar mPogressBar = null;
    Parametre p;
    ParametreDAO pdao ;
    private String urlString ="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);
        View myView;
        myView = inflater.inflate(R.layout.activity_fragment_import_data, container, false);

        Toolbar toolbar = myView.findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //textView = myView.findViewById(R.id.textView);
        //mPogressBar = (ProgressBar) myView.findViewById(R.id.progressBar);

        cmv=(TextView) myView.findViewById(R.id.txtcmv);
        utilisateur=(TextView) myView.findViewById(R.id.txtUtilisateur);
        lblcmv=(TextView) myView.findViewById(R.id.lblcmv);
        lblutilisateur=(TextView) myView.findViewById(R.id.lblUtilisateur);
        btnimport=(Button) myView.findViewById(R.id.btnimportdata);

        textView = myView.findViewById(R.id.textView);
        mPogressBar = (ProgressBar) myView.findViewById(R.id.progressBar);


        mPogressBar.setVisibility(View.GONE);

        getActivity().setTitle(getString(R.string.import_data));

        Parametre p1 ;
        Parametre p2 ;
        ParametreDAO pdao=new ParametreDAO(getActivity());

        p1=pdao.getData("CODE_CMV");

        p2=pdao.getData("UTILISATEUR");



        if (TextUtils.isEmpty(p1.getValeur_parametre()) || TextUtils.isEmpty(p2.getValeur_parametre())){

            btnimport.setEnabled(false);
            lblcmv.setVisibility(View.GONE);
            lblutilisateur.setVisibility(View.GONE);
            new AlertDialog.Builder(getActivity())
                    .setTitle(getString(R.string.import_data))
                    .setMessage(R.string.import_data_msg)
                    .setCancelable(false)
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Whatever...
                        }
                    }).show();
        }
        else {


            CmvDAO c = new CmvDAO(getActivity());
            UtilisateurDAO u = new UtilisateurDAO(getActivity());
            cmv.setText(c.getData(p1.getValeur_parametre()).getCmv());
            utilisateur.setText(u.getData(p2.getValeur_parametre()).getUtilisateur());

            btnimport.setEnabled(true);
            lblcmv.setVisibility(View.VISIBLE);
            lblutilisateur.setVisibility(View.VISIBLE);
        }



        btnimport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //Toast.makeText(getActivity(), "Import", Toast.LENGTH_LONG).show();
                //new getAllPrisesTask().execute(new ApiConnector());
            }
        });

        myView.setFocusableInTouchMode(true);
        myView.requestFocus();

        return myView;

    }






}
