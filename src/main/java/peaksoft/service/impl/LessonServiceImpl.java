package peaksoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Lesson;
import peaksoft.repository.LessonRepository;
import peaksoft.service.LessonService;

import java.util.List;
@Service

public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;

    @Autowired
    public LessonServiceImpl(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public List<Lesson> getAllLessons(Long courseId) {
        return lessonRepository.getAllLessons(courseId);
    }

    @Override
    public void addLesson(Long id, Lesson lesson) {
        lessonRepository.addLesson(id,lesson);

    }

    @Override
    public Lesson getLessonById(Long id) {
        return lessonRepository.getLessonById(id);
    }

    @Override
    public void updateLesson(Lesson lesson, Long id) {
        lessonRepository.updateLesson(lesson,id);

    }

    @Override
    public void deleteLesson(Long id) {
        lessonRepository.deleteLesson(id);

    }
}