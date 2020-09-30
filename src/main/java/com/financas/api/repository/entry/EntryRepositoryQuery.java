package com.financas.api.repository.entry;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.financas.api.filter.EntryFilter;
import com.financas.api.model.Entry;
import com.financas.api.repository.projection.EntryProjection;

public interface EntryRepositoryQuery {
	
	public Page<Entry> findEntriesByFilter(EntryFilter filter, Pageable pageable);
	public Page<EntryProjection> findEntryProjectionByFilter(EntryFilter filter, Pageable pageable);

}
