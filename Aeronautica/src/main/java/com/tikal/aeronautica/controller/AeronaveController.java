package com.tikal.aeronautica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tikal.aeronautica.dao.impl.AeronaveDaoImpl;

@Controller
@RequestMapping("/aeronave")


public class AeronaveController {
  AeronaveDaoImpl aeronaveDao;
  public void setaeronaveDao(AeronaveDaoImpl aeronaveDao) {
    this.aeronaveDao = aeronaveDao;
  }
}