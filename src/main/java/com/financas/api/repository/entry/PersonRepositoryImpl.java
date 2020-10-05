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

import com.financas.api.filter.PersonFilter;
import com.financas.api.model.Person;
import com.financas.api.model.Person_;

public class PersonRepositoryImpl implements PersonRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Person> findPersonByName(PersonFilter filter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Person> criteria = builder.createQuery(Person.class);
		Root<Person> root = criteria.from(Person.class);

		Predicate[] predicates = createRestrictions(filter, builder, root);
		criteria.where(predicates);

		TypedQuery<Person> query = manager.createQuery(criteria);
		addPagination(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, getTotal(filter));
	}

	private Predicate[] createRestrictions(PersonFilter filter, CriteriaBuilder builder, Root<Person> root) {

		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(filter.getName())) {
			predicates.add(
					builder.like(builder.lower(root.get(Person_.name)), "%" + filter.getName().toLowerCase() + "%"));
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

	private long getTotal(PersonFilter filter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Person> root = criteria.from(Person.class);

		Predicate[] predicates = createRestrictions(filter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));

		return manager.createQuery(criteria).getSingleResult();

	}

}
