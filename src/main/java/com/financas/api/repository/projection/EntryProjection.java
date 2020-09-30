package com.financas.api.repository.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.financas.api.model.enums.TypeEntry;

public class EntryProjection {
	
	private Long id;
	private String description;
	private LocalDate expirationDate;
	private LocalDate payDate;
	private BigDecimal value;
	private TypeEntry type;
	private String category;
	private String person;
		
	public EntryProjection(Long id, String description, LocalDate expirationDate, LocalDate payDate, BigDecimal value,
			TypeEntry type, String category, String person) {
		this.id = id;
		this.description = description;
		this.expirationDate = expirationDate;
		this.payDate = payDate;
		this.value = value;
		this.type = type;
		this.category = category;
		this.person = person;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}
	public LocalDate getPayDate() {
		return payDate;
	}
	public void setPayDate(LocalDate payDate) {
		this.payDate = payDate;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public TypeEntry getType() {
		return type;
	}
	public void setType(TypeEntry type) {
		this.type = type;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	

}
