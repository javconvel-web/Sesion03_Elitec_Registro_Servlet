package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entidad.Concurso;
import util.MySqlDBConexion;

public class ConcursoModel {

	public int registraConcurso(Concurso obj) {
		int salida = -1;
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			// 1 Crear la conexion a la BD
			cn = MySqlDBConexion.getConexion();

			// 2 Crear el SQL de insercion
			String sql = "INSERT INTO concurso (nombre, fechaInicio, fechaFin, estado) VALUES (?,?,?,?)";

			// 3 Crear el PreparedStatement
			ps = cn.prepareStatement(sql);
			ps.setString(1, obj.getNombre());
			ps.setDate(2, java.sql.Date.valueOf(obj.getFechaInicio()));
			ps.setDate(3, java.sql.Date.valueOf(obj.getFechaFin()));
			ps.setString(4, obj.getEstado());

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

	public List<Concurso> filtraConcurso(String nombre, LocalDate fechaInicio, LocalDate fechaFin, String estado) {

		List<Concurso> lista = new ArrayList<Concurso>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = MySqlDBConexion.getConexion();
			
			String sql = ""
					+ "SELECT idConcurso, nombre, fechaInicio, fechaFin, estado "
					+ "FROM concurso "
					+ "WHERE "
					+ "nombre LIKE ? AND "
					+ "(? = '' OR estado = ?) AND "
					+ "(? = '9999-12-31' OR fechaInicio >= ?) AND "
					+ "(? = '9999-12-31' OR fechaFin <= ?)";

			ps = con.prepareStatement(sql);

			ps.setString(1, "%" + nombre + "%");

			ps.setString(2, estado);
			ps.setString(3, estado);

			ps.setDate(4, java.sql.Date.valueOf(fechaInicio));
			ps.setDate(5, java.sql.Date.valueOf(fechaInicio));

			ps.setDate(6, java.sql.Date.valueOf(fechaFin));
			ps.setDate(7, java.sql.Date.valueOf(fechaFin));

			rs = ps.executeQuery();

			while (rs.next()) {
				Concurso c = new Concurso();

				c.setIdConcurso(rs.getInt("idConcurso"));
				c.setNombre(rs.getString("nombre"));
				c.setFechaInicio(rs.getDate("fechaInicio").toLocalDate());
				c.setFechaFin(rs.getDate("fechaFin").toLocalDate());
				c.setEstado(rs.getString("estado"));

				c.setFechaInicioStr(rs.getDate("fechaInicio").toString());
				c.setFechaFinStr(rs.getDate("fechaFin").toString());

				lista.add(c);
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