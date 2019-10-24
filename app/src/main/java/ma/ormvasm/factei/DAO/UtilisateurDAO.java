package ma.ormvasm.factei.DAO;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import java.util.ArrayList;

public class UtilisateurDAO extends DAOBase{

        public static final String UTILISATEUR_TABLE_NAME = "utilisateur";
        public static final String UTILISATEUR_COLUMN_CODE_UTILISATEUR= "code_utilisateur";
        public static final String UTILISATEUR_COLUMN_UTILISATEUR = "utilisateur";
        public static final String UTILISATEUR_COLUMN_MOT_PASSE = "mot_passe";
        public static final String UTILISATEUR_COLUMN_CODE_CMV = "code_cmv";

        private String cond_releve="";

        public UtilisateurDAO(Context pContext) {

            super(pContext);
            open();
        }

        /**
         * @param p le relevé d'index à ajouter à la base
         */
        public void ajouter(Utilisateur p) {
            // CODE
            ContentValues value = new ContentValues();
            value.put(UTILISATEUR_COLUMN_CODE_UTILISATEUR, p.getCode_utilisateur());
            value.put(UTILISATEUR_COLUMN_UTILISATEUR, p.getUtilisateur());
            value.put(UTILISATEUR_COLUMN_MOT_PASSE, p.getMot_passe());
            value.put(UTILISATEUR_COLUMN_CODE_CMV, p.getCode_cmv());

            mDb.insert(UTILISATEUR_TABLE_NAME, null, value);

        }

        /**
         * @param st l'identifiant du métier à supprimer
         */
        public void supprimer(String st) {
            // CODE
            mDb.delete(UTILISATEUR_TABLE_NAME, UTILISATEUR_COLUMN_CODE_UTILISATEUR + " = ?", new String[]{st});
        }

        /**
         * @param p le relevé d'index modifié
         */
        public void modifier(Utilisateur p) {
            // CODE
            ContentValues value = new ContentValues();
            value.put(UTILISATEUR_COLUMN_CODE_UTILISATEUR, p.getCode_utilisateur());
            value.put(UTILISATEUR_COLUMN_UTILISATEUR, p.getUtilisateur());
            value.put(UTILISATEUR_COLUMN_MOT_PASSE, p.getMot_passe());
            value.put(UTILISATEUR_COLUMN_CODE_CMV, p.getCode_cmv());


            mDb.update(UTILISATEUR_TABLE_NAME, value, UTILISATEUR_COLUMN_CODE_UTILISATEUR + " = ?", new String[]{p.getCode_utilisateur()});
        }

        /**
         * @param //id l'identifiant du relevé d'index à récupérer
         */
        public Utilisateur getData(String st) {
            // CODE
            Cursor res = mDb.rawQuery("SELECT * FROM " + UTILISATEUR_TABLE_NAME + " where " + UTILISATEUR_COLUMN_CODE_UTILISATEUR +  " ='" + st + "'", null);

            if (res.moveToFirst()) {
                res.moveToFirst();
                Utilisateur p;

                p = new Utilisateur(
                        res.getString(res.getColumnIndex(UTILISATEUR_COLUMN_CODE_UTILISATEUR)),
                        res.getString(res.getColumnIndex(UTILISATEUR_COLUMN_UTILISATEUR)),
                        res.getString(res.getColumnIndex(UTILISATEUR_COLUMN_MOT_PASSE)),
                        res.getString(res.getColumnIndex(UTILISATEUR_COLUMN_CODE_CMV)));
                return p;
            } else {
                return null;
            }

        }



        public ArrayList<Utilisateur> getAllData() {
            ArrayList<Utilisateur> array_list = new ArrayList<Utilisateur>();
            String stCond="";
            if (!cond_releve.equals("")){
                stCond=" where " + cond_releve;
            }
            //hp = new HashMap();
            Cursor res = mDb.rawQuery("select * from utilisateur " +   stCond +" order by utilisateur", null);
            res.moveToFirst();
            Utilisateur p;

            while (res.isAfterLast() == false) {

                p = new Utilisateur(
                        res.getString(res.getColumnIndex(UTILISATEUR_COLUMN_CODE_UTILISATEUR)),
                        res.getString(res.getColumnIndex(UTILISATEUR_COLUMN_UTILISATEUR)),
                        res.getString(res.getColumnIndex(UTILISATEUR_COLUMN_MOT_PASSE)),
                        res.getString(res.getColumnIndex(UTILISATEUR_COLUMN_CODE_CMV)));

                array_list.add(p);
                res.moveToNext();
            }
            return array_list;
        }

    public void setCondUtilisateur(String cond) {
        cond_releve = cond;
    }

    }


