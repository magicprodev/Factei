package ma.ormvasm.factei.DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

    //TABLE releveindex

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

    public static final String RELEVEINDEX_TABLE_CREATE =
            "CREATE TABLE " + RELEVEINDEX_TABLE_NAME + " (" +
                    RELEVEINDEX_COLUMN_ID_RELEVEINDEX + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    RELEVEINDEX_COLUMN_CODE_PRISE + " TEXT, " +
                    RELEVEINDEX_COLUMN_DATE_DEBUT_INDEX + " TEXT, " +
                    RELEVEINDEX_COLUMN_DATE_FIN_INDEX + " TEXT, " +
                    RELEVEINDEX_COLUMN_INDEX_DEBUT + " INTEGER, " +
                    RELEVEINDEX_COLUMN_INDEX_FIN + " INTEGER, " +
                    RELEVEINDEX_COLUMN_CODE_ETAT_PRISE + " TEXT, " +
                    RELEVEINDEX_COLUMN_VOLUME_INDEX + " INTEGER, " +
                    RELEVEINDEX_COLUMN_VALIDE + " INTEGER, " +
                    RELEVEINDEX_COLUMN_CODE_CMV + " TEXT, " +
                    RELEVEINDEX_COLUMN_DATE_MAJ + " TEXT, " +
                    RELEVEINDEX_COLUMN_UTILISATEUR_MAJ + " TEXT, " +
                    RELEVEINDEX_COLUMN_DATE_INSERT + " TEXT, " +
                    RELEVEINDEX_COLUMN_UTILISATEUR_INSERT + " TEXT, " +
                    RELEVEINDEX_COLUMN_OBSERVATIONS + " TEXT, " +
                    RELEVEINDEX_COLUMN_POSITION_X + " TEXT, " +
                    RELEVEINDEX_COLUMN_POSITION_Y + " TEXT, " +
                    RELEVEINDEX_COLUMN_ROW_ID + " TEXT);";

    public static final String RELEVEINDEX_TABLE_DROP ="DROP TABLE IF EXISTS " + RELEVEINDEX_TABLE_NAME;


    //TABLE parametre

    public static final String PARAMETRE_TABLE_NAME = "parametre";
    public static final String PARAMETRE_COLUMN_CODE_PARAMETRE = "code_parametre";
    public static final String PARAMETRE_COLUMN_VALEUR_PARAMETRE = "valeur_parametre";
    public static final String PARAMETRE_COLUMN_DATE_MAJ = "date_maj";
    public static final String PARAMETRE_COLUMN_UTILISATEUR_MAJ = "utilisateur_maj";
    public static final String PARAMETRE_COLUMN_DATE_INSERT = "date_insert";
    public static final String PARAMETRE_COLUMN_UTILISATEUR_INSER = "utilisateur_insert";

    public static final String PARAMETRE_TABLE_CREATE =
            "CREATE TABLE " + PARAMETRE_TABLE_NAME + " (" +
                    PARAMETRE_COLUMN_CODE_PARAMETRE+ " TEXT, " +
                    PARAMETRE_COLUMN_VALEUR_PARAMETRE+ " TEXT, " +
                    PARAMETRE_COLUMN_DATE_MAJ+ " TEXT, " +
                    PARAMETRE_COLUMN_UTILISATEUR_MAJ+ " TEXT, " +
                    PARAMETRE_COLUMN_DATE_INSERT+ " TEXT, " +
                    PARAMETRE_COLUMN_UTILISATEUR_INSER+ " TEXT); " ;

    public static final String PARAMETRE_TABLE_DROP ="DROP TABLE IF EXISTS " + PARAMETRE_TABLE_NAME;

    //TABLE cmv

    public static final String CMV_TABLE_NAME = "cmv";
    public static final String CMV_COLUMN_CODE_CMV = "code_cmv";
    public static final String CMV_COLUMN_CMV = "cmv";


    public static final String CMV_TABLE_CREATE =
            "CREATE TABLE " + CMV_TABLE_NAME + " (" +
                    CMV_COLUMN_CODE_CMV+ " TEXT, " +
                    CMV_COLUMN_CMV+ " TEXT); " ;

    public static final String CMV_TABLE_DROP ="DROP TABLE IF EXISTS " + CMV_TABLE_NAME;

    //TABLE utilisateur

    public static final String UTILISATEUR_TABLE_NAME = "utilisateur";
    public static final String UTILISATEUR_COLUMN_CODE_UTILISATEUR = "code_utilisateur";
    public static final String UTILISATEUR_COLUMN_UTILISATEUR = "utilisateur";
    public static final String UTILISATEUR_COLUMN_MOT_PASSE = "mot_passe";
    public static final String UTILISATEUR_COLUMN_GROUPE = "groupe";
    public static final String UTILISATEUR_COLUMN_CODE_CMV = "code_cmv";


    public static final String UTILISATEUR_TABLE_CREATE =
            "CREATE TABLE " + UTILISATEUR_TABLE_NAME + " (" +
                    UTILISATEUR_COLUMN_CODE_UTILISATEUR+ " TEXT, " +
                    UTILISATEUR_COLUMN_UTILISATEUR+ " TEXT, " +
                    UTILISATEUR_COLUMN_MOT_PASSE+ " TEXT, " +
                    UTILISATEUR_COLUMN_GROUPE+ " TEXT, " +
                    UTILISATEUR_COLUMN_CODE_CMV+ " TEXT); " ;

    public static final String UTILISATEUR_TABLE_DROP ="DROP TABLE IF EXISTS " + UTILISATEUR_TABLE_NAME;

    //TABLE AUTORISATION

    public static final String AUTORISATION_TABLE_NAME = "autorisation";
    public static final String AUTORISATION_COLUMN_ID_AUTORISATION = "id_autorisation";
    public static final String AUTORISATION_COLUMN_GROUPE = "groupe";
    public static final String AUTORISATION_COLUMN_ECRAN = "ecran";
    public static final String AUTORISATION_COLUMN_DROIT_ACCESS = "droit_access";
    public static final String AUTORISATION_COLUMN_DROIT_INSERT = "droit_insert";
    public static final String AUTORISATION_COLUMN_DROIT_UPDATE = "droit_update";
    public static final String AUTORISATION_COLUMN_DROIT_DELETE = "droit_delete";


    public static final String AUTORISATION_TABLE_CREATE =
            "CREATE TABLE " + AUTORISATION_TABLE_NAME + " (" +
                    AUTORISATION_COLUMN_ID_AUTORISATION + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    AUTORISATION_COLUMN_GROUPE + " TEXT, " +
                    AUTORISATION_COLUMN_ECRAN + " TEXT, " +
                    AUTORISATION_COLUMN_DROIT_ACCESS + " TEXT, " +
                    AUTORISATION_COLUMN_DROIT_INSERT + " INTEGER, " +
                    AUTORISATION_COLUMN_DROIT_UPDATE + " INTEGER, " +
                    AUTORISATION_COLUMN_DROIT_DELETE + " TEXT);";


    public static final String AUTORISATION_TABLE_DROP ="DROP TABLE IF EXISTS " + AUTORISATION_TABLE_NAME;


    //TABLE prise
    public static final String PRISE_TABLE_NAME = "prise";
    public static final String PRISE_COLUMN_CODE_PRISE="code_prise";
    public static final String PRISE_COLUMN_N_PRISE="n_prise";
    public static final String PRISE_COLUMN_CODE_ANTENNE="code_antenne";
    public static final String PRISE_COLUMN_ANTENNE="antenne";
    public static final String PRISE_COLUMN_CODE_SECTEUR="code_secteur";
    public static final String PRISE_COLUMN_SECTEUR="secteur";
    public static final String PRISE_COLUMN_CODE_ZONEAIG="code_zoneaig";
    public static final String PRISE_COLUMN_ZONEAIG="zoneaig";
    public static final String PRISE_COLUMN_CODE_CMV="code_cmv";
    public static final String PRISE_COLUMN_ROW_ID="row_id";

    public static final String PRISE_TABLE_CREATE =
            "CREATE TABLE " + PRISE_TABLE_NAME + " (" +
                    PRISE_COLUMN_CODE_PRISE + " TEXT, " +
                    PRISE_COLUMN_N_PRISE + " TEXT, " +
                    PRISE_COLUMN_CODE_ANTENNE + " TEXT, " +
                    PRISE_COLUMN_ANTENNE + " TEXT, " +
                    PRISE_COLUMN_CODE_SECTEUR + " TEXT, " +
                    PRISE_COLUMN_SECTEUR + " TEXT, " +
                    PRISE_COLUMN_CODE_ZONEAIG + " TEXT, " +
                    PRISE_COLUMN_ZONEAIG + " TEXT, " +
                    PRISE_COLUMN_CODE_CMV + " TEXT, " +
                    PRISE_COLUMN_ROW_ID + " TEXT);";

    public static final String PRISE_TABLE_DROP ="DROP TABLE IF EXISTS " + PRISE_TABLE_NAME;

    //TABLE ETATPRISE

    public static final String ETATPRISE_TABLE_NAME = "etatprise";
    public static final String ETATPRISE_COLUMN_CODE_ETAT_PRISE="code_etat_prise";
    public static final String ETATPRISE_COLUMN_ETAT_PRISE="etat_prise";


    public static final String ETATPRISE_TABLE_CREATE =
            "CREATE TABLE " + ETATPRISE_TABLE_NAME + " (" +
                    ETATPRISE_COLUMN_CODE_ETAT_PRISE + " TEXT, " +
                    ETATPRISE_COLUMN_ETAT_PRISE + " TEXT);";

    public static final String ETATPRISE_TABLE_DROP ="DROP TABLE IF EXISTS " + ETATPRISE_TABLE_NAME;


    private HashMap hp;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name , null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(RELEVEINDEX_TABLE_CREATE);
        db.execSQL(PRISE_TABLE_CREATE);
        db.execSQL(ETATPRISE_TABLE_CREATE);
        db.execSQL(PARAMETRE_TABLE_CREATE);
        db.execSQL(CMV_TABLE_CREATE);
        db.execSQL(UTILISATEUR_TABLE_CREATE);
        db.execSQL(AUTORISATION_TABLE_CREATE);

        //Prises
        //etat prise
        db.execSQL("insert into etatprise(code_etat_prise,etat_prise) values('N','Normal');");
        db.execSQL("insert into etatprise(code_etat_prise,etat_prise) values('B','Bloqué');");

        //parametre
        db.execSQL("INSERT INTO parametre(code_parametre, valeur_parametre) values('CODE_CMV','01');");
        db.execSQL("INSERT INTO parametre(code_parametre, valeur_parametre) values('UTILISATEUR','');");
        db.execSQL("INSERT INTO parametre(code_parametre, valeur_parametre) values('IP_SERVEUR','');");
        db.execSQL("INSERT INTO parametre(code_parametre, valeur_parametre) values('ID_SOCIETE','1');");

        //cmv
        db.execSQL("INSERT INTO cmv(code_cmv, cmv) values('01','801')");
        db.execSQL("INSERT INTO cmv(code_cmv, cmv) values('02','802')");
        db.execSQL("INSERT INTO cmv(code_cmv, cmv) values('03','803')");
        db.execSQL("INSERT INTO cmv(code_cmv, cmv) values('04','804')");
        db.execSQL("INSERT INTO cmv(code_cmv, cmv) values('05','805')");
        db.execSQL("INSERT INTO cmv(code_cmv, cmv) values('06','806')");
        db.execSQL("INSERT INTO cmv(code_cmv, cmv) values('07','807')");
        db.execSQL("INSERT INTO cmv(code_cmv, cmv) values('08','808')");
        db.execSQL("INSERT INTO cmv(code_cmv, cmv) values('09','809')");
        db.execSQL("INSERT INTO cmv(code_cmv, cmv) values('10','810')");
        db.execSQL("INSERT INTO cmv(code_cmv, cmv) values('11','811')");
        db.execSQL("INSERT INTO cmv(code_cmv, cmv) values('12','812')");


        //utilisateur
        db.execSQL("INSERT INTO utilisateur(code_utilisateur,utilisateur,mot_passe,groupe,code_cmv) values('admin','admin','admin','ADM','');");
        db.execSQL("INSERT INTO utilisateur(code_utilisateur,utilisateur,mot_passe,groupe,code_cmv) values('fact','fact','fact','ADF','');");


        //autorisation
        db.execSQL("INSERT INTO autorisation(groupe,ecran,droit_access,droit_insert,droit_update,droit_delete) values('ADM','FragmentExportData','Y','Y','Y','Y');");
        db.execSQL("INSERT INTO autorisation(groupe,ecran,droit_access,droit_insert,droit_update,droit_delete) values('ADM','FragmentImportData','Y','Y','Y','Y');");
        db.execSQL("INSERT INTO autorisation(groupe,ecran,droit_access,droit_insert,droit_update,droit_delete) values('ADM','FragmentListePrise','Y','Y','Y','Y');");
        db.execSQL("INSERT INTO autorisation(groupe,ecran,droit_access,droit_insert,droit_update,droit_delete) values('ADM','FragmentListeUtilisateur','Y','Y','Y','Y');");
        db.execSQL("INSERT INTO autorisation(groupe,ecran,droit_access,droit_insert,droit_update,droit_delete) values('ADM','FragmentSettings','Y','Y','Y','Y');");
        db.execSQL("INSERT INTO autorisation(groupe,ecran,droit_access,droit_insert,droit_update,droit_delete) values('ADM','ListeReleveindex','Y','Y','Y','Y');");
        db.execSQL("INSERT INTO autorisation(groupe,ecran,droit_access,droit_insert,droit_update,droit_delete) values('ADM','ModifReleveindex','Y','Y','Y','Y');");
        db.execSQL("INSERT INTO autorisation(groupe,ecran,droit_access,droit_insert,droit_update,droit_delete) values('ADM','SaisieReleveindex','Y','Y','Y','Y');");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL(RELEVEINDEX_TABLE_DROP);
        db.execSQL(PRISE_TABLE_DROP);
        db.execSQL(ETATPRISE_TABLE_DROP);
        db.execSQL(PARAMETRE_TABLE_DROP);
        db.execSQL(CMV_TABLE_DROP);
        db.execSQL(UTILISATEUR_TABLE_DROP);
        onCreate(db);
    }


}