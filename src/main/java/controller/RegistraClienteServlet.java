package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import entidad.Categoria;
import entidad.Cliente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ClienteModel;

@WebServlet("/registraClienteAlias")
public class RegistraClienteServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1 recibir los parametros
		String nombre = req.getParameter("nombre");
		String dni = req.getParameter("dni");
		String categoria = req.getParameter("categoria");
		
		//2 Crear el objeto
		Categoria objCategoria = new Categoria();
		objCategoria.setIdCategoria(Integer.parseInt(categoria));;		
		
		Cliente objCliente = new Cliente();
		objCliente.setNombre(nombre);
		objCliente.setDni(dni);
		objCliente.setEstado(1);
		objCliente.setFechaRegistro(LocalDateTime.now());
		objCliente.setCategoria(objCategoria);
		
		//3 Crear el Model
		ClienteModel model = new ClienteModel();
		int salida = model.insertarCliente(objCliente);
		
		String mensajeSalida = (salida > 0) ? "Cliente registrado correctamente (OK)" : "Error al registrar el cliente";
		
		// 4 Enviar una respuesta al cliente en JSON al jquery
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write("{\"mensajeSalida\":\"" + mensajeSalida + "\"}");
	}


}