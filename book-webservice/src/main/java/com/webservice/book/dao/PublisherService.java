package com.webservice.book.dao;

import java.util.List;

import com.webservice.book.model.Book;
import com.webservice.book.model.Publisher;


public abstract class PublisherService implements Service<Publisher> {
	
	public abstract List<Book> getBooksById( int id );
	
}
