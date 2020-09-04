package com.webservice.book.dao;

import java.util.List;

public interface Service <T> {

	T create(T object);

	List<T> getAll();

	T getById(long Id);

}
