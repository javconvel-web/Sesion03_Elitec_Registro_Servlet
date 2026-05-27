package model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import entidad.Concurso;
import util.MySqlDBConexion;

public class ConcursoModel {

	public int registraConcurso(Concurso obj) {
		int salida = -1;
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			//1 Crear la conexion a la BD
			cn = MySqlDBConexion.getConexion();
			
			//2 Crear el SQL de insercion
			String sql = "INSERT INTO concurso (nombre, fechaInicio, fechaFin, estado) VALUES (?,?,?,?)";
			
			//3 Crear el PreparedStatement
			ps = cn.prepareStatement(sql);
			ps.setString(1, obj.getNombre());
			ps.setDate(2, java.sql.Date.valueOf(obj.getFechaInicio()));
			ps.setDate(3, java.sql.Date.valueOf(obj.getFechaFin()));
			ps.setInt(4, obj.getEstado());
			
			System.out.println("SQL: " + ps);
			
			//4 Ejecutar el SQL	
			salida = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
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