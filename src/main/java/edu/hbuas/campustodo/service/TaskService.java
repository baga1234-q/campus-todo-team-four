package edu.hbuas.campustodo.service;

import edu.hbuas.campustodo.model.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {

    // ---------- 原有基线测试 ----------
    @Test
    void addTask_success() {
        TaskService service = new TaskService();
        Task task = service.addTask("测试任务");
        assertNotNull(task);
        assertEquals("测试任务", task.getTitle());
        assertEquals(1L, task.getId());
        assertFalse(task.isCompleted());
    }

    @Test
    void addTask_emptyTitle_throwException() {
        TaskService service = new TaskService();
        assertThrows(IllegalArgumentException.class, () ->
                service.addTask("")
        );
    }

    // ---------- 新增：任务完成功能测试 ----------
    // 1. 正常完成任务，状态从false变为true
    @Test
    void completeTask_success() {
        TaskService service = new TaskService();
        Task task = service.addTask("完成实验报告");
        assertFalse(task.isCompleted());

        service.completeTask(task.getId());
        assertTrue(task.isCompleted());
    }

    // 2. 完成不存在的任务ID，抛出IllegalArgumentException
    @Test
    void completeTask_notFoundId_throwException() {
        TaskService service = new TaskService();
        assertThrows(IllegalArgumentException.class, () ->
                service.completeTask(999L)
        );
    }

    // 3. 重复完成已完成的任务，抛出IllegalStateException
    @Test
    void completeTask_alreadyCompleted_throwException() {
        TaskService service = new TaskService();
        Task task = service.addTask("重复完成测试");
        service.completeTask(task.getId());

        assertThrows(IllegalStateException.class, () ->
                service.completeTask(task.getId())
        );
    }
}
