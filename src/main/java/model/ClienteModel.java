package model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import entidad.Cliente;

public class ClienteModel {

	
	public int insertarCliente(Cliente cliente) {
		int salida = -1;
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			cn = util.MySqlDBConexion.getConexion();
			String sql = "INSERT INTO cliente (nombre, dni, fechaRegistro, estado, idCategoria) VALUES (?,?,?,?,?)";
			ps = cn.prepareStatement(sql);
			ps.setString(1, cliente.getNombre());
			ps.setString(2, cliente.getDni());
			ps.setTimestamp(3, java.sql.Timestamp.valueOf(cliente.getFechaRegistro()));
			ps.setInt(4, cliente.getEstado());
			ps.setInt(5, cliente.getCategoria().getIdCategoria());
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