package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

	
	@Query(nativeQuery = true, value = """
		    SELECT 
		        s.ID AS id,
		        s.DATE AS date,
		        s.AMOUNT AS amount,
		        sl.NAME AS name 
		    FROM 
		        TB_SALES s 
		    JOIN  
		        TB_SELLER sl ON s.SELLER_ID = sl.ID 
		    WHERE 
		        (:dataInicial IS NULL OR s.DATE >= :dataInicial) 
		        AND (:dataFinal IS NULL OR s.DATE <= :dataFinal) 
		        AND (:name IS NULL OR sl.NAME LIKE CONCAT('%', :name, '%')) 
		    """)
		Page<Sale> search(
		        @Param("dataInicial") LocalDate dataInicial, 
		        @Param("dataFinal") LocalDate dataFinal, 
		        @Param("name") String name, 
		        Pageable pageable);


	}
