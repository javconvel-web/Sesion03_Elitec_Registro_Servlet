package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidad.Tipo;

public class TipoModel {

	public List<Tipo> listaTodasTipos(){
		List<Tipo> lista = new ArrayList<Tipo>(); 
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = util.MySqlDBConexion.getConexion();
			String sql = "SELECT idTipo, descripcion FROM tipo";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			Tipo c = null;
			while (rs.next()) {
				c = new Tipo();
				c.setIdTipo(rs.getInt("idTipo"));
				c.setDescripcion(rs.getString("descripcion"));
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