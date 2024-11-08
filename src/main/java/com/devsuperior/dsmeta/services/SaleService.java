package com.devsuperior.dsmeta.services;

import java.time.LocalDate;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
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
		Page<Sale>result = repository.search(dataInicial, dataFinal, name, pageable);
		return result.map(x -> new SaleMinDTO(x));
	}
	
	
	//metodo feito para teste
	public Page <SaleMinDTO> findAll(Pageable pageable) {
		Page<Sale> result = repository.findAll(pageable);
		return result.map(x-> new SaleMinDTO(x));
	}
	
	
	
	
	
}
