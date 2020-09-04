package com.webservice.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.webservice.book.dao.ReaderService;
import com.webservice.book.model.Reader;





@RestController
public class ReaderController {

	@Autowired
	private ReaderService readerService;
	
	@GetMapping("/readers")
	public ResponseEntity<List<Reader>> getAllReader(){
		return ResponseEntity.ok().body(readerService.getAll());
	}
	
	@GetMapping("/readers/{id}")
	public ResponseEntity<Reader> getReaderById(@PathVariable long id){
		return ResponseEntity.ok().body(readerService.getById(id));
	}
	
	@PostMapping("/readers")
	public ResponseEntity<Reader> createReader(@RequestBody Reader reader){
		return ResponseEntity.ok().body(this.readerService.create(reader));
	}
}
