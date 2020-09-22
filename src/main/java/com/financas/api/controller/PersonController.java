package com.financas.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financas.api.model.Person;
import com.financas.api.service.PersonService;

@RestController
@RequestMapping("/persons")
public class PersonController {
    
    @Autowired
    private PersonService service;
    
    @GetMapping
    public List<Person> list() {
       return service.findAll();
        
    }
}
