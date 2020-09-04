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
@Table(name = "reader")
public class Reader {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "reader_name")
    @NotNull
    private String reader_name;
    
    @ManyToOne( fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "book_isbn", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Book book;
    
    @ManyToOne( fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "publisher_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Publisher publisher;
    
    @Column(name = "purchashed_date")
    @NotNull
    private Date purchashed_date;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getReader_name() {
		return reader_name;
	}

	public void setReader_name(String reader_name) {
		this.reader_name = reader_name;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public Date getPurchashed_date() {
		return purchashed_date;
	}

	public void setPurchashed_date(Date purchashed_date) {
		this.purchashed_date = purchashed_date;
	}
}



