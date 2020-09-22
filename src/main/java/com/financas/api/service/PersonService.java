package com.financas.api.service;

import java.util.List;

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

}
