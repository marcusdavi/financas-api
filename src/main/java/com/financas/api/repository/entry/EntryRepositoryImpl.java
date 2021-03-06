package com.financas.api.repository.entry;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.financas.api.filter.EntryFilter;
import com.financas.api.model.Category_;
import com.financas.api.model.Entry;
import com.financas.api.model.Entry_;
import com.financas.api.model.Person_;
import com.financas.api.repository.projection.EntryProjection;

public class EntryRepositoryImpl implements EntryRepositoryQuery{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Entry> findEntriesByFilter(EntryFilter filter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Entry> criteria = builder.createQuery(Entry.class);
		Root<Entry> root = criteria.from(Entry.class);
		
		
		Predicate[] predicates = createRestrictions(filter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Entry> query = manager.createQuery(criteria);
		addPagination(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, getTotal(filter));
	}
	
	@Override
	public Page<EntryProjection> findEntryProjectionByFilter(EntryFilter filter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<EntryProjection> criteria = builder.createQuery(EntryProjection.class);
		Root<Entry> root = criteria.from(Entry.class);
		
		criteria.select(builder.construct(EntryProjection.class
				,root.get(Entry_.id)
				,root.get(Entry_.description)
				,root.get(Entry_.expirationDate)
				,root.get(Entry_.payDate)
				,root.get(Entry_.value)
				,root.get(Entry_.type)
				,root.get(Entry_.category).get(Category_.name)
				,root.get(Entry_.person).get(Person_.name)
				));
		
		
		Predicate[] predicates = createRestrictions(filter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<EntryProjection> query = manager.createQuery(criteria);
		addPagination(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, getTotal(filter));
	}

	private Predicate[] createRestrictions(EntryFilter filter, CriteriaBuilder builder, Root<Entry> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(!StringUtils.isEmpty(filter.getDescription())) {
			predicates.add(builder.like(
					builder.lower(root.get(Entry_.description)), "%" + filter.getDescription().toLowerCase() + "%"));
		}
		
		if(filter.getExpirationDateInitial() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(Entry_.expirationDate), filter.getExpirationDateInitial()));
		}
		
		if(filter.getExpirationDateFinal() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get(Entry_.expirationDate), filter.getExpirationDateFinal()));
		}

		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	

	private void addPagination(TypedQuery<?> query, Pageable pageable) {
		Integer pageNow = pageable.getPageNumber();
		Integer pageSize = pageable.getPageSize();
		Integer firstRegistry = pageNow * pageSize;
		
		query.setFirstResult(firstRegistry);
		query.setMaxResults(pageSize);
	}

	private long getTotal(EntryFilter filter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Entry> root = criteria.from(Entry.class);
		
		Predicate[] predicates = createRestrictions(filter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		
		return manager.createQuery(criteria).getSingleResult();
			
	}

}
