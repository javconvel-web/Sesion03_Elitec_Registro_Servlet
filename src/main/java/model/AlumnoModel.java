package model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import entidad.Alumno;
import util.MySqlDBConexion;

public class AlumnoModel {

	public int  registraAlumno(Alumno alumno) {
		int salida = -1;
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			//1 Crear la conexion a la BD
			cn = MySqlDBConexion.getConexion();
			
			//2 Crear el SQL de insercion
			String sql = "INSERT INTO alumno (nombre, dni, correo, fechaNacimiento) VALUES (?,?,?,?)";
			
			//3 Crear el PreparedStatement
			ps = cn.prepareStatement(sql);
			ps.setString(1, alumno.getNombre());
			ps.setString(2, alumno.getDni());
			ps.setString(3, alumno.getCorreo());
			ps.setDate(4, java.sql.Date.valueOf(alumno.getFechaNacimiento()));
			
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