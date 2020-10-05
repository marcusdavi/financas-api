package com.financas.api.repository.entry;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.financas.api.filter.PersonFilter;
import com.financas.api.model.Person;

public interface PersonRepositoryQuery {
	
	Page<Person> findPersonByName(PersonFilter filter, Pageable pageable);

}
