package com.financas.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
		Optional<Person> optionalPerson = repository.findById(id);
		
		if(optionalPerson.isPresent()){
			return optionalPerson.get();
		} else {
			throw new EmptyResultDataAccessException(1);
		}
	}
	
}
