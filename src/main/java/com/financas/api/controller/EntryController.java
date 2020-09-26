package com.financas.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financas.api.event.ResourceCreatedEvent;
import com.financas.api.model.Entry;
import com.financas.api.service.EntryService;

@RestController
@RequestMapping("/entries")
public class EntryController {
    
    @Autowired
    private EntryService service;
    
    @Autowired
    private ApplicationEventPublisher publisher;
    
    @GetMapping
    public List<Entry> list() {
       return service.findAll();
        
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Entry> get(@PathVariable Long id) {
    	return service.get(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
        
    }
    
    @PostMapping
    public ResponseEntity<Entry> create(@Valid @RequestBody Entry entry, HttpServletResponse response) {
    	Entry newEntry = service.create(entry);
        
        publisher.publishEvent(new ResourceCreatedEvent(this, response, newEntry.getId()));
        
        return ResponseEntity.status(HttpStatus.CREATED).body(newEntry);
    	
    }
 
}
