package ma.ormvasm.factei.DAO;

public class Etatprise {
    private String code_etat_prise;
    private String etat_prise;

    public Etatprise(String code_etat_prise,String etat_prise){
        this.code_etat_prise=code_etat_prise;
        this.etat_prise=etat_prise;

    }

    public String getCode_etat_prise(){
        return code_etat_prise;
    }

    public void setCode_etat_prise(String code_etat_prise){
        this.code_etat_prise=code_etat_prise;
    }

    public String getEtat_prise(){
        return etat_prise;
    }

    public void setEtat_prise(String etat_prise){
        this.etat_prise=etat_prise;
    }

}
