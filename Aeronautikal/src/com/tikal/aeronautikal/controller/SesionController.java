package com.tikal.aeronautikal.controller;

import java.io.IOException;
//import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tikal.aeronautikal.dao.PerfilDAO;
import com.tikal.aeronautikal.dao.UsuarioDao;
import com.tikal.aeronautikal.entity.DiscrepanciaEntity;
import com.tikal.aeronautikal.entity.Perfil;
import com.tikal.aeronautikal.entity.Usuario;
import com.tikal.aeronautikal.util.AsignadorDeCharset;
import com.tikal.aeronautikal.util.JsonConvertidor;

@SessionAttributes({"usuario"})
@Controller

public class SesionController {

	@Autowired
	UsuarioDao usuarioDao;  // para la autentificacion
	
	////////////////////// checar si el usuario es válido

	@RequestMapping(value = { "/user" }, method = RequestMethod.POST, consumes="application/json", produces = "application/json")
	public void user(HttpServletResponse res, HttpServletRequest req, @RequestBody String json) throws IOException {

		AsignadorDeCharset.asignar(req, res);
		System.out.println("usuario edgar "+json);
		Usuario usuarioFront = (Usuario) JsonConvertidor.fromJson(json, Usuario.class);
		if (usuarioFront.getUsername().isEmpty() || usuarioFront.getPassword().isEmpty()){
			System.out.println("username vacio:"+usuarioFront.getUsername());
			System.out.println("Error 403 , usuario no autentificado");
			res.sendError(403);
		}else{
				System.out.println("username json:"+usuarioFront.getUsername());
				Usuario usuBase= usuarioDao.consultarUsuario(usuarioFront.getUsername());
				System.out.println("obeto usuario de bck:"+usuBase);
				if(usuBase==null){
					System.out.println("username vacio:"+usuarioFront.getUsername());
					System.out.println("Error 403 , usuario no autentificado");
					res.sendError(403);
				}else{
						String p= usuarioFront.getPassword(); 
						
						System.out.println("password front:"+p);
						System.out.println("password back:"+usuBase.getPassword());		
						String passfrontCryp= UsuarioController.otroMetodo(p);
						System.out.println("password encriptado front:"+passfrontCryp);
						
						if (usuBase.getPassword().equals(passfrontCryp) == false) {
							System.out.println("Error 403 , usuario no autentificado");
							res.sendError(403);
							 
						} else {
							System.out.println(" usuario Valido");
							
							//usuario.resetPassword();
							//req.getSession().setAttribute("userName", usuarioFront.getUsername());
							HttpSession s = req.getSession();
							
							System.out.println(" ------ sesion:"+s);
							System.out.println(" ------id:"+s.getId());
							req.setAttribute("usuario", usuarioFront.getUsername());
							System.out.println("req:::::"+req.getAttribute("usuario"));
							
							res.getWriter().println(JsonConvertidor.toJson(usuarioFront));
						}
				}
		}
	}

	
	@RequestMapping(value = { "/user" }, method = RequestMethod.GET, consumes="application/json", produces = "application/json")
	public void userg(HttpServletResponse res, HttpServletRequest req, @RequestBody String json) throws IOException {

		AsignadorDeCharset.asignar(req, res);
		System.out.println("usuario edgar "+json);
		Usuario usuarioFront = (Usuario) JsonConvertidor.fromJson(json, Usuario.class);
		if (usuarioFront.getUsername().isEmpty() || usuarioFront.getPassword().isEmpty()){
			System.out.println("username vacio:"+usuarioFront.getUsername());
			System.out.println("Error 403 , usuario no autentificado");
			res.sendError(403);
		}else{
				System.out.println("username json:"+usuarioFront.getUsername());
				Usuario usuBase= usuarioDao.consultarUsuario(usuarioFront.getUsername());
				System.out.println("obeto usuario de bck:"+usuBase);
				if(usuBase==null){
					System.out.println("username vacio:"+usuarioFront.getUsername());
					System.out.println("Error 403 , usuario no autentificado");
					res.sendError(403);
				}else{
						String p= usuarioFront.getPassword(); 
						
						System.out.println("password front:"+p);
						System.out.println("password back:"+usuBase.getPassword());		
						String passfrontCryp= UsuarioController.otroMetodo(p);
						System.out.println("password encriptado front:"+passfrontCryp);
						
						if (usuBase.getPassword().equals(passfrontCryp) == false) {
							System.out.println("Error 403 , usuario no autentificado");
							res.sendError(403);
							 
						} else {
							System.out.println(" usuario Valido");
							
							//usuario.resetPassword();
							//req.getSession().setAttribute("userName", usuarioFront.getUsername());
							HttpSession s = req.getSession();
							
							System.out.println(" ------ sesion:"+s);
							System.out.println(" ------id:"+s.getId());
							req.setAttribute("usuario", usuarioFront.getUsername());
							System.out.println("req:::::"+req.getAttribute("usuario"));
							
							res.getWriter().println(JsonConvertidor.toJson(usuarioFront));
						}
				}
		}
	}

  
	
	@RequestMapping(value = { "/currentSession" }, method = RequestMethod.GET, produces = "application/json")
	public void currentUser(HttpServletResponse res, HttpServletRequest req) throws IOException, ServletException {
		System.out.println("currentSession:urtenticate::"+req.authenticate(res));
		HttpSession s = req.getSession();
		System.out.println(" ------ sesion:"+s);
		String n = (String) s.getAttribute("usuario");
		
		System.out.println(" atributo usuario de la sesion:"+req.getAttribute("usuario"));
		if (n == null) {
			res.sendError(400);
		}
	}
	
	
	/// verifica los permisos del usuario ya autentificado
	
	public static boolean verificarPermiso(HttpServletRequest request, UsuarioDao usuarioDao, PerfilDAO  perfildao, int per){
		HttpSession s = request.getSession();
		String nombreUsuario = (String) s.getAttribute("userName");
		System.out.println(" ------ usuario:"+nombreUsuario);
		if(nombreUsuario == null){
			System.out.println(" *****************usuario con permisos*******");
			return false;
		}else{
			System.out.println(" ----------------usuario SIN PERMISOS-----------");
			Usuario usuario = usuarioDao.consultarUsuario(nombreUsuario);
			Perfil perfil = perfildao.consultarPerfil(usuario.getPerfil());
			if(perfil.getPermisos()[per]==true){
				return true;
			}
		}
		return false;
	}
	
}

