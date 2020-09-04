package com.webservice.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.webservice.book.dao.AuthorService;
import com.webservice.book.model.Author;



@RestController
public class AuthorController {

	@Autowired
	private AuthorService authorService;
	
	@GetMapping("/authors")
	public ResponseEntity<List<Author>> getAllAuthor(){
		return ResponseEntity.ok().body(authorService.getAll());
	}
	
	@GetMapping("/authors/{id}")
	public ResponseEntity<Author> getAuthorById(@PathVariable long id){
		return ResponseEntity.ok().body(authorService.getById(id));
	}
	
	@PostMapping("/authors")
	public ResponseEntity<Author> createAuthor(@RequestBody Author author){
		return ResponseEntity.ok().body(this.authorService.create(author));
	}
}
