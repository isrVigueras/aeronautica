package com.tikal.aeronautica.controller;


import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;
//import com.tikal.aeronautica.service.SelectionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
//import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value="/aeronave")
public class AeronaveController  {
	
  
	//private static final int RECORDS_PER_PAGE = 5;

//    @Autowired
//    private SelectionService selectionService;

//    static {
//        ObjectifyService.register(AeronaveEntity.class);
//    }
    
    //// EJEMPLO DE FUNCION CONSUMIENDO EL WEB SERVICE
   @RequestMapping(value={"/guarda"},method = RequestMethod.GET, consumes="application/json")
   public void guardar(HttpServletResponse response, HttpServletRequest request,@RequestBody String json) throws IOException {
	   response.getWriter().println("Prueba del mètodo guardar");

    }
    
    
//    @RequestMapping(method = RequestMethod.GET)
//    public String aeronave(ModelMap model) {
//        preparePagedModel(model, 1);
//        return "Hello word dummmmmmmyyyyyy aeronave";
//       
//    }
    
    
    
    
//    @RequestMapping(value = "/page/{matricula}", method = RequestMethod.GET)
//    public String aeronavePage(ModelMap model, @PathVariable("matricula") int matricula) {
//        preparePagedModel(model, matricula);
//        return "aeronave";
//    }
//    
//    private void preparePagedModel(ModelMap model, int index) {
//        model.put("currentIndex", index);
//        long totalRecords = selectionService.countAll(AeronaveEntity.class);
//        if (totalRecords < (index - 1) * RECORDS_PER_PAGE) {
//            // requested page doesn't exist
//            throw new ObjectNotFoundException("Requested page doesn't exist");
//        }
//        model.put("pages", Math.ceil(totalRecords / (float) RECORDS_PER_PAGE));
//        List<AeronaveEntity> aeronaveList = selectionService.listObjectByPage(AeronaveEntity.class, index,
//                RECORDS_PER_PAGE, "-date");
//        model.put("entries", aeronaveList);
//    }
//  
//    @RequestMapping(value = "/view/{modelo}", method = RequestMethod.GET)
//    public String aeronaveEntry(ModelMap model, @PathVariable("modelo") String modelo,
//                           @ModelAttribute("entry") AeronaveEntity entity, HttpServletRequest request) {
//        Map<String, Object> conditions = new HashMap<String, Object>();
//        conditions.put("modelo", modelo);
//        entity = selectionService.getUniqueEntity(AeronaveEntity.class, conditions);
//        model.put("entry", entity);
//        //model.put("backLink", request.getHeader("referer"));
//        //UserService userService = UserServiceFactory.getUserService();
//        //if (userService.getCurrentUser() != null && userService.isUserAdmin()) {
//        //    model.put("isAdmin", true);
//        //}
//        return "aeronaveEntry";
//    }
//    
//    
//    @RequestMapping(value = "/add", method = RequestMethod.GET)
//    public String addAeronaveGet(@ModelAttribute("entry") AeronaveEntity entry) {
//        return "addAeronave";
//    }
//    
//    
//    @RequestMapping(value = "/add", method = RequestMethod.POST)   //put matricula
//    public String addAeronavePost(ModelMap model, @ModelAttribute("entry") AeronaveEntity entry) {
//        try {
//            // server validation. Link should be unique
//            Map<String, Object> conditions = new HashMap<String, Object>();
//            conditions.put("matricula", entry.getMatricula().toLowerCase());
//            selectionService.getUniqueEntity(AeronaveEntity.class, conditions);
//            model.put("notUniqueMatricula", true);
//            return "addAeronave";
//        } catch (RuntimeException ignored) {
//            // getUniqueEntity should throw exception
//        }
//       
//        //entry.setDate(Calendar.getInstance().getTime());
//        selectionService.save(entry);
//        return "redirect:/aeronave";
//    }
//    
//    @RequestMapping(value = "/delete/{matricula}", method = RequestMethod.POST)
//    public String deleteAeronave(@PathVariable("matricula") String matricula) {
//        Map<String, Object> conditions = new HashMap<String, Object>();
//        conditions.put("matricula", matricula);
//        AeronaveEntity entity = selectionService.getUniqueEntity(AeronaveEntity.class, conditions);
//        selectionService.delete(entity);
//        return "redirect:/aeronave";
//    }
//    
//    @ExceptionHandler(ObjectNotFoundException.class)
//    public String error404() {
//        return "error";
//    }
    
	//public String main() {
    //    return "index";
   // }
  
  
  //public void setaeronaveDao(AeronaveDaoImpl aeronaveDao) {
  //  this.aeronaveDao = aeronaveDao;
  //}
}