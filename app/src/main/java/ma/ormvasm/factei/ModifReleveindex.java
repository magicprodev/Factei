package ma.ormvasm.factei;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import ma.ormvasm.factei.DAO.Etatprise;
import ma.ormvasm.factei.DAO.Prise;
import ma.ormvasm.factei.DAO.PriseDAO;
import ma.ormvasm.factei.DAO.Releveindex;
import ma.ormvasm.factei.DAO.ReleveindexDAO;
import ma.ormvasm.factei.DAO.Secteur;
import ma.ormvasm.factei.DAO.EtatpriseDAO;

public class ModifReleveindex extends AppCompatActivity {

    TextView datereleve;
    TextView secteur;
    TextView numprise;
    TextView indexfin;
    TextView etatprise;
    TextView observations;

    private SpinEtatpriseAdapter adapterEtatprise;
    Spinner spinnerEtatprise;
    ArrayList<Secteur> ListeSecteur;
    ArrayList<Etatprise> ListeEtatprise;
    private String code_secteur="";
    private String code_etat_prise="";
    private String idReleve;
    private Releveindex ri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modif_releveindex);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        datereleve=(TextView) findViewById(R.id.txtdatereleve);
        secteur=(TextView) findViewById(R.id.txtsecteur);
        numprise=(TextView) findViewById(R.id.txtnumprise);
        indexfin=(TextView) findViewById(R.id.txtindexfin);
        spinnerEtatprise=(Spinner) findViewById(R.id.spinetatprise);
        observations=(TextView) findViewById(R.id.txtobservations);



        idReleve=getIntent().getStringExtra("ID_RELEVE");




        spinnerEtatprise = (Spinner)  findViewById(R.id.spinetatprise);
        loadSpinnerEtatprise();


        spinnerEtatprise.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Etatprise tempEtatprise = adapterEtatprise.getItem(spinnerEtatprise.getSelectedItemPosition());

                if (tempEtatprise != null) {
                    //  Toast.makeText(getActivity().getApplicationContext(), tempSalle.getLibelle_salle(), Toast.LENGTH_LONG).show();
                    code_etat_prise=tempEtatprise.getCode_etat_prise();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });


    ReleveindexDAO rdao=new ReleveindexDAO(ModifReleveindex.this) ;
    ri = rdao.getData2(idReleve);

    datereleve.setText(Helper.convertFormatDate(ri.getDate_fin_index()+""));
    secteur.setText(ri.getCode_cmv()+"");
    numprise.setText(ri.getRow_id()+"");
    int etprise=0;
    if (ri.getCode_etat_prise().equals("B")) etprise=1;
    spinnerEtatprise.setSelection(etprise);
    indexfin.setText(ri.getIndex_fin()+"");
    observations.setText(ri.getObservations()+"");

    }



    public void enregistrer(View v) {

        if (validateForm(ModifReleveindex.this,numprise,indexfin)) {
            PriseDAO p = new PriseDAO(ModifReleveindex.this);

            String code_prise = ri.getCode_prise();

            String index_fin = indexfin.getText().toString();
            String date_debut_index = "";
            Integer index_debut = 0;
            Integer volume_index = 0;

            if (!index_fin.matches("")) {

                ReleveindexDAO der = new ReleveindexDAO(ModifReleveindex.this);
                Releveindex dr = der.getDernierReleve(code_prise);

                if (dr != null) {
                    date_debut_index = dr.getDate_fin_index();
                    index_debut = dr.getIndex_fin();
                    volume_index = (Integer.parseInt(index_fin) - index_debut>0)?Integer.parseInt(index_fin) - index_debut:0;
                }

                if (validateIndexFin(ModifReleveindex.this, index_debut, Integer.parseInt(index_fin), indexfin, code_etat_prise)){

                    ReleveindexDAO rd = new ReleveindexDAO(ModifReleveindex.this);
                    ri.setIndex_fin(Integer.parseInt(index_fin));
                    ri.setCode_etat_prise(code_etat_prise);
                    ri.setObservations(observations.getText().toString());
                    ri.setUtilisateur_maj("admin");
                    ri.setDate_maj(Helper.getCurrentDateTime());
                    ri.setVolume_index(volume_index);

                    rd.modifier(ri);
                    Toast.makeText(ModifReleveindex.this, "Relevé Index modifié avec succès", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent();
                    setResult(RESULT_OK, intent);
                    finish();

                }
            } else {
                Toast.makeText(ModifReleveindex.this, "Veuillez renseigner la valeur d'index ", Toast.LENGTH_LONG).show();
                return;

            }
        }
    }



    private void loadSpinnerEtatprise() {
        //  url = url +"?cmd=listeSecteur&examen="+idexam;
        ListeEtatprise = new ArrayList<Etatprise>();
        EtatpriseDAO p=new EtatpriseDAO(ModifReleveindex.this);
        ListeEtatprise = p.getAllData();
        adapterEtatprise = new SpinEtatpriseAdapter(ModifReleveindex.this,
                R.layout.spinner_layout,ListeEtatprise);
        adapterEtatprise.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnerEtatprise.setAdapter(adapterEtatprise);

    }

    Boolean validateForm(Context pcontext, TextView etnumprise, TextView etindexfin){

        if (TextUtils.isEmpty(etnumprise.getText().toString())) {
            etnumprise.setError(pcontext.getString(R.string.required));
            return false;
        } else {

            etnumprise.setError(null);

        }


        if (TextUtils.isEmpty(etindexfin.getText().toString())) {
            etindexfin.setError(pcontext.getString(R.string.required));
            return false;
        } else {

            etindexfin.setError(null);

        }

        return true;
    }


    Boolean validateIndexFin(Context pcontext,int indexdebut,int indexfin,TextView etindexfin,String etatprise){

        if (etatprise.equals("N") && (indexfin < indexdebut )) {
            etindexfin.setError(pcontext.getString(R.string.index_non_valide));
            return false;
        } else {

            etindexfin.setError(null);

        }

        return true;
    }
}
