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
	PersonRepository personRepository;

	public List<Person> findAll() {
		return personRepository.findAll();
	}

	public Optional<Person> get(Long id) {
		return personRepository.findById(id);
	}

	public Person save(Person person) {
		return personRepository.save(person);
	}

}
