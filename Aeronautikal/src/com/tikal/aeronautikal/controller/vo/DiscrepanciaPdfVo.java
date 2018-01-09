package com.tikal.aeronautikal.controller.vo;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class DiscrepanciaPdfVo {
	
	@Id Long id;
	private String empresa;
	private String folioOrden;
	private String marcaAeronave;
	private String modeloAeronave;
	private String numeroSerie;
	private String matricula;
	private String Descripcion;
	private String Accion;
	private String Componente;
	private String noParte;
	private String Cantidad;
	private String nsRemovida;
	private String nsInstalada;
	private String Observaciones;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
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
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	public String getAccion() {
		return Accion;
	}
	public void setAccion(String accion) {
		Accion = accion;
	}
	public String getComponente() {
		return Componente;
	}
	public void setComponente(String componente) {
		Componente = componente;
	}
	public String getNoParte() {
		return noParte;
	}
	public void setNoParte(String noParte) {
		this.noParte = noParte;
	}
	public String getCantidad() {
		return Cantidad;
	}
	public void setCantidad(String cantidad) {
		Cantidad = cantidad;
	}
	public String getNsRemovida() {
		return nsRemovida;
	}
	public void setNsRemovida(String nsRemovida) {
		this.nsRemovida = nsRemovida;
	}
	public String getNsInstalada() {
		return nsInstalada;
	}
	public void setNsInstalada(String nsInstalada) {
		this.nsInstalada = nsInstalada;
	}
	public String getObservaciones() {
		return Observaciones;
	}
	public void setObservaciones(String observaciones) {
		Observaciones = observaciones;
	}
	
	
	

}
