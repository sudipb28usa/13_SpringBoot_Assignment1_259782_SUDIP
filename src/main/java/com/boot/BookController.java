package com.boot;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {
	
	
	 @Autowired
	 private BookService bookService;
	 @Autowired
	 private SubjectService subjectService;
	 
  	    
	     
	    @ModelAttribute("book")
	    public Book formBackingObject() {
	        return new Book();
	    }
	    
		@RequestMapping(value="/addBookPage", method = RequestMethod.GET)
		public String  addBookPage(ModelMap model) {
			
			model.addAttribute("Title", "Add Book");	
			model.addAttribute("Header", "Add Book");
			
			
			List<Subject> subjectList=subjectService.listAll();
			
		 	Map< String ,String> subjectDD = new LinkedHashMap< String,String>();
			
			for(Subject sub : subjectList) {

				subjectDD.put(Long.toString(sub.getSubjectId()), sub.getSubtitle());
	 
			}
 
			
			model.addAttribute("subjectDD", subjectDD);
			

			return "AddBook"; 
		}
	 
	    @PostMapping("/addBook")
	    public String saveUser(@ModelAttribute("book") @Valid Book book,
	                            BindingResult result, Model model) {
	  
	    	/*if (result.hasErrors()) {
				model.addAttribute("bookList", bookService.list());
				return "AddBook";
			}*/
	    	
	    	
	    	Subject subject=subjectService.findById(book.getSubjectId());
	    	
	    	Set<Book> bookSet=subject.getReferences();
	    	
	    	if(bookSet==null) {
	    		bookSet=new HashSet<Book>();
	    	}
	    	
	    	bookSet.add(book);
	    	subject.setReferences(bookSet);
	    	subjectService.save(subject);
	    	
 
	        
	        
	    	model.addAttribute("Title", "Add Book");	
			model.addAttribute("Header", "************Books*******************");
			model.addAttribute("message", "Book has been successfully added");
	    	

			return "redirect:/getBook";
	    }
	    
	    
		@RequestMapping(value="/getBook", method = RequestMethod.GET)
		public String getBook(ModelMap model) {
			model.addAttribute("bookList", bookService.listAll());
			model.addAttribute("message", "");
			model.addAttribute("Title", "Books");
			model.addAttribute("Header", "************Books*******************");
			return "Books"; 
		}
		
		@PostMapping("/searchBook")
		public String searchBook(@Valid @ModelAttribute("book")Book book,  BindingResult result, ModelMap model) {

			model.addAttribute("bookList",bookService.search(book.getTitle()));
	 
			model.addAttribute("message", "Search Results are below");
			model.addAttribute("Title", "Books");
			model.addAttribute("Header", "************Books*******************");
 
			return "Books";
			
		}
		
	/*	@RequestMapping(value="/deleteBook/{bookId}", method = RequestMethod.GET)
		public String searchBook(@PathVariable("bookId")Long bookId,ModelMap model) {
			 
			Book book=bookService.findById(bookId);
			bookService.delete(book);

 		 
	    	model.addAttribute("Title", "Home");	
			model.addAttribute("Header", "Please click on below menu");
			
			model.addAttribute("message", "Book has been deleted successfully");
			
			
		
			return "redirect:/getBook";
		}*/
		
		
		 
		@PostMapping("/deleteBook")
		public String searchSubject(@RequestParam long bookId, ModelMap model) {
			Book book=bookService.findById(bookId);
			bookService.delete(book);
			
			model.addAttribute("Title", "Add Book");	
			model.addAttribute("Header", "************Books*******************");
			
			model.addAttribute("message", "Book has been deleted added");
			
			return "redirect:/getBook";
		}
		
	    
	    
		

}
