package com.boot;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TestBookService {

	@InjectMocks
	BookService service;

	@Mock
	BookRepository dao;

 

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void saveTest() {
 
		Subject subject=new Subject("Math", 121, new HashSet<Book>());
		
		Book book = new Book("Math_1",12.23,10, LocalDate.now(), subject);
 
		service.save(book);

		verify(dao, times(1)).save(book);
	}

	@Test
	public void listAllTest() {
		Subject subject=new Subject("Math", 121, new HashSet<Book>());
		
		List<Book> list = new ArrayList<Book>();
		Book book =  new Book("Math_1",11.11,11, LocalDate.now(), subject);
		list.add(book);
		book =  new Book("Math_2",22.22,22, LocalDate.now(), subject);
		list.add(book);
		book =  new Book("Math_3",33.33,33, LocalDate.now(), subject);
		list.add(book);
		book =  new Book("Math_4",44.44,44, LocalDate.now(), subject);
		list.add(book);

		when(dao.findAll()).thenReturn(list);

		List<Book> bookList = service.listAll();

		assertEquals(4, bookList.size());
		verify(dao, times(1)).findAll();

	}
 
	@Test
	public void findBySubtitleTest() {
		
		String bookTitle = "Math_1";

		Subject subject=new Subject("Math", 121, new HashSet<Book>());
		
		List<Book> list = new ArrayList<Book>();
		Book book =  new Book(bookTitle,11.11,11, LocalDate.now(), subject);
		list.add(book);

		when(dao.findByTitle(bookTitle)).thenReturn(list);

		List<Book> bookList1 = service.findByTitle(bookTitle);
		Book sub = bookList1.get(0);

		assertEquals(bookTitle, sub.getTitle());
 

	}
 
 	@Test
	public void searchTest() {

		String bookTitle = "Math_1";
		String bookTitle2 = "Math_2";
		Subject subject=new Subject("Math", 121, new HashSet<Book>());
		
		List<Book> list = new ArrayList<Book>();
		Book book =  new Book(bookTitle,11.11,11, LocalDate.now(), subject);
		list.add(book);
		book =  new Book(bookTitle2,22.22,22, LocalDate.now(), subject);
		list.add(book);

		when(dao.search(bookTitle)).thenReturn(list);

		List<Book> bookList = service.search(bookTitle);

		assertEquals(2, bookList.size());
		verify(dao, times(1)).search(bookTitle);

	} 

}
