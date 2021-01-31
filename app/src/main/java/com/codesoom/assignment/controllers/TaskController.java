package com.codesoom.assignment.controllers;

import com.codesoom.assignment.exceptions.TaskNotFoundException;
import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repositories.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    private Long id = 0L;
    private TaskRepository taskRepository;

    public TaskController() {
        taskRepository = new TaskRepository();
    }

    @GetMapping
    public List<Task> list() {

        return taskRepository.getTasks();
    }

    @GetMapping("{id}")
    public Task detail(@PathVariable Long id) {
        return taskRepository.getTask(id);
    }


    @PostMapping
    public Task create(@RequestBody Task task) {
        task.setId(generateId());
        taskRepository.getTasks().add(task);
        return task;
    }


    @PatchMapping("{id}")
    public Task update(@PathVariable Long id, @RequestBody Task source) {
        Task task = taskRepository.getTask(id);
        task.setTitle(source.getTitle());
        return task;
    }


//
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity <Task> delete(@PathVariable Long id) throws IOException {
        Task task = taskRepository.getTask(id);
        taskRepository.deleteTask(task);

        return ResponseEntity.noContent().build();
    }


    private Long generateId() {
        id++;
        return id;
    }

}
