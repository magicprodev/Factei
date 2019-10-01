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
                    RELEVEINDEX_COLUMN_ROW_ID + " TEXT);";

    public static final String RELEVEINDEX_TABLE_DROP ="DROP TABLE IF EXISTS " + RELEVEINDEX_TABLE_NAME;


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
        //Prises

        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('1-EM1','1','s2a1','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('101-EM1','101','s1a1','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('103-EM1','103','s2d','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('105-EM1','105','s2a','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('106-EM1','106','s1a','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('107-EM1','107','s1a','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('108-EM1','108','s1a','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('109-EM1','109','s1a','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('111-EM1','111','s1a','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('112-EM1','112','s1a','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('113-EM1','113','s1a','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('114-EM1','114','s1a','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('115-EM1','115','s1a','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('116-EM1','116','s1a','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('117-EM1','117','s1a','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('119-EM1','119','s1c','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('120-EM1','120','s1c','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('121-EM1','121','s1c','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('122-EM1','122','s1b','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('123-EM1','123','s1b','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('124-EM1','124','s1b','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('125-EM1','125','s1b','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('126-EM1','126','s1b','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('127-EM1','127','s1b','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('128-EM1','128','s1b','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('129-EM1','129','s1b','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('13-EM1','13','s2c','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('130-EM1','130','s1b','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('131-EM1','131','s1b','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('132-EM1','132','s1b','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('133-EM1','133','s1b','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('134-EM1','134','s1b','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('1909-EM1','1909','s2b','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('22-EM1','22','s2d1','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('23-EM1','23','s2d1','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('24-EM1','24','s2d1','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('25-EM1','25','s2d1','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('26-EM1','26','s2d1','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('27-EM1','27','s2d1','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('28-EM1','28','s2d1','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('29-EM1','29','s2d1','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('30-EM1','30','s2d1','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('31-EM1','31','s2d1','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('32-EM1','32','s2d','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('33-EM1','33','s2d','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('34-EM1','34','s2d','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('35-EM1','35','s2d','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('36-EM1','36','s2d','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('37-EM1','37','s2d','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('38-EM1','38','s1d11','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('39-EM1','39','s1d11','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('40-EM1','40','s1d11','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('41-EM1','41','s1d11','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('42-EM1','42','s1d11','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('43-EM1','43','s1d12','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('44-EM1','44','s1d12','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('4400-EM1','4400','s1b','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('45-EM1','45','s1d12','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('6600-EM1','6600','s1a1','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('7000-EM1','7000','s1a','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('8800-EM1','8800','s1b','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('98-EM1','98','s1a1','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('99-EM1','99','s1a1','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('99900-EM1','99900','s1c','A','EM3','El Mhazem 3','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('1-EM1','1','s2a1','A1','EM1','El Mhazem 1','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('101-EM1','101','s1a1','A1','EM1','El Mhazem 1','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('103-EM1','103','s2d','A1','EM1','El Mhazem 1','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('105-EM1','105','s2a','A1','EM1','El Mhazem 1','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('106-EM1','106','s1a','A1','EM1','El Mhazem 1','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('107-EM1','107','s1a','A1','EM1','El Mhazem 1','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('108-EM1','108','s1a','A1','EM1','El Mhazem 1','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('109-EM1','109','s1a','A1','EM1','El Mhazem 1','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('111-EM1','111','s1a','A1','EM1','El Mhazem 1','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('112-EM1','112','s1a','A1','EM1','El Mhazem 1','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('113-EM1','113','s1a','A1','EM1','El Mhazem 1','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('114-EM1','114','s1a','A1','EM1','El Mhazem 1','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('115-EM1','115','s1a','A1','EM1','El Mhazem 1','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('116-EM1','116','s1a','A1','EM1','El Mhazem 1','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('117-EM1','117','s1a','A1','EM1','El Mhazem 1','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('119-EM1','119','s1c','A1','EM1','El Mhazem 1','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('120-EM1','120','s1c','A1','EM1','El Mhazem 1','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('121-EM1','121','s1c','A1','EM1','El Mhazem 1','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('122-EM1','122','s1b','A1','EM1','El Mhazem 1','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('123-EM1','123','s1b','A1','EM1','El Mhazem 1','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('124-EM1','124','s1b','A1','EM1','El Mhazem 1','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('125-EM1','125','s1b','A1','EM1','El Mhazem 1','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('126-EM1','126','s1b','A1','EM1','El Mhazem 1','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('127-EM1','127','s1b','A1','EM1','El Mhazem 1','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('128-EM1','128','s1b','A1','EM1','El Mhazem 1','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('129-EM1','129','s1b','A1','EM1','El Mhazem 1','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('13-EM1','13','s2c','A1','EM1','El Mhazem 1','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('130-EM1','130','s1b','A1','EM1','El Mhazem 1','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('131-EM1','131','s1b','A1','EM1','El Mhazem 1','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('132-EM1','132','s1b','A1','EM1','El Mhazem 1','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('133-EM1','133','s1b','A1','EM1','El Mhazem 1','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('134-EM1','134','s1b','A1','EM1','El Mhazem 1','1','AMRIBTI','04','');");
        db.execSQL("insert into prise(code_prise,n_prise,code_antenne,antenne,code_secteur,secteur,code_zoneaig,zoneaig,code_cmv,row_id) values('1909-EM1','1909','s2b','A1','EM1','El Mhazem 1','1','AMRIBTI','04','');");
        //etat prise
        db.execSQL("insert into etatprise(code_etat_prise,etat_prise) values('N','Normal');");
        db.execSQL("insert into etatprise(code_etat_prise,etat_prise) values('B','Bloqué');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL(RELEVEINDEX_TABLE_DROP);
        db.execSQL(PRISE_TABLE_DROP);
        db.execSQL(ETATPRISE_TABLE_DROP);

        onCreate(db);
    }






}