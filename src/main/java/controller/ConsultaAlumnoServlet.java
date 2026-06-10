package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import entidad.Alumno;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.AlumnoModel;

@WebServlet("/consultaAlumnoAlias")
public class ConsultaAlumnoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1 Recibir el dato del formulario del JSP
		String nombre = req.getParameter("nombre");
		String dni = req.getParameter("dni");
		String correo = req.getParameter("correo");
		String fecNacInicio = req.getParameter("fecNacInicio");
		String fecNacFin = req.getParameter("fecNacFin");
		
		//2 Mostrar los datos recibidos en la consola del servidor
		System.out.println("Datos recibidos: " + nombre + " - " + dni + " - " + correo + " - " + fecNacInicio + " - " + fecNacFin);
		
		//3 Convertir las fechas de String a LocalDate (si no estan vacias)
		LocalDate fecNacInicioLD = (fecNacInicio.isEmpty()) ? LocalDate.parse("9999-12-31") : LocalDate.parse(fecNacInicio);
		LocalDate fecNacFinLD = (fecNacFin.isEmpty()) ? LocalDate.parse("9999-12-31") : LocalDate.parse(fecNacFin);
		
		//4 Crear un objeto AlumnoModel
		AlumnoModel model = new AlumnoModel();
		List<Alumno> lista = model.filtraAlumno(nombre, dni, correo,fecNacInicioLD, fecNacFinLD);
		
		//5 Convertir la lista de alumnos a un formato JSON usando gson
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonSalida = gson.toJson(lista);
		System.out.println("Respuesta JSON: " + jsonSalida);
		
		//6 Enviar el JSON al cliente
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(jsonSalida);
		
	}	
	
}