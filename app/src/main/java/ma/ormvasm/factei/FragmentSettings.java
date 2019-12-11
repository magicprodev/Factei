package ma.ormvasm.factei;

import android.app.Fragment;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import ma.ormvasm.factei.DAO.Cmv;
import ma.ormvasm.factei.DAO.CmvDAO;
import ma.ormvasm.factei.DAO.Parametre;
import ma.ormvasm.factei.DAO.ParametreDAO;
import ma.ormvasm.factei.DAO.PriseDAO;
import ma.ormvasm.factei.DAO.Secteur;
import ma.ormvasm.factei.DAO.Utilisateur;
import ma.ormvasm.factei.DAO.UtilisateurDAO;

import static android.content.ContentValues.TAG;

public class FragmentSettings extends Fragment {
    TextView serveur;
    TextView lblserveur;
    private SpinCmvAdapter adaptercmv;
    private SpinUtilisateurAdapter adapterutilisateur;
    Spinner spinnercmv;
    Spinner spinnerutilisateur;
    Button btnrefresh;
    Button btnsavedb;
    private String code_cmv="";
    private String code_utilisateur="";
    ArrayList<Cmv> listeCmv;
    ArrayList<Utilisateur> listeUtilisateur;
    private String urlString ="";
    Parametre p;
    ParametreDAO pdao ;
    private String user_encours="";
    private String groupe_encours="";

