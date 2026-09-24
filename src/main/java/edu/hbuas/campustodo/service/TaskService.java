package edu.hbuas.campustodo.service;

import edu.hbuas.campustodo.model.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * 任务应用服务。学生将在功能分支中逐步扩展该类。
 */
public class TaskService {
    private final List<Task> tasks = new ArrayList<>();
    private long nextId = 1;

    public Task addTask(String title) {
        Task task = new Task(nextId++, title);
        tasks.add(task);
        return task;
    }

    public List<Task> listAll() {
        return List.copyOf(tasks);
    }
        /** 按编号完成任务；编号不存在或已重复完成时抛出异常。 */
    public void completeTask(long id) {
        Task target = null;
        for (Task t : listAll()) {
            if (t.getId() == id) {
                target = t;
                break;
            }
        }
        if (target == null) {
            throw new IllegalArgumentException("任务不存在: id=" + id);
        }
        if (target.isCompleted()) {
            throw new IllegalStateException("任务已完成，不能重复完成: id=" + id);
        }
                target.complete();

    }

}
