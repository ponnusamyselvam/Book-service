package com.webservice.book.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webservice.book.exception.ResourceNotFoundException;
import com.webservice.book.model.Book;
import com.webservice.book.model.Publisher;
import com.webservice.book.model.Reader;
import com.webservice.book.repository.BookRepository;
import com.webservice.book.repository.PublisherRepository;
import com.webservice.book.repository.ReaderRepository;


@Service
@Transactional
public class ReaderServiceImpl extends ReaderService{

	
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private PublisherRepository publisherRepository;
	@Autowired
	private ReaderRepository readerRepository;
	
	
	@Override
	public Reader create(Reader reader) {
		
		if (reader.getBook().getId() != 0 )
		{
			Book book = checkIfIdIsPresentandReturnBook(reader.getBook().getId());
			reader.setBook(book);
			//book.addBook(book);
		}

		if (reader.getPublisher().getId() != 0 )
		{
			Publisher publisher = checkIfIdIsPresentandReturnPublisher(reader.getPublisher().getId());
			reader.setPublisher(publisher);
		//	publisher.addBook(book);
		}
		return readerRepository.save(reader);
	}

	@Override
	public List<Reader> getAll() {
		return this.readerRepository.findAll();
	}

	@Override
	public Reader getById(long readerId) {
		
		Optional<Reader> readerDb = this.readerRepository.findById(readerId);
		
		if(readerDb.isPresent()) {
			return readerDb.get();
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + readerId);
		}
	}
	

	private Book checkIfIdIsPresentandReturnBook( long id )
	{
		if ( !bookRepository.findById( id ).isPresent() )
			throw new ResourceNotFoundException( " Author id = " + id + " not found" );
		else
			return bookRepository.findById( id ).get();
	}

	private Publisher checkIfIdIsPresentandReturnPublisher( long id )
	{
		if ( !publisherRepository.findById( id ).isPresent() )
			throw new ResourceNotFoundException( " Publisher id = " + id + " not found" );
		else
			return publisherRepository.findById( id ).get();
	}


}
