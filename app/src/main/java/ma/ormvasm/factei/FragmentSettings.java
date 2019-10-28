package ma.ormvasm.factei;

import android.app.Fragment;
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
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

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
    private SpinCmvAdapter adaptercmv;
    private SpinUtilisateurAdapter adapterutilisateur;
    Spinner spinnercmv;
    Spinner spinnerutilisateur;
    private String code_cmv="";
    private String code_utilisateur="";
    ArrayList<Cmv> listeCmv;
    ArrayList<Utilisateur> listeUtilisateur;

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
        spinnercmv=(Spinner) myView.findViewById(R.id.spincmv);
        spinnerutilisateur=(Spinner) myView.findViewById(R.id.spinutilisateur);

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

        return  myView;
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
        CmvDAO c =new CmvDAO(getActivity());
        String cmv="";
        cmv=p.getValeur_parametre();

        if (cmv != null && !cmv.isEmpty()) {
            pos = Integer.parseInt(c.getData2(p.getValeur_parametre()).getCmv());
        }
        spinnercmv.setSelection(pos-1);



        p=pdao.getData("UTILISATEUR");
        pos=0;
        UtilisateurDAO u =new UtilisateurDAO(getActivity());
        String user="";
        user=p.getValeur_parametre();

        if (user != null && !user.isEmpty()) {
            pos = Integer.parseInt(u.getData2(p.getValeur_parametre()).getUtilisateur());
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
}
