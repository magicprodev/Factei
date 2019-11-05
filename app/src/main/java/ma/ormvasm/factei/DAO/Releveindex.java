package ma.ormvasm.factei.DAO;

public class Releveindex {
    private int id_releveindex;
    private String code_prise;
    private String date_debut_index;
    private String date_fin_index;
    private int index_debut;
    private int index_fin;
    private String code_etat_prise;
    private int volume_index;
    private int valide;
    private String code_cmv;
    private String date_maj;
    private String utilisateur_maj;
    private String date_insert;
    private String utilisateur_insert;
    private String observations;
    private String row_id;
    private double position_x;
    private double position_y;


    public Releveindex(int id_releveindex,String code_prise,String date_debut_index,String date_fin_index,int index_debut,int index_fin,String code_etat_prise,int volume_index,int valide,String code_cmv,String date_maj,String utilisateur_maj,String date_insert,String utilisateur_insert,String observations,double position_x,double position_y,String row_id){
        this.id_releveindex=id_releveindex;
        this.code_prise=code_prise;
        this.date_debut_index=date_debut_index;
        this.date_fin_index=date_fin_index;
        this.index_debut=index_debut;
        this.index_fin=index_fin;
        this.code_etat_prise=code_etat_prise;
        this.volume_index=volume_index;
        this.valide=valide;
        this.code_cmv=code_cmv;
        this.date_maj=date_maj;
        this.utilisateur_maj=utilisateur_maj;
        this.date_insert=date_insert;
        this.utilisateur_insert=utilisateur_insert;
        this.observations=observations;
        this.position_x=position_x;
        this.position_y=position_y;
        this.row_id=row_id;
    }
    public int getId_releveindex(){
        return id_releveindex;
    }

    public void setId_releveindex(int id_releveindex){
        this.id_releveindex=id_releveindex;
    }

    public String getCode_prise(){
        return code_prise;
    }

    public void setCode_prise(String code_prise){
        this.code_prise=code_prise;
    }

    public String getDate_debut_index(){
        return date_debut_index;
    }

    public void setDate_debut_index(String date_debut_index){
        this.date_debut_index=date_debut_index;
    }

    public String getDate_fin_index(){
        return date_fin_index;
    }

    public void setDate_fin_index(String date_fin_index){
        this.date_fin_index=date_fin_index;
    }

    public int getIndex_debut(){
        return index_debut;
    }

    public void setIndex_debut(int index_debut){
        this.index_debut=index_debut;
    }

    public int getIndex_fin(){
        return index_fin;
    }

    public void setIndex_fin(int index_fin){
        this.index_fin=index_fin;
    }

    public String getCode_etat_prise(){
        return code_etat_prise;
    }

    public void setCode_etat_prise(String code_etat_prise){
        this.code_etat_prise=code_etat_prise;
    }

    public int getVolume_index(){
        return volume_index;
    }

    public void setVolume_index(int volume_index){
        this.volume_index=volume_index;
    }

    public int getValide(){
        return valide;
    }

    public void setValide(int valide){
        this.valide=valide;
    }

    public String getCode_cmv(){
        return code_cmv;
    }

    public void setCode_cmv(String code_cmv){
        this.code_cmv=code_cmv;
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

    public String getObservations(){
        return observations;
    }

    public void setObservations(String observations){
        this.observations=observations;
    }

    public double getPosition_x(){
        return position_x;
    }

    public void setPosition_x(double volume_index){
        this.position_x=position_x;
    }

    public double getPosition_y(){return position_y; }

    public void setPosition_y(double volume_index){this.position_y=position_y; }

    public String getRow_id(){
        return row_id;
    }

    public void setRow_id(String row_id){
        this.row_id=row_id;
    }
}
