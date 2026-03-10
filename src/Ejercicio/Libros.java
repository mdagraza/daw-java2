package Ejercicio;

public class Libros {
	private int idLibros;
	private String nombre;
	private String autor;
	private String descripcion;
	private String pais;
	private String codigo;
	
	//Constructor
	public Libros(int idLibros, String nombre, String autor, String descripcion, String pais, String codigo) {
		super();
		this.idLibros = idLibros;
		this.nombre = nombre;
		this.autor = autor;
		this.descripcion = descripcion;
		this.pais = pais;
		this.codigo = codigo;
	}

	//Getter y setter
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getIdLibros() {
		return idLibros;
	}

	//metodos sobrescritos
	@Override
	public String toString() {
		return "Libros [idLibros=" + idLibros + ", nombre=" + nombre + ", autor=" + autor + ", descripcion="
				+ descripcion + ", pais=" + pais + ", codigo=" + codigo + "]";
	}
	
	
	
}
