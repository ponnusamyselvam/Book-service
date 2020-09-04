package com.webservice.book.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webservice.book.exception.ResourceNotFoundException;
import com.webservice.book.model.Book;
import com.webservice.book.model.Publisher;
import com.webservice.book.repository.PublisherRepository;


@Service
@Transactional
public class PublisherServiceImpl extends PublisherService{
	
	@Autowired
	private PublisherRepository publisherRepository;
	
	@Override
	public List<Publisher> getAll() {
		return this.publisherRepository.findAll();
	}

	@Override
	public Publisher create( Publisher o )
	{
		return publisherRepository.save( o );
	}
	
	@Override
	public Publisher getById( long id ) throws ResourceNotFoundException
	{
		return checkIfIdIsPresentandReturnPublisher( id );
	}
	
	@Override
	public List<Book> getBooksById( int id )
	{
		return checkIfIdIsPresentandReturnPublisher( id ).getBookList();
	}

	private Publisher checkIfIdIsPresentandReturnPublisher( long id )
	{
		if ( !publisherRepository.findById( id ).isPresent() )
			throw new ResourceNotFoundException( " Publisher id = " + id + " not found" );
		else
			return publisherRepository.findById( id ).get();
	}
	
}
