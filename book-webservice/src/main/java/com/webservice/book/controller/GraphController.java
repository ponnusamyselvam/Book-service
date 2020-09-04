package com.webservice.book.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webservice.book.dao.AuthorService;
import com.webservice.book.dao.BookService;
import com.webservice.book.dao.GraphService;
import com.webservice.book.dao.PublisherService;
import com.webservice.book.dao.ReaderService;
import com.webservice.book.model.Graph;

@RestController
@CrossOrigin
public class GraphController {

	@Autowired
	private AuthorService authorService;
	@Autowired
	private PublisherService publisherService;
	@Autowired
	private BookService bookService;
	@Autowired
	private ReaderService readerService;
	
	@GetMapping("/graph")
	public ResponseEntity<Graph> getAllAuthor(){
		GraphService graph = new GraphService();
		return ResponseEntity.ok().body(graph.getGraphData(authorService,publisherService,bookService,readerService));
	}
	
}
