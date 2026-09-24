package edu.hbuas.campustodo.service;

import edu.hbuas.campustodo.model.Priority;
import edu.hbuas.campustodo.model.Task;
import edu.hbuas.campustodo.model.Task.Priority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    // 优先级筛选功能（来自main分支）
    public List<Task> filterByPriority(Priority priority) {
        return tasks.stream()
                .filter(task -> task.getPriority() == priority)
                .collect(Collectors.toList());
    }

    // 任务完成功能（你的分支）
    public void completeTask(long id) {
        Task target = null;
        for (Task t : listAll()) {
            if (t.getId() == id) {
                target = t;
                break;
            }
        }
        if (target == null) {
            throw new IllegalArgumentException("任务不存在：id=" + id);
        }
        if (target.isCompleted()) {
            throw new IllegalStateException("任务已完成，不能重复完成：id=" + id);
        }
        target.complete();
    /**
     * 按优先级筛选任务（Issue #1）。
     * 返回匹配优先级的任务快照；无匹配时返回空列表（非 null）；priority 为 null 时抛出异常。
     */
    // CI 触发：确认工作流已在 main 生效（#4 合并后）
    public List<Task> filterByPriority(Priority priority) {
        if (priority == null) {
            throw new IllegalArgumentException("priority 不能为 null");
        }
        List<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getPriority() == priority) {
                result.add(t);
            }
        }
        return List.copyOf(result);
    }
}
