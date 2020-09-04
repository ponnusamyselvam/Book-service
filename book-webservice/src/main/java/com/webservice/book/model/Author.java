package com.webservice.book.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "author")
public class Author {
	
	@Id
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "specialization")
	private String specialization;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
	@JsonIgnore
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	private List<Book> bookList = new ArrayList<>();
	
	public List<Book> getBookList() {
		return bookList;
	}
	
	public void addBook( Book book ){
		this.bookList.add( book );
	}
	
}
