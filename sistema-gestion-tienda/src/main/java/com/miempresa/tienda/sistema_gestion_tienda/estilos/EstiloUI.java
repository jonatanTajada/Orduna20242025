package com.miempresa.tienda.sistema_gestion_tienda.estilos;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class EstiloUI {

	// Colores principales
	public static final Color COLOR_PRIMARIO = new Color(70, 130, 180); // Azul suave
	public static final Color COLOR_SECUNDARIO = new Color(240, 248, 255); // Azul claro
	public static final Color COLOR_TEXTO = Color.BLACK; // Negro para el texto
	public static final Color COLOR_BOTON = new Color(100, 149, 237); // Azul más fuerte para botones

	// Colores adicionales para tablas
	public static final Color COLOR_FONDO_TABLA = Color.WHITE;
	public static final Color COLOR_FILA_ALTERNADA = new Color(220, 230, 240); // Azul claro
	public static final Color COLOR_ENCABEZADO_TABLA = Color.BLACK; // Fondo negro para encabezado
	public static final Color COLOR_TEXTO_ENCABEZADO = Color.WHITE; // Texto blanco en encabezado

	// Fuentes
	public static final Font FUENTE_TITULO = new Font("Arial", Font.BOLD, 24);
	public static final Font FUENTE_TEXTO = new Font("Arial", Font.PLAIN, 14);
	public static final Font FUENTE_BOTON = new Font("Arial", Font.BOLD, 14);

	// Cargar imágenes
	public static ImageIcon cargarIcono(String nombreArchivo) {
		return new ImageIcon(EstiloUI.class.getClassLoader().getResource(nombreArchivo));
	}

	/**
	 * Configura el estilo de una JTable para mantener la uniformidad en la app.
	 *
	 * @param tabla JTable a la que se aplicará el estilo.
	 */
	public static void configurarTabla(JTable tabla) {
		// Configuración de la tabla
		tabla.setFont(FUENTE_TEXTO);
		tabla.setRowHeight(25); // Altura de las filas
		tabla.setBackground(COLOR_FONDO_TABLA);

		// Configuración del encabezado
		JTableHeader encabezado = tabla.getTableHeader();
		encabezado.setFont(FUENTE_TEXTO.deriveFont(Font.BOLD));
		encabezado.setBackground(COLOR_ENCABEZADO_TABLA);
		encabezado.setForeground(COLOR_TEXTO_ENCABEZADO);
		encabezado.setOpaque(true);

		// Centrar el texto de las celdas
		DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
		centrado.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		tabla.setDefaultRenderer(Object.class, centrado);

		// Configuración alternada para las filas
		tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			@Override
			public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				java.awt.Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
						column);

				if (!isSelected) {
					if (row % 2 == 0) {
						c.setBackground(COLOR_FILA_ALTERNADA);
					} else {
						c.setBackground(COLOR_FONDO_TABLA);
					}
				}
				return c;
			}
		});
	}
}
