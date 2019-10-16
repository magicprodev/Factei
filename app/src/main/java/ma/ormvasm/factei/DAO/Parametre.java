package ma.ormvasm.factei.DAO;

public class Parametre {
    private String code_parametre;
    private String valeur_parametre;
    private String date_maj;
    private String utilisateur_maj;
    private String date_insert;
    private String utilisateur_insert;

    public Parametre(String code_parametre,String valeur_parametre,String date_maj,String utilisateur_maj,String date_insert,String utilisateur_insert){
        this.code_parametre=code_parametre;
        this.valeur_parametre=valeur_parametre;
        this.date_maj=date_maj;
        this.utilisateur_maj=utilisateur_maj;
        this.date_insert=date_insert;
        this.utilisateur_insert=utilisateur_insert;

    }
    public String getCode_parametre(){
        return code_parametre;
    }

    public void setCode_parametre(String code_parametre){
        this.code_parametre=code_parametre;
    }

    public String getValeur_parametre(){
        return valeur_parametre;
    }

    public void setValeur_parametre(String valeur_parametre){
        this.valeur_parametre=valeur_parametre;
    }

    public String getDate_maj(){
        return date_maj;
    }

    public void setDate_maj(String date_maj){
        this.date_maj=date_maj;
    }

    public String getUtilisateur_maj(){
        return utilisateur_maj;
    }

    public void setUtilisateur_maj(String utilisateur_maj){
        this.utilisateur_maj=utilisateur_maj;
    }

    public String getDate_insert(){
        return date_insert;
    }

    public void setDate_insert(String date_insert){
        this.date_insert=date_insert;
    }

    public String getUtilisateur_insert(){
        return utilisateur_insert;
    }

    public void setUtilisateur_insert(String utilisateur_insert){
        this.utilisateur_insert=utilisateur_insert;
    }
}
