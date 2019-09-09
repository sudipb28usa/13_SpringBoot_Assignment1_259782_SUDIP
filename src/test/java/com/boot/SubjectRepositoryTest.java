package com.boot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
public class SubjectRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private SubjectRepository repository;

	@Test
	public void findBySubtitle() {
		Subject subject = new Subject("Test1", 121, new HashSet<Book>());
		entityManager.persist(subject);

		List<Subject> subjectList1 = repository.findBySubtitle("Test1");
		Subject sub = subjectList1.get(0);

		assertEquals(subject.getSubjectId(), sub.getSubjectId());

	}

	@Test
	public void search() {

		String subTitle = "Test1";
		String subTitle2 = "Test2";

		Subject subject = new Subject(subTitle, 111, new HashSet<Book>());
		entityManager.persist(subject);
		subject = new Subject(subTitle2, 222, new HashSet<Book>());
		entityManager.persist(subject);

		List<Subject> subjectList1 = repository.search("test");

		assertEquals(2, subjectList1.size());

	}

}
