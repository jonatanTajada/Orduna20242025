package proyectoPlantillaJavaFX.src.pruebafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

//PLANTILLA PARA CREAR NUEVOS PROYECTOS. ECLIPSE TRATA CADA PROYECTO DE FORMA UNDIVIDUAL
// Y TENGO QUE ADD JARS, PARA EVITAR ESTO, COPIO Y PEGO ESTE PROYECTO Y DESPUES CAMBIO NOMBRE
//					***USO PLANTILLA JAVA FX ****



public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Crear un control Label
        Label label = new Label("¡Hola, JavaFX!");

        // Crear una escena con el control Label
        Scene scene = new Scene(label, 300, 200);

        // Configurar la ventana (Stage)
        primaryStage.setTitle("Mi primera aplicación JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);  // Lanzar la aplicación JavaFX
    }
    
	
    
    
    
    
}
