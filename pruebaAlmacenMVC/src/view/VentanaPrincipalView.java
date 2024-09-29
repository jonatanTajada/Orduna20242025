package view;


import javax.swing.*;
import java.awt.*;

public class VentanaPrincipalView extends JFrame {

	private JButton btnGestionarCamioneros;
	private JButton btnGestionarCamiones;
	private JButton btnGestionarCitas;

	public VentanaPrincipalView() {
		
		setTitle("Gestión de Almacén");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(3, 1)); // Disposición con tres botones en filas separadas

		// Crear botones
		btnGestionarCamioneros = new JButton("Gestionar Camioneros");
		btnGestionarCamiones = new JButton("Gestionar Camiones");
		btnGestionarCitas = new JButton("Gestionar Citas (Agenda)");

		// Añadir los botones al layout
		add(btnGestionarCamioneros);
		add(btnGestionarCamiones);
		add(btnGestionarCitas);
	}

	// Getters para los botones para poder añadir eventos en el controlador
	public JButton getBtnGestionarCamioneros() {
		return btnGestionarCamioneros;
	}

	public JButton getBtnGestionarCamiones() {
		return btnGestionarCamiones;
	}

	public JButton getBtnGestionarCitas() {
		return btnGestionarCitas;
	}
}
