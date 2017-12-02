package com.tikal.aeronautikal.model;

public class Contador {
	private Long id;
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
