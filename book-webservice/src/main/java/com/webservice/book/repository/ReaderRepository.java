package com.webservice.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webservice.book.model.Reader;

public interface ReaderRepository extends JpaRepository<Reader, Long> {
	
}
