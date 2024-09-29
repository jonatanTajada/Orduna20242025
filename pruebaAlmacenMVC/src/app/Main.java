package app;

import controller.CamioneroController;
import controller.CamionController;
import controller.CitaController;
import controller.VentanaPrincipalController;
import model.CamioneroModel;
import view.CamioneroView;
import view.CamionView;
import view.CitaView;
import view.VentanaPrincipalView;

public class Main {
	public static void main(String[] args) {
		// Crear las vistas
		VentanaPrincipalView ventanaPrincipalView = new VentanaPrincipalView();
		CamioneroView camioneroView = new CamioneroView();
		CamionView camionView = new CamionView();
		CitaView citaView = new CitaView();

		// Crear el modelo inicial para los camioneros (puedes hacerlo más dinámico si
		// lo necesitas)
		CamioneroModel camioneroModel = new CamioneroModel(1, "John", "Doe", "12345678A");

		// Instanciar los controladores con sus respectivas vistas y modelos
		VentanaPrincipalController ventanaPrincipalController = new VentanaPrincipalController(ventanaPrincipalView,
				camioneroView, camionView, citaView);

		CamioneroController camioneroController = new CamioneroController(camioneroView);
		CamionController camionController = new CamionController(camionView, camioneroModel);
		CitaController citaController = new CitaController(citaView, camioneroModel);

		// Mostrar la ventana principal
		ventanaPrincipalView.setVisible(true);
	}
}
