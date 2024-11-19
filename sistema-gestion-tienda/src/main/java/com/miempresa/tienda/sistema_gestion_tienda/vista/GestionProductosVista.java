package com.miempresa.tienda.sistema_gestion_tienda.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
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
	private JTextField txtBuscar;
	private JComboBox<Categoria> cmbFiltroCategoria;
	private JComboBox<String> cmbFiltroEstado;
	private JLabel lblNotificacion;

	public GestionProductosVista() {
		super("Gestión de Productos");

		// Panel superior (Filtros y Búsqueda)
		JPanel panelSuperior = new JPanel(new BorderLayout());
		panelSuperior.setBackground(EstiloUI.COLOR_PRIMARIO);
		panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JLabel lblTitulo = new JLabel("Gestión de Productos", SwingConstants.CENTER);
		lblTitulo.setFont(EstiloUI.FUENTE_TITULO);
		lblTitulo.setForeground(Color.WHITE);
		panelSuperior.add(lblTitulo, BorderLayout.NORTH);

		JPanel panelFiltros = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelFiltros.setBackground(EstiloUI.COLOR_PRIMARIO);

		JLabel lblBuscar = new JLabel("Buscar producto: ");
		lblBuscar.setForeground(Color.WHITE);
		txtBuscar = new JTextField(15);

		JLabel lblFiltroCategoria = new JLabel("Categoría: ");
		lblFiltroCategoria.setForeground(Color.WHITE);
		cmbFiltroCategoria = new JComboBox<>();
		cmbFiltroCategoria.addItem(new Categoria(0, "Todas"));

		CategoriaDAO categoriaDAO = new CategoriaDAO();
		List<Categoria> categorias = categoriaDAO.obtenerTodas();
		for (Categoria categoria : categorias) {
			cmbFiltroCategoria.addItem(categoria);
		}

		JLabel lblFiltroEstado = new JLabel("Estado: ");
		lblFiltroEstado.setForeground(Color.WHITE);
		cmbFiltroEstado = new JComboBox<>(new String[] { "Todos", "Activo", "Inactivo" });

		JButton btnLimpiarFiltros = new JButton("Limpiar Filtros");

		panelFiltros.add(lblBuscar);
		panelFiltros.add(txtBuscar);
		panelFiltros.add(lblFiltroCategoria);
		panelFiltros.add(cmbFiltroCategoria);
		panelFiltros.add(lblFiltroEstado);
		panelFiltros.add(cmbFiltroEstado);
		panelFiltros.add(btnLimpiarFiltros);

		panelSuperior.add(panelFiltros, BorderLayout.SOUTH);
		add(panelSuperior, BorderLayout.NORTH);

		// Panel central (Tabla)
		String[] columnas = { "ID", "Nombre", "Descripción", "Precio", "Stock", "Categoría", "Estado" };
		modeloTabla = new DefaultTableModel(columnas, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tablaProductos = new JTable(modeloTabla);
		tablaProductos.setAutoCreateRowSorter(true);
		EstiloUI.configurarTabla(tablaProductos);

		JScrollPane scrollTabla = new JScrollPane(tablaProductos);
		add(scrollTabla, BorderLayout.CENTER);

		llenarTabla();

		// Eventos de Filtros y Búsqueda
		txtBuscar.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				aplicarFiltros();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				aplicarFiltros();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				aplicarFiltros();
			}
		});

		cmbFiltroCategoria.addActionListener(e -> aplicarFiltros());
		cmbFiltroEstado.addActionListener(e -> aplicarFiltros());
		btnLimpiarFiltros.addActionListener(e -> limpiarFiltros());

		// Panel inferior (Botones y Notificación)
		JPanel panelInferior = new JPanel(new BorderLayout());
		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

		JButton btnAgregar = new JButton("Agregar");
		JButton btnEditar = new JButton("Editar");
		JButton btnCambiarEstado = new JButton("Activar/Inactivar");
		JButton btnExportarCSV = new JButton("Exportar a CSV");

		panelBotones.add(btnAgregar);
		panelBotones.add(btnEditar);
		panelBotones.add(btnCambiarEstado);
		panelBotones.add(btnExportarCSV);

		panelInferior.add(panelBotones, BorderLayout.NORTH);

		lblNotificacion = new JLabel(" ");
		lblNotificacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotificacion.setFont(EstiloUI.FUENTE_GENERAL);
		lblNotificacion.setForeground(Color.DARK_GRAY); // Cambiar color del mensaje a más visible
		panelInferior.add(lblNotificacion, BorderLayout.SOUTH);

		add(panelInferior, BorderLayout.SOUTH);

		// Acciones de los botones
		btnAgregar.addActionListener(e -> abrirFormularioAgregar());
		btnEditar.addActionListener(e -> abrirFormularioEditar());
		btnCambiarEstado.addActionListener(e -> cambiarEstadoProducto());
		btnExportarCSV.addActionListener(e -> exportarTablaACSV());

		setVisible(true);
	}

	private void aplicarFiltros() {
		String texto = txtBuscar.getText().toLowerCase();
		Categoria categoriaSeleccionada = (Categoria) cmbFiltroCategoria.getSelectedItem();
		String estadoSeleccionado = (String) cmbFiltroEstado.getSelectedItem();

		modeloTabla.setRowCount(0);
		ProductoDAO productoDAO = new ProductoDAO();
		List<Producto> productos = productoDAO.obtenerTodos();

		for (Producto producto : productos) {
			boolean coincideTexto = producto.getNombre().toLowerCase().contains(texto)
					|| producto.getDescripcion().toLowerCase().contains(texto);

			boolean coincideCategoria = categoriaSeleccionada == null || categoriaSeleccionada.getId() == 0
					|| producto.getCategoria().getId() == categoriaSeleccionada.getId();

			boolean coincideEstado = estadoSeleccionado.equals("Todos")
					|| (estadoSeleccionado.equals("Activo") && producto.isActivo())
					|| (estadoSeleccionado.equals("Inactivo") && !producto.isActivo());

			if (coincideTexto && coincideCategoria && coincideEstado) {
				modeloTabla.addRow(new Object[] { producto.getId(), producto.getNombre(), producto.getDescripcion(),
						producto.getPrecio(), producto.getStock(), producto.getCategoria().getNombre(),
						producto.isActivo() ? "Activo" : "Inactivo" });
			}
		}
	}

	private void llenarTabla() {
		aplicarFiltros();
	}

	private void limpiarFiltros() {
		txtBuscar.setText("");
		cmbFiltroCategoria.setSelectedIndex(0);
		cmbFiltroEstado.setSelectedIndex(0);
		aplicarFiltros();
	}

	private void exportarTablaACSV() {
		
		if (modeloTabla.getRowCount() == 0) {
			mostrarNotificacion("No hay datos para exportar.", Color.RED);
			return;
		}

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Guardar como CSV");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		int userSelection = fileChooser.showSaveDialog(this);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			String filePath = fileChooser.getSelectedFile().getAbsolutePath();

			// Asegurarse de que el archivo tenga extensión ".csv"
			if (!filePath.toLowerCase().endsWith(".csv")) {
				filePath += ".csv";
			}

			try (FileWriter writer = new FileWriter(filePath)) {
				// Escribir encabezados
				for (int i = 0; i < modeloTabla.getColumnCount(); i++) {
					writer.write(modeloTabla.getColumnName(i));
					if (i < modeloTabla.getColumnCount() - 1) {
						writer.write(","); // Separador
					}
				}
				writer.write("\n");

				// Escribir datos
				for (int i = 0; i < modeloTabla.getRowCount(); i++) {
					for (int j = 0; j < modeloTabla.getColumnCount(); j++) {
						writer.write(String.valueOf(modeloTabla.getValueAt(i, j)));
						if (j < modeloTabla.getColumnCount() - 1) {
							writer.write(","); // Separador
						}
					}
					writer.write("\n");
				}

				mostrarNotificacion("Archivo CSV exportado correctamente.", new Color(0, 128, 255)); // Azul legible
			} catch (IOException ex) {
				mostrarNotificacion("Error al guardar el archivo CSV.", Color.RED);
			}
		}
	}

	private void abrirFormularioEditar() {
		int filaSeleccionada = tablaProductos.getSelectedRow();

		if (filaSeleccionada == -1) {
			mostrarNotificacion("Debe seleccionar un producto para editar.", Color.RED);
			return;
		}

		int filaModelo = tablaProductos.convertRowIndexToModel(filaSeleccionada);
		int idProducto = (int) modeloTabla.getValueAt(filaModelo, 0);

		ProductoDAO productoDAO = new ProductoDAO();
		Producto producto = productoDAO.obtenerPorId(idProducto);

		if (producto == null) {
			mostrarNotificacion("Error al cargar los datos del producto.", Color.RED);
			return;
		}

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JTextField txtNombre = new JTextField(producto.getNombre(), 20);
		JTextField txtDescripcion = new JTextField(producto.getDescripcion(), 20);
		JTextField txtPrecio = new JTextField(String.valueOf(producto.getPrecio()), 20);
		JTextField txtStock = new JTextField(String.valueOf(producto.getStock()), 20);
		JComboBox<Categoria> cmbCategorias = new JComboBox<>();
		JCheckBox chkActivo = new JCheckBox("Activo");
		chkActivo.setSelected(producto.isActivo());

		CategoriaDAO categoriaDAO = new CategoriaDAO();
		List<Categoria> categorias = categoriaDAO.obtenerTodas();
		for (Categoria categoria : categorias) {
			cmbCategorias.addItem(categoria);
		}

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
		panel.add(new JLabel("Estado:"));
		panel.add(chkActivo);

		int result = JOptionPane.showConfirmDialog(this, panel, "Editar Producto", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			try {
				String nombre = txtNombre.getText().trim();
				String descripcion = txtDescripcion.getText().trim();
				double precio = Double.parseDouble(txtPrecio.getText().trim());
				int stock = Integer.parseInt(txtStock.getText().trim());
				Categoria categoriaSeleccionada = (Categoria) cmbCategorias.getSelectedItem();
				boolean activo = chkActivo.isSelected();

				if (nombre.isEmpty() || descripcion.isEmpty()) {
					throw new IllegalArgumentException("Los campos Nombre y Descripción son obligatorios.");
				}

				if (productoDAO.existeNombreDuplicado(nombre, producto.getId())) {
					throw new IllegalArgumentException("El nombre del producto ya está en uso.");
				}

				producto.setNombre(nombre);
				producto.setDescripcion(descripcion);
				producto.setPrecio(precio);
				producto.setStock(stock);
				producto.setCategoria(categoriaSeleccionada);
				producto.setActivo(activo);

				if (productoDAO.actualizar(producto)) {
					mostrarNotificacion("Producto actualizado correctamente.", new Color(0, 128, 255)); // Azul legible
					llenarTabla();
				} else {
					mostrarNotificacion("Error al actualizar el producto.", Color.RED);
				}
			} catch (NumberFormatException ex) {
				mostrarNotificacion("Verifique que los campos Precio y Stock sean numéricos.", Color.RED);
			} catch (IllegalArgumentException ex) {
				mostrarNotificacion(ex.getMessage(), Color.RED);
			}
		}
	}

	private void cambiarEstadoProducto() {
		
	    int[] filasSeleccionadas = tablaProductos.getSelectedRows();

	    if (filasSeleccionadas.length == 0) {
	        JOptionPane.showMessageDialog(this, "Debe seleccionar al menos un producto.", "Advertencia", JOptionPane.WARNING_MESSAGE);
	        return;
	    }

	    int confirmacion = JOptionPane.showConfirmDialog(
	            this,
	            "¿Está seguro de cambiar el estado de los productos seleccionados?",
	            "Confirmar acción",
	            JOptionPane.YES_NO_OPTION
	    );

	    if (confirmacion != JOptionPane.YES_OPTION) {
	        return; // Si el usuario cancela, no hacer nada
	    }

	    ProductoDAO productoDAO = new ProductoDAO();
	    boolean exito = true;

	    for (int fila : filasSeleccionadas) {
	        int filaModelo = tablaProductos.convertRowIndexToModel(fila);
	        int idProducto = (int) modeloTabla.getValueAt(filaModelo, 0);
	        String estadoActual = (String) modeloTabla.getValueAt(filaModelo, 6);

	        boolean nuevoEstado = estadoActual.equals("Inactivo");

	        if (!productoDAO.cambiarEstado(idProducto, nuevoEstado)) {
	            exito = false; // Si falla un cambio, marcamos el error
	        }
	    }

	    if (exito) {
	        mostrarNotificacion("Estado de los productos cambiado correctamente.", Color.ORANGE);
	    } else {
	        mostrarNotificacion("Hubo un error al cambiar el estado de algunos productos.", Color.RED);
	    }

	    llenarTabla(); // Actualizar la tabla
	}


	private void mostrarNotificacion(String mensaje, Color color) {
		lblNotificacion.setText(mensaje);
		lblNotificacion.setForeground(color);
		Timer timer = new Timer(3000, e -> lblNotificacion.setText(" "));
		timer.setRepeats(false);
		timer.start();
	}

	private void filtrarTabla(String texto) {
		modeloTabla.setRowCount(0);
		ProductoDAO productoDAO = new ProductoDAO();
		List<Producto> productos = productoDAO.obtenerTodos();

		for (Producto producto : productos) {
			if (producto.getNombre().toLowerCase().contains(texto.toLowerCase())
					|| producto.getCategoria().getNombre().toLowerCase().contains(texto.toLowerCase())) {
				modeloTabla.addRow(new Object[] { producto.getId(), producto.getNombre(), producto.getDescripcion(),
						producto.getPrecio(), producto.getStock(), producto.getCategoria().getNombre(),
						producto.isActivo() ? "Activo" : "Inactivo" });
			}
		}
	}

	private void abrirFormularioAgregar() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JTextField txtNombre = new JTextField(20);
		JTextField txtDescripcion = new JTextField(20);
		JTextField txtPrecio = new JTextField(20);
		JTextField txtStock = new JTextField(20);
		JComboBox<Categoria> cmbCategorias = new JComboBox<>();
		JCheckBox chkActivo = new JCheckBox("Activo");
		chkActivo.setSelected(true);

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
		panel.add(new JLabel("Estado:"));
		panel.add(chkActivo);

		int result = JOptionPane.showConfirmDialog(this, panel, "Agregar Producto", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			try {
				String nombre = txtNombre.getText().trim();
				String descripcion = txtDescripcion.getText().trim();
				double precio = Double.parseDouble(txtPrecio.getText().trim());
				int stock = Integer.parseInt(txtStock.getText().trim());
				Categoria categoriaSeleccionada = (Categoria) cmbCategorias.getSelectedItem();
				boolean activo = chkActivo.isSelected();

				if (nombre.isEmpty() || descripcion.isEmpty()) {
					throw new IllegalArgumentException("Los campos Nombre y Descripción son obligatorios.");
				}

				ProductoDAO productoDAO = new ProductoDAO();
				if (productoDAO.existeNombreDuplicado(nombre, -1)) {
					throw new IllegalArgumentException("El nombre del producto ya está en uso.");
				}

				Producto nuevoProducto = new Producto();
				nuevoProducto.setNombre(nombre);
				nuevoProducto.setDescripcion(descripcion);
				nuevoProducto.setPrecio(precio);
				nuevoProducto.setStock(stock);
				nuevoProducto.setCategoria(categoriaSeleccionada);
				nuevoProducto.setActivo(activo);

				if (productoDAO.insertar(nuevoProducto)) {
					mostrarNotificacion("Producto agregado correctamente.", new Color(34, 139, 34)); // Verde más
																										// legible
					llenarTabla();
				} else {
					mostrarNotificacion("Error al agregar producto.", Color.RED);
				}
			} catch (NumberFormatException ex) {
				mostrarNotificacion("Verifique que los campos Precio y Stock sean numéricos.", Color.RED);
			} catch (IllegalArgumentException ex) {
				mostrarNotificacion(ex.getMessage(), Color.RED);
			}
		}
	}

}
