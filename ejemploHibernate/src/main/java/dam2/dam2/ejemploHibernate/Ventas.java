package dam2.dam2.ejemploHibernate;
// Generated 9 dic 2024, 10:27:08 by Hibernate Tools 6.5.1.Final


import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Ventas generated by hbm2java
 */
public class Ventas  implements java.io.Serializable {


     private Integer id;
     private Timestamp fecha;
     private double total;
     private Set lineaVentases = new HashSet(0);

    public Ventas() {
    }

	
    public Ventas(double total) {
        this.total = total;
    }
    public Ventas(Timestamp fecha, double total, Set lineaVentases) {
       this.fecha = fecha;
       this.total = total;
       this.lineaVentases = lineaVentases;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Timestamp getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }
    public double getTotal() {
        return this.total;
    }
    
    public void setTotal(double total) {
        this.total = total;
    }
    public Set getLineaVentases() {
        return this.lineaVentases;
    }
    
    public void setLineaVentases(Set lineaVentases) {
        this.lineaVentases = lineaVentases;
    }




}

