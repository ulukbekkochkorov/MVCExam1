package peaksoft.service.impl;

import org.springframework.stereotype.Service;
import peaksoft.model.Course;
import peaksoft.repository.CourseRepository;
import peaksoft.service.CourseService;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAllCourses(Long id) {
        return courseRepository.getAllCourses(id);
    }

    @Override
    public void addCourse(Long id, Course course) {
        courseRepository.addCourse(id, course);
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.getCourseById(id);
    }

    @Override
    public void updateCourse(Course course, Long id) {
        courseRepository.updateCourse(course, id);
    }

    @Override
    public void deleteByIdCourse(Long id) {
        courseRepository.deleteByIdCourse(id);
    }
}
