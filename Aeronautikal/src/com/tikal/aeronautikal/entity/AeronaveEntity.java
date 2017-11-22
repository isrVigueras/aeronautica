package com.tikal.aeronautikal.entity;

import com.googlecode.objectify.annotation.Container;
import  com.googlecode.objectify.annotation.Entity ;
import  com.googlecode.objectify.annotation.Id ;
import  com.googlecode.objectify.annotation.Index ;



@Entity 
public  class  AeronaveEntity implements BaseEntity{
    @Index  private String matricula;
    @Index private String modelo;
    @Id private long numeroSerie;
    private int aterrizaje;
    private int tiempovuelo;
    
	@Container OrdenEntity orden;
    
    
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getModelo() {
		return modelo;
	}
	public long getNumeroSerie() {
		return numeroSerie;
	}
	public void setNumeroSerie(long numeroSerie) {
		this.numeroSerie = numeroSerie;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public int getAterrizaje() {
		return aterrizaje;
	}
	public void setAterrizaje(int aterrizaje) {
		this.aterrizaje = aterrizaje;
	}
	public int getTiempovuelo() {
		return tiempovuelo;
	}
	public void setTiempovuelo(int tiempovuelo) {
		this.tiempovuelo = tiempovuelo;
	}
}


