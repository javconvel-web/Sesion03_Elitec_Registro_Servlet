package controller;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import entidad.Pais;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.PaisModel;

@WebServlet("/cargaPaisAlias")
public class CargaComboPaisServlet  extends HttpServlet {
	
private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//lista las categorias
		PaisModel model = new PaisModel();
		List<Pais> lista = model.listaTodasPais();
		
		//Convertir en json
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonSalida = gson.toJson(lista);
		System.out.println("Respuesta JSON: " + jsonSalida);
		
		//6 Enviar el JSON al cliente
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(jsonSalida);
		
	}


}