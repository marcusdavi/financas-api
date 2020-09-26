package com.financas.api.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class EntryFilter {
	
	private String description;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate expirationDateInitial;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate expirationDateFinal;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getExpirationDateInitial() {
		return expirationDateInitial;
	}
	public void setExpirationDateInitial(LocalDate expirationDateInitial) {
		this.expirationDateInitial = expirationDateInitial;
	}
	public LocalDate getExpirationDateFinal() {
		return expirationDateFinal;
	}
	public void setExpirationDateFinal(LocalDate expirationDateFinal) {
		this.expirationDateFinal = expirationDateFinal;
	}
	
	
	

}
