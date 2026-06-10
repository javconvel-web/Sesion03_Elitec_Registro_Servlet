package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidad.Categoria;

public class CategoriaModel {

	public List<Categoria> listaTodasCategorias(){
		List<Categoria> lista = new ArrayList<Categoria>(); 
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = util.MySqlDBConexion.getConexion();
			String sql = "SELECT idCategoria, nombre FROM categoria";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			Categoria c = null;
			while (rs.next()) {
				c = new Categoria();
				c.setIdCategoria(rs.getInt("idCategoria"));
				c.setNombre(rs.getString("nombre"));
				lista.add(c);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (Exception ex2) {
				ex2.printStackTrace();
			}
		
		}
		return lista;
	}
	
}