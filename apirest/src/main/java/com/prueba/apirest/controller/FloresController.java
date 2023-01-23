package com.prueba.apirest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.apirest.dto.FloresDto;
import com.prueba.apirest.dto.FloresDtoID;
import com.prueba.apirest.entity.Flores;
import com.prueba.apirest.service.FloresService;

@RestController
@RequestMapping("/api")
public class FloresController {

	 @Autowired
	 private FloresService floresService;
	 
	 
	 @GetMapping("/flores")
	    public List<FloresDtoID> findAll(@RequestParam(required = false) String pName, @RequestParam (required = false) Double pPrice  ){
	        if(pName!=null) {
	        	return floresService.findAllByName(pName);
	        }
	        else if(pPrice !=null) {
	        	return floresService.findAllByPrice(pPrice);
	        }
	        else {
	        	throw new RuntimeException("No es una ruta válida");
	        }
	  }
	 
	 @GetMapping("/floreskometsales")
	    public List<FloresDto> findAllkometsales(){
	        return floresService.findAll();
	  }
	 
	 @PostMapping("/flores")
	    public List<FloresDtoID> addFlores(@RequestBody List<FloresDtoID> flores) {
		 	floresService.save(flores);
		 	return flores;

	    }
	 @DeleteMapping("flores/{floresId}")
	 public String deteteFlor(@PathVariable  int floresId) {
		 FloresDto flor = floresService.findById(floresId);
	        if(flor == null) {
	            throw new RuntimeException("User id not found - "+floresId);
	        }
		 floresService.deleteById(floresId);
		 return "Deleted flor id - "+floresId;
	 }
}
