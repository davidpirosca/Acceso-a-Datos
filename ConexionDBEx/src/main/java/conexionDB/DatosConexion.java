package conexionDB;

public class DatosConexion {

    private final String CLASSNAME = "com.mysql.cj.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost:3306/jcvd";
    private final String USER = "user";
    private final String PASS = "1234";

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
