package com.tikal.aeronautikal.controller;

import java.io.IOException;
//import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tikal.aeronautikal.dao.PerfilDAO;
import com.tikal.aeronautikal.dao.UsuarioDao;
import com.tikal.aeronautikal.entity.Perfil;
import com.tikal.aeronautikal.entity.Usuario;
import com.tikal.aeronautikal.util.JsonConvertidor;


@Controller
public class SesionController {

	@Autowired
	UsuarioDao usuarioDao;  // para la autentificacion
	
	////////////////////// checar si el usuario es válido

	@RequestMapping(value = { "/user" }, method = RequestMethod.GET, produces = "application/json")
	public void user(HttpServletResponse res, HttpServletRequest req) throws IOException {
		String auti = req.getHeader("authorization");
		auti = auti.substring(5);
		byte[] dec = Base64Utils.decodeFromString(auti);

		String c = "";
		for (byte b : dec) {
			c += (char) b;
		}
		String[] parts = c.split(":");
		String u = parts[0];
		String p = UsuarioController.otroMetodo(parts[1]);
		Usuario usuario = usuarioDao.consultarUsuario(u);
		// Verificar que el usuario y contraseña coincidan
		if (usuario == null || (usuario.getPassword().equals(p) == false)) {
			res.sendError(403);
		} else {
			usuario.resetPassword();
			req.getSession().setAttribute("userName", usuario.getUsername());
			res.getWriter().println(JsonConvertidor.toJson(usuario));
		}
	}

	// currentSession chec la session activa

	@RequestMapping(value = { "/currentSession" }, method = RequestMethod.GET, produces = "application/json")
	public void currentUser(HttpServletResponse res, HttpServletRequest req) throws IOException {
		HttpSession s = req.getSession();
		String n = (String) s.getAttribute("userName");
		if (n == null) {
			res.sendError(400);
		}
	}
	
	
	/// verifica los permisos del usuario ya autentificado
	
	public static boolean verificarPermiso(HttpServletRequest request, UsuarioDao usuarioDao, PerfilDAO  perfildao, int per){
		HttpSession s = request.getSession();
		String nombreUsuario = (String) s.getAttribute("userName");
		if(nombreUsuario == null){
			return false;
		}else{
			Usuario usuario = usuarioDao.consultarUsuario(nombreUsuario);
			Perfil perfil = perfildao.consultarPerfil(usuario.getPerfil());
			if(perfil.getPermisos()[per]==true){
				return true;
			}
		}
		return false;
	}
	
}

