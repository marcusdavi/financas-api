package com.financas.api.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
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

import com.financas.api.filter.EntryFilter;
import com.financas.api.model.Category;
import com.financas.api.model.Entry;
import com.financas.api.model.Person;
import com.financas.api.model.enums.TypeEntry;
import com.financas.api.repository.projection.EntryProjection;
import com.financas.api.service.EntryService;

@RunWith(MockitoJUnitRunner.class)
public class EntryControllerTest {

	@InjectMocks
	private EntryController controller;

	@Mock
	private EntryService service;

	@Mock
	private ApplicationEventPublisher publisher;

	private EntryFilter filter;

	@Before
	public void init() {
		filter = new EntryFilter();
		filter.setDescription("Pet");
		filter.setExpirationDateFinal(LocalDate.now());
		filter.setExpirationDateInitial(LocalDate.now());
	}

	@Test
	public void testListOk() {

		List<Entry> entryList = new ArrayList<>();

		entryList.add(buildEntry(1L, "Pet Shop", LocalDate.now()));
		entryList.add(buildEntry(2L, "Pet Prime", LocalDate.now()));

		PageImpl<Entry> entryPage = new PageImpl<Entry>(entryList);

		when(service.list(Mockito.any(), Mockito.any())).thenReturn(entryPage);

		Page<Entry> response = controller.list(filter, PageRequest.of(0, 1));

		assertEquals(1, response.getTotalPages());
		assertEquals(2, response.getTotalElements());
		assertEquals(1L, response.getContent().get(0).getId());

	}

	@Test
	public void testListEmptyOk() {

		List<Entry> entryList = new ArrayList<>();

		PageImpl<Entry> entryPage = new PageImpl<Entry>(entryList);

		when(service.list(Mockito.any(), Mockito.any())).thenReturn(entryPage);

		Page<Entry> response = controller.list(filter, PageRequest.of(0, 1));

		assertEquals(1, response.getTotalPages());
		assertEquals(0, response.getTotalElements());
		assertTrue(response.getContent().isEmpty());

	}

	@Test
	public void testResumeOk() {

		List<EntryProjection> entryList = new ArrayList<>();
		Entry entry1 = buildEntry(1L, "Pet Shop", LocalDate.now());
		Entry entry2 = buildEntry(2L, "Pet Prime", LocalDate.now());

		entryList.add(entryToEntryProjection(entry1));
		entryList.add(entryToEntryProjection(entry2));

		PageImpl<EntryProjection> entryPage = new PageImpl<EntryProjection>(entryList);

		when(service.resume(Mockito.any(), Mockito.any())).thenReturn(entryPage);

		Page<EntryProjection> response = controller.resume(filter, PageRequest.of(0, 1));

		assertEquals(1, response.getTotalPages());
		assertEquals(2, response.getTotalElements());
		assertFalse(response.getContent().isEmpty());
		assertTrue(response.stream().filter(p -> p.getId() == 1L).findFirst().isPresent());
		assertTrue(response.stream().filter(p -> p.getId() == 2L).findFirst().isPresent());
		assertTrue(
				response.stream().filter(p -> p.getDescription().equalsIgnoreCase("Pet Shop")).findFirst().isPresent());

	}

	@Test
	public void testResumeEmptyOk() {
		
		PageImpl<EntryProjection> entryPage = new PageImpl<EntryProjection>(new ArrayList<EntryProjection>());

		when(service.resume(Mockito.any(), Mockito.any())).thenReturn(entryPage);

		Page<EntryProjection> response = controller.resume(filter, PageRequest.of(0, 1));

		assertEquals(1, response.getTotalPages());
		assertEquals(0, response.getTotalElements());
		assertTrue(response.getContent().isEmpty());
	}

	@Test
	public void testGetOk() {

		Entry entry = buildEntry(2L, "Pet Prime", LocalDate.now());

		when(service.get(Mockito.anyLong())).thenReturn(Optional.of(entry));

		ResponseEntity<Entry> response = controller.get(2L);

		assertNotNull(response.getBody().getId());
		assertEquals("Pet Prime", response.getBody().getDescription());

	}

	@Test
	public void testGetNotFound() {

		when(service.get(Mockito.anyLong())).thenReturn(Optional.empty());

		ResponseEntity<Entry> response = controller.get(2L);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

	}

	private Entry buildEntry(Long id, String description, LocalDate expirationDate) {

		Person person = new Person();
		person.setName("Marcus");

		Category category = new Category();
		category.setName("Pet");

		Entry entry = new Entry();
		entry.setId(id);
		entry.setDescription(description);
		entry.setExpirationDate(expirationDate);
		entry.setPerson(person);
		entry.setCategory(category);
		entry.setNote("teste");
		entry.setPayDate(expirationDate);
		entry.setType(TypeEntry.CREDIT);
		entry.setValue(BigDecimal.valueOf(200));

		return entry;
	}

	private EntryProjection entryToEntryProjection(Entry entry) {

		return new EntryProjection(entry.getId(), entry.getDescription(), entry.getExpirationDate(), entry.getPayDate(),
				entry.getValue(), entry.getType(), entry.getCategory().getName(), entry.getPerson().getName());
	}
}
