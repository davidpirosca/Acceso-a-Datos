package miEmpresas;
// Generated 20 dic 2023, 22:06:08 by Hibernate Tools 6.3.1.Final

/**
 * Clientes generated by hbm2java
 */
public class Clientes implements java.io.Serializable {

	private Integer id;
	private String nombre;
	private String pais;

	public Clientes() {
	}

	public Clientes(String nombre, String pais) {
		this.nombre = nombre;
		this.pais = pais;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

}
