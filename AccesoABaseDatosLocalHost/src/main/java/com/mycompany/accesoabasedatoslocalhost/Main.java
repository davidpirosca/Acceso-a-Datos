package com.mycompany.accesoabasedatoslocalhost;

import java.sql.*;

public class Main {

    static final String DB_URL = "jdbc:mysql://localhost:3306/jcvd";
    static final String USER = "user";
    static final String PASS = "1234";
    static final String QUERY = "SELECT * FROM videojuegos";
    static final String QUERYINSERT = "INSERT INTO videojuegos VALUES (NULL, 'SinChan', "
            + "'Flipa en Colores', '2023-12-29', 'Miguel Pardo', 100)";
    static final String DELETE = "DELETE FROM videojuegos WHERE nombre = 'SinChan'";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {//crea la conexion con los datos
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY);//ejercutar la consulta

            while (rs.next()) {//recorre la informacion linea a linea
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nombre: " + rs.getString("Nombre"));
                System.out.println("Genero: " + rs.getString("Genero"));
                System.out.println("Fecha Lanzamiento: " + rs.getDate("FechaLanzamiento"));
                System.out.println("Compañia: " + rs.getString("Compañia"));
                System.out.println("Precio: " + rs.getFloat("Precio"));
            }

            //ejecutamos las query ya sea para un insert o delete que es diistinto del de mostrar
            // stmt.executeUpdate(QUERY_INSERT);
            stmt.executeUpdate(DELETE);
            conn.close();//cerramos la conexion
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
