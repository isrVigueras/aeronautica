package com.tikal.aeronautikal.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Contador {
	 @Id private Long id;
	private static Long folio;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public static Long getFolio() {
		if (folio == null)
			folio=Long.parseLong("1");
		return folio;
	}
	public static void setFolio(Long folio) {
		Contador.folio = folio;
	}
	
	public static Long incremeta(){
		
		Contador.folio=getFolio()+1;
		return folio;
	}
	
	
}
