package org.hibernate.bugs.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NamedEntityGraph(
  name = "book-with-basic-relations",
  attributeNodes = {
    @NamedAttributeNode("authors"),
    @NamedAttributeNode("labels")
  }
)
@Entity
@Table(name = "book")
public class Book implements Serializable {

  @EmbeddedId
  private ISBN isbn;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "book")
  private Set<BookAuthor> authors = new HashSet<>();

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "book")
  private Set<BookLabel> labels = new HashSet<>();

  public Book(ISBN isbn, Set<BookAuthor> authors, Set<BookLabel> labels) {
    this.isbn = isbn;
    this.authors = authors;
    this.labels = labels;
  }

  public Book() {

  }

  // Getters and setters
  public ISBN getIsbn() {
    return isbn;
  }

  public void setIsbn(ISBN isbn) {
    this.isbn = isbn;
  }

  public Set<BookAuthor> getAuthors() {
    return authors;
  }

  public void setAuthors(Set<BookAuthor> authors) {
    this.authors = authors;
  }

  public Set<BookLabel> getLabels() {
    return labels;
  }

  public void setLabels(Set<BookLabel> labels) {
    this.labels = labels;
  }
}

