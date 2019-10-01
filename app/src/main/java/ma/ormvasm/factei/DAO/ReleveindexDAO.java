package ma.ormvasm.factei.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

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
    public static final String RELEVEINDEX_COLUMN_ROW_ID = "row_id";

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
        value.put(RELEVEINDEX_COLUMN_ROW_ID, r.getRow_id());

        mDb.update(RELEVEINDEX_TABLE_NAME, value, RELEVEINDEX_COLUMN_ID_RELEVEINDEX  + " = ?", new String[] {String.valueOf(r.getId_releveindex())});
    }

    /**
     * @param //id l'identifiant du relevé d'index à récupérer
     */
    public Releveindex getData(long id) {
        // CODE
        Cursor res =  mDb.rawQuery( "select * from releveindex where id=" + id+ "", null );

        if (res.isAfterLast() != false){
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
                curs.getString(curs.getColumnIndex(RELEVEINDEX_COLUMN_ROW_ID)));
        return r;
    }


    public ArrayList<Releveindex> getAllData() {
        ArrayList<Releveindex> array_list = new ArrayList<Releveindex>();

        //hp = new HashMap();
        Cursor res =  mDb.rawQuery( "select * from releveindex", null );
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

}
