package com.tikal.aeronautikal.controller.vo;

import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class OrdenXlsVo {
	
	@Id Long id;
	private String nombreArchivo;
	private String fechaOrden;
	private String nombreEmpresa;
	private String folioOrden;
	private String marcaAeronave;
	private String modeloAeronave;
	private String numeroSerie;
	private String matricula;
	private String planeador;
	private String motor1;
	private String motor2;
	private String marcas;
	private List<String> accionesDiscrepancia;
	
	
	
	
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	public String getFechaOrden() {
		return fechaOrden;
	}
	public void setFechaOrden(String fecha) {
		this.fechaOrden = fecha;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	public String getFolioOrden() {
		return folioOrden;
	}
	public void setFolioOrden(String folioOrden) {
		this.folioOrden = folioOrden;
	}
	public String getMarcaAeronave() {
		return marcaAeronave;
	}
	public void setMarcaAeronave(String marcaAeronave) {
		this.marcaAeronave = marcaAeronave;
	}
	public String getModeloAeronave() {
		return modeloAeronave;
	}
	public void setModeloAeronave(String modeloAeronave) {
		this.modeloAeronave = modeloAeronave;
	}
	public String getNumeroSerie() {
		return numeroSerie;
	}
	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getPlaneador() {
		return planeador;
	}
	public void setPlaneador(String planeador) {
		this.planeador = planeador;
	}
	public String getMotor1() {
		return motor1;
	}
	public void setMotor1(String motor1) {
		this.motor1 = motor1;
	}
	public String getMotor2() {
		return motor2;
	}
	public void setMotor2(String motor2) {
		this.motor2 = motor2;
	}
	public String getMarcas() {
		return marcas;
	}
	public void setMarcas(String marcas) {
		this.marcas = marcas;
	}
	public List<String> getAccionesDiscrepancia() {
		return accionesDiscrepancia;
	}
	public void setAccionesDiscrepancia(List<String> accionesDiscrepancia) {
		this.accionesDiscrepancia = accionesDiscrepancia;
	}
	
	

}
