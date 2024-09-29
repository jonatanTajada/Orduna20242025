package controller;

import model.CamioneroModel;
import model.CamionModel;
import model.CitaModel;
import view.CitaView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;

public class CitaController {

	private CitaView citaView;
	private CamioneroModel camioneroModel;

	public CitaController(CitaView citaView, CamioneroModel camioneroModel) {
		this.citaView = citaView;
		this.camioneroModel = camioneroModel;

		// Añadir listener al botón de agendar
		citaView.getBtnAgendarCita().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				agendarCita();
			}
		});
	}

	private void agendarCita() {
		try {
			// Obtener los datos de la vista
			int idCamion = citaView.getIdCamion();
			int idCamionero = citaView.getIdCamionero();
			LocalDate fecha = citaView.getFecha();
			LocalTime hora = citaView.getHora();

			// Validar si el camionero ya tiene una cita en ese momento
			CitaModel nuevaCita = new CitaModel(0, fecha, hora, camioneroModel,
					new CamionModel(idCamion, "", "", 0, camioneroModel));
			if (camioneroModel.tieneCitaAlMismoMomento(nuevaCita)) {
				JOptionPane.showMessageDialog(citaView, "Ya existe una cita en ese momento para el camionero.");
			} else {
				// Agregar la cita si no hay conflicto de horarios
				camioneroModel.agregarCita(nuevaCita);
				JOptionPane.showMessageDialog(citaView, "Cita agendada con éxito.");
				limpiarCampos(); // Limpiar los campos después de guardar
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(citaView, "Error al agendar la cita. Verifique los datos ingresados.");
		}
	}

	// Método para limpiar los campos de texto después de guardar
	private void limpiarCampos() {
		citaView.getTxtFecha().setText("");
		citaView.getTxtHora().setText("");
		citaView.getTxtIdCamion().setText("");
		citaView.getTxtIdCamionero().setText("");
	}
}
