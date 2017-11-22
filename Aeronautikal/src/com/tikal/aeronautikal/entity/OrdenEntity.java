package com.tikal.aeronautikal.entity;

import java.util.Calendar;
import java.util.List;


import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.tikal.aeronautikal.model.Aeronave;
import com.tikal.aeronautikal.model.Contacto;
import com.tikal.aeronautikal.model.Empresa;
import com.tikal.aeronautikal.model.otBody.Discrepancia;

@Entity

public class OrdenEntity implements BaseEntity{
	
	  
	    @Id private long folio;
	    @Index private Empresa laEmpresa;
	    @Index private Aeronave elAeronave;
		private Contacto datosContacto;		
		private Calendar fechaApertura;
		private String condiciones;
		private List<Discrepancia> discrepancias;
	
		//SortedSet<Discrepancia> discrepancias = new TreeSet<>(new TreadRemainingComparator()); es para inicializar el 
		
		
		public long getFolio() {
			return folio;
		}
		public void setFolio(long folio) {
			this.folio = folio;
		}
		public Empresa getLaEmpresa() {
			return laEmpresa;
		}
		public void setLaEmpresa(Empresa laEmpresa) {
			this.laEmpresa = laEmpresa;
		}
		public Aeronave getElAeronave() {
			return elAeronave;
		}
		public void setElAeronave(Aeronave elAeronave) {
			this.elAeronave = elAeronave;
		}
		public Contacto getDatosContacto() {
			return datosContacto;
		}
		public void setDatosContacto(Contacto datosContacto) {
			this.datosContacto = datosContacto;
		}
		public Calendar getFechaApertura() {
			return fechaApertura;
		}
		public void setFechaApertura(Calendar fechaApertura) {
			this.fechaApertura = fechaApertura;
		}
		public String getCondiciones() {
			return condiciones;
		}
		public void setCondiciones(String condiciones) {
			this.condiciones = condiciones;
		}
		public List<Discrepancia> getDiscrepancias() {
			return discrepancias;
		}
		public void setDiscrepancias(List<Discrepancia> discrepancias) {
			this.discrepancias = discrepancias;
		}

	    

}
