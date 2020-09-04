package com.webservice.book.dao;

import java.util.List;

import com.webservice.book.model.Author;
import com.webservice.book.model.Book;


public abstract class AuthorService implements Service<Author> {
	
	public abstract List<Book> getBooksById( int id );
	
}
