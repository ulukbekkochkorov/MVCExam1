package peaksoft.service;

import peaksoft.model.Course;

import java.util.List;

public interface CourseService {
    public List<Course> getAllCourses(Long id);
    void addCourse(Long id, Course course);
    Course getCourseById(Long id);
    void updateCourse(Course course, Long id);
    void deleteByIdCourse(Long id);
}
