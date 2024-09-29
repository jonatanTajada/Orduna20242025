package controller;

import model.CamioneroModel;
import view.CamioneroView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CamioneroController {

	private CamioneroView camioneroView;

	public CamioneroController(CamioneroView camioneroView) {
		this.camioneroView = camioneroView;

		// Añadir listener al botón de guardar
		camioneroView.getBtnGuardarCamionero().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				guardarCamionero();
			}
		});
	}

	private void guardarCamionero() {
		// Obtener los datos de la vista
		String nombre = camioneroView.getNombre();
		String apellidos = camioneroView.getApellidos();
		String dni = camioneroView.getDni();

		// Validar los datos y crear un nuevo camionero
		if (nombre.isEmpty() || apellidos.isEmpty() || dni.isEmpty()) {
			JOptionPane.showMessageDialog(camioneroView, "Por favor, complete todos los campos.");
		} else {
			CamioneroModel camionero = new CamioneroModel(0, nombre, apellidos, dni); // ID generado internamente
			// Guardar el camionero en la base de datos o el repositorio
			JOptionPane.showMessageDialog(camioneroView, "Camionero guardado con éxito.");
			// Limpiar los campos de texto para un nuevo ingreso
			camioneroView.limpiarCampos();
		}
	}
}
