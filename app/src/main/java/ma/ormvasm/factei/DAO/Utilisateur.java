package ma.ormvasm.factei.DAO;

public class Utilisateur {
    private String code_utilisateur;
    private String utilisateur;
    private String mot_passe;
    private String code_cmv;

    public Utilisateur(String code_utilisateur,String utilisateur,String mot_passe, String code_cmv){
        this.code_utilisateur=code_utilisateur;
        this.utilisateur=utilisateur;
        this.mot_passe=mot_passe;
        this.code_cmv=code_cmv;
    }
    public String getCode_utilisateur(){
        return code_utilisateur;
    }

    public void setCode_utilisateur(String code_utilisateur){
        this.code_utilisateur=code_utilisateur;
    }

    public String getUtilisateur(){
        return utilisateur;
    }

    public void setUtilisateur(String utilisateur){
        this.utilisateur=utilisateur;
    }
    public String getMot_passe(){
        return mot_passe;
    }

    public void setMot_passe(String mot_passe){
        this.mot_passe=mot_passe;
    }

    public String getCode_cmv(){
        return code_cmv;
    }

    public void setCode_cmv(String code_cmv){
        this.code_cmv=code_cmv;
    }
}
