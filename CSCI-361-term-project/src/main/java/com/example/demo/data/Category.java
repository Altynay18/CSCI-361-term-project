package com.example.demo.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category {
	
	@Id
	private String category;
	
	private Double discount;
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discout) {
		this.discount = discout;
	}

}
