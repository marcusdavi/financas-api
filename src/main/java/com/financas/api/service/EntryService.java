package com.financas.api.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.financas.api.exception.UnknownOrInactivePersonException;
import com.financas.api.model.Entry;
import com.financas.api.model.Person;
import com.financas.api.repository.EntryRepository;
import com.financas.api.repository.PersonRepository;

@Service
public class EntryService {
	
	@Autowired
	private EntryRepository entryRepository;
	
	@Autowired
	private PersonRepository personRepository;

	public List<Entry> findAll() {
		return entryRepository.findAll();
	}

	public Optional<Entry> get(Long id) {
		return entryRepository.findById(id);
	}

	public Entry create(@Valid Entry entry) {
		
		Optional<Person> optPerson = personRepository.findById(entry.getPerson().getId());
		
		if(optPerson.isPresent() && optPerson.get().getActive()) {
			return entryRepository.save(entry);		
		} else {
			throw new UnknownOrInactivePersonException();
		}
	}

}
