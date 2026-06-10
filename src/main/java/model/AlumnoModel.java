package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entidad.Alumno;
import util.MySqlDBConexion;

public class AlumnoModel {

	public int registraAlumno(Alumno alumno) {
		int salida = -1;
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			// 1 Crear la conexion a la BD
			cn = MySqlDBConexion.getConexion();

			// 2 Crear el SQL de insercion
			String sql = "INSERT INTO alumno (nombre, dni, correo, fechaNacimiento) VALUES (?,?,?,?)";

			// 3 Crear el PreparedStatement
			ps = cn.prepareStatement(sql);
			ps.setString(1, alumno.getNombre());
			ps.setString(2, alumno.getDni());
			ps.setString(3, alumno.getCorreo());
			ps.setDate(4, java.sql.Date.valueOf(alumno.getFechaNacimiento()));

			System.out.println("SQL: " + ps);

			// 4 Ejecutar el SQL
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

	public List<Alumno> filtraAlumno(String nombre, String dni, String correo, LocalDate fechaNacInicio,
			LocalDate fechaNacFin) {

		List<Alumno> lista = new ArrayList<Alumno>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = MySqlDBConexion.getConexion();
			String sql = "" + "SELECT idAlumno, nombre, dni, correo, fechaNacimiento FROM alumno " + "WHERE "
					+ "nombre LIKE ? AND " + "(? = '' OR dni = ? ) AND " + "(? = '' OR correo = ? ) AND "
					+ "(? = '9999-12-31' OR fechaNacimiento >= ?) AND " + "(? = '9999-12-31' OR fechaNacimiento <= ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, "%" + nombre + "%");
			ps.setString(2, dni);
			ps.setString(3, dni);
			ps.setString(4, correo);
			ps.setString(5, correo);
			ps.setDate(6, java.sql.Date.valueOf(fechaNacInicio));
			ps.setDate(7, java.sql.Date.valueOf(fechaNacInicio));
			ps.setDate(8, java.sql.Date.valueOf(fechaNacFin));
			ps.setDate(9, java.sql.Date.valueOf(fechaNacFin));

			System.out.println("SQL: " + ps);

			rs = ps.executeQuery();

			while (rs.next()) {
				Alumno a = new Alumno();
				a.setIdAlumno(rs.getInt("idAlumno"));
				a.setNombre(rs.getString("nombre"));
				a.setDni(rs.getString("dni"));
				a.setCorreo(rs.getString("correo"));
				a.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
				a.setFechaNacimientoStr(rs.getDate("fechaNacimiento").toString());
				lista.add(a);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return lista;
	}

}