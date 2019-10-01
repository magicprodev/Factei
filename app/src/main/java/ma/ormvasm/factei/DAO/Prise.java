package ma.ormvasm.factei.DAO;

public class Prise {
    private String code_prise;
    private String n_prise;
    private String code_antenne;
    private String antenne;
    private String code_secteur;
    private String secteur;
    private String code_zoneaig;
    private String zoneaig;
    private String code_cmv;
    private String row_id;

    public Prise(String code_prise,String n_prise, String code_antenne, String antenne, String code_secteur, String secteur, String code_zoneaig, String zoneaig, String code_cmv, String row_id){
        this.code_prise=code_prise;
        this.n_prise=n_prise;
        this.code_antenne=code_antenne;
        this.antenne=antenne;
        this.code_secteur=code_secteur;
        this.secteur=secteur;
        this.code_zoneaig=code_zoneaig;
        this.zoneaig=zoneaig;
        this.code_cmv=code_cmv;
        this.row_id=row_id;
    }

    public String getCode_prise(){
        return code_prise;
    }

    public void setCode_prise(String code_prise){
        this.code_prise=code_prise;
    }

    public String getN_prise(){
        return n_prise;
    }

    public void setN_prise(String n_prise){
        this.n_prise=n_prise;
    }

    public String getCode_antenne(){
        return code_antenne;
    }

    public void setCode_antenne(String code_antenne){
        this.code_antenne=code_antenne;
    }

    public String getAntenne(){
        return antenne;
    }

    public void setAntenne(String antenne){
        this.antenne=antenne;
    }

    public String getCode_secteur(){
        return code_secteur;
    }

    public void setCode_secteur(String code_secteur){
        this.code_secteur=code_secteur;
    }

    public String getSecteur(){
        return secteur;
    }

    public void setSecteur(String secteur){
        this.secteur=secteur;
    }

    public String getCode_zoneaig(){
        return code_zoneaig;
    }

    public void setCode_zoneaig(String code_zoneaig){
        this.code_zoneaig=code_zoneaig;
    }

    public String getZoneaig(){
        return zoneaig;
    }

    public void setZoneaig(String zoneaig){
        this.zoneaig=zoneaig;
    }

    public String getCode_cmv(){
        return code_cmv;
    }

    public void setCode_cmv(String code_cmv){
        this.code_cmv=code_cmv;
    }

    public String getRow_id(){
        return row_id;
    }

    public void setRow_id(String row_id){
        this.row_id=row_id;
    }
}
