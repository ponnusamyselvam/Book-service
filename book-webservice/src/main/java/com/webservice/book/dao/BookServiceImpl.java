package com.webservice.book.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webservice.book.exception.ResourceNotFoundException;
import com.webservice.book.model.Author;
import com.webservice.book.model.Book;
import com.webservice.book.model.Publisher;
import com.webservice.book.repository.AuthorRepository;
import com.webservice.book.repository.BookRepository;
import com.webservice.book.repository.PublisherRepository;


@Service
@Transactional
public class BookServiceImpl extends BookService{

	
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private PublisherRepository publisherRepository;
	@Autowired
	private AuthorRepository authorRepository;
	
	@Override
	public Book create(Book book) {
		
		//if author n publisher gets an id we can search the db and assign them
		if (book.getAuthor().getId() != 0 )
		{
			Author author = checkIfIdIsPresentandReturnAuthor(book.getAuthor().getId());
			book.setAuthor(author);
			author.addBook(book);
		}
		if (book.getCo_author_id().getId() != 0 )
		{
			Author author = checkIfIdIsPresentandReturnAuthor(book.getCo_author_id().getId());
			book.setCo_author_id(author);
			author.addBook(book);
		}
		if (book.getPublisher().getId() != 0 )
		{
			Publisher publisher = checkIfIdIsPresentandReturnPublisher(book.getPublisher().getId());
			book.setPublisher(publisher);
			publisher.addBook(book);
		}
		return bookRepository.save(book);
	}

	@Override
	public List<Book> getAll() {
		return this.bookRepository.findAll();
	}

	@Override
	public Book getById(long bookId) {
		
		Optional<Book> bookDb = this.bookRepository.findById(bookId);
		
		if(bookDb.isPresent()) {
			return bookDb.get();
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + bookId);
		}
	}
	

	private Author checkIfIdIsPresentandReturnAuthor( long id )
	{
		if ( !authorRepository.findById( id ).isPresent() )
			throw new ResourceNotFoundException( " Author id = " + id + " not found" );
		else
			return authorRepository.findById( id ).get();
	}

	private Publisher checkIfIdIsPresentandReturnPublisher( long id )
	{
		if ( !publisherRepository.findById( id ).isPresent() )
			throw new ResourceNotFoundException( " Publisher id = " + id + " not found" );
		else
			return publisherRepository.findById( id ).get();
	}

}
