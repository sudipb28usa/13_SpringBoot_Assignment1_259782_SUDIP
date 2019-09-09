package com.boot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan("com.boot")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private BookRepository repository;

	@Test
	public void findBySubtitle() {
 
		Subject subject=new Subject("Physics", 121, new HashSet<Book>());
		
		Book book = new Book("Phy_1",12.23,10, LocalDate.now(), subject);
 	
		entityManager.persist(book);

		List<Book> bookList = repository.findByTitle("Phy_1");
		Book bk = bookList.get(0);

		assertEquals(book.getBookid(), bk.getBookid());

	}

	@Test
	public void search() {
		String bookTitle = "Chem_1";
		String bookTitle2 = "Chem_2";
		Subject subject=new Subject("Chemistry", 121, new HashSet<Book>());
		
 
		Book book =  new Book(bookTitle,11.11,11, LocalDate.now(), subject);
		entityManager.persist(book);
		book =  new Book(bookTitle2,22.22,22, LocalDate.now(), subject);
		entityManager.persist(book);
		

	 

		List<Book> bookList1 = repository.search("chem");

		assertEquals(2, bookList1.size());

	}

}
