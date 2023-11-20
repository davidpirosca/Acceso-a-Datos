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
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY);
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nombre: " + rs.getString("nombre"));
                System.out.println("Genero: " + rs.getString("genero"));
                System.out.println("Fecha Lanzamiento: " + rs.getDate("fecha_lanzamiento"));
                System.out.println("Compañia: " + rs.getString("compañia"));
                System.out.println("Precio: " + rs.getFloat("precio"));
            }
            //CREAR UN VIDEOJUEGO SINCHAN
            //stmt.executeUpdate(QUERYINSERT);
            //BORRANDO EL VIDEOJUEGO SINCHAN
            stmt.executeUpdate(DELETE);
            
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
