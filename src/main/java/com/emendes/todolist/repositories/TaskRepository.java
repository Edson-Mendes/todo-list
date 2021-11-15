package com.emendes.todolist.repositories;

import com.emendes.todolist.entities.Task;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer>{
  
}
