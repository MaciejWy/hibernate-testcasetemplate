package org.hibernate.bugs.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import java.io.Serializable;

@Embeddable
public class BookLabelId implements Serializable {

  @Embedded
  private ISBN isbn;

  @Column(name = "label", nullable = false)
  private String label;

  // Getters and setters
  public ISBN getIsbn() {
    return isbn;
  }

  public void setIsbn(ISBN isbn) {
    this.isbn = isbn;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }
}
