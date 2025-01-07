package org.hibernate.bugs.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import java.io.Serializable;

@Entity
@Table(name = "book_author")
public class BookAuthor implements Serializable {

  @EmbeddedId
  private BookAuthorId bookAuthorId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
    name = "isbn",
    referencedColumnName = "isbn",
    insertable = false,
    nullable = false,
    updatable = false
  )
  @MapsId("bookAuthorId.isbn")
  @NotFound(action = NotFoundAction.IGNORE)
  private Book book;

  public BookAuthor(BookAuthorId bookAuthorId) {
    this.bookAuthorId = bookAuthorId;
  }

  public BookAuthor() {

  }

  // Getters and setters
  public BookAuthorId getBookAuthorId() {
    return bookAuthorId;
  }

  public void setBookAuthorId(BookAuthorId bookAuthorId) {
    this.bookAuthorId = bookAuthorId;
  }

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }
}

