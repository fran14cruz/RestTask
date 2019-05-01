package me.restapi.controller;

import me.restapi.model.Task;
import me.restapi.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping(value = "/task")
    public UUID createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @GetMapping(path = "task/{id}")
    public @ResponseBody
    Task getTask(@PathVariable UUID id) {
        return taskService.getTask(id);
    }
}
