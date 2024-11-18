package com.miempresa.tienda.sistema_gestion_tienda.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import com.miempresa.tienda.sistema_gestion_tienda.dao.CategoriaDAO;
import com.miempresa.tienda.sistema_gestion_tienda.estilos.EstiloUI;
import com.miempresa.tienda.sistema_gestion_tienda.modelo.Categoria;

public class GestionCategoriasVista extends VentanaBase {

	private JTable tablaCategorias;
	private DefaultTableModel modeloTabla;

	/**
	 * Constructor de la ventana de Gestión de Categorías.
	 */
	public GestionCategoriasVista() {
		super("Gestión de Categorías");

		// -----------------------
		// Panel superior (título + búsqueda)
		// -----------------------
		JPanel panelSuperior = new JPanel();
		panelSuperior.setLayout(new BorderLayout());
		panelSuperior.setBackground(EstiloUI.COLOR_PRIMARIO);
		panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Título
		JLabel lblTitulo = new JLabel("Gestión de Categorías");
		lblTitulo.setFont(EstiloUI.FUENTE_TITULO);
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		panelSuperior.add(lblTitulo, BorderLayout.NORTH);

		// Búsqueda
		JPanel panelBusqueda = new JPanel();
		panelBusqueda.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelBusqueda.setBackground(EstiloUI.COLOR_PRIMARIO);

		JLabel lblBuscar = new JLabel("Buscar: ");
		lblBuscar.setForeground(Color.WHITE);

		JTextField txtBuscar = new JTextField(20);
		panelBusqueda.add(lblBuscar);
		panelBusqueda.add(txtBuscar);

		panelSuperior.add(panelBusqueda, BorderLayout.SOUTH);
		add(panelSuperior, BorderLayout.NORTH);

		// -----------------------
		// Panel central (tabla)
		// -----------------------
		String[] columnas = { "ID", "Nombre" };
		modeloTabla = new DefaultTableModel(columnas, 0);
		tablaCategorias = new JTable(modeloTabla);
		
		// Aplicar el estilo personalizado a la tabla
		EstiloUI.configurarTabla(tablaCategorias);
		
		JScrollPane scrollTabla = new JScrollPane(tablaCategorias);
		add(scrollTabla, BorderLayout.CENTER);

		// Llenar la tabla con datos iniciales
		llenarTabla();

		// -----------------------
		// Eventos del campo de búsqueda
		// -----------------------
		txtBuscar.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				filtrarTabla(txtBuscar.getText());
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				filtrarTabla(txtBuscar.getText());
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				filtrarTabla(txtBuscar.getText());
			}

