package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SaleProjection;

public class SaleSummaryDTO {
	
	private String sellerName;
	private Double total;
	
	 public SaleSummaryDTO(SaleProjection projection) {
	        this.sellerName = projection.getSellerName();
	        this.total = projection.getTotal();
	    }

	public String getSellerName() {
		return sellerName;
	}

	public Double getTotal() {
		return total;
	}
	 
	 
}
