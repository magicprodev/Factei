package ma.ormvasm.factei;

import android.app.AlertDialog;
import android.content.Context;
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
import ma.ormvasm.factei.DAO.Releveindex;
import ma.ormvasm.factei.DAO.ReleveindexDAO;
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

        getActivity().setTitle(getString(R.string.title_activity_fragment_import_data));

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
                showDeleteDialog(R.layout.custom_title_dialog,getString(R.string.import_data) ,getString(R.string.import_data),getString(R.string.cancel_record));
            }
        });

        myView.setFocusableInTouchMode(true);
        myView.requestFocus();

        return myView;

    }



    public void showDeleteDialog(int titre_layout,String dialogTitle ,String positiveButtonTitle, String negativeButtonTitle){

        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //int tt =R.layout.custom_title_dialog;
        View customTitleView = inflater.inflate(titre_layout, null);
        TextView title = (TextView) customTitleView.findViewById(R.id.title);
        title.setText(dialogTitle);


        final AlertDialog d = new AlertDialog.Builder(getActivity())
                .setCustomTitle(customTitleView)
                .setMessage(getActivity().getString(R.string.confirm_inport_anciens_index) )
                .setPositiveButton(positiveButtonTitle, null) //Set to null. We override the onclick
                .setNegativeButton(negativeButtonTitle, null)
                .create();

        d.setCanceledOnTouchOutside(false);

        d.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialog) {

                Button b = d.getButton(AlertDialog.BUTTON_POSITIVE);
                b.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        // TODO Do something
                        new getAllRelevesTask().execute(new ApiConnector());


                        d.dismiss();
                    }



                });
            }
        });

        d.show();
    }


    private class getAllRelevesTask extends AsyncTask<ApiConnector,Long, JSONArray> {
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

            urlString = serv + "/datalist/?cmd=releveslist&cmv=" +cmv+"&user="+user+"&offset=0";

            if (ApiConnector.isServerAlive(urlString)==false){
                serverOK = false;
                return null;
            }
            else {
                ArrayList<Releveindex> resp=new ArrayList<Releveindex>();
                JSONArray jsonArray = params[0].getData(Releveindex.class,urlString,resp);
                serverOK = true;
                return jsonArray;}
        }
        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            final ReleveindexDAO rdao =new ReleveindexDAO(getActivity());
            if (serverOK){
                if (jsonArray == null) {
                    Helper.showMessage(getActivity(), getString(R.string.donnees_non_trouvees), getString(R.string.title_err_import), R.drawable.ic_error_red);
                }
                else {
                    rdao.InsererRelevesFromJson(jsonArray, getActivity());

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
