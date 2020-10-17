package com.financas.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.financas.api.exception.CategoryCannotBeExcludedException;
import com.financas.api.model.Category;
import com.financas.api.repository.CategoryRepository;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository repository;

    public List<Category> list() {
        return repository.findAll();
    }

    public Optional<Category> get(Long id) {
        return repository.findById(id);
    }
    
    public Category create(Category category) {
        return repository.save(category);
        
    }

	public void delete(Long id) {
			try {
				repository.deleteById(id);	
			} catch (Exception e) {
				throw new CategoryCannotBeExcludedException(e.toString());
			}

		

	}

}
