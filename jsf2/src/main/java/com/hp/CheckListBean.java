package com.hp;

import com.hp.model.dao.TaskDao;
import com.hp.model.domain.Task;
import lombok.Data;
import lombok.extern.log4j.Log4j;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named(value = "checkListBean")
@ApplicationScoped
@Data
@Log4j
public class CheckListBean {

    private List<Task> taskList;
    private List<Task> selectedTasks;
    private String textArea;
    @Inject
    TaskDao taskDao;

    @PostConstruct
    public void setupResources() {
        taskList = taskDao.getAll();
        selectedTasks = new ArrayList<>();
    }

    public void addTask() {
        taskDao.addTask(new Task(taskDao.getLastId() + 1, textArea));
    }

    public void removeTask(Task task) {
        log.warn("Removing task: " + task);
        taskDao.deleteTask(task);
    }

    public void clear() {
        taskDao.clearList();
    }

    public void removeSelectedItems() {
        for (int i = 0; i < selectedTasks.size(); i++) {
            removeTask(selectedTasks.get(i));
        }
    }
}
