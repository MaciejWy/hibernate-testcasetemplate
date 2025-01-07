package org.hibernate.bugs.repository;

import org.hibernate.bugs.domain.Book;
import org.hibernate.bugs.domain.ISBN;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, ISBN> {

    @Query("SELECT book FROM Book book LEFT JOIN FETCH book.authors LEFT JOIN FETCH book.labels WHERE book.isbn = ?1")
    Book findByISBN(ISBN isbn);
}
