package ma.ormvasm.factei.DAO;

import android.content.Context;

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
}
