package com.financas.api.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.financas.api.model.Category;
import com.financas.api.service.CategoryService;

@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerTest {

	@InjectMocks
	private CategoryController controller;

	@Mock
	private CategoryService service;

	@Mock
	private ApplicationEventPublisher publisher;

	@Test
	public void testListOk() {

		List<Category> categories = new ArrayList<>();
		categories.add(buildCategory(1L, "Pet"));
		categories.add(buildCategory(2L, "Home"));

		when(service.list()).thenReturn(categories);

		List<Category> response = controller.list();

		assertTrue(!response.isEmpty());
		assertEquals(2, response.size());
		assertEquals(1L, response.get(0).getId());
		assertEquals("Pet", response.get(0).getName());
		assertEquals(2L, response.get(1).getId());
		assertEquals("Home", response.get(1).getName());

	}

	@Test
	public void testListEmptyOk() {

		when(service.list()).thenReturn(new ArrayList<>());

		List<Category> response = controller.list();

		assertTrue(response.isEmpty());

	}

	@Test
	public void testGetCategoryOk() {

		when(service.get(Mockito.anyLong())).thenReturn(Optional.of(buildCategory(1L, "Pet")));

		ResponseEntity<Category> response = controller.get(1L);

		assertNotNull(response.getBody());
		assertEquals(1L, response.getBody().getId());
		assertEquals("Pet", response.getBody().getName());
		assertEquals(HttpStatus.OK, response.getStatusCode());

	}

	@Test
	public void testGetCategorynotFound() {

		when(service.get(Mockito.anyLong())).thenReturn(Optional.empty());

		ResponseEntity<Category> response = controller.get(1L);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertNull(response.getBody());

	}

	@Test
	public void testCreateCategoryOk() {

		Category category = buildCategory(1L, "Pet");

		when(service.create(Mockito.any())).thenReturn(category);

		ResponseEntity<Category> response = controller.create(category, null);

		assertNotNull(response.getBody());
		assertEquals(1L, response.getBody().getId());
		assertEquals("Pet", response.getBody().getName());
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	@Test
	public void testDeleteOk() {

		ResponseEntity<Category> response = controller.delete(1L);

		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

	private Category buildCategory(Long id, String name) {
		Category category = new Category();
		category.setId(id);
		category.setName(name);

		return category;
	}
}
