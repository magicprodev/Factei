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
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import ma.ormvasm.factei.DAO.CmvDAO;
import ma.ormvasm.factei.DAO.Parametre;
import ma.ormvasm.factei.DAO.ParametreDAO;
import ma.ormvasm.factei.DAO.Releveindex;
import ma.ormvasm.factei.DAO.ReleveindexDAO;
import ma.ormvasm.factei.DAO.UtilisateurDAO;

public class FragmentExportData extends Fragment {

    TextView cmv;
    TextView utilisateur;
    TextView lblcmv;
    TextView lblutilisateur;
    Button btnexport;
    ParametreDAO pdao;
    Parametre p;
    ReleveindexDAO indx;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);
        View myView;
        myView = inflater.inflate(R.layout.activity_fragment_export_data, container, false);

        Toolbar toolbar = myView.findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //textView = myView.findViewById(R.id.textView);
        //mPogressBar = (ProgressBar) myView.findViewById(R.id.progressBar);

        cmv=(TextView) myView.findViewById(R.id.txtcmv);
        utilisateur=(TextView) myView.findViewById(R.id.txtUtilisateur);
        lblcmv=(TextView) myView.findViewById(R.id.lblcmv);
        lblutilisateur=(TextView) myView.findViewById(R.id.lblUtilisateur);
        btnexport=(Button) myView.findViewById(R.id.btnexportdata);

        getActivity().setTitle(getString(R.string.export_data));

        Parametre p1 ;
        Parametre p2 ;
        ParametreDAO pdao=new ParametreDAO(getActivity());

        p1=pdao.getData("CODE_CMV");

        p2=pdao.getData("UTILISATEUR");



        if (TextUtils.isEmpty(p1.getValeur_parametre()) || TextUtils.isEmpty(p2.getValeur_parametre())){

            btnexport.setEnabled(false);
            lblcmv.setVisibility(View.GONE);
            lblutilisateur.setVisibility(View.GONE);
            new AlertDialog.Builder(getActivity())
                    .setTitle(getString(R.string.export_data))
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

            btnexport.setEnabled(true);
            lblcmv.setVisibility(View.VISIBLE);
            lblutilisateur.setVisibility(View.VISIBLE);
        }



        btnexport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //Toast.makeText(getActivity(), "Export", Toast.LENGTH_LONG).show();
                //new FragmentSettings.getAllUsersTask().execute(new ApiConnector());

                new SyncRelevesData().execute(new ApiConnector());
            }
        });

        myView.setFocusableInTouchMode(true);
        myView.requestFocus();

        return myView;

    }


    private class SyncRelevesData extends AsyncTask<ApiConnector,Long, JSONArray> {
        private boolean serverOK = true ;

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected JSONArray doInBackground(ApiConnector... params) {
            pdao =new ParametreDAO(getActivity());
            p=pdao.getData("IP_SERVEUR");
            String serv =p.getValeur_parametre();

            String urlString = serv + "putdata/index.php";
            //if (ApiConnector.isServerAlive(urlString)==false){
            if(false){
                serverOK = false;
                return null;
            }

            else {

                serverOK = true;
                indx = new ReleveindexDAO(getActivity());
                indx.setCondReleve("valide=0");


                final ArrayList<Releveindex> releves = new ArrayList<Releveindex>(indx.getListReleveindex2());

                try {
                    JSONObject userdata = null;
                    JSONArray data = new JSONArray();

                    for ( Releveindex rel:releves
                    ) {

                        userdata = new JSONObject();

                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                        userdata.put("id_releveindex",rel.getId_releveindex());
                        userdata.put("code_prise",rel.getCode_prise());
                        userdata.put("date_debut_index",rel.getDate_debut_index());
                        userdata.put("date_fin_index",rel.getDate_fin_index());
                        userdata.put("index_debut",rel.getIndex_debut());
                        userdata.put("index_fin",rel.getIndex_fin());
                        userdata.put("code_etat_prise",rel.getCode_etat_prise());
                        userdata.put("volume_index",rel.getVolume_index());
                        userdata.put("valide",rel.getValide());
                        userdata.put("code_cmv",rel.getCode_cmv());
                        userdata.put("date_maj",rel.getDate_maj());
                        userdata.put("utilisateur_maj",rel.getUtilisateur_maj());
                        userdata.put("date_insert",rel.getDate_insert());
                        userdata.put("utilisateur_insert",rel.getUtilisateur_insert());
                        userdata.put("observations",rel.getObservations());
                        userdata.put("position_x",rel.getPosition_x());
                        userdata.put("position_y",rel.getPosition_y());
                        userdata.put("row_id",rel.getRow_id());

                        data.put(userdata);

                    }

                    params[0].setData(data,urlString,getActivity());
                }
                catch(JSONException e) {
                    e.printStackTrace();
                }

                return null;

            }
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {

            if (serverOK){

                Helper.showMessage(getActivity(), getString(R.string.donnees_exportees_avec_succes), getString(R.string.title_export), R.drawable.ic_success_green);

            }
            else {

                Helper.showMessage(getActivity(),getString(R.string.probleme_connexion),getString(R.string.title_err_export),R.drawable.ic_error_red);

            }
        }
    }


}
