package com.emendes.todolist.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_task")
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String description;
  private boolean isConcluded;

  public Task(int id, String description, boolean isConcluded){
    this.id = id;
    this.description = description;
    this.isConcluded = isConcluded;
  }

  public Task(){}

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean getIsConcluded() {
    return isConcluded;
  }

  public void setIsConcluded(boolean isConcluded) {
    this.isConcluded = isConcluded;
  }

}
