package ficheros;

import java.util.Scanner;

public class AppPersona {

	Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		
		
		
	}
	
	
	
	
	public Persona guardarPersona() {
		
		System.out.print("Introduce tu nombre: ");
		String nombre = scanner.nextLine();
		
		System.out.println("Introduce D.N.I: ");
		String dni = scanner.nextLine();
		
		System.out.print("Introduce edad: ");
		int edad = Integer.parseInt(scanner.nextLine());
		
		Persona persona = new Persona(nombre, edad, dni);
		
		
		return persona;
		
		
	}
	
	
	
}
