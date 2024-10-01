package com.gestionComprasVentas.modelo;

import java.util.Date;

public class Compra {
    private int id;
    private Producto producto;
    private int cantidad;
    private Date fecha;

    // Constructor
    public Compra(int id, Producto producto, int cantidad, Date fecha) {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
