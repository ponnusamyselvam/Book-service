package com.webservice.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.webservice.book.dao.BookService;
import com.webservice.book.model.Book;



@RestController
public class BookController {

	@Autowired
	private BookService bookService;
	
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getAllBook(){
		return ResponseEntity.ok().body(bookService.getAll());
	}
	
	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable long id){
		return ResponseEntity.ok().body(bookService.getById(id));
	}
	
	@PostMapping("/books")
	public ResponseEntity<Book> createBook(@RequestBody Book book){
		return ResponseEntity.ok().body(this.bookService.create(book));
	}
}
