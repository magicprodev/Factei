package ma.ormvasm.factei.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

public class CmvDAO extends DAOBase{
    public static final String CMV_TABLE_NAME = "cmv";
    public static final String CMV_COLUMN_CODE_CMV= "code_cmv";
    public static final String CMV_COLUMN_CMV = "cmv";


    public CmvDAO(Context pContext) {

        super(pContext);
        open();
    }

    /**
     * @param p le relevé d'index à ajouter à la base
     */
    public void ajouter(Cmv p) {
        // CODE
        ContentValues value = new ContentValues();
        value.put(CMV_COLUMN_CODE_CMV, p.getCode_cmv());
        value.put(CMV_COLUMN_CMV, p.getCmv());

        mDb.insert(CMV_TABLE_NAME, null, value);

    }

    /**
     * @param st l'identifiant du métier à supprimer
     */
    public void supprimer(String st) {
        // CODE
        mDb.delete(CMV_TABLE_NAME, CMV_COLUMN_CODE_CMV + " = ?", new String[]{st});
    }

    /**
     * @param p le relevé d'index modifié
     */
    public void modifier(Cmv p) {
        // CODE
        ContentValues value = new ContentValues();
        value.put(CMV_COLUMN_CODE_CMV, p.getCode_cmv());
        value.put(CMV_COLUMN_CMV, p.getCmv());;


        mDb.update(CMV_TABLE_NAME, value, CMV_COLUMN_CODE_CMV + " = ?", new String[]{p.getCode_cmv()});
    }

    /**
     * @param //id l'identifiant du relevé d'index à récupérer
     */
    public Cmv getData(String st) {
        // CODE
        Cursor res = mDb.rawQuery("SELECT * FROM " + CMV_TABLE_NAME + " where " + CMV_COLUMN_CODE_CMV +  " ='" + st + "'", null);

        if (res.moveToFirst()) {
            res.moveToFirst();
            Cmv p;

            p = new Cmv(
                    res.getString(res.getColumnIndex(CMV_COLUMN_CODE_CMV)),
                    res.getString(res.getColumnIndex(CMV_COLUMN_CMV)));
            return p;
        } else {
            return null;
        }

    }



    public ArrayList<Cmv> getAllData() {
        ArrayList<Cmv> array_list = new ArrayList<Cmv>();

        //hp = new HashMap();
        Cursor res = mDb.rawQuery("select * from cmv", null);
        res.moveToFirst();
        Cmv p;

        while (res.isAfterLast() == false) {

            p = new Cmv(
                    res.getString(res.getColumnIndex(CMV_COLUMN_CODE_CMV)),
                    res.getString(res.getColumnIndex(CMV_COLUMN_CMV)));

            array_list.add(p);
            res.moveToNext();
        }
        return array_list;
    }



}
