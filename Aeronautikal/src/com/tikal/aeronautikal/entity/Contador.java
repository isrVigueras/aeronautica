package com.tikal.aeronautikal.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Contador {
	 @Id private Long id;
	private static Long folio;
	private static Long folioDiscrepancia;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public static Long getFolio() {
		if (folio == null || folio==0)
			folio=Long.parseLong("1");
		return folio;
	}
	public static void setFolio(Long folio) {
		Contador.folio = folio;
	}
	
	public static Long getFolioDiscrepancia() {
		return folioDiscrepancia;
	}
	public static void setFolioDiscrepancia(Long folioDiscrepancia) {
		Contador.folioDiscrepancia = folioDiscrepancia;
	}
	public static Long incremeta(){
		
		Contador.folio=getFolio()+1;
		return folio;
	}
	
	public static void  reinicia(){
		Contador.setFolio(Long.parseLong("0"));
	}
}
