package me.restapi.controller;

import me.restapi.model.Task;
import me.restapi.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

@RestController
@Validated
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping(value = "/task")
    public ResponseEntity<UUID> createTask() {
        return new ResponseEntity<>(taskService.createTask(), HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/task/{id}")
    public ResponseEntity<Task> getTask(@PathVariable("id") @NotNull UUID id) {

        Task task = taskService.getTask(id);

        if (task == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @ExceptionHandler(value = { ConstraintViolationException.class })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleResourceNotFoundException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        StringBuilder strBuilder = new StringBuilder();
        for (ConstraintViolation<?> violation : violations ) {
            strBuilder.append(violation.getMessage() + "\n");
        }
        return strBuilder.toString();
    }
}
