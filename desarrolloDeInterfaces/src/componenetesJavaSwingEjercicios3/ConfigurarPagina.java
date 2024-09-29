package componenetesJavaSwingEjercicios3;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigurarPagina extends JFrame {


	private static final long serialVersionUID = -673528825014244789L;
	private JComboBox<String> orientacionComboBox;
    private JSpinner margenSuperiorSpinner, margenInferiorSpinner;
    private JPanel panelHoja;
    private int margenSuperior = 10, margenInferior = 10;
    private boolean orientacionVertical = true;

    public static void main(String[] args) {
        ConfigurarPagina ventana = new ConfigurarPagina();
        ventana.setVisible(true);
    }

    public ConfigurarPagina() {
        // Configuración de la ventana
        setTitle("Configuración de Página");
        setSize(550, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Panel para representar la hoja
        panelHoja = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dibujarHoja(g);
            }
        };
        panelHoja.setBounds(50, 50, 100, 200);
        panelHoja.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        add(panelHoja);

        // Spinner para el margen superior
        JLabel labelMargenSuperior = new JLabel("Margen superior");
        labelMargenSuperior.setBounds(200, 50, 150, 20);
        add(labelMargenSuperior);

        margenSuperiorSpinner = new JSpinner(new SpinnerNumberModel(10, 0, 10, 1));
        margenSuperiorSpinner.setBounds(200, 70, 50, 30);
        margenSuperiorSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                margenSuperior = (int) margenSuperiorSpinner.getValue();
                panelHoja.repaint();
            }
        });
        add(margenSuperiorSpinner);

        // Spinner para el margen inferior
        JLabel labelMargenInferior = new JLabel("Margen inferior");
        labelMargenInferior.setBounds(200, 120, 150, 20);
        add(labelMargenInferior);

        margenInferiorSpinner = new JSpinner(new SpinnerNumberModel(10, 0, 10, 1));
        margenInferiorSpinner.setBounds(200, 140, 50, 30);
        margenInferiorSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                margenInferior = (int) margenInferiorSpinner.getValue();
                panelHoja.repaint();
            }
        });
        add(margenInferiorSpinner);

        // ComboBox para la orientación
        JLabel labelOrientacion = new JLabel("Orientación de página.");
        labelOrientacion.setBounds(330, 50, 150, 20);
        add(labelOrientacion);

        String[] orientaciones = {"Vertical", "Horizontal"};
        orientacionComboBox = new JComboBox<>(orientaciones);
        orientacionComboBox.setBounds(330, 70, 120, 30);
        orientacionComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccion = (String) orientacionComboBox.getSelectedItem();
                orientacionVertical = seleccion.equals("Vertical");
                panelHoja.repaint();
            }
        });
        add(orientacionComboBox);

        // Botón Inicializar
        JButton botonInicializar = new JButton("Inicializar");
        botonInicializar.setBounds(200, 300, 100, 30);
        botonInicializar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inicializar();
            }
        });
        add(botonInicializar);
    }

    // Método para dibujar la hoja con márgenes y orientación
    private void dibujarHoja(Graphics g) {
        g.setColor(Color.RED);
        if (orientacionVertical) {
            // Dibujar la hoja en orientación vertical
            g.drawRect(10, 10, 50, 100); // Dibuja la hoja
        } else {
            // Dibujar la hoja en orientación horizontal
            g.drawRect(10, 10, 100, 50); // Dibuja la hoja
        }

        // Dibujar los márgenes
        g.setColor(Color.BLUE);
        g.fillRect(10, 10 + margenSuperior, orientacionVertical ? 50 : 100, 2); // Margen superior
        g.fillRect(10, (orientacionVertical ? 110 : 60) - margenInferior, orientacionVertical ? 50 : 100, 2); // Margen inferior
    }

    // Método para inicializar la configuración
    private void inicializar() {
        margenSuperiorSpinner.setValue(0);
        margenInferiorSpinner.setValue(0);
        orientacionComboBox.setSelectedItem("Horizontal");
    }
}

