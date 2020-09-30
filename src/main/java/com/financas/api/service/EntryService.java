package com.financas.api.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.financas.api.exception.UnknownOrInactivePersonException;
import com.financas.api.filter.EntryFilter;
import com.financas.api.model.Entry;
import com.financas.api.model.Person;
import com.financas.api.repository.EntryRepository;
import com.financas.api.repository.PersonRepository;
import com.financas.api.repository.projection.EntryProjection;

@Service
public class EntryService {
	
	@Autowired
	private EntryRepository entryRepository;
	
	@Autowired
	private PersonRepository personRepository;

	public Page<Entry> list(EntryFilter filter, Pageable pageable) {
		return entryRepository.findEntriesByFilter(filter, pageable);

	}
	

	public Page<EntryProjection> resume(EntryFilter filter, Pageable pageable) {
		return entryRepository.findEntryProjectionByFilter(filter, pageable);
	}

	public Optional<Entry> get(Long id) {
		return entryRepository.findById(id);
	}

	public Entry create(@Valid Entry entry) {
		
		Optional<Person> optPerson = personRepository.findById(entry.getPerson().getId());
		
		if(optPerson.isPresent() && optPerson.get().getActive()) {
			return entryRepository.save(entry);		
		} else {
			throw new UnknownOrInactivePersonException("It's not possible to create entry for the person unknown or inactive.");
		}
	}

	public void delete(Long id) {
		entryRepository.deleteById(id);
		
	}

}
