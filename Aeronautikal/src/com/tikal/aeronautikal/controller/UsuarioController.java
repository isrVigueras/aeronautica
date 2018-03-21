package com.tikal.aeronautikal.controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tikal.aeronautikal.dao.PerfilDAO;
import com.tikal.aeronautikal.dao.PuestoDao;
import com.tikal.aeronautikal.dao.SessionDao;
import com.tikal.aeronautikal.dao.UsuarioDao;
import com.tikal.aeronautikal.entity.Perfil;
import com.tikal.aeronautikal.entity.PuestoEntity;
import com.tikal.aeronautikal.entity.Usuario;
import com.tikal.aeronautikal.util.AsignadorDeCharset;
import com.tikal.aeronautikal.util.JsonConvertidor;
import com.tikal.aeronautikal.util.Util;


@SessionAttributes({"usuario"})
@Controller
@RequestMapping(value = { "/usuario" })
public class UsuarioController {

	@Autowired
	@Qualifier ("usuarioDao")
	UsuarioDao usuarioDao;
	
	@Autowired
	@Qualifier ("sessionDao")
	SessionDao sessionDao;

	
	@Autowired
	PerfilDAO perfilDAO; 
	
	@Autowired
	@Qualifier ("puestoDao")
	PuestoDao puestoDao;
																	///////////alta de usuarios
	@RequestMapping(value = { "/registro/{userName}" }, method = RequestMethod.POST, consumes = "Application/Json")
	public void crearUsuario(HttpServletRequest request, HttpServletResponse response, @RequestBody String json, @PathVariable String userName)
			throws IOException {
		if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 48, sessionDao,userName)){
			AsignadorDeCharset.asignar(request, response);
			System.out.println("usuario edgar "+json);
			Usuario usuario = (Usuario) JsonConvertidor.fromJson(json, Usuario.class);
			System.out.println("usuari password: "+usuario.getPassword());
			usuario.setPassword(UsuarioController.otroMetodo(usuario.getPassword()));
			System.out.println("usuario, idpuesto: "+usuario.getIdPuesto());
			usuario.setNombrePuesto(puestoDao.consult(usuario.getIdPuesto()).getDescripcion());
			
			//if (usuario.getUsername() == null || usuario.getPassword() == null || usuario.getEmail() == null) {
			if (usuario.getUsername() == null || usuario.getPassword() == null ) {
				response.sendError(400);
			} else {
				// System.out.println(usuario.getUsername()+"YYYYYYYYYYYYYy");
				if (!usuarioDao.crearUsuario(usuario)) {
					response.sendError(400);
				}
			}
		}else{
			response.sendError(403);
		}
	}

	@RequestMapping(value = { "/getAll" }, method = RequestMethod.GET, produces = "application/json")
	public void consultarUsuarios(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//if(SesionController.verificarPermiso(request, usuarioDao, perfilDAO, 8)){
			AsignadorDeCharset.asignar(request, response);
			List<Usuario> lista = usuarioDao.consultarUsuarios();
			response.getWriter().println(JsonConvertidor.toJson(lista));
	////	}else{
		//	response.sendError(403);
		//}
	}

	@RequestMapping(value = { "/update/{userName}" }, method = RequestMethod.POST, consumes = "Application/Json")
	public void actualizarUsuario(HttpServletRequest request, HttpServletResponse response, @RequestBody String json, @PathVariable String userName)
			throws IOException {
		if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 49, sessionDao,userName)){
			AsignadorDeCharset.asignar(request, response);
			Usuario usuario = (Usuario) JsonConvertidor.fromJson(json, Usuario.class);
			usuarioDao.actualizarUsuario(usuario);
		}else{
			response.sendError(403);
		}
	}

	@RequestMapping(value = { "/delete/{id}/{userName}" }, method = RequestMethod.POST)
	public void eliminarUsuario(HttpServletRequest request, HttpServletResponse response, @RequestBody String json,
			@PathVariable String userName, @PathVariable Long id )
			throws IOException {
		if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 50, sessionDao,userName)){
			AsignadorDeCharset.asignar(request, response);
			
			Usuario usuario = usuarioDao.consult(id);
			usuarioDao.eliminarUsuario(usuario);
			response.getWriter().println("ok");
		}else{
			response.sendError(403);
		}
	}

	@RequestMapping(value = { "/reset" }, method = RequestMethod.POST, consumes = "Application/Json")
	public void resetearPass(HttpServletRequest request, HttpServletResponse response, @RequestBody String email)
			throws IOException {
		AsignadorDeCharset.asignar(request, response);
		String correo = (String) JsonConvertidor.fromJson(email, String.class);
		Usuario usuario = usuarioDao.consultarPorEmail(correo);
		//System.out.println("Printf de UsuarioController = " + usuario.getUsername());
		String user= usuario.getUsername();
		//String mail= usuario.getEmail();
		if(usuario.getUsername()==null){
			response.sendError(400);
		}else{
			//EmailSender sender = new EmailSender();
			String nuevoPass = Util.randomString(10);
			//sender.enviaEmail(mail, user, nuevoPass);
			
			usuario.setPassword(UsuarioController.otroMetodo(nuevoPass));
			usuarioDao.actualizarUsuario(usuario);
			//System.out.println("Si mando el correo :*");
			
		}
	}
	
		static String otroMetodo(String cadena){
		String password = cadena;
		String algorithm = "SHA";
		
		byte[] plainText = password.getBytes();
		
		MessageDigest md = null;
		
		try {		
			md = MessageDigest.getInstance(algorithm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		md.reset();		
		md.update(plainText);
		byte[] encodedPassword = md.digest();
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < encodedPassword.length; i++) {
			if ((encodedPassword[i] & 0xff) < 0x10) {
				sb.append("0");
			}
			
			sb.append(Long.toString(encodedPassword[i] & 0xff, 16));
		}

//		System.out.println("Plain    : " + password);
//		System.out.println("Encrypted: " + sb.toString());
		return sb.toString();
	}
		
		  ///////////////////////////////////////verifica si la session del usuario esta activa
		@RequestMapping(value = {"/check"}, method = RequestMethod.GET)
		public void consultarSesion(HttpServletRequest request, HttpServletResponse response) throws IOException{
			HttpSession s = request.getSession();
			String user = (String)s.getAttribute("userName");
			if(user == null){
				response.sendError(403); 
			}
		}
		
		
		
		@RequestMapping(value = {"/cerrarSesion"}, method = RequestMethod.GET)
		public void cerrarSesion(HttpServletRequest request, HttpServletResponse response) throws IOException{
			
			HttpSession session = request.getSession(true);
			System.out.println("la SESSION trae... "+session);
			if (session != null) {
				System.out.println("Se cerrara la session.........");
			    session.invalidate();
			}
		}
		
		
		
		@RequestMapping(value = { "/SuperAdministrador" }, method = RequestMethod.GET)
		public void crearUsuarioUnico(HttpServletRequest request, HttpServletResponse response){
			
				Usuario usuario = new Usuario();
				//usuario.setEmail("root@root.com");
				usuario.setPassword(otroMetodo("root"));
				usuario.setPerfil("SuperAdministrador");
				usuario.setUsername("root");
				usuario.setNombre("ROOT");
				usuario.setaPaterno("Root");
				usuario.setaMaterno("Root");
				usuario.setIdPuesto(Long.parseLong("1111111111"));
				
				usuarioDao.crearUsuario(usuario);
				
				PuestoEntity puesto = new PuestoEntity();
				puesto.setId(Long.parseLong("1111111111"));
				puesto.setClave("Admin");
				puesto.setDescripcion("Administrador");
				
				puestoDao.save(puesto);
				
				
				Perfil perfil = new Perfil();
				perfil.setTipo("SuperAdministrador");
				boolean[] arreglo = new boolean[53];
				for(int i=0; i < arreglo.length; i++){
					arreglo[i] = true;
				}
				
				perfil.setPermisos(arreglo);
				perfilDAO.crearPerfil(perfil);
						
		}

}
