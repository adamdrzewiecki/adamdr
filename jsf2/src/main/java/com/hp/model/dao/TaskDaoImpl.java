package com.hp.model.dao;

import com.hp.model.domain.Task;
import lombok.extern.log4j.Log4j;

import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
@Log4j
public class TaskDaoImpl implements TaskDao {
    private List<Task> listOfTasks;

    public TaskDaoImpl() {
        listOfTasks = new ArrayList<Task>();
        listOfTasks.add(new Task(0, "Task 1"));
        listOfTasks.add(new Task(1, "Task 2"));
        listOfTasks.add(new Task(2, "Task 3"));
    }

    @Override
    public List<Task> getAll() {
        return listOfTasks;
    }

    @Override
    public Task findById(int id) {
        for (int i = 0; i < listOfTasks.size(); i++) {
            Task task = listOfTasks.get(i);
            if(id == task.getId())
                return task;
        }
        return null;
    }

    @Override
    public void addTask(Task task) {
        listOfTasks.add(task);
    }

    @Override
    public void deleteTask(Task task) {
        listOfTasks.remove(task);
    }

    @Override
    public int getLastId() {
        try {
            int max = Integer.MIN_VALUE;
            for (Task task : listOfTasks) {
                int number = task.getId();
                if (number > max)
                    max = number;
            }
            return max;
        }
        catch (IndexOutOfBoundsException e) {
            log.error("IndexOutOfBoundsException :" + e);
        }
        catch (RuntimeException e) {
            log.error("RuntimeException :" + e);
        }
        return Integer.MIN_VALUE;
    }

    @Override
    public void clearList() {
        listOfTasks.clear();
    }
}
