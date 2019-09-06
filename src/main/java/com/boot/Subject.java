package com.boot;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonNaming;

@Entity
 
@Table(name = "Subject")
public class Subject   {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Subject [subjectid=" + subjectid + ", subtitle=" + subtitle + ", durationinhours=" + durationinhours
				+ ", references=" + bookSet + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long subjectid;


	private String subtitle;


	private int durationinhours;

	@OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinTable(name = "subjectreference", joinColumns = { @JoinColumn(name = "subjectid") }, inverseJoinColumns = {
	@JoinColumn(name = "bookid") })
	private Set<Book> bookSet = new HashSet<Book>();

	public Subject() {
	}

	public Subject(Long subjectId, String subtitle, int durationInHours, Set<Book> bookSet) {
		this.subjectid = subjectId;
		this.subtitle = subtitle;
		this.durationinhours = durationInHours;
		this.bookSet = bookSet;
	}

	public Subject(String subtitle, int durationInHours, Set<Book> references) {
		this.subtitle = subtitle;
		this.durationinhours = durationInHours;
		this.bookSet = references;
	}

	public Long getSubjectId() {
		return subjectid;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectid = subjectId;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public int getDurationInHours() {
		return durationinhours;
	}

	public void setDurationInHours(int durationInHours) {
		this.durationinhours = durationInHours;
	}

	public Set<Book> getReferences() {
		if (bookSet == null) {
			return Collections.emptySet();
		}
		return this.bookSet;
	}

	public void setReferences(Set<Book> bookSet) {
		this.bookSet = bookSet;
	}

}
