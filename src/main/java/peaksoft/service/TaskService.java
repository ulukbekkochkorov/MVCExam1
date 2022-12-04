package peaksoft.service;

import peaksoft.model.Lesson;
import peaksoft.model.Task;

import java.util.List;

public interface TaskService {
    List<Lesson> getAllTasks(Long lessonId);

    void addTask(Long id, Task task);

    Task getTaskById(Long id);

    void updateTask(Task task, Long id);

    void deleteTask(Long id);
}