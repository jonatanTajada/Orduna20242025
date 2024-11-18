package com.miempresa.tienda.sistema_gestion_tienda.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import com.miempresa.tienda.sistema_gestion_tienda.dao.ProductoDAO;
import com.miempresa.tienda.sistema_gestion_tienda.estilos.EstiloUI;
import com.miempresa.tienda.sistema_gestion_tienda.modelo.Categoria;
import com.miempresa.tienda.sistema_gestion_tienda.modelo.Producto;

public class GestionProductosVista extends VentanaBase {

	private JTable tablaProductos;
	private DefaultTableModel modeloTabla;

	public GestionProductosVista() {

		super("Gestión de Productos");

		// -----------------------
		// Panel superior (Título + Búsqueda)
		// -----------------------
		JPanel panelSuperior = new JPanel(new BorderLayout());
		panelSuperior.setBackground(EstiloUI.COLOR_PRIMARIO);
		panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JLabel lblTitulo = new JLabel("Gestión de Productos", SwingConstants.CENTER);
		lblTitulo.setFont(EstiloUI.FUENTE_TITULO);
		lblTitulo.setForeground(Color.WHITE);
		panelSuperior.add(lblTitulo, BorderLayout.NORTH);

		JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelBusqueda.setBackground(EstiloUI.COLOR_PRIMARIO);
		JLabel lblBuscar = new JLabel("Buscar: ");
		lblBuscar.setForeground(Color.WHITE);
		JTextField txtBuscar = new JTextField(20);
		panelBusqueda.add(lblBuscar);
		panelBusqueda.add(txtBuscar);
		panelSuperior.add(panelBusqueda, BorderLayout.SOUTH);

		add(panelSuperior, BorderLayout.NORTH);

		// -----------------------
		// Panel central (Tabla)
		// -----------------------

		String[] columnas = { "ID", "Nombre", "Descripción", "Precio", "Stock", "Categoría" };
		modeloTabla = new DefaultTableModel(columnas, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Evita que las celdas sean editables directamente
			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				// Define el tipo de dato para cada columna
				switch (columnIndex) {
				case 0:
					return Integer.class; // ID
				case 3:
					return Double.class; // Precio
				case 4:
					return Integer.class; // Stock
				default:
					return String.class; // Nombre, Descripción, Categoría
				}
			}
		};

		tablaProductos = new JTable(modeloTabla);
		tablaProductos.setAutoCreateRowSorter(true); // Habilita el ordenamiento
		EstiloUI.configurarTabla(tablaProductos);

		JScrollPane scrollTabla = new JScrollPane(tablaProductos);
		add(scrollTabla, BorderLayout.CENTER);

		// Llenar la tabla con datos iniciales
		llenarTabla();

