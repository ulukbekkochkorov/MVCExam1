package peaksoft.repository.impl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Company;
import peaksoft.model.Course;
import peaksoft.repository.CourseRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class CourseRepositoryImpl implements CourseRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Course> getAllCourses(Long id) {
        return entityManager.createQuery("select c from Course c where c.company.id= :id", Course.class)
                .setParameter("id", id).getResultList();
    }

    @Override
    public void addCourse(Long id, Course course) {
        Company company = entityManager.find(Company.class, id);
        company.addCourse(course);
        course.setCompany(company);
        entityManager.merge(course);
        entityManager.merge(company);

    }

    @Override
    public Course getCourseById(Long id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    public void updateCourse(Course course, Long id) {
        Course course1 = entityManager.find(Course.class, id);
        course1.setName(course.getName());
        course1.setDescription(course.getDescription());
        entityManager.merge(course1);

    }

    @Override
    public void deleteByIdCourse(Long id) {
        Course course = entityManager.find(Course.class, id);
        course.setCompany(null);
        entityManager.remove(course);

    }
}
