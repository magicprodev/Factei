package ma.ormvasm.factei.DAO;

public class Autorisation {
    private int id_autorisation;
    private String groupe;
    private String ecran;
    private String droit_access;
    private String droit_insert;
    private String droit_update;
    private String droit_delete;

    public Autorisation(int id_autorisation, String groupe, String ecran, String droit_access, String droit_insert, String droit_update, String droit_delete){
        this.id_autorisation = id_autorisation;
        this.groupe = groupe;
        this.ecran = ecran;
        this.droit_access = droit_access;
        this.droit_insert = droit_insert;
        this.droit_update = droit_update;
        this.droit_delete = droit_delete;
    }

    public int getId_autorisation(){
        return id_autorisation;
    }

    public void setId_autorisation(int Id_autorisation){
        this.id_autorisation=id_autorisation;
    }

    public String getGroupe(){
        return groupe;
    }

    public void setGroupe(String Groupe){
        this.groupe=groupe;
    }

    public String getEcran(){
        return ecran;
    }

    public void setEcran(String Ecran){
        this.ecran=ecran;
    }

    public String getDroit_access(){
        return droit_access;
    }

    public void setDroit_access(String Droit_access){
        this.droit_access=droit_access;
    }

    public String getDroit_insert(){
        return droit_insert;
    }

    public void setDroit_insert(String Droit_insert){
        this.droit_insert=droit_insert;
    }

    public String getDroit_update(){
        return droit_update;
    }

    public void setDroit_update(String Droit_update){
        this.droit_update=droit_update;
    }

    public String getDroit_delete(){
        return droit_delete;
    }

    public void setDroit_delete(String Droit_delete){
        this.droit_delete=droit_delete;
    }



   }
