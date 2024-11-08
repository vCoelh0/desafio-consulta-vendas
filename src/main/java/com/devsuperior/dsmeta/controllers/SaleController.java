package com.devsuperior.dsmeta.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleMinDTO>> getReport(
	        @RequestParam(name = "dataInicial", required = false) 
	        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
	        
	        @RequestParam(name = "dataFinal", required = false) 
	        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal,
	        
	        @RequestParam(name = "name", defaultValue = "", required = false) String name,
	        Pageable pageable) {

	    Page<SaleMinDTO> dto = service.getReport(dataInicial, dataFinal, name, pageable);
	    return ResponseEntity.ok(dto);
	}

	//metodo feito para teste
	@GetMapping(value = "/reports")
	public ResponseEntity<Page<SaleMinDTO>> findAll(Pageable pageable) {
	Page<SaleMinDTO> dto = service.findAll(pageable);
	return ResponseEntity.ok(dto);
	}
	
	
	
	
	
		
	@GetMapping(value = "/summary")
	public ResponseEntity<?> getSummary() {
		// TODO
		return null;
	}
}
