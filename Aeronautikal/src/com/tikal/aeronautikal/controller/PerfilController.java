package com.tikal.aeronautikal.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tikal.aeronautikal.dao.PerfilDAO;

import com.tikal.aeronautikal.dao.UsuarioDao;
import com.tikal.aeronautikal.entity.Perfil;
import com.tikal.aeronautikal.util.AsignadorDeCharset;
import com.tikal.aeronautikal.util.JsonConvertidor;


@Controller
@RequestMapping("/perfil")
public class PerfilController {

	
	 
	@Autowired
	@Qualifier("usuarioDao")
	UsuarioDao usuarioDao;
	
	@Autowired
	@Qualifier("perfilDAO")
	PerfilDAO perfilDAO;
	
	@RequestMapping(value={"/prueba"},method = RequestMethod.GET)
	   
	   public void prueba(HttpServletResponse response, HttpServletRequest request) throws IOException {
		   response.getWriter().println("Prueba del mètodo Perfil");

	    }

	@RequestMapping(value = { "/add" }, method = RequestMethod.POST, consumes = "Application/Json")
	public void crearPerfil(HttpServletRequest request, HttpServletResponse response, @RequestBody String json)
			throws IOException {
		//if (SesionController.verificarPermiso(request, usuarioDao, perfilDAO, 9)) {
			AsignadorDeCharset.asignar(request, response);
			System.out.println(" edgar manda:"+json);
			Perfil perfil = (Perfil) JsonConvertidor.fromJson(json, Perfil.class);
			System.out.println("lista de permisos:"+perfil.getPermisos());
			perfilDAO.crearPerfil(perfil);
		//} else {
		//	response.sendError(403);
		//}
	}

	@RequestMapping(value = { "/getAll" }, method = RequestMethod.GET, produces = "application/json")
	public void consultarUsuarios(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (SesionController.verificarPermiso(request, usuarioDao, perfilDAO, 10)) {
			AsignadorDeCharset.asignar(request, response);
			this.consult(response);
		}else{
			response.sendError(403);
		}
	}

	@RequestMapping(value = { "/update" }, method = RequestMethod.POST, consumes = "Application/Json")
	public void actualizarUsuario(HttpServletRequest request, HttpServletResponse response, @RequestBody String json)
			throws IOException {
		if (SesionController.verificarPermiso(request, usuarioDao, perfilDAO, 10)) {
			AsignadorDeCharset.asignar(request, response);
			Perfil perfil = (Perfil) JsonConvertidor.fromJson(json, Perfil.class);
			Perfil aux = perfilDAO.consultarPerfilPorId(perfil.getId());
			String nombrePerfilViejo = aux.getTipo();
			perfilDAO.actulizaPerfil(perfil);
			usuarioDao.actualizarUsuarios(nombrePerfilViejo, perfil.getTipo());
		} else {
			response.sendError(403);
		}

	}

	@RequestMapping(value = { "/delete" }, method = RequestMethod.POST, consumes = "Application/Json")
	public void eliminarUsuario(HttpServletRequest request, HttpServletResponse response, @RequestBody String json)
			throws IOException {
		AsignadorDeCharset.asignar(request, response);
		Perfil perfil = (Perfil) JsonConvertidor.fromJson(json, Perfil.class);
		perfilDAO.eliminarPerfil(perfil.getTipo());
	}
	
	//consulta los perfiles de cada usuario
	@RequestMapping(value = { "/getAllU" }, method = RequestMethod.GET, produces = "application/json")
	public void consultarPerfiles(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (SesionController.verificarPermiso(request, usuarioDao, perfilDAO, 7)) {
			AsignadorDeCharset.asignar(request, response);
			this.consult(response);
		}else{
			response.sendError(403);
		}
	}
	
	private void consult(HttpServletResponse response) throws IOException{
		List<Perfil> lista = perfilDAO.consultarPerfiles();
		response.getWriter().println(JsonConvertidor.toJson(lista));
	}

}
