package com.boot;

 
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name = "book")
public class Book   {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Override
	public String toString() {
		return "Book [bookid=" + bookid + ", title=" + title + ", price=" + price + ", volume=" + volume
				+ ", publishdatelocal=" + publishdatelocal + "]";
	}
	
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long bookid;
	

	private String title;
    

	private double price ;
	

	private Integer volume ;
	 
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	private Date publishdate ;
    
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	private transient LocalDate publishdatelocal ;
    
 
    @OneToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinTable(name = "subjectreference", joinColumns = { @JoinColumn(name = "bookid") }, inverseJoinColumns = {
	@JoinColumn(name = "bookid") })
    private  Subject subject;
    
	private transient Long subjectid ;

	/**
	 * @return the subject
	 */
	public Subject getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Book() {
    }
    
    public Book(Long bookId, String title, double price, Integer volume, LocalDate publishDate , Subject subject ) {
        this.bookid = bookId;
        this.title = title;
        this.price = price;
        this.volume = volume;
        this.publishdatelocal = publishDate;
        this.subject=subject;
      
    }
    
    public Book(String title, double price, Integer volume, LocalDate publishDate, Subject subject  ) {
        this.title = title;
        this.price = price;
        this.volume = volume;
        this.publishdatelocal = publishDate;
        this.subject=subject;
    }
 

	public Long getBookId() {
		return bookid;
	}

	public void setBookId(Long bookId) {
		this.bookid = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getVolume() {
		return volume;
	}

	public void setVolume(Integer volume) {
		this.volume = volume;
	}

	/*public LocalDate getPublishDate() {
		
		LocalDate dateLocal = this.publishdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		this.publishdatelocal=dateLocal;
		return publishdatelocal;
	}*/

	/*public void setPublishDate(LocalDate publishDate) {--d
		Date utilDate = Date.from(publishDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		this.publishdate=utilDate;
		this.publishdatelocal = publishDate;
	}
	*/
    /**
	 * @return the publishdate
	 */
	/**public Date getPublishDateSQL() {
		return publishdate;
	}*/

	/**
	 * @param publishdate the publishdate to set-d
	 */
	/*public void setPublishDateSQL(Date publishDateSQL) {
		this.publishdate = publishDateSQL;
		LocalDate dateLocal = this.publishdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		this.publishdatelocal=dateLocal;
	}*/
	
	
	
	

	public Long getSubjectId() {
		return subjectid;
	}

	public Long getBookid() {
		return bookid;
	}

	public void setBookid(Long bookid) {
		this.bookid = bookid;
	}

	public Date getPublishdate() {
		return publishdate;
	}

	public void setPublishdate(Date publishdate) {
		
		this.publishdate = publishdate;
		
		LocalDate dateLocal = this.publishdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		this.publishdatelocal=dateLocal;
 
	}

	public LocalDate getPublishdatelocal() {
		
	LocalDate dateLocal = this.publishdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		this.publishdatelocal=dateLocal;
 
		
		return publishdatelocal;
	}

	public void setPublishdatelocal(LocalDate publishdatelocal) {
		this.publishdatelocal = publishdatelocal;
		Date utilDate = Date.from(publishdatelocal.atStartOfDay(ZoneId.systemDefault()).toInstant());
		this.publishdate=utilDate;
	}

	public Long getSubjectid() {
		return subjectid;
	}

	public void setSubjectid(Long subjectid) {
		this.subjectid = subjectid;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectid = subjectId;
	}


	
	
	
	/*
	
Develop a menu driven console based java application to facilitate the below 
operations using Hibernate DAO
a.
Add a Book
b.
Delete a Subject
c.
Delete a book
d.
Search for a book
e.
Search for a subject
f.
Exit


	 * */


}
