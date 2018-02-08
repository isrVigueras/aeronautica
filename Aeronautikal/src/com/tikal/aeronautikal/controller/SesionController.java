package com.tikal.aeronautikal.controller;

import java.io.IOException;
//import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tikal.aeronautikal.dao.PerfilDAO;
import com.tikal.aeronautikal.dao.UsuarioDao;
import com.tikal.aeronautikal.entity.DiscrepanciaEntity;
import com.tikal.aeronautikal.entity.Perfil;
import com.tikal.aeronautikal.entity.Usuario;
import com.tikal.aeronautikal.util.AsignadorDeCharset;
import com.tikal.aeronautikal.util.JsonConvertidor;


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
		System.out.println("username json:"+usuarioFront.getUsername());
		Usuario usuBase= usuarioDao.consultarUsuario(usuarioFront.getUsername());
		System.out.println("obeto usuario de bck:"+usuBase);

		String p= usuarioFront.getPassword(); 
		
		//String username= usuBase.getUsername() ;
		// Verificar que el usuario y contraseña coincidan
		//System.out.println("username:"+username);
		System.out.println("password front:"+p);
		//System.out.println("obeto usuario de bck:"+usuBase);
		System.out.println("password back:"+usuBase.getPassword());		
		String passfrontCryp= UsuarioController.otroMetodo(p);
		System.out.println("password encriptado front:"+passfrontCryp);
		
		if ((usuBase == null ) || (usuBase.getPassword().equals(passfrontCryp) == false)) {
			System.out.println("Error 403 , usuario no autentificado");
			res.sendError(403);
			
		} else {
			System.out.println(" usuario Valido");
			
			//usuario.resetPassword();
			//req.getSession().setAttribute("userName", usuario.getUsername());
			req.setAttribute("usuario", usuarioFront.getUsername());
			//System.out.println("req:::::"+req.getAttribute("usuario"));
			
			res.getWriter().println(JsonConvertidor.toJson(usuarioFront));
		}
	}

  
	
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

