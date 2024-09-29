package model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CamioneroModel {

	private int id;
	private String nombre;
	private String apellidos;
	private String dni;
	private Set<CamionModel> camiones;
	private Set<CitaModel> citas;

	// Constructor
	public CamioneroModel(int id, String nombre, String apellidos, String dni) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.camiones = new HashSet<>();
		this.citas = new HashSet<>();
	}

	// Getters y setters
	public int getId() {
		return id;
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

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Set<CamionModel> getCamiones() {
		return camiones;
	}

	public void agregarCamion(CamionModel camion) {
		this.camiones.add(camion);
	}

	public Set<CitaModel> getCitas() {
		return citas;
	}

	public void agregarCita(CitaModel cita) {
		this.citas.add(cita);
	}

	public boolean tieneCitaAlMismoMomento(CitaModel nuevaCita) {
		for (CitaModel cita : citas) {
			if (cita.getFecha().equals(nuevaCita.getFecha()) && cita.getHora().equals(nuevaCita.getHora())) {
				return true;
			}
		}
		return false;
	}

	// Métodos toString, equals, hashCode si son necesarios
	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CamioneroModel other = (CamioneroModel) obj;
		return Objects.equals(dni, other.dni);
	}

	@Override
	public String toString() {
		return "CamioneroModel [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", dni=" + dni
				+ ", camiones=" + camiones + ", citas=" + citas + "]";
	}

	
	
	
	
}
