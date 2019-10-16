package ma.ormvasm.factei.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public abstract class DAOBase {

    // Nous sommes à la première version de la base
    // Si je décide de la mettre à jour, il faudra changer cet attribut
    protected final static int VERSION = 10;
    // Le nom du fichier qui représente ma base
    protected final static String NOM = "factei.db";

    protected SQLiteDatabase mDb = null;
    protected DBHelper mHandler = null;

    public DAOBase(Context pContext) {
        this.mHandler = new DBHelper(pContext, NOM, null, VERSION);
    }



    public SQLiteDatabase open() {
        // Pas besoin de fermer la dernière base puisque getWritableDatabase s'en charge
        mDb = mHandler.getWritableDatabase();
        return mDb;
    }

    public void close() {
        mDb.close();
    }

    public SQLiteDatabase getDb() {
        return mDb;
    }
}
