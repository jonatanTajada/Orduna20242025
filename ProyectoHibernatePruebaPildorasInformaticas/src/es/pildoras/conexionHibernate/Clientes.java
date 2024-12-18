package es.pildoras.conexionHibernate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "clientes") // Mapea a la tabla 'clientes' en la base de datos
public class Clientes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremento
	private int id;

	@Column(name = "nombre", nullable = false, length = 100) // Mapea la columna 'nombre'
	private String nombre;

	@Column(name = "apellidos", nullable = false, length = 100) // Mapea la columna 'apellidos'
	private String apellidos;

	@Column(name = "direccion", nullable = false, length = 100) // Mapea la columna 'direccion'
	private String direccion;

	// Constructor vacío (obligatorio para Hibernate)
	public Clientes() {
	}

	// Constructor con parámetros
	public Clientes(String nombre, String apellidos, String direccion) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
	}

	// Getters y Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
}
