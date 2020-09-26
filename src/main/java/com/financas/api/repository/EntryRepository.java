package com.financas.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financas.api.model.Entry;
import com.financas.api.repository.entry.EntryRepositoryQuery;

public interface EntryRepository extends JpaRepository<Entry, Long>, EntryRepositoryQuery{

}
