package dam2.dam2.ejemploHibernate;
// Generated 9 dic 2024, 10:27:08 by Hibernate Tools 6.5.1.Final


import java.util.HashSet;
import java.util.Set;

/**
 * Proveedores generated by hbm2java
 */
public class Proveedores  implements java.io.Serializable {


     private Integer id;
     private String nombre;
     private String contacto;
     private String direccion;
     private Set pedidoses = new HashSet(0);

    public Proveedores() {
    }

	
    public Proveedores(String nombre) {
        this.nombre = nombre;
    }
    public Proveedores(String nombre, String contacto, String direccion, Set pedidoses) {
       this.nombre = nombre;
       this.contacto = contacto;
       this.direccion = direccion;
       this.pedidoses = pedidoses;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getContacto() {
        return this.contacto;
    }
    
    public void setContacto(String contacto) {
        this.contacto = contacto;
    }
    public String getDireccion() {
        return this.direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public Set getPedidoses() {
        return this.pedidoses;
    }
    
    public void setPedidoses(Set pedidoses) {
        this.pedidoses = pedidoses;
    }




}

