package com.tikal.aeronautikal.dao.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.tikal.aeronautikal.dao.UsuarioDao;
import com.tikal.aeronautikal.entity.Usuario;


//@Repository
@Service("usuarioDao")  ///esta se la puse yo
public class UsuarioDaoImpl implements UsuarioDao{
	
	@Override
	public void add(Usuario usuario) {
		ofy().save().entity(usuario);

	}

	@Override
	public void update(Usuario usuario) {
		ofy().save().entity(usuario);

	}

	@Override
	public Usuario consult(String id) {
		return ofy().load().type(Usuario.class).id(id).now();
	}

	@Override
	public void delete(Usuario usuario) {
		ofy().delete().entity(usuario);

	}


	@Override
	public boolean crearUsuario(Usuario usuario) {
//		System.out.println(this.consultarPorEmail(usuario.getEmail()).getEmail()+"PRIMER IF");
//		System.out.println(this.consultarUsuario(usuario.getUsername())+"SEGUNDO IF");

		//if(this.consultarPorEmail(usuario.getEmail()).getEmail()!=null){
			//return false;
		//}
		if (this.consultarUsuario(usuario.getUsername())==null) {
			ofy().save().entity(usuario).now();
		} else {
			return false;
		}

		return true;
	}

	@Override
	public boolean eliminarUsuario(String usuario) {
		ofy().delete().entities(this.consultarUsuario(usuario)).now();
		return true;
	}

	@Override
	public Usuario consultarUsuario(String usuario) {
		List<Usuario> usu = ofy().load().type(Usuario.class).filter("usuario", usuario).list();
		System.out.println("lista de usuarios con ese username:"+usu);
		if (usu.size() == 0) {
			return null;
		}
		Usuario nuevo = usu.get(0);
		return nuevo;
	}

	@Override
	public List<Usuario> consultarUsuarios() {

		return ofy().load().type(Usuario.class).list();
	}

	@Override
	public boolean actualizarUsuario(Usuario usuario) {
		ofy().save().entity(usuario).now();
		return true;
	}

	@Override
	public boolean eliminarUsuario(Usuario usuario) {
		ofy().delete().entity(usuario).now();
		return true;
	}

	@Override
	public boolean actualizarUsuarios(String nombrePerfilviejo, String nombrePerfilNuevo) {
		List<Usuario> lista =  ofy().load().type(Usuario.class).filter("perfil", nombrePerfilviejo).list();
		for(int i = 0; i < lista.size(); i++){
			lista.get(i).setPerfil(nombrePerfilNuevo);
		}
		ofy().save().entities(lista).now();
		return true;
	}

	@Override
	public Usuario consultarPorEmail(String email) {
		List<Usuario> lista = ofy().load().type(Usuario.class).filter("email", email).list();	
//		System.out.println("Tama�o de la lista: " + lista.size());
		Usuario usuario = new Usuario();
		if(lista.size()>0){
			return lista.get(0);
		}
		return usuario;	
	}

}



