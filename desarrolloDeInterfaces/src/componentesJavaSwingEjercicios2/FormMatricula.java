package componentesJavaSwingEjercicios2;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FormMatricula implements ActionListener {

	private final int ANCHO = 550;
	private final int ALTO = 450;
	private JFrame ventana;
	private JLabel lMatricula;
	private JTextField tMatricula;
	private JButton matricular;
	private JPanel panelNorte;
	
	public static void main(String[] args) {
		new FormMatricula(); 
	}
	
	public FormMatricula() {
		
		// Crear la ventana con título y tamaño
		ventana = new JFrame("Matriculación barco");
		ventana.setSize(ANCHO, ALTO);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setLocationRelativeTo(null);
		ventana.setLayout(new BorderLayout()); 

		// Crear el panel
		panelNorte = new JPanel();
		panelNorte.setLayout(new FlowLayout());

		// Crear y añadir la etiqueta de matrícula al panel
		lMatricula = new JLabel("Matrícula:");
		panelNorte.add(lMatricula);

		// Crear el campo de texto con la matrícula de ejemplo y añadirlo al panel
		tMatricula = new JTextField("7º-PM-1-01-11", 15);
		panelNorte.add(tMatricula);

		// Crear el botón "Matricular" y añadirlo al panel
		matricular = new JButton("Matricular");
		panelNorte.add(matricular);

		// Añadir el panel al JFrame 
		ventana.add(panelNorte, BorderLayout.NORTH);

		// Añadir listener al botón
		añadirListenerBotones();

		// Hacer visible la ventana
		ventana.setVisible(true);
	}

	public void añadirListenerBotones() {

		matricular.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		Barco nuevoBarco = new Barco(null, "Barco", 13.5F, 4);

		// Asignar al atributo matrícula del barco el valor del campo de texto
		nuevoBarco.setMatricula(tMatricula.getText());

		// Mostrar los datos del nuevo barco en la consola
		System.out.println("Barco matriculado con éxito:");
		System.out.println(nuevoBarco.toString());
	}
}
