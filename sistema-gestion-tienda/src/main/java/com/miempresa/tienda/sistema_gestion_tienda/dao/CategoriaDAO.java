package com.miempresa.tienda.sistema_gestion_tienda.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.miempresa.tienda.sistema_gestion_tienda.modelo.Categoria;

/**
 * Clase que gestiona las operaciones CRUD de la tabla categorias.
 */
public class CategoriaDAO {

    /**
     * Obtiene todas las categorías de la base de datos.
     * 
     * @return Lista de categorías.
     */
	public List<Categoria> obtenerTodas() {
		
	    List<Categoria> categorias = new ArrayList<>();
	    String sql = "SELECT * FROM categorias";

	    try (Connection con = ConexionDB.getConnection();
	         Statement stmt = con.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        while (rs.next()) {
	            Categoria categoria = new Categoria();
	            categoria.setId(rs.getInt("id"));
	            categoria.setNombre(rs.getString("nombre"));
	            categorias.add(categoria);
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al obtener categorías: " + e.getMessage());
	    }

	    return categorias;
	}
	



    /**
     * Inserta una nueva categoría en la base de datos.
     * 
     * @param categoria La categoría a insertar.
     * @return true si la inserción fue exitosa, false en caso contrario.
     */
    public boolean insertar(Categoria categoria) {
        String sql = "INSERT INTO categorias (nombre) VALUES (?)";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, categoria.getNombre());
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar categoría: " + e.getMessage());
            return false;
        }
    }

    /**
     * Actualiza una categoría existente en la base de datos.
     * 
     * @param categoria La categoría con los datos actualizados.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    public boolean actualizar(Categoria categoria) {
        String sql = "UPDATE categorias SET nombre = ? WHERE id = ?";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, categoria.getNombre());
            pstmt.setInt(2, categoria.getId());
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar categoría: " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina una categoría de la base de datos por su ID.
     * 
     * @param id ID de la categoría a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     */
    public boolean eliminar(int id) {
        String sql = "DELETE FROM categorias WHERE id = ?";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar categoría: " + e.getMessage());
            return false;
        }
    }
    
    
    /**
     * Verifica si una categoría ya existe en la base de datos.
     *
     * @param nombre El nombre de la categoría a verificar.
     * @return true si ya existe, false en caso contrario.
     */
    public boolean existeCategoria(String nombre) {
        String sql = "SELECT COUNT(*) AS total FROM categorias WHERE nombre = ?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, nombre);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("total") > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar existencia de categoría: " + e.getMessage());
        }
        return false;
    }

}

