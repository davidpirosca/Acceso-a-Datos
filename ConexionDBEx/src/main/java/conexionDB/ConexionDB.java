package conexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import oracle.ucp.jdbc.PoolDataSourceFactory;
import oracle.ucp.jdbc.PoolDataSource;

public class ConexionDB {

    static final String QUERY = "SELECT * FROM videojuegos";
    static final String QUERYINSERT = "INSERT INTO videojuegos VALUES (NULL, 'SinChan', "
            + "'Flipa en Colores', '2023-12-29', 'Miguel Pardo', 100)";
    static final String DELETE = "DELETE FROM videojuegos WHERE nombre = 'SinChan'";
    static final String UPDATE = "UPDATE videojuegos SET Nombre = 'NuevoNombre', "
            + "Genero = 'NuevoGenero', FechaLanzamiento = '2023-12-30', "
            + "Compañia = 'NuevaCompañia', Precio = 150.00 WHERE id = 23";

    static final String nombre = "Horizon";
    static final String titulo = "SinChan";
    static final String genero = "Flipa en Colores";
    static final Date fecha = Date.valueOf("2023-12-29");
    static final String compañia = "Miguel Pardo";
    static final float precio = (float) 100.10;

    static final PoolDataSource pds = PoolDataSourceFactory.getPoolDataSource(); //crear el pool de conexiones

