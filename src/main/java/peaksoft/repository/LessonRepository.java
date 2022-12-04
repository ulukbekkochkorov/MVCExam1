package peaksoft.repository;

import peaksoft.model.Company;
import peaksoft.model.Course;
import peaksoft.model.Lesson;

import java.util.List;

    public interface LessonRepository {
        List<Lesson> getAllLessons(Long courseId);

        void addLesson(Long id, Lesson lesson);

        Lesson getLessonById(Long id);

        void updateLesson(Lesson lesson, Long id);

        void deleteLesson(Long id);
    }
