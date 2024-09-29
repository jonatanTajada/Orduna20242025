package controller;

import model.CamionModel;
import model.CamioneroModel;
import view.CamionView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CamionController {

	private CamionView camionView;
	private CamioneroModel camioneroModel; // Relación con camionero

	public CamionController(CamionView camionView, CamioneroModel camioneroModel) {
		this.camionView = camionView;
		this.camioneroModel = camioneroModel;

		// Añadir listener al botón de guardar
		camionView.getBtnGuardarCamion().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				guardarCamion();
			}
		});
	}

	private void guardarCamion() {
		// Obtener los datos de la vista
		String matricula = camionView.getMatricula();
		String marca = camionView.getMarca();
		int capacidad = Integer.parseInt(camionView.getCapacidad());

		// Validar los datos y crear un nuevo camión
		if (matricula.isEmpty() || marca.isEmpty() || capacidad <= 0) {
			JOptionPane.showMessageDialog(camionView, "Por favor, complete todos los campos correctamente.");
		} else {
			CamionModel camion = new CamionModel(0, matricula, marca, capacidad, camioneroModel); // ID generado
																									// internamente
			camioneroModel.agregarCamion(camion); // Relacionar el camión con el camionero
			JOptionPane.showMessageDialog(camionView, "Camión guardado con éxito.");
		}
	}
}
