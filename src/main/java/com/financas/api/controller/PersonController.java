package com.financas.api.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financas.api.event.ResourceCreatedEvent;
import com.financas.api.filter.PersonFilter;
import com.financas.api.model.Person;
import com.financas.api.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {
    
    @Autowired
    private PersonService service;
    
    @Autowired
    private ApplicationEventPublisher publisher;
    
    @GetMapping
    @PreAuthorize("hasAuthority('PERMISSION_SEARCH_PERSON') and #oauth2.hasScope('read')")
    public Page<Person> list(PersonFilter filter, Pageable pageable) {
       return service.list(filter, pageable);
        
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('PERMISSION_SEARCH_PERSON') and #oauth2.hasScope('read')")
    public ResponseEntity<Person> get(@PathVariable Long id) {
    	return service.get(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
        
    }
    
    @PostMapping
    @PreAuthorize("hasAuthority('PERMISSION_CREATE_PERSON') and #oauth2.hasScope('write')")
    public ResponseEntity<Person> create(@Valid @RequestBody Person person, HttpServletResponse response) {
    	Person newPerson = service.create(person);
        
        publisher.publishEvent(new ResourceCreatedEvent(this, response, newPerson.getId()));
        
        return ResponseEntity.status(HttpStatus.CREATED).body(newPerson);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('PERMISSION_UPDATE_PERSON') and #oauth2.hasScope('write')")
    public ResponseEntity<Person> delete(@PathVariable Long id) {
    	service.delete(id);
    	
    	return ResponseEntity.noContent().build();
    	
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Person> update(@PathVariable Long id, @Valid @RequestBody Person person) {
    	Person personUpdate = service.update(id, person);
    	
    	return ResponseEntity.ok(personUpdate);
    	
    }
    
    @PutMapping("/{id}/active")
    public ResponseEntity<Person> update(@PathVariable Long id, @RequestBody Boolean active) {
    	service.updateActive(id, active);
    	
    	return ResponseEntity.noContent().build();
    	
    }
}
