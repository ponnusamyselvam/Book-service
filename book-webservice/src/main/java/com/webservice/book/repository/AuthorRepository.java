package com.webservice.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webservice.book.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
