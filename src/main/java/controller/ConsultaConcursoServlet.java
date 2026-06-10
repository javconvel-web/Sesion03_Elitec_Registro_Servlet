package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import entidad.Concurso;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ConcursoModel;

@WebServlet("/consultaConcursoAlias")
public class ConsultaConcursoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1 Recibir el dato del formulario del JSP
		String nombre = req.getParameter("nombre");
		String estado = req.getParameter("estado");
		String fecIni = req.getParameter("fecIni");
		String fecFin = req.getParameter("fecFin");
		
		
		//2 Mostrar los datos recibidos en la consola del servidor
		System.out.println("Datos: " + nombre + " - " + estado + " - " + fecIni + " - " + fecFin);
		
		//3 Convertir las fechas de String a LocalDate (si no estan vacias)
		LocalDate fecIniLD = (fecIni.isEmpty()) ? LocalDate.parse("9999-12-31") : LocalDate.parse(fecIni);
		LocalDate fecFinLD = (fecFin.isEmpty()) ? LocalDate.parse("9999-12-31") : LocalDate.parse(fecFin);
		
		//4 Crear un objeto ConcursoModel
		ConcursoModel model = new ConcursoModel();
		List<Concurso> lista = model.filtraConcurso(nombre, fecIniLD, fecFinLD, estado);
		
		//5 Convertir la lista de consursos a un formato JSON usando gson
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(lista);

		
		//6 Enviar el JSON al cliente
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(json);
		
	}

	
	
}