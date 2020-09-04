package com.webservice.book.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.webservice.book.constants.BookServiceConstatns;
import com.webservice.book.model.Author;
import com.webservice.book.model.Book;
import com.webservice.book.model.Publisher;
import com.webservice.book.model.Reader;
import com.webservice.book.repository.AuthorRepository;
import com.webservice.book.repository.BookRepository;
import com.webservice.book.repository.PublisherRepository;
import com.webservice.book.repository.ReaderRepository;

public class LoadCSV {
	
	public void loadAuthorData(AuthorRepository authorRepo, String csvFile) {
		boolean isHeader = true;
		String[] header = null;
		String[] row = null;
		String line;
		try {
			File file = new File(getClass().getClassLoader().getResource(csvFile).getFile());
			BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()));  
			while ((line = br.readLine()) != null) {  
				if(isHeader) {
					header = line.split(BookServiceConstatns.SEPERATOR);
					isHeader = false;
				} else {
					row = line.split(BookServiceConstatns.SEPERATOR);
					if(header != null && row != null) {
						Author author = new Author();
						for(int i=0;i<row.length;i++) {
							if("id".equalsIgnoreCase(header[i])) {
								author.setId(Long.parseLong(row[i]));
							} else if ("name".equalsIgnoreCase(header[i])) {
								author.setName(row[i]);
							} else if ("specialization".equalsIgnoreCase(header[i])) {
								author.setSpecialization(row[i]);
							}
						}
						authorRepo.save(author);
					}
				}
			}  
			br.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadPublisherData(PublisherRepository publisherRepo, String csvFile) {
		boolean isHeader = true;
		String[] header = null;
		String[] row = null;
		String line;
		try {
			File file = new File(getClass().getClassLoader().getResource(csvFile).getFile());
			BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()));  
			while ((line = br.readLine()) != null) {  
				if(isHeader) {
					header = line.split(BookServiceConstatns.SEPERATOR);
					isHeader = false;
				} else {
					row = line.split(BookServiceConstatns.SEPERATOR);
					if(header != null && row != null) {
						Publisher publisher = new Publisher();
						for(int i=0;i<row.length;i++) {
							if("publisher_id".equalsIgnoreCase(header[i])) {
								publisher.setId(Long.parseLong(row[i]));
							} else if ("publisher_name".equalsIgnoreCase(header[i])) {
								publisher.setPublisher_name(row[i]);
							}
						}
						publisherRepo.save(publisher);
					}
				}
			}  
			br.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadBookData(BookRepository bookRepo, PublisherRepository publisherRepo, AuthorRepository authorRepo, String csvFile) {
		boolean isHeader = true;
		String[] header = null;
		String[] row = null;
		String line;
		try {
			File file = new File(getClass().getClassLoader().getResource(csvFile).getFile());
			BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()));  
			while ((line = br.readLine()) != null) {  
				if(isHeader) {
					header = line.split(BookServiceConstatns.SEPERATOR);
					isHeader = false;
				} else {
					row = line.split(BookServiceConstatns.SEPERATOR);
					if(header != null && row != null) {
						Book book = new Book();
						for(int i=0;i<row.length;i++) {
							if(StringUtils.isNotBlank(row[i])) {
								if("book_isbn".equalsIgnoreCase(header[i])) {
									book.setBook_isbn(row[i]);
								} else if ("book_name".equalsIgnoreCase(header[i])) {
									book.setBook_name(row[i]);
								} else if ("subject_name".equalsIgnoreCase(header[i])) {
									book.setSubject_name(row[i]);
								} else if ("author_id".equalsIgnoreCase(header[i])) {
									book.setAuthor(authorRepo.findById(Long.parseLong(row[i])).get());
								} else if ("coauthor_id".equalsIgnoreCase(header[i])) {
									book.setCo_author_id(authorRepo.findById(Long.parseLong(row[i])).get());
								} else if ("publisher_id".equalsIgnoreCase(header[i])) {
									book.setPublisher(publisherRepo.findById(Long.parseLong(row[i])).get());
								} else if ("published_date".equalsIgnoreCase(header[i])) {
									book.setPublished_date(Date.valueOf(row[i]));
								} else if ("price".equalsIgnoreCase(header[i])) {
									book.setPrice(Double.parseDouble(row[i]));
								}
							} 
						}
						bookRepo.save(book);
					}
				}
			}  
			br.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadReaderData(ReaderRepository readerRepo, BookRepository bookRepo, PublisherRepository publisherRepo, String csvFile) {
		boolean isHeader = true;
		String[] header = null;
		String[] row = null;
		String line;
		try {
			File file = new File(getClass().getClassLoader().getResource(csvFile).getFile());
			BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()));  
			while ((line = br.readLine()) != null) {  
				if(isHeader) {
					header = line.split(BookServiceConstatns.SEPERATOR);
					isHeader = false;
				} else {
					row = line.split(BookServiceConstatns.SEPERATOR);
					if(header != null && row != null) {
						Reader reader = new Reader();
						for(int i=0;i<row.length;i++) {
							if("reader_name".equalsIgnoreCase(header[i])) {
								reader.setReader_name(row[i]);
							} else if ("book_isbn".equalsIgnoreCase(header[i])) {
								List<Book> booklist = bookRepo.findBybook_isbn(row[i]);
								try{
									reader.setBook(booklist.get(0));
								}catch(Exception e) {
									e.printStackTrace();
								}
							} else if ("publisher_id".equalsIgnoreCase(header[i])) {
								reader.setPublisher(publisherRepo.findById(Long.parseLong(row[i])).get());
							} else if ("purchase_date".equalsIgnoreCase(header[i])) {
								reader.setPurchashed_date(Date.valueOf(row[i]));
							}
						}
						readerRepo.save(reader);
					}
				}
			}  
			br.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
