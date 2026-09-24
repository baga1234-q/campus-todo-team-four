package edu.hbuas.campustodo.model;

import java.util.Objects;

/**
 * 校园待办任务。
 */
public class Task {
    private final long id;
    private final String title;
    private boolean completed;
    private Priority priority;

    /** 任务优先级：高 / 中 / 低 */
    public enum Priority {
        HIGH, MEDIUM, LOW
    }

    public Task(long id, String title) {
        if (id <= 0) {
            throw new IllegalArgumentException("任务编号必须为正数");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("任务标题不能为空");
        }
        this.id = id;
        this.title = title.trim();
        this.completed = false;
        this.priority = Priority.MEDIUM;   // 新建任务默认 MEDIUM（Issue #1 验收标准）
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void complete() {
        completed = true;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority == null ? Priority.MEDIUM : priority;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Task task)) {
            return false;
        }
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
