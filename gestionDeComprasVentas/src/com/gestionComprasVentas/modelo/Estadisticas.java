package com.gestionComprasVentas.modelo;

import java.util.Date;
import java.util.List;

public class Estadisticas {
    private List<Compra> compras;
    private List<Venta> ventas;

    // Constructor
    public Estadisticas(List<Compra> compras, List<Venta> ventas) {
        this.compras = compras;
        this.ventas = ventas;
    }

    // Métodos para calcular estadísticas
    public double calcularGastos(Date inicio, Date fin) {
        double gastos = 0;
        for (Compra compra : compras) {
            if (!compra.getFecha().before(inicio) && !compra.getFecha().after(fin)) {
                gastos += compra.getProducto().getPrecioCompra() * compra.getCantidad();
            }
        }
        return gastos;
    }

    public double calcularIngresos(Date inicio, Date fin) {
        double ingresos = 0;
        for (Venta venta : ventas) {
            if (!venta.getFecha().before(inicio) && !venta.getFecha().after(fin)) {
                ingresos += venta.getProducto().getPrecioVenta() * venta.getCantidad();
            }
        }
        return ingresos;
    }

    public double calcularBeneficio(Date inicio, Date fin) {
        return calcularIngresos(inicio, fin) - calcularGastos(inicio, fin);
    }
}
