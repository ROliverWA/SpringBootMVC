package com.alwaysforward.mvc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.alwaysforward.mvc.models.Book;
import com.alwaysforward.mvc.repositories.BookRepository;

@Service
public class BookService {
	private final BookRepository bookRepo;
	
	public BookService(BookRepository bookRepo) {
		this.bookRepo = bookRepo;
	}
	// returns all the books
    public List<Book> allBooks() {
        return bookRepo.findAll();
    }
    // creates a book
    public Book createBook(Book b) {
        return bookRepo.save(b);
    }
    // retrieves a book
    public Book findBook(Long id) {
        Optional<Book> optionalBook = bookRepo.findById(id);
        if(optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            return null;
        }
    }
    public Book updateBook(Long id, String title, String desc, String lang, Integer numOfPages) {
    	Optional<Book> optionalBook = bookRepo.findById(id);
    	if(optionalBook.isPresent()) {
    		Book requestedBook = optionalBook.get();
    		requestedBook.setTitle(title);
    		requestedBook.setLanguage(lang);
    		requestedBook.setDescription(desc);
    		requestedBook.setNumberOfPages(numOfPages);
    		Book updatedBook = bookRepo.save(requestedBook);
    		return updatedBook;    		
    	}
    	else {
    		return null;
    	}
    	
    }
    
    public Book deleteBook(Long id) {
    	 Optional<Book> optionalBook = bookRepo.findById(id);
    	 if(optionalBook.isPresent()) {
    		 Book removedBook = optionalBook.get();
    		 bookRepo.deleteById(id);
    		 return removedBook;
    	 } else {
    		 return null;
    	 }
    }
}
