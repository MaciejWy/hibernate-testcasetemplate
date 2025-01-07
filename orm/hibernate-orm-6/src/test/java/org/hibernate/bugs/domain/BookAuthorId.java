package org.hibernate.bugs.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import java.io.Serializable;

@Embeddable
public class BookAuthorId implements Serializable {

  public BookAuthorId(ISBN isbn, String name) {
    this.isbn = isbn;
    this.name = name;
  }

  public BookAuthorId() {

  }

  @Embedded
  private ISBN isbn;

  @Column(name = "name", nullable = false)
  private String name;


  // Getters and setters
  public ISBN getIsbn() {
    return isbn;
  }

  public void setIsbn(ISBN isbn) {
    this.isbn = isbn;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
