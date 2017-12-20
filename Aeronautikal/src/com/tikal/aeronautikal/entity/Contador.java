package com.tikal.aeronautikal.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Contador {
	 @Id private Long id;
	private static Long folio;
	private static Long folioEvento;
	private static Long idDiscrepancia;
	
	
	public static Long getIdDiscrepancia() {
		return idDiscrepancia;
	}
	public static void setIdDiscrepancia(Long idDiscrepancia) {
		Contador.idDiscrepancia = idDiscrepancia;
	}
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
	
	
	public static Long getFolioEvento() {
		if (folioEvento == null || folioEvento==0)
			folioEvento=Long.parseLong("1");
		return folioEvento;
	}
	public static void setFolioEvento(Long folioEvento) {
		Contador.folioEvento = folioEvento;
	}
	public static Long incremeta(){
		
		Contador.folio=getFolio()+1;
		return folio;
	}
	
	public static void  reinicia(){
		Contador.setFolio(Long.parseLong("0"));
	}
	
	public static Long incrementaE(){
		Contador.folioEvento=getFolioEvento()+1;
		return folioEvento;
	}
	
	public static void reiniciaE(){
		Contador.setFolioEvento(Long.parseLong("0"));
	}
}
