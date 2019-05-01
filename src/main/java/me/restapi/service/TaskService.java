package me.restapi.service;

import me.restapi.dao.TaskDao;
import me.restapi.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    private TaskDao taskDao;

    public UUID createTask(Task task) {
        return taskDao.createTask(task);
    }

    public Task getTask(UUID id) {
        return taskDao.getTask(id);
    }
 }
