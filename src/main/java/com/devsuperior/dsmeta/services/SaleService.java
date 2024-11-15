package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SaleProjection;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}
	
	
	public Page <SaleMinDTO> getReport(LocalDate dataInicial, LocalDate dataFinal, String name, Pageable pageable) {	
		if(dataFinal == null) {
			LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		}
		if(dataInicial == null) {	
			dataInicial = LocalDate.now().minusYears(1);
		}
			
		return  repository.search(dataInicial, dataFinal, name, pageable);
	}
	
	
	
	public Page <SaleSummaryDTO> getSummary (LocalDate dataInicial, LocalDate dataFinal, Pageable pageable ){
		if(dataFinal == null) {
			LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		}
		if(dataInicial == null) {	
			dataInicial = LocalDate.now().minusYears(1);
		}
		
		Page<SaleProjection> result = repository.search2(dataInicial, dataFinal, pageable);
		return result.map(SaleSummaryDTO :: new);
	
	}
	
	
	
	//metodo feito para teste
	public Page <SaleMinDTO> findAll(Pageable pageable) {
		Page<Sale> result = repository.findAll(pageable);
		return result.map(x-> new SaleMinDTO(x));
	}
	
	
	
	
	
}
