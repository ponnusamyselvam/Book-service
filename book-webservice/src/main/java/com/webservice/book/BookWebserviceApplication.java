package com.webservice.book;


import org.apache.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.webservice.book.common.LoadCSV;
import com.webservice.book.repository.AuthorRepository;
import com.webservice.book.repository.BookRepository;
import com.webservice.book.repository.PublisherRepository;
import com.webservice.book.repository.ReaderRepository;

@SpringBootApplication
public class BookWebserviceApplication {

	static Logger log = Logger.getLogger(BookWebserviceApplication.class.getName());
	
	public static void main(String[] args) {
		SpringApplication.run(BookWebserviceApplication.class, args);
	}
	
	
	@Bean
    CommandLineRunner initDatabase(AuthorRepository authorRepo, PublisherRepository publisherRepo, BookRepository bookrepo, ReaderRepository readerRepo) {
		LoadCSV csv = new LoadCSV();
		//load author data
		log.info("load data into DB");
		try {
			csv.loadAuthorData(authorRepo,"author.csv");
		} catch(Exception e) {
			log.error("Error Occured while loading author data",e);
		}
		try {
			csv.loadPublisherData(publisherRepo,"publisher.csv");
		} catch(Exception e) {
			log.error("Error Occured while loading author data",e);
		}
		try {
			csv.loadBookData(bookrepo, publisherRepo, authorRepo,"books.csv");
		} catch(Exception e) {
			log.error("Error Occured while loading author data",e);
		}
		try {
			csv.loadReaderData(readerRepo, bookrepo, publisherRepo,"reader.csv");
		} catch(Exception e) {
			log.error("Error Occured while loading author data",e);
		}
		
		
		return args -> {
            
        };
    }

}
