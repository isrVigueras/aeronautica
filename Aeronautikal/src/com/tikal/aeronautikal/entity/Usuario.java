package com.tikal.aeronautikal.entity;
//import java.util.List;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Usuario implements UserDetails{
	
	@Id Long id;
	@Index String usuario;
	private String password;
	private List<Rol> authorities;
	@Index private String perfil;    //nombre del perfil
	//@Index private Long idPerfil;
	@Index private String email;


	@Override
	public List<Rol> getAuthorities() {
		return this.authorities;
	}
	
	public List<Rol> setAuthorities(List<Rol> authorities){
		return this.authorities = authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}
	public void resetPassword(){
		this.password="";
	}

	
	public void setUsername(String username){
		this.usuario = username;
	}
	
	@Override
	public String getUsername() {
		return this.usuario;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public boolean hasRole(String rol){
		for(Rol r:this.getAuthorities()){
			if(r.getName().compareTo(rol)==0){
				return true;
			}
		}
		return false;

	}
	
	

	public String getEmail() {
		return email;
	}

	

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

//	public Long getIdPerfil() {
//		return idPerfil;
//	}
//
//	public void setIdPerfil(Long idPerfil) {
//		this.idPerfil = idPerfil;
//	}

	public void setEmail(String email) {
		this.email = email;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
}

