package com.webservice.book.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.webservice.book.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	
	@Query("SELECT t FROM Book t WHERE LOWER(t.book_isbn) LIKE LOWER(CONCAT('%',:book_isbn, '%'))")
	List<Book> findBybook_isbn(@Param("book_isbn") String book_isbn);
}
