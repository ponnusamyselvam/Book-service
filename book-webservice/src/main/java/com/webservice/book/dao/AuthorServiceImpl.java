package com.webservice.book.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webservice.book.exception.ResourceNotFoundException;
import com.webservice.book.model.Author;
import com.webservice.book.model.Book;
import com.webservice.book.repository.AuthorRepository;


@Service
@Transactional
public class AuthorServiceImpl extends AuthorService{

	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Override
	public Author create(Author author) {
		return authorRepository.save(author);
	}

	@Override
	public List<Author> getAll() {
		return this.authorRepository.findAll();
	}

	@Override
	public Author getById(long authorId) {
		
		Optional<Author> authorDb = this.authorRepository.findById(authorId);
		
		if(authorDb.isPresent()) {
			return authorDb.get();
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + authorId);
		}
	}


	@Override
	public List<Book> getBooksById(int id) {
		return checkIfIdIsPresentandReturnAuthor( id ).getBookList();
	}
	
	private Author checkIfIdIsPresentandReturnAuthor( long id )
	{
		if ( !authorRepository.findById( id ).isPresent() )
			throw new ResourceNotFoundException( " Author id = " + id + " not found" );
		else
			return authorRepository.findById( id ).get();
	}

}
