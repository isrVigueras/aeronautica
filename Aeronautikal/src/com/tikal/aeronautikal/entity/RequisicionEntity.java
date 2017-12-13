package com.tikal.aeronautikal.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
@Entity
public class RequisicionEntity {
		
		@Id Long folio;
		@Index Long folio_componente;
		Long folio_discrepancia;
		Integer numero_piezas;
		//String d_componente;
		String fechaApertura;
		@Index String estatus;
		public Long getFolio() {
			return folio;
		}
		public void setFolio(Long folio) {
			this.folio = folio;
		}
		public Long getFolio_componente() {
			return folio_componente;
		}
		public void setFolio_componente(Long folio_componente) {
			this.folio_componente = folio_componente;
		}
		public Long getFolio_discrepancia() {
			return folio_discrepancia;
		}
		public void setFolio_discrepancia(Long folio_discrepancia) {
			this.folio_discrepancia = folio_discrepancia;
		}
		public Integer getNumero_piezas() {
			return numero_piezas;
		}
		public void setNumero_piezas(Integer numero_piezas) {
			this.numero_piezas = numero_piezas;
		}
		public String getFechaApertura() {
			return fechaApertura;
		}
		public void setFechaApertura(String fechaApertura) {
			this.fechaApertura = fechaApertura;
		}
		public String getEstatus() {
			return estatus;
		}
		public void setEstatus(String estatus) {
			this.estatus = estatus;
		}
		
		
		
		
		

}
