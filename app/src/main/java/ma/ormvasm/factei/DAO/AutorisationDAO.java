package ma.ormvasm.factei.DAO;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import ma.ormvasm.factei.Helper;

public class AutorisationDAO extends DAOBase {

    public static final String AUTORISATION_TABLE_NAME = "autorisation";
    public static final String AUTORISATION_COLUMN_ID_AUTORISATION = "id_autorisation";
    public static final String AUTORISATION_COLUMN_GROUPE = "groupe";
    public static final String AUTORISATION_COLUMN_ECRAN = "ecran";
    public static final String AUTORISATION_COLUMN_DROIT_ACCESS = "droit_access";
    public static final String AUTORISATION_COLUMN_DROIT_INSERT = "droit_insert";
    public static final String AUTORISATION_COLUMN_DROIT_UPDATE = "droit_update";
    public static final String AUTORISATION_COLUMN_DROIT_DELETE = "droit_delete";

    public AutorisationDAO(Context pContext) {

        super(pContext);
        open();
    }

    public Autorisation getAutorisation(String ecran, String groupe) {
        // CODE
        String req="SELECT * FROM " + AUTORISATION_TABLE_NAME + " where ecran='"+ecran+"' and groupe='"+groupe+"'";


        Cursor res = mDb.rawQuery(req, null);

        if (res.moveToFirst()) {
            res.moveToFirst();
            Autorisation p;

            p = new Autorisation(
                    res.getInt(res.getColumnIndex(AUTORISATION_COLUMN_ID_AUTORISATION)),
                    res.getString(res.getColumnIndex(AUTORISATION_COLUMN_ECRAN)),
                    res.getString(res.getColumnIndex(AUTORISATION_COLUMN_GROUPE)),
                    res.getString(res.getColumnIndex(AUTORISATION_COLUMN_DROIT_ACCESS)),
                    res.getString(res.getColumnIndex(AUTORISATION_COLUMN_DROIT_INSERT)),
                    res.getString(res.getColumnIndex(AUTORISATION_COLUMN_DROIT_UPDATE)),
                    res.getString(res.getColumnIndex(AUTORISATION_COLUMN_DROIT_DELETE)));
            return p;
        } else {
            return null;
        }

    }
}
