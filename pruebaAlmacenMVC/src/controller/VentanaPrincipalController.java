package controller;

import view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipalController {

	private VentanaPrincipalView ventanaPrincipalView;
	private CamioneroView camioneroView;
	private CamionView camionView;
	private CitaView citaView;

	public VentanaPrincipalController(VentanaPrincipalView ventanaPrincipalView, CamioneroView camioneroView,
			CamionView camionView, CitaView citaView) {
		this.ventanaPrincipalView = ventanaPrincipalView;
		this.camioneroView = camioneroView;
		this.camionView = camionView;
		this.citaView = citaView;

		// Añadir listeners para los botones
		ventanaPrincipalView.getBtnGestionarCamioneros().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarGestionCamioneros();
			}
		});

		ventanaPrincipalView.getBtnGestionarCamiones().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarGestionCamiones();
			}
		});

		ventanaPrincipalView.getBtnGestionarCitas().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarGestionCitas();
			}
		});
	}

	// Métodos para mostrar las vistas secundarias
	private void mostrarGestionCamioneros() {
		camioneroView.setVisible(true);
	}

	private void mostrarGestionCamiones() {
		camionView.setVisible(true);
	}

	private void mostrarGestionCitas() {
		citaView.setVisible(true);
	}
}
