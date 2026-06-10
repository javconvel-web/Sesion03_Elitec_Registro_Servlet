package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import entidad.Tipo;
import entidad.Pais;
import entidad.Proveedor;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ProveedorModel;

@WebServlet("/registraProveedorAlias")
public class RegistraProveedorServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1 recibir los parametros
		String nombre = req.getParameter("nombre");
		String dni = req.getParameter("dni");
		String tipo = req.getParameter("tipo");
		String pais = req.getParameter("pais");
		
		//2 Crear el objeto
		Tipo objTipo = new Tipo();
		objTipo.setIdTipo(Integer.parseInt(tipo));;
		
		Pais objPais = new Pais();
		objPais.setIdPais(Integer.parseInt(pais));;	
		
		
		Proveedor objProveedor = new Proveedor();
		objProveedor.setNombre(nombre);
		objProveedor.setDni(dni);
		objProveedor.setFechaRegistro(LocalDateTime.now());
		objProveedor.setFechaActualizacion(LocalDateTime.now());
		objProveedor.setIdTipo(objTipo);
		objProveedor.setIdPais(objPais);
		objProveedor.setEstado(1);
		
	
		//3 Crear el Model
		ProveedorModel model = new ProveedorModel();
		int salida = model.insertarProveedor(objProveedor);
		
		String mensajeSalida = (salida > 0) ? "Cliente registrado correctamente (OK)" : "Error al registrar el cliente";
		
		// 4 Enviar una respuesta al cliente en JSON al jquery
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write("{\"mensajeSalida\":\"" + mensajeSalida + "\"}");
	}


}