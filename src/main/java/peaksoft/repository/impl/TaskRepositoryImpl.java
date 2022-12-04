package peaksoft.repository.impl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Lesson;
import peaksoft.model.Task;
import peaksoft.repository.TaskRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional


public class TaskRepositoryImpl implements TaskRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Lesson> getAlLTasks(Long lessonId) {
        return entityManager.createQuery("select t from Task t where t.lesson.id= :id", Lesson.class)
                .setParameter("id", lessonId).getResultList();
    }

    @Override
    public void addTask(Long id, Task task) {
        Lesson lesson = entityManager.find(Lesson.class,id);
        lesson.addTask(task);
        task.setLesson(lesson);
        entityManager.merge(lesson);

    }

    @Override
    public Task getTaskById(Long id) {
        return entityManager.find(Task.class,id);
    }

    @Override
    public void updateTask(Task task, Long id) {
        Task task1 = entityManager.find(Task.class,id);
        task1.setName(task.getName());
        task1.setDeadline(task.getDeadline());
        task1.setText(task.getText());
        entityManager.merge(task1);

    }

    @Override
    public void deleteTask(Long id) {
        entityManager.remove(entityManager.find(Task.class,id));

    }
}