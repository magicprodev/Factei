package ma.ormvasm.factei;

import android.content.Context;
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
import ma.ormvasm.factei.DAO.PriseDAO;
import ma.ormvasm.factei.DAO.Releveindex;
import ma.ormvasm.factei.DAO.ReleveindexDAO;
import ma.ormvasm.factei.DAO.Secteur;
import ma.ormvasm.factei.DAO.EtatpriseDAO;

public class SaisieReleveindex extends AppCompatActivity {

    TextView numprise;
    TextView indexfin;
    TextView etatprise;
    TextView observations;
    TextView datereleve;
    private SpinSecteurAdapter adapterSecteur;
    private SpinEtatpriseAdapter adapterEtatprise;
    Spinner spinnerSecteur;
    Spinner spinnerEtatprise;
    ArrayList<Secteur> ListeSecteur;
    ArrayList<Etatprise> ListeEtatprise;
    private String code_secteur="";
    private String code_etat_prise="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saisie_releveindex);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        numprise=(TextView) findViewById(R.id.txtnumprise);
        indexfin=(TextView) findViewById(R.id.txtindexfin);
        spinnerEtatprise=(Spinner) findViewById(R.id.spinetatprise);
        observations=(TextView) findViewById(R.id.txtobservations);
        datereleve=(TextView) findViewById(R.id.txtdatereleve);


        String formattedDate ;
        formattedDate=Helper.getCurrentDate();

        datereleve.setText(formattedDate);

        spinnerSecteur = (Spinner)  findViewById(R.id.spinsecteur);
        loadSpinnerSecteur();

        spinnerEtatprise = (Spinner)  findViewById(R.id.spinetatprise);
        loadSpinnerEtatprise();
        spinnerSecteur.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Secteur tempSecteur = adapterSecteur.getItem(spinnerSecteur.getSelectedItemPosition());

                if (tempSecteur != null) {
                    code_secteur=tempSecteur.getCode_secteur();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });

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

    }

    public void enregistrer(View v) {

        if (validateForm(SaisieReleveindex.this,numprise,indexfin)) {
            PriseDAO p = new PriseDAO(SaisieReleveindex.this);

            String code_prise = "";
            if (p.getPriseBySecteur(code_secteur, numprise.getText().toString()) != null) {
                code_prise = p.getPriseBySecteur(code_secteur, numprise.getText().toString()).getCode_prise();
            } else {
                Toast.makeText(SaisieReleveindex.this, "N° Prise introuvable", Toast.LENGTH_LONG).show();
                return;
            }
            ;

            String index_fin = indexfin.getText().toString();
            String date_debut_index = "";
            Integer index_debut = 0;
            Integer volume_index = 0;

            if (!index_fin.matches("")) {

                ReleveindexDAO der = new ReleveindexDAO(SaisieReleveindex.this);
                Releveindex dr = der.getDernierReleve(code_prise);

                if (dr != null) {
                    date_debut_index = dr.getDate_fin_index();
                    index_debut = dr.getIndex_fin();
                    volume_index = (Integer.parseInt(index_fin) - index_debut>0)?Integer.parseInt(index_fin) - index_debut:0;
                }

                if (validateIndexFin(SaisieReleveindex.this, index_debut, Integer.parseInt(index_fin), indexfin, code_etat_prise)){
                    Releveindex r = new Releveindex(1, code_prise, date_debut_index, Helper.getCurrentDateTime(),
                            index_debut, Integer.parseInt(index_fin), code_etat_prise, volume_index, 0, "04", Helper.getCurrentDateTime(), "admin",
                            Helper.getCurrentDateTime(), "admin", observations.getText().toString(), Helper.rowId(code_prise));

                ReleveindexDAO rd = new ReleveindexDAO(SaisieReleveindex.this);

                rd.ajouter(r);
                Toast.makeText(SaisieReleveindex.this, "Relevé Index ajouté avec succès", Toast.LENGTH_LONG).show();

                numprise.setText("");
                indexfin.setText("");
                observations.setText("");
                numprise.requestFocus();
            }
            } else {
                Toast.makeText(SaisieReleveindex.this, "Veuillez renseigner la valeur d'index ", Toast.LENGTH_LONG).show();
                return;

            }
        }
    }





    private void loadSpinnerSecteur() {
        //  url = url +"?cmd=listeSecteur&examen="+idexam;
        ListeSecteur = new ArrayList<Secteur>();
        PriseDAO p=new PriseDAO(SaisieReleveindex.this);
        ListeSecteur = p.getAllSecteur();
        adapterSecteur = new SpinSecteurAdapter(SaisieReleveindex.this,
                R.layout.spinner_layout,ListeSecteur);
        adapterSecteur.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnerSecteur.setAdapter(adapterSecteur);

    }
    private void loadSpinnerEtatprise() {
        //  url = url +"?cmd=listeSecteur&examen="+idexam;
        ListeEtatprise = new ArrayList<Etatprise>();
        EtatpriseDAO p=new EtatpriseDAO(SaisieReleveindex.this);
        ListeEtatprise = p.getAllData();
        adapterEtatprise = new SpinEtatpriseAdapter(SaisieReleveindex.this,
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
