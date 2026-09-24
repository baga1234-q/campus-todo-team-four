package edu.hbuas.campustodo.service;

import edu.hbuas.campustodo.model.Task;
import edu.hbuas.campustodo.model.Task.Priority;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskServiceTest {
    @Test
    void shouldAddTask() {
        TaskService service = new TaskService();
        var task = service.addTask("完成需求评审");
        assertEquals(1L, task.getId());
        assertEquals("完成需求评审", task.getTitle());
        assertFalse(task.isCompleted());
        assertEquals(1, service.listAll().size());
    }

    @Test
    void shouldRejectBlankTitle() {
        TaskService service = new TaskService();
        assertThrows(IllegalArgumentException.class,
                () -> service.addTask("   "));
    }



    @Test
    void addTask_shouldCreateWithDefaultMediumPriority() {
        TaskService service = new TaskService();
        Task task = service.addTask("写实验报告");
        assertEquals(Priority.MEDIUM, task.getPriority());
        assertFalse(task.isCompleted());
    }


    @Test
    void filterByPriority_shouldReturnMatchingTasks() {
        TaskService service = new TaskService();
        Task high1 = service.addTask("交作业");
        high1.setPriority(Priority.HIGH);
        service.addTask("买水");                   
        Task high2 = service.addTask("准备答辩");
        high2.setPriority(Priority.HIGH);

        List<Task> result = service.filterByPriority(Priority.HIGH);

        assertEquals(2, result.size());
        assertTrue(result.contains(high1));
        assertTrue(result.contains(high2));
    }


    @Test
    void filterByPriority_noMatch_shouldReturnEmptyList() {
        TaskService service = new TaskService();
        service.addTask("普通任务");               

        List<Task> result = service.filterByPriority(Priority.LOW);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

 
    @Test
    void filterByPriority_null_shouldThrow() {
        TaskService service = new TaskService();
        service.addTask("任务");
        assertThrows(IllegalArgumentException.class,
                () -> service.filterByPriority(null));
    }
  
    @Test
    void setPriority_null_shouldThrow() {
        TaskService service = new TaskService();
        Task task = service.addTask("任务");
        assertThrows(IllegalArgumentException.class,
                () -> task.setPriority(null));
    }  
}
