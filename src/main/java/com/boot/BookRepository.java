package com.boot;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long>   {
	
	@Query(value = "SELECT b FROM Book b WHERE b.title LIKE '%' || :keyword || '%'"
			+ " OR b.price LIKE '%' || :keyword || '%'"			 
			+ " OR b.volume LIKE '%' || :keyword || '%'")
    public List<Book> search(@Param("keyword") String keyword);
	
	public List<Book> findByTitle(String title);

}
