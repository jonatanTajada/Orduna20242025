package dam2.dam2.ejemploHibernate;
// Generated 9 dic 2024, 10:27:08 by Hibernate Tools 6.5.1.Final


import java.util.HashSet;
import java.util.Set;

/**
 * Categorias generated by hbm2java
 */
public class Categorias  implements java.io.Serializable {


     private Integer id;
     private String nombre;
     private String descripcion;
     private Boolean activo;
     private Set productoses = new HashSet(0);

    public Categorias() {
    }

	
    public Categorias(String nombre) {
        this.nombre = nombre;
    }
    public Categorias(String nombre, String descripcion, Boolean activo, Set productoses) {
       this.nombre = nombre;
       this.descripcion = descripcion;
       this.activo = activo;
       this.productoses = productoses;
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
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Boolean getActivo() {
        return this.activo;
    }
    
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
    public Set getProductoses() {
        return this.productoses;
    }
    
    public void setProductoses(Set productoses) {
        this.productoses = productoses;
    }




}


