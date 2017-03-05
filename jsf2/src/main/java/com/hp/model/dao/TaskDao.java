package com.hp.model.dao;

import com.hp.model.domain.Task;

import java.util.List;

public interface TaskDao {

    List<Task> getAll();

    void addTask(Task task);

    void deleteTask(Task task);

    int getLastId();

    void clearList();
}
