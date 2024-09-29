package view;

import javax.swing.*;
import java.awt.*;

public class CamioneroView extends JFrame {

	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtDni;
	private JButton btnGuardarCamionero;

	public CamioneroView() {
		setTitle("Gestión de Camioneros");
		setSize(400, 300);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(4, 2)); // Layout con 4 filas y 2 columnas

		// Crear campos de texto y etiquetas
		add(new JLabel("Nombre:"));
		txtNombre = new JTextField();
		add(txtNombre);

		add(new JLabel("Apellidos:"));
		txtApellidos = new JTextField();
		add(txtApellidos);

		add(new JLabel("DNI:"));
		txtDni = new JTextField();
		add(txtDni);

		// Botón para guardar el camionero
		btnGuardarCamionero = new JButton("Guardar Camionero");
		add(btnGuardarCamionero);
	}

	// Getters para acceder a los valores de los campos desde el controlador
	public String getNombre() {
		return txtNombre.getText(); // Retorna el texto del campo nombre
	}

	public String getApellidos() {
		return txtApellidos.getText(); // Retorna el texto del campo apellidos
	}

	public String getDni() {
		return txtDni.getText(); // Retorna el texto del campo DNI
	}

	public JButton getBtnGuardarCamionero() {
		return btnGuardarCamionero;
	}

	// Métodos para limpiar los campos después de guardar
	public void limpiarCampos() {
		txtNombre.setText("");
		txtApellidos.setText("");
		txtDni.setText("");
	}
}
