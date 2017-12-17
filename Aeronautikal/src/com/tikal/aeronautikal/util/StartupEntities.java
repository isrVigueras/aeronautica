package com.tikal.aeronautikal.util;

import org.springframework.stereotype.Component;

import com.googlecode.objectify.ObjectifyService;
import com.tikal.aeronautikal.controller.vo.OrdenVo;
import com.tikal.aeronautikal.controller.vo.RequisicionesComponenteVo;
import com.tikal.aeronautikal.entity.AeronaveEntity;
import com.tikal.aeronautikal.entity.ContactoEntity;
import com.tikal.aeronautikal.entity.Contador;
import com.tikal.aeronautikal.entity.DatosFacturacionEntity;
import com.tikal.aeronautikal.entity.DiscrepanciaEntity;
import com.tikal.aeronautikal.entity.EmpleadoEntity;
import com.tikal.aeronautikal.entity.EmpresaEntity;
import com.tikal.aeronautikal.entity.OrdenEntity;
import com.tikal.aeronautikal.entity.RequisicionEntity;
import com.tikal.aeronautikal.entity.otBody.ComponenteEntity;

@Component
public class StartupEntities {
	
	
	public StartupEntities() {
		
		ObjectifyService.register(AeronaveEntity.class);
		ObjectifyService.register(OrdenEntity.class);
		ObjectifyService.register(OrdenVo.class);
		ObjectifyService.register(ContactoEntity.class);
		ObjectifyService.register(DatosFacturacionEntity.class);
		ObjectifyService.register(DiscrepanciaEntity.class);
		ObjectifyService.register(EmpresaEntity.class);
		ObjectifyService.register(EmpleadoEntity.class);
		ObjectifyService.register(ComponenteEntity.class);
		ObjectifyService.register(Contador.class);
		ObjectifyService.register(RequisicionEntity.class);
		ObjectifyService.register(RequisicionesComponenteVo.class);
		
		
	}
	
    
        //ObjectifyService.register(Motorcycle.class);
    
}
