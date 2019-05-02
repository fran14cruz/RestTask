package me.restapi.service;

import me.restapi.dao.TaskDao;
import me.restapi.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    private TaskDao taskDao;

    public UUID createTask() {

        Task task = new Task();

        task.setStatus("created");
        task.setTimestamp(dateTimeIsoFormat());

        return taskDao.createTask(task);

    }

    public Task getTask(UUID id) {
        return taskDao.getTask(id);
    }

    private String dateTimeIsoFormat() {

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        return formatter.format(localDateTime);
    }

 }
