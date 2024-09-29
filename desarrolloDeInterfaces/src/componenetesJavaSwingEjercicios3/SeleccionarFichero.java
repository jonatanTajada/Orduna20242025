package componenetesJavaSwingEjercicios3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SeleccionarFichero extends JFrame {


	private static final long serialVersionUID = 670078702198285721L;
	private JTextField rutaTextField;
    private JButton seleccionarButton;

    public SeleccionarFichero() {
        setTitle("Mostrar ruta fichero");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        // Crear componentes
        JLabel label = new JLabel("Pulsa en el botón y elige una ruta");
        rutaTextField = new JTextField(20);
        seleccionarButton = new JButton("...");

        // Añadir componentes al layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(label, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(rutaTextField, gbc);

        gbc.gridx = 1;
        add(seleccionarButton, gbc);

        // Evento para abrir JFileChooser y seleccionar fichero .txt
        seleccionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

                // Filtro para solo permitir archivos .txt
                fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        return f.isDirectory() || f.getName().toLowerCase().endsWith(".txt");
                    }

                    @Override
                    public String getDescription() {
                        return "Archivos de Texto (.txt)";
                    }
                });

                int seleccion = fileChooser.showOpenDialog(SeleccionarFichero.this);
                if (seleccion == JFileChooser.APPROVE_OPTION) {
                    File archivoSeleccionado = fileChooser.getSelectedFile();
                    rutaTextField.setText(archivoSeleccionado.getAbsolutePath());
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SeleccionarFichero ventana = new SeleccionarFichero();
            ventana.setVisible(true);
        });
    }
}

