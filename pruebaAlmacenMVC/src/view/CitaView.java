package view;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class CitaView extends JFrame {

	private JTextField txtFecha;
	private JTextField txtHora;
	private JTextField txtIdCamionero;
	private JTextField txtIdCamion;
	private JButton btnAgendarCita;

	public CitaView() {
		setTitle("Agendar Cita");
		setSize(400, 300);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(5, 2));

		// Campos para los datos de la cita
		add(new JLabel("Fecha (AAAA-MM-DD):"));
		txtFecha = new JTextField();
		add(txtFecha);

		add(new JLabel("Hora (HH:MM):"));
		txtHora = new JTextField();
		add(txtHora);

		add(new JLabel("ID Camionero:"));
		txtIdCamionero = new JTextField();
		add(txtIdCamionero);

		add(new JLabel("ID Camión:"));
		txtIdCamion = new JTextField();
		add(txtIdCamion);

		// Botón para agendar la cita
		btnAgendarCita = new JButton("Agendar Cita");
		add(btnAgendarCita);
	}

	// Getters para acceder a los campos de texto
	public JTextField getTxtFecha() {
		return txtFecha;
	}

	public JTextField getTxtHora() {
		return txtHora;
	}

	public JTextField getTxtIdCamionero() {
		return txtIdCamionero;
	}

	public JTextField getTxtIdCamion() {
		return txtIdCamion;
	}

	public JButton getBtnAgendarCita() {
		return btnAgendarCita;
	}

	// Métodos para obtener la fecha y hora correctamente
	public LocalDate getFecha() {
		return LocalDate.parse(txtFecha.getText());
	}

	public LocalTime getHora() {
		return LocalTime.parse(txtHora.getText());
	}

	public int getIdCamionero() {
		return Integer.parseInt(txtIdCamionero.getText());
	}

	public int getIdCamion() {
		return Integer.parseInt(txtIdCamion.getText());
	}
}
