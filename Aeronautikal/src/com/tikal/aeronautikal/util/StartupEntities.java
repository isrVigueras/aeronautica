package com.tikal.aeronautikal.util;

import org.springframework.stereotype.Component;

import com.googlecode.objectify.ObjectifyService;
import com.tikal.aeronautikal.entity.AeronaveEntity;
import com.tikal.aeronautikal.entity.ContactoEntity;
import com.tikal.aeronautikal.entity.DatosFacturacionEntity;
import com.tikal.aeronautikal.entity.DiscrepanciaEntity;
import com.tikal.aeronautikal.entity.EmpleadoEntity;
import com.tikal.aeronautikal.entity.EmpresaEntity;
import com.tikal.aeronautikal.entity.OrdenEntity;

@Component
public class StartupEntities {
	
	public StartupEntities() {
		
		ObjectifyService.register(AeronaveEntity.class);
		ObjectifyService.register(OrdenEntity.class);
		ObjectifyService.register(ContactoEntity.class);
		ObjectifyService.register(DatosFacturacionEntity.class);
		ObjectifyService.register(DiscrepanciaEntity.class);
		ObjectifyService.register(EmpresaEntity.class);
		ObjectifyService.register(EmpleadoEntity.class);
	
	}
	
    
        //ObjectifyService.register(Motorcycle.class);
    
}
