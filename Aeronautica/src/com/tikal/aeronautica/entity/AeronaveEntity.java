package com.tikal.aeronautica.entity;

import  com.googlecode.objectify.annotation.Entity ;
import  com.googlecode.objectify.annotation.Id ;
import  com.googlecode.objectify.annotation.Index ;



@Entity 
public  class  AeronaveEntity implements BaseEntity{
    @Id  private String matricula;
    @Index private String modelo;
    @Index private String numeroSerie;
    private int aterrizaje;
    private int tiempovuelo;
    
    
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getNumeroSerie() {
		return numeroSerie;
	}
	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
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


