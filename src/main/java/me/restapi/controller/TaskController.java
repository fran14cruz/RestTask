package me.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping(value = "/task")
    public Long createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @GetMapping(path = "task/{id}")
    public @ResponseBody
    Task getTask(@PathVariable Long id) {
        return taskService.getTaks(id);
    }
}
