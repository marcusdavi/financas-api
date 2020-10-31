package com.financas.api.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.financas.api.filter.PersonFilter;
import com.financas.api.model.Person;
import com.financas.api.service.PersonService;

@RunWith(MockitoJUnitRunner.class)
public class PersonControllerTest {

	@InjectMocks
	private PersonController controller;

	@Mock
	private PersonService service;

	@Mock
	private ApplicationEventPublisher publisher;

	private PersonFilter filter;

	@Before
	public void init() {
		filter = new PersonFilter();
		filter.setName("Maria");
	}

	@Test
	public void testListOk() {

		List<Person> personList = new ArrayList<>();
		personList.add(buildPerson(1L, "Maria José", true));
		personList.add(buildPerson(2L, "Maria João", false));

		PageImpl<Person> pagePerson = new PageImpl<Person>(personList);

		when(service.list(Mockito.any(), Mockito.any())).thenReturn(pagePerson);

		Page<Person> response = controller.list(filter, PageRequest.of(1, 2));

		assertEquals(1, response.getTotalPages());
		assertEquals(2, response.getTotalElements());
		assertEquals(1L, response.getContent().get(0).getId());
		assertEquals("Maria José", response.getContent().get(0).getName());
		assertFalse(response.getContent().get(1).getActive());

	}

	@Test
	public void testListEmptyOk() {

		PageImpl<Person> pagePerson = new PageImpl<Person>(new ArrayList<>());

		when(service.list(Mockito.any(), Mockito.any())).thenReturn(pagePerson);

		Page<Person> response = controller.list(filter, PageRequest.of(1, 2));

		assertTrue(response.getContent().isEmpty());

	}

	@Test
	public void getOk() {

		Person person = buildPerson(2L, "Maria João", false);
		Optional<Person> personOpt = Optional.of(person);

		when(service.get(Mockito.anyLong())).thenReturn(personOpt);

		ResponseEntity<Person> response = controller.get(1L);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(2L, response.getBody().getId());
		assertEquals("Maria João", response.getBody().getName());
		assertFalse(response.getBody().getActive());

	}

	@Test
	public void getNotFound() {

		Optional<Person> personOpt = Optional.empty();

		when(service.get(Mockito.anyLong())).thenReturn(personOpt);

		ResponseEntity<Person> response = controller.get(1L);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

	}

	@Test
	public void testCreateOk() {

		Person person = buildPerson(2L, "Maria João", false);

		when(service.create(person)).thenReturn(person);

		ResponseEntity<Person> response = controller.create(person, null);

		assertNotNull(response.getBody());
		assertEquals(2L, response.getBody().getId());
		assertEquals("Maria João", response.getBody().getName());
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	
	@Test
	public void testDeleteOk() {

		ResponseEntity<Person> response = controller.delete(1L);
		
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}
	
	@Test
	public void testUpdateActiveOk() {
		
		ResponseEntity<Person> response = controller.updateActive(2L, true);
		
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}
	
	@Test
	public void testActiveOk() {
		Person person = buildPerson(2L, "Maria João", false);
		
		ResponseEntity<Person> response = controller.update(2L, person);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	private Person buildPerson(Long id, String name, Boolean active) {
		Person person = new Person();
		person.setId(id);
		person.setName(name);
		person.setActive(active);

		return person;
	}
}
