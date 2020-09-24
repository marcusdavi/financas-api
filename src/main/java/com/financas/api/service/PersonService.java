package com.financas.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.financas.api.model.Person;
import com.financas.api.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	PersonRepository repository;

	public List<Person> findAll() {
		return repository.findAll();
	}

	public Optional<Person> get(Long id) {
		return repository.findById(id);
	}

	public Person save(Person person) {
		return repository.save(person);
	}

	public void delete(Long id) {
		repository.deleteById(id);
		
	}

}
