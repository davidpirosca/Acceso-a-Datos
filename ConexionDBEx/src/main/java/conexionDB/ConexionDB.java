package conexionDB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    static final String id = null;
    static final String titulo = "SinChan";
    static final String genero = "Flipa en Colores";
    static final Date fecha = Date.valueOf("2023-12-29");
    static final String compañia = "Miguel Pardo";
    static final float precio = (float) 100.10;
    
    static final PoolDataSource pds = PoolDataSourceFactory.getPoolDataSource();

    public static void main(String[] args) {

        try {
            DatosConexion datosConexion = new DatosConexion();
            pds.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");
            pds.setURL(datosConexion.getDB_URL_ORACLE());
            pds.setUser(datosConexion.getUSER());
            pds.setPassword(datosConexion.getPASS());
            pds.setInitialPoolSize(5);

            System.out.println("================================================");
            System.out.println("BUSCAR NOMBRE");
            System.out.println("================================================");
            if (buscaNombre(nombre, pds)) {
                System.out.println("Nombre Encontrado!");
            } else {
                System.out.println("Nombre No Encontrado!");
            }
//            System.out.println("================================================");
//            System.out.println("================================================");
//            System.out.println("NUEVO REGISTRO POR PARAMETRO");
//            System.out.println("================================================");
//            lanzaConsulta(QUERY, pds);
//            System.out.println("================================================");
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
//        if (eliminarRegistro(nombre)) {
//            System.out.println("Eliminado!");
//        } else {
//            System.out.println("No Eliminado!");
//        }
//        System.out.println("================================================");
//
//        System.out.println("================================================");
//        System.out.println("PRUEBA CON UPDATE");
//        modificarRegistro(pds);
//        System.out.println("================================================");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static ArrayList<VideoJuego> todosLosDatos(PoolDataSource pds) {
        ArrayList<VideoJuego> videojuegos = new ArrayList<>();
        final String QUERY = "SELECT * FROM videojuegos";
        VideoJuego videojuegoNuevo;

        try (Connection conn = pds.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY);
            while (rs.next()) {
                videojuegos.add(videojuegoNuevo = new VideoJuego(rs.getString("id"), rs.getString("Nombre"),
                        rs.getString("Genero"), rs.getDate("FechaLanzamiento"),
                        rs.getString("Compañia"), rs.getFloat("Precio")));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return videojuegos;
    }

    private static boolean buscaNombre(String nombre, PoolDataSource pds) {

        final String QUERY = "SELECT * FROM videojuegos WHERE Nombre = ?";
        boolean salida = false;
        try (Connection conn = pds.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(QUERY);
            pstmt.setString(1, nombre);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String nombreDentro = rs.getString("Nombre");
                if (nombreDentro.equals(nombreDentro)) {
                    salida = true;
                }
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salida;
    }

    private static void lanzaConsulta(String consulta, PoolDataSource pds) {
        if (consulta != null) {
            String[] palabras = consulta.split(" ");
            try (Connection conn = pds.getConnection()) {
                Statement stmt = conn.createStatement();
                if (palabras[0].equals("SELECT")) {
                    ResultSet rs = stmt.executeQuery(consulta);
                    while (rs.next()) {
                        System.out.println("ID: " + rs.getInt("id"));
                        System.out.println("Nombre: " + rs.getString("Nombre"));
                        System.out.println("Genero: " + rs.getString("Genero"));
                        System.out.println("Fecha Lanzamiento: " + rs.getDate("FechaLanzamiento"));
                        System.out.println("Compañia: " + rs.getString("Compañia"));
                        System.out.println("Precio: " + rs.getFloat("Precio"));
                    }
                } else if (palabras[0].equals("DELETE")
                        || palabras[0].equals("INSERT")
                        || palabras[0].equals("UPDATE")) {
                    stmt.executeUpdate(consulta);
                    System.out.println("Consulta Realizada!");
                } else {
                    System.out.println("Consulta no es Valida!");
                }
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Consulta Vacia!");
        }
    }

    private static void nuevoRegistro(VideoJuego nuevoVideoJuego, PoolDataSource pds) {
        int filasAfectadas = 0;
        final String QUERYINSERT = "INSERT INTO videojuegos VALUES (NULL, ?, ?, ?, ?, ?)";

        try (Connection conn = pds.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(QUERYINSERT);
            pstmt.setString(1, nuevoVideoJuego.getNombre());
            pstmt.setString(2, nuevoVideoJuego.getGenero());
            pstmt.setDate(3, nuevoVideoJuego.getFechaLanzamiento());
            pstmt.setString(4, nuevoVideoJuego.getCompañia());
            pstmt.setFloat(5, nuevoVideoJuego.getPrecio());

            filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Operación Exitosa!");
            } else {
                System.out.println("La Operación Ha Fracasado!");
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void nuevoRegistro(PoolDataSource pds) {
        VideoJuego videoJuegoMetodo = new VideoJuego();
        VideoJuego nuevoVideoJuego;
        nuevoVideoJuego = videoJuegoMetodo.CrearVideoJuego();
        int filasAfectadas = 0;
        final String QUERYINSERT = "INSERT INTO videojuegos VALUES (NULL, ?, ?, ?, ?, ?)";

        try (Connection conn = pds.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(QUERYINSERT);
            pstmt.setString(1, nuevoVideoJuego.getNombre());
            pstmt.setString(2, nuevoVideoJuego.getGenero());
            pstmt.setDate(3, nuevoVideoJuego.getFechaLanzamiento());
            pstmt.setString(4, nuevoVideoJuego.getCompañia());
            pstmt.setFloat(5, nuevoVideoJuego.getPrecio());

            filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Operación Exitosa!");
            } else {
                System.out.println("La Operación Ha Fracasado!");
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean eliminarRegistro(String nombre, PoolDataSource pds) {
        boolean salida = false;
        VideoJuego videoJuego = new VideoJuego();
        final String DELETE = "DELETE FROM videojuegos WHERE nombre = ?";
        int filasAfectadas = 0;

        try (Connection conn = pds.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(DELETE);
            pstmt.setString(1, nombre);
            filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                salida = true;
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salida;
    }

    private static void modificarRegistro(PoolDataSource pds) {
        Scanner sc = new Scanner(System.in);
        ArrayList<VideoJuego> videoJuegos = new ArrayList<>();
        videoJuegos = todosLosDatos(pds);
        int id = 0;
        boolean esValido = false;

        for (VideoJuego videoJuego : videoJuegos) {
            System.out.println(videoJuego.toString());
        }

        do {
            System.out.print("Dime el ID que Quieres Modificar -> ");

            if (sc.hasNextInt()) {
                id = sc.nextInt();
                for (VideoJuego videoJuego : videoJuegos) {
                    if (String.valueOf(id).equals(videoJuego.getId())) {
                        esValido = true;
                    }
                }
            } else {
                System.out.println("Ingresa un número válido. Gracias!");
                sc.next();
            }
        } while (!esValido);

        VideoJuego nuevoJuego = null;

        for (VideoJuego videoJuego : videoJuegos) {
            if (String.valueOf(id).equals(videoJuego.getId())) {
                float precioFloat = videoJuego.getPrecio();
                nuevoJuego = new VideoJuego(videoJuego.getId(), videoJuego.getNombre(),
                        videoJuego.getGenero(), videoJuego.getFechaLanzamiento(),
                        videoJuego.getCompañia(), precioFloat);
            }
        }

        int opcion;
        boolean salidaBucle = false;
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

        modificarRegistro(nuevoJuego, pds);

    }

    private static void modificarRegistro(VideoJuego videojuegoModificado, PoolDataSource pds) {
        DatosConexion datosConexion = new DatosConexion();
        int filasAfectadas = 0;
        final String QUERYUPDATE = "UPDATE videojuegos SET nombre = ?, genero = ?, fechaLanzamiento = ?, compañia = ?, precio = ? WHERE id = ?";

        try (Connection conn = pds.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(QUERYUPDATE);
            pstmt.setString(1, videojuegoModificado.getNombre());
            pstmt.setString(2, videojuegoModificado.getGenero());
            pstmt.setDate(3, videojuegoModificado.getFechaLanzamiento());
            pstmt.setString(4, videojuegoModificado.getCompañia());
            pstmt.setFloat(5, videojuegoModificado.getPrecio());
            pstmt.setString(6, videojuegoModificado.getId());

            filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Operación Exitosa!");
            } else {
                System.out.println("La Operación Ha Fracasado!");
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
