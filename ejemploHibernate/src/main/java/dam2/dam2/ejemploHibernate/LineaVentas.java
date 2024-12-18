package dam2.dam2.ejemploHibernate;
// Generated 9 dic 2024, 10:27:08 by Hibernate Tools 6.5.1.Final



/**
 * LineaVentas generated by hbm2java
 */
public class LineaVentas  implements java.io.Serializable {


     private Integer id;
     private Productos productos;
     private Ventas ventas;
     private int cantidad;
     private double subtotal;

    public LineaVentas() {
    }

	
    public LineaVentas(int cantidad, double subtotal) {
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }
    public LineaVentas(Productos productos, Ventas ventas, int cantidad, double subtotal) {
       this.productos = productos;
       this.ventas = ventas;
       this.cantidad = cantidad;
       this.subtotal = subtotal;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Productos getProductos() {
        return this.productos;
    }
    
    public void setProductos(Productos productos) {
        this.productos = productos;
    }
    public Ventas getVentas() {
        return this.ventas;
    }
    
    public void setVentas(Ventas ventas) {
        this.ventas = ventas;
    }
    public int getCantidad() {
        return this.cantidad;
    }
    
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public double getSubtotal() {
        return this.subtotal;
    }
    
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }




}


