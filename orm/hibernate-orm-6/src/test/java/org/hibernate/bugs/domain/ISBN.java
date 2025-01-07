package org.hibernate.bugs.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ISBN implements Serializable {

  public ISBN(String value) {
    this.value = value;
  }

  public ISBN() {

  }

  @Column(name = "isbn", nullable = false)
  private String value;


  // Getters and setters
  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
