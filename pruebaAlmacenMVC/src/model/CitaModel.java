package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class CitaModel {

	private int id;
	private LocalDate fecha;
	private LocalTime hora;
	private CamioneroModel camionero; // Relación con el camionero
	private CamionModel camion; // Relación con el camión

	// Constructor
	public CitaModel(int id, LocalDate fecha, LocalTime hora, CamioneroModel camionero, CamionModel camion) {
		this.id = id;
		this.fecha = fecha;
		this.hora = hora;
		this.camionero = camionero;
		this.camion = camion;
	}

	// Getters y setters
	public int getId() {
		return id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public CamioneroModel getCamionero() {
		return camionero;
	}

	public void setCamionero(CamioneroModel camionero) {
		this.camionero = camionero;
	}

	public CamionModel getCamion() {
		return camion;
	}

	public void setCamion(CamionModel camion) {
		this.camion = camion;
	}

	// Métodos toString, equals, hashCode si son necesarios
	@Override
	public int hashCode() {
		return Objects.hash(fecha, hora);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CitaModel other = (CitaModel) obj;
		return Objects.equals(fecha, other.fecha) && Objects.equals(hora, other.hora);
	}

	@Override
	public String toString() {
		return "CitaModel [id=" + id + ", fecha=" + fecha + ", hora=" + hora + ", camionero=" + camionero + ", camion="
				+ camion + "]";
	}

	
	
	
	
}



