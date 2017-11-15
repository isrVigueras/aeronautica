package com.tikal.aeronautikal.util;

import org.springframework.stereotype.Component;

import com.googlecode.objectify.ObjectifyService;
import com.tikal.aeronautikal.entity.AeronaveEntity;

@Component
public class StartupEntities {
	
	public StartupEntities() {
		ObjectifyService.register(AeronaveEntity.class);
	}
	
    
        //ObjectifyService.register(Motorcycle.class);
    
}
