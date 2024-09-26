package ficheros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PruebasEscritura {

	
	
	public static void main(String[] args) {
		
		File archivo;
		FileWriter fw;
		
		try {
			
			//Escritura - OUT
			archivo = new File("ficheroTexto.txt");
			fw = new FileWriter(archivo);
			
			fw.write("Hola, este es un tezto de ejmploi y nada mas\n");
			fw.write("Mi nombre es: Jonatan\n");
			fw.write("Edad: 36\n");
			
			
			
			fw.close();
			System.out.println("Archivo correctamente escrito");
			
			//Lectura - IN
			
			FileReader fr = new FileReader(archivo);
			BufferedReader br = new BufferedReader(fr);
			
			//Leer linea por linea en el archivo
			String linea;
			while ((linea = br.readLine()) !=null) {
				System.out.println(linea);
			}
			
			br.close();
			fw.close();
			
		} catch (IOException e) {
			System.err.println("No ha sido posible el error es el siguiente:");
			e.printStackTrace();
		}
		
		
		
		

	}

}
