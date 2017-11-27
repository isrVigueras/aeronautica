package com.tikal.aeronautikal.controller.vo;

import com.tikal.aeronautikal.entity.OrdenEntity;

public class EmpresaVo {
	
		private OrdenEntity orden;
		private String nickname;
		public OrdenEntity getOrden() {
			return orden;
		}
		public void setOrden(OrdenEntity orden) {
			this.orden = orden;
		}
		public String getNickname() {
			return nickname;
		}
		public void setNickname(String nickname) {
			this.nickname = nickname;
		}
		
		
}
