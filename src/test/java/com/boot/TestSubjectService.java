package com.boot;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TestSubjectService {

	@InjectMocks
	SubjectService service;

	@Mock
	SubjectRepository dao;

 

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void saveTest() {
		Subject subject = new Subject("Test1", 121, new HashSet<Book>());

		service.save(subject);

		verify(dao, times(1)).save(subject);
	}

	@Test
	public void listAllTest() {
		List<Subject> list = new ArrayList<Subject>();
		Subject subject = new Subject("Test1", 121, new HashSet<Book>());
		list.add(subject);
		subject = new Subject("Test2", 122, new HashSet<Book>());
		list.add(subject);
		subject = new Subject("Test3", 123, new HashSet<Book>());
		list.add(subject);
		subject = new Subject("Test4", 124, new HashSet<Book>());
		list.add(subject);

		when(dao.findAll()).thenReturn(list);

		List<Subject> subjectList = service.listAll();

		assertEquals(4, subjectList.size());
		verify(dao, times(1)).findAll();

	}

	@Test
	public void findBySubtitleTest() {

		String subTitle = "Test1";
		Subject subject = new Subject(subTitle, 111, new HashSet<Book>());

		List<Subject> subjectList = new ArrayList<Subject>();
		subjectList.add(subject);

		when(dao.findBySubtitle(subTitle)).thenReturn(subjectList);

		List<Subject> subjectList1 = service.findBySubtitle(subTitle);
		Subject sub = subjectList1.get(0);

		assertEquals("Test1", sub.getSubtitle());
		assertEquals(111, sub.getDurationInHours());

	}

	@Test
	public void searchTest() {

		String subTitle = "Test1";
		String subTitle2 = "Test2";
		List<Subject> list = new ArrayList<Subject>();

		Subject subject = new Subject(subTitle, 111, new HashSet<Book>());
		list.add(subject);
		subject = new Subject(subTitle2, 222, new HashSet<Book>());
		list.add(subject);

		when(dao.search("test")).thenReturn(list);

		List<Subject> subjectList = service.search("test");

		assertEquals(2, subjectList.size());
		verify(dao, times(1)).search("test");

	}

}
