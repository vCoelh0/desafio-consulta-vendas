package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SaleProjection;

public interface SaleRepository extends JpaRepository<Sale, Long> {

	@Query(value = """
		    SELECT new com.devsuperior.dsmeta.dto.SaleMinDTO(s.id, s.date, s.amount, sl.name)
		    FROM Sale s
		    JOIN s.seller sl
		    WHERE 
		        (:dataInicial IS NULL OR s.date >= :dataInicial) 
		        AND (:dataFinal IS NULL OR s.date <= :dataFinal) 
		        AND (:name IS NULL OR LOWER(sl.name) LIKE LOWER(CONCAT('%', :name, '%')))
		    """)
		Page<SaleMinDTO> search(
		        @Param("dataInicial") LocalDate dataInicial, 
		        @Param("dataFinal") LocalDate dataFinal, 
		        @Param("name") String name, 
		        Pageable pageable);
	
	
	@Query(nativeQuery = true, value = """
		    SELECT 
		        sl.NAME AS sellerName, 
		        SUM(s.AMOUNT) AS total 
		    FROM  
		        TB_SALES s 
		    JOIN  
		        TB_SELLER sl ON s.SELLER_ID = sl.ID 
		    WHERE 
		        (:dataInicial IS NULL OR s.DATE >= :dataInicial) 
		        AND (:dataFinal IS NULL OR s.DATE <= :dataFinal) 
		    GROUP BY 
		        sl.NAME 
		    ORDER BY  
		        total DESC
		""")
		Page<SaleProjection> search2(
		    @Param("dataInicial") LocalDate dataInicial, 
		    @Param("dataFinal") LocalDate dataFinal, 
		    Pageable pageable);

		
	
	
	
}



