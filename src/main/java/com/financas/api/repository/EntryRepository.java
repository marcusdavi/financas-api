package com.financas.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financas.api.model.Entry;

public interface EntryRepository extends JpaRepository<Entry, Long>{

}
