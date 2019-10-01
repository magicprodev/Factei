package ma.ormvasm.factei.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

public class EtatpriseDAO extends DAOBase {

    public static final String ETATPRISE_TABLE_NAME = "etatprise";
    public static final String ETATPRISE_COLUMN_CODE_ETAT_PRISE = "code_etat_prise";
    public static final String ETATPRISE_COLUMN_ETAT_PRISE = "etat_prise";


    public EtatpriseDAO(Context pContext) {

        super(pContext);
        open();
    }

    /**
     * @param p le relevé d'index à ajouter à la base
     */
    public void ajouter(Etatprise p) {
        // CODE
        ContentValues value = new ContentValues();
        value.put(ETATPRISE_COLUMN_CODE_ETAT_PRISE, p.getCode_etat_prise());
        value.put(ETATPRISE_COLUMN_ETAT_PRISE, p.getEtat_prise());


        mDb.insert(ETATPRISE_TABLE_NAME, null, value);

    }

    /**
     * @param st l'identifiant du métier à supprimer
     */
    public void supprimer(String st) {
        // CODE
        mDb.delete(ETATPRISE_TABLE_NAME, ETATPRISE_COLUMN_CODE_ETAT_PRISE + " = ?", new String[]{st});
    }

    /**
     * @param p le relevé d'index modifié
     */
    public void modifier(Etatprise p) {
        // CODE
        ContentValues value = new ContentValues();
        value.put(ETATPRISE_COLUMN_CODE_ETAT_PRISE, p.getCode_etat_prise());
        value.put(ETATPRISE_COLUMN_ETAT_PRISE, p.getEtat_prise());

        mDb.update(ETATPRISE_TABLE_NAME, value, ETATPRISE_COLUMN_CODE_ETAT_PRISE + " = ?", new String[]{p.getCode_etat_prise()});
    }

    /**
     * @param //id l'identifiant du relevé d'index à récupérer
     */
    public Etatprise getData(String st) {
        // CODE
        Cursor res = mDb.rawQuery("select * from " + ETATPRISE_TABLE_NAME + "  where code_prise='" + st + "'", null);

        if (res.isAfterLast() != false) {
            res.moveToFirst();
            Etatprise p;

            p = new Etatprise(
                    res.getString(res.getColumnIndex(ETATPRISE_COLUMN_CODE_ETAT_PRISE)),
                    res.getString(res.getColumnIndex(ETATPRISE_COLUMN_ETAT_PRISE)));

            return p;
        } else {
            return null;
        }

    }


    public ArrayList<Etatprise> getAllData() {
        ArrayList<Etatprise> array_list = new ArrayList<Etatprise>();

        //hp = new HashMap();
        Cursor res = mDb.rawQuery("SELECT   " + ETATPRISE_COLUMN_CODE_ETAT_PRISE + " , " + ETATPRISE_COLUMN_ETAT_PRISE + " FROM " + ETATPRISE_TABLE_NAME + " ORDER BY " + ETATPRISE_COLUMN_CODE_ETAT_PRISE + " DESC ", null);
        res.moveToFirst();
        Etatprise p;

        while (res.isAfterLast() == false) {

            p = new Etatprise(
                    res.getString(res.getColumnIndex(ETATPRISE_COLUMN_CODE_ETAT_PRISE)),
                    res.getString(res.getColumnIndex(ETATPRISE_COLUMN_ETAT_PRISE)));

            array_list.add(p);
            res.moveToNext();
        }
        return array_list;
    }

}


