package peaksoft.repository.impl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Course;
import peaksoft.model.Instructor;
import peaksoft.model.Lesson;
import peaksoft.repository.LessonRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional

public class LessonRepositoryImpl implements LessonRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Lesson> getAllLessons(Long courseId) {
        return entityManager.createQuery("select l from Lesson l where l.course.id= :id", Lesson.class)
                .setParameter("id", courseId).getResultList();
    }

    @Override
    public void addLesson(Long id, Lesson lesson) {
        Course course = entityManager.find(Course.class,id);
        course.addLesson(lesson);
        lesson.setCourse(course);
        entityManager.merge(lesson);


    }

    @Override
    public Lesson getLessonById(Long id) {
        return entityManager.find(Lesson.class,id);
    }

    @Override
    public void updateLesson(Lesson lesson, Long id) {
        Lesson lesson1 = entityManager.find(Lesson.class,id);
        lesson1.setLessonName(lesson.getLessonName());
        entityManager.merge(lesson1);
    }

    @Override
    public void deleteLesson(Long id) {
        entityManager.remove(entityManager.find(Lesson.class, id));
    }
}