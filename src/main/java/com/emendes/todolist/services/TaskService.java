package com.emendes.todolist.services;

import java.util.List;
import java.util.stream.Collectors;

import com.emendes.todolist.dto.TaskDto;
import com.emendes.todolist.entities.Task;
import com.emendes.todolist.repositories.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
  
  @Autowired
  private TaskRepository taskRepository;

  // Método para buscar todas as tasks
  public List<TaskDto> findAll(){
    List<Task> response = taskRepository.findAll();
    return response.stream().map(task -> new TaskDto(task)).collect(Collectors.toList());
  }

  // Método para salvar uma task.
  public Task saveTask(Task task){
    return taskRepository.save(task);
  }

  // Método para deletar uma task
  public void deleteTask(int id){
    taskRepository.deleteById(id);
  }

  // Método para atualizar uma task.
  public Task updateTask(int id, Task newTask){
    return taskRepository.findById(id).map(task -> {
      task.setDescription(newTask.getDescription());
      task.setIsConcluded(newTask.getIsConcluded());
      Task updatedTask = taskRepository.save(task);
      return updatedTask;
    }).orElse(null);
  }

  // Método para atualizar o status de concluded de uma task.
  public Task updateStatus(int id, boolean status){
    return taskRepository.findById(id).map(task -> {
      task.setIsConcluded(status);
      Task updatedTask = taskRepository.save(task);
      return updatedTask;
    }).orElse(null);
  }

  // Método para encontrar uma task por id.
  public Task findById(int id){
    return taskRepository.findById(id).map(taskOp -> {
      Task task = new Task(taskOp.getId(), taskOp.getDescription(), taskOp.getIsConcluded());
      return task;
    }).orElse(null);
  }

}