		// -----------------------
		// Eventos del buscador
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
		});

		// -----------------------
		// Panel inferior (Botones)
		// -----------------------
		JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		JButton btnAgregar = new JButton("Agregar");
		JButton btnEditar = new JButton("Editar");
		JButton btnEliminar = new JButton("Eliminar");

		panelInferior.add(btnAgregar);
		panelInferior.add(btnEditar);
		panelInferior.add(btnEliminar);

		add(panelInferior, BorderLayout.SOUTH);

		// -----------------------
		// Eventos de los botones
		// -----------------------
		btnAgregar.addActionListener(e -> {
			// Crear un panel para el formulario de agregar producto
			JPanel panel = new JPanel(new FlowLayout());
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

			JTextField txtNombre = new JTextField(20);
			JTextField txtDescripcion = new JTextField(20);
			JTextField txtPrecio = new JTextField(20);
			JTextField txtStock = new JTextField(20);
			JComboBox<Categoria> cmbCategorias = new JComboBox<>();

			// Cargar las categorías en el comboBox
			CategoriaDAO categoriaDAO = new CategoriaDAO();
			List<Categoria> categorias = categoriaDAO.obtenerTodas();
			for (Categoria categoria : categorias) {
				cmbCategorias.addItem(categoria);
			}

			panel.add(new JLabel("Nombre:"));
			panel.add(txtNombre);
			panel.add(new JLabel("Descripción:"));
			panel.add(txtDescripcion);
			panel.add(new JLabel("Precio:"));
			panel.add(txtPrecio);
			panel.add(new JLabel("Stock:"));
			panel.add(txtStock);
			panel.add(new JLabel("Categoría:"));
			panel.add(cmbCategorias);

			int result = JOptionPane.showConfirmDialog(this, panel, "Agregar Producto", JOptionPane.OK_CANCEL_OPTION);

			if (result == JOptionPane.OK_OPTION) {
				// Validar y agregar producto
				try {
					String nombre = txtNombre.getText().trim();
					String descripcion = txtDescripcion.getText().trim();
					double precio = Double.parseDouble(txtPrecio.getText().trim());
					int stock = Integer.parseInt(txtStock.getText().trim());
					Categoria categoriaSeleccionada = (Categoria) cmbCategorias.getSelectedItem();

					if (nombre.isEmpty() || descripcion.isEmpty()) {
						JOptionPane.showMessageDialog(this, "Los campos Nombre y Descripción son obligatorios.",
								"Error", JOptionPane.ERROR_MESSAGE);
						return;
					}

					if (precio < 0) {
						JOptionPane.showMessageDialog(this, "El precio debe ser un valor positivo.", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					if (stock < 0) {
						JOptionPane.showMessageDialog(this, "El stock debe ser un valor positivo.", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					Producto nuevoProducto = new Producto();
					nuevoProducto.setNombre(nombre);
					nuevoProducto.setDescripcion(descripcion);
					nuevoProducto.setPrecio(precio);
					nuevoProducto.setStock(stock);
					nuevoProducto.setCategoria(categoriaSeleccionada);

					ProductoDAO productoDAO = new ProductoDAO();
					if (productoDAO.insertar(nuevoProducto)) {
						JOptionPane.showMessageDialog(this, "Producto agregado correctamente.", "Éxito",
								JOptionPane.INFORMATION_MESSAGE);
						llenarTabla(); // Actualizar tabla
					} else {
						JOptionPane.showMessageDialog(this, "Error al agregar producto.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(this,
							"Verifique que los campos Precio y Stock sean numéricos y válidos.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnEditar.addActionListener(e -> {
			int filaSeleccionada = tablaProductos.getSelectedRow();

			if (filaSeleccionada == -1) {
				JOptionPane.showMessageDialog(this, "Debe seleccionar un producto para editar.", "Advertencia",
						JOptionPane.WARNING_MESSAGE);
				return;
			}

			// Obtener los datos del producto seleccionado
			int idProducto = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
			ProductoDAO productoDAO = new ProductoDAO();
			Producto producto = productoDAO.obtenerPorId(idProducto);

			if (producto == null) {
				JOptionPane.showMessageDialog(this, "Error al cargar los datos del producto.", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Crear el formulario de edición
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

			JTextField txtNombre = new JTextField(producto.getNombre(), 20);
			JTextField txtDescripcion = new JTextField(producto.getDescripcion(), 20);
			JTextField txtPrecio = new JTextField(String.valueOf(producto.getPrecio()), 20);
			JTextField txtStock = new JTextField(String.valueOf(producto.getStock()), 20);
			JComboBox<Categoria> cmbCategorias = new JComboBox<>();

			// Cargar las categorías en el comboBox
			CategoriaDAO categoriaDAO = new CategoriaDAO();
			List<Categoria> categorias = categoriaDAO.obtenerTodas();
			for (Categoria categoria : categorias) {
				cmbCategorias.addItem(categoria);
			}

			// Seleccionar la categoría actual del producto
			cmbCategorias.setSelectedItem(producto.getCategoria());

			panel.add(new JLabel("Nombre:"));
			panel.add(txtNombre);
			panel.add(new JLabel("Descripción:"));
			panel.add(txtDescripcion);
			panel.add(new JLabel("Precio:"));
			panel.add(txtPrecio);
			panel.add(new JLabel("Stock:"));
			panel.add(txtStock);
			panel.add(new JLabel("Categoría:"));
			panel.add(cmbCategorias);

			int result = JOptionPane.showConfirmDialog(this, panel, "Editar Producto", JOptionPane.OK_CANCEL_OPTION);

			if (result == JOptionPane.OK_OPTION) {
				// Validar y actualizar el producto
				try {
					String nombre = txtNombre.getText().trim();
					String descripcion = txtDescripcion.getText().trim();
					double precio = Double.parseDouble(txtPrecio.getText().trim());
					int stock = Integer.parseInt(txtStock.getText().trim());
					Categoria categoriaSeleccionada = (Categoria) cmbCategorias.getSelectedItem();

					if (nombre.isEmpty() || descripcion.isEmpty()) {
						JOptionPane.showMessageDialog(this, "Los campos Nombre y Descripción son obligatorios.",
								"Error", JOptionPane.ERROR_MESSAGE);
						return;
					}

					if (precio < 0) {
						JOptionPane.showMessageDialog(this, "El precio debe ser un valor positivo.", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					if (stock < 0) {
						JOptionPane.showMessageDialog(this, "El stock debe ser un valor positivo.", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					producto.setNombre(nombre);
					producto.setDescripcion(descripcion);
					producto.setPrecio(precio);
					producto.setStock(stock);
					producto.setCategoria(categoriaSeleccionada);

					if (productoDAO.actualizar(producto)) {
						JOptionPane.showMessageDialog(this, "Producto actualizado correctamente.", "Éxito",
								JOptionPane.INFORMATION_MESSAGE);
						llenarTabla(); // Actualizar tabla
					} else {
						JOptionPane.showMessageDialog(this, "Error al actualizar producto.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(this,
							"Verifique que los campos Precio y Stock sean numéricos y válidos.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnEliminar.addActionListener(e -> {
			int filaSeleccionada = tablaProductos.getSelectedRow();

			if (filaSeleccionada == -1) {
				JOptionPane.showMessageDialog(this, "Debe seleccionar un producto para eliminar.", "Advertencia",
						JOptionPane.WARNING_MESSAGE);
				return;
			}

			// Obtener los datos del producto seleccionado
			int idProducto = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
			String nombreProducto = (String) modeloTabla.getValueAt(filaSeleccionada, 1);

			int confirmacion = JOptionPane.showConfirmDialog(this,
					"¿Está seguro de que desea eliminar el producto '" + nombreProducto + "'?", "Confirmar eliminación",
					JOptionPane.YES_NO_OPTION);

			if (confirmacion == JOptionPane.YES_OPTION) {
				ProductoDAO productoDAO = new ProductoDAO();

				if (productoDAO.eliminar(idProducto)) {
					JOptionPane.showMessageDialog(this, "Producto eliminado correctamente.", "Éxito",
							JOptionPane.INFORMATION_MESSAGE);
					llenarTabla(); // Actualizar tabla
				} else {
					JOptionPane.showMessageDialog(this, "Error al eliminar producto.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		setVisible(true);
	}

	private void llenarTabla() {
		modeloTabla.setRowCount(0); // Limpia la tabla
		ProductoDAO productoDAO = new ProductoDAO();
		List<Producto> productos = productoDAO.obtenerTodos();

		if (productos.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No hay productos disponibles para mostrar.", "Advertencia",
					JOptionPane.WARNING_MESSAGE);
		}

		for (Producto producto : productos) {
			modeloTabla.addRow(new Object[] { producto.getId(), producto.getNombre(), producto.getDescripcion(),
					producto.getPrecio(), producto.getStock(), producto.getCategoria().getNombre() });
		}
	}

	private void filtrarTabla(String texto) {
		modeloTabla.setRowCount(0); // Limpiar tabla
		ProductoDAO productoDAO = new ProductoDAO();
		List<Producto> productos = productoDAO.obtenerTodos();

		for (Producto producto : productos) {
			if (producto.getNombre().toLowerCase().contains(texto.toLowerCase())
					|| producto.getCategoria().getNombre().toLowerCase().contains(texto.toLowerCase())) {
				modeloTabla.addRow(new Object[] { producto.getId(), producto.getNombre(), producto.getDescripcion(),
						producto.getPrecio(), producto.getStock(), producto.getCategoria().getNombre() });
			}
		}
	}

}
