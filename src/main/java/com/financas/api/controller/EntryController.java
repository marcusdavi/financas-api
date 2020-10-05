package com.financas.api.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financas.api.event.ResourceCreatedEvent;
import com.financas.api.filter.EntryFilter;
import com.financas.api.model.Entry;
import com.financas.api.repository.projection.EntryProjection;
import com.financas.api.service.EntryService;

@RestController
@RequestMapping("/entries")
public class EntryController {

	@Autowired
	private EntryService service;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	@PreAuthorize("hasAuthority('PERMISSION_SEARCH_ENTRY') and #oauth2.hasScope('read')")
	public Page<Entry> list(EntryFilter filter, Pageable pageable) {
		return service.list(filter, pageable);

	}

	@GetMapping(params = "resume")
	@PreAuthorize("hasAuthority('PERMISSION_SEARCH_ENTRY') and #oauth2.hasScope('read')")
	public Page<EntryProjection> resume(EntryFilter filter, Pageable pageable) {
		return service.resume(filter, pageable);

	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('PERMISSION_SEARCH_ENTRY') and #oauth2.hasScope('read')")
	public ResponseEntity<Entry> get(@PathVariable Long id) {
		return service.get(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

	}

	@PostMapping
	@PreAuthorize("hasAuthority('PERMISSION_CREATE_ENTRY') and #oauth2.hasScope('write')")
	public ResponseEntity<Entry> create(@Valid @RequestBody Entry entry, HttpServletResponse response) {
		Entry newEntry = service.create(entry);

		publisher.publishEvent(new ResourceCreatedEvent(this, response, newEntry.getId()));

		return ResponseEntity.status(HttpStatus.CREATED).body(newEntry);

	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('PERMISSION_CREATE_ENTRY') and #oauth2.hasScope('write')")
	public ResponseEntity<Entry> update(@PathVariable Long id, @Valid @RequestBody Entry entry) {

		try {
			Entry updateEntry = service.update(id, entry);
			return ResponseEntity.ok(updateEntry);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('PERMISSION_REMOVE_ENTRY') and #oauth2.hasScope('write')")
	public ResponseEntity<Entry> delete(@PathVariable Long id) {

		service.delete(id);
		return ResponseEntity.noContent().build();

	}

}
