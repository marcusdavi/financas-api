package com.financas.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financas.api.model.Person;
import com.financas.api.repository.entry.PersonRepositoryQuery;

public interface PersonRepository extends JpaRepository<Person, Long>, PersonRepositoryQuery{

}
