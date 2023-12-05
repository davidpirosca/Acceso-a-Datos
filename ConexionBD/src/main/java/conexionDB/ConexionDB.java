package conexionDB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class ConexionDB {

    static final String QUERY = "SELECT * FROM videojuegos";
    static final String QUERYINSERT = "INSERT INTO videojuegos VALUES (NULL, 'SinChan', "
            + "'Flipa en Colores', '2023-12-29', 'Miguel Pardo', 100)";
    static final String DELETE = "DELETE FROM videojuegos WHERE nombre = 'SinChan'";
    static final String UPDATE = "UPDATE videojuegos SET Nombre = 'NuevoNombre', "
            + "Genero = 'NuevoGenero', FechaLanzamiento = '2023-12-30', "
            + "Compañia = 'NuevaCompañia', Precio = 150.00 WHERE id = 23";

    static final String nombre = "Red Dead Redemption 2";

    static final String id = null;
    static final String titulo = "SinChan";
    static final String genero = "Flipa en Colores";
    static final Date fecha = Date.valueOf("2023-12-29");
    static final String compañia = "Miguel Pardo";
    static final float precio = (float) 100.10;

    public static void main(String[] args) {
//        System.out.println("================================================");
//        System.out.println("BUSCAR NOMBRE");
//        System.out.println("================================================");
//        if (buscaNombre(nombre)) {
//            System.out.println("Nombre Encontrado!");
//        } else {
//            System.out.println("Nombre No Encontrado!");
//        }
//        System.out.println("================================================");
//        System.out.println("================================================");
//        System.out.println("NUEVO REGISTRO POR PARAMETRO");
//        System.out.println("================================================");
//        lanzaConsulta(QUERY);
//        System.out.println("================================================");
//        System.out.println("NUEVO REGISTRO POR PARAMETRO");
//        System.out.println("================================================");
//        VideoJuego videoJuego = new VideoJuego(id, titulo, genero, fecha, compañia, precio);
//        nuevoRegistro(videoJuego);
//        System.out.println("================================================");
//        System.out.println("================================================");
//        System.out.println("NUEVO REGISTRO POR TECLADO");
//        System.out.println("================================================");
//        nuevoRegistro();
//        System.out.println("================================================");
//        System.out.println("================================================");
//        System.out.println("ELIMINAR REGISTRO");
//        System.out.println("================================================");
//        String nombre = "SinChan";
//        System.out.println("Eliminar un Registro! -> " + nombre);
//        if (EliminarRegistro(nombre)) {
//            System.out.println("Eliminado!");
//        } else {
//            System.out.println("No Eliminado!");
//        }
//        System.out.println("================================================");

        System.out.println("================================================");
        System.out.println("PRUEBA CON UPDATE");
        modificarRegistro();
        System.out.println("================================================");
    }

    //metodo que devuelve un arraylist con todos los datos de la tabla de la base de datos
    private static ArrayList<VideoJuego> todosLosDatos() {
        DatosConexion datosConexion = new DatosConexion();//clase con los datos de la conexion
        ArrayList<VideoJuego> videoJuegos = new ArrayList<>(); //arraylist que contendra todos los datos de la tabla
        final String QUERY = "SELECT * FROM videojuegos";
        VideoJuego videoJuegoNuevo; //objeto para almacenar los datos mas adelante

        try (Connection conn = DriverManager.getConnection(datosConexion.getDB_URL(), datosConexion.getUSER(), datosConexion.getPASS())) {//creamos la conexion
            Statement stmt = conn.createStatement();//cremaos el statement y se lo pasamos a la conexion
            ResultSet rs = stmt.executeQuery(QUERY);//result que es el encargado de hacer las consultas sql

            while (rs.next()) {//vamos linea a linea hasta que no queden mas

                videoJuegos.add(videoJuegoNuevo = new VideoJuego(//agregamos al arraylist un nuevo objeto con los datos de la linea
                        rs.getString("id"),
                        rs.getString("Nombre"),
                        rs.getString("Genero"),
                        rs.getDate("FechaLanzamiento"),
                        rs.getString("Compañia"),
                        rs.getFloat("Precio")
                ));
            }
            conn.close();//cerramos la conexion
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return videoJuegos;//devuelve el arraylist con todos los datos
    }

    //metodo que devuelve un booleano dependiendo si encuentra el nombre en la base de datos o no
    private static boolean buscaNombre(String nombre) {
        boolean salida = false;//variable de salida
        ArrayList<VideoJuego> videojuegos = todosLosDatos();//arraylist al que se le pasa el metodo que saca todos los datos de la tabla

        if (videojuegos != null) { //comprobar si esta el arraylist nulo
            for (VideoJuego videojuego : videojuegos) {//recorre el arraylist uno por uno

                if (videojuego.getNombre().equals(nombre)) {//comprueba si el nombre del objeto  es igual al de la variable
                    salida = true; //variable a true si son iguales
                }
            }
        } else {
            System.out.println("El ArrayList Está Vacío!");
        }
        return salida;//variable si ha encontrado el nombre 
    }

    //metodo para lanzar una consulta
    private static void lanzaConsulta(String consulta) {
        if (consulta != null) {//verificamos que la consulta no sea nula
            DatosConexion datosConexion = new DatosConexion();//objeto con los datos de la conexion
            String[] palabras = consulta.split(" "); //separamos la consulta en varias partes para poder acceder a la primer palabra

            //creamos la conexion con la base de datos a la que se le pasan los datos de la conexion
            try (Connection conn = DriverManager.getConnection(datosConexion.getDB_URL(), datosConexion.getUSER(), datosConexion.getPASS())) {
                Statement stmt = conn.createStatement();//creamos el statement y se le pasa a la conexion para enviarle instrucciones sql

                if (palabras[0].equals("SELECT")) {//comprobamos si la primera palabra de la consulta es un select
                    ResultSet rs = stmt.executeQuery(consulta);//pasamos la consulta a la base de datos por el metodo correspondiente executequery

                    while (rs.next()) {//vamos linea a linea mostrando los datos
                        System.out.println("ID: " + rs.getInt("id"));
                        System.out.println("Nombre: " + rs.getString("Nombre"));
                        System.out.println("Género: " + rs.getString("Genero"));
                        System.out.println("Fecha Lanzamiento: " + rs.getDate("FechaLanzamiento"));
                        System.out.println("Compañia: " + rs.getString("Compañia"));
                        System.out.println("Precio: " + rs.getFloat("Precio"));
                    }
                } else if (palabras[0].equals("DELETE") || palabras[0].equals("INSERT") || palabras[0].equals("UPDATE")) {//comprobamos si es un delete, inset o update
                    stmt.executeUpdate(consulta);//lanzamos la consulta con el metodo adecuado que en este caso seria executeupdate
                    System.out.println("Consulta Realizada!");
                } else {
                    System.out.println("Consulta no es Válida!");
                }
                conn.close();//cerramos la conexion
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Consulta Vacía!");
        }
    }

    //metodo para agregar un nuevo registro pasandole como parametro un objeto
    private static void nuevoRegistro(VideoJuego nuevoVideoJuego) {
        DatosConexion datosConexion = new DatosConexion();//objeto con los datos de la conexion
        int filasAfectadas = 0;//variable para saber si hay alguna fila afectada
        final String QUERYINSERT = "INSERT INTO videojuegos VALUES (NULL, ?, ?, ?, ?, ?)";

        //creamos un objeto conection al que se le pasan los datos de la conexion que los tiene el objeto
        try (Connection conn = DriverManager.getConnection(datosConexion.getDB_URL(), datosConexion.getUSER(), datosConexion.getPASS())) {

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
            e.printStackTrace(); // Manejo de errores
        }
    }

    // Método para agregar un nuevo registro de Videojuego a la base de datos obteniendo la información del usuario
    private static void nuevoRegistro() {
        DatosConexion datosConexion = new DatosConexion(); //objeto con los datos de la conexion
        VideoJuego videoJuegoMetodo = new VideoJuego();//un objeto de VideoJuego para poder acceder a los metodos
        VideoJuego nuevoVideoJuego;//un objetod e la clase Videojuego para meterle los datos creados a traves de un metodo de la misma clase
        nuevoVideoJuego = videoJuegoMetodo.CrearVideoJuego();
        int filasAfectadas = 0;//varaible para saber cuantas filas han sido afectadas por el insert
        final String QUERYINSERT = "INSERT INTO videojuegos VALUES (NULL, ?, ?, ?, ?, ?)";

        //creamos un objeto conection al que se le pasan los datos de la conexion que los tiene el objeto
        try (Connection conn = DriverManager.getConnection(datosConexion.getDB_URL(), datosConexion.getUSER(), datosConexion.getPASS())) {

            PreparedStatement pstmt = conn.prepareStatement(QUERYINSERT);//prepara el query de la conexion y a continuacion se le meten los datos
            pstmt.setString(1, nuevoVideoJuego.getNombre());
            pstmt.setString(2, nuevoVideoJuego.getGenero());
            pstmt.setDate(3, nuevoVideoJuego.getFechaLanzamiento());
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

    //metodo para eliminar un registro al que se le pasa el nombre que queremos borrar
    private static boolean eliminarRegistro(String nombre) {
        boolean salida = false;//variable de salida para saber si se ha eliminado algun registro
        DatosConexion datosConexion = new DatosConexion();//objeto con los datos de la conexion
        final String DELETE = "DELETE FROM videojuegos WHERE nombre = '" + nombre + "'";
        int filasAfectadas = 0;//variable para saber si ha sido afectada alguna fila

        //creamos un objeto conection al que se le pasan los datos de la conexion que los tiene el objeto
        try (Connection conn = DriverManager.getConnection(datosConexion.getDB_URL(), datosConexion.getUSER(), datosConexion.getPASS())) {
            Statement stmt = conn.createStatement();//conexion con el statement
            filasAfectadas = stmt.executeUpdate(DELETE);//variable de las filas se le pasa el statement con la query y si hay alguna fila afectada cambiara el numero

            conn.close();//cerramos la conexion

            if (filasAfectadas > 0) {//comprobamos si ha sido alguna fila afectada
                salida = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salida;//retornamos la variable para saber si ha eliminado o no
    }

    //metodo para modificar un registro de la tabla
    private static void modificarRegistro() {
        Scanner sc = new Scanner(System.in);
        ArrayList<VideoJuego> videoJuegos = todosLosDatos();//arraylist con todos los datos de la tabla
        int id = 0;//variable que contiene el id que se ingresara por teclado
        boolean esValido = false;//variable para el bucle que comprueba qu el id es valido

        for (VideoJuego videoJuego : videoJuegos) {//mostrar todos los datos de la tabla
            System.out.println(videoJuego.toString());
        }

        //comprueba que el id esta en la tabla y solo avanza si es valido
        do {
            System.out.print("Dime el ID que Quieres Modificar -> ");

            if (sc.hasNextInt()) {//comprueba que sea un int si no vuelve arriba hasta que lo sea
                id = sc.nextInt();
                for (VideoJuego videoJuego : videoJuegos) {
                    if (String.valueOf(id).equals(videoJuego.getId())) {//comprobar que el id que se ingresa por teclado esta en el arraylist
                        esValido = true;//variable para saber si esta el id
                    }
                }
            } else {
                System.out.println("Ingresa un número válido. Gracias!");
                sc.next();//limpiar el buffer
            }
        } while (!esValido);//variable para salida del bucle

        VideoJuego nuevoJuego = null;//objeto de videojuego al que se le pasaran los datos para poder ir jugando con ellos y modificarlos
        //antes de volver a pasarle el objeto a la base de datos con los datos modificados

        //volver a buscar el objeto dentro del arraylist con el id seleccionado antes y convertirlo en un objeto                            
        for (VideoJuego videoJuego : videoJuegos) {
            if (String.valueOf(id).equals(videoJuego.getId())) {//si lo encuentra lo convierte en un objeto para poder modificar sus datos
                float precioFloat = videoJuego.getPrecio();
                nuevoJuego = new VideoJuego(videoJuego.getId(), videoJuego.getNombre(),
                        videoJuego.getGenero(), videoJuego.getFechaLanzamiento(),
                        videoJuego.getCompañia(), precioFloat);
            }
        }

        int opcion;//opcion para el bucle siguiente con el menu para cambiar los datos
        boolean salidaBucle = false;//variable que comprueba la salida del bucle del menu

        //bucle del menu
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

            //dependiendo de la opcion elegida modifica un dato del objeto u otro, o por ultimo sale del menu
            switch (opcion) {

                case 1:
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
                    System.out.println(nuevoJuego.getPrecio());
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

        modificarRegistro(nuevoJuego);//llama al metodo para modificar un registro al que se le pasa el objeto
    }

    //metodo que modifica una fila de la base de datos al que se le pasa un objeto con todos los datos nuevos
    private static void modificarRegistro(VideoJuego videojuegoModificado) {
        DatosConexion datosConexion = new DatosConexion();//objeto con todos los datos de conexion
        int filasAfectadas = 0;//variable para saber si a sido afectada alguna fila
        final String QUERYUPDATE = "UPDATE videojuegos SET nombre = ?, genero = ?, fechaLanzamiento = ?, compañia = ?, precio = ? WHERE id = ?";

        //conexion con los datos de la base de datos que vienen del objeto
        try (Connection conn = DriverManager.getConnection(datosConexion.getDB_URL(), datosConexion.getUSER(), datosConexion.getPASS())) {
            PreparedStatement pstmt = conn.prepareStatement(QUERYUPDATE);//preparamos el statement con la query

            //ponemos los  valores de la query que faltan que estan en ?
            pstmt.setString(1, videojuegoModificado.getNombre());
            pstmt.setString(2, videojuegoModificado.getGenero());
            pstmt.setDate(3, videojuegoModificado.getFechaLanzamiento());
            pstmt.setString(4, videojuegoModificado.getCompañia());
            pstmt.setFloat(5, videojuegoModificado.getPrecio());
            pstmt.setString(6, videojuegoModificado.getId());

            filasAfectadas = pstmt.executeUpdate();//a la variable le pasamos el preparestatement y el query para saber cuantas filas han sido afectadas

            if (filasAfectadas > 0) {//comprobamos si ha sido alguna fila afectada
                System.out.println("Operación Exitosa!");
            } else {
                System.out.println("La Operación Ha Fracasado!");
            }

            conn.close();//cerramos la conexion
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
