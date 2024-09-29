package view;

import javax.swing.*;
import java.awt.*;

public class CamionView extends JFrame {

	private JTextField txtMatricula;
	private JTextField txtMarca;
	private JTextField txtCapacidad;
	private JButton btnGuardarCamion;

	public CamionView() {
		setTitle("Gestión de Camiones");
		setSize(400, 300);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(4, 2)); // Layout de 4 filas y 2 columnas

		// Campos de texto para los datos del camión
		add(new JLabel("Matrícula:"));
		txtMatricula = new JTextField();
		add(txtMatricula);

		add(new JLabel("Marca:"));
		txtMarca = new JTextField();
		add(txtMarca);

		add(new JLabel("Capacidad (Toneladas):"));
		txtCapacidad = new JTextField();
		add(txtCapacidad);

		// Botón para guardar el camión
		btnGuardarCamion = new JButton("Guardar Camión");
		add(btnGuardarCamion);
	}

	// Getters para acceder a los datos desde el controlador
	public String getMatricula() {
		return txtMatricula.getText();
	}

	public String getMarca() {
		return txtMarca.getText();
	}

	public String getCapacidad() {
		return txtCapacidad.getText();
	}

	public JButton getBtnGuardarCamion() {
		return btnGuardarCamion;
	}
}
