package com.boot;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookService {
	
	 @Autowired BookRepository repo;
	 
	 public void save(Book book) {
	        repo.save(book);
	    }
	     
	    public List<Book> listAll() {
	        return (List<Book>) repo.findAll();
	    }
	    
	    public List<Book> search(String keyword) {
	        return repo.search(keyword);
	    }
	     
	    public Book findById(Long bookId) {
	        return repo.findById(bookId).get();
	    }
	     
	    public void delete(Book book) {
	        repo.delete(book);
	    }
	    
	    public List<Book> findBySubtitle(String title){
	    	return repo.findByTitle(title);
	    }
	    
	    
	    
	    

}
