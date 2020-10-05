package com.financas.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.financas.api.filter.PersonFilter;
import com.financas.api.model.Person;
import com.financas.api.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	PersonRepository repository;

	public Page<Person> list(PersonFilter filter, Pageable pageable) {
		return repository.findPersonByName(filter, pageable);
	}

	public Optional<Person> get(Long id) {
		return repository.findById(id);
	}

	public Person create(Person person) {
		return repository.save(person);
	}

	public void delete(Long id) {
		repository.deleteById(id);
		
	}

	public Person update(Long id, Person person) {
		
		Person personUpdate = findPersonById(id);
		BeanUtils.copyProperties(person, personUpdate, "id");
		repository.save(personUpdate);
		
		return personUpdate;		
	}

	public void updateActive(Long id, Boolean active) {
		Person personUpdate = findPersonById(id);
		
		personUpdate.setActive(active);
		repository.save(personUpdate);

	}


	private Person findPersonById(Long id) {
		
		return repository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
		
	}
	
}
