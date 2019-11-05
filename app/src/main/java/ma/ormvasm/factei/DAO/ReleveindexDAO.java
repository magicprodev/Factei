package ma.ormvasm.factei.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ReleveindexDAO extends DAOBase {


    public static final String RELEVEINDEX_TABLE_NAME = "releveindex";
    public static final String RELEVEINDEX_COLUMN_ID_RELEVEINDEX = "id_releveindex";
    public static final String RELEVEINDEX_COLUMN_CODE_PRISE = "code_prise";
    public static final String RELEVEINDEX_COLUMN_DATE_DEBUT_INDEX = "date_debut_index";
    public static final String RELEVEINDEX_COLUMN_DATE_FIN_INDEX = "date_fin_index";
    public static final String RELEVEINDEX_COLUMN_INDEX_DEBUT = "index_debut";
    public static final String RELEVEINDEX_COLUMN_INDEX_FIN = "index_fin";
    public static final String RELEVEINDEX_COLUMN_CODE_ETAT_PRISE = "code_etat_prise";
    public static final String RELEVEINDEX_COLUMN_VOLUME_INDEX = "volume_index";
    public static final String RELEVEINDEX_COLUMN_VALIDE = "valide";
    public static final String RELEVEINDEX_COLUMN_CODE_CMV = "code_cmv";
    public static final String RELEVEINDEX_COLUMN_DATE_MAJ = "date_maj";
    public static final String RELEVEINDEX_COLUMN_UTILISATEUR_MAJ = "utilisateur_maj";
    public static final String RELEVEINDEX_COLUMN_DATE_INSERT = "date_insert";
    public static final String RELEVEINDEX_COLUMN_UTILISATEUR_INSERT= "utilisateur_insert";
    public static final String RELEVEINDEX_COLUMN_OBSERVATIONS= "observations";
    public static final String RELEVEINDEX_COLUMN_POSITION_X= "position_x";
    public static final String RELEVEINDEX_COLUMN_POSITION_Y= "position_y";
    public static final String RELEVEINDEX_COLUMN_ROW_ID = "row_id";
    private String cond_releve="";
    public ReleveindexDAO(Context pContext) {

        super(pContext);
        open();
    }

    /**
     * @param r le relevé d'index à ajouter à la base
     */
    public void ajouter(Releveindex r) {
        // CODE
        ContentValues value = new ContentValues();
        value.put(RELEVEINDEX_COLUMN_CODE_PRISE, r.getCode_prise());
        value.put(RELEVEINDEX_COLUMN_DATE_DEBUT_INDEX, r.getDate_debut_index());
        value.put(RELEVEINDEX_COLUMN_DATE_FIN_INDEX, r.getDate_fin_index());
        value.put(RELEVEINDEX_COLUMN_INDEX_DEBUT, r.getIndex_debut());
        value.put(RELEVEINDEX_COLUMN_INDEX_FIN, r.getIndex_fin());
        value.put(RELEVEINDEX_COLUMN_CODE_ETAT_PRISE, r.getCode_etat_prise());
        value.put(RELEVEINDEX_COLUMN_VOLUME_INDEX, r.getVolume_index());
        value.put(RELEVEINDEX_COLUMN_VALIDE, r.getValide());
        value.put(RELEVEINDEX_COLUMN_CODE_CMV, r.getCode_cmv());
        value.put(RELEVEINDEX_COLUMN_DATE_MAJ, r.getDate_maj());
        value.put(RELEVEINDEX_COLUMN_UTILISATEUR_MAJ, r.getUtilisateur_maj());
        value.put(RELEVEINDEX_COLUMN_DATE_INSERT, r.getDate_insert());
        value.put(RELEVEINDEX_COLUMN_UTILISATEUR_INSERT, r.getUtilisateur_insert());
        value.put(RELEVEINDEX_COLUMN_OBSERVATIONS, r.getObservations());
        value.put(RELEVEINDEX_COLUMN_POSITION_X, r.getPosition_x());
        value.put(RELEVEINDEX_COLUMN_POSITION_Y, r.getPosition_y());
        value.put(RELEVEINDEX_COLUMN_ROW_ID, r.getRow_id());
        mDb.insert(RELEVEINDEX_TABLE_NAME, null, value);

    }

    /**
     * @param id l'identifiant du métier à supprimer
     */
    public void supprimer(long id) {
        // CODE
        mDb.delete(RELEVEINDEX_TABLE_NAME, RELEVEINDEX_COLUMN_ID_RELEVEINDEX + " = ?", new String[] {String.valueOf(id)});
    }

    /**
     * @param r le relevé d'index modifié
     */
    public void modifier(Releveindex r) {
        // CODE
        ContentValues value = new ContentValues();
        value.put(RELEVEINDEX_COLUMN_CODE_PRISE, r.getCode_prise());
        value.put(RELEVEINDEX_COLUMN_DATE_DEBUT_INDEX, r.getDate_debut_index());
        value.put(RELEVEINDEX_COLUMN_DATE_FIN_INDEX, r.getDate_fin_index());
        value.put(RELEVEINDEX_COLUMN_INDEX_DEBUT, r.getIndex_debut());
        value.put(RELEVEINDEX_COLUMN_INDEX_FIN, r.getIndex_fin());
        value.put(RELEVEINDEX_COLUMN_CODE_ETAT_PRISE, r.getCode_etat_prise());
        value.put(RELEVEINDEX_COLUMN_VOLUME_INDEX, r.getVolume_index());
        value.put(RELEVEINDEX_COLUMN_VALIDE, r.getValide());
        value.put(RELEVEINDEX_COLUMN_CODE_CMV, r.getCode_cmv());
        value.put(RELEVEINDEX_COLUMN_DATE_MAJ, r.getDate_maj());
        value.put(RELEVEINDEX_COLUMN_UTILISATEUR_MAJ, r.getUtilisateur_maj());
        value.put(RELEVEINDEX_COLUMN_DATE_INSERT, r.getDate_insert());
        value.put(RELEVEINDEX_COLUMN_UTILISATEUR_INSERT, r.getUtilisateur_insert());
        value.put(RELEVEINDEX_COLUMN_OBSERVATIONS, r.getObservations());
        value.put(RELEVEINDEX_COLUMN_POSITION_Y, r.getPosition_x());
        value.put(RELEVEINDEX_COLUMN_POSITION_Y, r.getPosition_y());
        value.put(RELEVEINDEX_COLUMN_ROW_ID, r.getRow_id());

        mDb.update(RELEVEINDEX_TABLE_NAME, value, RELEVEINDEX_COLUMN_ID_RELEVEINDEX  + " = ?", new String[] {String.valueOf(r.getId_releveindex())});
    }

    /**
     * @param //id l'identifiant du relevé d'index à récupérer
     */
    public Releveindex getData(String st) {
        // CODE
        Cursor res =  mDb.rawQuery( "SELECT * FROM " + RELEVEINDEX_TABLE_NAME + " WHERE " + RELEVEINDEX_COLUMN_ID_RELEVEINDEX +  " ='" + st + "'", null );

        if (res.moveToFirst()){
            res.moveToFirst();
            Releveindex r;
            r=objectFromCursor(res);
            return r;
        }
        else {
            return null;
        }

    }

    public Releveindex getData2(String st) {
        // CODE
        Cursor res =  mDb.rawQuery( "select id_releveindex,r.code_prise,date_debut_index,date_fin_index,index_debut,index_fin," +
                "code_etat_prise,volume_index,valide,secteur as code_cmv,date_maj,utilisateur_maj,date_insert,utilisateur_insert," +
                "observations,position_x,position_y,n_prise as row_id" +
                " from releveindex r join prise p on r.code_prise=p.code_prise  " +
                " WHERE id_releveindex ='" + st + "'", null );

        if (res.moveToFirst()){
            res.moveToFirst();
            Releveindex r;
            r=objectFromCursor(res);
            return r;
        }
        else {
            return null;
        }

    }

    public Releveindex objectFromCursor(Cursor curs){
        Releveindex r;
        r=new Releveindex(curs.getInt(curs.getColumnIndex(RELEVEINDEX_COLUMN_ID_RELEVEINDEX)),
                curs.getString(curs.getColumnIndex(RELEVEINDEX_COLUMN_CODE_PRISE)),
                curs.getString(curs.getColumnIndex(RELEVEINDEX_COLUMN_DATE_DEBUT_INDEX)),
                curs.getString(curs.getColumnIndex(RELEVEINDEX_COLUMN_DATE_FIN_INDEX)),
                curs.getInt(curs.getColumnIndex(RELEVEINDEX_COLUMN_INDEX_DEBUT)),
                curs.getInt(curs.getColumnIndex(RELEVEINDEX_COLUMN_INDEX_FIN)),
                curs.getString(curs.getColumnIndex(RELEVEINDEX_COLUMN_CODE_ETAT_PRISE)),
                curs.getInt(curs.getColumnIndex(RELEVEINDEX_COLUMN_VOLUME_INDEX)),
                curs.getInt(curs.getColumnIndex(RELEVEINDEX_COLUMN_VALIDE)),
                curs.getString(curs.getColumnIndex(RELEVEINDEX_COLUMN_CODE_CMV)),
                curs.getString(curs.getColumnIndex(RELEVEINDEX_COLUMN_DATE_MAJ)),
                curs.getString(curs.getColumnIndex(RELEVEINDEX_COLUMN_UTILISATEUR_MAJ)),
                curs.getString(curs.getColumnIndex(RELEVEINDEX_COLUMN_DATE_INSERT)),
                curs.getString(curs.getColumnIndex(RELEVEINDEX_COLUMN_UTILISATEUR_INSERT)),
                curs.getString(curs.getColumnIndex(RELEVEINDEX_COLUMN_OBSERVATIONS)),
                curs.getDouble(curs.getColumnIndex(RELEVEINDEX_COLUMN_POSITION_X)),
                curs.getDouble(curs.getColumnIndex(RELEVEINDEX_COLUMN_POSITION_Y)),
                curs.getString(curs.getColumnIndex(RELEVEINDEX_COLUMN_ROW_ID)));
        return r;
    }


    public ArrayList<Releveindex> getListReleveindex() {
        ArrayList<Releveindex> array_list = new ArrayList<Releveindex>();

        //hp = new HashMap();
        String stCond="";
        if (!cond_releve.equals("")){
            stCond=" where " + cond_releve;
                    }
        if (stCond.equals("")){
            stCond=" where valide=0 ";
        }
        else {
            stCond=stCond+" and valide=0 ";
        }
        Cursor res =  mDb.rawQuery( "select id_releveindex,code_prise,date_debut_index,date_fin_index,index_debut,index_fin," +
                "etat_prise as code_etat_prise,volume_index,valide,code_cmv,date_maj,utilisateur_maj,date_insert,utilisateur_insert," +
                "observations,position_x,position_y,row_id" +
                " from releveindex r join etatprise e on r.code_etat_prise=e.code_etat_prise " +
                stCond +
                " order by code_prise,substr('0000000000'||code_prise, -10, 10)", null );
        res.moveToFirst();
        Releveindex r;

        while(res.isAfterLast() == false){
            r=objectFromCursor(res);
            array_list.add(r);
            res.moveToNext();
        }
        return array_list;
    }

    public Releveindex getDernierReleve(String code_prise) {
        // CODE
        String query ="SELECT * FROM " + RELEVEINDEX_TABLE_NAME + " WHERE " + RELEVEINDEX_COLUMN_CODE_PRISE + " ='" + code_prise + "' ORDER BY " + RELEVEINDEX_COLUMN_DATE_FIN_INDEX + " DESC LIMIT 1";

        Cursor res =  mDb.rawQuery( query, null );

        if (res.moveToFirst()){
            //res.moveToFirst();
            Releveindex r;
            r=objectFromCursor(res);
            return r;
        }
        else {
            return null;
        }

    }
    public void setCondReleve(String cond) {

        cond_releve = cond;
            }
    public int getNbRelevesindex() {
        // CODE
        Cursor res = mDb.rawQuery("SELECT count(*) as nb_tot_releves FROM " + RELEVEINDEX_TABLE_NAME+" WHERE valide=0", null);
        int nb = 0;
        if (res.moveToFirst()) {
            res.moveToFirst();
            nb = res.getInt(res.getColumnIndex("nb_tot_releves"));
        }
        return nb;
    }

    public void InsererRelevesFromJson(JSONArray jsonarr, Context context){
        Releveindex r;
        ReleveindexDAO rdao =new ReleveindexDAO(context);
        rdao.supprimerTout();

        try {
            for(int i=0;i<jsonarr.length();i++){
                JSONObject jsonObj=jsonarr.getJSONObject(i);
                r=new Releveindex(jsonObj.getInt("id_releveindex"),jsonObj.getString("code_prise"),jsonObj.getString("date_debut_index"),jsonObj.getString("date_fin_index"),jsonObj.getInt("index_debut"),jsonObj.getInt("index_fin"),jsonObj.getString("code_etat_prise"),jsonObj.getInt("volume_index"),jsonObj.getInt("valide"),jsonObj.getString("code_cmv"),jsonObj.getString("date_maj"),jsonObj.getString("utilisateur_maj"),jsonObj.getString("date_insert"),jsonObj.getString("utilisateur_insert"),jsonObj.getString("observations"),jsonObj.getDouble("position_x"),jsonObj.getDouble("position_y"),jsonObj.getString("row_id"));
                rdao.ajouter(r);
            }

        } catch (JSONException e) {
            Log.e("MYAPP", "unexpected JSON exception", e);
        }

    }


    public void supprimerTout() {
        // CODE
        mDb.delete(RELEVEINDEX_TABLE_NAME, "", new String[] {});
    }
}
