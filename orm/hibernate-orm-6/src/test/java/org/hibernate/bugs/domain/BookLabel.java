package org.hibernate.bugs.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import java.io.Serializable;

@Entity
@Table(name = "book_label")
public class BookLabel implements Serializable {

  @EmbeddedId
  private BookLabelId bookLabelId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
    name = "isbn",
    referencedColumnName = "isbn",
    insertable = false,
    nullable = false,
    updatable = false
  )
  @MapsId("bookLabelId.isbn")
  @NotFound(action = NotFoundAction.IGNORE)
  private Book book;

  // Getters and setters
  public BookLabelId getBookLabelId() {
    return bookLabelId;
  }

  public void setBookLabelId(BookLabelId bookLabelId) {
    this.bookLabelId = bookLabelId;
  }

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }
}

