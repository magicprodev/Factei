package ma.ormvasm.factei.DAO;

public class Cmv {
        private String code_cmv;
        private String cmv;

        public Cmv(String code_cmv,String cmv){
            this.code_cmv=code_cmv;
            this.cmv=cmv;
        }
        public String getCode_cmv(){
            return code_cmv;
        }

        public void setCode_cmv(String code_cmv){
            this.code_cmv=code_cmv;
        }

        public String getCmv(){
            return cmv;
        }

        public void setCmv(String cmv){
            this.cmv=cmv;
        }

}
