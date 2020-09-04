package com.webservice.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webservice.book.model.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
	
}
