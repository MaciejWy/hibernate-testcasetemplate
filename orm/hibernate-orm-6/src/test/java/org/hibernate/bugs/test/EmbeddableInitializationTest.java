package org.hibernate.bugs.test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.bugs.App;
import org.hibernate.bugs.domain.ISBN;
import org.hibernate.bugs.domain.Book;
import org.hibernate.bugs.domain.BookAuthor;
import org.hibernate.bugs.domain.BookAuthorId;
import org.hibernate.bugs.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.support.TransactionTemplate;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

@SpringBootTest
@ContextConfiguration(classes = {
        App.class
})
public class EmbeddableInitializationTest {

    @Autowired
    private BookRepository bookRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Test
    public void HHH19021RepositoryTest() {
        // given
        List<String> authors = List.of("John Doe", "Shakespeare");
        String isbn = "2348723847823483";
        Book book = prepareBook(isbn, authors);

        // when
        bookRepository.save(book);

        // then
        Book existingBook = bookRepository.findByISBN(new ISBN(isbn));
        assert existingBook != null;
    }

    @Test
    public void HHH19021EntityManagerTest() {
        // given
        List<String> authors = List.of("John Doe", "Shakespeare");
        String isbn = "2348723847823484";
        Book book = prepareBook(isbn, authors);

        // when
        transactionTemplate.execute(status -> {
            entityManager.persist(book);
            return null;
        });

        // then
        Book returnedBook = transactionTemplate.execute(status -> {
            var query = entityManager.createQuery("SELECT book FROM Book book LEFT JOIN FETCH book.authors LEFT JOIN FETCH book.labels WHERE book.isbn = ?1", Book.class);
            query.setParameter(1, new ISBN(isbn));
            return (Book) query.getSingleResult();
        });

        assert returnedBook != null && returnedBook.getIsbn().getValue().equals(isbn);
    }

    private static Book prepareBook(String isbn, List<String> authors) {
        return new Book(
                new ISBN(isbn),
                new HashSet<>(Set.of(
                        new BookAuthor(new BookAuthorId(new ISBN(isbn), authors.get(0))),
                        new BookAuthor(new BookAuthorId(new ISBN(isbn), authors.get(1)))
                )),
                new HashSet<>()
        );
    }
}