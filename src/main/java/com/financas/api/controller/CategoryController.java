package com.financas.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financas.api.event.ResourceCreatedEvent;
import com.financas.api.model.Category;
import com.financas.api.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
    
    @Autowired
    private CategoryService service;
    
    @Autowired
    private ApplicationEventPublisher publisher;
    
    @GetMapping
    public List<Category> list() {
       return service.list();
        
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('PERMISSION_SEARCH_CATEGORY') and #oauth2.hasScope('read')")
    public ResponseEntity<Category> get(@PathVariable Long id) {
        return service.get(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PreAuthorize("hasAuthority('PERMISSION_CREATE_CATEGORY') and #oauth2.hasScope('write')")
    @PostMapping
    public ResponseEntity<Category> create(@Valid @RequestBody Category category, HttpServletResponse response) {
        
    	
    	Category newCategory = service.create(category);
        
        publisher.publishEvent(new ResourceCreatedEvent(this, response, newCategory.getId()));
        
        return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('PERMISSION_CREATE_CATEGORY') and #oauth2.hasScope('write')")
    public ResponseEntity<Category> delete(@PathVariable Long id) {
    	
    	 service.delete(id);
    	 return ResponseEntity.noContent().build();

    	
    }
}
