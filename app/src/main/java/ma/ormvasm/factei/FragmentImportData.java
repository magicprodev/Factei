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

import ma.ormvasm.factei.DAO.CmvDAO;
import ma.ormvasm.factei.DAO.Parametre;
import ma.ormvasm.factei.DAO.ParametreDAO;
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
                new getAllPrisesTask().execute(new ApiConnector());
            }
        });

        myView.setFocusableInTouchMode(true);
        myView.requestFocus();

        return myView;

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
            urlString = serv + "/datalist/?cmd=priseslist&cmv=" +cmv+"&offset=0";

            if (ApiConnector.isServerAlive(urlString)==false){
                serverOK = false;
                return null;
            }
            else {
                JSONArray jsonArray = params[0].getData(Utilisateur.class,urlString);
                serverOK = true;
                return jsonArray;}
        }
        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            final PriseDAO prdao =new PriseDAO(getActivity());
            if (serverOK){
                if (jsonArray == null)
                    textView.setText(getString(R.string.donnees_non_trouvees));
                else

                    prdao.InsererPrisesFromJson(jsonArray,getActivity());
                Toast.makeText(getActivity(), "Import", Toast.LENGTH_LONG).show();
                //readData("");
                }
            else {
                textView.setText(getString(R.string.probleme_connexion));
            }
            mPogressBar.setVisibility(View.GONE);
        }
    }



}
