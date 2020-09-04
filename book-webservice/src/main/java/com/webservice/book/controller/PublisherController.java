package com.webservice.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.webservice.book.dao.PublisherService;
import com.webservice.book.model.Publisher;




@RestController
public class PublisherController {

	@Autowired
	private PublisherService publisherService;
	
	@GetMapping("/publishers")
	public ResponseEntity<List<Publisher>> getAllPublisher(){
		return ResponseEntity.ok().body(publisherService.getAll());
	}
	
	@GetMapping("/publishers/{id}")
	public ResponseEntity<Publisher> getPublisherById(@PathVariable long id){
		return ResponseEntity.ok().body(publisherService.getById(id));
	}
	
	@PostMapping("/publishers")
	public ResponseEntity<Publisher> createPublisher(@RequestBody Publisher publisher){
		return ResponseEntity.ok().body(this.publisherService.create(publisher));
	}

}
