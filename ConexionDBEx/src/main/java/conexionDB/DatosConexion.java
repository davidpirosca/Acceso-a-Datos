package conexionDB;

public class DatosConexion {

    private final String CLASSNAME = "com.mysql.cj.jdbc.Driver";
//    private final String DB_URL = "jdbc:mysql://localhost:3306/jcvd";
//    private final String USER = "user";
//    private final String PASS = "1234";
    private final String DB_URL = "jdbc:mysql://bkmeidiwxh4ksr900vfm-mysql.services.clever-cloud.com:3306/bkmeidiwxh4ksr900vfm";
    private final String USER = "u36xdnycuv2iciip";
    private final String PASS = "8s4y6TautB3OvPUz1pt3";

    public DatosConexion() {
    }

    public String getDB_URL() {
        return DB_URL;
    }

    public String getUSER() {
        return USER;
    }

    public String getPASS() {
        return PASS;
    }

    public String getCLASSNAME() {
        return CLASSNAME;
    }
}
