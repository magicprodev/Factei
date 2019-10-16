package ma.ormvasm.factei.DAO;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

public class ParametreDAO  extends DAOBase {
    public static final String PARAMETRE_TABLE_NAME = "parametre";
    public static final String PARAMETRE_COLUMN_CODE_PARAMETRE = "code_parametre";
    public static final String PARAMETRE_COLUMN_VALEUR_PARAMETRE = "valeur_parametre";
    public static final String PARAMETRE_COLUMN_DATE_MAJ = "date_maj";
    public static final String PARAMETRE_COLUMN_UTILISATEUR_MAJ = "utilisateur_maj";
    public static final String PARAMETRE_COLUMN_DATE_INSERT = "date_insert";
    public static final String PARAMETRE_COLUMN_UTILISATEUR_INSER = "utilisateur_insert";

    public ParametreDAO(Context pContext) {

        super(pContext);
        open();
    }

    public void ajouter(Parametre p) {
        // CODE
        ContentValues value = new ContentValues();
        value.put(PARAMETRE_COLUMN_CODE_PARAMETRE, p.getCode_parametre());
        value.put(PARAMETRE_COLUMN_VALEUR_PARAMETRE, p.getValeur_parametre());
        value.put(PARAMETRE_COLUMN_DATE_MAJ, p.getDate_maj());
        value.put(PARAMETRE_COLUMN_UTILISATEUR_MAJ, p.getUtilisateur_maj());
        value.put(PARAMETRE_COLUMN_DATE_INSERT, p.getDate_insert());
        value.put(PARAMETRE_COLUMN_UTILISATEUR_INSER, p.getUtilisateur_insert());
        mDb.insert(PARAMETRE_TABLE_NAME, null, value);
    }

    public void supprimer(String st) {
        // CODE
        mDb.delete(PARAMETRE_TABLE_NAME, PARAMETRE_COLUMN_CODE_PARAMETRE + " = ?", new String[]{st});
    }

    public void modifier(Parametre p) {
        // CODE
        ContentValues value = new ContentValues();
        value.put(PARAMETRE_COLUMN_CODE_PARAMETRE, p.getCode_parametre());
        value.put(PARAMETRE_COLUMN_VALEUR_PARAMETRE, p.getValeur_parametre());
        value.put(PARAMETRE_COLUMN_DATE_MAJ, p.getDate_maj());
        value.put(PARAMETRE_COLUMN_UTILISATEUR_MAJ, p.getUtilisateur_maj());
        value.put(PARAMETRE_COLUMN_DATE_INSERT, p.getDate_insert());
        value.put(PARAMETRE_COLUMN_UTILISATEUR_INSER, p.getUtilisateur_insert());
        mDb.insert(PARAMETRE_TABLE_NAME, null, value);

        mDb.update(PARAMETRE_TABLE_NAME, value, PARAMETRE_COLUMN_CODE_PARAMETRE + " = ?", new String[]{p.getCode_parametre()});
    }

    public Parametre getData(String st) {
        // CODE
        Cursor res = mDb.rawQuery("SELECT * FROM " + PARAMETRE_TABLE_NAME + " where " + PARAMETRE_COLUMN_CODE_PARAMETRE + " ='" + st + "'", null);

        if (res.moveToFirst()) {
            res.moveToFirst();
            Parametre p;

            p = new Parametre(
                    res.getString(res.getColumnIndex(PARAMETRE_COLUMN_CODE_PARAMETRE)),
                    res.getString(res.getColumnIndex(PARAMETRE_COLUMN_VALEUR_PARAMETRE)),
                    res.getString(res.getColumnIndex(PARAMETRE_COLUMN_DATE_MAJ)),
                    res.getString(res.getColumnIndex(PARAMETRE_COLUMN_UTILISATEUR_MAJ)),
                    res.getString(res.getColumnIndex(PARAMETRE_COLUMN_DATE_INSERT)),
                    res.getString(res.getColumnIndex(PARAMETRE_COLUMN_UTILISATEUR_INSER)));

            return p;
        } else {
            return null;
        }

    }

    public ArrayList<Parametre> getAllData() {
        ArrayList<Parametre> array_list = new ArrayList<Parametre>();

        //hp = new HashMap();
        Cursor res = mDb.rawQuery("select * from parametre", null);
        res.moveToFirst();
        Parametre p;

        while (res.isAfterLast() == false) {
            p = new Parametre(
            res.getString(res.getColumnIndex(PARAMETRE_COLUMN_CODE_PARAMETRE)),
            res.getString(res.getColumnIndex(PARAMETRE_COLUMN_VALEUR_PARAMETRE)),
            res.getString(res.getColumnIndex(PARAMETRE_COLUMN_DATE_MAJ)),
            res.getString(res.getColumnIndex(PARAMETRE_COLUMN_UTILISATEUR_MAJ)),
            res.getString(res.getColumnIndex(PARAMETRE_COLUMN_DATE_INSERT)),
            res.getString(res.getColumnIndex(PARAMETRE_COLUMN_UTILISATEUR_INSER)));

            array_list.add(p);
            res.moveToNext();
        }
        return array_list;
    }
}

