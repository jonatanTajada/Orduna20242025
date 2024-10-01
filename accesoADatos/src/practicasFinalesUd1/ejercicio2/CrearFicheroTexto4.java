package practicasFinalesUd1.ejercicio2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

// Ejercicio 4: Crear un fichero de texto y escribir en él

public class CrearFicheroTexto4 {
	public static void main(String[] args) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("ruta/fichero.txt"))) {
			writer.write("Ejemplo de escritura en un fichero de texto o txt");
			System.out.println("Fichero de texto creado y escrito con éxito.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
