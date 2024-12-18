package es.pildoras.pruebasHibernate;

import java.sql.*;


public class PruebasJDBC {

	public static void main(String[] args) {
		
		
		final String URL = "jdbc:mysql://localhost:3306/pruebasHibernate?useSSL=false";
		final String USER = "root";
		final String PASS ="1234";
		
		try {
			
			System.out.println("Intrntando conectar con la base de datos: " + URL);
			
			Connection conexion = DriverManager.getConnection(URL, USER, PASS);
			
			System.out.println("conexion exitosa!!!");
			
			
			
		} catch (Exception e) {
			System.err.println("Error no hemnos posido conectarnos a la bbdd");
			e.printStackTrace();
		}
		
		
		
		
		
		
		

	}

}