    public static void main(String[] args) {

        try {
            //crear un objeto de la clase de conexiones que tiene los datos para las conexiones
            DatosConexion datosConexion = new DatosConexion();

            //configuracion del pool de conexiones con lo datos que viene en la clase
            pds.setConnectionFactoryClassName(datosConexion.getCLASSNAME());
            pds.setURL(datosConexion.getDB_URL()); //url de conexion de la base de datos
            pds.setUser(datosConexion.getUSER()); //usuario de la base de datos con el que vamos a conectarnos
            pds.setPassword(datosConexion.getPASS()); //la contraseña del usuario de la base de datos
            pds.setInitialPoolSize(5); //tamaño inicial del pool de conexiones

//            System.out.println("================================================");
//            System.out.println("BUSCAR NOMBRE");
//            System.out.println("================================================");
//            if (buscaNombre(nombre, pds)) {
//                System.out.println("Nombre Encontrado!");
//            } else {
//                System.out.println("Nombre No Encontrado!");
//            }
//            System.out.println("================================================");
//            System.out.println("================================================");
            System.out.println("NUEVO REGISTRO POR PARAMETRO");
            System.out.println("================================================");
            lanzaConsulta(QUERY, pds);
            System.out.println("================================================");
        System.out.println("NUEVO REGISTRO POR PARAMETRO");
//        System.out.println("================================================");
//        VideoJuego videoJuego = new VideoJuego(id, titulo, genero, fecha, compañia, precio);
//        nuevoRegistro(videoJuego);
//        System.out.println("================================================");
//        System.out.println("================================================");
//        System.out.println("NUEVO REGISTRO POR TECLADO");
//        System.out.println("================================================");
//        nuevoRegistro(pds);
//        System.out.println("================================================");
//            System.out.println("================================================");
//            System.out.println("ELIMINAR REGISTRO");
//            System.out.println("================================================");
//            String nombre = "Horizon";
//            System.out.println("Eliminar un Registro! -> " + nombre);
//            if (eliminarRegistro(nombre, pds)) {
//                System.out.println("Eliminado!");
//            } else {
//                System.out.println("No Eliminado!");
//            }
//            System.out.println("================================================");
//
//        System.out.println("================================================");
//        System.out.println("PRUEBA CON UPDATE");
//        modificarRegistro(pds);
//        System.out.println("================================================");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //metodo que devuelve un arraylist con todos los datos de la tabla de la base de datos a la que se le pasa el pool de conexiones
    private static ArrayList<VideoJuego> todosLosDatos(PoolDataSource pds) {

        ArrayList<VideoJuego> videojuegos = new ArrayList<>();//creamos el arraylist

        final String QUERY = "SELECT * FROM videojuegos";//select de todo lo que tiene la tabla videojuegos

        VideoJuego videojuegoNuevo;//instancia temporal de la clase videojuego que tiene todos los datos de las conexiones

        try (Connection conn = pds.getConnection()) {//creamos una conexion a la que se le pasa el pool de conexiones con los datos

            Statement stmt = conn.createStatement();//creamos un statment con la conexion

            ResultSet rs = stmt.executeQuery(QUERY);//para ejecutar la consulta con el query creado anteriormente

            while (rs.next()) {//recorrer todos los resultados para obtener todas las filas

                videojuegos.add(videojuegoNuevo = new VideoJuego(//alarraylist se le pasa cada vez otra instancia que cremaos antes con los datos
                        rs.getString("id"),//devuelve el campo id de la tabla
                        rs.getString("Nombre"),
                        rs.getString("Genero"),
                        rs.getDate("FechaLanzamiento"),
                        rs.getString("Compañia"),
                        rs.getFloat("Precio")
                ));
            }

            conn.close();//se cierra la conexion
        } catch (SQLException e) {
            e.printStackTrace();//la ruta de la excepcion para saber dodne falla
        }
        return videojuegos;//devuelve el arraylist creado antes con todos los datos de la tabla
    }

    private static boolean buscaNombre(String nombre, PoolDataSource pds) {

        final String QUERY = "SELECT * FROM videojuegos WHERE Nombre = ?";//query para ver si esta el nombre

        boolean salida = false;//variable para saber si el nombre esta o no

        try (Connection conn = pds.getConnection()) {//creamos la conexion

            //preparestatment al que se le pasa la conexion.preparestatementy la consulta
            PreparedStatement pstmt = conn.prepareStatement(QUERY);
            pstmt.setString(1, nombre);//la ? declarada en el query la primera le asigna la variable nombre

            ResultSet rs = pstmt.executeQuery();//interfaz con metodos para poder ejecutar consultas sql

            while (rs.next()) {//recorrer todos los resultados para obtener todas las filas
                String nombreDentro = rs.getString("Nombre");//asigna lo que tiene el campo de nombre de la tabla

                if (nombreDentro.equals(nombre)) {//si el nombre de la tabla es igual al de la variable le ponemos a true
                    salida = true;//si lo encuentra
                }
            }
            conn.close();//cerrar la conexion
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salida;//valor del boleano de si ha econtrado el nombre o no
    }

    private static void lanzaConsulta(String consulta, PoolDataSource pds) {

        if (consulta != null) {//verifica si la consulta no es nula

            String[] palabras = consulta.split(" ");//divide la consulta en varias partes para poder jugar con cada palabra por separado
            try (Connection conn = pds.getConnection()) {//crea conexion con el pool de conexiones
                Statement stmt = conn.createStatement();//crea el statement con la conexion

                if (palabras[0].equals("SELECT")) {//comprobar si la primera palabra es un select

                    ResultSet rs = stmt.executeQuery(consulta);//ejecuta la consulta
                    while (rs.next()) {//ir linea  linea
                        System.out.println("ID: " + rs.getInt("id"));//muestra el dato del id
                        System.out.println("Nombre: " + rs.getString("Nombre"));
                        System.out.println("Genero: " + rs.getString("Genero"));
                        System.out.println("Fecha Lanzamiento: " + rs.getDate("FechaLanzamiento"));
                        System.out.println("Compañia: " + rs.getString("Compañia"));
                        System.out.println("Precio: " + rs.getFloat("Precio"));
                    }
                } else if (palabras[0].equals("DELETE")//comprueba si es un delete, insert o update
                        || palabras[0].equals("INSERT")
                        || palabras[0].equals("UPDATE")) {

                    stmt.executeUpdate(consulta);//cambiar el metodo por el que accede a los update,insert,delete ya que e sdistinto del select
                    System.out.println("Consulta Realizada!");
                } else {
                    System.out.println("Consulta no es Valida!");
                }

                conn.close();//cerrar la conexion
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Consulta Vacia!");
        }
    }

    //nuevo registro al que se le pasa un objeto de Videojuego que contendra todos los datos de un videojuego
    //aparte tambien el pool de conexiones
    private static void nuevoRegistro(VideoJuego nuevoVideoJuego, PoolDataSource pds) {
        int filasAfectadas = 0;//variable pasa saber la cantidad de las filas afectadas por el insert
        final String QUERYINSERT = "INSERT INTO videojuegos VALUES (NULL, ?, ?, ?, ?, ?)";

        try (Connection conn = pds.getConnection()) {//creamos la conexion con el pool de conexiones

            PreparedStatement pstmt = conn.prepareStatement(QUERYINSERT);//preparamos la conxulta con la conexion y la query
            pstmt.setString(1, nuevoVideoJuego.getNombre());//primer campo del string de Queryinsert
            pstmt.setString(2, nuevoVideoJuego.getGenero());
            pstmt.setDate(3, nuevoVideoJuego.getFechaLanzamiento());
            pstmt.setString(4, nuevoVideoJuego.getCompañia());
            pstmt.setFloat(5, nuevoVideoJuego.getPrecio());

            filasAfectadas = pstmt.executeUpdate();//al int de las filas afectadas se le pasa el preparestatement y executamos el insert
            //y en caso de que alguna fila sea afectada el numero cambiara y ya no sera un 0

            if (filasAfectadas > 0) {//si el numero ha cambiado y ya no es un 0 significa que el insert ha surgido efecto
                System.out.println("Operación Exitosa!");
            } else {
                System.out.println("La Operación Ha Fracasado!");
            }

            conn.close();//cerrar la conexion
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void nuevoRegistro(PoolDataSource pds) {

        VideoJuego videoJuegoMetodo = new VideoJuego();//un objeto de VideoJuego para poder acceder a los metodos
        VideoJuego nuevoVideoJuego;//un objetod e la clase Videojuego para meterle los datos creados a traves de un metodo de la misma clase
        nuevoVideoJuego = videoJuegoMetodo.CrearVideoJuego();

        int filasAfectadas = 0;//varaible para saber cuantas filas han sido afectadas por el insert
        final String QUERYINSERT = "INSERT INTO videojuegos VALUES (NULL, ?, ?, ?, ?, ?)";

        try (Connection conn = pds.getConnection()) {//creamos la conexion con los datos del pooldeconexiones

            PreparedStatement pstmt = conn.prepareStatement(QUERYINSERT);//prepara el query de la conexion y a continuacion se le meten los datos
            pstmt.setString(1, nuevoVideoJuego.getNombre());
            pstmt.setString(2, nuevoVideoJuego.getGenero());
            Date fecha = nuevoVideoJuego.getFechaLanzamiento();//parsear la fecha ya que es un tipo string y ahy que pasarle un tipo date
            pstmt.setDate(3, fecha);
            pstmt.setString(4, nuevoVideoJuego.getCompañia());
            pstmt.setFloat(5, nuevoVideoJuego.getPrecio());

            filasAfectadas = pstmt.executeUpdate();// int de las filas afectadas se le pasa la query para saber cuantas filas has sido afectadas
            if (filasAfectadas > 0) {//si las filas afectadas son mayores a 0 ha funcionado
                System.out.println("Operación Exitosa!");
            } else {
                System.out.println("La Operación Ha Fracasado!");
            }

            conn.close();//cerrar la conexion
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean eliminarRegistro(String nombre, PoolDataSource pds) {
        boolean salida = false;//variable para saber si se ha eliminado algun registro

        final String DELETE = "DELETE FROM videojuegos WHERE nombre = ?";
        int filasAfectadas = 0;//variable para saber si ha sido afectada alguna fila

        try (Connection conn = pds.getConnection()) {//creamos la conexion con los datos del pool

            PreparedStatement pstmt = conn.prepareStatement(DELETE);//preparamos la query
            pstmt.setString(1, nombre);//le asignamos el nombre a borrar a la query

            filasAfectadas = pstmt.executeUpdate();//al int se le pasa el query para saber si ha sido afectada alguna fila

            if (filasAfectadas > 0) {//para saber si ha sido afectada alguna fila
                salida = true;
            }

            conn.close();//cerramos la conexion
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return salida;
    }

    //metodo para modificar un registro en concreto al que s ele pasa el pool de conexiones
    private static void modificarRegistro(PoolDataSource pds) {
        Scanner sc = new Scanner(System.in);
        ArrayList<VideoJuego> videoJuegos = new ArrayList<>();//lista de videojuegos
        videoJuegos = todosLosDatos(pds); //obtener todos los datos de la tabla de la base de datos y meterselos al arraylist

        int id = 0; //id del juego a modificar
        boolean esValido = false; //comprobar si el id existe

        for (VideoJuego videoJuego : videoJuegos) {//mostrar todos los datos del arraylist
            System.out.println(videoJuego.toString());
        }

        //pedir que le insgrese que id quiere modificar
        do {
            System.out.print("Dime el ID que Quieres Modificar -> ");
            if (sc.hasNextInt()) {//comprueba que es un numero
                id = sc.nextInt();//leer el numero ingresado

                //comprueba si el id es valido y si no lo vuelve a pedir
                for (VideoJuego videoJuego : videoJuegos) {
                    if (String.valueOf(id).equals(videoJuego.getId())) {
                        esValido = true;
                    }
                }
            } else {
                System.out.println("Ingresa un número válido. Gracias!");
                sc.next();
            }
        } while (!esValido); //vuelve a pedir el id hasta que ingrese uno valido

        VideoJuego nuevoJuego = null;//creamos un objeto para poder almacenarle los datos que queremos modificar y poder cambiar los datos

        //busca el juego que tiene el id que se le ha ingresado antes
        for (VideoJuego videoJuego : videoJuegos) {
            if (String.valueOf(id).equals(videoJuego.getId())) {
                float precioFloat = videoJuego.getPrecio();
                //al objeto creado antes se le pasan todos los datos que tiene ese videojuego
                nuevoJuego = new VideoJuego(
                        videoJuego.getId(),
                        videoJuego.getNombre(),
                        videoJuego.getGenero(),
                        videoJuego.getFechaLanzamiento(),
                        videoJuego.getCompañia(),
                        precioFloat
                );
            }
        }

        int opcion; //almacenar la opcion que elige el usuario
        boolean salidaBucle = false; //para la salida del bucle

        //menu para que el usuario elija que quiere modificar del objeto
        do {
            System.out.println("===================================");
            System.out.println("¿Qué dato del videojuego deseas modificar?");
            System.out.println("1. Nombre");
            System.out.println("2. Género");
            System.out.println("3. Fecha de Lanzamiento");
            System.out.println("4. Compañía");
            System.out.println("5. Precio");
            System.out.println("6. Salir");
            System.out.println("===================================");

            opcion = sc.nextInt();
            sc.nextLine();

            //dependiendo de que opcion ha seleccionado el usuario ira a una parte o a otra
            switch (opcion) {
                // Opciones para cada campo del videojuego
                case 1: // Modificar Nombre
                    System.out.println("===================================");
                    System.out.println(nuevoJuego.getNombre().toString());
                    System.out.println("===================================");
                    System.out.println("Ingrese el nuevo nombre:");
                    String nuevoNombre = sc.nextLine();
                    nuevoJuego.setNombre(nuevoNombre);
                    break;
                case 2:
                    System.out.println("===================================");
                    System.out.println(nuevoJuego.getGenero().toString());
                    System.out.println("===================================");
                    System.out.println("Ingrese el nuevo género:");
                    String nuevoGenero = sc.nextLine();
                    nuevoJuego.setGenero(nuevoGenero);
                    break;
                case 3:
                    System.out.println("===================================");
                    System.out.println(nuevoJuego.getFechaLanzamiento().toString());
                    System.out.println("===================================");
                    boolean validarFecha = false;
                    String nuevaFecha;
                    do {
                        System.out.println("Ingrese la nueva fecha de lanzamiento (yyyy-mm-dd):");
                        nuevaFecha = sc.nextLine();
                        try {
                            Date fechaLanzamiento = Date.valueOf(nuevaFecha);
                            nuevoJuego.setFechaLanzamiento(fechaLanzamiento);
                            validarFecha = true;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Formato de fecha inválido. Intente de nuevo.");
                        }
                    } while (!validarFecha);
                    break;
                case 4:
                    System.out.println("===================================");
                    System.out.println(nuevoJuego.getCompañia().toString());
                    System.out.println("===================================");
                    System.out.println("Ingrese la nueva compañía:");
                    String nuevaCompania = sc.nextLine();
                    nuevoJuego.setCompañia(nuevaCompania);
                    break;
                case 5:
                    float precio = 0;
                    boolean salidaBuclePrecio = false;
                    System.out.println("===================================");
                    System.out.println(String.valueOf(nuevoJuego.getPrecio()));
                    System.out.println("===================================");
                    do {
                        System.out.println("Ingrese el nuevo precio:");

                        if (sc.hasNextFloat()) {
                            precio = sc.nextFloat();
                            salidaBuclePrecio = true;
                        } else {
                            System.out.println("Ingresa un número válido. Gracias!");
                            sc.next();
                        }
                    } while (!salidaBuclePrecio);
                    nuevoJuego.setPrecio(precio);
                    break;

                case 6:
                    System.out.println("Saliendo del programa!");
                    salidaBucle = true;
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        } while (!salidaBucle);

        modificarRegistro(nuevoJuego, pds);//modifica el registro que se le ha pasado
    }

    private static void modificarRegistro(VideoJuego videojuegoModificado, PoolDataSource pds) {
        DatosConexion datosConexion = new DatosConexion(); //clase que contiene los datos de la conexion
        int filasAfectadas = 0; //variable para saber las filas afectadas
        final String QUERYUPDATE = "UPDATE videojuegos SET nombre = ?, genero = ?, fechaLanzamiento = ?, compañia = ?, precio = ? WHERE id = ?";

        try (Connection conn = pds.getConnection()) {//creamos la conexion
            PreparedStatement pstmt = conn.prepareStatement(QUERYUPDATE);//prapar el statement y con el query
            pstmt.setString(1, videojuegoModificado.getNombre());//pasamos los datos al query
            pstmt.setString(2, videojuegoModificado.getGenero());
            pstmt.setDate(3, videojuegoModificado.getFechaLanzamiento());
            pstmt.setString(4, videojuegoModificado.getCompañia());
            pstmt.setFloat(5, videojuegoModificado.getPrecio());
            pstmt.setString(6, videojuegoModificado.getId());

            filasAfectadas = pstmt.executeUpdate();// saber cuantas filas has sido afectadas

            if (filasAfectadas > 0) {//comprobar si ha habido alguna fila afectada
                System.out.println("Operación Exitosa!");
            } else {
                System.out.println("La Operación Ha Fracasado!");
            }

            conn.close(); //cerramos la conexion
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