			private void filtrarTabla(String texto) {
			    modeloTabla.setRowCount(0); // Limpiar la tabla
			    CategoriaDAO categoriaDAO = new CategoriaDAO();
			    List<Categoria> categorias = categoriaDAO.obtenerTodas();

			    for (Categoria categoria : categorias) {
			        if (categoria.getNombre().toLowerCase().contains(texto.toLowerCase())) {
			            modeloTabla.addRow(new Object[] { categoria.getId(), categoria.getNombre() });
			        }
			    }
			}

		});

		// -----------------------
		// Panel inferior (botones)
		// -----------------------
		JPanel panelInferior = new JPanel();
		panelInferior.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

		JButton btnAgregar = new JButton("Agregar");
		JButton btnEditar = new JButton("Editar");
		JButton btnEliminar = new JButton("Eliminar");

		panelInferior.add(btnAgregar);
		panelInferior.add(btnEditar);
		panelInferior.add(btnEliminar);

		add(panelInferior, BorderLayout.SOUTH);

		// Eventos de los botones
		btnAgregar.addActionListener(e -> agregarCategoria());
		btnEditar.addActionListener(e -> editarCategoria());
		btnEliminar.addActionListener(e -> eliminarCategoria());

		setVisible(true);
	}

	private void llenarTabla() {
		modeloTabla.setRowCount(0); // Limpiar tabla
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		List<Categoria> categorias = categoriaDAO.obtenerTodas();

		for (Categoria categoria : categorias) {
			modeloTabla.addRow(new Object[] { categoria.getId(), categoria.getNombre() });
		}
	}

	/**
	 * Lógica para agregar una nueva categoría.
	 */
	private void agregarCategoria() {
		String nombreNuevaCategoria = JOptionPane.showInputDialog(this, "Ingrese el nombre de la nueva categoría:",
				"Agregar Categoría", JOptionPane.PLAIN_MESSAGE);

		if (nombreNuevaCategoria != null && !nombreNuevaCategoria.trim().isEmpty()) {
			CategoriaDAO categoriaDAO = new CategoriaDAO();
			Categoria nuevaCategoria = new Categoria();
			nuevaCategoria.setNombre(nombreNuevaCategoria.trim());

			if (categoriaDAO.insertar(nuevaCategoria)) {
				JOptionPane.showMessageDialog(this, "Categoría agregada correctamente.", "Éxito",
						JOptionPane.INFORMATION_MESSAGE);
				llenarTabla(); // Recargar la tabla
			} else {
				JOptionPane.showMessageDialog(this, "Error al agregar la categoría.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(this, "El nombre de la categoría no puede estar vacío.", "Advertencia",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * Lógica para editar una categoría existente.
	 */
	private void editarCategoria() {
		int filaSeleccionada = tablaCategorias.getSelectedRow();

		if (filaSeleccionada == -1) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar una categoría para editar.", "Advertencia",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		int idCategoria = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
		String nombreActual = (String) modeloTabla.getValueAt(filaSeleccionada, 1);

		String nuevoNombre = JOptionPane.showInputDialog(this, "Ingrese el nuevo nombre para la categoría:",
				nombreActual);

		if (nuevoNombre != null && !nuevoNombre.trim().isEmpty() && !nuevoNombre.equals(nombreActual)) {
			CategoriaDAO categoriaDAO = new CategoriaDAO();
			Categoria categoria = new Categoria(idCategoria, nuevoNombre.trim());

			if (categoriaDAO.actualizar(categoria)) {
				JOptionPane.showMessageDialog(this, "Categoría actualizada correctamente.", "Éxito",
						JOptionPane.INFORMATION_MESSAGE);
				modeloTabla.setValueAt(nuevoNombre.trim(), filaSeleccionada, 1); // Actualizar en la tabla
			} else {
				JOptionPane.showMessageDialog(this, "Error al actualizar la categoría.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (nuevoNombre != null) {
			JOptionPane.showMessageDialog(this, "El nuevo nombre no puede estar vacío ni ser igual al actual.",
					"Advertencia", JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * Lógica para eliminar una categoría.
	 */
	private void eliminarCategoria() {
		int filaSeleccionada = tablaCategorias.getSelectedRow();

		if (filaSeleccionada == -1) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar una categoría para eliminar.", "Advertencia",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		int idCategoria = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
		String nombreCategoria = (String) modeloTabla.getValueAt(filaSeleccionada, 1);

		int confirmacion = JOptionPane.showConfirmDialog(this,
				"¿Está seguro de que desea eliminar la categoría '" + nombreCategoria + "'?", "Confirmar eliminación",
				JOptionPane.YES_NO_OPTION);

		if (confirmacion == JOptionPane.YES_OPTION) {
			CategoriaDAO categoriaDAO = new CategoriaDAO();

			if (categoriaDAO.eliminar(idCategoria)) {
				JOptionPane.showMessageDialog(this, "Categoría eliminada correctamente.", "Éxito",
						JOptionPane.INFORMATION_MESSAGE);
				modeloTabla.removeRow(filaSeleccionada); // Eliminar la fila de la tabla
			} else {
				JOptionPane.showMessageDialog(this, "Error al eliminar la categoría.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
