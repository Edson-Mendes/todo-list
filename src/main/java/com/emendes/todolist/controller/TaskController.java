package com.emendes.todolist.controller;

import java.util.List;

import com.emendes.todolist.dto.TaskDto;
import com.emendes.todolist.entities.Task;
import com.emendes.todolist.services.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/tasks")
public class TaskController {
  
  @Autowired
  private TaskService taskService;

  @GetMapping
  public ResponseEntity<List<TaskDto>> findAll(){
    List<TaskDto> listTask = taskService.findAll();
    return ResponseEntity.ok(listTask);
  }

  @PostMapping
  @ResponseBody
  public ResponseEntity<TaskDto> saveTask(@RequestBody TaskDto taskDto){
    Task taskEntity = fromDto(taskDto);
    taskService.saveTask(taskEntity);
    return new ResponseEntity<TaskDto>(taskDto, HttpStatus.CREATED);
  }

  private Task fromDto(TaskDto taskDto){
    Task task = new Task(0, taskDto.getDescription(), taskDto.getIsConcluded());
    return task;
  }

}
