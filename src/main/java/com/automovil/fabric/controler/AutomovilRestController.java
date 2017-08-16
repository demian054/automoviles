package com.automovil.fabric.controler;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.automovil.fabric.beans.Automovil;
import com.automovil.fabric.service.AutomovilService;


@RestController
public class AutomovilRestController {
	
	@Autowired
	private  AutomovilService automovilService;
	
	
	
	public void setAutomovilService(AutomovilService automovilService) {
		this.automovilService = automovilService;
		
	}
	
	@RequestMapping(value="/automovil/new", method=RequestMethod.POST)
	public ResponseEntity<Automovil> addAutomovil(@RequestBody Automovil automovil, UriComponentsBuilder ucb) {
		if (automovilService.existAutomovil(automovil)) {
			return new ResponseEntity<Automovil>(HttpStatus.CONFLICT);
		} else {
			HttpHeaders headers = new HttpHeaders();
			if (automovilService.saveAutomovil(automovil)) {
				headers.setLocation(ucb.path("/automovil/{id}").buildAndExpand(automovil.getId()).toUri());
				return new ResponseEntity<Automovil>(HttpStatus.CREATED);
			} else {
				return new ResponseEntity<Automovil>(HttpStatus.EXPECTATION_FAILED);
			}
			
		} 

	}
	
	
	@RequestMapping(value="/automovil/{id}", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Automovil> getAutomovil(@PathVariable("id") int id) {
		
		Automovil automovil = automovilService.getById(id);
		if (automovil == null) {
			return new ResponseEntity<Automovil>(HttpStatus.NOT_FOUND);
		}			
		return new ResponseEntity<Automovil>(automovil, HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value="/automoviles", method=RequestMethod.GET)
	public ResponseEntity<List<Automovil>> getAutomoviles() {
		
		List<Automovil> automoviles = automovilService.findAll();
		if (automoviles.isEmpty()) {
			return new ResponseEntity<List<Automovil>>(HttpStatus.NO_CONTENT);
		}			
		return new ResponseEntity<List<Automovil>>(automoviles, HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value="/automovil", method=RequestMethod.PUT)
	public ResponseEntity<Automovil> updateAutomovil(@RequestBody Automovil reqAutomovil) {

		if (automovilService.getById(reqAutomovil.getId()) == null) {
			return new ResponseEntity<Automovil>(HttpStatus.NOT_FOUND);
		}			
		
		//automovil.setNombre(reqAutomovil.getNombre());
		if (automovilService.updateAutomovil(reqAutomovil)) {
			return new ResponseEntity<Automovil>(reqAutomovil, HttpStatus.OK);
		} else {
			return new ResponseEntity<Automovil>(HttpStatus.EXPECTATION_FAILED);
		}
		
		
	}
	
	@RequestMapping(value="/automovil/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Automovil> deleteAutomovil(@PathVariable("id") int id) {
		
		Automovil automovil = automovilService.getById(id);
		if (automovil == null) {
			return new ResponseEntity<Automovil>(HttpStatus.NOT_FOUND);
		}			
		
		if (automovilService.deleteAutomovil(automovil)) {
			return new ResponseEntity<Automovil>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Automovil>(HttpStatus.EXPECTATION_FAILED);
		}
		
	}
	
	
	
	
	
	
	

}
