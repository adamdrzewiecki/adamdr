package com.hp;

import com.hp.model.dao.TaskDao;
import com.hp.model.domain.Task;
import lombok.Data;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named(value = "checkListBean")
@ApplicationScoped
@Data
public class CheckListBean {

    private List<Task> taskList;
    private String textArea;
    @Inject
    TaskDao taskDao;

    @PostConstruct
    public void setupResources() {
        taskList = taskDao.getAll();
    }

    public void add() {
        taskDao.addTask(new Task(taskDao.getLastId() + 1, textArea));
    }

    public void remove(Task task) {
        taskDao.deleteTask(task);
    }

    public void clear() {
        taskDao.clearList();
    }
}
