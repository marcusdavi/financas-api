package com.financas.api.model;

import com.financas.api.model.enums.TypeEntry;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Entry.class)
public abstract class Entry_ {

	public static volatile SingularAttribute<Entry, String> note;
	public static volatile SingularAttribute<Entry, Person> person;
	public static volatile SingularAttribute<Entry, String> description;
	public static volatile SingularAttribute<Entry, Long> id;
	public static volatile SingularAttribute<Entry, TypeEntry> type;
	public static volatile SingularAttribute<Entry, Category> category;
	public static volatile SingularAttribute<Entry, BigDecimal> value;
	public static volatile SingularAttribute<Entry, LocalDate> expirationDate;
	public static volatile SingularAttribute<Entry, LocalDate> payDate;

	public static final String NOTE = "note";
	public static final String PERSON = "person";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";
	public static final String TYPE = "type";
	public static final String CATEGORY = "category";
	public static final String VALUE = "value";
	public static final String EXPIRATION_DATE = "expirationDate";
	public static final String PAY_DATE = "payDate";

}

