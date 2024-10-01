package practicasFinalesUd1.ejercicio2;

import java.io.File;

// Ejercicio 3: Crear un directorio vacío en la ruta dada

public class CrearDirectorio3 {
	public static void main(String[] args) {
		File directorio = new File("C:\\Users\\Alumni\\Documents\\NetBeansProjects\\Ficheros\\src\\ficheros");

		if (directorio.mkdirs()) {
			System.out.println("Directorio creado con éxito.");
		} else {
			System.out.println("No se pudo crear el directorio (puede que ya exista).");
		}
	}
}
