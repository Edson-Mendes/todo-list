package com.emendes.todolist.controller;


import java.util.List;

import com.emendes.todolist.dto.TaskDto;
import com.emendes.todolist.entities.Task;
import com.emendes.todolist.services.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndView;

@Controller
// @RequestMapping()
public class TaskController {

  @Autowired
  private TaskService taskService;

  // Método para retornar a página de criação de tarefas
  @RequestMapping(value = "/createTask", method = RequestMethod.GET)
  public String form() {
    return "task/taskForm";
  }

  // Método para salvar uma tarefa.
  @RequestMapping(value = "/createTask", method = RequestMethod.POST)
  public String form(TaskDto taskDto) {
    taskDto.setIsConcluded(false);
    Task task = fromDto(taskDto);
    taskService.saveTask(task);
    return "redirect:/createTask";
  }

  @RequestMapping("/tasks")
  public ModelAndView tasksList(){
    ModelAndView modelAndView = new ModelAndView("index");
    List<TaskDto> tasks = taskService.findAll();
    System.out.println(tasks);
    modelAndView.addObject("tasks", tasks);
    return modelAndView;
  }

  // Método para converter uma taskdto em task
  private Task fromDto(TaskDto taskDto) {
    Task task = new Task(0, taskDto.getDescription(), taskDto.getIsConcluded());
    return task;
  }

}
