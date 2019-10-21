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

import static android.content.ContentValues.TAG;

public class FragmentSettings extends Fragment {
    TextView serveur;
    private SpinCmvAdapter adaptercmv;
    Spinner spinnercmv;
    private String code_cmv="";
    ArrayList<Cmv> ListeCmv;

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

        loadSpinnerCmv();
        loadPametres();

        spinnercmv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Cmv tempCmv = adaptercmv.getItem(spinnercmv.getSelectedItemPosition());

                if (tempCmv != null) {
                    code_cmv=tempCmv.getCode_cmv();
                    int u=1;
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
    }
    private void loadSpinnerCmv() {
        //  url = url +"?cmd=listeSecteur&examen="+idexam;
        ListeCmv = new ArrayList<Cmv>();
        CmvDAO p=new CmvDAO(getActivity());
        ListeCmv = p.getAllData();
        adaptercmv = new SpinCmvAdapter(getActivity(),
                R.layout.spinner_layout,ListeCmv);
        adaptercmv.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnercmv.setAdapter(adaptercmv);

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

        spinnercmv.setSelection(pos);

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
}
