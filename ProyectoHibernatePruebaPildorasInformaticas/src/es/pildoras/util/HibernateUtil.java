package es.pildoras.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	// Variable estática para mantener una única instancia de SessionFactory
	private static final SessionFactory sessionFactory;

	// Bloque estático para inicializar la SessionFactory
	static {
		try {
			// Crear la configuración y construir el SessionFactory
			sessionFactory = new Configuration().configure("hibernate.cfg.xml") // Archivo de configuración
					.buildSessionFactory();
		} catch (Throwable ex) {
			// Manejo de errores durante la inicialización
			System.err.println("La creación de SessionFactory falló: " + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	// Método público para obtener la instancia de SessionFactory
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	// Método para cerrar el SessionFactory al final de la aplicación
	public static void shutdown() {
		getSessionFactory().close();
	}
}
