package controller;

import java.io.IOException;

import entidad.Concurso;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ConcursoModel;

@WebServlet("/registraConcursoAlias")
public class RegistraConcursoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1 Recibir los datos del formulario del JSP
		String nombre = req.getParameter("nombre");
		String fechaInicio = req.getParameter("fecIni");
		String fechaFin = req.getParameter("fecFin");
		String estado = req.getParameter("estado");

		System.out.println("Datos recibidos: " + nombre + " - " + fechaInicio + " - " + fechaFin + " - " + estado);
		    

		// 2 Crear un objeto Concurso
		Concurso concurso = new Concurso();
		concurso.setNombre(nombre);
		concurso.setFechaInicio(java.time.LocalDate.parse(fechaInicio));
		concurso.setFechaFin(java.time.LocalDate.parse(fechaFin));
		concurso.setEstado(Integer.parseInt(estado));
		

		// 3 Crear un objeto CocnursoModel
		ConcursoModel model = new ConcursoModel();
		int salida = model.registraConcurso(concurso);

		String mensajeSalida = (salida > 0) ? "Concurso registrado correctamente (OK)" : "Error al registrar el concurso";

		
		// 4 Enviar una respuesta al cliente en JSON al jquery
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write("{\"mensajeSalida\":\"" + mensajeSalida + "\"}");
		
	}

}