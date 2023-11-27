package conexionDB;

import java.sql.Date;
import java.util.Scanner;

public class VideoJuego {

    private String id;
    private String nombre;
    private String genero;
    private Date fechaLanzamiento;
    private String compañia;
    private float precio;

    public VideoJuego(String id, String nombre, String genero, Date fechaLanzamiento, String compañia, float precio) {
        this.id = id;
        this.nombre = nombre;
        this.genero = genero;
        this.fechaLanzamiento = fechaLanzamiento;
        this.compañia = compañia;
        this.precio = precio;
    }

    public VideoJuego() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public String getCompañia() {
        return compañia;
    }

    public void setCompañia(String compañia) {
        this.compañia = compañia;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "\nID: " + this.id
                + "\nNombre: " + this.nombre
                + "\nGenero: " + this.genero
                + "\nFecha Lanzamiento: " + this.fechaLanzamiento
                + "\nCompañia: " + this.compañia
                + "\nPrecio: " + this.precio + "\n";
    }

    public VideoJuego CrearVideoJuego() {
        Scanner sc = new Scanner(System.in);
        String id = null;
        String fecha;
        System.out.print("Dime el Nombre del Videojuego -> ");
        String nombre = sc.nextLine();
        System.out.print("Dime el Genero del Videojuego -> ");
        String genero = sc.nextLine();

        do {
            System.out.print("Dime la Fecha de Lanzamiento del Videojuego (Formato yyyy-mm-dd) -> ");
            fecha = sc.nextLine();
        } while (!validarFecha(fecha));
        Date fechaLanzamiento = Date.valueOf(fecha);

        System.out.print("Dime la Compañia del Videojuego -> ");
        String compañia = sc.nextLine();

        boolean esValido = false;
        float precio = 0.0f;

        do {
            System.out.print("Dime el precio del Videojuego -> ");

            if (sc.hasNextFloat()) {
                precio = sc.nextFloat();
                esValido = true;
            } else {
                System.out.println("Ingresa un número válido. Gracias!");
                sc.next();
            }
        } while (!esValido);

        VideoJuego videoJuego = new VideoJuego(null, nombre, genero, fechaLanzamiento, compañia, precio);
        return videoJuego;
    }

    private static boolean validarFecha(String fecha) {
        boolean salida = true;
        try {
            Date fechaLanzamiento = Date.valueOf(fecha);
        } catch (IllegalArgumentException e) {
            salida = false;
        }
        return salida;
    }

}//FIN CLASE
