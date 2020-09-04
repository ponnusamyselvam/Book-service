package com.webservice.book.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

@Entity
@Table(name = "book")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "book_isbn")
	private String book_isbn;
	
	@Column(name = "book_name")
	private String book_name;
	
	@Column(name = "subject_name")
	private String subject_name;
	
	 @ManyToOne( fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	 @JoinColumn(name = "author_id", nullable = false)
	 @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	 private Author author;
	 
	 @ManyToOne( fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	 @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	 @JoinColumn(name = "co_author_id", nullable = true)
	 private Author co_author_id;
	 
	 public Author getCo_author_id() {
		return co_author_id;
	}

	public void setCo_author_id(Author co_author_id) {
		this.co_author_id = co_author_id;
	}

	 @ManyToOne( fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	 @JoinColumn(name = "publisher_id", nullable = false)
	 @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	 private Publisher publisher;
	 
	 @NotNull
	 @Column(name = "price")
	 private double price;
	 
	 public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBook_isbn() {
		return book_isbn;
	}

	public void setBook_isbn(String book_isbn) {
		this.book_isbn = book_isbn;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getSubject_name() {
		return subject_name;
	}

	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getPublished_date() {
		return published_date;
	}

	public void setPublished_date(Date published_date) {
		this.published_date = published_date;
	}

	 @Column(name = "published_Date")
	 private Date published_date;
}
