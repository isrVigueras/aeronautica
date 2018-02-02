package com.tikal.aeronautikal.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tikal.aeronautikal.dao.impl.SesionDaoImp;
import com.tikal.aeronautikal.entity.Usuario;



@Service
public class CustomUsuarioServicio implements UserDetailsService  {
	
	@Autowired
    private SesionDaoImp userDao;
    
    
   public Usuario loadUserByUsername(String usuario) throws UsernameNotFoundException {
       return userDao.consultarUsuario(usuario);
   }

}
