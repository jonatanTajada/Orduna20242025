package com.ejemplo.HibernateEjemplo;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {

    public static void main(String[] args) {
    	
        Usuario usuario = new Usuario("Juan Pérez", "juan@example.com");
        saveUsuario(usuario);

        Usuario usuarioLeido = getUsuario(usuario.getId());
        System.out.println("Usuario leído: " + usuarioLeido.getNombre());

        usuarioLeido.setNombre("Juan Pérez Modificado");
        updateUsuario(usuarioLeido);

     //   deleteUsuario(usuarioLeido.getId());

        HibernateUtil.shutdown();
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------------
    
    public static void saveUsuario(Usuario usuario) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(usuario);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public static Usuario getUsuario(Long id) {
    	
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Usuario.class, id);
        }
    }

    public static void updateUsuario(Usuario usuario) {
    	
        Transaction transaction = null;
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        	
            transaction = session.beginTransaction();
            session.update(usuario);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public static void deleteUsuario(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Usuario usuario = session.get(Usuario.class, id);
            if (usuario != null) session.delete(usuario);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}

