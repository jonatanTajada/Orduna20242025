package ud1Procesos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class Lanzador {

    
	public static void main(String[] args) {
		Lanzador l = new Lanzador();
		l.lanzarSumador(1, 5, "result1.txt");
		l.lanzarSumador(6, 10, "result2.txt");
		System.out.println("Ok");
	}
	
	
	
	
    public void lanzarSumador(int n1, int n2, String archivoResultado) {
    	
        try {
            // Crear el comando para ejecutar Sumador con n1 y n2
        	ProcessBuilder builder = new ProcessBuilder("java", "ud1Procesos.Sumador", String.valueOf(n1), String.valueOf(n2));

            
            // Iniciar el proceso
            Process proceso = builder.start();
            
            // Leer la salida del proceso (el resultado de la suma)
            InputStream is = proceso.getInputStream();
            StringBuilder resultado = new StringBuilder();
            int c;
            while ((c = is.read()) != -1) {
                resultado.append((char) c);
            }
            
            // Esperar a que el proceso termine
            proceso.waitFor();
            
            // Guardar el resultado en un archivo
            guardarResultadoEnArchivo(resultado.toString(), archivoResultado);
            
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Método para guardar el resultado en un archivo
    private void guardarResultadoEnArchivo(String resultado, String archivoResultado) {
        try (FileWriter writer = new FileWriter(new File(archivoResultado))) {
            writer.write(resultado);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
