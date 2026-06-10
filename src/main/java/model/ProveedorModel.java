package model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import entidad.Proveedor;

public class ProveedorModel {

	
	public int insertarProveedor(Proveedor proveedor) {
		int salida = -1;
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			cn = util.MySqlDBConexion.getConexion();
			String sql = "INSERT INTO proveedor (nombre, dni, fechaRegistro, fechaActualizacion, idTipo, idPais, estado) VALUES (?,?,?,?,?,?,?)";
			ps = cn.prepareStatement(sql);
			
			ps.setString(1, proveedor.getNombre());
			ps.setString(2, proveedor.getDni());
			ps.setTimestamp(3, java.sql.Timestamp.valueOf(proveedor.getFechaRegistro()));
			ps.setTimestamp(4, java.sql.Timestamp.valueOf(proveedor.getFechaActualizacion()));
			ps.setInt(5, proveedor.getIdTipo().getIdTipo());
			ps.setInt(6, proveedor.getIdPais().getIdPais());
			ps.setInt(7, proveedor.getEstado());
			
			System.out.println("SQL: " + ps);
			salida = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			salida = -1;
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (cn != null)
					cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return salida;
	}
}