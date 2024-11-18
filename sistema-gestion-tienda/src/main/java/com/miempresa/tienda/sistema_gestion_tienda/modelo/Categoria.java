package com.miempresa.tienda.sistema_gestion_tienda.modelo;


/**
 * Clase que representa una categoría de productos.
 */
public class Categoria {
	
    private int id;       // Identificador único
    private String nombre; // Nombre de la categoría

    /**
     * Constructor vacío.
     */
    public Categoria() {
    }

    /**
     * Constructor con parámetros.
     * 
     * @param id Identificador único.
     * @param nombre Nombre de la categoría.
     */
    public Categoria(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre; 
    }

}