    public void setUserEncours(String user_encours, String groupe_encours) {
        this.user_encours = user_encours;
        this.groupe_encours = groupe_encours;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        View myView;
        myView = inflater.inflate(R.layout.activity_fragment_settings, container, false);
        getActivity().setTitle(getString(R.string.parametres));

        Toolbar toolbar = (Toolbar) myView.findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        serveur=(TextView) myView.findViewById(R.id.txtserveur);
        lblserveur=(TextView) myView.findViewById(R.id.lblserveur);
        spinnercmv=(Spinner) myView.findViewById(R.id.spincmv);
        spinnerutilisateur=(Spinner) myView.findViewById(R.id.spinutilisateur);
        btnrefresh=(Button) myView.findViewById(R.id.btnrefreshusers);

        btnsavedb=(Button) myView.findViewById(R.id.btnsavedb);


        loadSpinnerCmv();
        loadSpinnerUtilisateur();
        loadPametres();

        spinnercmv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {

               Cmv tempCmv = adaptercmv.getItem(spinnercmv.getSelectedItemPosition());
                //code_cmv = ((TextView) view.findViewById(R.id.txtCodeCmv)).getText().toString();
                if (tempCmv != null) {
                    code_cmv=tempCmv.getCode_cmv();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });



        spinnerutilisateur.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {

                Utilisateur tempUtilisateur = adapterutilisateur.getItem(spinnerutilisateur.getSelectedItemPosition());
                //code_cmv = ((TextView) view.findViewById(R.id.txtCodeCmv)).getText().toString();
                if (tempUtilisateur != null) {
                    code_utilisateur=tempUtilisateur.getCode_utilisateur();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });

        btnrefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                rafraichir();
                new getAllUsersTask().execute(new ApiConnector());
            }
        });


        btnsavedb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                saveDataBase();

            }
        });

        if (groupe_encours.equals("ADM")){
            serveur.setVisibility(View.VISIBLE);
            lblserveur.setVisibility(View.VISIBLE);
            spinnercmv.setEnabled(true);
            spinnercmv.setClickable(true);
        }
        else{

            serveur.setVisibility(View.GONE);
            lblserveur.setVisibility(View.GONE);
            spinnercmv.setEnabled(false);
            spinnercmv.setClickable(false);
        }
        return  myView;
    }

    public void saveDataBase(){

        File f=new File("/data/data/ma.ormvasm.factei/databases/factei.db");
        FileInputStream fis=null;
        FileOutputStream fos=null;

        try
        {
            Date c = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String strdt = df.format(c);
            fis=new FileInputStream(f);
            fos=new FileOutputStream("/mnt/sdcard/factei_dump_" + strdt + ".db");
            while(true)
            {
                int i=fis.read();
                if(i!=-1)
                {fos.write(i);}
                else
                {break;}
            }
            fos.flush();
            Toast.makeText(getActivity(), getString(R.string.dumpdb_ok), Toast.LENGTH_LONG).show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Toast.makeText(getActivity(), getString(R.string.dumpdb_error), Toast.LENGTH_LONG).show();
        }
        finally
        {
            try
            {
                fos.close();
                fis.close();
            }
            catch(IOException ioe)
            {}
        }
    }
    public void enregistrer(View v) {
        Parametre p ;
        ParametreDAO pdao=new ParametreDAO(getActivity());
        p=pdao.getData("CODE_CMV");
        p.setValeur_parametre(code_cmv);
        pdao.modifier(p);

        p=pdao.getData("IP_SERVEUR");
        p.setValeur_parametre(serveur.getText().toString());
        pdao.modifier(p);

        p=pdao.getData("UTILISATEUR");
        p.setValeur_parametre(code_utilisateur);
        pdao.modifier(p);

    }

    public void rafraichir() {
        Parametre p ;
        ParametreDAO pdao=new ParametreDAO(getActivity());
        p=pdao.getData("CODE_CMV");
        p.setValeur_parametre(code_cmv);
        pdao.modifier(p);

        p=pdao.getData("IP_SERVEUR");
        p.setValeur_parametre(serveur.getText().toString());
        pdao.modifier(p);

        p=pdao.getData("UTILISATEUR");
        p.setValeur_parametre("");
        pdao.modifier(p);
    }

    private void loadSpinnerCmv() {
        //  url = url +"?cmd=listeSecteur&examen="+idexam;
        listeCmv = new ArrayList<Cmv>();
        CmvDAO p=new CmvDAO(getActivity());
        listeCmv = p.getAllData();
        adaptercmv = new SpinCmvAdapter(getActivity(),R.layout.spinner_layout,listeCmv);
        //adaptercmv = new SpinCmvAdapter(listeCmv,getActivity());
        adaptercmv.setDropDownViewResource(R.layout.spinner_dropdown_item);
        //adaptercmv = new SpinCmvAdapter(getActivity(),listeCmv);
        spinnercmv.setAdapter(adaptercmv);

    }

    private void loadSpinnerUtilisateur() {
        //  url = url +"?cmd=listeSecteur&examen="+idexam;
        listeUtilisateur = new ArrayList<Utilisateur>();
        UtilisateurDAO p=new UtilisateurDAO(getActivity());
        listeUtilisateur = p.getAiguadiers();

        adapterutilisateur = new SpinUtilisateurAdapter(getActivity(), R.layout.spinner_layout, listeUtilisateur);
        adapterutilisateur.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnerutilisateur.setAdapter(adapterutilisateur);


    }

    private void loadPametres(){
        Parametre p ;
        ParametreDAO pdao=new ParametreDAO(getActivity());
        p=pdao.getData("CODE_CMV");
        int pos=0;
        CmvDAO cdao =new CmvDAO(getActivity());
        String cmv="";
        cmv=p.getValeur_parametre();

        if (cmv != null && !cmv.isEmpty()) {
            Cmv c =cdao.getData2(p.getValeur_parametre());
            if (c!=null) {
                pos = Integer.parseInt(c.getCmv());
            }
        }
        spinnercmv.setSelection(pos-1);



        p=pdao.getData("UTILISATEUR");
        pos=0;
        UtilisateurDAO udao =new UtilisateurDAO(getActivity());
        String user="";
        user=p.getValeur_parametre();

        if (!(user.equals("")) && (user!= null) && !(user.isEmpty())) {
            Utilisateur u =  udao.getData2(p.getValeur_parametre());
            if (u!=null) {
                pos = Integer.parseInt(u.getUtilisateur());
            }

        }
        spinnerutilisateur.setSelection(pos-1);




        p=pdao.getData("IP_SERVEUR");
        serveur.setText(p.getValeur_parametre()+"");
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //Log.d(TAG, "Fragment.onOptionsItemSelected");
        menu.clear();
        inflater.inflate(R.menu.menu_form, menu);

        super.onCreateOptionsMenu(menu,inflater);
        MenuItem filter_delete = menu.findItem(R.id.btn_delete);
        filter_delete.setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.btn_save) {
            enregistrer(item.getActionView());
            return true;
        }
        if (id == R.id.btn_cancel) {

            getActivity().onBackPressed();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause() {
        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
        super.onPause();
    }


    private class getAllUsersTask extends AsyncTask<ApiConnector,Long, JSONArray> {
        private boolean serverOK = true ;
        @Override
        protected void onPreExecute() {
            //mPogressBar.setVisibility(View.VISIBLE);
        }
        @Override
        protected JSONArray doInBackground(ApiConnector... params) {
            pdao =new ParametreDAO(getActivity());
            p=pdao.getData("IP_SERVEUR");
            String serv =p.getValeur_parametre();
            p=pdao.getData("CODE_CMV");
            String cmv =p.getValeur_parametre();


            urlString = "http://"+serv + "/eFact/datalist/?cmd=userslist&cmv=" +cmv+"&offset=0";

            if (ApiConnector.isServerAlive(urlString)==false){
                serverOK = false;
                return null;
            }
            else {
                ArrayList<Utilisateur> resp=new ArrayList<Utilisateur>();
                JSONArray jsonArray = params[0].getData(Utilisateur.class,urlString,resp);
                serverOK = true;
                return jsonArray;}
        }
        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            final UtilisateurDAO udao =new UtilisateurDAO(getActivity());
            if (serverOK){
                if (jsonArray == null) {
                    Helper.showMessage(getActivity(), getString(R.string.donnees_non_trouvees), getString(R.string.title_err_import), R.drawable.ic_error_red);
                }
                else{
                    udao.InsererUtilisateursFromJson(jsonArray,getActivity());
                    loadSpinnerUtilisateur();}
              }
            else {
                Helper.showMessage(getActivity(),getString(R.string.probleme_connexion),getString(R.string.title_err_import),R.drawable.ic_error_red);
            }
            //mPogressBar.setVisibility(View.GONE);
        }
    }
}
