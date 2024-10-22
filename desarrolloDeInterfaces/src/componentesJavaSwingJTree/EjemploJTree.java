package componentesJavaSwingJTree;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

public class EjemploJTree extends JFrame {

	
	//   MIRAR SI HACERLO DE NUEVO,, NO ME COGE IMAGEN Y TAMPOCO ME GUSTA COMO ESCRIBE EN LA TABLA...REVISAR!!
	
	
	
	
	
	
    private JTree tree;
    private JPanel panelDerecho;
    private JTable rutaTabla;
    private DefaultTableModel tableModel;

    public EjemploJTree() {
    	
        // Configuración de la ventana principal
        setTitle("DAM: Ejemplo JTree");
        setSize(900, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear los nodos del árbol con nombres imaginativos
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Mundo");
        DefaultMutableTreeNode carpeta1 = new DefaultMutableTreeNode("Aventuras");
        DefaultMutableTreeNode carpeta2 = new DefaultMutableTreeNode("Secretos");

        DefaultMutableTreeNode nodoHoja1 = new DefaultMutableTreeNode("Piratas");
        DefaultMutableTreeNode nodoHoja2 = new DefaultMutableTreeNode("Tesoros");
        DefaultMutableTreeNode nodoHoja3 = new DefaultMutableTreeNode("Magia");
        DefaultMutableTreeNode nodoHoja4 = new DefaultMutableTreeNode("Hechizos");

        // Construir la jerarquía
        root.add(carpeta1);
        root.add(carpeta2);
        carpeta1.add(nodoHoja1);
        carpeta1.add(nodoHoja2);
        carpeta2.add(nodoHoja3);
        carpeta2.add(nodoHoja4);

        // Crear el modelo del árbol
        DefaultTreeModel model = new DefaultTreeModel(root);
        tree = new JTree(model);

        // Personalizar los íconos de los nodos hoja (sin hijos)
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
        renderer.setLeafIcon(new ImageIcon("D:\\PROGRAMACION\\DAM ORDUNA 2024_2025\\SegundoAnio\\workspaceEclipseSegundoAnioOrduna\\desarrolloDeInterfaces\\imgNodoHoja.png")); // Cambia la ruta al ícono que prefieras
        tree.setCellRenderer(renderer);

        // Panel derecho que muestra la información del nodo seleccionado
        panelDerecho = new JPanel();
        panelDerecho.setBackground(Color.GREEN);
        JLabel tituloPanel = new JLabel("Selecciona un nodo");
        panelDerecho.add(tituloPanel);

        // Crear la tabla para almacenar las rutas seleccionadas
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Ruta");

        rutaTabla = new JTable(tableModel);
        JScrollPane tablaScrollPane = new JScrollPane(rutaTabla);

        // Escuchar la selección de nodos en el árbol
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (selectedNode != null) {
                    String selectedNodeName = selectedNode.toString();
                    tituloPanel.setText("Nodo seleccionado: " + selectedNodeName); // Cambiar el título del panel con el nombre del nodo seleccionado

                    // Obtener la ruta con separadores "/"
                    String ruta = e.getPath().toString().replace(", ", "/").replace("[", "").replace("]", "");
                    
                    // Añadir la ruta a la tabla
                    tableModel.addRow(new Object[]{ruta});
                }
            }
        });

        // Crear el SplitPane que divide el árbol y el panel derecho
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(tree), panelDerecho);
        splitPane.setDividerLocation(200); // Ajustar el tamaño inicial de las dos áreas

        // Añadir los componentes al JFrame
        add(new JLabel("Componente JTree", JLabel.CENTER), BorderLayout.NORTH); // Título superior
        add(splitPane, BorderLayout.CENTER); // Árbol y panel derecho
        add(tablaScrollPane, BorderLayout.SOUTH); // Añadir la tabla en lugar del JTextArea

        // Hacer visible la ventana
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EjemploJTree());
    }
}
