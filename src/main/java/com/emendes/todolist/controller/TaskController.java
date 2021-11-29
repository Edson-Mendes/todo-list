package com.emendes.todolist.controller;

import java.util.List;

import com.emendes.todolist.dto.TaskDto;
import com.emendes.todolist.entities.Task;
import com.emendes.todolist.services.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
// @RequestMapping()
public class TaskController {

  @Autowired
  private TaskService taskService;

  @RequestMapping("/")
  public String redirectToTasks() {
    return "redirect:/tasks";
  }

  // Método para encaminhar para tela da lista de tarefas.
  @RequestMapping("/tasks")
  public ModelAndView tasksList() {
    ModelAndView modelAndView = new ModelAndView("index");
    List<TaskDto> tasks = taskService.findAll();
    modelAndView.addObject("tasks", tasks);
    return modelAndView;
  }

  // Método para retornar a página de criação de tarefas
  @RequestMapping(value = "/createTask", method = RequestMethod.GET)
  public String form() {
    return "task/createTask";
  }

  // Método para salvar uma tarefa.
  @RequestMapping(value = "/createTask", method = RequestMethod.POST)
  public String form(TaskDto taskDto, RedirectAttributes attributes) {

    if (taskDto.getDescription() == null || taskDto.getDescription().isEmpty()) {
      // System.err.println("Campo description está vazio!!!");

      attributes.addFlashAttribute("message", "Campo vazio!");
      return "redirect:/createTask";
    }

    taskDto.setIsConcluded(false);
    Task task = fromDto(taskDto);
    taskService.saveTask(task);

    attributes.addFlashAttribute("successMessage", "Tarefa criada com sucesso!");

    return "redirect:/createTask";
  }

  // Método para encaminhar para página de atualização de tarefa
  @RequestMapping(value = "updateTask/{id}", method = RequestMethod.GET)
  public ModelAndView updateForm(@PathVariable int id) {
    // TODO: Validar id, se corresponde a alguma task salvo no banco de dados.
    ModelAndView modelAndView = new ModelAndView("task/updateTask");
    TaskDto task = new TaskDto(taskService.findById(id));
    modelAndView.addObject("task", task);

    return modelAndView;
  }

  // Método para atualizar uma tarefa.
  @RequestMapping(value = "updateTask/{id}", method = RequestMethod.POST)
  public String updateTask(@PathVariable int id, TaskDto taskDto, RedirectAttributes attributes) {

    if (taskDto.getDescription() == null || taskDto.getDescription().isEmpty()) {

      attributes.addFlashAttribute("message", "Campo vazio!");
      return "redirect:/updateTask/" + id;
    }

    Task task = fromDto(taskDto);
    taskService.updateTask(id, task);

    attributes.addFlashAttribute("successMessage", "Tarefa atualizada com sucesso!");

    return "redirect:/tasks";
  }

  // Método para atualizar o status da task.
  @RequestMapping("/updateStatus")
  public String updateStatus(int id, boolean status) {
    // TODO: Verificar se retornou null, se sim, adicionar msg de erro.
    taskService.updateStatus(id, !status);

    return "redirect:/tasks";
  }

  // Método para deletar uma tarefa.
  @RequestMapping("/deleteTask")
  public String deleteTask(int id) {
    taskService.deleteTask(id);
    return "redirect:/tasks";
  }

  // Método para converter uma taskdto em task
  private Task fromDto(TaskDto taskDto) {
    Task task = new Task(0, taskDto.getDescription(), taskDto.getIsConcluded());
    return task;
  }

}
