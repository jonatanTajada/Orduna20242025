package PruebasExamenesPrimerTrimestre.MIAS;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class GestionEstudiantes {

	public static void main(String[] args) {

		// Configurar ventana principal
		JFrame ventana = new JFrame();
		ventana.setTitle("Gestión de Estudiantes");
		ventana.setSize(800, 600);
		ventana.setLocationRelativeTo(null);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setLayout(new BorderLayout());

		// Panel superior: Formulario de ingreso de estudiantes
		JPanel panelRegistro = new JPanel(new BorderLayout());
		panelRegistro.setBorder(BorderFactory.createTitledBorder("Registro de Estudiantes"));
		ventana.add(panelRegistro, BorderLayout.NORTH);

		// Subpanel para los campos de entrada
		JPanel panelCampos = new JPanel(new GridLayout(4, 2, 5, 5));
		JTextField campoNombre = new JTextField();
		JTextField campoEdad = new JTextField();
		JTextField campoCarrera = new JTextField();
		JTextField campoNota = new JTextField();

		panelCampos.add(new JLabel("Nombre"));
		panelCampos.add(campoNombre);
		panelCampos.add(new JLabel("Edad"));
		panelCampos.add(campoEdad);
		panelCampos.add(new JLabel("Carrera"));
		panelCampos.add(campoCarrera);
		panelCampos.add(new JLabel("Nota promedio"));
		panelCampos.add(campoNota);

		// Añadir panel de campos al panel de registro
		panelRegistro.add(panelCampos, BorderLayout.CENTER);

		// Subpanel para botones de acción
		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		JButton btnAgregar = new JButton("Agregar Estudiante");
		JButton btnEditar = new JButton("Editar Estudiante");
		JButton btnEliminar = new JButton("Eliminar Estudiante");
		panelBotones.add(btnAgregar);
		panelBotones.add(btnEliminar);
		panelBotones.add(btnEditar);

		panelRegistro.add(panelBotones, BorderLayout.SOUTH);

		// Tabla de estudiantes
		String[] columnas = { "Nombre", "Edad", "Carrera", "Nota promedio" };
		DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0);
		JTable tablaEstudiantes = new JTable(modeloTabla);
		JScrollPane scrollPane = new JScrollPane(tablaEstudiantes);
		ventana.add(scrollPane, BorderLayout.CENTER);

		// RowSorter para la tabla
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modeloTabla);
		tablaEstudiantes.setRowSorter(sorter);

		// Panel de filtro
		JPanel panelFiltro = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelFiltro.setBorder(BorderFactory.createTitledBorder("Filtrar por Nombre"));
		JTextField campoFiltro = new JTextField(20);
		panelFiltro.add(new JLabel("Buscar:"));
		panelFiltro.add(campoFiltro);
		ventana.add(panelFiltro, BorderLayout.SOUTH);

		// Filtrar estudiantes en tiempo real
		campoFiltro.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(javax.swing.event.DocumentEvent e) {
				filtrar();
			}

			public void removeUpdate(javax.swing.event.DocumentEvent e) {
				filtrar();
			}

			public void changedUpdate(javax.swing.event.DocumentEvent e) {
				filtrar();
			}

			private void filtrar() {
				String filtro = campoFiltro.getText();
				if (filtro.isEmpty()) {
					sorter.setRowFilter(null);
				} else {
					sorter.setRowFilter(RowFilter.regexFilter("(?i)" + filtro, 0));
				}
			}
		});

		// Acción para agregar estudiante
		btnAgregar.addActionListener((ActionEvent e) -> {
			String nombre = campoNombre.getText().trim();
			String edadStr = campoEdad.getText().trim();
			String carrera = campoCarrera.getText().trim();
			String notaStr = campoNota.getText().trim();

			if (!nombre.isEmpty() && !edadStr.isEmpty() && !carrera.isEmpty() && !notaStr.isEmpty()) {
				try {
					int edad = Integer.parseInt(edadStr);
					double nota = Double.parseDouble(notaStr);

					modeloTabla.addRow(new Object[] { nombre, edad, carrera, nota });

					campoNombre.setText("");
					campoEdad.setText("");
					campoCarrera.setText("");
					campoNota.setText("");
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(ventana, "Edad y Nota deben ser valores numéricos.",
							"Error de formato", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(ventana, "Por favor, complete todos los campos.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		});

		// Acción para eliminar estudiante
		btnEliminar.addActionListener((ActionEvent e) -> {
			int filaSeleccionada = tablaEstudiantes.getSelectedRow();
			if (filaSeleccionada != -1) {
				int confirmar = JOptionPane.showConfirmDialog(ventana,
						"¿Está seguro de que quiere eliminar este estudiante?", "Confirmación",
						JOptionPane.YES_NO_OPTION);
				if (confirmar == JOptionPane.YES_OPTION) {
					modeloTabla.removeRow(tablaEstudiantes.convertRowIndexToModel(filaSeleccionada));
				}
			} else {
				JOptionPane.showMessageDialog(ventana, "Seleccione un estudiante para eliminar.", "No hay selección",
						JOptionPane.WARNING_MESSAGE);
			}
		});

		// Acción para editar estudiante
		btnEditar.addActionListener((ActionEvent e) -> {
			int filaSeleccionada = tablaEstudiantes.getSelectedRow();
			if (filaSeleccionada != -1) {
				campoNombre.setText(modeloTabla.getValueAt(filaSeleccionada, 0).toString());
				campoEdad.setText(modeloTabla.getValueAt(filaSeleccionada, 1).toString());
				campoCarrera.setText(modeloTabla.getValueAt(filaSeleccionada, 2).toString());
				campoNota.setText(modeloTabla.getValueAt(filaSeleccionada, 3).toString());

				btnAgregar.setText("Guardar cambios");

				// Remover ActionListener existente para evitar duplicados
				for (var al : btnAgregar.getActionListeners()) {
					btnAgregar.removeActionListener(al);
				}

				btnAgregar.addActionListener((ActionEvent e2) -> {
					try {
						int edad = Integer.parseInt(campoEdad.getText().trim());
						double nota = Double.parseDouble(campoNota.getText().trim());

						modeloTabla.setValueAt(campoNombre.getText().trim(), filaSeleccionada, 0);
						modeloTabla.setValueAt(edad, filaSeleccionada, 1);
						modeloTabla.setValueAt(campoCarrera.getText().trim(), filaSeleccionada, 2);
						modeloTabla.setValueAt(nota, filaSeleccionada, 3);

						campoNombre.setText("");
						campoEdad.setText("");
						campoCarrera.setText("");
						campoNota.setText("");

						btnAgregar.setText("Agregar Estudiante");
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(ventana, "Edad y Nota deben ser valores numéricos.",
								"Error de formato", JOptionPane.ERROR_MESSAGE);
					}
				});
			} else {
				JOptionPane.showMessageDialog(ventana, "Seleccione un estudiante para editar.", "No hay selección",
						JOptionPane.WARNING_MESSAGE);
			}
		});

		ventana.setVisible(true);
	}
}
