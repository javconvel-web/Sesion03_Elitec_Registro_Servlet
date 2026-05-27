package controller;

import java.io.IOException;

import entidad.Alumno;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.AlumnoModel;

@WebServlet("/registraAlumnoAlias")
public class RegistraAlumnoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//1 Recibir los datos del formulario del JSP
		String nombre = req.getParameter("nombre");
		String dni = req.getParameter("dni");
		String correo = req.getParameter("correo");
		String fechaNacimiento = req.getParameter("fecNac");
		
		System.out.println("Datos recibidos: " + nombre + ", " + dni + ", " + correo + ", " + fechaNacimiento);

		//2 Crear un objeto Alumno
		Alumno alumno = new Alumno();
		alumno.setNombre(nombre);
		alumno.setDni(dni);
		alumno.setCorreo(correo);
		alumno.setFechaNacimiento(java.time.LocalDate.parse(fechaNacimiento));
		
		//3 Crear un objeto AlumnoModel
		AlumnoModel model = new AlumnoModel();
		int salida = model.registraAlumno(alumno);
		
		//4 Enviar una respuesta al cliente
		resp.sendRedirect("registraAlumno.jsp");
	}
	

	
}