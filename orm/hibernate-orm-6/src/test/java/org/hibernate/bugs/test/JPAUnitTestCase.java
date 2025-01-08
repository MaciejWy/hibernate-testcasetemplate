package org.hibernate.bugs.test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.bugs.domain.ISBN;
import org.hibernate.bugs.domain.Book;
import org.hibernate.bugs.domain.BookAuthor;
import org.hibernate.bugs.domain.BookAuthorId;
import org.junit.jupiter.api.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using the Java Persistence API.
 */
class JPAUnitTestCase {

	private EntityManagerFactory entityManagerFactory;

	@BeforeEach
	void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory( "templatePU");
	}

	@AfterEach
	void destroy() {
		entityManagerFactory.close();
	}

	// Entities are auto-discovered, so just add them anywhere on class-path
	// Add your tests, using standard JUnit.
	@Test
	void HHH19021WithoutSpringBootTest() throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		String isbn = "2348723847823483";
		Set<BookAuthor> authors = new HashSet<>();
		authors.add(new BookAuthor(new BookAuthorId(new ISBN(isbn), "John Doe")));
		authors.add(new BookAuthor(new BookAuthorId(new ISBN(isbn), "Shakespeare")));

		Book book = new Book(
			new ISBN(isbn),
			authors,
			new HashSet<>()
		);

		entityManager.persist(book);
		entityManager.getTransaction().commit();

		entityManager.getTransaction().begin();
		List<Book> returnedBooks = entityManager.createQuery("SELECT book FROM Book book LEFT JOIN FETCH book.authors LEFT JOIN FETCH book.labels WHERE book.isbn = ?1")
				.setParameter(1, new ISBN(isbn))
				.getResultList();

		assert returnedBooks.size() == 1;
		entityManager.getTransaction().commit();

		entityManager.close();
	}
}
