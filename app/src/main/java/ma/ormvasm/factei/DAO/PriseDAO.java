package ma.ormvasm.factei.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PriseDAO extends DAOBase {

    public static final String PRISE_TABLE_NAME = "prise";
    public static final String PRISE_COLUMN_CODE_PRISE = "code_prise";
    public static final String PRISE_COLUMN_N_PRISE = "n_prise";
    public static final String PRISE_COLUMN_CODE_ANTENNE = "code_antenne";
    public static final String PRISE_COLUMN_ANTENNE = "antenne";
    public static final String PRISE_COLUMN_CODE_SECTEUR = "code_secteur";
    public static final String PRISE_COLUMN_SECTEUR = "secteur";
    public static final String PRISE_COLUMN_CODE_ZONEAIG = "code_zoneaig";
    public static final String PRISE_COLUMN_ZONEAIG = "zoneaig";
    public static final String PRISE_COLUMN_CODE_CMV = "code_cmv";
    public static final String PRISE_COLUMN_ROW_ID = "row_id";
    private String cond_prise="";


    public PriseDAO(Context pContext) {

        super(pContext);
        open();
    }

    public void setCondPrise(String cond) {
        cond_prise = cond;
    }

    /**
     * @param p le relevé d'index à ajouter à la base
     */


    public void ajouter(Prise p) {
        // CODE
        ContentValues value = new ContentValues();
        value.put(PRISE_COLUMN_CODE_PRISE, p.getCode_prise());
        value.put(PRISE_COLUMN_N_PRISE, p.getN_prise());
        value.put(PRISE_COLUMN_CODE_ANTENNE, p.getCode_antenne());
        value.put(PRISE_COLUMN_ANTENNE, p.getAntenne());
        value.put(PRISE_COLUMN_CODE_SECTEUR, p.getCode_secteur());
        value.put(PRISE_COLUMN_SECTEUR, p.getSecteur());
        value.put(PRISE_COLUMN_CODE_ZONEAIG, p.getCode_zoneaig());
        value.put(PRISE_COLUMN_ZONEAIG, p.getZoneaig());
        value.put(PRISE_COLUMN_CODE_CMV, p.getCode_cmv());
        value.put(PRISE_COLUMN_ROW_ID, p.getRow_id());

        mDb.insert(PRISE_TABLE_NAME, null, value);

    }

    /**
     * @param st l'identifiant du métier à supprimer
     */
    public void supprimer(String st) {
        // CODE
        mDb.delete(PRISE_TABLE_NAME, PRISE_COLUMN_CODE_PRISE + " = ?", new String[]{st});
    }

    public void supprimerTout() {
        // CODE
        mDb.delete(PRISE_TABLE_NAME,"" ,new String[]{});
    }
    /**
     * @param p le relevé d'index modifié
     */
    public void modifier(Prise p) {
        // CODE
        ContentValues value = new ContentValues();
        value.put(PRISE_COLUMN_CODE_PRISE, p.getCode_prise());
        value.put(PRISE_COLUMN_N_PRISE, p.getN_prise());
        value.put(PRISE_COLUMN_CODE_ANTENNE, p.getCode_antenne());
        value.put(PRISE_COLUMN_ANTENNE, p.getAntenne());
        value.put(PRISE_COLUMN_CODE_SECTEUR, p.getCode_secteur());
        value.put(PRISE_COLUMN_SECTEUR, p.getSecteur());
        value.put(PRISE_COLUMN_CODE_ZONEAIG, p.getCode_zoneaig());
        value.put(PRISE_COLUMN_ZONEAIG, p.getZoneaig());
        value.put(PRISE_COLUMN_CODE_CMV, p.getCode_cmv());
        value.put(PRISE_COLUMN_ROW_ID, p.getRow_id());

        mDb.update(PRISE_TABLE_NAME, value, PRISE_COLUMN_CODE_PRISE + " = ?", new String[]{p.getCode_prise()});
    }

    /**
     * @param //id l'identifiant du relevé d'index à récupérer
     */
    public Prise getData(String st) {
        // CODE
        Cursor res = mDb.rawQuery("SELECT * FROM " + PRISE_TABLE_NAME + " where " + PRISE_COLUMN_CODE_PRISE +  " ='" + st + "'", null);

        if (res.moveToFirst()) {
            res.moveToFirst();
            Prise p;

            p = new Prise(
                    res.getString(res.getColumnIndex(PRISE_COLUMN_CODE_PRISE)),
                    res.getString(res.getColumnIndex(PRISE_COLUMN_N_PRISE)),
                    res.getString(res.getColumnIndex(PRISE_COLUMN_CODE_ANTENNE)),
                    res.getString(res.getColumnIndex(PRISE_COLUMN_ANTENNE)),
                    res.getString(res.getColumnIndex(PRISE_COLUMN_CODE_SECTEUR)),
                    res.getString(res.getColumnIndex(PRISE_COLUMN_SECTEUR)),
                    res.getString(res.getColumnIndex(PRISE_COLUMN_CODE_ZONEAIG)),
                    res.getString(res.getColumnIndex(PRISE_COLUMN_ZONEAIG)),
                    res.getString(res.getColumnIndex(PRISE_COLUMN_CODE_CMV)),
                    res.getString(res.getColumnIndex(PRISE_COLUMN_ROW_ID)));
            return p;
        } else {
            return null;
        }

    }


    public Prise getPriseBySecteur(String c_secteur,String n_prise) {
        // CODE
        Cursor res = mDb.rawQuery("SELECT * FROM " + PRISE_TABLE_NAME + " WHERE " + PRISE_COLUMN_CODE_SECTEUR +  " ='" + c_secteur + "' AND " + PRISE_COLUMN_N_PRISE +  " ='" + n_prise + "'", null);

        //System.out.println("count curseur = "+ res.getCount());
        //if (res.isAfterLast() != false) {
        if (res.moveToFirst()){
            //res.moveToFirst();
            Prise p;

            p = new Prise(
                    res.getString(res.getColumnIndex(PRISE_COLUMN_CODE_PRISE)),
                    res.getString(res.getColumnIndex(PRISE_COLUMN_N_PRISE)),
                    res.getString(res.getColumnIndex(PRISE_COLUMN_CODE_ANTENNE)),
                    res.getString(res.getColumnIndex(PRISE_COLUMN_ANTENNE)),
                    res.getString(res.getColumnIndex(PRISE_COLUMN_CODE_SECTEUR)),
                    res.getString(res.getColumnIndex(PRISE_COLUMN_SECTEUR)),
                    res.getString(res.getColumnIndex(PRISE_COLUMN_CODE_ZONEAIG)),
                    res.getString(res.getColumnIndex(PRISE_COLUMN_ZONEAIG)),
                    res.getString(res.getColumnIndex(PRISE_COLUMN_CODE_CMV)),
                    res.getString(res.getColumnIndex(PRISE_COLUMN_ROW_ID)));
            return p;
        } else {
            return null;
        }

    }

    public ArrayList<Prise> getAllData() {
        ArrayList<Prise> array_list = new ArrayList<Prise>();

        //hp = new HashMap();
        Cursor res = mDb.rawQuery("select * from prise", null);
        res.moveToFirst();
        Prise p;

        while (res.isAfterLast() == false) {

            p = new Prise(
                    res.getString(res.getColumnIndex(PRISE_COLUMN_CODE_PRISE)),
                    res.getString(res.getColumnIndex(PRISE_COLUMN_N_PRISE)),
                    res.getString(res.getColumnIndex(PRISE_COLUMN_CODE_ANTENNE)),
                    res.getString(res.getColumnIndex(PRISE_COLUMN_ANTENNE)),
                    res.getString(res.getColumnIndex(PRISE_COLUMN_CODE_SECTEUR)),
                    res.getString(res.getColumnIndex(PRISE_COLUMN_SECTEUR)),
                    res.getString(res.getColumnIndex(PRISE_COLUMN_CODE_ZONEAIG)),
                    res.getString(res.getColumnIndex(PRISE_COLUMN_ZONEAIG)),
                    res.getString(res.getColumnIndex(PRISE_COLUMN_CODE_CMV)),
                    res.getString(res.getColumnIndex(PRISE_COLUMN_ROW_ID)));

            array_list.add(p);
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<Prise> getPrises() {
        ArrayList<Prise> array_list = new ArrayList<Prise>();

        //hp = new HashMap();
        String stCond="";
        if (!cond_prise.equals("")){
            stCond=" where " + cond_prise;
        }

        Cursor res = mDb.rawQuery("select * from prise "+
                stCond +
                " order by code_secteur,substr('0000000000'||n_prise, -10, 10)", null );

        res.moveToFirst();
        Prise p;

        while (res.isAfterLast() == false) {

            p = new Prise(
                    res.getString(res.getColumnIndex(PRISE_COLUMN_CODE_PRISE)),
                    res.getString(res.getColumnIndex(PRISE_COLUMN_N_PRISE)),
                    res.getString(res.getColumnIndex(PRISE_COLUMN_CODE_ANTENNE)),
                    res.getString(res.getColumnIndex(PRISE_COLUMN_ANTENNE)),
                    res.getString(res.getColumnIndex(PRISE_COLUMN_CODE_SECTEUR)),
                    res.getString(res.getColumnIndex(PRISE_COLUMN_SECTEUR)),
                    res.getString(res.getColumnIndex(PRISE_COLUMN_CODE_ZONEAIG)),
                    res.getString(res.getColumnIndex(PRISE_COLUMN_ZONEAIG)),
                    res.getString(res.getColumnIndex(PRISE_COLUMN_CODE_CMV)),
                    res.getString(res.getColumnIndex(PRISE_COLUMN_ROW_ID)));

            array_list.add(p);
            res.moveToNext();
        }
        return array_list;
    }


    public ArrayList<Secteur> getAllSecteur() {
        String query;


        //regular query
        //query = "SELECT  '' as " + PRISE_COLUMN_CODE_SECTEUR + " , '(Sélectionner Secteur)' as " + PRISE_COLUMN_SECTEUR + "  UNION SELECT  distinct " + PRISE_COLUMN_CODE_SECTEUR + " ," + PRISE_COLUMN_SECTEUR + " FROM " + PRISE_TABLE_NAME + " ORDER BY " + PRISE_COLUMN_SECTEUR + " ASC";
        query = "SELECT  distinct " + PRISE_COLUMN_CODE_SECTEUR + " ," + PRISE_COLUMN_SECTEUR + " FROM " + PRISE_TABLE_NAME + " ORDER BY " + PRISE_COLUMN_SECTEUR + " ASC";


        ArrayList<Secteur> secteurList = new ArrayList<Secteur>();
        //SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = mDb.rawQuery(query, null);
        Secteur sect;

        if (cursor.moveToFirst()) {
            do {
                sect = new Secteur();
                sect.setCode_secteur(cursor.getString(cursor.getColumnIndex(PRISE_COLUMN_CODE_SECTEUR)));
                sect.setSecteur(cursor.getString(cursor.getColumnIndex(PRISE_COLUMN_SECTEUR)));

                secteurList.add(sect);
            } while (cursor.moveToNext());
        }

        return secteurList;
    }

    public void InsererPrisesFromJson(JSONArray jsonarr, Context context){
        Prise pr;
        PriseDAO prdao =new PriseDAO(context);
        prdao.supprimerTout();
        int l=jsonarr.length();
        try {
            for(int i=0;i<jsonarr.length();i++){
                JSONObject jsonObj=jsonarr.getJSONObject(i);
                pr=new Prise(jsonObj.getString("code_prise"),jsonObj.getString("n_prise"), jsonObj.getString("code_antenne"), jsonObj.getString("antenne"), jsonObj.getString("code_secteur"), jsonObj.getString("secteur"), jsonObj.getString("code_zoneaig"), jsonObj.getString("zoneaig"), jsonObj.getString("code_cmv"), jsonObj.getString("row_id"));
                prdao.ajouter(pr);
            }

        } catch (JSONException e) {
            Log.e("MYAPP", "unexpected JSON exception", e);
        }

    }

    public int getNbPrises() {
        // CODE
        Cursor res = mDb.rawQuery("SELECT count(*) as nb_tot_prises FROM " + PRISE_TABLE_NAME, null);
        int nb = 0;
        if (res.moveToFirst()) {
            res.moveToFirst();
            nb = res.getInt(res.getColumnIndex("nb_tot_prises"));
        }
        return nb;
    }


}
