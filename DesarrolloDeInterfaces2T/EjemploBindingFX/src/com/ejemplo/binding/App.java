package com.ejemplo.binding;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage ventanaPrincipal) throws Exception {
        // Cargar el archivo FXML
        Parent root = FXMLLoader.load(getClass().getResource("/com/ejemplo/binding/vistas/bindingEjemplo.fxml"));

        // Crear la escena
        Scene scene = new Scene(root);

        // Configurar el escenario (ventana principal)
        ventanaPrincipal.setTitle("Ejemplo Binding FX");
        ventanaPrincipal.setScene(scene);
        ventanaPrincipal.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
