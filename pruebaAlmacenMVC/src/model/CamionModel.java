package model;

import java.util.Objects;

public class CamionModel {

	private int id;
	private String matricula;
	private String marca;
	private int capacidad;
	private CamioneroModel camionero; // Relación con Camionero

	// Constructor
	public CamionModel(int id, String matricula, String marca, int capacidad, CamioneroModel camionero) {
		this.id = id;
		this.matricula = matricula;
		this.marca = marca;
		this.capacidad = capacidad;
		this.camionero = camionero;
	}

	// Getters y setters
	public int getId() {
		return id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public CamioneroModel getCamionero() {
		return camionero;
	}

	public void setCamionero(CamioneroModel camionero) {
		this.camionero = camionero;
	}

	// Métodos toString, equals, hashCode si son necesarios
	@Override
	public int hashCode() {
		return Objects.hash(matricula);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CamionModel other = (CamionModel) obj;
		return Objects.equals(matricula, other.matricula);
	}

	@Override
	public String toString() {
		return "CamionModel [id=" + id + ", matricula=" + matricula + ", marca=" + marca + ", capacidad=" + capacidad
				+ ", camionero=" + camionero + "]";
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}



