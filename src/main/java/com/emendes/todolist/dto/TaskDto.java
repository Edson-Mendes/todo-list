package com.emendes.todolist.dto;

import com.emendes.todolist.entities.Task;

public class TaskDto {
  
  private int id;
  private String description;
  private boolean isConcluded;

  public TaskDto(Task task){
    this.id = task.getId();
    this.description = task.getDescription();
    this.isConcluded = task.getIsConcluded();
  }

  public TaskDto(int id, String description, boolean isConcluded){
    this.id = id;
    this.description = description;
    this.isConcluded = isConcluded;
  }

  public TaskDto(){}

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

  public String toString(){
    return "{id: "+this.id+", description: "+this.description+", isConcluded: "+this.isConcluded+"}";
  }
}
