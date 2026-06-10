package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidad.Pais;

public class PaisModel {

	public List<Pais> listaTodasPais(){
		List<Pais> lista = new ArrayList<Pais>(); 
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = util.MySqlDBConexion.getConexion();
			String sql = "SELECT idPais, iso, nombre FROM pais";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			Pais c = null;
			while (rs.next()) {
				c = new Pais();
				c.setIdPais(rs.getInt("idPais"));
				c.setIso(rs.getString("iso"));
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