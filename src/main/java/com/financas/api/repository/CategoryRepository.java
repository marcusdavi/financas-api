package com.financas.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financas.api.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
    

}
