package es.pildoras.conexionHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import es.pildoras.util.HibernateUtil;

public class GuardaClientePrueba {

	public static void main(String[] args) {

		// Obtener SessionFactory usando HibernateUtil
		SessionFactory miFactory = HibernateUtil.getSessionFactory();

		// Manejo de la sesión
		try (Session miSession = miFactory.openSession()) {
			// Crear un objeto cliente
			Clientes cliente1 = new Clientes("Jonatan", "Tajada Rico", "Karranzairu 8");

			// Iniciar transacción
			miSession.beginTransaction();

			// Guardar cliente en la base de datos
			miSession.save(cliente1);

			// Confirmar transacción
			miSession.getTransaction().commit();

			System.out.println("Registro registrado correctamente en la base de datos");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Cerrar el SessionFactory al final de la aplicación
			HibernateUtil.shutdown();
		}
	}
}
